package binarySearch;

public class BinarySearchApp
{
	public static void main(String[] args)
	{
	    int maxSize=100;
	    OrdArray arr=new OrdArray(maxSize);

	    arr.insert(72);
		arr.insert(90);
		arr.insert(45);
		arr.insert(126);
		arr.insert(54);
		arr.insert(99);
		arr.insert(144);
		arr.insert(27);
		arr.insert(135);
		arr.insert(81);
		arr.insert(18);
		arr.insert(108);
		arr.insert(9);
		arr.insert(117);
		arr.insert(63);
		arr.insert(36);

		arr.display();

		int searchKey=27;
		if(arr.find(searchKey)!=arr.size())
		{
			System.out.println("Found "+searchKey);
		}
		else
		{
			System.out.println("Can't find "+searchKey);
		}


	}
}

class OrdArray
{
	private long a[];
	private int nElemens;

	public OrdArray(int max)
	{
		a=new long[max];
		nElemens=0;
	}

	public int size()
	{
		return nElemens;
	}

	public int find(long searchKey)
	{
		return recFind(searchKey,0,nElemens-1);
	}

	/**
	 *
	 * @param searchKey
	 * @param lowerBound
	 * @param upperBound
	 * @return 要查找的元素的下标
	 */
	private int recFind(long searchKey, int lowerBound, int upperBound)
	{
		int currIndex;
		currIndex=(lowerBound+upperBound)/2;
		if(a[currIndex]==searchKey)
		{
			return currIndex;
		}
		else if(lowerBound>upperBound)
		{
			return nElemens;
		}
		else
		{
			if(a[currIndex]<searchKey)
			{
				return recFind(searchKey,currIndex+1,upperBound);
			}
			else
			{
				return recFind(searchKey,lowerBound,currIndex-1);
			}
		}
	}

	public void display()
	{
		for(int j=0;j<nElemens;j++)
		{
			System.out.print(a[j]+" ");
		}
		System.out.println();
	}

	public void insert(long value)
	{
		int j;
		for(j=0;j<nElemens;j++)
		{
			if(a[j]>value)
			{
				break;
			}
		}
		for(int k=nElemens;k>j;k--)
		{
			a[k]=a[k-1];
		}
		a[j]=value;
		nElemens++;
	}
}
