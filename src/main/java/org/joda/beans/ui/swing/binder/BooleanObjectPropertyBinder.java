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
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import javax.swing.JComponent;

import org.joda.beans.Bean;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.ui.swing.component.JRadioButtonPanel;

/**
 * Binder for a {@code Boolean}.
 */
public class BooleanObjectPropertyBinder
        extends SingleComponentPropertyBinder {

    /**
     * The factory.
     */
    public static final PropertyBinderFactory FACTORY = new PropertyBinderFactory() {
        @Override
        public PropertyBinder createBinder(Bean bean, MetaProperty<?> metaProperty) {
            if (metaProperty.propertyType() == Boolean.class) {
                return new BooleanObjectPropertyBinder(metaProperty.createProperty(bean));
            }
            return null;
        }
    };
    /**
     * The radio button map.
     */
    private static final Map<Boolean, String> TRUE_FALSE;
    static {
        Map<Boolean, String> map = new LinkedHashMap<>();
        map.put(Boolean.TRUE, BinderMsg.lookup("Boolean.true"));
        map.put(Boolean.FALSE, BinderMsg.lookup("Boolean.false"));
        map.put(null, BinderMsg.lookup("Boolean.null"));
        TRUE_FALSE = Collections.unmodifiableMap(map);
    }

    /**
     * The associated component.
     */
    private final JRadioButtonPanel<Boolean> component;
    /**
     * The associated property.
     */
    private final Property<?> property;

    /**
     * Creates an instance.
     * 
     * @param property  the property to bind, not null
     */
    public BooleanObjectPropertyBinder(Property<?> property) {
        super();
        this.property = Objects.requireNonNull(property, "property");
        this.component = new JRadioButtonPanel<Boolean>(TRUE_FALSE);
    }

    //-------------------------------------------------------------------------
    @Override
    protected JComponent getComponent() {
        return component;
    }

    @Override
    public void updateUI() {
        component.setSelection((Boolean) property.get());
    }

    @Override
    public void updateProperty() {
        property.set(component.getSelection());
    }

    //-------------------------------------------------------------------------
    @Override
    public String toString() {
        return property.toString() + "::" + component.getClass().getSimpleName();
    }

}
