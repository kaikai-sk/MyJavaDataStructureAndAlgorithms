package com.sk.radixtree;

import static org.junit.Assert.*;

import org.junit.Test;

public class RadixTreeUtilTest 
{

	@Test
	public void testDumpTreeRadixTreeNodeOfVString()
	{
	}

	@Test
	public void testDumpTreeRadixTreeOfV()
	{
	}

	@Test
	public void testLargestPrefixLength()
	{
		assertEquals(5, RadixTreeUtil.largestPrefixLength("abcdefg", "abcdexyz"));
		assertEquals(3, RadixTreeUtil.largestPrefixLength("abcdefg", "abcxyz"));
		assertEquals(3, RadixTreeUtil.largestPrefixLength("abcdefg", "abctuvxyz"));
		assertEquals(0, RadixTreeUtil.largestPrefixLength("abcdefg", ""));
		assertEquals(0, RadixTreeUtil.largestPrefixLength("", "abcxyz"));
		assertEquals(0, RadixTreeUtil.largestPrefixLength("xyz", "abcxyz"));
	}
}
