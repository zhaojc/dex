package com.lotoquebec.iris.mei;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author Daniel Marchese
 *
 * Implante une collection de DTO
 */
public class DTO_Collection extends BaseDTO implements Collection {

	private Collection collection;

	/* (non-Javadoc)
	 * @see java.util.Collection#size()
	 */
	public int size()
	{
		return this.getCollection().size();
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#isEmpty()
	 */
	public boolean isEmpty()
	{
		return this.getCollection().isEmpty();
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#contains(java.lang.Object)
	 */
	public boolean contains(Object o)
	{
		return this.getCollection().contains(o);
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#iterator()
	 */
	public Iterator iterator()
	{
		return this.getCollection().iterator();
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#toArray()
	 */
	public Object[] toArray()
	{
		return this.getCollection().toArray();
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#toArray(java.lang.Object[])
	 */
	public Object[] toArray(Object[] a)
	{
		return this.getCollection().toArray(a);
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#add(java.lang.Object)
	 */
	public boolean add(Object o)
	{
		return this.getCollection().add(o);
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#remove(java.lang.Object)
	 */
	public boolean remove(Object o)
	{
		return this.getCollection().remove(o);
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#containsAll(java.util.Collection)
	 */
	public boolean containsAll(Collection c)
	{
		return this.getCollection().containsAll(c);
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#addAll(java.util.Collection)
	 */
	public boolean addAll(Collection c)
	{
		return this.getCollection().addAll(c);
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#removeAll(java.util.Collection)
	 */
	public boolean removeAll(Collection c)
	{
		return this.getCollection().removeAll(c);
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#retainAll(java.util.Collection)
	 */
	public boolean retainAll(Collection c)
	{
		return this.getCollection().retainAll(c);
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#clear()
	 */
	public void clear()
	{
		this.getCollection().clear();
	}

	/**
	 * @return
	 */
	public Collection getCollection() {
		return collection;
	}

	/**
	 * @param collection
	 */
	public void setCollection(Collection collection) {
		this.collection = collection;
	}

    public boolean equals (Object o) {
    	return super.equals(o);
    }
    
    public int hashCode(){
    	return 1;
    }
    
}
