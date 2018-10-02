package Tree234;

import java.awt.TextArea;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tree234App
{
	public static void main(String[] args) throws Exception
	{
		long value;
		Tree234 theTree=new Tree234();
		
		theTree.insert(50);
		theTree.insert(40);
		theTree.insert(60);
		theTree.insert(30);
		theTree.insert(70);
		
		while(true)
		{
			System.out.println("Enter first letter of show,insert,or find:");
			char choice=getChar();
			switch(choice)
			{
			case 's':
				theTree.displayTree();
				break;
			case 'i':
				System.out.print("Enter value to insert:");
				value=getInt();
				theTree.insert(value);
				break;
			case 'f':
				System.out.println("Enter value to find:");
				value = getInt();
				int found=theTree.find(value);
				if(found!=-1)
				{
					System.out.println("Found "+value);
				}
				else
				{
					System.out.println("Can not Found "+value);
				}
				break;
			default:
				System.out.println("Invalid Entry:");
				break;
			}
		}
	}

	private static long getInt() throws Exception, IOException
	{
		return Integer.parseInt(getString());
	}

	private static char getChar() throws Exception
	{
		return getString().charAt(0);
	}
	
	public static String getString() throws IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		return br.readLine();
	}
	
	
}

class Tree234
{
	private Node root=new Node();
	
	public void displayTree()
	{
		recDisplayTree(root,0,0);
	}
	
	private void recDisplayTree(Node thisNode, int level, int childNumber)
	{
		System.out.print("level="+level+" child="+childNumber+" ");
		thisNode.displayNode();
		int numItems=thisNode.getNumItems();
		for(int j=0;j<numItems+1;j++)
		{
			Node nextNode=thisNode.getChild(j);
			if(nextNode!=null)
			{
				recDisplayTree(nextNode, level+1, j);
			}
			else
			{
				return;
			}
		}
	}

	public void insert(long dValue)
	{
		Node curNode=root;
		DataItem tempItem=new DataItem(dValue);
		
		while(true)
		{
			if(curNode.isFull())
			{
				split(curNode);
				curNode=curNode.getParent();
				curNode=getNextChild(curNode, dValue);
			}
			else if(curNode.isLeaf())
			{
				break;
			}
			else
			{
				curNode=getNextChild(curNode, dValue);
			}
		}
		curNode.insertItem(tempItem);		
	}
	
	
	private void split(Node thisNode)
	{
		DataItem itemB,itemC;
		Node parent,child2,child3;
		int itemIndex;
		
		itemC=thisNode.removeItem();
		itemB=thisNode.removeItem();
		child2=thisNode.disconnectChild(2);
		child3=thisNode.disconnectChild(3);
		
		Node newRight=new Node();
		
		if(thisNode==root)
		{
			root=new Node();
			parent=root;
			root.connectChild(0, thisNode);
		}
		else
		{
			parent=thisNode.getParent();
		}
		
		//deal with parent
		itemIndex=parent.insertItem(itemB);
		int n=parent.getNumItems();
		
		//保证child和item之间的顺序
		for(int j=n-1;j>itemIndex;j--)
		{
			Node temp=parent.disconnectChild(j);
			parent.connectChild(j+1, temp);
		}
		parent.connectChild(itemIndex+1, newRight);
		
		//deal with newRight
		newRight.insertItem(itemC);
		newRight.connectChild(0, child2);
		newRight.connectChild(1, child3);
	}


	public int find(long key)
	{
		Node currNode=root;
		int childNumber;
		while(true)
		{
			if((childNumber=currNode.findItem(key))!=-1)
			{
				return childNumber;
			}
			else if(currNode.isLeaf())
			{
				return -1;
			}
			else
			{
				currNode=getNextChild(currNode,key);
			}
		}
	}

	private Node getNextChild(Node theNode, long theValue)
	{
		int j;
		int numItems=theNode.getNumItems();
		for(j=0;j<numItems;j++)
		{
			if(theValue<theNode.getItem(j).dData)
			{
				return theNode.getChild(j);
			}
		}
		
		return theNode.getChild(j);
	}
}


class Node
{
	private static final int ORDER=4;
	private int numItems;
	private Node parent;
	private Node[] childArray=new Node[ORDER];
	private DataItem[] itemArray=new DataItem[ORDER-1];

	public void displayNode()
	{
		for(int j=0;j<numItems;j++)
		{
			itemArray[j].displayItem();
		}
		System.out.println();
	}
	
	
	/*
	 * delete the max item in itemArray
	 */
	public DataItem removeItem()
	{
		DataItem temp=itemArray[numItems-1];
		itemArray[numItems-1]=null;
		numItems--;
		return temp;
	}
	
	/**
	 * 保持顺序插入
	 * @param newItem
	 * @return
	 */
	public int insertItem(DataItem newItem)
	{
		numItems++;
		long newKey=newItem.dData;
		
		for(int j=ORDER-2;j>=0;j--)
		{
			if(itemArray[j]==null)
			{
				continue;
			}
			else
			{
				long itsKey=itemArray[j].dData;
				if(newKey<itsKey)
				{
					itemArray[j+1]=itemArray[j];
				}
				else
				{
					itemArray[j+1]=newItem;
					return j+1;
				}
			}
		}//end for
		itemArray[0]=newItem;
		return 0; 
	}
	
	public int findItem(long key)
	{
		for(int j=0;j<ORDER-1;j++)
		{
			if(itemArray[j]==null)
			{
				break;
			}
			else if(itemArray[j].dData==key)
			{
				return j;
			}
		}
		return -1;
	}
	
	public boolean isFull()
	{
		if(numItems==ORDER-1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public DataItem getItem(int index)
	{
		return itemArray[index];
	}
	
	public int getNumItems()
	{
		return numItems;
	}
	
	public boolean isLeaf()
	{
		if(childArray[0]==null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public Node getParent()
	{
		return parent;
	}
	
	public Node getChild(int childNum)
	{
		return childArray[childNum];
	}
	
	//disconnect child from this node,return the child
	public Node disconnectChild(int childNum)
	{
		Node tempNode=childArray[childNum];
		childArray[childNum]=null;
		return tempNode;
	}
	
	//connect child to this node
	public void connectChild(int childNum,Node child)
	{
		childArray[childNum]=child;
		if(child!=null)
		{
			child.parent=this;
		}
	}
	
}

class DataItem
{
	public long dData;
	
	public DataItem(long dd)
	{
		dData=dd;
	}
	
	public void displayItem()
	{
		System.out.print("/"+dData);
	}
	
	
}