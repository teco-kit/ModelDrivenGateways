package edu.teco.automata.generator.templates;

import java.util.HashSet;
public class Set
{
	static java.util.Set<Object> _set;
	
	public static void reset()
	{
		_set=new HashSet<Object>();
	}
	
	public static boolean has(Object s)
	{
		return _set.contains(s);
	}
	
	public static void add(Object s)
	{
		_set.add(s);
	}
}