package heapSort;

import sun.text.normalizer.NormalizerBase;
import sun.util.resources.cldr.en.CurrencyNames_en_TT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.nio.file.attribute.DosFileAttributes;

public class HeapSortApp
{
	public static void main(String[] args) throws IOException
	{
	    int size,j;
		System.out.print("Enter number of items : ");
		size=getInt();
		Heap theHeap=new Heap(size);

		for(j=0;j<size;j++)
		{
			int random=(int)(Math.random()*100);
			Node newNode=new Node(random);
			theHeap.insertAt(j,newNode);
			theHeap.incrementSize();
		}

		System.out.print("Random : ");
		theHeap.displayArray();

		/**
		 * 从倒数第二层开始，由下往上，保证是堆
		 */
		for(j=size/2-1;j>=0;j--)
		{
			theHeap.trickleDown(j);
		}

		System.out.print("Heap:");
		theHeap.displayArray();
		theHeap.displayHeap();

		for(j=size-1;j>=0;j--)
		{
			Node biggestNode = theHeap.remove();
			theHeap.insertAt(j,biggestNode);
		}

		System.out.print("Sorted : ");
		theHeap.displayArray();

	}

	public static String getString() throws IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		return br.readLine();
	}


	private static int getInt() throws IOException
	{
		return Integer.parseInt(getString());
	}
}

class Heap
{
	private Node[] heapArray;
	private int maxSize;
	private int currentSize;

	public void incrementSize()
	{
		++currentSize;
	}

	public void insertAt(int index,Node newNode)
	{
		heapArray[index]=newNode;
	}

	public void displayArray()
	{
		for(int j=0;j<maxSize;j++)
		{
			System.out.print(heapArray[j].getKey()+" ");
		}
		System.out.println();
	}

	public void displayHeap()
	{
		int nBlanks=32;
		int itemsPerRow=1;
		int column=0;
		int j=0;
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

			if(++column==itemsPerRow)
			{
				nBlanks/=2;
				itemsPerRow*=2;
				column=0;
				System.out.println();
			}
			else
			{
				for(int k=0;k<2*nBlanks-2;k++)
				{
					System.out.print(' ');
				}
			}
		}
		System.out.println("\n"+dots+ dots);
	}

	public Heap(int max)
	{
		maxSize=max;
		heapArray=new Node[maxSize];
		currentSize=0;
	}

	public Node remove()
	{
		Node root=heapArray[0];
		heapArray[0]=heapArray[--currentSize];
		trickleDown(0);
		return root;
	}

	public void trickleDown(int index)
	{
		int largeChild;
		Node top=heapArray[index];

		while(index<currentSize/2)
		{
			int leftChild=2*index+1;
			int rightChild=leftChild+1;


			if(rightChild<currentSize &&
					heapArray[leftChild].getKey()<heapArray[rightChild].getKey())
			{
				largeChild=rightChild;
			}
			else
			{
				largeChild=leftChild;
			}

			if(top.getKey()>=heapArray[largeChild].getKey())
				break;

			heapArray[index]=heapArray[largeChild];
			index=largeChild;
		}

		heapArray[index]=top;

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

}
