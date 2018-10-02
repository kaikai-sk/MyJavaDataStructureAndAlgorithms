package stack.use;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BrackketsApp
{
	public static void main(String[] args)
	{
	    String input;
		while(true)
		{
			System.out.println("Entering string contains delimiters:");
			System.out.flush();
			input=getString();
			if(input.equals(""))
				break;
			BraceketChecker checker=new BraceketChecker(input);
			checker.check();
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

class BraceketChecker
{
	private String input;
	public BraceketChecker(String in)
	{
		input=in;
	}

	public void check()
	{
		int stackSize=input.length();
		StackXBrace theStack=new StackXBrace(stackSize);
		for (int i = 0; i < stackSize; i++)
		{
			char ch=input.charAt(i);

			switch (ch)
			{
				case '{':
				case '(':
				case '[':
					theStack.push(ch);
					break;
				case '}':
				case ')':
				case ']':
					if(!theStack.isEmpty())
					{
						char chx=theStack.pop();
						if(ch=='}'&&chx!='{' ||
								ch==')'&&chx!='(' ||
								ch==']'&&chx!='['
								)
						{
							System.out.println("Error: "+ch+" at "+i);
						}
					}
					else
						System.out.println("Error: "+ch+" at "+i);
					break;
				default:
					break;
			}
		}//end for for
		if(!theStack.isEmpty())
		{
			System.out.println("Error: missing right delimiter");
		}
	}

}

class StackXBrace
{
	private int maxSize;
	private char[] stackArray;
	private int top;

	public StackXBrace(int max)
	{
		maxSize=max;
		stackArray=new char[maxSize];
		top=-1;
	}

	public void push(char ch)
	{
		stackArray[++top]=ch;
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
