/*
   minizip.c
   Version 1.01e, February 12th, 2005

   Copyright (C) 1998-2005 Gilles Vollant
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <errno.h>
#include <fcntl.h>

#ifdef unix
# include <unistd.h>
# include <utime.h>
# include <sys/types.h>
# include <sys/stat.h>
#else
# include <direct.h>
# include <io.h>
#endif

#include "ziplib-files/zip.h"
#include "ziplib-files/unzip.h"

#ifdef WIN32
#define USEWIN32IOAPI
#include "ziplib-files/iowin32.h"
#include "ziplib-files/ioapi.h"
#endif

#define CASESENSITIVITY (0)
#define WRITEBUFFERSIZE (16384)
#define MAXFILENAME (256)

#ifdef WIN32
uLong
filetime (f, tmzip, dt)
     char *f;                   /* name of file to get info on */
     tm_zip *tmzip;             /* return value: access, modific. and creation times */
     uLong *dt;                 /* dostime */
{
  int ret = 0;
  {
    FILETIME ftLocal;
    HANDLE hFind;
    WIN32_FIND_DATA ff32;

    hFind = FindFirstFile (f, &ff32);
    if (hFind != INVALID_HANDLE_VALUE)
      {
        FileTimeToLocalFileTime (&(ff32.ftLastWriteTime), &ftLocal);
        FileTimeToDosDateTime (&ftLocal, ((LPWORD) dt) + 1,
                               ((LPWORD) dt) + 0);
        FindClose (hFind);
        ret = 1;
      }
  }
  return ret;
}
#else
#ifdef unix
uLong
filetime (f, tmzip, dt)
     char *f;                   /* name of file to get info on */
     tm_zip *tmzip;             /* return value: access, modific. and creation times */
     uLong *dt;                 /* dostime */
{
  int ret = 0;
  struct stat s;                /* results of stat() */
  struct tm *filedate;
  time_t tm_t = 0;

  if (strcmp (f, "-") != 0)
    {
      char name[MAXFILENAME + 1];
      int len = strlen (f);
      if (len > MAXFILENAME)
        len = MAXFILENAME;

      strncpy (name, f, MAXFILENAME - 1);
      /* strncpy doesnt append the trailing NULL, of the string is too long. */
      name[MAXFILENAME] = '\0';

      if (name[len - 1] == '/')
        name[len - 1] = '\0';
      /* not all systems allow stat'ing a file with / appended */
      if (stat (name, &s) == 0)
        {
          tm_t = s.st_mtime;
          ret = 1;
        }
    }
  filedate = localtime (&tm_t);

  tmzip->tm_sec = filedate->tm_sec;
  tmzip->tm_min = filedate->tm_min;
  tmzip->tm_hour = filedate->tm_hour;
  tmzip->tm_mday = filedate->tm_mday;
  tmzip->tm_mon = filedate->tm_mon;
  tmzip->tm_year = filedate->tm_year;

  return ret;
}
#else
uLong
filetime (f, tmzip, dt)
     char *f;                   /* name of file to get info on */
     tm_zip *tmzip;             /* return value: access, modific. and creation times */
     uLong *dt;                 /* dostime */
{
  return 0;
}
#endif
#endif




int
check_exist_file (filename)
     const char *filename;
{
  FILE *ftestexist;
  int ret = 1;
  ftestexist = fopen (filename, "rb");
  if (ftestexist == NULL)
    ret = 0;
  else
    fclose (ftestexist);
  return ret;
}


/* calculate the CRC32 of a file,
   because to encrypt a file, we need known the CRC32 of the file before */
int
getFileCrc (const char *filenameinzip, void *buf, unsigned long size_buf,
            unsigned long *result_crc)
{
  unsigned long calculate_crc = 0;
  int err = ZIP_OK;
  FILE *fin = fopen (filenameinzip, "rb");
  unsigned long size_read = 0;
  unsigned long total_read = 0;
  if (fin == NULL)
    {
      err = ZIP_ERRNO;
    }

  if (err == ZIP_OK)
    do
      {
        err = ZIP_OK;
        size_read = (int) fread (buf, 1, size_buf, fin);
        if (size_read < size_buf)
          if (feof (fin) == 0)
            {
              printf ("error in reading %s\n", filenameinzip);
              err = ZIP_ERRNO;
            }

        if (size_read > 0)
          calculate_crc = crc32 (calculate_crc, buf, size_read);
        total_read += size_read;

      }
    while ((err == ZIP_OK) && (size_read > 0));

  if (fin)
    fclose (fin);

  *result_crc = calculate_crc;
  printf ("file %s crc %x\n", filenameinzip, calculate_crc);
  return err;
}

