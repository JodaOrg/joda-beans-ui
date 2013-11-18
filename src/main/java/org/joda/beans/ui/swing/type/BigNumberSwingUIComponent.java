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

import java.math.BigDecimal;
import java.math.BigInteger;

import org.joda.beans.Bean;
import org.joda.beans.ui.form.MetaUIComponent;
import org.joda.beans.ui.swing.SwingUIComponent;
import org.joda.beans.ui.swing.SwingUISettings;
import org.joda.beans.ui.swing.component.JValidatedTextField;
import org.joda.beans.ui.swing.component.JValidatedTextFields;

/**
 * An instantiated Swing component for {@code BigDecimal} and {@code BigInteger}.
 */
public class BigNumberSwingUIComponent extends SwingUIComponent<JValidatedTextField> {

    /**
     * Creates an instance.
     * 
     * @param metaComponent  the meta-component, not null
     */
    public BigNumberSwingUIComponent(MetaUIComponent metaComponent) {
        super(metaComponent);
        Class<?> type = metaComponent.getPropertyType();
        JValidatedTextField component;
        if (type == BigDecimal.class) {
            component = JValidatedTextFields.createBigDecimalTextField(
                    metaComponent.isMandatory(),
                    toBigDecimal(metaComponent.getMinValue()),
                    toBigDecimal(metaComponent.getMaxValue()));
        } else if (type == BigInteger.class) {
            component = JValidatedTextFields.createBigIntegerTextField(
                    metaComponent.isMandatory(),
                    toBigInteger(metaComponent.getMinValue()),
                    toBigInteger(metaComponent.getMaxValue()));
        } else {
            throw new IllegalArgumentException("Unknown type: " + type);
        }
        setComponent(component);
    }

    private static BigDecimal toBigDecimal(Comparable<?> value) {
        if (value == null) {
            return null;
        }
        if (value instanceof BigDecimal) {
            return (BigDecimal) value;
        }
        if (value instanceof BigInteger) {
            return new BigDecimal((BigInteger) value);
        }
        if (value instanceof Long) {
            return BigDecimal.valueOf(((Long) value).longValue());
        }
        if (value instanceof Number) {
            return BigDecimal.valueOf(((Number) value).doubleValue());
        }
        throw new IllegalArgumentException("Unknown min/max type for BigDecimal: " + value.getClass().getName());
    }

    private static BigInteger toBigInteger(Comparable<?> value) {
        if (value == null) {
            return null;
        }
        if (value instanceof BigInteger) {
            return (BigInteger) value;
        }
        if (value instanceof BigDecimal) {
            return ((BigDecimal) value).toBigInteger();
        }
        if (value instanceof Double) {
            return BigDecimal.valueOf(((Double) value).doubleValue()).toBigInteger();
        }
        if (value instanceof Number) {
            return BigInteger.valueOf(((Number) value).longValue());
        }
        throw new IllegalArgumentException("Unknown min/max type for BigInteger: " + value.getClass().getName());
    }

    //-------------------------------------------------------------------------
    @Override
    public void updateUI(Bean bean) {
        String text = getMetaProperty().getString(bean, SwingUISettings.INSTANCE.getStringConvert());
        getComponent().setText(text);
    }

}
