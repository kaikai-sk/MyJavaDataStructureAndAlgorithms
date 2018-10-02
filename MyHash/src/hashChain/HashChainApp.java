package hashChain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HashChainApp
{
	public static void main(String[] args)
	{
		Link aDataItem;
		int aKey, size, n, keysPerCell;
		System.out.print("Enter size of HashTable:");
		size = getInt();
		//初始的项的个数
		System.out.print("Enter initial number of items:");
		n = getInt();
		keysPerCell = 100;

		HashTable theHashTable = new HashTable(size);
		for (int j = 0; j < n; j++)
		{
			aKey = (int) (Math.random() * keysPerCell * size);
			aDataItem = new Link(aKey);
			theHashTable.insert(aDataItem);
		}

		while (true)
		{
			System.out.print("Enter first letter of ");
			System.out.print("show insert delete or find:");
			char choice = getChar();
			switch (choice)
			{
				case 's':
					theHashTable.displayTable();
					break;
				case 'i':
					System.out.print("Enter key value to insert:");
					aKey = getInt();
					aDataItem = new Link(aKey);
					theHashTable.insert(aDataItem);
					break;
				case 'd':
					System.out.print("Enter key value to delete:");
					aKey = getInt();
					theHashTable.delete(aKey);
					break;
				case 'f':
					System.out.print("Enter key value to find:");
					aKey = getInt();
					aDataItem = theHashTable.find(aKey);
					if (aDataItem != null)
					{
						System.out.println("Find " + aKey);
					}
					else
					{
						System.out.println("Could not find " + aKey);
					}
					break;
				default:
					System.out.println("Invalid Entry!!!");
					break;
			}
		}

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

	public static char getChar()
	{
		return getString().charAt(0);
	}

	public static int getInt()
	{
		return Integer.parseInt(getString());
	}
}

class HashTable
{
	private SortedList[] hashArray;
	private int arraySize;

	public Link find(int key)
	{
		int hashVal=hashFunc(key);
		Link theLink=hashArray[hashVal].find(key);
		return theLink;
	}

	public void delete(int key)
	{
		int hashVal=hashFunc(key);
		hashArray[hashVal].delete(key);
	}

	public void insert(Link theLink)
	{
		int key=theLink.getKey();
		int hashVal=hashFunc(key);
		hashArray[hashVal].insert(theLink);
	}

	public int hashFunc(int key)
	{
		return key%arraySize;
	}

	public void displayTable()
	{
		for(int j=0;j<arraySize;j++)
		{
			System.out.println(j+". ");
			hashArray[j].displayList();
		}
	}


	public HashTable(int size)
	{
		arraySize=size;
		hashArray=new SortedList[arraySize];

		for(int j=0;j<arraySize;j++)
		{
			hashArray[j]=new SortedList();
		}
	}
	
	
}


class SortedList
{
	private Link first;

	public void displayList()
	{
		System.out.print("List (first-->last):");
		Link current=first;
		while(current!=null)
		{
			current.displayLink();
			current=current.next;
		}
		System.out.println();
	}

	public Link find(int key)
	{
		Link current=first;

		while(current!=null && current.getKey()<=key)
		{
			if(current.getKey()==key)
			{
				return current;
			}
			current=current.next;
		}
		return null;
	}

	public void delete(int key)
	{
		Link previous=null;
		Link current=first;

		while (current!=null && key!=current.getKey())
		{
			previous=current;
			current=current.next;
		}

		if(previous==null)
		{
			first=first.next;
		}
		else
		{
			previous.next=current.next;
		}

	}

	public void insert(Link theLink)
	{
		int key=theLink.getKey();
		Link previous=null;
		Link current=first;

		while(current!=null && key>current.getKey())
		{
			previous=current;
			current=current.next;

		}

		if(previous==null)
		{
			first=theLink;
		}
		else
		{
			previous.next=theLink;
		}
		theLink.next=current;
	}


	public SortedList()
	{
		first=null;
	}


}

class Link
{
	public int iData;
	public Link next;

	public void displayLink()
	{
		System.out.print(iData+" ");
	}

	public int getKey()
	{
		return iData;
	}

	public Link(int ii)
	{
		iData=ii;
	}


}
