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
package org.joda.beans.ui.swing.type;

import org.joda.beans.Bean;
import org.joda.beans.ui.form.MetaUIComponent;
import org.joda.beans.ui.swing.SwingUIComponent;
import org.joda.beans.ui.swing.SwingUISettings;
import org.joda.beans.ui.swing.component.JValidatedTextField;
import org.joda.beans.ui.swing.component.JValidatedTextFields;

/**
 * An instantiated Swing component for an integral number.
 */
public class IntegralSwingUIComponent extends SwingUIComponent<JValidatedTextField> {

    /**
     * Creates an instance.
     * 
     * @param metaComponent  the meta-component, not null
     */
    public IntegralSwingUIComponent(MetaUIComponent metaComponent) {
        super(metaComponent);
        Class<?> type = metaComponent.getPropertyType();
        JValidatedTextField component;
        if (type == Long.class || type == Long.TYPE) {
            component = JValidatedTextFields.createLongTextField(
                    metaComponent.isMandatory(),
                    metaComponent.getMinValue(Long.MIN_VALUE).longValue(),
                    metaComponent.getMaxValue(Long.MAX_VALUE).longValue());
        } else if (type == Integer.class || type == Integer.TYPE) {
            component = JValidatedTextFields.createIntegerTextField(
                    metaComponent.isMandatory(),
                    metaComponent.getMinValue(Integer.MIN_VALUE).intValue(),
                    metaComponent.getMaxValue(Integer.MAX_VALUE).intValue());
        } else if (type == Short.class || type == Short.TYPE) {
            component = JValidatedTextFields.createShortTextField(
                    metaComponent.isMandatory(),
                    metaComponent.getMinValue(Short.MIN_VALUE).shortValue(),
                    metaComponent.getMaxValue(Short.MAX_VALUE).shortValue());
        } else if (type == Byte.class || type == Byte.TYPE) {
            component = JValidatedTextFields.createByteTextField(
                    metaComponent.isMandatory(),
                    metaComponent.getMinValue(Byte.MIN_VALUE).byteValue(),
                    metaComponent.getMaxValue(Byte.MAX_VALUE).byteValue());
        } else {
            throw new IllegalArgumentException("Unknown type: " + type);
        }
        setComponent(component);
    }

    //-------------------------------------------------------------------------
    @Override
    public void updateUI(Bean bean) {
        String text = getMetaProperty().getString(bean, SwingUISettings.INSTANCE.getStringConvert());
        getComponent().setText(text);
    }

}
