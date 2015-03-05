/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardexCommun.business;

import java.util.List;

import com.lotoquebec.cardexCommun.exception.IteratorException;

/**
 * This interface provide iteration facility
 * on object list.
 *
 * @author $Author: mlibersan $
 * @version $Revison: $, $Date: 2002/03/18 21:46:00 $
 */
public interface ValueListIterator {

    /**
     * Return the size of the list.
     *
     * @return int The size of the result set.
     *
     * @throws IteratorException If no list are set
     */
    public int getSize() throws IteratorException;

    /**
     * Return the current value object from the list.
     *
     * @return Object the current value object from the list.
     *
     * @throws IteratorException If no list are set
     */
    public Object getCurrentElement() throws IteratorException;

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
     */
    public List getPreviousElements(int count)
            throws IteratorException;

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
     */
    public List getNextElements(int count) throws IteratorException;

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
    public List getFirstElements(int count) throws IteratorException;

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
    public List getLastElements(int count) throws IteratorException;

    /**
     * Reset the index to the start of the list.
     *
     * @throws IteratorException If no list are set
     */
    public void resetIndex() throws IteratorException;

    /**
     *
     *
     * @see com.lotoquebec.cardexCommun.business.ValueListIterator#hasPrevious()
     */
    public boolean hasPrevious();

    /**
     *
     *
     * @see com.lotoquebec.cardexCommun.business.ValueListIterator#hasNext()
     */
    public boolean hasNext();

    /**
     *
     *
     * @see com.lotoquebec.cardexCommun.business.ValueListIterator#hasNext()
     */
    public long getNextIndex();

}

