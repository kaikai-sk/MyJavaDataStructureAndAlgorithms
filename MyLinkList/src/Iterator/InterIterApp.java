package Iterator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InterIterApp
{
	public static void main(String[] args) throws IOException
	{
		LinkList theList=new LinkList();
		ListIterator iterator1=theList.getIterator();
		long value;
		iterator1.insertAfter(20);
		iterator1.insertAfter(40);
		iterator1.insertAfter(80);
		iterator1.insertBefore(60);
		
		while(true)
		{
			System.out.print("Enter first letter of show ,reset ");
			System.out.println("next,get,before,after,delete: ");
			System.out.flush();
			int choice =getChar();
			switch (choice)
			{
			//show list
			case 's':
				if(!theList.isEmpty())
				{
					theList.displayList();
				}
				else
				{
					System.out.println("the list is empty");
				}
				break;
			//insert to first
			case 'r':
				iterator1.reset();
				break;
			//advance to next item 前进到下一项
			case 'n':
				if(!theList.isEmpty() && !iterator1.atEnd())
				{
					iterator1.nextLink();
				}
				else
				{
					System.out.println("Can not goto next link");
				}
				break;
			//get current item
			case 'g':
				if(!theList.isEmpty())
				{
					value=iterator1.getCurrent().dData;
					System.out.println("returned "+value);
				}
				else
				{
					System.out.println("List is empty");
				}
				break;
			//insert before current
			case 'b':
				System.out.println("enter value to insert:");
				System.out.flush();
				value = getInt();
				iterator1.insertBefore(value);
				break;
			//insert after current:
			case 'a':
				System.out.println("enter value to insert:");
				System.out.flush();
				value = getInt();
				iterator1.insertAfter(value);
				break;
			//delete current item
			case 'd':
				if(!theList.isEmpty())
				{
					value=iterator1.deleteCurrent();
					System.out.println("deleted "+value);
				}
				else
				{
					System.out.println("Empty list,can not delete");
				}
				break;
			default:
				System.out.println("Invalid entry");
				break;
			}
			
		}
	}

	public static int getInt() throws IOException
	{
		String string=getString();
		int value=Integer.parseInt(string);
		return value;
	}

	public static char getChar() throws IOException
	{
		String s=getString();
		return s.charAt(0);
	}

	public static String getString() throws IOException
	{
		InputStreamReader isr=new InputStreamReader(System.in);
		BufferedReader br=new BufferedReader(isr);
		String string=br.readLine();
		return string;
	}
}

class ListIterator
{
	private Link current;
	private Link previous;
	private LinkList ourList;

	public long deleteCurrent()
	{
		long value=current.dData;
		
		if(previous==null)
		{
			ourList.setFirst(current.next);
			reset();
		}
		else
		{
			previous.next=current.next;
			if(atEnd())
				reset();
			else
			{
				current=current.next;
			}
		}
		
		return value;
	}
	
	public void insertBefore(long dd)
	{
		Link newLink=new Link(dd);
		if(previous==null)
		{
			newLink.next=ourList.getFirst();
			ourList.setFirst(newLink);
		}
		else
		{
			newLink.next=previous.next;
			previous.next=newLink;
			current=newLink;
		}
	}
	
	/**
	 * 在当前节点之后插入节点
	 * @param dd
	 */
	public void insertAfter(long dd)
	{
		Link newLink=new Link(dd);
		if(ourList.isEmpty())
		{
			ourList.setFirst(newLink);
			current=newLink;
		}
		else
		{
			newLink.next=current.next;
			current.next=newLink;
			nextLink();
		}
	}
	
	public Link getCurrent()
	{
		return this.current;
	}
	
	public void nextLink()
	{
		previous=current;
		current=current.next;
	}
	
	/**
	 * @return true if last link
	 */
	public boolean atEnd()
	{
		return current.next==null;
	}
	
	/**
	 * start at 'first'
	 */
	public void reset()
	{
		current=ourList.getFirst();
		previous=null;
	}
	
	public ListIterator(LinkList list)
	{
		this.ourList=list;
	}
}

class LinkList
{
	private Link first;
	
	
	
	public void displayList()
	{
		Link current=first;
		while(current!=null)
		{
			current.displayLink();
			current=current.next;
		}
		System.out.println();
	}
	
	public ListIterator getIterator()
	{
		return new ListIterator(this);
	}
	
	public boolean isEmpty()
	{
		return first==null;
	}
	
	public void setFirst(Link f)
	{
		this.first=f;
	}
	
	public Link getFirst()
	{
		return this.first;
	}
	
	public LinkList()
	{
		first=null;
	}
}

class Link
{
	public long dData;
	public Link next;
	
	public void displayLink()
	{
		System.out.print(dData+" ");
	}
	
	public Link(long dd)
	{
		this.dData=dd;
	}
}