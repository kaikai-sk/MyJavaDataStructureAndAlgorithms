package com.sk.radixtree;

import java.util.List;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.sk.radixtree.*;

/**
 * A radix tree. Radix trees are String -> Object mappings which allow quick
 * lookups on the strings. Radix trees also make it easy to grab the objects
 * with a common prefix.
 * 
 * @see <a href="http://en.wikipedia.org/wiki/Radix_tree">Wikipedia</a>
 * 
 * @param <V>  the type of values stored in the tree
 */
public class RadixTree <V extends Serializable> implements Map<String, V>,Serializable
{
	public static final String KEY_CANNOT_BE_NULL="key cannot be null";
	public static final String KEYS_MUST_BE_STRING_INSTANCES="keys must be String instaces";
	
	//radix tree 的根节点
	RadixTreeNode<V> root;
	
	/**
	 * Gets a list of values whose associated keys have the given prefix.
	 * 
	 * @param prefix  the prefix to look for
	 * 
	 * @return the list of values
	 * 
	 * @throws NullPointerException if prefix is <code>null</code>
	 */
	public List<V> getValuesWithPrefix(String prefix)
	{
		RadixTreeVisitor<V,List<V>> visitor=new RadixTreeVisitor<V, List<V>>()
		{
			List<V> result=new ArrayList<V>();
			
			@Override
			public void visit(String key, V value)
			{
				result.add(value);
			}

			@Override
			public List<V> getResult()
			{
				return result;
			}
			
		};
		visit(visitor,prefix);
		return visitor.getResult();
	}
	
	/**
	 * Gets a list of keys with the given prefix.
	 * 
	 * @param prefix  the prefix to look for
	 * 
	 * @return the list of prefixes
	 * 
	 * @throws NullPointerException if prefix is <code>null</code>
	 */
	public List<String> getKeysWithPrefix(String prefix)
	{
		RadixTreeVisitor<V, List<String>> visitor=new RadixTreeVisitor<V, List<String>>()
		{
			List<String> result=new ArrayList<String>();
			
			@Override
			public void visit(String key, V value)
			{
				result.add(key);				
			}

			@Override
			public List<String> getResult()
			{
				return result;
			}
			
		};
		visit(visitor, prefix);
		return visitor.getResult();
	}
	
	
	
	
	/**
	 * Gets a list of entries whose associated keys have the given prefix.
	 * 
	 * @param prefix  the prefix to look for
	 * 
	 * @return the list of values
	 * 
	 * @throws NullPointerException if prefix is <code>null</code>
	 */
	public List<Map.Entry<String,V>> getEntriesWithPrefix(String prefix)
	{
		RadixTreeVisitor<V, List<Map.Entry<String,V>>> visitor=new 
				RadixTreeVisitor<V, List<Map.Entry<String,V>>>()
		{
			List<Map.Entry<String,V>> result=new ArrayList<Map.Entry<String,V>>();
			
			@Override
			public void visit(String key, V value)
			{
				result.add(new AbstractMap.SimpleEntry(key,value));
			}

			@Override
			public List<java.util.Map.Entry<String, V>> getResult()
			{
				return result;
			}
		};
		visit(visitor, prefix);
		return visitor.getResult();
	}
	
	
	/**
	 * Visits the given node of this tree with the given prefix and visitor. Also,
	 * recursively visits the left/right subtrees of this node.
	 * 
	 * @param node  the node
	 * @param prefix  the prefix
	 * @param visitor  the visitor
	 */
	public void  visit(RadixTreeNode<V> node,String prefixAllowed,String prefix,RadixTreeVisitor<V, ?>visitor)
	{
		if(node.hasValue() && prefix.startsWith(prefixAllowed))
		{
			visitor.visit(prefix, node.getValue());
		}
		
		for(RadixTreeNode<V> child:node)
		{
			final int prefixLength=prefix.length();
			final String newPrefix=prefix+child.getPrefix();
			/**
			 * 还需要继续查找的条件
			 */
			if(prefixAllowed.length()<=prefixLength
					|| newPrefix.length()<=prefixLength
					|| newPrefix.charAt(prefixLength) == prefixAllowed.charAt(prefixLength))
			{
				visit(child, prefixAllowed, newPrefix, visitor);
			}
		}
	}
	
	
	/**
	 * Traverses this radix tree using the given visitor. Only values with
	 * the given prefix will be visited. Note that the tree will be traversed
	 * in lexicographical order.
	 * 
	 * @param visitor  the visitor
	 * @param prefix  the prefix used to restrict visitation
	 */
	public void visit(RadixTreeVisitor<V, ?> visitor,String prefix)
	{
		visit(root,prefix,"",visitor);
	}
	
	
	
