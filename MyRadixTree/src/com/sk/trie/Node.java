package com.sk.trie;

import java.util.LinkedList;

public class Node
{
	//the charicter in the node
	char content;
	//whether the end of the word
	boolean isEnd;
	//the number of words sharing this char
	int count;
	//the child list;
	LinkedList<Node> childList;
	
	public Node subNode(char c)
	{
		if(childList!=null)
		{
			for(Node eachChild:childList)
			{
				if(eachChild.content==c)
				{
					return eachChild;
				}
			}
		}
		return null;
	}
	
	public Node(char c)
	{
		childList=new LinkedList<Node>();
		isEnd=false;
		this.content=c;
		count=0;
	}
}
