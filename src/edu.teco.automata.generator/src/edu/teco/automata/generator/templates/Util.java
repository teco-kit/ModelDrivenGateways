package edu.teco.automata.generator.templates;

/**
 * Class is used to do some calculations not available in Xtend/XPand
 * 
 * @author dy
 * 
 */
public class Util {

   /**
    * Calculates the count of bytes needed to save a number in the range min max
    * @param min the minimal value of the number
    * @param max the maximal value of the number
    * @return Bytes needed to represent the number
    */
   public static int byteLen(Integer min, Integer max) {
      return (int) Math.ceil((double) bitsLen(min, max) / 8.0);
   }

   /**
    * Calculates the count of bytes needed to save a number in the range min max
    * but with stepping
    * @param min the minimal value of the number
    * @param max the maximal value of the number
    * @param stepping stepping for the value
    * @return Bytes needed to represent the number
    */
   public static int byteLen(Integer min, Integer max, Integer stepping) {
      return (int) Math.ceil((double) bitsLen(min, max, stepping) / 8.0);
   }

   /**
    * Calculates the count of bits needed to save a number in the range min max
    * @param min the minimal value of the number
    * @param max the maximal value of the number
    * @return Bits needed to represent the number
    */
   public static int bitsLen(Integer min, Integer max) {
      int result = (int) Math.ceil(Math.log((double) Math.abs(max-min+1))
    		  / (Math.log(2.0)));
      return result;
   }

   /**
    * Calculates the count of bits needed to save a number in the range min max
    * but with stepping
    * @param min the minimal value of the number
    * @param max the maximal value of the number
    * @param stepping stepping for the value
    * @return Bits needed to represent the number
    */
   public static int bitsLen(Integer min, Integer max, Integer stepping) {

      if (stepping <= 0) return bitsLen(min, max);

      // log((max - min) + 1) / stepping round up
      int result = (int) Math.ceil(Math.log((double) Math.abs(max- min) + 1)
            / (Math.log(2.0) * (double) stepping));
      
      //System.out.print("XXXXXXXXXXXXXXXXXXXXXX"+min+","+max+","+stepping+"="+result);
      return result;
   }

   /**
    * Calculates the count of bits needed to save a number in the range min max
    * but with stepping
    * @param min the minimal value of the number
    * @param max the maximal value of the number
    * @param stepping stepping for the value
    * @return Bits needed to represent the number
    */
   public static int bitsLenDouble(Integer min, Integer max, Double stepping) {
      if (stepping <= 0)
         return bitsLen(min, max);

      // (log((max - min)/stepping + stepping) round up) round up
      int result = (int) Math.ceil(Math.log(Math
            .ceil((((double) Math.abs(max) - Math.abs(min)) / (stepping))
                  + stepping))
            / (Math.log(2.0)));
      return result;
   }

   /**
    * Calculates the count of bytes needed to save a number in the range min max
    * but with stepping
    * @param min the minimal value of the number
    * @param max the maximal value of the number
    * @param stepping stepping for the value
    * @return Bytes needed to represent the number
    */
   public static int byteLenDouble(Integer min, Integer max, Double stepping) {
      return (int) Math.ceil((double) bitsLenDouble(min, max, stepping) / 8.0);
   }

   /**
    * Calculates the count of bits needed to represent the decimal 
    * places of a floating number  
    * @param precision
    * @return Count of bits needed
    */
   public static int fracBitsLen(Integer val) {
      int result = (int) Math.ceil(Math.log((double) (java.math.BigInteger
            .valueOf(10).pow(val).intValue()))
            / (Math.log(2.0)));
      return result;
   }

   /**
    * Calculates the count of bytes needed to represent the decimal 
    * places of a floating number  
    * @param precision
    * @return Count of bytes needed
    */
   public static int fracLen(Integer val) {
      int result = (int) Math.ceil(Math.log((double) (java.math.BigInteger
            .valueOf(10).pow(val).intValue()))
            / (Math.log(2.0) * 8));
      return result;
   }

   /**
    * Calculates a power of 2 for a number
    * @param number
    * @return 2 power number
    */
   public static int twoPow(Long val) {
      return java.math.BigInteger.valueOf(2).pow(val.intValue()).intValue();
   }
}