	/**
	 * Traverses this radix tree using the given visitor. Note that the tree
	 * will be traversed in lexicographical order.
	 * 
	 * @param visitor  the visitor
	 */
	public void visit(RadixTreeVisitor<V, ?> visitor )
	{
		visit(root,"","",visitor);
	}
	
	/**
	 * 缺省的构造函数
	 */
	public RadixTree()
	{
		this.root=new RadixTreeNode<V>("");
	}
	
	@Override
	public int size()
	{
		RadixTreeVisitor<V, Integer> visitor=new RadixTreeVisitor<V, Integer>()
		{
			int count=0;
			
			@Override
			public void visit(String key, V value)
			{
				count++;
			}

			@Override
			public Integer getResult()
			{
				return count;
			}
			
		};
		visit(visitor);
		return visitor.getResult() ;
	}

	@Override
	public boolean isEmpty()
	{
		return root.getChildren().isEmpty();
	}

	@Override
	public boolean containsKey(final Object keyToCheck)
	{
		if (keyToCheck==null)
		{
			throw new NullPointerException(KEY_CANNOT_BE_NULL);
		}
		if(!(keyToCheck instanceof String))
		{
			throw new ClassCastException(KEYS_MUST_BE_STRING_INSTANCES);
		}
		
		RadixTreeVisitor<V, Boolean> visitor=new RadixTreeVisitor<V, Boolean>()
		{
			boolean found =false;
			
			@Override
			public void visit(String key, V value)
			{
				if(key.equals(keyToCheck))
				{
					found=true;
				}
			}

			@Override
			public Boolean getResult()
			{
				return found;
			}
		};
		visit(visitor, (String)keyToCheck);
		return visitor.getResult();
		
	}

	@Override
	public boolean containsValue(final Object val)
	{
		RadixTreeVisitor<V, Boolean> visitor=new RadixTreeVisitor<V, Boolean>()
		{
			boolean found=false;
			
			@Override
			public void visit(String key, V value)
			{
				if(val==value || (value!=null) && value.equals(val))
				{
					found=true;
				}
			}

			@Override
			public Boolean getResult()
			{
				return found;
			}
			
		};
		visit(visitor);
		return visitor.getResult();
	}

	@Override
	public V get(final Object keyToCheck)
	{
		if(keyToCheck==null)
		{
			throw new NullPointerException(KEY_CANNOT_BE_NULL);
		}
		if(!(keyToCheck instanceof String))
		{
			throw new ClassCastException(KEYS_MUST_BE_STRING_INSTANCES);
		}
		
		RadixTreeVisitor<V, V> visitor=new RadixTreeVisitor<V, V>()
		{
			V result=null;
			
			@Override
			public void visit(String key, V value)
			{
				if(key.equals(keyToCheck))
				{
					result=value;
				}
				
			}

			@Override
			public V getResult()
			{
				return result;
			}
		};
		visit(visitor, (String)keyToCheck);
		return visitor.getResult();
	}

	@Override
	public V put(String key, V value)
	{
		if(key==null)
		{
			throw new NullPointerException(KEY_CANNOT_BE_NULL);
		}
		
		return put(key, value,root);
	}

