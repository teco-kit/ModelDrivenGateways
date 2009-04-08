package edu.teco.automata.generator.templates;

import java.util.HashMap;
import java.util.Stack;

/**
 * 
 * @author dy
 * Used to save the parent complex elements.
 */
public class CTypeStack {
   private static Stack<CTypeStack> stack = new Stack<CTypeStack>();
   private        String            name;
   private        String            kind;
   private        int               depth;
   private        int               lowB;
   private        int               upB;
   private        int               beginCase;
   private static int               nextCase = 0;
   private static HashMap<String, Integer>   childrenSet = 
      new HashMap<String, Integer>();
   
   /**
    * 
    * @param name   The name of a complex type
    * @param depth  The depth, means count of parents
    * @param lowB   Lower bound
    * @param upB    Upper bound
    * @param kind   Only used with XSD indicates if it is element or attribute 
    * @param label
    */
   public static void push(String  name, Integer depth,
                           Integer lowB, Integer upB, String kind, Integer label) 
   {
      stack.push(new CTypeStack(name, depth, lowB, upB, kind, label));
   }
   
   private CTypeStack(String name, int depth, int lowB, int upB, 
                      String kind, Integer label) 
   {
      this.name  = name;
      this.depth = depth;
      this.lowB  = lowB;
      this.upB   = upB;
      this.kind  = kind;
      this.beginCase = label;
   }
   
   /**
    * @return Stack is empty or not
    */
   public static boolean isEmpty() {
      return stack.isEmpty();
   }
   
   /**
    * @return The name of the top element
    */
   public static String getName() {
      return stack.peek().name;
   }

   /**
    * @return The type of the top element in the stack
    */
   public static String getParentKind() {
      return stack.peek().kind;
   }
   
   /**
    * @return The depth in the hierarchy 
    */
   public static int getDepth() {
      return stack.peek().depth;
   }
   
   /**
    * @return The switch-label where this element begins 
    */
   public static int getBeginCase() {
      return stack.peek().beginCase;
   }
   
   /**
    * Remove the top element from the stack
    */
   public static void pop() {
      stack.pop();
   }
   
   /**
    * @return Lower bound
    */
   public static int getLowB() {
      return stack.peek().lowB;
   }
   
   /**
    * @return Upper bound
    */
   public static int getUpB() {
      return stack.peek().upB;
   }
   
   /**
    * @return The size of the stack
    */
   public static int size() {
      return stack.size();
   }
   
   /**
    * Remove all elements from the stack
    */
   public static void clear() {
      stack.clear();
   }
   
   /**
    * @return the path to the current element in Unix path style 
    * adds a trailing / at the end if not empty 
    */
   public static String getCurrentPath() {
      String ret = getCurrentParentPath();
      
      return ret == "" ? ret : (ret + "/");
   }
   
   /**
    * @return The path to the current element in Unix path style
    */
   public static String getCurrentParentPath() {
      String ret = "";
      for (int i = 0; i < stack.size(); i++)
         ret += "/" + stack.get(i).name;

      return ret;
   }
   
   /**
    * Calculates current path in the tree but uses . or -> as separator, 
    * depending on the type of element: structure or pointer to a structure 
    * @return
    */
   public static String getStructPath() {
      String ret = stack.get(0).name + "->";
      for (int i = 1; i < stack.size(); i++)
      {
         String sep;
         if (stack.get(i).upB > 0)
            sep = ".";
         else 
            sep = "->";
         
         if ((stack.get(i).lowB != stack.get(i).upB) && (stack.get(i).upB != 1))
            ret += stack.get(i).name + 
                   "[" + stack.get(i).name.split("_")[0] + "_it]";
         else
            ret += stack.get(i).name;
         
         //if (i < (stack.size() - 1))
            ret += sep;
      }

      return ret;
   }
   
   /**
    * Just a counter used to increment the switch cases
    * @return the next place
    */
   public static int getNextCase() { 
      return nextCase++;
   }
   
   /**
    * Only increment
    */
   public static void incCase() { 
      nextCase++;
   }
   
   /**
    * Start from the beginning
    */
   public static void resetCase() { 
      nextCase = 0;
   }
   
   /**
    * Sets the label to be able to find where a complex type ends
    * @param name  The name of the complex type
    * @param label The switch label, it is only a number
    */
   public static void setEndCase(String name, Integer label) {
      childrenSet.put(name, label);
   }
   
   /**
    * @param name The name of the complex element
    * @return 
    */
   public static int getEndCase(String name) {
	 //  if(childrenSet.containsKey(name))          
		   return childrenSet.get(name);
	 //  else
	//	   return -1;
   }
   
}
