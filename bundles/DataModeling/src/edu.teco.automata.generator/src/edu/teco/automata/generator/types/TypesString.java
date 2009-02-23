package edu.teco.automata.generator.types;

import java.io.IOException;

import edu.teco.automata.generator.core.BitsIO;

public class TypesString {

	public static class BYTE implements TypeStringIntf {
		public int write(String value, BitsIO io) throws IOException {
			byte val[] = {Byte.parseByte(value.trim())};
			
			io.write_bits(val, Byte.SIZE);

			return Byte.SIZE/8;
		}
		public String read(BitsIO io) throws IOException {
		   byte val[] = {0};
		   io.read_bits(val, Byte.SIZE);
		   
			return (new Byte(val[0])).toString();
		}	
	}
	
	public static class SHORT implements TypeStringIntf {
		public int write(String value, BitsIO io) throws IOException {
			short val = Short.parseShort(value.trim());
			io.write_bits(Common.short2bytes(val), Short.SIZE);
			return Short.SIZE/8;
		}
		public String read(BitsIO io) throws IOException {
		   byte[] byteArray = new byte[Short.SIZE/8];
	      io.read_bits(byteArray, Short.SIZE);
		   return (new Short(Common.bytes2short(byteArray))).toString();
		}	
	}
	
	public static class INT implements TypeStringIntf {
		public int write(String value, BitsIO io) throws IOException {
			int val = Integer.parseInt(value.trim());
			System.out.println("INT " + val);
			io.write_bits(Common.int2bytes(val), Integer.SIZE);
			return Integer.SIZE/8;
		}
		public String read(BitsIO io) throws IOException {
		   byte[] byteArray = new byte[Integer.SIZE/8];
		   io.read_bits(byteArray, Integer.SIZE);
			return (new Integer(Common.bytes2int(byteArray))).toString();
		}	
	}
	
	public static class LONG implements TypeStringIntf {
		public int write(String value, BitsIO io) throws IOException {
			long val = Long.parseLong(value.trim());
			io.write_bits(Common.long2bytes(val), Long.SIZE);
			return Long.SIZE;
		}
		public String read(BitsIO io) throws IOException {
		   byte[] byteArray = new byte[Long.SIZE/8];
	      io.read_bits(byteArray, Long.SIZE);
			return (new Long(Common.bytes2long(byteArray)).toString());
		}	
	}
	
	public static class FLOAT implements TypeStringIntf {
		public int write(String value, BitsIO io) throws IOException {
			float val = Float.parseFloat(value.trim());
			io.write_bits(Common.float2bytes(val), Integer.SIZE);
			return Integer.SIZE/8;
		}
		public String read(BitsIO io) throws IOException {
		   byte[] byteArray = new byte[Integer.SIZE/8];
	      io.read_bits(byteArray, Integer.SIZE);

			return (new Float(Common.bytes2float(byteArray))).toString();
		}	
	}
	
	public static class DOUBLE implements TypeStringIntf {
		public int write(String value, BitsIO io) throws IOException {
			double val = Double.parseDouble(value.trim());
			io.write_bits(Common.double2bytes(val), Long.SIZE);
			return Long.SIZE/8;
		}
		public String read(BitsIO io) throws IOException {
		   byte[] byteArray = new byte[Long.SIZE/8];
		   io.read_bits(byteArray, Long.SIZE);
		   return (new Double(Common.bytes2double(byteArray))).toString();
		}	
	}
	
	public static class STRING implements TypeStringIntf {
		public int write(String value, BitsIO io) throws IOException {
		   io.write_bits(Common.int2bytes(value.length()), Integer.SIZE);
			io.write_bits(Common.string2bytes(value), (value.length()*2+4)*8);
			System.out.println("String " + value + " len " + value.length());
			return value.length()*2+4;
		}
		public String read(BitsIO io) throws IOException {
		   byte[] byteArray = new byte[Integer.SIZE/8];
         io.read_bits(byteArray, Integer.SIZE);
         int strLen       = Common.bytes2int(byteArray);
         byteArray        = new byte[strLen];
         io.read_bits(byteArray, strLen);
         System.out.println("String " + Common.bytes2string(byteArray, strLen) + " len " + strLen);
		   return Common.bytes2string(byteArray, strLen);
		}	
	}
	
	public static class CHAR implements TypeStringIntf {
	   public int write(String value, BitsIO io) throws IOException {
	      io.write_bits(Common.char2bytes(value.trim().charAt(0)), Character.SIZE);
	      return Character.SIZE/8;
	   }
	   public String read(BitsIO io) throws IOException {
	      byte[] byteArray = new byte[Character.SIZE/8];
	      io.read_bits(byteArray, Character.SIZE);      
	      return (new Character(Common.bytes2char(byteArray))).toString();
	   }  
	}
	
	public static class COMPLEXTYPE implements TypeStringIntf {

      public String read(BitsIO io) throws IOException {
         return null;
      }

      public int write(String value, BitsIO io) throws IOException {
         return 0;
      }
	
	}
}
