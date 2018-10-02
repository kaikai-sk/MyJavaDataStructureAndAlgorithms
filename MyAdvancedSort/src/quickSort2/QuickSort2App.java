package quickSort2;

import java.io.FileReader;

public class QuickSort2App
{
	public static void main(String[] args)
	{
		int maxSize=16;
		ArrayIns arr=new ArrayIns(maxSize);

		for(int j=0;j<maxSize;j++)
		{
			long n=(int)(Math.random()*99);
			arr.insert(n);
		}
		arr.display();
		arr.quickSort();
		arr.display();
	}
}

class ArrayIns
{
	private long[] theArray;
	private int nElems;

	public void quickSort()
	{
		recQuickSort(0,nElems-1);
	}

	private void recQuickSort(int left, int right)
	{
		int size=right-left+1;
		if(size<=3)
		{
			manualSort(left,right);
		}
		else
		{
			long median=medianOf3(left,right);
			int partition=patitionIt(left,right,median);
			recQuickSort(left,partition-1);
			recQuickSort(partition+1,right);
		}

	}

	private void manualSort(int left, int right)
	{
		int size=right-left+1;
		if(size<=1)
			return;
		if(size==2)
		{
			if(theArray[left]>theArray[right])
			{
				swap(left,right);
			}
			return;
		}
		else     	//size is 3
		{
			if(theArray[left]>theArray[right-1]);
			{
				swap(left,right-1);
			}
			if(theArray[left]>theArray[right])
			{
				swap(left, right);
			}
			if(theArray[right-1]>theArray[right])
			{
				swap(right-1,right);
			}
		}

	}

	private long medianOf3(int left, int right)
	{
		int center=(left+right)/2;
		if(theArray[left]>theArray[center])
		{
			swap(left,center);
		}
		if(theArray[left]>theArray[right])
		{
			swap(left,right);
		}
		if(theArray[center]>theArray[right])
		{
			swap(center,right);
		}
		swap(center,right-1);
		return theArray[right-1];
	}

	private int patitionIt(int left, int right, long pivot)
	{
		int leftPtr=left;
		int rightPtr=right-1;

		while(true)
		{
			while(theArray[++leftPtr]<pivot)
				;
			while(theArray[--rightPtr]>pivot)
				;
			if(leftPtr>=rightPtr)
				break;
			else
			{
				swap(leftPtr,rightPtr);
			}
		}
		swap(leftPtr,right-1);
		return leftPtr;
	}

	private void swap(int leftPtr, int rightPtr)
	{
		long temp=theArray[leftPtr];
		theArray[leftPtr]=theArray[rightPtr];
		theArray[rightPtr]=temp;
	}


	public ArrayIns(int max)
	{
		theArray=new long[max];
		nElems=0;
	}

	public void insert(long value)
	{
		theArray[nElems]=value;
		nElems++;
	}

	public void display()
	{
		System.out.print("A=");
		for(int j=0;j<nElems;j++)
		{
			System.out.print(theArray[j]+" ");
		}
		System.out.println();
	}



}
