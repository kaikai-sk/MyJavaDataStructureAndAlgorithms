package com.sk.trie;

import static org.junit.Assert.*;

import org.junit.Test;

public class TrieTest
{

	@Test
	public void testMain()
	{
	}

	@Test
	public void testGetRoot()
	{
	}

	@Test
	public void testPrintTree()
	{
		Trie trie=new Trie();
		
		trie.insert("ball");
		trie.insert("bat");
		trie.insert("doll");
		trie.insert("dork");
		trie.insert("dorm");
		trie.insert("send");
		trie.insert("sense");
		
		trie.printTree(trie.getRoot(),0);
	}

	@Test
	public void testDeleteWord()
	{
	}

	@Test
	public void testSearch()
	{
		Trie trie=new Trie();
		
		boolean res=trie.search("ball");
		assertEquals(false, res);
	}

	@Test
	public void testInsert()
	{
		Trie trie=new Trie();
		
		trie.insert("ball");
		boolean res=trie.search("ball");
		assertEquals(true, res);
	}

	@Test
	public void testTrie()
	{
	}

}
