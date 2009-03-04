package edu.teco.automata.generator.templates;

import java.util.HashMap;

import edu.teco.automata.generator.transformer.DataTypes;

/**
 * Mapping between types in the Automata model and those used at runtime
 * @author dy
 *
 */
public class TypesMap {
   public static final HashMap<String, String> typesMap = 
      new HashMap<String, String>()
   {
      private static final long serialVersionUID = 1L;

      {
         put(DataTypes.TByte  , "BYTE");
         put(DataTypes.TShort , "SHORT");
         put(DataTypes.TInt   , "INT");
         put(DataTypes.TLong  , "LONG");
         put(DataTypes.TFloat , "FLOAT");
         put(DataTypes.TDouble, "DOUBLE");
         put(DataTypes.TString, "STRING");  
         put(DataTypes.complexType, "COMPLEXTYPE"); 
      }
   };
   
   /**
    * @param name Automata type
    * @return Java runtime type
    */
   public static String getType(String name) {
      return typesMap.get(name);

   }
}
