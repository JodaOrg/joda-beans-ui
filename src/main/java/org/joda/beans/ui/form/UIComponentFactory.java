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

/**
 * A factory allowing the real UI component to be created.
 * For example, this might return a Swing-based factory.
 */
public abstract class UIComponentFactory {

    /**
     * Creates an instance.
     */
    protected UIComponentFactory() {
    }

    //-------------------------------------------------------------------------
    /**
     * Creates the UI component.
     * 
     * @param <T>  the UI component type
     * @param metaComponent  the meta-component, not null
     * @return the UI component, not null
     */
    public abstract <T> UIComponent<T> createComponent(MetaUIComponent metaComponent);

}
