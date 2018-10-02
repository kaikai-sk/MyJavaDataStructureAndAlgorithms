package sort.bubble;

public class BubbleSortApp
{
	public static void main(String[] args)
	{
	    int maxSize=100;
	    ArrayBub arrayBub=new ArrayBub(maxSize);
	    arrayBub.insert(77);
		arrayBub.insert(99);
		arrayBub.insert(44);
		arrayBub.insert(55);
		arrayBub.insert(22);
		arrayBub.insert(88);
		arrayBub.insert(11);
		arrayBub.insert(00);
		arrayBub.insert(66);
		arrayBub.insert(33);

		arrayBub.display();
		arrayBub.bubbleSort();
		arrayBub.display();
	}
}

class ArrayBub
{
	private long[] a;
	private int nElems;

	public ArrayBub(int max)
	{
		a=new long[max];
		nElems=0;
	}

	public void insert(long value)
	{
		a[nElems]=value;
		nElems++;
	}

	public void display()
	{
		for(int j=0;j<nElems;j++)
		{
			System.out.print(a[j]+" ");
		}
		System.out.println();
	}

	public void bubbleSort()
	{
		int out,in;
		for(out=nElems-1;out>=1;out--)
		{
			for(in=0;in<out;in++)
			{
				if(a[in]>a[in+1])
				{
					swap(in,in+1);
				}
			}
			System.out.print("out="+out+" ");
			display();
		}
	}

	private void swap(int one,int two)
	{
		long temp=a[one];
		a[one]=a[two];
		a[two]=temp;
	}
}
