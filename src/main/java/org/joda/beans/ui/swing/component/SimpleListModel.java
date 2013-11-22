/*
 *  Copyright 2001-2013 Stephen Colebourne
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.joda.beans.ui.swing.component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.swing.AbstractListModel;

/**
 * A list model based on an {@code ArrayList}.
 * 
 * @param <T>  the type of the data being displayed
 */
public class SimpleListModel<T> extends AbstractListModel<T> implements List<T> {

    /** Serialization version. */
    private static final long serialVersionUID = 1L;

    /**
     * The list.
     */
    private final List<T> listData;

    /**
     * Creates an instance.
     */
    public SimpleListModel() {
        listData = new ArrayList<>();
    }

    /**
     * Creates an instance with list data.
     * 
     * @param listData  the list data, not null
     */
    public SimpleListModel(List<T> listData) {
        this.listData = new ArrayList<>(Objects.requireNonNull(listData, "listData"));
    }

    //-------------------------------------------------------------------------
    /**
     * Gets the raw list data.
     * 
     * @return the raw list data, not null
     */
    protected List<T> getListData() {
        return listData;
    }

    //-------------------------------------------------------------------------
    @Override
    public int getSize() {
        return listData.size();
    }

    @Override
    public T getElementAt(int index) {
        return listData.get(index);
    }

    //-------------------------------------------------------------------------
    @Override
    public int size() {
        return listData.size();
    }

    @Override
    public boolean isEmpty() {
        return listData.isEmpty();
    }

    @Override
    public T get(int index) {
        return listData.get(index);
    }

    @Override
    public int indexOf(Object obj) {
        return listData.indexOf(obj);
    }

    @Override
    public int lastIndexOf(Object obj) {
        return listData.lastIndexOf(obj);
    }

    @Override
    public boolean contains(Object obj) {
        return listData.contains(obj);
    }

    @Override
    public boolean containsAll(Collection<?> coll) {
        return listData.containsAll(coll);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return Collections.unmodifiableList(listData.subList(fromIndex, toIndex));  // should be modifiable
    }

    @Override
    public Object[] toArray() {
        return listData.toArray();
    }

    @Override
    public <R> R[] toArray(R[] array) {
        return listData.toArray(array);
    }

    //-------------------------------------------------------------------------
    @Override
    public Iterator<T> iterator() {
        return listIterator();
    }

    @Override
    public ListIterator<T> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(final int index) {
        return new ListIterator<T>() {
            private int pos = index;
            private int last = -1;
            @Override
            public boolean hasNext() {
                return pos < listData.size();
            }
            @Override
            public boolean hasPrevious() {
                return pos > 0;
            }
            @Override
            public T next() {
                if (hasNext() == false) {
                    throw new NoSuchElementException();
                }
                T next = listData.get(pos);
                last = index;
                pos++;
                return next;
            }
            @Override
            public T previous() {
                if (hasPrevious() == false) {
                    throw new NoSuchElementException();
                }
                pos--;
                T next = listData.get(pos);
                last = index;
                return next;
            }
            @Override
            public int nextIndex() {
                return pos;
            }
            @Override
            public int previousIndex() {
                return pos - 1;
            }
            @Override
            public void remove() {
                if (last < 0) {
                    throw new IllegalStateException("Unable to remove");
                }
                SimpleListModel.this.remove(last);
                if (last < pos) {
                    pos--;
                }
                last = -1;
            }
            @Override
            public void set(T item) {
                if (last < 0) {
                    throw new IllegalStateException("Unable to set");
                }
                SimpleListModel.this.set(last, item);
            }
            @Override
            public void add(T item) {
                SimpleListModel.this.add(pos, item);
                last = -1;
                pos++;
            }
        };
    }

    //-------------------------------------------------------------------------
    @Override
    public boolean add(T item) {
        add(listData.size(), item);
        return true;
    }

    @Override
    public void add(int index, T item) {
        listData.add(index, item);
        fireIntervalAdded(this, index, index);
    }

    @Override
    public T set(int index, T item) {
        T result = listData.set(index, item);
        fireContentsChanged(this, index, index);
        return result;
    }

    @Override
    public boolean addAll(Collection<? extends T> coll) {
        return addAll(listData.size(), coll);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> coll) {
        if (coll.size() > 0) {
            listData.addAll(index, coll);
            fireIntervalAdded(this, index, index + coll.size());
            return true;
        }
        return false;
    }

    @Override
    public T remove(int index) {
        T result = listData.remove(index);
        fireIntervalRemoved(this, index, index);
        return result;
    }

    @Override
    public boolean remove(Object item) {
        int index = indexOf(item);
        if (index >= 0) {
            remove(index);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> coll) {
        boolean changed = listData.removeAll(coll);
        if (changed) {
            fireContentsChanged(this, 0, getSize() - 1);
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> coll) {
        boolean changed = listData.retainAll(coll);
        if (changed) {
            fireContentsChanged(this, 0, getSize() - 1);
        }
        return changed;
    }

    @Override
    public void clear() {
        if (listData.size() > 0) {
            int oldSize = listData.size();
            listData.clear();
            fireIntervalRemoved(this, 0, oldSize - 1);
        }
    }

    //-------------------------------------------------------------------------
    @Override
    public boolean equals(Object obj) {
        return listData.equals(obj);
    }

    @Override
    public int hashCode() {
        return listData.hashCode();
    }

    @Override
    public String toString() {
        return "SimpleListModel" + listData;
    }

}
