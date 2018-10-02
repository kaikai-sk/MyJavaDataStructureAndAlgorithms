package com.sk.radixtree;

import java.io.Serializable;

/**
 * Radix tree utility functions
 * @author root
 *
 */
public class RadixTreeUtil
{
	private RadixTreeUtil()
	{
		
	}
	
	static <V extends Serializable> void dumpTree(RadixTreeNode<V> node,String outputPrefix)
	{
		if(node.hasValue())
		{
			System.out.format("%s{%s : %s}%n", outputPrefix,node.getPrefix(),node.getValue());
		}
		else
		{
			System.out.format("%s{%s}%n", outputPrefix,node.getPrefix(),node.getValue());
		}
		for (RadixTreeNode<V> child:node)
		{
			dumpTree(child, outputPrefix+"\t");
		}
	}
	
	/**
	 * Prints a radix tree to <code>System.out</code>.
	 * 
	 * @param tree  the tree
	 */
	public static <V extends Serializable> void dumpTree(RadixTree <V> tree)
	{
		dumpTree(tree.root,"");
	}
	
	/**
	 * Finds the length of the largest prefix for two character sequences.
	 * 
	 * @param a  character sequence
	 * @param b  character sequence
	 * 
	 * @return the length of largest prefix of <code>a</code> and <code>b</code>
	 *         
	 * @throws IllegalArgumentException  if either <code>a</code> or <code>b</code>
	 *                                   is <code>null</code>
	 */
	public static int largestPrefixLength(CharSequence a,CharSequence b)
	{
		int length=0;
		
		for(int i=0;i<Math.min(a.length(), b.length());i++)
		{
			if(a.charAt(i)!=b.charAt(i))
			{
				break;
			}
			length++;
		}
		
		return length;
	}
}
