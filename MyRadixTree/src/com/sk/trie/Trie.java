package com.sk.trie;

public class Trie
{
	private Node root;
	
	public static void main(String[] args)
	{
		Trie trie=new Trie();

		trie.insert("ball");
		trie.insert("balls");
		trie.insert("sense");
		
		trie.printTree(trie.getRoot(),0);
		
		//testing deletion
		System.out.println(trie.search("balls"));
		System.out.println(trie.search("ba"));
		trie.deleteWord("balls");
		System.out.println(trie.search("balls"));
		System.out.println(trie.search("ball"));
	}
	
	public Node getRoot()
	{
		return this.root;
	}
	
	public void printTree(Node root,int index)
	{
		if(root.childList.size()>0)
		{
			for (int i=0;i<root.childList.size();i++)
			{
				Node child=root.childList.get(i);
				index++;
				printTree(child, index);
				
				for(int spaceNum=0;spaceNum<index;spaceNum++)
				{
					System.out.print(' ');
				}
				System.out.println(root.content);
				break;
			}
		}
		
	
	}
	
	public void deleteWord(String word)
	{
		if(search(word)==false)
		{
			return ;
		}
		Node current=root;
		for (char c:word.toCharArray())
		{
			Node child=current.subNode(c);
			if(child.count==1)
			{
				current.childList.remove(child);
				return;
			}
			else
			{
				child.count--;
				current=child;
			}
		}
		
		current.isEnd=false;
	}
	
	public boolean search(String word)
	{
		Node current=root;
		
		for(int i=0;i<word.length();i++)
		{
			if(current.subNode(word.charAt(i))==null)
			{
				return false;
			}
			else
			{
				current=current.subNode(word.charAt(i));
			}
		}
		
		if(current.isEnd)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public void insert(String word)
	{
		if(search(word)==true)
		{
			return;
		}
		
		Node current=root;
		
		for(int i=0;i<word.length();i++)
		{
			Node child=current.subNode(word.charAt(i));
			if(child!=null)
			{
				current=child;
			}
			else
			{
				current.childList.add(new Node(word.charAt(i)));
				current=current.subNode(word.charAt(i));
			}
			current.count++;
		}
		
		current.isEnd=true;
		
	}
	
	public Trie()
	{
		root=new Node(' ');
	}
}
