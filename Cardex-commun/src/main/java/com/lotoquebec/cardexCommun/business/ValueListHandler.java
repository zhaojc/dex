/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardexCommun.business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import com.lotoquebec.cardexCommun.exception.IteratorException;

/**
 * The ValueListHandler is a  generic collection (a list) that holds
 * value objects obtain from a prior query and provide iteration facility
 * on the object list.
 *
 * @see com.lotoquebec.cardexCommun.business.ValueListIterator
 * @author $Autor: $
 * @version $Revision: 1.8 $, $Date: 2002/03/19 23:30:24 $
 */
public class ValueListHandler implements ValueListIterator {
    private List         list;
    private ListIterator listIterator;

    /**
     * Construct a ValueListHandler instance.
     */
    public ValueListHandler() {}

    public ValueListHandler(List list) throws IteratorException {
    	setList(list);
    }
    /**
     * Set the list of value objects.
     *
     *
     * @param list The collection of value objetcs.
     *
     * @throws IteratorException If the list is null
     */
    public void setList(List list) throws IteratorException {
        this.list = list;

        if (list != null) {
            this.listIterator = list.listIterator();
        } else {
            throw new IteratorException("List not set");
        }
    }

    /**
     * Return the list of value objects.
     *
     * @return Collection The collection of value objetcs.
     *
     * @throws IteratorException If no list are set
     */
    public Collection getList() {
        return this.list;
    }

    /**
     * Return the size of the list.
     *
     * @return int The size of the result set.
     *
     * @throws IteratorException If no list are set
     *
     * @see com.lotoquebec.cardexCommun.business.ValueListIterator#getSize()
     */
    public int getSize() throws IteratorException {
        int size = 0;

        if (list != null) {
            size = this.list.size();
        } else {
            throw new IteratorException("List not set");
        }

        return size;
    }

    /**
     * Return the current value object from the list.
     *
     * @return Object the current value object from the list.
     *
     * @throws IteratorException If no list are set
     *
     * @see com.lotoquebec.cardexCommun.business.ValueListIterator#getCurrentElement()
     */
    public Object getCurrentElement() throws IteratorException {
        Object obj = null;

        if (list != null) {
            int currentIndex = listIterator.nextIndex();

            obj = list.get(currentIndex);
        } else {
            throw new IteratorException("List not set");
        }

        return obj;
    }

    /**
     * Return a collection of value objects that are in the list
     * prior to the current element.
     *
     * @param count How many objects prior to the current element.
     *
     * @return List  Collection of value objects that are in the list
     * prior to the current element.
     *
     * @throws IteratorException If no list are set
     *
     * @see com.lotoquebec.cardexCommun.business.ValueListIterator#getPreviousElements()
     */
    public List getPreviousElements(int count)
            throws IteratorException {
        Object     obj = null;
        LinkedList list = new LinkedList();

        if (count < 1 ){
          return list;
        }

        if (this.listIterator != null) {
            for (int index = 0;
                    this.listIterator.hasPrevious() && (index < count);
                    index++) {
                obj = listIterator.previous();

                list.addFirst(obj);
            }
        } else {
            throw new IteratorException("List not set");
        }
        return list;
    }

     /**
     * Return a collection of value objects that are in the list
     * after the current element.
     *
     * @param count How many objects after the current element.
     *
     * @return List  Collection of value objects that are in the list
     * after the current element.
     *
     * @throws IteratorException If no list are set
    *
     * @see com.lotoquebec.cardexCommun.business.ValueListIterator#getNextElements()
     */
    public List getNextElements(int count) throws IteratorException {
        Object     obj = null;
        LinkedList list = new LinkedList();
        if (count < 1 ){
          return list;
        }

        if (this.listIterator != null ) {
            for (int index = 0;
                    this.listIterator.hasNext() && (index < count);
                    index++) {
                obj = listIterator.next();

                list.add(obj);
            }
        } else {
            throw new IteratorException("List not set");
        }

        return list;
    }


    /**
     * Return a collection of value objects that at the beginning
     * of the list.
     *
     * @param count How many objects after the current element.
     *
     * @return List  Collection of value objects that are in the list
     * after the current element.
     *
     * @throws IteratorException If no list are set
     */
    public List getFirstElements(int count) throws IteratorException {
        this.listIterator = this.list.listIterator();
        return getNextElements(count);
    }

    /**
     * Return a collection of value objects that at the end
     * of the list.
     *
     * @param count How many objects after the current element.
     *
     * @return List  Collection of value objects that are in the list
     * after the current element.
     *
     * @throws IteratorException If no list are set
     */
    public List getLastElements(int count) throws IteratorException {

        this.listIterator = this.list.listIterator();
        List list = new ArrayList();
        if (count < 1 ){
          return list;
        }
        while (this.listIterator.hasNext() ) {
          list = getNextElements(count);
        }
        return list;
    }

    /**
     * Reset the index to the start of the list.
     *
     * @throws IteratorException If no list are set
     *
     * @see com.lotoquebec.cardexCommun.business.ValueListIterator#resetIndex()
     */
    public void resetIndex() throws IteratorException {
        if (this.listIterator != null) {
            this.listIterator = this.list.listIterator();
        } else {
            throw new IteratorException("List not set");
        }
    }

    /**
     *
     *
     * @see com.lotoquebec.cardexCommun.business.ValueListIterator#hasPrevious()
     */
    public boolean hasPrevious() {
        if (this.listIterator != null) {
            return this.listIterator.hasPrevious();
        } else {
            return false;
        }
    }

    /**
     *
     *
     * @see com.lotoquebec.cardexCommun.business.ValueListIterator#hasNext()
     */
    public boolean hasNext()  {
        if (this.listIterator != null) {
            return this.listIterator.hasNext();
        } else {
            return false;
        }
    }

    /**
     *
     *
     * @see com.lotoquebec.cardexCommun.business.ValueListIterator#hasNext()
     */
    public long getNextIndex()  {
        if (this.listIterator != null) {
            return this.listIterator.nextIndex();
        } else {
            return 0;
        }
    }

}