int
do_zip (argc, argv)
     int argc;
     char *argv[];
{
  int i;
  int opt_overwrite = 0;
  int opt_compress_level = Z_DEFAULT_COMPRESSION;
  int zipfilenamearg = 0;
  char filename_try[MAXFILENAME + 16];
  int zipok;
  int err = 0;
  int size_buf = 0;
  void *buf = NULL;
  const char *password = NULL;


  if (argc == 1)
    {
      return -1;
    }
  else
    {
      for (i = 1; i < argc; i++)
        {
          if ((*argv[i]) == '-')
            {
              const char *p = argv[i] + 1;

              while ((*p) != '\0')
                {
                  char c = *(p++);;
                  if ((c == 'o') || (c == 'O'))
                    opt_overwrite = 1;
                  if ((c == 'a') || (c == 'A'))
                    opt_overwrite = 2;
                  if ((c >= '0') && (c <= '9'))
                    opt_compress_level = c - '0';

                  if (((c == 'p') || (c == 'P')) && (i + 1 < argc))
                    {
                      password = argv[i + 1];
                      i++;
                    }
                }
            }
          else if (zipfilenamearg == 0)
            zipfilenamearg = i;
        }
    }

  size_buf = WRITEBUFFERSIZE;
  buf = (void *) malloc (size_buf);
  if (buf == NULL)
    {
      printf ("Error allocating memory\n");
      return ZIP_INTERNALERROR;
    }

  if (zipfilenamearg == 0)
    zipok = 0;
  else
    {
      int i, len;
      int dot_found = 0;

      zipok = 1;
      strncpy (filename_try, argv[zipfilenamearg], MAXFILENAME - 1);
      /* strncpy doesnt append the trailing NULL, of the string is too long. */
      filename_try[MAXFILENAME] = '\0';

      len = (int) strlen (filename_try);
      for (i = 0; i < len; i++)
        if (filename_try[i] == '.')
          dot_found = 1;

      if (dot_found == 0)
        strcat (filename_try, ".zip");

      if (opt_overwrite == 2)
        {
          /* if the file don't exist, we not append file */
          if (check_exist_file (filename_try) == 0)
            opt_overwrite = 1;
        }
      else if (opt_overwrite == 0)
        if (check_exist_file (filename_try) != 0)
          {
            char rep = 0;
            do
              {
                char answer[128];
                int ret;
                printf
                  ("The file %s exists. Overwrite ? [y]es, [n]o, [a]ppend : ",
                   filename_try);
                ret = scanf ("%1s", answer);
                if (ret != 1)
                  {
                    exit (EXIT_FAILURE);
                  }
                rep = answer[0];
                if ((rep >= 'a') && (rep <= 'z'))
                  rep -= 0x20;
              }
            while ((rep != 'Y') && (rep != 'N') && (rep != 'A'));
            if (rep == 'N')
              zipok = 0;
            if (rep == 'A')
              opt_overwrite = 2;
          }
    }

  if (zipok == 1)
    {
      zipFile zf;
      int errclose;
#        ifdef USEWIN32IOAPI
      zlib_filefunc_def ffunc;
      fill_win32_filefunc (&ffunc);
      zf =
        zipOpen2 (filename_try, (opt_overwrite == 2) ? 2 : 0, NULL, &ffunc);
#        else
      zf = zipOpen (filename_try, (opt_overwrite == 2) ? 2 : 0);
#        endif

      if (zf == NULL)
        {
          printf ("error opening %s\n", filename_try);
          err = ZIP_ERRNO;
        }
      else
        printf ("creating %s\n", filename_try);

      for (i = zipfilenamearg + 1; (i < argc) && (err == ZIP_OK); i++)
        {
          if (!((((*(argv[i])) == '-') || ((*(argv[i])) == '/')) &&
                ((argv[i][1] == 'o') || (argv[i][1] == 'O') ||
                 (argv[i][1] == 'a') || (argv[i][1] == 'A') ||
                 (argv[i][1] == 'p') || (argv[i][1] == 'P') ||
                 ((argv[i][1] >= '0') || (argv[i][1] <= '9'))) &&
                (strlen (argv[i]) == 2)))
            {
              FILE *fin;
              int size_read;
              const char *filenameinzip = argv[i];
              zip_fileinfo zi;
              unsigned long crcFile = 0;

              zi.tmz_date.tm_sec = zi.tmz_date.tm_min = zi.tmz_date.tm_hour =
                zi.tmz_date.tm_mday = zi.tmz_date.tm_mon =
                zi.tmz_date.tm_year = 0;
              zi.dosDate = 0;
              zi.internal_fa = 0;
              zi.external_fa = 0;
              filetime (filenameinzip, &zi.tmz_date, &zi.dosDate);

/*
                err = zipOpenNewFileInZip(zf,filenameinzip,&zi,
                                 NULL,0,NULL,0,NULL / * comment * /,
                                 (opt_compress_level != 0) ? Z_DEFLATED : 0,
                                 opt_compress_level);
*/
              if ((password != NULL) && (err == ZIP_OK))
                err = getFileCrc (filenameinzip, buf, size_buf, &crcFile);

              err = zipOpenNewFileInZip3 (zf, filenameinzip, &zi,
                                          NULL, 0, NULL, 0,
                                          NULL /* comment */ ,
                                          (opt_compress_level !=
                                           0) ? Z_DEFLATED : 0,
                                          opt_compress_level, 0,
                                          /* -MAX_WBITS, DEF_MEM_LEVEL, Z_DEFAULT_STRATEGY, */
                                          -MAX_WBITS, DEF_MEM_LEVEL,
                                          Z_DEFAULT_STRATEGY, password,
                                          crcFile);

              if (err != ZIP_OK)
                printf ("error in opening %s in zipfile\n", filenameinzip);
              else
                {
                  fin = fopen (filenameinzip, "rb");
                  if (fin == NULL)
                    {
                      err = ZIP_ERRNO;
                      printf ("error in opening %s for reading\n",
                              filenameinzip);
                    }
                }

              if (err == ZIP_OK)
                do
                  {
                    err = ZIP_OK;
                    size_read = (int) fread (buf, 1, size_buf, fin);
                    if (size_read < size_buf)
                      if (feof (fin) == 0)
                        {
                          printf ("error in reading %s\n", filenameinzip);
                          err = ZIP_ERRNO;
                        }

                    if (size_read > 0)
                      {
                        err = zipWriteInFileInZip (zf, buf, size_read);
                        if (err < 0)
                          {
                            printf ("error in writing %s in the zipfile\n",
                                    filenameinzip);
                          }

                      }
                  }
                while ((err == ZIP_OK) && (size_read > 0));

              if (fin)
                fclose (fin);

              if (err < 0)
                err = ZIP_ERRNO;
              else
                {
                  err = zipCloseFileInZip (zf);
                  if (err != ZIP_OK)
                    printf ("error in closing %s in the zipfile\n",
                            filenameinzip);
                }
            }
        }
      errclose = zipClose (zf, NULL);
      if (errclose != ZIP_OK)
        printf ("error in closing %s\n", filename_try);
    }
  else
    {
      return -1;
    }

  free (buf);
  return 0;
}



