package edu.teco.automata.generator.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// !!! Will be generated !!!
// Don't implement anything here

public class Queries {
   public static String getAction(String element, String type) {
      return (
      "//handle" + element + 
      "(TypesBin.read" + firstToUpper(type) + "(ins));");
   }

   public static boolean hasAction(String name) {
      return false;
   }
   
   public static String getCondition(String element) {
      return "";
   }

   public static String getImports() {
      return "";
   }

   public static boolean hasCondition(String element) {
      return false;
   }
   
   public static boolean isNeeded(String name) {
      return true;
   }

   public static void init() {

   }
   
   private static String firstToUpper(String str) {
      Matcher m = Pattern.compile("([a-z])").matcher(str);
      StringBuffer sb = new StringBuffer();
      if (m.find(0))
      {
         m.appendReplacement(sb, m.group(1).toUpperCase());
      }
      str = m.appendTail(sb).toString();
      return str;
   }
}

