package edu.teco.automata.generator.templates;

import java.util.HashMap;
import java.util.Stack;
import  edu.teco.automata.Automata.SimpleState;
/**
 * 
 * @author dy
 * Used to save the parent complex elements.
 */
public class CTypeStack {
   private static Stack<SimpleState> stack = new Stack<SimpleState>();
   /*
   private        String            name;
   private        String            kind;
   private        int               depth;
   private        int               lowB;
   private        int               upB;
   */
 //  private SimpleState state;
 //  private        int               beginCase;
   private static int               nextCase = 0;
   
 
   
   private static HashMap<SimpleState, Integer>   beginCase = new HashMap<SimpleState, Integer>();
   
   private static HashMap<SimpleState, Integer>   endCase = new HashMap<SimpleState, Integer>();
   
   /**
    * 
    * @param name   The name of a complex type
    * @param depth  The depth, means count of parents
    * @param label
    */
   
   public static void push(SimpleState state)
   {
	   stack.push(state); 
   }
   
   /*
   private CTypeStack(SimpleState state, int label)
   {
	  // this.beginCase = label;
	   this.state=state;
   }
   */
		  
   
   /**
    * @return Stack is empty or not
    */
   public static boolean isEmpty() {
      return stack.isEmpty();
   }
   
   /**
    * @return The name of the top element
    */

   public static void pop() {
      stack.pop();
   }
   
   public static SimpleState top() {
	      return stack.peek();
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
   public static String getCurrentPathSlash() {
      String ret = getCurrentPath();
      
      return ret == "" ? ret : (ret + "/");
   }
   
   /**
    * @return The path to the current element in Unix path style
    */
   public static String getCurrentPath() {
      String ret = "";
      for (int i = 0; i < stack.size(); i++)
         ret += "/" + stack.get(i).getName();

      return ret;
   }
   
   /**
    * Calculates current path in the tree but uses . or -> as separator, 
    * depending on the type of element: structure or pointer to a structure 
    * @return
    */
   public static String getStructPath() {
      String ret = stack.get(0).getName() + "->";
      for (int i = 1; i < stack.size(); i++)
      {
         String sep;
         if (stack.get(i).getUpperBound() > 0)
            sep = ".";
         else 
            sep = "->";
         
         if ((stack.get(i).getLowerBound() != stack.get(i).getUpperBound()) && (stack.get(i).getUpperBound() != 1))
            ret += stack.get(i).getName() + 
                   "[" + stack.get(i).getName().split("_")[0] + "_it]";
         else
            ret += stack.get(i).getName();
         
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
      endCase.clear();
      beginCase.clear();
   }
   
   /**
    * Sets the label to be able to find where a complex type ends
    * @param name  The name of the complex type
    * @param label The switch label, it is only a number
    */
   
   public static void setEndCase(SimpleState state, Integer label) {
	   if(endCase.containsKey(state)&& endCase.get(state)!=label) 
		   throw new java.lang.ArrayStoreException("child "+state.getName()+"already present!");
	  // System.out.println(getCurrentPath()+":endCase "+state.getName()+"="+label);
	   endCase.put(state, label);
   }
   /**
    * Sets the label to be able to find where a complex type begins
    * @param name  The name of the complex type
    * @param label The switch label, it is only a number
    */
   
   public static void setBeginCase(SimpleState state, Integer label) {
	   if(beginCase.containsKey(state)&& beginCase.get(state)!=label) 
		   throw new java.lang.ArrayStoreException("child "+state.getName()+"already present!");
	  // System.out.println(getCurrentPath()+":beginCase "+state.getName()+"="+label);
	   beginCase.put(state, label);
   }
   
   /**
    * @param name The name of the complex element
    * @return 
    */
   public static int getEndCase(SimpleState state){
	   if(endCase.containsKey(state))          
		   return endCase.get(state);
	   else
		   throw new java.util.NoSuchElementException("child "+state.getName()+" not found!");
		   //return -1;
   }
   /**
    * @param name The name of the complex element
    * @return 
    */
   public static int getBeginCase(SimpleState state){
	   if(beginCase.containsKey(state))          
		   return beginCase.get(state);
	   else
		   throw new java.util.NoSuchElementException("child "+state.getName()+" not found!");
		   //return -1;
   }
}
