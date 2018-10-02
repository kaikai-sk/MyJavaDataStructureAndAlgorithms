package stack.infix;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InfixApp
{
	public static void main(String[] args)
	{
	    String input,output;
	    while(true)
		{
			System.out.print("Enter infix:");
			System.out.flush();
			input=getString();
			if(input.equals(""))
				break;
			InToPost theTrans=new InToPost(input);
			output=theTrans.doTrans();
			System.out.println("The postfix is "+output+"\n");

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

/**
 * Infix to postfix conversion
 */
class InToPost
{
	private StackX theStack;
	private String input;
	private String output="";

	public InToPost(String in)
	{
		input=in;
		theStack=new StackX(input.length());
	}

	/**
	 * do translation to postfix
	 * @return
	 */
	public String doTrans()
	{
		for(int j=0;j<input.length();j++)
		{
			char ch=input.charAt(j);
			theStack.displayStack("For "+ch+" ");

			switch (ch)
			{
				case '+':
				case '-':
					gotOper(ch,1);
					break;
				case '*':
				case '/':
					gotOper(ch,2);
					break;
				case '(':
					theStack.push(ch);
					break;
				case ')':
					gotParen(ch);
					break;
				default:
					output=output+ch;
			}
		}//end for
		//pop remainning opers
		while(!theStack.isEmpty())
		{
			theStack.displayStack("While ");
			output=output+theStack.pop();
		}
		theStack.displayStack("End  ");
		return output;
	}

	private void gotParen(char ch)
	{
		while(!theStack.isEmpty())
		{
			char chx=theStack.pop();
			if(chx=='(')
			{
				break;
			}
			else
			{
				output=output+chx;
			}
		}
	}

	/**
	 * got operator from input
	 * @param opThis
	 * @param prec1
	 */
	private void gotOper(char opThis, int prec1)
	{
		while(!theStack.isEmpty())
		{
			char opTop=theStack.pop();

			/**
			 * 如果是'(',重新推入栈中
			 */
			if(opTop=='(')
			{
				theStack.push(opTop);
				break;
			}
			else
			{
				//precedence of new op
				int prec2;

				if(opTop=='+' || opTop=='-')
				{
					prec2=1;
				}
				else
					prec2=2;
				if(prec2<prec1)
				{
					theStack.push(opTop);
					break;
				}
				else
					output=output+opTop;
			}//end else
		}//end while
		theStack.push(opThis);
	}
}


class StackX
{
	private int maxSize;
	private char[] stackArray;
	private int top;

	public StackX(int s)
	{
		maxSize=s;
		stackArray=new char[maxSize];
		top=-1;
	}

	public void push(char j)
	{
		stackArray[++top]=j;
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
		return top==-1;
	}

	public boolean isFull()
	{
		return top==maxSize-1;
	}

	public char peekN(int n)
	{
		return stackArray[n];
	}

	public int size()
	{
		return top+1;
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