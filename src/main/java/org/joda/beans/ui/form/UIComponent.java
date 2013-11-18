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

import java.util.Objects;

import org.joda.beans.Bean;
import org.joda.beans.MetaProperty;

/**
 * An instantiated UI component.
 * <p>
 * This stores information about a UI component that is in active use.
 * 
 * @param <T>  the type of the component
 */
public abstract class UIComponent<T> {

    /**
     * The meta-component.
     */
    private final MetaUIComponent metaComponent;

    /**
     * Creates an instance.
     * 
     * @param metaComponent  the meta-component, not null
     */
    protected UIComponent(MetaUIComponent metaComponent) {
        this.metaComponent = Objects.requireNonNull(metaComponent, "metaComponent");
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the meta-component.
     * 
     * @return the meta-component, not null
     */
    public MetaUIComponent getMetaComponent() {
        return metaComponent;
    }

    /**
     * Gets the UI component.
     * 
     * @return the UI component, null if not yet created
     */
    public abstract T getComponent();

    /**
     * Whether the component should be marked as mandatory.
     * <p>
     * This is set by default from the meta-component.
     * However on occasion, users might perceive a component as mandatory
     * even when it is not. For example, a {@code Boolean} exposed as
     * three radio buttons is best indicated to the user as mandatory.
     * 
     * @return true if visibly mandatory
     */
    public boolean isDisplayedAsMandatory() {
        return getMetaComponent().isMandatory();
    }

    //-------------------------------------------------------------------------
    /**
     * Gets the meta-property associated with the meta-component.
     * 
     * @return the meta-property, not null
     */
    public MetaProperty<?> getMetaProperty() {
        return getMetaComponent().getMetaProperty();
    }

    //-------------------------------------------------------------------------
    /**
     * Updates the UI with data from the bean.
     * 
     * @param bean  the bean to update from, not null
     */
    public abstract void updateUI(Bean bean);

    /**
     * Is the UI component currently in a valid state.
     * 
     * @return true if valid, false if in error
     */
    public boolean isValid() {
        return true;
    }

    //-------------------------------------------------------------------------
    @Override
    public String toString() {
        return metaComponent + "::" + (getComponent() != null ? getComponent().getClass().getSimpleName() : "");
    }

}
