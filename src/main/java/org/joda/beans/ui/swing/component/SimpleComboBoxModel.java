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

import java.util.List;
import java.util.Objects;

import javax.swing.MutableComboBoxModel;

/**
 * A combobox model based on an {@code ArrayList}.
 * 
 * @param <T>  the type of the data being displayed
 */
public class SimpleComboBoxModel<T> extends SimpleListModel<T> implements MutableComboBoxModel<T> {

    /** Serialization version. */
    private static final long serialVersionUID = 1L;

    /**
     * The selected item.
     */
    private Object selected;

    /**
     * Creates an instance.
     */
    public SimpleComboBoxModel() {
        super();
    }

    /**
     * Creates an instance with list data.
     * 
     * @param listData  the list data, not null
     */
    public SimpleComboBoxModel(List<T> listData) {
        super(listData);
    }

    //-------------------------------------------------------------------------
    @Override
    public Object getSelectedItem() {
        return selected;
    }

    @Override
    public void setSelectedItem(Object item) {
        if (Objects.equals(selected, item) == false) {
            selected = item;
            fireContentsChanged(this, -1, -1);
        }
    }

    //-------------------------------------------------------------------------
    @Override
    public void addElement(T item) {
        super.add(item);
    }

    @Override
    public void insertElementAt(T item, int index) {
        super.add(index, item);
    }

    @Override
    public void removeElement(Object item) {
        super.remove(item);
    }

    @Override
    public void removeElementAt(int index) {
        super.remove(index);
    }

}
