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
package org.joda.beans.ui.swing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;
import org.joda.beans.ui.form.UISettings;
import org.joda.beans.ui.swing.binder.BooleanObjectPropertyBinder;
import org.joda.beans.ui.swing.binder.BooleanPrimitivePropertyBinder;
import org.joda.beans.ui.swing.binder.NumericPropertyBinders;
import org.joda.beans.ui.swing.binder.PropertyBinderFactory;
import org.joda.beans.ui.swing.binder.StringPropertyBinder;

/**
 * Settings for the Swing UI.
 */
@BeanDefinition(style = "minimal")
public class SwingUISettings extends UISettings {

    /**
     * The singleton settings.
     */
    public static final SwingUISettings INSTANCE = new SwingUISettings();

    /**
     * The list of factories, which can be edited.
     */
    @PropertyDefinition(validate = "notNull", set = "private")
    private final List<PropertyBinderFactory> factories = new ArrayList<>();

    //-------------------------------------------------------------------------
    /**
     * Creates an instance.
     */
    public SwingUISettings() {
        factories.add(BooleanPrimitivePropertyBinder.FACTORY);
        factories.add(BooleanObjectPropertyBinder.FACTORY);
        factories.add(NumericPropertyBinders.BYTE_PRIMITIVE);
        factories.add(NumericPropertyBinders.BYTE_OBJECT);
        factories.add(NumericPropertyBinders.SHORT_PRIMITIVE);
        factories.add(NumericPropertyBinders.SHORT_OBJECT);
        factories.add(NumericPropertyBinders.INT_PRIMITIVE);
        factories.add(NumericPropertyBinders.INT_OBJECT);
        factories.add(NumericPropertyBinders.LONG_PRIMITIVE);
        factories.add(NumericPropertyBinders.LONG_OBJECT);
        factories.add(NumericPropertyBinders.DOUBLE_PRIMITIVE);
        factories.add(NumericPropertyBinders.DOUBLE_OBJECT);
        factories.add(StringPropertyBinder.FACTORY);
    }

    //------------------------- AUTOGENERATED START -------------------------
    ///CLOVER:OFF
    /**
     * The meta-bean for {@code SwingUISettings}.
     * @return the meta-bean, not null
     */
    public static SwingUISettings.Meta meta() {
        return SwingUISettings.Meta.INSTANCE;
    }

    static {
        JodaBeanUtils.registerMetaBean(SwingUISettings.Meta.INSTANCE);
    }

    @Override
    public SwingUISettings.Meta metaBean() {
        return SwingUISettings.Meta.INSTANCE;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the list of factories, which can be edited.
     * @return the value of the property, not null
     */
    public List<PropertyBinderFactory> getFactories() {
        return factories;
    }

    /**
     * Sets the list of factories, which can be edited.
     * @param factories  the new value of the property
     */
    private void setFactories(List<PropertyBinderFactory> factories) {
        this.factories.clear();
        this.factories.addAll(factories);
    }

    //-----------------------------------------------------------------------
    @Override
    public SwingUISettings clone() {
        return (SwingUISettings) super.clone();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == this.getClass()) {
            SwingUISettings other = (SwingUISettings) obj;
            return JodaBeanUtils.equal(getFactories(), other.getFactories()) &&
                    super.equals(obj);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash += hash * 31 + JodaBeanUtils.hashCode(getFactories());
        return hash ^ super.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder(64);
        buf.append("SwingUISettings{");
        int len = buf.length();
        toString(buf);
        if (buf.length() > len) {
            buf.setLength(buf.length() - 2);
        }
        buf.append('}');
        return buf.toString();
    }

    @Override
    protected void toString(StringBuilder buf) {
        super.toString(buf);
        buf.append("factories").append('=').append(JodaBeanUtils.toString(getFactories())).append(',').append(' ');
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-bean for {@code SwingUISettings}.
     */
    public static class Meta extends UISettings.Meta {
        /**
         * The singleton instance of the meta-bean.
         */
        static final Meta INSTANCE = new Meta();

        /**
         * The meta-property for the {@code factories} property.
         */
        @SuppressWarnings({"unchecked", "rawtypes" })
        private final MetaProperty<List<PropertyBinderFactory>> factories = DirectMetaProperty.ofReadWrite(
                this, "factories", SwingUISettings.class, (Class) List.class);
        /**
         * The meta-properties.
         */
        private final Map<String, MetaProperty<?>> metaPropertyMap$ = new DirectMetaPropertyMap(
                this, (DirectMetaPropertyMap) super.metaPropertyMap(),
                "factories");

        /**
         * Restricted constructor.
         */
        protected Meta() {
        }

        @Override
        protected MetaProperty<?> metaPropertyGet(String propertyName) {
            switch (propertyName.hashCode()) {
                case -1327306968:  // factories
                    return factories;
            }
            return super.metaPropertyGet(propertyName);
        }

        @Override
        public BeanBuilder<? extends SwingUISettings> builder() {
            return new DirectBeanBuilder<SwingUISettings>(new SwingUISettings());
        }

        @Override
        public Class<? extends SwingUISettings> beanType() {
            return SwingUISettings.class;
        }

        @Override
        public Map<String, MetaProperty<?>> metaPropertyMap() {
            return metaPropertyMap$;
        }

        //-----------------------------------------------------------------------
        @Override
        protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
            switch (propertyName.hashCode()) {
                case -1327306968:  // factories
                    return ((SwingUISettings) bean).getFactories();
            }
            return super.propertyGet(bean, propertyName, quiet);
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
            switch (propertyName.hashCode()) {
                case -1327306968:  // factories
                    ((SwingUISettings) bean).setFactories((List<PropertyBinderFactory>) newValue);
                    return;
            }
            super.propertySet(bean, propertyName, newValue, quiet);
        }

        @Override
        protected void validate(Bean bean) {
            JodaBeanUtils.notNull(((SwingUISettings) bean).factories, "factories");
            super.validate(bean);
        }

    }

    ///CLOVER:ON
    //-------------------------- AUTOGENERATED END --------------------------
}
