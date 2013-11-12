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

import java.util.Objects;

import javax.swing.JComponent;
import javax.swing.JTextField;

import org.joda.beans.Bean;
import org.joda.beans.MetaProperty;
import org.joda.beans.ui.swing.SwingUISettings;
import org.joda.convert.StringConvert;

/**
 * Binder for a {@code String} or anything convertible to a string.
 */
public class StringPropertyBinder
        extends SingleComponentPropertyBinder {

    /**
     * The factory.
     */
    public static final PropertyBinderFactory FACTORY = new PropertyBinderFactory() {
        @Override
        public PropertyBinder createBinder(Bean bean, MetaProperty<?> metaProperty) {
            StringConvert convert = SwingUISettings.INSTANCE.getStringConvert();
            if (convert.isConvertible(metaProperty.propertyType())) {
                return new StringPropertyBinder(bean, metaProperty);
            }
            return null;
        }
    };

    /**
     * The associated component.
     */
    private final JTextField component;
    /**
     * The associated bean.
     */
    private final Bean bean;
    /**
     * The associated property.
     */
    private final MetaProperty<?> metaProperty;

    /**
     * Creates an instance.
     * 
     * @param bean  the bean, not null
     * @param metaProperty  the property to bind, not null
     */
    public StringPropertyBinder(Bean bean, MetaProperty<?> metaProperty) {
        super();
        this.bean = Objects.requireNonNull(bean, "bean");
        this.metaProperty = Objects.requireNonNull(metaProperty, "metaProperty");
        this.component = new JTextField();
    }

    //-------------------------------------------------------------------------
    @Override
    protected JComponent getComponent() {
        return component;
    }

    @Override
    public void updateUI() {
        StringConvert converter = SwingUISettings.INSTANCE.getStringConvert();
        component.setText(metaProperty.getString(bean, converter));
    }

    @Override
    public void updateProperty() {
        StringConvert converter = SwingUISettings.INSTANCE.getStringConvert();
        metaProperty.setString(bean, component.getText(), converter);
    }

    //-------------------------------------------------------------------------
    @Override
    public String toString() {
        return metaProperty.toString() + "::" + component.getClass().getSimpleName();
    }

}
