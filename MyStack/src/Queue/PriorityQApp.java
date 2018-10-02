package Queue;

public class PriorityQApp
{
	public static void main(String[] args)
	{
	    PriorityQueue thePQ=new PriorityQueue(5);

	    thePQ.insert(30);
		thePQ.insert(50);
		thePQ.insert(10);
		thePQ.insert(40);
		thePQ.insert(20);

		while(!thePQ.isEmpty())
		{
			long item=thePQ.remove();
			System.out.print(item+" ");
		}
		System.out.println();
	}
}

class PriorityQueue
{
	private int maxSize;
	private long[] queArray;
	private int nItems;

	public PriorityQueue(int s)
	{
		maxSize=s;
		queArray=new long[maxSize];
		nItems=0;
	}

	

	public boolean isFull()
	{
		return nItems==maxSize;
	}

	public boolean isEmpty()
	{
		return nItems==0;
	}

	public long peekMin()
	{
		return queArray[nItems-1];
	}

	public long remove()
	{
		return queArray[--nItems];
	}

	public void insert(long item)
	{
		int j;
		if(nItems==0)
			queArray[nItems++]=item;
		else
		{
			for(j=nItems-1;j>=0;j--)
			{
				if(item>queArray[j])
				{
					queArray[j+1]=queArray[j];
				}
				else
					break;
			}
			queArray[j+1]=item;
			nItems++;
		}

	}
}
