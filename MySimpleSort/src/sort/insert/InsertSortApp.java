package sort.insert;

public class InsertSortApp
{
	public static void main(String[] args)
	{
		int maxSize=100;
		ArrayIns arraySel=new ArrayIns(maxSize);
		arraySel.insert(77);
		arraySel.insert(99);
		arraySel.insert(44);
		arraySel.insert(55);
		arraySel.insert(22);
		arraySel.insert(88);
		arraySel.insert(11);
		arraySel.insert(00);
		arraySel.insert(66);
		arraySel.insert(33);

		arraySel.display();
		arraySel.insertionSort();
		arraySel.display();
	}
}

class ArrayIns
{
	private long[] a;
	private int nElemens;

	public ArrayIns(int max)
	{
		a=new long[max];
		nElemens=0;
	}

	public void insert(long value)
	{
		a[nElemens++]=value;
	}

	public void display()
	{
		for (int i = 0; i < nElemens; i++)
		{
			System.out.print(a[i]+" ");
		}
		System.out.println();
	}

	public void insertionSort()
	{
		int in;
		for (int out = 1; out <nElemens ; out++)
		{
			long temp=a[out];
			in=out;
			while(in>0 && a[in-1]>=temp)
			{
				a[in]=a[in-1];
				in--;
			}
			a[in]=temp;
		}
	}
}
