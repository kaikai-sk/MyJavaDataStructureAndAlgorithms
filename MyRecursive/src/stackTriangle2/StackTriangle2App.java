package stackTriangle2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StackTriangle2App
{
	static int theNumber;
	static int theAnser;
	static StackX theStack;

	public static void main(String[] args)
	{
		System.out.print("Enter a number:");
		theNumber=getInt();
		stackTriangle();
		System.out.println("Triangle="+theAnser);
	}

	private static void stackTriangle()
	{
		theStack=new StackX(10000);

		theAnser=0;

		while(theNumber>0)
		{
			theStack.push(theNumber);
			theNumber--;
		}
		while(!theStack.isEmpty())
		{
			int newN=theStack.pop();
			theAnser+=newN;
		}
	}

	private static int getInt()
	{
		String s=getString();
		return Integer.parseInt(s);
	}

	public static String getString()
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



class StackX
{
	private int maxSize;
	private int[] stackArray;
	private int top;

	public StackX(int s)
	{
		maxSize=s;
		stackArray=new int[maxSize];
		top=-1;
	}

	public void push(int j)
	{
		stackArray[++top]=j;
	}

	public int pop()
	{
		return stackArray[top--];
	}

	public int peek()
	{
		return stackArray[top];
	}

	public boolean isEmpty()
	{
		return -1==top;
	}


}