	/**
	 * Remove the value with the given key from the subtree rooted at the
	 * given node.
	 * 
	 * @param key  the key
	 * @param node  the node to start searching from
	 * 
	 * @return the old value associated with the given key, or <code>null</code>
	 *         if there was no mapping for <code>key</code> 
	 */
	private V put(String key, V value, RadixTreeNode<V> node)
	{
		V ret =null;
		final int largestPrefixLength=RadixTreeUtil.largestPrefixLength(key, node.getPrefix());
		
		//key就是node的情况
		if(largestPrefixLength==node.getPrefix().length() && largestPrefixLength==key.length())
		{
			ret=node.getValue();
			node.setValue(value);
			node.setHasValue(true);
		}
		//key所在节点在node的子节点中
		else if(largestPrefixLength==0 ||
				(largestPrefixLength<key.length()) && largestPrefixLength>=node.getPrefix().length() )
		{
			// Key is bigger than the prefix located at this node, so we need to see if
			// there's a child that can possibly share a prefix, and if not, we just add
			// a new node to this node
			final String leftoverKey=key.substring(largestPrefixLength);
			boolean found=false;
			for(RadixTreeNode<V> child:node)
			{
				if(child.getPrefix().charAt(0)==leftoverKey.charAt(0))
				{
					found=true;
					ret=put(leftoverKey, value, child);
					break;
				}
			}
			
			if(!found)
			{
				// No child exists with any prefix of the given key, so add a new one
				RadixTreeNode<V> n=new RadixTreeNode<V>(leftoverKey,value);
				node.getChildren().add(n);
			}
		}
		// Key and node.getPrefix() share a prefix, so split node
		else if(largestPrefixLength<node.getPrefix().length())
		{
			final String leftoverPrefix=node.getPrefix().substring(largestPrefixLength);
			
			node.printInfo();
			
			//创建一个新的节点
			final RadixTreeNode<V> n=new RadixTreeNode<V>(leftoverPrefix,node.getValue());
			n.setHasValue(node.hasValue());
			n.getChildren().addAll(node.getChildren());
			
			n.printInfo();
			
			//原来的节点升级了(喜当爹)
			node.setPrefix(node.getPrefix().substring(0,largestPrefixLength));
			node.getChildren().clear();
			node.getChildren().add(n);
			
			node.printInfo();
			
			// The largest prefix is equal to the key, so set this node's value
			if(largestPrefixLength==key.length())
			{
				ret=node.getValue();
				node.setValue(value);
				node.setHasValue(true);
			}
			//// There's a leftover suffix on the key, so add another child 
			else
			{
				final String leftoverKey=key.substring(largestPrefixLength);
				final RadixTreeNode<V> n1=new RadixTreeNode<V>(leftoverKey,value);
				node.getChildren().add(n1);
				
				node.printInfo();
				
				node.setHasValue(false);
			}
		}
		else
		{
			// node.getPrefix() is a prefix of key, so add as child
			final String leftoverKey=key.substring(largestPrefixLength);
			RadixTreeNode<V> n=new RadixTreeNode<V>(leftoverKey,value);
			node.getChildren().add(n);
		}
		return ret;
	}


	@Override
	public V remove(Object key)
	{
		if(key==null)
		{
			throw new NullPointerException(KEY_CANNOT_BE_NULL);
		}
		if(!(key instanceof String))
		{
			throw new ClassCastException(KEYS_MUST_BE_STRING_INSTANCES);
		}
		
		final String sKey=(String)key;
		if(sKey.equals(""))
		{
			final V value=root.getValue();
			root.setHasValue(false);
			return value;
		}
		return remove(sKey,root);
	}

