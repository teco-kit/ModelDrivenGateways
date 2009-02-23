package edu.teco.automata.generator.templates;

import java.util.HashMap;

public class NeededMap {
   private static HashMap<String, Boolean> needed = 
      new HashMap<String, Boolean>();
   
   private static boolean isCurrNeeded = false;

   public static void setName(String name, Boolean isNeeded) {
      needed.put(name, isNeeded);
   }
   
   public static boolean isNeeded(String name) {
      
      if (needed.containsKey(name))
         return needed.get(name);
      else if (name.contains("/"))
      {
         String[] states = name.split("/");
         if (needed.containsKey(states[states.length-1]))
            return true;
         else
            return false;
      }
      else
         return false;
   }

   public static boolean isCurrNeeded() {
      return isCurrNeeded;
   }

   public static void setCurrNeeded(Boolean isCurrNeeded) {
      NeededMap.isCurrNeeded = isCurrNeeded;
   }
}
