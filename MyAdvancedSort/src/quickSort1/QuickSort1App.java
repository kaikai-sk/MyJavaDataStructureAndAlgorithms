package quickSort1;

public class QuickSort1App
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
		if(right-left<=0)
		{
			return;
		}
		else
		{
			long pivot=theArray[right];

			int partition=patitionIt(left,right,pivot);
			recQuickSort(left,partition-1);
			recQuickSort(partition+1,right);
		}

	}

	private int patitionIt(int left, int right, long pivot)
	{
		int leftPtr=left-1;
		int rightPtr=right;

		while(true)
		{
			while(theArray[++leftPtr]<pivot && leftPtr<right)
				;
			while(theArray[--rightPtr]>pivot && rightPtr>left)
				;
			if(leftPtr>=rightPtr)
				break;
			else
			{
				swap(leftPtr,rightPtr);
			}
		}
		swap(leftPtr,right);
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
