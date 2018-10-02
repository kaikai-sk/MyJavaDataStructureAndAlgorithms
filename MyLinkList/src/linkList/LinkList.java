package linkList;

public class LinkList
{
	private Link first;
	
	public LinkList()
	{
		first=null;//目前链表中还没有数据
	}
	
	public Link delete(int key)
	{
		Link current=first;
		Link previous=first;
		while(current.iData!=key)
		{
			if(current.next==null)
			{
				return null;
			}
			else
			{
				previous=current;
				current=current.next;
			}
		}
		if(current==first)
		{
			first=first.next;
		}
		else
		{
			previous.next=current.next;
		}
		return current;
	}
	
	public Link find(int key)
	{
		Link current=first;
		while(current.iData!=key)
		{
			if(current.next==null)
			{
				return null;
			}
			else
			{
				current=current.next;
			}
		}
		return current;
	}
	
	public boolean isEmpty()
	{
		return first==null;
	}
	
	public void insertFirst(int id,double dd)
	{
		Link newLink=new Link(id, dd);
		newLink.next=first;
		first=newLink;
	}
	
	public Link deleteFirst()
	{
		Link temp=first;
		first=first.next;
		return temp;
	}
	
	public void displayList()
	{
		System.out.println("List (First---->Last)");
		Link current=first;
		while(current!=null)
		{
			current.displayLink();
			current=current.next;
		}
		System.out.println("");
	}
	
	
}
