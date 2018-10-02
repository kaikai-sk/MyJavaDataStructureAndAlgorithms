package Queue.noItem;

/**
 * Queue implement without nItem
 */
public class Queue
{
	private int maxSize;
	private long[] queArray;
	private int front;
	private int rear;

	public Queue(int s)
	{
		maxSize=s+1;
		queArray=new long[maxSize];
		front=0;
		rear=-1;
	}

	public void insert(long j)
	{
		if(rear==maxSize-1)
			rear=-1;
		queArray[++rear]=j;
	}

	public long remove()
	{
		long temp=queArray[front++];
		if(front==maxSize)
			front=0;
		return temp;
	}

	public long peek()
	{
		return queArray[front];
	}

}
