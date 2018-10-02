package linkQueue;

public class LinkQueueApp
{
	public static void main(String[] args)
	{		
		LinkQueue theQueue = new LinkQueue();
		theQueue.insert(20); // insert items
		theQueue.insert(40);

		theQueue.displayQueue(); // display queue

		theQueue.insert(60); // insert items
		theQueue.insert(80);

		theQueue.displayQueue(); // display queue

		theQueue.remove(); // remove items
		theQueue.remove();

		theQueue.displayQueue(); // display queue
	}	
}

class LinkQueue
{
	private FirstLastList theList;
	
	public LinkQueue()
	{
		theList=new FirstLastList();
	}
	
	public void insert(long j)
	{
		theList.insertLast(j);
	}
	
	public long remove()
	{
		return theList.deleteFirst();
	}
	
	public boolean isEmpty()
	{
		return theList.isEmpty();
	}
	
	public void displayQueue()
	{
		System.out.println("Stack(front---->rear):");
		theList.displayList();
	}
}

class FirstLastList
{
	private Link first;
	private Link last;
	
	public FirstLastList()
	{
		first=null;
		last=null;
	}
	
	public boolean isEmpty()
	{
		return first==null;
	}
	
	public void insertFirst(long dd)
	{
		Link newLink=new Link(dd);
		if(first==null)
			last=newLink;
		newLink.next=first;
		first=newLink;
	}
	
	public void insertLast(long dd)
	{
		Link newLink=new Link(dd);
		if(isEmpty())
			first=newLink;
		else
			last.next=newLink;
		last=newLink;
	}
	
	public long deleteFirst()
	{
		long temp=first.dData;
		if(first.next==null)
			last=null;
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


