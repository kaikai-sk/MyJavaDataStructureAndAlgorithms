package linkList;

public class Link
{
	public int iData;
	public double dData;
	public Link next;
	
	public Link(int id,double dd)
	{
		this.iData=id;
		this.dData=dd;
		next=null;
	}
	
	public void displayLink()
	{
		System.out.print("{"+iData+","+dData+"}");
	}
}
