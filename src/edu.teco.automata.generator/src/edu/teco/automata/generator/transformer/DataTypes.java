package edu.teco.automata.generator.transformer;

import edu.teco.automata.Automata.AutomataFactory;
import edu.teco.automata.Automata.DataType;
/**
 * 
 * @author dy
 *
 */
public class DataTypes {
   
   public static final String complexType = "Automata::complexType"; 
   public static final String TByte       = "Automata::TByte";
   public static final String TShort      = "Automata::TShort";
   public static final String TInt        = "Automata::TInt";
   public static final String TLong       = "Automata::TLong";
   public static final String TFloat      = "Automata::TFloat";
   public static final String TDouble     = "Automata::TDouble";
   public static final String TString     = "Automata::TString";
   
   /**
    * 
    * @return
    */
   public static String getComplexTypeName() {
      return complexType;
   }
   
   /**
    * 
    * @param name
    * @return
    */
   public static DataType getType(String name) {
      if (name == null)
         return null;
      
      DataType ret = null;
      
      if (name == "byte")
         return AutomataFactory.eINSTANCE.createTByte();
      else if (name == "UnsignedByte")
      {
         ret = AutomataFactory.eINSTANCE.createTByte();
         ((edu.teco.automata.Automata.TByte)ret).setMin(0);
         ((edu.teco.automata.Automata.TByte)ret).setMax(
               java.math.BigInteger.valueOf(2).pow(8).intValue()-1);
         return ret;
      }
      else if (name == "short")
         return AutomataFactory.eINSTANCE.createTShort();
      else if (name == "UnsignedShort")
      {
         ret = AutomataFactory.eINSTANCE.createTShort();
         ((edu.teco.automata.Automata.TShort)ret).setMin(0);
         ((edu.teco.automata.Automata.TShort)ret).setMax(
               java.math.BigInteger.valueOf(2).pow(16).intValue()-1);
         return ret;
      }
      else if (name == "int" || name == "java.math.BigInteger")
         return AutomataFactory.eINSTANCE.createTInt();
      else if (name == "UnsignedInt")
      {
         ret = AutomataFactory.eINSTANCE.createTInt();
         ((edu.teco.automata.Automata.TInt)ret).setMin(0);
         ((edu.teco.automata.Automata.TInt)ret).setMax(
               java.math.BigInteger.valueOf(2).pow(32).intValue()-1);
         return ret;
      }
      else if (name == "long")
         return AutomataFactory.eINSTANCE.createTLong();
      else if (name == "float")
         return AutomataFactory.eINSTANCE.createTFloat();
      else if (name == "double" || name == "java.math.BigDecimal")
         return AutomataFactory.eINSTANCE.createTDouble();
      else if (name == "java.lang.String")
         return AutomataFactory.eINSTANCE.createTString();
      else
         return null;
   }
   
   /**
    * 
    * @return
    */
   public static DataType getComplexType() {
      return AutomataFactory.eINSTANCE.createcomplexType();
   }
}