///////////////////////////
// unzip
///////////////////////////

/* change_file_date : change the date/time of a file
    filename : the filename of the file where date/time must be modified
    dosdate : the new date at the MSDos format (4 bytes)
    tmu_date : the SAME new date at the tm_unz format */
void
change_file_date (filename, dosdate, tmu_date)
     const char *filename;
     uLong dosdate;
     tm_unz tmu_date;
{
#ifdef WIN32
  HANDLE hFile;
  FILETIME ftm, ftLocal, ftCreate, ftLastAcc, ftLastWrite;

  hFile = CreateFile (filename, GENERIC_READ | GENERIC_WRITE,
                      0, NULL, OPEN_EXISTING, 0, NULL);
  GetFileTime (hFile, &ftCreate, &ftLastAcc, &ftLastWrite);
  DosDateTimeToFileTime ((WORD) (dosdate >> 16), (WORD) dosdate, &ftLocal);
  LocalFileTimeToFileTime (&ftLocal, &ftm);
  SetFileTime (hFile, &ftm, &ftLastAcc, &ftm);
  CloseHandle (hFile);
#else
#ifdef unix
  struct utimbuf ut;
  struct tm newdate;
  newdate.tm_sec = tmu_date.tm_sec;
  newdate.tm_min = tmu_date.tm_min;
  newdate.tm_hour = tmu_date.tm_hour;
  newdate.tm_mday = tmu_date.tm_mday;
  newdate.tm_mon = tmu_date.tm_mon;
  if (tmu_date.tm_year > 1900)
    newdate.tm_year = tmu_date.tm_year - 1900;
  else
    newdate.tm_year = tmu_date.tm_year;
  newdate.tm_isdst = -1;

  ut.actime = ut.modtime = mktime (&newdate);
  utime (filename, &ut);
#endif
#endif
}


