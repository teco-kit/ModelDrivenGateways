package edu.teco.automata.generator.types;

public class Common {

   public static byte[] short2bytes(short val) {
      byte[] byteArray = new byte[2]; 

      byteArray[1] = (byte)(val        & 0xff);
      byteArray[0] = (byte)(val >>   8 & 0xff);

      return byteArray;
   }
   public static byte[] int2bytes(int val) {
      byte[] byteArray = new byte[4]; 

      byteArray[3] = (byte)(val        & 0xff);
      byteArray[2] = (byte)(val >>   8 & 0xff);
      byteArray[1] = (byte)(val >>  16 & 0xff);
      byteArray[0] = (byte)(val >>> 24);
      //for (int i = 0; i < Integer.SIZE; i++)
        // System.out.println("Byte " + i + " " + byteArray[i]);

      return byteArray;
   }
   public static byte[] long2bytes(long val) {
      byte[] byteArray = new byte[8]; 

      byteArray[7] = (byte)(val        & 0xff);
      byteArray[6] = (byte)(val >>   8 & 0xff);
      byteArray[5] = (byte)(val >>  16 & 0xff);
      byteArray[4] = (byte)(val >>  24 & 0xff);
      byteArray[3] = (byte)(val >>  32 & 0xff);
      byteArray[2] = (byte)(val >>  40 & 0xff);
      byteArray[1] = (byte)(val >>  48 & 0xff);
      byteArray[0] = (byte)(val >>> 56);

      return byteArray;
   }
   public static byte[] float2bytes(float fval) {
      int val = Float.floatToIntBits(fval);
      byte[] byteArray = new byte[4]; 

      byteArray[3] = (byte)(val        & 0xff);
      byteArray[2] = (byte)(val >>   8 & 0xff);
      byteArray[1] = (byte)(val >>  16 & 0xff);
      byteArray[0] = (byte)(val >>> 24);

      return byteArray;
   }
   public static byte[] double2bytes(double dval) {
      long val = Double.doubleToLongBits(dval);
      byte[] byteArray = new byte[8]; 

      byteArray[7] = (byte)(val        & 0xff);
      byteArray[6] = (byte)(val >>   8 & 0xff);
      byteArray[5] = (byte)(val >>  16 & 0xff);
      byteArray[4] = (byte)(val >>  24 & 0xff);
      byteArray[3] = (byte)(val >>  32 & 0xff);
      byteArray[2] = (byte)(val >>  40 & 0xff);
      byteArray[1] = (byte)(val >>  48 & 0xff);
      byteArray[0] = (byte)(val >>> 56);

      return byteArray;
   }
   public static byte[] char2bytes(char cval) {
      byte[] byteArray = new byte[2];
      byteArray[0]     = (byte)(cval      & 0xff);
      byteArray[1]     = (byte)(cval >> 8 & 0xff);
      return byteArray;
   }
   public static byte[] string2bytes(String sval) {
      char[] strChar   = sval.toCharArray();
      int    strLen    = strChar.length * 2;
      byte[] byteArray = new byte[strLen+4];
      
      byteArray[3] = (byte)(strLen        & 0xff);
      byteArray[2] = (byte)(strLen >>   8 & 0xff);
      byteArray[1] = (byte)(strLen >>  16 & 0xff);
      byteArray[0] = (byte)(strLen >>> 24);
      
      for (int i = 0; i < strChar.length; i++)
      {
         byte [] c = char2bytes(strChar[i]);
         byteArray[4+i*2]   = c[0];
         byteArray[4+i*2+1] = c[1];
      }
      
      return byteArray;
   }
   public static short bytes2short(byte [] val) {
      return (short)(((val[0] & 0xff) << 8) |
                      (val[1] & 0xff));
   }
   public static int bytes2int(byte [] val) {
      return
            ((val[0] & 0xff) << 24) |
            ((val[1] & 0xff) << 16) |
            ((val[2] & 0xff) << 8 ) |
            ((val[3] & 0xff)      );
   }
   public static long bytes2long(byte[] val) {
      return (long)
      ((((long)(val[0] & 0xff)) << 56) |
       (((long)(val[1] & 0xff)) << 48) |
       (((long)(val[2] & 0xff)) << 40) |
       (((long)(val[3] & 0xff)) << 32) |
       (((long)(val[4] & 0xff)) << 24) |
       (((long)(val[5] & 0xff)) << 16) |
       (((long)(val[6] & 0xff)) << 8)  |
       (((long)(val[7] & 0xff))));
   }
   public static float bytes2float(byte[] val) {
      return Float.intBitsToFloat(bytes2int(val));
   }
   public static double bytes2double(byte[] val) {
      return Double.longBitsToDouble(bytes2long(val));
   }
   public static char bytes2char(byte[] val) {
      return (char)(((val[0] & 0xff) << 8) |
                    ((val[1] & 0xff)));
   }
   public static String bytes2string(byte[] val, int strLen) {
      char[] charArr = new char[strLen/2];

      for (int i = 0; i < charArr.length; i++)
      {
         byte[] c   = new byte[2];
         c[1]       = val[i*2];
         c[0]       = val[i*2+1];
         charArr[i] = bytes2char(c);
      }
      
      return new String(charArr);
   }
}
