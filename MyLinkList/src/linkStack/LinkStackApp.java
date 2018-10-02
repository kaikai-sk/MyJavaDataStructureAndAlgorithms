package linkStack;

public class LinkStackApp
{
	public static void main(String[] args)
	{
		LinkStack theStack=new LinkStack();
		
		theStack.push(20);
		theStack.push(40);
		
		theStack.displayStack();
		
		theStack.push(60);
		theStack.push(80);
		
		theStack.displayStack();
		
		theStack.pop();
		theStack.pop();
		
		theStack.displayStack();
	}	
}

class LinkStack
{
	private LinkList theList;
	
	public LinkStack()
	{
		theList=new LinkList();
	}
	
	public void push(long j)
	{
		theList.insertFirst(j);
	}
	
	public long pop()
	{
		return theList.deleteFirst();
	}
	
	public boolean isEmpty()
	{
		return theList.isEmpty();
	}
	
	public void displayStack()
	{
		System.out.println("Stack(top---->bottom):");
		theList.displayList();
	}
}

class LinkList
{
	private Link first;
	
	public LinkList()
	{
		first=null;
	}
	
	public boolean isEmpty()
	{
		return first==null;
	}
	
	public void insertFirst(long dd)
	{
		Link newLink=new Link(dd);
		newLink.next=first;
		first=newLink;
	}
	
	public long deleteFirst()
	{
		long temp=first.dData;
		first=first.next;
		return temp;
	}
	
	public  void displayList()
	{
		Link current=first;
		while(current!=null)
		{
			current.displayLink();
			current=current.next;
		}
		System.out.println();
	}
	
}

class Link
{
	long dData;
	Link next;
	
	public Link(long dd)
	{
		dData=dd;
	}
	
	public void displayLink()
	{
		System.out.print(dData+" ");
	}
}


