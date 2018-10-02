package anagram;

import javafx.scene.transform.Rotate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.temporal.Temporal;

public class AnagramApp
{
	static int size;
	static int count;
	static char[] arrChar=new char[100];

	public static void main(String[] args)
	{
	    System.out.print("Enter a word:");
	    String input=getString();
		size=input.length();
		count=0;
		for(int j=0;j<size;j++)
		{
			arrChar[j]=input.charAt(j);
		}
		doAnagram(size);
	}

	public static void doAnagram(int newSize)
	{
		if(newSize==1)
			return;
		for(int j=0;j<newSize;j++)
		{
			doAnagram(newSize-1);
			if(newSize==2)
			{
				displayWord();
			}
			rotate(newSize);
		}
	}

	/**
	 * rotate left all chars from position to end
	 */
	private static void rotate(int newSize)
	{
		int j;
		int position=size-newSize;
		char temp=arrChar[position];
		for(j=position+1;j<size;j++)
		{
			arrChar[j-1]=arrChar[j];
		}
		arrChar[j-1]=temp;
	}

	private static void displayWord()
	{
		/**
		 * 控制格式，对齐
		 */
		if(count<99)
		{
			System.out.print(" ");
		}
		if(count<9)
		{
			System.out.print(" ");
		}
		System.out.print(++count+" ");
		for(int j=0;j<size;j++)
		{
			System.out.print(arrChar[j]);
		}
		System.out.print("    ");
		System.out.flush();
		if(count%6==0)
		{
			System.out.println();
		}
	}

	private static String getString()
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s= null;
		try
		{
			s = br.readLine();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return s;
	}
}


