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

import java.util.Map;
import java.util.Set;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

/**
 * Example address class.
 */
@BeanDefinition
public class Address implements Bean {

    @PropertyDefinition
    private String street;
    @PropertyDefinition
    private String city;
    @PropertyDefinition
    private String area;
    @PropertyDefinition
    private String postalCode;

    //------------------------- AUTOGENERATED START -------------------------
    ///CLOVER:OFF
    /**
     * The meta-bean for {@code Address}.
     * @return the meta-bean, not null
     */
    public static Address.Meta meta() {
        return Address.Meta.INSTANCE;
    }

    static {
        JodaBeanUtils.registerMetaBean(Address.Meta.INSTANCE);
    }

    @Override
    public Address.Meta metaBean() {
        return Address.Meta.INSTANCE;
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
     * Gets the street.
     * @return the value of the property
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the street.
     * @param street  the new value of the property
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Gets the the {@code street} property.
     * @return the property, not null
     */
    public final Property<String> street() {
        return metaBean().street().createProperty(this);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the city.
     * @return the value of the property
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city.
     * @param city  the new value of the property
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the the {@code city} property.
     * @return the property, not null
     */
    public final Property<String> city() {
        return metaBean().city().createProperty(this);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the area.
     * @return the value of the property
     */
    public String getArea() {
        return area;
    }

    /**
     * Sets the area.
     * @param area  the new value of the property
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * Gets the the {@code area} property.
     * @return the property, not null
     */
    public final Property<String> area() {
        return metaBean().area().createProperty(this);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the postalCode.
     * @return the value of the property
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the postalCode.
     * @param postalCode  the new value of the property
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Gets the the {@code postalCode} property.
     * @return the property, not null
     */
    public final Property<String> postalCode() {
        return metaBean().postalCode().createProperty(this);
    }

    //-----------------------------------------------------------------------
    @Override
    public Address clone() {
        BeanBuilder<? extends Address> builder = metaBean().builder();
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
            Address other = (Address) obj;
            return JodaBeanUtils.equal(getStreet(), other.getStreet()) &&
                    JodaBeanUtils.equal(getCity(), other.getCity()) &&
                    JodaBeanUtils.equal(getArea(), other.getArea()) &&
                    JodaBeanUtils.equal(getPostalCode(), other.getPostalCode());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = getClass().hashCode();
        hash += hash * 31 + JodaBeanUtils.hashCode(getStreet());
        hash += hash * 31 + JodaBeanUtils.hashCode(getCity());
        hash += hash * 31 + JodaBeanUtils.hashCode(getArea());
        hash += hash * 31 + JodaBeanUtils.hashCode(getPostalCode());
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder(160);
        buf.append("Address{");
        int len = buf.length();
        toString(buf);
        if (buf.length() > len) {
            buf.setLength(buf.length() - 2);
        }
        buf.append('}');
        return buf.toString();
    }

    protected void toString(StringBuilder buf) {
        buf.append("street").append('=').append(JodaBeanUtils.toString(getStreet())).append(',').append(' ');
        buf.append("city").append('=').append(JodaBeanUtils.toString(getCity())).append(',').append(' ');
        buf.append("area").append('=').append(JodaBeanUtils.toString(getArea())).append(',').append(' ');
        buf.append("postalCode").append('=').append(JodaBeanUtils.toString(getPostalCode())).append(',').append(' ');
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-bean for {@code Address}.
     */
    public static class Meta extends DirectMetaBean {
        /**
         * The singleton instance of the meta-bean.
         */
        static final Meta INSTANCE = new Meta();

        /**
         * The meta-property for the {@code street} property.
         */
        private final MetaProperty<String> street = DirectMetaProperty.ofReadWrite(
                this, "street", Address.class, String.class);
        /**
         * The meta-property for the {@code city} property.
         */
        private final MetaProperty<String> city = DirectMetaProperty.ofReadWrite(
                this, "city", Address.class, String.class);
        /**
         * The meta-property for the {@code area} property.
         */
        private final MetaProperty<String> area = DirectMetaProperty.ofReadWrite(
                this, "area", Address.class, String.class);
        /**
         * The meta-property for the {@code postalCode} property.
         */
        private final MetaProperty<String> postalCode = DirectMetaProperty.ofReadWrite(
                this, "postalCode", Address.class, String.class);
        /**
         * The meta-properties.
         */
        private final Map<String, MetaProperty<?>> metaPropertyMap$ = new DirectMetaPropertyMap(
                this, null,
                "street",
                "city",
                "area",
                "postalCode");

        /**
         * Restricted constructor.
         */
        protected Meta() {
        }

        @Override
        protected MetaProperty<?> metaPropertyGet(String propertyName) {
            switch (propertyName.hashCode()) {
                case -891990013:  // street
                    return street;
                case 3053931:  // city
                    return city;
                case 3002509:  // area
                    return area;
                case 2011152728:  // postalCode
                    return postalCode;
            }
            return super.metaPropertyGet(propertyName);
        }

        @Override
        public BeanBuilder<? extends Address> builder() {
            return new DirectBeanBuilder<Address>(new Address());
        }

        @Override
        public Class<? extends Address> beanType() {
            return Address.class;
        }

        @Override
        public Map<String, MetaProperty<?>> metaPropertyMap() {
            return metaPropertyMap$;
        }

        //-----------------------------------------------------------------------
        /**
         * The meta-property for the {@code street} property.
         * @return the meta-property, not null
         */
        public final MetaProperty<String> street() {
            return street;
        }

        /**
         * The meta-property for the {@code city} property.
         * @return the meta-property, not null
         */
        public final MetaProperty<String> city() {
            return city;
        }

        /**
         * The meta-property for the {@code area} property.
         * @return the meta-property, not null
         */
        public final MetaProperty<String> area() {
            return area;
        }

        /**
         * The meta-property for the {@code postalCode} property.
         * @return the meta-property, not null
         */
        public final MetaProperty<String> postalCode() {
            return postalCode;
        }

        //-----------------------------------------------------------------------
        @Override
        protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
            switch (propertyName.hashCode()) {
                case -891990013:  // street
                    return ((Address) bean).getStreet();
                case 3053931:  // city
                    return ((Address) bean).getCity();
                case 3002509:  // area
                    return ((Address) bean).getArea();
                case 2011152728:  // postalCode
                    return ((Address) bean).getPostalCode();
            }
            return super.propertyGet(bean, propertyName, quiet);
        }

        @Override
        protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
            switch (propertyName.hashCode()) {
                case -891990013:  // street
                    ((Address) bean).setStreet((String) newValue);
                    return;
                case 3053931:  // city
                    ((Address) bean).setCity((String) newValue);
                    return;
                case 3002509:  // area
                    ((Address) bean).setArea((String) newValue);
                    return;
                case 2011152728:  // postalCode
                    ((Address) bean).setPostalCode((String) newValue);
                    return;
            }
            super.propertySet(bean, propertyName, newValue, quiet);
        }

    }

    ///CLOVER:ON
    //-------------------------- AUTOGENERATED END --------------------------
}
