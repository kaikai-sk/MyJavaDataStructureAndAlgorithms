package hashTable;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HashTableApp
{
	public static void main(String[] args)
	{
	    DataItem aDataItem;
		int aKey,size,n,keysPerCell;
		System.out.print("Enter size of HashTable:");
		size=getInt();
		//初始的项的个数
		System.out.print("Enter initial number of items:");
		n=getInt();
		keysPerCell=10;

		HashTable theHashTable=new HashTable(size);
		for(int j=0;j<n;j++)
		{
			aKey=(int)(Math.random()*keysPerCell*size);
			aDataItem=new DataItem(aKey);
			theHashTable.insert(aDataItem);
		}

		while(true)
		{
			System.out.print("Enter first letter of ");
			System.out.print("show insert delete or find:");
			char choice =getChar();
			switch (choice)
			{
				case 's':
					theHashTable.displayTable();
					break;
				case 'i':
					System.out.print("Enter key value to insert:");
					aKey=getInt();
					aDataItem=new DataItem(aKey);
					theHashTable.insert(aDataItem);
					break;
				case 'd':
					System.out.print("Enter key value to delete:");
					aKey=getInt();
					theHashTable.delete(aKey);
					break;
				case 'f':
					System.out.print("Enter key value to find:");
					aKey=getInt();
					aDataItem=theHashTable.find(aKey);
					if(aDataItem!=null)
					{
						System.out.println("Find "+aKey);
					}
					else
					{
						System.out.println("Could not find "+aKey);
					}
					break;
				default:
					System.out.println("Invalid Entry!!!");
					break;
			}
		}
	}

	public static String getString()
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s= null;
		try
		{
			s = br.readLine();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return s;
	}

	public static char getChar()
	{
		return getString().charAt(0);
	}

	public static int getInt()
	{
		return Integer.parseInt(getString());
	}
}

/**
 * 开放地址法的简单HashTable
 */
class HashTable
{
	private DataItem hashArray[];
	private int arraySize;
	private DataItem nonItem;

	public DataItem find(int key)
	{
		int hashVal=hashFunc(key);
		/**
		 * 如果遇到空的单元就退出
		 */
		while (hashArray[hashVal]!=null)
		{
			if(hashArray[hashVal].getKey()==key)
			{
				DataItem item=hashArray[hashVal];
				//hashArray[hashVal]=nonItem;
				return item;
			}
			hashVal++;
			hashVal%=arraySize;
		}
		return null;
	}


	public DataItem delete(int key)
	{
		int hashVal=hashFunc(key);

		/**
		 * 如果遇到空的单元就退出
		 */
		while (hashArray[hashVal]!=null)
		{
			if(hashArray[hashVal].getKey()==key)
			{
				DataItem item=hashArray[hashVal];
				hashArray[hashVal]=nonItem;
				return item;
			}
			hashVal++;
			hashVal%=arraySize;
		}
		return null;
	}

	public void insert(DataItem item)
	{
		int key=item.getKey();
		int hashVal=hashFunc(key);

		while(hashArray[hashVal]!=null && hashArray[hashVal].getKey()!=-1)
		{
			hashVal++;
			hashVal%=arraySize;
		}
		hashArray[hashVal]=item;
	}


	public int hashFunc(int key)
	{
		return key%arraySize;
	}


	public HashTable(int size)
	{
		arraySize=size;
		hashArray=new DataItem[arraySize];
		nonItem=new DataItem(-1);
	}

	public void displayTable()
	{
		System.out.print("Table: ");
		for(int j=0;j<arraySize;j++)
		{
			if(hashArray[j]!=null)
			{
				System.out.print(hashArray[j].getKey()+" ");
			}
			else
			{
				System.out.print("** ");
			}
		}
		System.out.println();
	}

}

class DataItem
{
	private int iData;
	public DataItem(int ii)
	{
		iData=ii;
	}

	public int getKey()
	{
		return iData;
	}
}
