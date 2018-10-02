package sort.select;

public class SelectSortApp
{
	public static void main(String[] args)
	{
		int maxSize=100;
		ArraySel arraySel=new ArraySel(maxSize);
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
		arraySel.selectSort();
		arraySel.display();
	}
}

class ArraySel
{
	private long a[];
	int nElemens;

	public ArraySel(int max)
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
		for(int i=0;i<nElemens;i++)
		{
			System.out.print(a[i]+" ");
		}
		System.out.println();
	}

	public void selectSort()
	{
		int minIndex;
		for(int out=0;out<nElemens-1;out++)
		{
			minIndex=out;
			for(int in=out;in<nElemens;in++)
			{
				if(a[in]<a[minIndex])
				{
					minIndex=in;
				}
			}
			swap(out,minIndex);
		}
	}

	private void swap(int one,int two)
	{
		long temp=a[one];
		a[one]=a[two];
		a[two]=temp;
	}
}
