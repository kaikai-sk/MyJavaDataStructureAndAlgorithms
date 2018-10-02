package com.sk.radixtree;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

import javax.xml.soap.Node;

public class RadixTreeNode <V extends Serializable> implements Iterable<RadixTreeNode<V>>,
Comparable<RadixTreeNode<V>>
{
	//在节点中的前缀
	private String prefix;
	//存储在节点中的value
	private V value;
	//该节点是否存储一个值。 这个值主要由RadixTreeVisitor用来判断是否应该访问这个节点。
	private boolean hasValue;
	//这个节点的孩子节点。请注意，因为我们在这里使用了{@link TreeSet}，所以RadixTree的遍历将按字典顺序排列。
	private Collection<RadixTreeNode<V>> children;
	
	/**
	 * 打印node节点的信息
	 */
	public void printInfo()
	{
		if(children!=null)
		{
			System.out.println("[prefix="+prefix
					+",value="+value+
					",hasValue="+hasValue+
					",numOfChildren"+children.size()+"]");	
		}
		else
		{
			System.out.println("[prefix="+prefix
					+",value="+value+
					",hasValue="+hasValue+
					"]");
		}
	}
	
	
	/**
	 * 根据给出的prefix构造节点
	 * @param prefix
	 */
	public RadixTreeNode(String prefix)
	{
		this.prefix=prefix;
		this.hasValue=false;
	}
	
	/**
	 * Constructs a node from the given prefix and value.
	 * 
	 * @param prefix  the prefix
	 * @param value  the value
	 */
	public RadixTreeNode(String prefix,V value)
	{
		this.prefix=prefix;
		this.value=value;
		this.hasValue=true;
	}
	
	/**
	 * Gets the value attached to this node.
	 * 
	 * @return the value, or <code>null</code> if an internal node
	 */
	public V getValue()
	{
		return value;
	}
	
	/**
	 * Sets the value attached to this node.
	 * 
	 * @param value  the value, or <code>null</code> if an internal node
	 */
	public void setValue(V value)
	{
		this.value=value;
	}
	
	/**
	 * Gets the prefix associated with this node.
	 * 
	 * @return the prefix
	 */
	String getPrefix()
	{
		return prefix;
	}
	
	/**
	 * Sets the prefix associated with this node.
	 * 
	 * @param prefix  the prefix
	 */
	void setPrefix(String prefix)
	{
		this.prefix=prefix;
	}
	
	/**
	 * Gets the children of this node.
	 * 
	 * @return the list of children
	 */
	Collection<RadixTreeNode<V>> getChildren()
	{
		//延迟创建children以节约内存
		if(children==null)
		{
			children=new TreeSet<RadixTreeNode<V>>();
		}
		return children;
	}
	
	/**
	 * Whether or not this node has a value attached to it.
	 * 
	 * @return whether or not this node has a value
	 */
	boolean hasValue()
	{
		return hasValue;
	}
	
	/**
	 * Sets whether or not this node has a value attached to it.
	 * 
	 * @param hasValue  <code>true</code> if this node will have a value,
	 *                  <code>false</code> otherwise. If <code>false</code>,
	 *                  {@link #getValue()} will return <code>null</code>
	 *                  after this call.
	 */
	void setHasValue(boolean hasValue)
	{
		this.hasValue=hasValue;
		if(!hasValue)
		{
			//this.hasValue=(Boolean) null;
		}
	}

	@Override
	public int compareTo(RadixTreeNode<V> node)
	{
		return prefix.toString().compareTo(node.getPrefix().toString());
	}

	@Override
	public Iterator<RadixTreeNode<V>> iterator()
	{
		if(children==null)
		{
			return new Iterator<RadixTreeNode<V>>()
			{

				@Override
				public boolean hasNext()
				{
					return false;
				}

				@Override
				public RadixTreeNode<V> next()
				{
					return null;
				}
			};
		}
		return children.iterator();
	}
}
