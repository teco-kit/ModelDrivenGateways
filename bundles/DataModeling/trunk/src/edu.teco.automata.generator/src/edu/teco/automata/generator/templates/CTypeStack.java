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
   private static Stack<CTypeStack> stack = new Stack<CTypeStack>();
   /*
   private        String            name;
   private        String            kind;
   private        int               depth;
   private        int               lowB;
   private        int               upB;
   */
   private SimpleState state;
   private        int               beginCase;
   private static int               nextCase = 0;
   
 
   
   private static HashMap<SimpleState, Integer>   childrenSet = 
      new HashMap<SimpleState, Integer>();
   
   /**
    * 
    * @param name   The name of a complex type
    * @param depth  The depth, means count of parents
    * @param label
    */
   
   public static void push(SimpleState state,  Integer label)
   {
	   stack.push(new CTypeStack(state,label)); 
   }
   
   private CTypeStack(SimpleState state, int label)
   {
	   this.beginCase = label;
	   this.state=state;
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
      return stack.peek().state.getName();
   }

   /**
    * @return The type of the top element in the stack
    */
   public static String getParentKind() {
      return stack.peek().state.getKind();
   }
   
   /**
    * @return The depth in the hierarchy 
    */
   public static int getDepth() {
      return stack.peek().state.getDepth();
   }
   
   /**
    * @return The switch-label where this element begins 
    */
   public static SimpleState getState()
   {
	   return stack.peek().state;
   }
   
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
      return stack.peek().state.getLowerBound();
   }
   
   /**
    * @return Upper bound
    */
   public static int getUpB() {
	    return stack.peek().state.getUpperBound();
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
         ret += "/" + stack.get(i).state.getName();

      return ret;
   }
   
   /**
    * Calculates current path in the tree but uses . or -> as separator, 
    * depending on the type of element: structure or pointer to a structure 
    * @return
    */
   public static String getStructPath() {
      String ret = stack.get(0).state.getName() + "->";
      for (int i = 1; i < stack.size(); i++)
      {
         String sep;
         if (stack.get(i).state.getUpperBound() > 0)
            sep = ".";
         else 
            sep = "->";
         
         if ((stack.get(i).state.getLowerBound() != stack.get(i).state.getUpperBound()) && (stack.get(i).state.getUpperBound() != 1))
            ret += stack.get(i).state.getName() + 
                   "[" + stack.get(i).state.getName().split("_")[0] + "_it]";
         else
            ret += stack.get(i).state.getName();
         
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
   public static void setEndCase(SimpleState state, Integer label) {
      childrenSet.put(state, label);
   }
   
   /**
    * @param name The name of the complex element
    * @return 
    */
   public static int getEndCase(SimpleState state){
	   if(childrenSet.containsKey(state))          
		   return childrenSet.get(state);
	   else
		   throw new NullPointerException("child "+state.getName()+" not found!");
		   //return -1;
   }
}
