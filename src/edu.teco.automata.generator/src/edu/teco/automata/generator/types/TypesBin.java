package edu.teco.automata.generator.types;
import java.io.IOException;

import edu.teco.automata.generator.core.BitsIO;
   
public class TypesBin {
      public static int writeByte(byte value, BitsIO io) 
      throws IOException 
      {
         byte b[] = {value};
         io.write_bits(b, Byte.SIZE);
         return Byte.SIZE/8;
      }
      public static byte readByte(BitsIO io) 
      throws IOException {
         byte [] b = new byte [1];
         io.read_bits(b, Byte.SIZE);
         return b[0];
      }  
      public static int writeShort(short value, BitsIO io) 
      throws IOException {
         io.write_bits(Common.short2bytes(value), Short.SIZE);
         return Short.SIZE/8;
      }
      public static short readShort(BitsIO io) 
      throws IOException {
         byte[] byteArray = new byte[Short.SIZE/8];
         io.read_bits(byteArray, Short.SIZE);
         return (Common.bytes2short(byteArray));
      }  
      public static int writeInt(int value, BitsIO io) 
      throws IOException {
         io.write_bits(Common.int2bytes(value), Integer.SIZE);
         return Integer.SIZE/8;
      }
      public static int readInt(BitsIO io) 
      throws IOException {
         byte[] byteArray = new byte[Integer.SIZE/8];
         io.read_bits(byteArray, Integer.SIZE);
         return Common.bytes2int(byteArray);
      }  
      public static int writeLong(long value, BitsIO io) 
      throws IOException {
         io.write_bits(Common.long2bytes(value), Long.SIZE);
         return Long.SIZE/8;
      }
      public static long readLong(BitsIO io) 
      throws IOException {
         byte[] byteArray = new byte[Long.SIZE/8];
         io.read_bits(byteArray, Long.SIZE);
         return Common.bytes2long(byteArray);
      }  
      public static int writeFloat(float value, BitsIO io) 
      throws IOException {
         io.write_bits(Common.float2bytes(value), Integer.SIZE);
         return Integer.SIZE/8;
      }
      public static float readFloat(BitsIO io) 
      throws IOException {
         byte[] byteArray = new byte[Integer.SIZE/8];
         io.read_bits(byteArray, Integer.SIZE);
         return Common.bytes2float(byteArray);
      }  
      public static int writeDouble(double value, BitsIO io) 
      throws IOException {
         io.write_bits(Common.double2bytes(value), Long.SIZE);
         return Long.SIZE/8;
      }
      public static double readDouble(BitsIO io) 
      throws IOException {
         byte[] byteArray = new byte[Long.SIZE/8];
         io.read_bits(byteArray, Long.SIZE);
         return Common.bytes2double(byteArray);
      }  
      public static int writeString(String value, BitsIO io) 
      throws IOException {
         io.write_bits(Common.string2bytes(value), (value.length()*2+4)*8);
         return value.length()*2+4;
      }
      public static String readString(BitsIO io) 
      throws IOException {
         byte[] byteArray = new byte[Integer.SIZE/8];
         io.read_bits(byteArray, Integer.SIZE);
         int strLen       = Common.bytes2int(byteArray);
         byteArray        = new byte[strLen];
         io.read_bits(byteArray, strLen*8);
         return Common.bytes2string(byteArray, strLen);
      }  
      public static int writeChar(char value, BitsIO io) 
      throws IOException {
         io.write_bits(Common.char2bytes(value), Character.SIZE);
         return Character.SIZE/8;
      }
      public static char readChar(BitsIO io) 
      throws IOException {
         byte[] byteArray = new byte[Character.SIZE/8];
         io.read_bits(byteArray, Character.SIZE);      
         return Common.bytes2char(byteArray);
      }
      public static int sizeByte() {
         return Byte.SIZE/8;
      }
      public static int sizeShort() {
         return Short.SIZE/8;
      }
      public static int sizeInt() {
         return Integer.SIZE/8;
      }
      public static int sizeLong() {
         return Long.SIZE/8;
      }
      public static int sizeFloat() {
         return Float.SIZE/8;
      }
      public static int sizeDouble() {
         return Double.SIZE/8;
      }
      public static int sizeChar() {
         return Character.SIZE/8;
      }
}