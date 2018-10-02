package mergeSort;

public class MergeSortApp
{
	public static void main(String[] args)
	{
	    int maxSize=100;
	    DArray theArray=new DArray(maxSize);

	    theArray.insert(64);
		theArray.insert(21);
		theArray.insert(33);
		theArray.insert(70);
		theArray.insert(12);
		theArray.insert(85);
		theArray.insert(44);
		theArray.insert(3);
		theArray.insert(99);
		theArray.insert(0);
		theArray.insert(108);
		theArray.insert(36);

		theArray.display();

		theArray.mergeSort();

		theArray.display();

	}
}

class DArray
{
	private long[] theArray;
	private int nElemens;

	public DArray(int max)
	{
		theArray=new long[max];
		nElemens=0;
	}

	public void insert(long value)
	{
		theArray[nElemens++]=value;
	}

	public void display()
	{
		for (int i = 0; i < nElemens; i++)
		{
			System.out.print(theArray[i]+" ");
		}
		System.out.println();
	}

	public void mergeSort()
	{
		long[] workSpace=new long[nElemens];
		recMergeSort(workSpace,0,nElemens-1);
	}

	/**
	 * 真正的归并排序
	 * @param workSpace
	 * @param lowerBound
	 * @param upperBound
	 */
	private void recMergeSort(long[] workSpace, int lowerBound, int upperBound)
	{
		if(lowerBound==upperBound)
		{
			return;
		}
		else
		{
			int mid=(lowerBound+upperBound)/2;
			recMergeSort(workSpace,lowerBound,mid);
			recMergeSort(workSpace,mid+1,upperBound);
			merge(workSpace,lowerBound,mid+1,upperBound);
		}
	}

	private void merge(long[] workSpace, int lowPtr, int hightPtr, int upperBound)
	{
		int j=0;
		int lowerBound=lowPtr;
		int mid=hightPtr-1;
		int items=upperBound-lowerBound+1;

		while(lowPtr<=mid && hightPtr<=upperBound)
		{
			if(theArray[lowPtr]<theArray[hightPtr])
			{
				workSpace[j++]=theArray[lowPtr++];
			}
			else
			{
				workSpace[j++]=theArray[hightPtr++];
			}
		}
		while(lowPtr<=mid)
		{
			workSpace[j++]=theArray[lowPtr++];
		}
		while(hightPtr<=upperBound)
		{
			workSpace[j++]=theArray[hightPtr++];
		}
		for (int i = 0; i < items; i++)
		{
			theArray[lowerBound+i]=workSpace[i];
		}
	}//end merge
}