	/**
	 * Remove the value with the given key from the subtree rooted at the
	 * given node.
	 * 
	 * @param key  the key
	 * @param node  the node to start searching from
	 * 
	 * @return the value associated with the given key, or <code>null</code>
	 *         if there was no mapping for <code>key</code> 
	 */
	private V remove(String key,RadixTreeNode<V> node)
	{
		V ret =null;
		final Iterator<RadixTreeNode<V>> iterator=node.getChildren().iterator();
		while(iterator.hasNext())
		{
			final RadixTreeNode<V> child=iterator.next();
			final int largestPrefixLen=RadixTreeUtil.largestPrefixLength(key, child.getPrefix());
			// Found our match, remove the value from this node
			if(largestPrefixLen==key.length() && largestPrefixLen==child.getPrefix().length())
			{
				if(child.getChildren().isEmpty())
				{
					ret=child.getValue();
					iterator.remove();
					break;
				}
				else if(child.hasValue())
				{
					//internal node
					ret=child.getValue();
					child.setHasValue(false);
					
					if(child.getChildren().size()==1)
					{
						// The subchild's prefix can be reused, with a little modification
						final RadixTreeNode<V> subChild=child.getChildren().iterator().next();
						final String newPrefix=child.getPrefix()+subChild.getPrefix();
						
						// Merge child node with its single child
						child.setValue(subChild.getValue());
						child.setHasValue(subChild.hasValue());
						child.setPrefix(newPrefix);
						child.getChildren().clear();
					}
					break;
				}
			}
			else if(largestPrefixLen>0 && largestPrefixLen<key.length())
			{
				// Continue down subtree of child
				final String leftoverKey=key.substring(largestPrefixLen);
				ret=remove(leftoverKey, child);
				break;
			}
		}
		return ret;
	}
	
	@Override
	public void putAll(Map<? extends String, ? extends V> map)
	{
		for(Map.Entry<? extends String, ? extends V> entry:map.entrySet())
		{
			put(entry.getKey(), entry.getValue());
		}
		
	}

	@Override
	public void clear()
	{
		root.getChildren().clear();
	}

	@Override
	public Set<String> keySet()
	{
		// TODO documentation Of Map.keySet() specifies that this is a view of
		//      the keys, and modifications to this collection should be
		//      reflected in the parent structure
		//
		RadixTreeVisitor<V, Set<String>> visitor=new RadixTreeVisitor<V, Set<String>>()
		{
			Set<String> result=new TreeSet<String>();
			
			@Override
			public void visit(String key, V value)
			{
				result.add(key);
			}

			@Override
			public Set<String> getResult()
			{
				// TODO Auto-generated method stub
				return result;
			}
			
		};
		visit(visitor);
		return visitor.getResult();
	}

	@Override
	public Collection<V> values()
	{
		// TODO documentation Of Map.values() specifies that this is a view of
		//      the values, and modifications to this collection should be
		//      reflected in the parent structure
		//
		RadixTreeVisitor<V, Collection<V>> visitor=new RadixTreeVisitor<V, Collection<V>>()
		{
			Collection<V> values=new ArrayList<V>();

			@Override
			public void visit(String key, V value)
			{
				values.add(value);
			}

			@Override
			public Collection<V> getResult()
			{
				return values;
			}
		};
		visit(visitor);
		return visitor.getResult();
	}

	@Override
	public Set<java.util.Map.Entry<String, V>> entrySet()
	{
		// TODO documentation Of Map.entrySet() specifies that this is a view of
		//      the entries, and modifications to this collection should be
		//      reflected in the parent structure
		//
		RadixTreeVisitor<V, Set<Map.Entry<String, V>>> visitor=new 
				RadixTreeVisitor<V, Set<Map.Entry<String, V>>>()
		{
			Set<Map.Entry<String, V>> result = new HashSet<Map.Entry<String, V>>();

			@Override
			public void visit(String key, V value)
			{
				result.add(new AbstractMap.SimpleEntry(key,value));
			}

			@Override
			public Set<java.util.Map.Entry<String, V>> getResult()
			{
				return result;
			}
			
			
		};
		
		visit(visitor);
		return visitor.getResult();	
	}

}
