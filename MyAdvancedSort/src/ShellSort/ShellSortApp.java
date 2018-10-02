package ShellSort;

public class ShellSortApp
{
	public static void main(String[] args)
	{
	    int maxSize=10;
		ArraySh arr=new ArraySh(maxSize);

		for(int j=0;j<maxSize;j++)
		{
			long n=(int)((Math.random())*99);
			arr.insert(n);
		}
		arr.display();
		arr.shellSort();
		arr.display();
	}
}

class ArraySh
{
	private long[] theArray;
	private int nElems;

	public void shellSort()
	{
		int inner,outer;
		long temp;

		int h=1;
		while (h<=nElems/3)
		{
			h=h*3+1;
		}

		while(h>0)
		{
			for(int i=0;i<h;i++)
			{
				outer=h+i;

				/**
				 * 内部是个插入排序
				 */
				while(outer<nElems)
				{
					temp=theArray[outer];
					inner=outer;

					while (inner>0 && theArray[inner-h]>temp)
					{
						theArray[inner]=theArray[inner-h];
						inner-=h;
					}

					theArray[inner]=temp;
					outer+=h;
				}
			}//end for
			h=(h-1)/3;
		}
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

	public ArraySh(int max)
	{
		theArray=new long[max];
		nElems=0;
	}



	public void insert(long value)
	{
		theArray[nElems++]=value;
	}
}