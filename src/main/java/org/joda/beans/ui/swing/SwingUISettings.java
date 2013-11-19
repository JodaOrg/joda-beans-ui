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

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;
import org.joda.beans.ui.UISettings;
import org.joda.beans.ui.form.DefaultUIComponentFactory;
import org.joda.beans.ui.form.UIComponentFactory;
import org.joda.beans.ui.swing.type.BigNumberSwingUIComponent;
import org.joda.beans.ui.swing.type.BooleanSwingUIComponent;
import org.joda.beans.ui.swing.type.CurrencySwingUIComponent;
import org.joda.beans.ui.swing.type.DateSwingUIComponent;
import org.joda.beans.ui.swing.type.FloatingSwingUIComponent;
import org.joda.beans.ui.swing.type.IntegralSwingUIComponent;
import org.joda.beans.ui.swing.type.StringSwingUIComponent;
import org.joda.beans.ui.swing.type.TimeZoneSwingUIComponent;

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
     * The map of factories, which can be edited.
     */
    @PropertyDefinition(validate = "notNull", set = "private")
    private final Map<Class<?>, UIComponentFactory> factories = new HashMap<>();

    //-------------------------------------------------------------------------
    /**
     * Creates an instance.
     */
    public SwingUISettings() {
        factories.put(Boolean.TYPE, DefaultUIComponentFactory.of(BooleanSwingUIComponent.class));
        factories.put(Boolean.class, DefaultUIComponentFactory.of(BooleanSwingUIComponent.class));
        factories.put(Byte.TYPE, DefaultUIComponentFactory.of(IntegralSwingUIComponent.class));
        factories.put(Byte.class, DefaultUIComponentFactory.of(IntegralSwingUIComponent.class));
        factories.put(Short.TYPE, DefaultUIComponentFactory.of(IntegralSwingUIComponent.class));
        factories.put(Short.class, DefaultUIComponentFactory.of(IntegralSwingUIComponent.class));
        factories.put(Integer.TYPE, DefaultUIComponentFactory.of(IntegralSwingUIComponent.class));
        factories.put(Integer.class, DefaultUIComponentFactory.of(IntegralSwingUIComponent.class));
        factories.put(Long.TYPE, DefaultUIComponentFactory.of(IntegralSwingUIComponent.class));
        factories.put(Long.class, DefaultUIComponentFactory.of(IntegralSwingUIComponent.class));
        factories.put(Float.TYPE, DefaultUIComponentFactory.of(FloatingSwingUIComponent.class));
        factories.put(Float.class, DefaultUIComponentFactory.of(FloatingSwingUIComponent.class));
        factories.put(Double.TYPE, DefaultUIComponentFactory.of(FloatingSwingUIComponent.class));
        factories.put(Double.class, DefaultUIComponentFactory.of(FloatingSwingUIComponent.class));
        factories.put(BigDecimal.class, DefaultUIComponentFactory.of(BigNumberSwingUIComponent.class));
        factories.put(BigInteger.class, DefaultUIComponentFactory.of(BigNumberSwingUIComponent.class));
        factories.put(String.class, DefaultUIComponentFactory.of(StringSwingUIComponent.class));
        factories.put(CharSequence.class, DefaultUIComponentFactory.of(StringSwingUIComponent.class));
        factories.put(StringBuffer.class, DefaultUIComponentFactory.of(StringSwingUIComponent.class));
        factories.put(StringBuilder.class, DefaultUIComponentFactory.of(StringSwingUIComponent.class));
        factories.put(Calendar.class, DefaultUIComponentFactory.of(DateSwingUIComponent.class));
        factories.put(GregorianCalendar.class, DefaultUIComponentFactory.of(DateSwingUIComponent.class));
        factories.put(Date.class, DefaultUIComponentFactory.of(DateSwingUIComponent.class));
        factories.put(Class.class, DefaultUIComponentFactory.of(StringSwingUIComponent.class));
        factories.put(Package.class, DefaultUIComponentFactory.of(StringSwingUIComponent.class));
        factories.put(URL.class, DefaultUIComponentFactory.of(StringSwingUIComponent.class));
        factories.put(URI.class, DefaultUIComponentFactory.of(StringSwingUIComponent.class));
        factories.put(File.class, DefaultUIComponentFactory.of(StringSwingUIComponent.class));
        factories.put(InetAddress.class, DefaultUIComponentFactory.of(StringSwingUIComponent.class));
        factories.put(UUID.class, DefaultUIComponentFactory.of(StringSwingUIComponent.class));
        factories.put(Currency.class, DefaultUIComponentFactory.of(CurrencySwingUIComponent.class));
        factories.put(TimeZone.class, DefaultUIComponentFactory.of(TimeZoneSwingUIComponent.class));
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
     * Gets the map of factories, which can be edited.
     * @return the value of the property, not null
     */
    public Map<Class<?>, UIComponentFactory> getFactories() {
        return factories;
    }

    /**
     * Sets the map of factories, which can be edited.
     * @param factories  the new value of the property
     */
    private void setFactories(Map<Class<?>, UIComponentFactory> factories) {
        this.factories.clear();
        this.factories.putAll(factories);
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
        private final MetaProperty<Map<Class<?>, UIComponentFactory>> factories = DirectMetaProperty.ofReadWrite(
                this, "factories", SwingUISettings.class, (Class) Map.class);
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
                    ((SwingUISettings) bean).setFactories((Map<Class<?>, UIComponentFactory>) newValue);
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
