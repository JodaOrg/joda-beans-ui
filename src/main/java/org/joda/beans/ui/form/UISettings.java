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
package org.joda.beans.ui.form;

import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;
import org.joda.convert.StringConvert;

/**
 * Settings for the UI.
 */
@BeanDefinition(style = "minimal")
public abstract class UISettings implements Bean {

    /**
     * The string converter, which can be edited
     */
    @PropertyDefinition(validate = "notNull")
    private StringConvert stringConvert = StringConvert.create();
    /**
     * The locale.
     */
    @PropertyDefinition(validate = "notNull")
    private Locale locale = Locale.getDefault();
    /**
     * The time-zone.
     */
    @PropertyDefinition(validate = "notNull")
    private TimeZone timeZone = TimeZone.getDefault();

    //-------------------------------------------------------------------------
    /**
     * Creates an instance.
     */
    protected UISettings() {
    }

    //------------------------- AUTOGENERATED START -------------------------
    ///CLOVER:OFF
    /**
     * The meta-bean for {@code UISettings}.
     * @return the meta-bean, not null
     */
    public static UISettings.Meta meta() {
        return UISettings.Meta.INSTANCE;
    }

    static {
        JodaBeanUtils.registerMetaBean(UISettings.Meta.INSTANCE);
    }

    @Override
    public UISettings.Meta metaBean() {
        return UISettings.Meta.INSTANCE;
    }

    @Override
    public <R> Property<R> property(String propertyName) {
        return metaBean().<R>metaProperty(propertyName).createProperty(this);
    }

    @Override
    public Set<String> propertyNames() {
        return metaBean().metaPropertyMap().keySet();
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the string converter, which can be edited
     * @return the value of the property, not null
     */
    public StringConvert getStringConvert() {
        return stringConvert;
    }

    /**
     * Sets the string converter, which can be edited
     * @param stringConvert  the new value of the property, not null
     */
    public void setStringConvert(StringConvert stringConvert) {
        JodaBeanUtils.notNull(stringConvert, "stringConvert");
        this.stringConvert = stringConvert;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the locale.
     * @return the value of the property, not null
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * Sets the locale.
     * @param locale  the new value of the property, not null
     */
    public void setLocale(Locale locale) {
        JodaBeanUtils.notNull(locale, "locale");
        this.locale = locale;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the time-zone.
     * @return the value of the property, not null
     */
    public TimeZone getTimeZone() {
        return timeZone;
    }

    /**
     * Sets the time-zone.
     * @param timeZone  the new value of the property, not null
     */
    public void setTimeZone(TimeZone timeZone) {
        JodaBeanUtils.notNull(timeZone, "timeZone");
        this.timeZone = timeZone;
    }

    //-----------------------------------------------------------------------
    @Override
    public UISettings clone() {
        BeanBuilder<? extends UISettings> builder = metaBean().builder();
        for (MetaProperty<?> mp : metaBean().metaPropertyIterable()) {
            if (mp.style().isBuildable()) {
                Object value = mp.get(this);
                if (value instanceof Bean) {
                    value = ((Bean) value).clone();
                }
                builder.set(mp.name(), value);
            }
        }
        return builder.build();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == this.getClass()) {
            UISettings other = (UISettings) obj;
            return JodaBeanUtils.equal(getStringConvert(), other.getStringConvert()) &&
                    JodaBeanUtils.equal(getLocale(), other.getLocale()) &&
                    JodaBeanUtils.equal(getTimeZone(), other.getTimeZone());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = getClass().hashCode();
        hash += hash * 31 + JodaBeanUtils.hashCode(getStringConvert());
        hash += hash * 31 + JodaBeanUtils.hashCode(getLocale());
        hash += hash * 31 + JodaBeanUtils.hashCode(getTimeZone());
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder(128);
        buf.append("UISettings{");
        int len = buf.length();
        toString(buf);
        if (buf.length() > len) {
            buf.setLength(buf.length() - 2);
        }
        buf.append('}');
        return buf.toString();
    }

    protected void toString(StringBuilder buf) {
        buf.append("stringConvert").append('=').append(JodaBeanUtils.toString(getStringConvert())).append(',').append(' ');
        buf.append("locale").append('=').append(JodaBeanUtils.toString(getLocale())).append(',').append(' ');
        buf.append("timeZone").append('=').append(JodaBeanUtils.toString(getTimeZone())).append(',').append(' ');
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-bean for {@code UISettings}.
     */
    public static class Meta extends DirectMetaBean {
        /**
         * The singleton instance of the meta-bean.
         */
        static final Meta INSTANCE = new Meta();

        /**
         * The meta-property for the {@code stringConvert} property.
         */
        private final MetaProperty<StringConvert> stringConvert = DirectMetaProperty.ofReadWrite(
                this, "stringConvert", UISettings.class, StringConvert.class);
        /**
         * The meta-property for the {@code locale} property.
         */
        private final MetaProperty<Locale> locale = DirectMetaProperty.ofReadWrite(
                this, "locale", UISettings.class, Locale.class);
        /**
         * The meta-property for the {@code timeZone} property.
         */
        private final MetaProperty<TimeZone> timeZone = DirectMetaProperty.ofReadWrite(
                this, "timeZone", UISettings.class, TimeZone.class);
        /**
         * The meta-properties.
         */
        private final Map<String, MetaProperty<?>> metaPropertyMap$ = new DirectMetaPropertyMap(
                this, null,
                "stringConvert",
                "locale",
                "timeZone");

        /**
         * Restricted constructor.
         */
        protected Meta() {
        }

        @Override
        protected MetaProperty<?> metaPropertyGet(String propertyName) {
            switch (propertyName.hashCode()) {
                case 1044598882:  // stringConvert
                    return stringConvert;
                case -1097462182:  // locale
                    return locale;
                case -2077180903:  // timeZone
                    return timeZone;
            }
            return super.metaPropertyGet(propertyName);
        }

        @Override
        public BeanBuilder<? extends UISettings> builder() {
            throw new UnsupportedOperationException("UISettings is an abstract class");
        }

        @Override
        public Class<? extends UISettings> beanType() {
            return UISettings.class;
        }

        @Override
        public Map<String, MetaProperty<?>> metaPropertyMap() {
            return metaPropertyMap$;
        }

        //-----------------------------------------------------------------------
        @Override
        protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
            switch (propertyName.hashCode()) {
                case 1044598882:  // stringConvert
                    return ((UISettings) bean).getStringConvert();
                case -1097462182:  // locale
                    return ((UISettings) bean).getLocale();
                case -2077180903:  // timeZone
                    return ((UISettings) bean).getTimeZone();
            }
            return super.propertyGet(bean, propertyName, quiet);
        }

        @Override
        protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
            switch (propertyName.hashCode()) {
                case 1044598882:  // stringConvert
                    ((UISettings) bean).setStringConvert((StringConvert) newValue);
                    return;
                case -1097462182:  // locale
                    ((UISettings) bean).setLocale((Locale) newValue);
                    return;
                case -2077180903:  // timeZone
                    ((UISettings) bean).setTimeZone((TimeZone) newValue);
                    return;
            }
            super.propertySet(bean, propertyName, newValue, quiet);
        }

        @Override
        protected void validate(Bean bean) {
            JodaBeanUtils.notNull(((UISettings) bean).stringConvert, "stringConvert");
            JodaBeanUtils.notNull(((UISettings) bean).locale, "locale");
            JodaBeanUtils.notNull(((UISettings) bean).timeZone, "timeZone");
        }

    }

    ///CLOVER:ON
    //-------------------------- AUTOGENERATED END --------------------------
}
