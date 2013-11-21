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
package org.joda.beans.ui.form;

import java.util.List;
import java.util.Objects;

import org.joda.beans.Bean;

/**
 * An instantiated UI component.
 * <p>
 * This stores information about a UI component that is in active use.
 * 
 * @param <T>  the type of the component
 */
public abstract class UIForm<T> {

    /**
     * The bean that the form is for.
     */
    private MetaUIForm metaForm;

    /**
     * Creates an instance.
     */
    protected UIForm() {
    }

    /**
     * Creates an instance.
     * 
     * @param metaForm  the meta-form, not null
     */
    public UIForm(MetaUIForm metaForm) {
        this.metaForm = Objects.requireNonNull(metaForm, "metaForm");
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the meta-form.
     * 
     * @return the meta-form, not null
     */
    public MetaUIForm getMetaForm() {
        return metaForm;
    }

    /**
     * Gets the UI form.
     * 
     * @return the UI form, not null
     */
    public abstract T getForm();

    /**
     * Gets the instantiated components.
     * 
     * @return the UI components, editable, not null
     */
    public abstract List<? extends UIComponent<?>> getComponents();

    //-------------------------------------------------------------------------
    /**
     * Updates the UI with data from the bean.
     * 
     * @param bean  the bean to update from, not null
     */
    public void updateUI(Bean bean) {
        for (UIComponent<?> comp : getComponents()) {
            comp.updateUI(bean);
        }
    }

    /**
     * Is the UI component currently in a valid state.
     * 
     * @return true if valid, false if in error
     */
    public boolean isValid() {
        for (UIComponent<?> comp : getComponents()) {
            if (comp.isValid() == false) {
                return false;
            }
        }
        return true;
    }

    //-------------------------------------------------------------------------
    @Override
    public String toString() {
        return metaForm + "::" + (getForm() != null ? getForm().getClass().getSimpleName() : "");
    }

}
