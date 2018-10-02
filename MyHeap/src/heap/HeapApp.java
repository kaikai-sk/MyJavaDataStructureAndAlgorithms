package heap;

import sun.security.provider.certpath.SunCertPathBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HeapApp
{
	public static void main(String[] args)
	{
	    int value,value2;
		Heap theHeap=new Heap(31);
		boolean success;

		theHeap.insert(70);
		theHeap.insert(40);
		theHeap.insert(50);
		theHeap.insert(20);
		theHeap.insert(60);
		theHeap.insert(100);
		theHeap.insert(80);
		theHeap.insert(30);
		theHeap.insert(10);
		theHeap.insert(90);

		while(true)
		{
			System.out.print("Enter first letter of ");
			System.out.print("show,insert,remove,change:");
			int choice = getChar();

			switch (choice)
			{
				case 's':
					theHeap.displayHeap();
					break;
				case 'i':
					System.out.print("Enter value to insert: ");
					value=getInt();
					success=theHeap.insert(value);
					if(!success)
					{
						System.out.println("Can't insert;heap full");
					}
					break;
				case 'r':
					if(!theHeap.isEmpty())
					{
						theHeap.remove();
					}
					else
					{
						System.out.println("Can't remove;heap empty");
					}
					break;
				case 'c':
					System.out.print("Enter current index of item:");
					value=getInt();
					System.out.print("Enter new key: ");
					value2=getInt();
					success=theHeap.change(value,value2);
					if(!success)
					{
						System.out.println("Invalid index");
					}
					break;
				default:
					System.out.println("Invalid entry\n");
					break;
			}
		}
	}

	private static int getChar()
	{
		return getString().charAt(0);
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

	private static int getInt()
	{
		return Integer.parseInt(getString());
	}

}

class Heap
{
	private Node[] heapArray;
	private int maxSize;
	private int currentSize;

	public void displayHeap()
	{
		System.out.print("Heap Array:");
		for(int m=0;m<currentSize;m++)
		{
			if(heapArray[m]!=null)
			{
				System.out.print(heapArray[m].getKey()+" ");
			}
			else
			{
				System.out.print("-- ");
			}
		}
		System.out.println();

		/**
		 * tree format
		 */
		int nBlanks=32;
		int itemsPerRow=1;
		int column=0;
		int j=0;		//current item;
		String dots="......................................";
		System.out.println(dots+dots);

		while(currentSize>0)
		{
			if(column==0)
			{
				for(int k=0;k<nBlanks;k++)
				{
					System.out.print(' ');
				}
			}
			System.out.print(heapArray[j].getKey());
			if(++j==currentSize)
				break;

			//end of row?
			if(++column==itemsPerRow)
			{
				nBlanks/=2;
				itemsPerRow*=2;
				column=0;
				System.out.println();
			}
			else
			{
				for(int k=0;k<nBlanks*2-2;k++)
				{
					System.out.print(' ');
				}
			}
		}
		System.out.println("\n"+dots+dots);
	}

	public boolean change(int index,int newValue)
	{
		if(index<0 || index>currentSize)
		{
			return false;
		}
		int oldValue=heapArray[index].getKey();
		heapArray[index].setKey(newValue);
		if(newValue>oldValue)
		{
			trickleUp(index);
		}
		else
		{
			trickleDown(index);
		}

		return true;
	}

	/**
	 * 删除顶
	 * @return
	 */
	public Node remove()
	{
		Node root =heapArray[0];
		heapArray[0]=heapArray[--currentSize];
		trickleDown(0);
		return root;
	}

	public void trickleDown(int index)
	{
		int largeChild;
		Node top=heapArray[index];
		/**
		 * while node has at least one child
		 */
		while(index<currentSize/2)
		{
			int leftChild=2*index+1;
			int rightChild=leftChild+1;

			if(rightChild<currentSize && heapArray[leftChild].getKey()<heapArray[rightChild].getKey() )
			{
				largeChild=rightChild;
			}
			else
			{
				largeChild=leftChild;
			}

			if(top.getKey()>=heapArray[largeChild].getKey())
			{
				break;
			}
			/**
			 * 小顶往下移
			 */
			heapArray[index]=heapArray[largeChild];
			index=largeChild;
		}
		heapArray[index]=top;
	}


	public boolean insert(int key)
	{
		if(currentSize==maxSize)
		{
			return false;
		}

		Node newNode=new Node(key);
		heapArray[currentSize]=newNode;
		trickleUp(currentSize++);
		return true;
	}

	public void trickleUp(int index)
	{
		int parent=(index-1)/2;
		/**
		 * 保存要插入的节点
		 */
		Node bottom=heapArray[index];

		while(index>0 && heapArray[parent].getKey()<bottom.getKey())
		{
			heapArray[index]=heapArray[parent];
			index=parent;
			parent=(parent-1)/2;
		}
		heapArray[index]=bottom;
	}


	public boolean isEmpty()
	{
		return 0==currentSize;
	}

	public Heap(int max)
	{
		maxSize=max;
		heapArray=new Node[maxSize];
		currentSize=0;
	}
}

class Node
{
	private int iData;

	public Node(int key)
	{
		iData=key;
	}

	public int getKey()
	{
		return iData;
	}

	public void setKey(int key)
	{
		this.iData=key;
	}
}
