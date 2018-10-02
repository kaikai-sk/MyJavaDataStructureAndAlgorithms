package com.sk.radixtree;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

import javax.xml.soap.Node;

public class RadixTreeNode <V extends Serializable> implements Iterable<RadixTreeNode<V>>,
Comparable<RadixTreeNode<V>>
{
	//�ڽڵ��е�ǰ׺
	private String prefix;
	//�洢�ڽڵ��е�value
	private V value;
	//�ýڵ��Ƿ�洢һ��ֵ�� ���ֵ��Ҫ��RadixTreeVisitor�����ж��Ƿ�Ӧ�÷�������ڵ㡣
	private boolean hasValue;
	//����ڵ�ĺ��ӽڵ㡣��ע�⣬��Ϊ����������ʹ����{@link TreeSet}������RadixTree�ı��������ֵ�˳�����С�
	private Collection<RadixTreeNode<V>> children;
	
	/**
	 * ��ӡnode�ڵ����Ϣ
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
	 * ���ݸ�����prefix����ڵ�
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
		//�ӳٴ���children�Խ�Լ�ڴ�
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
