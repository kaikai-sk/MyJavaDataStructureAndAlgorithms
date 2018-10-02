package sort.object;

public class ObjectSortApp
{
	public static void main(String[] args)
	{
	    int maxSize=100;
	    ArrayInOb array=new ArrayInOb(maxSize);

	    array.insert("Evans","Petty",24);
		array.insert("Smith","Doc",59);
		array.insert("Smith","Lorraine",37);
		array.insert("Smith","Paul",37);
		array.insert("Yee","Tom",43);
		array.insert("Hashimoto","Sato",21);
		array.insert("Velasquez","Jose",72);
		array.insert("Vang","Minh",22);
		array.insert("Creswell","Lucinda",18);

		array.display();
		array.insertionSort();
		array.display();
	}
}

class ArrayInOb
{
	private Person[] a;
	private int nElemes;

	public ArrayInOb(int max)
	{
		a=new Person[max];
		nElemes=0;
	}

	public void insert(String last,String first,int age)
	{
		a[nElemes]=new Person(last,first,age);
		nElemes++;
	}

	public void display()
	{
		for (int i = 0; i < nElemes; i++)
		{
			a[i].displayPerson();
		}
		System.out.println();
	}

	public void insertionSort()
	{
		int in;
		for (int out=1;out<nElemes;out++)
		{
			Person temp=a[out];
			in = out;
			while (in>0 && a[in-1].getLastName().compareTo(temp.getLastName())>0)
			{
				a[in]=a[in-1];
				in--;
			}
			a[in]=temp;
		}
	}
}

class Person
{
	private String lastName;
	private String firstName;
	private int age;

	public Person(String last,String first,int a)
	{
		this.lastName=last;
		this.firstName=first;
		this.age=a;
	}

	public void displayPerson()
	{
		System.out.print(" LastName:"+lastName);
		System.out.print(",FisrtName:"+firstName);
		System.out.println(",Age:"+age);
	}

	public String getLastName()
	{
		return lastName;
	}
}

