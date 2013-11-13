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
import org.joda.beans.Property;
import org.joda.beans.ui.swing.component.JComponentUtils;
import org.joda.convert.StringConvert;

/**
 * Binder for numeric types.
 */
public class NumericPropertyBinders {

    /**
     * Factory for {@code byte}.
     */
    public static final PropertyBinderFactory BYTE_PRIMITIVE = new IntegralPropertyBinderFactory(Byte.TYPE, Byte.MIN_VALUE, Byte.MAX_VALUE);
    /**
     * Factory for {@code Byte}.
     */
    public static final PropertyBinderFactory BYTE_OBJECT = new IntegralPropertyBinderFactory(Byte.class, Byte.MIN_VALUE, Byte.MAX_VALUE);
    /**
     * Factory for {@code short}.
     */
    public static final PropertyBinderFactory SHORT_PRIMITIVE = new IntegralPropertyBinderFactory(Short.TYPE, Short.MIN_VALUE, Short.MAX_VALUE);
    /**
     * Factory for {@code Short}.
     */
    public static final PropertyBinderFactory SHORT_OBJECT = new IntegralPropertyBinderFactory(Short.class, Short.MIN_VALUE, Short.MAX_VALUE);
    /**
     * Factory for {@code int}.
     */
    public static final PropertyBinderFactory INT_PRIMITIVE = new IntegralPropertyBinderFactory(Integer.TYPE, Integer.MIN_VALUE, Integer.MAX_VALUE);
    /**
     * Factory for {@code Integer}.
     */
    public static final PropertyBinderFactory INT_OBJECT = new IntegralPropertyBinderFactory(Integer.class, Integer.MIN_VALUE, Integer.MAX_VALUE);
    /**
     * Factory for {@code long}.
     */
    public static final PropertyBinderFactory LONG_PRIMITIVE = new IntegralPropertyBinderFactory(Long.TYPE, Long.MIN_VALUE, Long.MAX_VALUE);
    /**
     * Factory for {@code Long}.
     */
    public static final PropertyBinderFactory LONG_OBJECT = new IntegralPropertyBinderFactory(Long.class, Long.MIN_VALUE, Long.MAX_VALUE);
    /**
     * Factory for {@code double}.
     */
    public static final PropertyBinderFactory DOUBLE_PRIMITIVE = new FloatingPropertyBinderFactory(Double.TYPE, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    /**
     * Factory for {@code Double}.
     */
    public static final PropertyBinderFactory DOUBLE_OBJECT = new FloatingPropertyBinderFactory(Double.class, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);

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
         * The min.
         */
        private final long minInclusive;
        /**
         * The max.
         */
        private final long maxInclusive;

        /**
         * Creates an instance.
         * 
         * @param type  the type to validate, not null
         * @param minInclusive  the minimum value, inclusive
         * @param maxInclusive  the minimum value, inclusive
         */
        IntegralPropertyBinderFactory(Class<?> type, long minInclusive, long maxInclusive) {
            super();
            this.type = type;
            this.minInclusive = minInclusive;
            this.maxInclusive = maxInclusive;
        }

        //-------------------------------------------------------------------------
        @Override
        public PropertyBinder createBinder(Bean bean, MetaProperty<?> metaProperty) {
            if (metaProperty.propertyType() == type) {
                return new IntegralPropertyBinder(metaProperty.createProperty(bean), type, minInclusive, maxInclusive);
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
        private final JTextField component;
        /**
         * The associated property.
         */
        private final Property<?> property;
        /**
         * The type.
         */
        private final Class<?> type;

        /**
         * Creates an instance.
         * 
         * @param property  the property to bind, not null
         * @param type  the type to validate, not null
         * @param minInclusive  the minimum value, inclusive
         * @param maxInclusive  the minimum value, inclusive
         */
        public IntegralPropertyBinder(Property<?> property, Class<?> type, long minInclusive, long maxInclusive) {
            super();
            this.property = Objects.requireNonNull(property, "property");
            this.type = type;
            this.component = JComponentUtils.createLongTextField(type.isPrimitive() == false, minInclusive, maxInclusive);
        }

        //-------------------------------------------------------------------------
        @Override
        protected JComponent getComponent() {
            return component;
        }

        @Override
        public void updateUI() {
            component.setText(property.get().toString());
        }

        @Override
        public void updateProperty() {
            String value = component.getText();
            if (value == null) { // should not happen
                value = "0";
            }
            property.set(StringConvert.INSTANCE.convertFromString(type, value));
        }

        //-------------------------------------------------------------------------
        @Override
        public String toString() {
            return property.toString() + "::" + component.getClass().getSimpleName();
        }

    }

    //-------------------------------------------------------------------------
    /**
     * Factory for a floating type.
     */
    static class FloatingPropertyBinderFactory implements PropertyBinderFactory {
        
        /**
         * The type.
         */
        private final Class<?> type;
        /**
         * The min.
         */
        private final double minInclusive;
        /**
         * The max.
         */
        private final double maxInclusive;

        /**
         * Creates an instance.
         * 
         * @param type  the type to validate, not null
         * @param minInclusive  the minimum value, inclusive
         * @param maxInclusive  the minimum value, inclusive
         */
        FloatingPropertyBinderFactory(Class<?> type, double minInclusive, double maxInclusive) {
            super();
            this.type = type;
            this.minInclusive = minInclusive;
            this.maxInclusive = maxInclusive;
        }

        //-------------------------------------------------------------------------
        @Override
        public PropertyBinder createBinder(Bean bean, MetaProperty<?> metaProperty) {
            if (metaProperty.propertyType() == type) {
                return new FloatingPropertyBinder(metaProperty.createProperty(bean), type, minInclusive, maxInclusive);
            }
            return null;
        }
    }

    //-------------------------------------------------------------------------
    /**
     * Binder for a floating type.
     */
    static class FloatingPropertyBinder extends SingleComponentPropertyBinder {

        /**
         * The associated component.
         */
        private final JTextField component;
        /**
         * The associated property.
         */
        private final Property<?> property;
        /**
         * The type.
         */
        private final Class<?> type;

        /**
         * Creates an instance.
         * 
         * @param property  the property to bind, not null
         * @param type  the type to validate, not null
         * @param minInclusive  the minimum value, inclusive
         * @param maxInclusive  the minimum value, inclusive
         */
        public FloatingPropertyBinder(Property<?> property, Class<?> type, double minInclusive, double maxInclusive) {
            super();
            this.property = Objects.requireNonNull(property, "property");
            this.type = type;
            this.component = JComponentUtils.createDoubleTextField(type.isPrimitive() == false, true, minInclusive, maxInclusive);
        }

        //-------------------------------------------------------------------------
        @Override
        protected JComponent getComponent() {
            return component;
        }

        @Override
        public void updateUI() {
            component.setText(property.get().toString());
        }

        @Override
        public void updateProperty() {
            String value = component.getText();
            if (value == null) { // should not happen
                value = "0";
            }
            property.set(StringConvert.INSTANCE.convertFromString(type, value));
        }

        //-------------------------------------------------------------------------
        @Override
        public String toString() {
            return property.toString() + "::" + component.getClass().getSimpleName();
        }

    }

}