/* mymkdir and change_file_date are not 100 % portable
   As I don't know well Unix, I wait feedback for the unix portion */

int
mymkdir (dirname)
     const char *dirname;
{
  int ret = 0;
#ifdef WIN32
  ret = mkdir (dirname);
#else
#ifdef unix
  ret = mkdir (dirname, 0775);
#endif
#endif
  return ret;
}

int
makedir (newdir)
     char *newdir;
{
  char *buffer;
  char *p;
  int len = (int) strlen (newdir);

  if (len <= 0)
    return 0;

  buffer = (char *) malloc (len + 1);
  strcpy (buffer, newdir);

  if (buffer[len - 1] == '/')
    {
      buffer[len - 1] = '\0';
    }
  if (mymkdir (buffer) == 0)
    {
      free (buffer);
      return 1;
    }

  p = buffer + 1;
  while (1)
    {
      char hold;

      while (*p && *p != '\\' && *p != '/')
        p++;
      hold = *p;
      *p = 0;
      if ((mymkdir (buffer) == -1) && (errno == ENOENT))
        {
          printf ("couldn't create directory %s\n", buffer);
          free (buffer);
          return 0;
        }
      if (hold == 0)
        break;
      *p++ = hold;
    }
  free (buffer);
  return 1;
}


int
do_list (uf)
     unzFile uf;
{
  uLong i;
  unz_global_info gi;
  int err;

  err = unzGetGlobalInfo (uf, &gi);
  if (err != UNZ_OK)
    printf ("error %d with zipfile in unzGetGlobalInfo \n", err);
  printf (" Length  Method   Size  Ratio   Date    Time   CRC-32     Name\n");
  printf (" ------  ------   ----  -----   ----    ----   ------     ----\n");
  for (i = 0; i < gi.number_entry; i++)
    {
      char filename_inzip[256];
      unz_file_info file_info;
      uLong ratio = 0;
      const char *string_method;
      char charCrypt = ' ';
      err =
        unzGetCurrentFileInfo (uf, &file_info, filename_inzip,
                               sizeof (filename_inzip), NULL, 0, NULL, 0);
      if (err != UNZ_OK)
        {
          printf ("error %d with zipfile in unzGetCurrentFileInfo\n", err);
          break;
        }
      if (file_info.uncompressed_size > 0)
        ratio =
          (file_info.compressed_size * 100) / file_info.uncompressed_size;

      /* display a '*' if the file is crypted */
      if ((file_info.flag & 1) != 0)
        charCrypt = '*';

      if (file_info.compression_method == 0)
        string_method = "Stored";
      else if (file_info.compression_method == Z_DEFLATED)
        {
          uInt iLevel = (uInt) ((file_info.flag & 0x6) / 2);
          if (iLevel == 0)
            string_method = "Defl:N";
          else if (iLevel == 1)
            string_method = "Defl:X";
          else if ((iLevel == 2) || (iLevel == 3))
            string_method = "Defl:F";   /* 2:fast , 3 : extra fast */
        }
      else
        string_method = "Unkn. ";

      printf
        ("%7lu  %6s%c%7lu %3lu%%  %2.2lu-%2.2lu-%2.2lu  %2.2lu:%2.2lu  %8.8lx   %s\n",
         file_info.uncompressed_size, string_method, charCrypt,
         file_info.compressed_size, ratio,
         (uLong) file_info.tmu_date.tm_mon + 1,
         (uLong) file_info.tmu_date.tm_mday,
         (uLong) file_info.tmu_date.tm_year % 100,
         (uLong) file_info.tmu_date.tm_hour,
         (uLong) file_info.tmu_date.tm_min, (uLong) file_info.crc,
         filename_inzip);
      if ((i + 1) < gi.number_entry)
        {
          err = unzGoToNextFile (uf);
          if (err != UNZ_OK)
            {
              printf ("error %d with zipfile in unzGoToNextFile\n", err);
              break;
            }
        }
    }

  return 0;
}


