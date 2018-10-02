package firstLastList;

public class FirstLastListApp
{
	public static void main(String[] args)
	{
		FirstLastList theList=new FirstLastList();
		
		theList.insertFirst(22);
		theList.insertFirst(44);
		theList.insertFirst(66);
		
		theList.insertLast(11);
		theList.insertLast(33);
		theList.insertLast(55);
		
		theList.displayList();
		
		theList.deleteFisrt();
		theList.deleteFisrt();
		
		theList.displayList();
	}
}

class Link
{
	public long dData;
	public Link next;
	
	public Link(long d)
	{
		dData=d;
	}
	
	public void displayLink()
	{
		System.out.print(dData+"");
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
	
	public void displayList()
	{
		System.out.print("List (first-->Last):");
		Link current=first;
		while(current!=null)
		{
			current.displayLink();
			System.out.print(" ");
			current=current.next;
		}
		System.out.println();
	}
	
	public long deleteFisrt()
	{
		long temp=first.dData;
		if(first.next==null)
			last=null;
		first=first.next;
		return temp;
	}
	
	public void insertFirst(long dd)
	{
		Link newLink=new Link(dd);
		if(isEmpty())
			last=newLink;
		newLink.next=first;
		first=newLink ;
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
}
