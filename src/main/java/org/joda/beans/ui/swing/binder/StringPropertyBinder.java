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

import javax.swing.JComponent;
import javax.swing.JTextField;

import org.joda.beans.Bean;
import org.joda.beans.ui.form.MetaUIComponent;
import org.joda.beans.ui.swing.SwingUISettings;
import org.joda.beans.ui.swing.component.JValidatedTextFields;
import org.joda.beans.ui.swing.component.TextUndoManager;
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
        public PropertyBinder createBinder(MetaUIComponent metaComponent) {
            StringConvert convert = SwingUISettings.INSTANCE.getStringConvert();
            if (convert.isConvertible(metaComponent.getPropertyType())) {
                return new StringPropertyBinder(metaComponent);
            }
            return null;
        }
    };

    /**
     * The associated component.
     */
    private final JTextField component;

    /**
     * Creates an instance.
     * 
     * @param metaComponent  the component to bind, not null
     */
    public StringPropertyBinder(MetaUIComponent metaComponent) {
        super(metaComponent);
        this.component = JValidatedTextFields.createStringTextField(metaComponent.isMandatory(), metaComponent.getMaxSize());
        TextUndoManager.applyTo(component);
    }

    //-------------------------------------------------------------------------
    @Override
    protected JComponent getComponent() {
        return component;
    }

    @Override
    public void updateUI(Bean bean) {
        StringConvert converter = SwingUISettings.INSTANCE.getStringConvert();
        component.setText(getMetaProperty().getString(bean, converter));
    }

    @Override
    public void updateProperty(Bean bean) {
        StringConvert converter = SwingUISettings.INSTANCE.getStringConvert();
        getMetaProperty().setString(bean, component.getText(), converter);
    }

}
