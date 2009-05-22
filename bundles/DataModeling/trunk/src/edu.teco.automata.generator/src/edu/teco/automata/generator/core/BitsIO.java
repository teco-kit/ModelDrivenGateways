package edu.teco.automata.generator.core;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.eclipse.cdt.core.parser.EndOfFileException;

public class BitsIO {

   private OutputStream os = null;
   private InputStream is = null;
   private byte o_lastByte = 0;
   private byte o_currBit = 0;
   private byte i_lastByte = 0;
   private byte i_currBit = 0;
   
   public BitsIO(InputStream is) {
      this.is = is;
   }
   
   public BitsIO(OutputStream os) {
      this.os = os;
   }
   
   public BitsIO(InputStream is, OutputStream os) {
      this.is = is;
      this.os = os;
   }

   public boolean read_bit() throws IOException{
      byte val[] = new byte[1];
      read_bits(val, 1);
      
      return val[0] == 1 ? true : false;
   }

   public void write_true() throws IOException {
      byte val[] = { 1 };
      write_bits(val, 1);
   }

   public void write_false() throws IOException {
      byte val[] = { 0 };
      write_bits(val, 1);
   }

   /*
    * ========================================================================
    * 
    * 
    * ========================================================================
    */
   public int write_bits(byte bits[], int bits_len) throws IOException {
     
      int  bytes = bits_len / 8;
      byte rest_len = (byte) (bits_len % 8);
      int  ret = 0;
      
      System.out.print("w["+bits_len+"]:");
      
      for(int i=0;i<bits_len;i+=8)
      { 
    	  System.out.print(""+Integer.toBinaryString(bits[i/8]));
      }
      System.out.println("");
      
      if (o_currBit > 0) {
         byte byteA[] = new byte[1];

         for (int i = 0; i < bytes; i++) {
            byteA[0] = (byte) ((bits[i] >> o_currBit) | (o_lastByte));
            o_lastByte = low_n_bits_shift(bits[i], o_currBit);
            os.write(byteA);
            ret++;
         }

         if (rest_len > 0) {

            /* set rest of the bits to zero */
            bits[bytes] = low_n_bits(bits[bytes], rest_len);

            if (rest_len + o_currBit > 8) {
               byteA[0] = (byte) ((bits[bytes] >> (rest_len + o_currBit - 8)) | (o_lastByte));
               o_lastByte = low_n_bits_shift(bits[bytes], (byte) (rest_len
                     + o_currBit - 8));
               o_currBit = (byte) ((rest_len + o_currBit) - 8);
               os.write(byteA);
               ret++;
            } else if (rest_len + o_currBit == 8) {
               byteA[0] = (byte) (bits[bytes] | o_lastByte);

               o_lastByte = 0;
               o_currBit = 0;
               os.write(byteA);
               ret++;
            } else {
               o_lastByte |= bits[bytes] << (8 - o_currBit - rest_len);
               o_currBit += rest_len;
            }
         }

         return ret;
      } else {
         if (bytes > 0) {
            os.write(bits, 0, bytes);
            ret += bytes;
         }

         if ((bits_len % 8) > 0) {
            /* set rest of the bits to zero */
            bits[bytes] = low_n_bits(bits[bytes], rest_len);
            o_lastByte = (byte) (bits[bytes] << (8 - rest_len));
            o_currBit = rest_len;
         }

         return ret;
      }
   }

   /*
    * ========================================================================
    * 
    * 
    * ========================================================================
    */
   public void write_finish() throws IOException {
      if (o_currBit > 0) {
         os.write(o_lastByte);
      }
      o_lastByte = 0;
      o_currBit = 0;
   }

   /*
    * ========================================================================
    * 
    * 
    * ========================================================================
    */
   public int read_bits(byte dst_buf[], int bits_len) throws IOException {
      int bytes = bits_len / 8;
      byte rest_len = (byte) (bits_len % 8);
      int byte_idx = 0;
      byte b[] = new byte[1];
      int ret = 0, res;
      
      if (i_currBit > 0) {
         if (i_currBit >= bits_len) {
            b[0] = up_n_bits_shift(i_lastByte, rest_len);
            dst_buf[byte_idx] = b[0];
            i_lastByte <<= rest_len;
            i_currBit -= rest_len;
         } else {
            for (; bytes > 0; bytes--) {
               byte tmp_byte;

               res = is.read(b);
               if (res < 0) {
                  throw new EOFException();
               }
               ret += res;
               tmp_byte = low_n_bits_shift(b[0], i_currBit);
               b[0] = (byte) (up_n_bits_shift(b[0], (byte) (8 - i_currBit)) | i_lastByte);
               dst_buf[byte_idx++] = b[0];
               i_lastByte = tmp_byte;
            }
            if (rest_len > 0) {
               if (i_currBit >= rest_len) {
                  b[0] = up_n_bits_shift(i_lastByte, rest_len);
                  dst_buf[byte_idx] = b[0];
                  i_lastByte <<= rest_len;
                  i_currBit -= rest_len;
               } else {
                  byte tmp_byte;

                  res = is.read(b);
                  if (res < 0) {
                     System.out.println("Error reading. EOF");
                     return (-1);
                  }
                  ret += res;
                  tmp_byte = low_n_bits_shift(b[0],
                        (byte) (8 - rest_len + i_currBit));

                  b[0] = (byte) (up_n_bits_shift(b[0],
                        (byte) (rest_len - i_currBit)) | (i_lastByte >> (8 - rest_len)));

                  dst_buf[byte_idx] = b[0];
                  i_lastByte = tmp_byte;
                  i_currBit = (byte) (8 - rest_len + i_currBit);
               }
            }
         }

         return ret;
      } else {
         if (bytes > 0) {
            res = is.read(dst_buf, 0, bytes);
            if (res < 0) {
               throw new IOException("Error reading. EOF");
            }
            ret += res;
         }

         if (rest_len > 0) {

            res = is.read(b);

            if (res < 0) {
            	throw new IOException("Error reading. EOF");
            }
            ret += res;
            i_lastByte = low_n_bits_shift(b[0], (byte) (8 - rest_len));

            b[0] = up_n_bits_shift(b[0], rest_len);
            dst_buf[byte_idx] = b[0];
            i_currBit = (byte) (8 - rest_len);
         }

         return ret;
      }
   }

   private byte low_n_bits(byte b, byte n) {
      return (byte) ((b & ((1 << (n)) & 0xff) - 1));
   }

   private byte up_n_bits(byte b, byte n) {
      return (byte) (b & ~((1 << (8 - n)) - 1));
   }

   private byte low_n_bits_shift(byte b, byte n) {
      return (byte) ((low_n_bits(b, n) & 0xff)<< (8 - n));
   }

   private byte up_n_bits_shift(byte b, byte n) {
      return (byte) ((up_n_bits(b, n) & 0xff) >>> (8 - n));
   }

}
