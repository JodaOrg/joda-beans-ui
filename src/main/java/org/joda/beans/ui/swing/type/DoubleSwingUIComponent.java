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
 * An instantiated Swing component for a {@code Double}.
 */
public class DoubleSwingUIComponent extends SwingUIComponent<JValidatedTextField> {

    /**
     * Meta-component additional field.
     * True to allow NaN, false to disallow NaN.
     */
    private static final String ALLOW_NAN = "allowNaN";

    /**
     * Creates an instance.
     * 
     * @param metaComponent  the meta-component, not null
     */
    public DoubleSwingUIComponent(MetaUIComponent metaComponent) {
        super(metaComponent);
        Class<?> type = metaComponent.getPropertyType();
        JValidatedTextField component;
        if (type == Double.class || type == Double.TYPE) {
            Boolean allowNan = (Boolean) metaComponent.getAdditional().get(ALLOW_NAN);
            component = JValidatedTextFields.createDoubleTextField(
                    metaComponent.isMandatory(), allowNan != null && allowNan.booleanValue(),
                    metaComponent.getMinValue(Double.NEGATIVE_INFINITY).doubleValue(),
                    metaComponent.getMaxValue(Double.POSITIVE_INFINITY).doubleValue());
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
