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
package org.joda.beans.ui.swing.binder;

import java.util.Collections;
import java.util.List;

import javax.swing.JComponent;

/**
 * Defines how a property is bound to the UI.
 */
public abstract class SingleComponentPropertyBinder extends PropertyBinder {

    /**
     * Creates an instance.
     */
    protected SingleComponentPropertyBinder() {
    }

    //-------------------------------------------------------------------------
    /**
     * Gets the components to display.
     * 
     * @return the components to display, typically one, not null
     */
    public List<JComponent> getComponentList() {
        return Collections.singletonList(getComponent());
    }

    /**
     * Gets the components to display.
     * 
     * @return the component to display, not null
     */
    protected abstract JComponent getComponent();

}