int
do_extract_currentfile (uf, popt_extract_without_path, popt_overwrite,
                        password)
     unzFile uf;
     const int *popt_extract_without_path;
     int *popt_overwrite;
     const char *password;
{
  char filename_inzip[256];
  char *filename_withoutpath;
  char *p;
  int err = UNZ_OK;
  FILE *fout = NULL;
  void *buf;
  uInt size_buf;

  unz_file_info file_info;
  uLong ratio = 0;
  err =
    unzGetCurrentFileInfo (uf, &file_info, filename_inzip,
                           sizeof (filename_inzip), NULL, 0, NULL, 0);

  if (err != UNZ_OK)
    {
      printf ("error %d with zipfile in unzGetCurrentFileInfo\n", err);
      return err;
    }

  size_buf = WRITEBUFFERSIZE;
  buf = (void *) malloc (size_buf);
  if (buf == NULL)
    {
      printf ("Error allocating memory\n");
      return UNZ_INTERNALERROR;
    }

  p = filename_withoutpath = filename_inzip;
  while ((*p) != '\0')
    {
      if (((*p) == '/') || ((*p) == '\\'))
        filename_withoutpath = p + 1;
      p++;
    }

  if ((*filename_withoutpath) == '\0')
    {
      if ((*popt_extract_without_path) == 0)
        {
          printf ("creating directory: %s\n", filename_inzip);
          mymkdir (filename_inzip);
        }
    }
  else
    {
      const char *write_filename;
      int skip = 0;

      if ((*popt_extract_without_path) == 0)
        write_filename = filename_inzip;
      else
        write_filename = filename_withoutpath;

      err = unzOpenCurrentFilePassword (uf, password);
      if (err != UNZ_OK)
        {
          printf ("error %d with zipfile in unzOpenCurrentFilePassword\n",
                  err);
        }

      if (((*popt_overwrite) == 0) && (err == UNZ_OK))
        {
          char rep = 0;
          FILE *ftestexist;
          ftestexist = fopen (write_filename, "rb");
          if (ftestexist != NULL)
            {
              fclose (ftestexist);
              do
                {
                  char answer[128];
                  int ret;

                  printf
                    ("The file %s exists. Overwrite ? [y]es, [n]o, [A]ll: ",
                     write_filename);
                  ret = scanf ("%1s", answer);
                  if (ret != 1)
                    {
                      exit (EXIT_FAILURE);
                    }
                  rep = answer[0];
                  if ((rep >= 'a') && (rep <= 'z'))
                    rep -= 0x20;
                }
              while ((rep != 'Y') && (rep != 'N') && (rep != 'A'));
            }

          if (rep == 'N')
            skip = 1;

          if (rep == 'A')
            *popt_overwrite = 1;
        }

      if ((skip == 0) && (err == UNZ_OK))
        {
          fout = fopen (write_filename, "wb");

          /* some zipfile don't contain directory alone before file */
          if ((fout == NULL) && ((*popt_extract_without_path) == 0) &&
              (filename_withoutpath != (char *) filename_inzip))
            {
              char c = *(filename_withoutpath - 1);
              *(filename_withoutpath - 1) = '\0';
              makedir (write_filename);
              *(filename_withoutpath - 1) = c;
              fout = fopen (write_filename, "wb");
            }

          if (fout == NULL)
            {
              printf ("error opening %s\n", write_filename);
            }
        }

      if (fout != NULL)
        {
          printf (" extracting: %s\n", write_filename);

          do
            {
              err = unzReadCurrentFile (uf, buf, size_buf);
              if (err < 0)
                {
                  printf ("error %d with zipfile in unzReadCurrentFile\n",
                          err);
                  break;
                }
              if (err > 0)
                if (fwrite (buf, err, 1, fout) != 1)
                  {
                    printf ("error in writing extracted file\n");
                    err = UNZ_ERRNO;
                    break;
                  }
            }
          while (err > 0);
          if (fout)
            fclose (fout);

          if (err == 0)
            change_file_date (write_filename, file_info.dosDate,
                              file_info.tmu_date);
        }

      if (err == UNZ_OK)
        {
          err = unzCloseCurrentFile (uf);
          if (err != UNZ_OK)
            {
              printf ("error %d with zipfile in unzCloseCurrentFile\n", err);
            }
        }
      else
        unzCloseCurrentFile (uf);       /* don't lose the error */
    }

  free (buf);
  return err;
}


