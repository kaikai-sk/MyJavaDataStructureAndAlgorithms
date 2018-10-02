package stack.use;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class reverseApp
{
	public static void main(String[] args)
	{
	    String input,output;
		while(true)
		{
			System.out.print("Enter a string:");
			System.out.flush();
			input=getString();
			if(input.equals(""))
				break;
			Reverser theReverser=new Reverser(input);
			output=theReverser.doRev();
			System.out.println("Reversed:"+output);
		}
	}

	public static String getString()
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		try
		{
			String s=br.readLine();
			return s;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}

class Reverser
{
	private String input;
	private String output;

	public Reverser(String in)
	{
		input=in;
	}

	public String doRev()
	{
		int stackSize=input.length();
		StackX theStack=new StackX(stackSize);
		for (int i = 0; i < input.length(); i++)
		{
			char ch=input.charAt(i);
			theStack.push(ch);
		}
		output="";
		while(!theStack.isEmpty())
		{
			char ch=theStack.pop();
			output=output+ch;
		}
		return output;
	}
}


class StackX
{
	private int maxSize;
	private char[] stackArray;
	private int top;

	public StackX(int max)
	{
		maxSize=max;
		stackArray=new char[maxSize];
		top=-1;
	}

	public void push(char c)
	{
		stackArray[++top]=c;
	}

	public char pop()
	{
		return stackArray[top--];
	}

	public char peek()
	{
		return stackArray[top];
	}

	public boolean isEmpty()
	{
		return -1==top;
	}

	public boolean isFull()
	{
		return maxSize-1==top;
	}
}
