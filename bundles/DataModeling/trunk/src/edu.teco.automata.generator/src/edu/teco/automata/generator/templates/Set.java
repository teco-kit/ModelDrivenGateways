package edu.teco.automata.generator.templates;

import java.util.HashSet;
public class Set
{
	static java.util.Set<String> _set;
	
	public static void reset()
	{
		_set=new HashSet<String>();
	}
	
	public static boolean has(String s)
	{
		return _set.contains(s);
	}
	
	public static void add(String s)
	{
		_set.add(s);
	}
}