int
do_extract (uf, opt_extract_without_path, opt_overwrite, password)
     unzFile uf;
     int opt_extract_without_path;
     int opt_overwrite;
     const char *password;
{
  uLong i;
  unz_global_info gi;
  int err;
  FILE *fout = NULL;

  err = unzGetGlobalInfo (uf, &gi);
  if (err != UNZ_OK)
    printf ("error %d with zipfile in unzGetGlobalInfo \n", err);

  for (i = 0; i < gi.number_entry; i++)
    {
      if (do_extract_currentfile (uf, &opt_extract_without_path,
                                  &opt_overwrite, password) != UNZ_OK)
        break;

      if ((i + 1) < gi.number_entry)
        {
          err = unzGoToNextFile (uf);
          if (err != UNZ_OK)
            {
              printf ("error %d with zipfile in unzGoToNextFile\n", err);
              break;
            }
        }
    }

  return 0;
}

int
do_extract_onefile (uf, filename, opt_extract_without_path, opt_overwrite,
                    password)
     unzFile uf;
     const char *filename;
     int opt_extract_without_path;
     int opt_overwrite;
     const char *password;
{
  int err = UNZ_OK;
  if (unzLocateFile (uf, filename, CASESENSITIVITY) != UNZ_OK)
    {
      printf ("file %s not found in the zipfile\n", filename);
      return 2;
    }

  if (do_extract_currentfile (uf, &opt_extract_without_path,
                              &opt_overwrite, password) == UNZ_OK)
    return 0;
  else
    return 1;
}


int
do_unzip (argc, argv)
     int argc;
     char *argv[];
{
  const char *zipfilename = NULL;
  const char *filename_to_extract = NULL;
  const char *password = NULL;
  char filename_try[MAXFILENAME + 16] = "";
  int i;
  int opt_do_list = 0;
  int opt_do_extract = 1;
  int opt_do_extract_withoutpath = 0;
  int opt_overwrite = 0;
  int opt_extractdir = 0;
  const char *dirname = NULL;
  unzFile uf = NULL;


  if (argc == 1)
    {
      return -1;
    }
  else
    {
      for (i = 1; i < argc; i++)
        {
          if ((*argv[i]) == '-')
            {
              const char *p = argv[i] + 1;

              while ((*p) != '\0')
                {
                  char c = *(p++);;
                  if ((c == 'l') || (c == 'L'))
                    opt_do_list = 1;
                  if ((c == 'v') || (c == 'V'))
                    opt_do_list = 1;
                  if ((c == 'x') || (c == 'X'))
                    opt_do_extract = 1;
                  if ((c == 'e') || (c == 'E'))
                    opt_do_extract = opt_do_extract_withoutpath = 1;
                  if ((c == 'o') || (c == 'O'))
                    opt_overwrite = 1;
                  if ((c == 'd') || (c == 'D'))
                    {
                      opt_extractdir = 1;
                      dirname = argv[i + 1];
                    }

                  if (((c == 'p') || (c == 'P')) && (i + 1 < argc))
                    {
                      password = argv[i + 1];
                      i++;
                    }
                }
            }
          else
            {
              if (zipfilename == NULL)
                zipfilename = argv[i];
              else if ((filename_to_extract == NULL) && (!opt_extractdir))
                filename_to_extract = argv[i];
            }
        }
    }

  if (zipfilename != NULL)
    {

#        ifdef USEWIN32IOAPI
      zlib_filefunc_def ffunc;
#        endif

      strncpy (filename_try, zipfilename, MAXFILENAME - 1);
      /* strncpy doesnt append the trailing NULL, of the string is too long. */
      filename_try[MAXFILENAME] = '\0';

#        ifdef USEWIN32IOAPI
      fill_win32_filefunc (&ffunc);
      uf = unzOpen2 (zipfilename, &ffunc);
#        else
      uf = unzOpen (zipfilename);
#        endif
      if (uf == NULL)
        {
          strcat (filename_try, ".zip");
#            ifdef USEWIN32IOAPI
          uf = unzOpen2 (filename_try, &ffunc);
#            else
          uf = unzOpen (filename_try);
#            endif
        }
    }

  if (uf == NULL)
    {
      printf ("Cannot open %s or %s.zip\n", zipfilename, zipfilename);
      return 1;
    }
  printf ("%s opened\n", filename_try);

  if (opt_do_list == 1)
    return do_list (uf);
  else if (opt_do_extract == 1)
    {
      if (opt_extractdir && chdir (dirname))
        {
          printf ("Error changing into %s, aborting\n", dirname);
          exit (-1);
        }

      if (filename_to_extract == NULL)
        return do_extract (uf, opt_do_extract_withoutpath, opt_overwrite,
                           password);
      else
        return do_extract_onefile (uf, filename_to_extract,
                                   opt_do_extract_withoutpath, opt_overwrite,
                                   password);
    }
  unzCloseCurrentFile (uf);

  return 0;
}
