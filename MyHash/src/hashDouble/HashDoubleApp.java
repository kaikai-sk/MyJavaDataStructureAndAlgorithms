package hashDouble;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HashDoubleApp
{
	public static void main(String[] args)
	{
		DataItem aDataItem;
		int aKey, size, n;

		System.out.print("Enter size of HashTable:");
		size = getInt();
		//初始的项的个数
		System.out.print("Enter initial number of items:");
		n = getInt();

		HashTable theHashTable = new HashTable(size);
		for (int j = 0; j < n; j++)
		{
			aKey = (int) (Math.random() * 2 * size);
			aDataItem = new DataItem(aKey);
			theHashTable.insert(aKey,aDataItem);
		}

		while (true)
		{
			System.out.print("Enter first letter of ");
			System.out.print("show insert delete or find:");
			char choice = getChar();
			switch (choice)
			{
				case 's':
					theHashTable.displayTable();
					break;
				case 'i':
					System.out.print("Enter key value to insert:");
					aKey = getInt();
					aDataItem = new DataItem(aKey);
					theHashTable.insert(aKey,aDataItem);
					break;
				case 'd':
					System.out.print("Enter key value to delete:");
					aKey = getInt();
					theHashTable.delete(aKey);
					break;
				case 'f':
					System.out.print("Enter key value to find:");
					aKey = getInt();
					aDataItem = theHashTable.find(aKey);
					if (aDataItem != null)
					{
						System.out.println("Find " + aKey);
					}
					else
					{
						System.out.println("Could not find " + aKey);
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
 * 使用再哈希法的HashTable
 */
class HashTable
{
	private DataItem[] hashArray;
	private int arraySize;
	private DataItem nonItem;




	public DataItem find(int key)
	{
		int hashVal=hashFunc1(key);
		int stepSize=hashFunc2(key);

		while(hashArray[hashVal]!=null)
		{
			if(key==hashArray[hashVal].getKey())
			{
				DataItem item=hashArray[hashVal];
				return item;
			}
			else
			{
				hashVal+=stepSize;
				hashVal%=stepSize;
			}
		}
		return null;
	}


	public DataItem delete(int key)
	{
		int hashVal=hashFunc1(key);
		int stepSize=hashFunc2(key);

		while(hashArray[hashVal]!=null)
		{
			if(key==hashArray[hashVal].getKey())
			{
				DataItem item=hashArray[hashVal];
				hashArray[hashVal]=nonItem;
				return item;
			}
			else
			{
				hashVal+=stepSize;
				hashVal%=stepSize;
			}
		}
		return null;
	}


	public void insert(int key, DataItem item)
	{
		int hashVal=hashFunc1(key);
		int stepSize=hashFunc2(key);

		while(hashArray[hashVal]!=null &&hashArray[hashVal].getKey()!=-1)
		{
			hashVal+=stepSize;
			hashVal%=arraySize;
		}
		hashArray[hashVal]=item;
	}

	/**
	 * 再哈希函数
	 * @param key
	 * @return
	 */
	public int hashFunc2(int key)
	{
		return 5-(key%5);
	}

	/**
	 * 第一次hash，取模计算index
	 * @return
	 */
	public int hashFunc1(int key)
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
		System.out.print("Table:");
		for(int j=0;j<arraySize;j++)
		{
			if(hashArray[j]!=null)
			{
				System.out.print(hashArray[j].getKey()+' ');
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
