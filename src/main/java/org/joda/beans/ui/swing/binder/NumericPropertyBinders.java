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

import org.joda.beans.Bean;
import org.joda.beans.ui.form.MetaUIComponent;
import org.joda.beans.ui.swing.SwingUISettings;
import org.joda.beans.ui.swing.component.JValidatedTextField;
import org.joda.beans.ui.swing.component.JValidatedTextFields;

/**
 * Binder for numeric types.
 */
public class NumericPropertyBinders {

    /**
     * Factory for {@code byte}.
     */
    public static final PropertyBinderFactory BYTE_PRIMITIVE = new IntegralPropertyBinderFactory(Byte.TYPE);
    /**
     * Factory for {@code Byte}.
     */
    public static final PropertyBinderFactory BYTE_OBJECT = new IntegralPropertyBinderFactory(Byte.class);
    /**
     * Factory for {@code short}.
     */
    public static final PropertyBinderFactory SHORT_PRIMITIVE = new IntegralPropertyBinderFactory(Short.TYPE);
    /**
     * Factory for {@code Short}.
     */
    public static final PropertyBinderFactory SHORT_OBJECT = new IntegralPropertyBinderFactory(Short.class);
    /**
     * Factory for {@code int}.
     */
    public static final PropertyBinderFactory INT_PRIMITIVE = new IntegralPropertyBinderFactory(Integer.TYPE);
    /**
     * Factory for {@code Integer}.
     */
    public static final PropertyBinderFactory INT_OBJECT = new IntegralPropertyBinderFactory(Integer.class);
    /**
     * Factory for {@code long}.
     */
    public static final PropertyBinderFactory LONG_PRIMITIVE = new IntegralPropertyBinderFactory(Long.TYPE);
    /**
     * Factory for {@code Long}.
     */
    public static final PropertyBinderFactory LONG_OBJECT = new IntegralPropertyBinderFactory(Long.class);
    /**
     * Factory for {@code double}.
     */
    public static final PropertyBinderFactory DOUBLE_PRIMITIVE = new DoublePropertyBinderFactory(Double.TYPE);
    /**
     * Factory for {@code Double}.
     */
    public static final PropertyBinderFactory DOUBLE_OBJECT = new DoublePropertyBinderFactory(Double.class);

    //-------------------------------------------------------------------------
    /**
     * Factory for an integral type.
     */
    static class IntegralPropertyBinderFactory implements PropertyBinderFactory {
        /**
         * The type.
         */
        private final Class<?> type;

        /**
         * Creates an instance.
         * 
         * @param type  the type to validate, not null
         */
        IntegralPropertyBinderFactory(Class<?> type) {
            this.type = type;
        }

        //-------------------------------------------------------------------------
        @Override
        public PropertyBinder createBinder(MetaUIComponent metaComponent) {
            if (metaComponent.getPropertyType() == type) {
                return new IntegralPropertyBinder(metaComponent);
            }
            return null;
        }
    }

    //-------------------------------------------------------------------------
    /**
     * Binder for an integral type.
     */
    static class IntegralPropertyBinder extends SingleComponentPropertyBinder {
        /**
         * The associated component.
         */
        private final JValidatedTextField component;

        /**
         * Creates an instance.
         * 
         * @param property  the property to bind, not null
         */
        public IntegralPropertyBinder(MetaUIComponent metaComponent) {
            super(metaComponent);
            Class<?> type = metaComponent.getPropertyType();
            if (type == Long.class || type == Long.TYPE) {
                this.component = JValidatedTextFields.createLongTextField(
                        metaComponent.isMandatory(),
                        metaComponent.getMinValue(Long.MIN_VALUE),
                        metaComponent.getMaxValue(Long.MAX_VALUE));
            } else if (type == Integer.class || type == Integer.TYPE) {
                this.component = JValidatedTextFields.createIntegerTextField(
                        metaComponent.isMandatory(),
                        metaComponent.getMinValue(Integer.MIN_VALUE),
                        metaComponent.getMaxValue(Integer.MAX_VALUE));
            } else if (type == Short.class || type == Short.TYPE) {
                this.component = JValidatedTextFields.createShortTextField(
                        metaComponent.isMandatory(),
                        metaComponent.getMinValue(Short.MIN_VALUE),
                        metaComponent.getMaxValue(Short.MAX_VALUE));
            } else if (type == Byte.class || type == Byte.TYPE) {
                this.component = JValidatedTextFields.createByteTextField(
                        metaComponent.isMandatory(),
                        metaComponent.getMinValue(Byte.MIN_VALUE),
                        metaComponent.getMaxValue(Byte.MAX_VALUE));
            } else {
                throw new IllegalArgumentException();
            }
        }

        //-------------------------------------------------------------------------
        @Override
        protected JComponent getComponent() {
            return component;
        }

        @Override
        public void updateUI(Bean bean) {
            String text = getMetaProperty().getString(bean, SwingUISettings.INSTANCE.getStringConvert());
            component.setText(text);
        }

        @Override
        public void updateProperty(Bean bean) {
            String text = component.getText();
            if (text == null) { // should not happen
                text = "0";
            }
            getMetaProperty().setString(bean, text, SwingUISettings.INSTANCE.getStringConvert());
        }
    }

    //-------------------------------------------------------------------------
    /**
     * Factory for a {@code Double} type.
     */
    static class DoublePropertyBinderFactory implements PropertyBinderFactory {
        /**
         * The type.
         */
        private final Class<?> type;

        /**
         * Creates an instance.
         * 
         * @param type  the type to validate, not null
         * @param minInclusive  the minimum value, inclusive
         * @param maxInclusive  the minimum value, inclusive
         */
        DoublePropertyBinderFactory(Class<?> type) {
            this.type = type;
        }

        //-------------------------------------------------------------------------
        @Override
        public PropertyBinder createBinder(MetaUIComponent metaComponent) {
            if (metaComponent.getPropertyType() == type) {
                return new DoublePropertyBinder(metaComponent);
            }
            return null;
        }
    }

    //-------------------------------------------------------------------------
    /**
     * Binder for a {@code Double} type.
     */
    static class DoublePropertyBinder extends SingleComponentPropertyBinder {

        /**
         * The associated component.
         */
        private final JValidatedTextField component;

        /**
         * Creates an instance.
         * 
         * @param property  the property to bind, not null
         * @param minInclusive  the minimum value, inclusive
         * @param maxInclusive  the minimum value, inclusive
         */
        public DoublePropertyBinder(MetaUIComponent metaComponent) {
            super(metaComponent);
            this.component = JValidatedTextFields.createDoubleTextField(
                    metaComponent.isMandatory(), true,
                    metaComponent.getMinValue(Double.NEGATIVE_INFINITY),
                    metaComponent.getMaxValue(Double.POSITIVE_INFINITY));
        }

        //-------------------------------------------------------------------------
        @Override
        protected JComponent getComponent() {
            return component;
        }

        @Override
        public void updateUI(Bean bean) {
            String text = getMetaProperty().getString(bean, SwingUISettings.INSTANCE.getStringConvert());
            component.setText(text);
        }

        @Override
        public void updateProperty(Bean bean) {
            String text = component.getText();
            if (text == null) { // should not happen
                text = "0";
            }
            getMetaProperty().setString(bean, text, SwingUISettings.INSTANCE.getStringConvert());
        }
    }

}
