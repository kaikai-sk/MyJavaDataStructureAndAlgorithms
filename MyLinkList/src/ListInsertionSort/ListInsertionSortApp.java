package ListInsertionSort;

public class ListInsertionSortApp
{
	public static void main(String[] args)
	{
		int size=10;
		Link[] linkArray=new Link[size];
		for(int j=0;j<size;j++)
		{
			int n=(int)(Math.random()*99);
			Link newLink=new Link(n);
			linkArray[j]=newLink;
		}
		
		System.out.println("un sorted list:");
		for(int j=0;j<size;j++)
		{
			System.out.print(linkArray[j].data+" ");
		}
		System.out.println();
		
		SortedList sortedList=new SortedList(linkArray);
		for(int j=0;j<size;j++)
			linkArray[j]=sortedList.remove();
		System.out.println("sorted array:");
		for(int j=0;j<size;j++)
		{
			System.out.print(linkArray[j].data+" ");
		}
		System.out.println();
	}
}

class SortedList
{
	private Link first;
	
	public SortedList()
	{
		first=null;
	}
	
	public SortedList(Link[] linkArr)
	{
		this();
		for(int j=0;j<linkArr.length;j++)
		{
			insert(linkArr[j]);
			
		}
		
	}
	
	public boolean isEmpty()
	{
		return first==null;
	}
	
	public void insert(Link newLink)
	{
		Link previous=null;
		Link current=first;
		
		while(current != null && newLink.data>current.data)
		{
			previous=current;
			current=current.next;
		}
		
		if(previous==null)
		{
			first=newLink;
		}
		else
		{
			previous.next=newLink;
		}
		newLink.next=current;
	}
	
	public void insert(long key)
	{
		Link newLink=new Link(key);
		Link previous=null;
		Link current=first;
		
		while(current != null && key>current.data)
		{
			previous=current;
			current=current.next;
		}
		
		if(previous==null)
		{
			first=newLink;
		}
		else
		{
			previous.next=newLink;
		}
		newLink.next=current;
	}
	
	public Link remove()
	{
		Link temp=first;
		first=first.next;
		return temp;
	}
	
	public void displayList()
	{
		System.out.print("List (first-->last): ");
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
	public long data;
	public Link next;
	
	public Link(long dd)
	{
		data=dd;
	}
	
	public void displayLink()
	{
		System.out.println(data+"    ");
	}
}