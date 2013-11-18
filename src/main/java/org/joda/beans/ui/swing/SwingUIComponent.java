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
package org.joda.beans.ui.swing;

import javax.swing.JComponent;

import org.joda.beans.ui.form.MetaUIComponent;
import org.joda.beans.ui.form.UIComponent;

/**
 * An instantiated Swing UI component.
 * 
 * @param <T>  the type of the component
 */
public abstract class SwingUIComponent<T extends JComponent> extends UIComponent<T> {

    /**
     * The UI component.
     */
    private T component;
    /**
     * Whether the component should be marked as mandatory.
     * This is set by default from the meta-component.
     * However on occasion, users might perceive a component as mandatory
     * even when it is not. For example, a {@code Boolean} exposed as
     * three radio buttons is best indicated to the user as mandatory.
     */
    private boolean displayedAsMandatory;

    /**
     * Creates an instance.
     * 
     * @param metaComponent  the meta-component, not null
     */
    protected SwingUIComponent(MetaUIComponent metaComponent) {
        super(metaComponent);
    }

    //-------------------------------------------------------------------------
    /**
     * Gets the UI component.
     * 
     * @return the UI component, null if not yet created
     */
    public T getComponent() {
        return component;
    }

    /**
     * Sets the UI component.
     * <p>
     * This is typically invoked from the subclass constructor.
     * 
     * @param component  the component that was created, may be null
     */
    protected void setComponent(T component) {
        this.component = component;
    }

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
        return displayedAsMandatory;
    }

    /**
     * Sets whether the component should be marked as mandatory.
     * <p>
     * This is set by default from the meta-component.
     * However on occasion, users might perceive a component as mandatory
     * even when it is not. For example, a {@code Boolean} exposed as
     * three radio buttons is best indicated to the user as mandatory.
     * 
     * @param displayedAsMandatory  true to be visibly mandatory
     */
    protected void setDisplayedAsMandatory(boolean displayedAsMandatory) {
        this.displayedAsMandatory = displayedAsMandatory;
    }

}
