package stack.infix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PostfixApp
{
	public static void main(String[] args)
	{
	    String input;
		int output;
		while(true)
		{
			System.out.print("Enter postfix:");
			System.out.flush();
			input=getString();
			if(input.equals(""))
				break;

			ParsePost aParser=new ParsePost(input);
			output=aParser.doParse();
			System.out.println("Evaluates to "+output);


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

class ParsePost
{
	private StackS theStack;
	private String input;

	public ParsePost(String s)
	{
		this.input=s;

	}

	public int doParse()
	{
		theStack=new StackS(20);
		char ch;
		int j;
		int num1,num2,interAns;

		for(j=0;j<input.length();j++)
		{
			ch=input.charAt(j);
			theStack.displayStack(ch+" ");
			if(ch>='0' && ch<='9')
				theStack.push((int)(ch-'0'));
			else
			{
				num2=theStack.pop();
				num1=theStack.pop();
				switch (ch)
				{
					case '+':
						interAns=num1+num2;
						break;
					case '-':
						interAns=num1-num2;
						break;
					case '*':
						interAns=num1*num2;
						break;
					case '/':
						interAns=num1/num2;
						break;
					default:
						interAns=0;
						break;
				}//end swtich
				theStack.push(interAns);
			}


		}//end for
		interAns=theStack.pop();
		return interAns;
	}//end doParse
}

class StackS
{
	private int maxSize;
	private int[] stackArray;
	private int top;

	public StackS(int s)
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

	public int peekN(int n)
	{
		return stackArray[n];
	}

	public int size()
	{
		return top+1;
	}

	public boolean isEmpty()
	{
		return -1==top;
	}

	public void displayStack(String s)
	{
		System.out.print(s);
		System.out.print("Stack (bottom to top):");
		for(int j=0;j<size();j++)
		{
			System.out.print(peekN(j)+" ");

		}
		System.out.println();
	}
}
