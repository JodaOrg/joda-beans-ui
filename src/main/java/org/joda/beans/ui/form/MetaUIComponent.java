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

import java.util.List;
import java.util.Map;
import java.util.Objects;
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
 * A description of a UI component.
 */
@BeanDefinition(style = "minimal")
public class MetaUIComponent implements Bean {

    /**
     * The property that the component is for.
     */
    @PropertyDefinition(validate = "notNull", set = "private")
    private MetaProperty<?> metaProperty;
    /**
     * Whether the component is mandatory.
     * Default false.
     */
    @PropertyDefinition
    private boolean mandatory;
    /**
     * The minimum valid value, inclusive.
     * Default null.
     */
    @PropertyDefinition
    private Comparable<?> minValue;
    /**
     * The maximum valid value, inclusive.
     * Default null.
     */
    @PropertyDefinition
    private Comparable<?> maxValue;
    /**
     * The minimum size, inclusive.
     * Default zero.
     */
    @PropertyDefinition
    private int minSize;
    /**
     * The maximum size, inclusive.
     * Default Integer.MAX_VALUE.
     */
    @PropertyDefinition
    private int maxSize = Integer.MAX_VALUE;
    /**
     * Whether the component only allows a limited set of values.
     * If true, the list of values must be defined.
     * If false then any value is allowed.
     * Default false.
     */
    @PropertyDefinition
    private boolean limitedValues;
    /**
     * The values that can be selected.
     */
    @PropertyDefinition
    private List<Object> selectableValues;

    /**
     * Creates an instance.
     */
    protected MetaUIComponent() {
    }

    /**
     * Creates an instance.
     * 
     * @param metaProperty  the meta-property, not null
     */
    public MetaUIComponent(MetaProperty<?> metaProperty) {
        setMetaProperty(metaProperty);
    }

    //-------------------------------------------------------------------------
    /**
     * Gets the type of the underlying property.
     * 
     * @return the type, not null
     */
    public Class<?> getPropertyType() {
        return getMetaProperty().propertyType();
    }

    /**
     * Gets the minimum numeric value, defaulting for null and casting.
     * 
     * @param defaultValue  the default value, not null
     * @return the minimum value, not null
     */
    public Number getMinValue(Number defaultValue) {
        Objects.requireNonNull(defaultValue, "defaultValue");
        Number min = (Number) getMinValue();
        return (min != null ? min : defaultValue);
    }

    /**
     * Gets the maximum numeric value, defaulting for null and casting.
     * 
     * @param defaultValue  the default value, not null
     * @return the maximum value, not null
     */
    public Number getMaxValue(Number defaultValue) {
        Objects.requireNonNull(defaultValue, "defaultValue");
        Number max = (Number) getMaxValue();
        return (max != null ? max : defaultValue);
    }

    //------------------------- AUTOGENERATED START -------------------------
    ///CLOVER:OFF
    /**
     * The meta-bean for {@code MetaUIComponent}.
     * @return the meta-bean, not null
     */
    public static MetaUIComponent.Meta meta() {
        return MetaUIComponent.Meta.INSTANCE;
    }

    static {
        JodaBeanUtils.registerMetaBean(MetaUIComponent.Meta.INSTANCE);
    }

    @Override
    public MetaUIComponent.Meta metaBean() {
        return MetaUIComponent.Meta.INSTANCE;
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
     * Gets the property that the component is for.
     * @return the value of the property, not null
     */
    public MetaProperty<?> getMetaProperty() {
        return metaProperty;
    }

    /**
     * Sets the property that the component is for.
     * @param metaProperty  the new value of the property, not null
     */
    private void setMetaProperty(MetaProperty<?> metaProperty) {
        JodaBeanUtils.notNull(metaProperty, "metaProperty");
        this.metaProperty = metaProperty;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets whether the component is mandatory.
     * Default false.
     * @return the value of the property
     */
    public boolean isMandatory() {
        return mandatory;
    }

    /**
     * Sets whether the component is mandatory.
     * Default false.
     * @param mandatory  the new value of the property
     */
    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the minimum valid value, inclusive.
     * Default null.
     * @return the value of the property
     */
    public Comparable<?> getMinValue() {
        return minValue;
    }

    /**
     * Sets the minimum valid value, inclusive.
     * Default null.
     * @param minValue  the new value of the property
     */
    public void setMinValue(Comparable<?> minValue) {
        this.minValue = minValue;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the maximum valid value, inclusive.
     * Default null.
     * @return the value of the property
     */
    public Comparable<?> getMaxValue() {
        return maxValue;
    }

    /**
     * Sets the maximum valid value, inclusive.
     * Default null.
     * @param maxValue  the new value of the property
     */
    public void setMaxValue(Comparable<?> maxValue) {
        this.maxValue = maxValue;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the minimum size, inclusive.
     * Default null.
     * @return the value of the property
     */
    public Integer getMinSize() {
        return minSize;
    }

    /**
     * Sets the minimum size, inclusive.
     * Default null.
     * @param minSize  the new value of the property
     */
    public void setMinSize(Integer minSize) {
        this.minSize = minSize;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the maximum size, inclusive.
     * Default null.
     * @return the value of the property
     */
    public Integer getMaxSize() {
        return maxSize;
    }

    /**
     * Sets the maximum size, inclusive.
     * Default null.
     * @param maxSize  the new value of the property
     */
    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets whether the component only allows a limited set of values.
     * If true, the list of values must be defined.
     * If false then any value is allowed.
     * Default false.
     * @return the value of the property
     */
    public boolean isLimitedValues() {
        return limitedValues;
    }

    /**
     * Sets whether the component only allows a limited set of values.
     * If true, the list of values must be defined.
     * If false then any value is allowed.
     * Default false.
     * @param limitedValues  the new value of the property
     */
    public void setLimitedValues(boolean limitedValues) {
        this.limitedValues = limitedValues;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the values that can be selected.
     * @return the value of the property
     */
    public List<Object> getSelectableValues() {
        return selectableValues;
    }

    /**
     * Sets the values that can be selected.
     * @param selectableValues  the new value of the property
     */
    public void setSelectableValues(List<Object> selectableValues) {
        this.selectableValues = selectableValues;
    }

    //-----------------------------------------------------------------------
    @Override
    public MetaUIComponent clone() {
        BeanBuilder<? extends MetaUIComponent> builder = metaBean().builder();
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
            MetaUIComponent other = (MetaUIComponent) obj;
            return JodaBeanUtils.equal(getMetaProperty(), other.getMetaProperty()) &&
                    (isMandatory() == other.isMandatory()) &&
                    JodaBeanUtils.equal(getMinValue(), other.getMinValue()) &&
                    JodaBeanUtils.equal(getMaxValue(), other.getMaxValue()) &&
                    JodaBeanUtils.equal(getMinSize(), other.getMinSize()) &&
                    JodaBeanUtils.equal(getMaxSize(), other.getMaxSize()) &&
                    (isLimitedValues() == other.isLimitedValues()) &&
                    JodaBeanUtils.equal(getSelectableValues(), other.getSelectableValues());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = getClass().hashCode();
        hash += hash * 31 + JodaBeanUtils.hashCode(getMetaProperty());
        hash += hash * 31 + JodaBeanUtils.hashCode(isMandatory());
        hash += hash * 31 + JodaBeanUtils.hashCode(getMinValue());
        hash += hash * 31 + JodaBeanUtils.hashCode(getMaxValue());
        hash += hash * 31 + JodaBeanUtils.hashCode(getMinSize());
        hash += hash * 31 + JodaBeanUtils.hashCode(getMaxSize());
        hash += hash * 31 + JodaBeanUtils.hashCode(isLimitedValues());
        hash += hash * 31 + JodaBeanUtils.hashCode(getSelectableValues());
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder(288);
        buf.append("MetaUIComponent{");
        int len = buf.length();
        toString(buf);
        if (buf.length() > len) {
            buf.setLength(buf.length() - 2);
        }
        buf.append('}');
        return buf.toString();
    }

    protected void toString(StringBuilder buf) {
        buf.append("metaProperty").append('=').append(JodaBeanUtils.toString(getMetaProperty())).append(',').append(' ');
        buf.append("mandatory").append('=').append(JodaBeanUtils.toString(isMandatory())).append(',').append(' ');
        buf.append("minValue").append('=').append(JodaBeanUtils.toString(getMinValue())).append(',').append(' ');
        buf.append("maxValue").append('=').append(JodaBeanUtils.toString(getMaxValue())).append(',').append(' ');
        buf.append("minSize").append('=').append(JodaBeanUtils.toString(getMinSize())).append(',').append(' ');
        buf.append("maxSize").append('=').append(JodaBeanUtils.toString(getMaxSize())).append(',').append(' ');
        buf.append("limitedValues").append('=').append(JodaBeanUtils.toString(isLimitedValues())).append(',').append(' ');
        buf.append("selectableValues").append('=').append(JodaBeanUtils.toString(getSelectableValues())).append(',').append(' ');
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-bean for {@code MetaUIComponent}.
     */
    public static class Meta extends DirectMetaBean {
        /**
         * The singleton instance of the meta-bean.
         */
        static final Meta INSTANCE = new Meta();

        /**
         * The meta-property for the {@code metaProperty} property.
         */
        @SuppressWarnings({"unchecked", "rawtypes" })
        private final MetaProperty<MetaProperty<?>> metaProperty = DirectMetaProperty.ofReadWrite(
                this, "metaProperty", MetaUIComponent.class, (Class) MetaProperty.class);
        /**
         * The meta-property for the {@code mandatory} property.
         */
        private final MetaProperty<Boolean> mandatory = DirectMetaProperty.ofReadWrite(
                this, "mandatory", MetaUIComponent.class, Boolean.TYPE);
        /**
         * The meta-property for the {@code minValue} property.
         */
        @SuppressWarnings({"unchecked", "rawtypes" })
        private final MetaProperty<Comparable<?>> minValue = DirectMetaProperty.ofReadWrite(
                this, "minValue", MetaUIComponent.class, (Class) Comparable.class);
        /**
         * The meta-property for the {@code maxValue} property.
         */
        @SuppressWarnings({"unchecked", "rawtypes" })
        private final MetaProperty<Comparable<?>> maxValue = DirectMetaProperty.ofReadWrite(
                this, "maxValue", MetaUIComponent.class, (Class) Comparable.class);
        /**
         * The meta-property for the {@code minSize} property.
         */
        private final MetaProperty<Integer> minSize = DirectMetaProperty.ofReadWrite(
                this, "minSize", MetaUIComponent.class, Integer.class);
        /**
         * The meta-property for the {@code maxSize} property.
         */
        private final MetaProperty<Integer> maxSize = DirectMetaProperty.ofReadWrite(
                this, "maxSize", MetaUIComponent.class, Integer.class);
        /**
         * The meta-property for the {@code limitedValues} property.
         */
        private final MetaProperty<Boolean> limitedValues = DirectMetaProperty.ofReadWrite(
                this, "limitedValues", MetaUIComponent.class, Boolean.TYPE);
        /**
         * The meta-property for the {@code selectableValues} property.
         */
        @SuppressWarnings({"unchecked", "rawtypes" })
        private final MetaProperty<List<Object>> selectableValues = DirectMetaProperty.ofReadWrite(
                this, "selectableValues", MetaUIComponent.class, (Class) List.class);
        /**
         * The meta-properties.
         */
        private final Map<String, MetaProperty<?>> metaPropertyMap$ = new DirectMetaPropertyMap(
                this, null,
                "metaProperty",
                "mandatory",
                "minValue",
                "maxValue",
                "minSize",
                "maxSize",
                "limitedValues",
                "selectableValues");

        /**
         * Restricted constructor.
         */
        protected Meta() {
        }

        @Override
        protected MetaProperty<?> metaPropertyGet(String propertyName) {
            switch (propertyName.hashCode()) {
                case -1396997382:  // metaProperty
                    return metaProperty;
                case -392910375:  // mandatory
                    return mandatory;
                case -1376969153:  // minValue
                    return minValue;
                case 399227501:  // maxValue
                    return maxValue;
                case 1063879027:  // minSize
                    return minSize;
                case 844081029:  // maxSize
                    return maxSize;
                case -877280420:  // limitedValues
                    return limitedValues;
                case 953376024:  // selectableValues
                    return selectableValues;
            }
            return super.metaPropertyGet(propertyName);
        }

        @Override
        public BeanBuilder<? extends MetaUIComponent> builder() {
            return new DirectBeanBuilder<MetaUIComponent>(new MetaUIComponent());
        }

        @Override
        public Class<? extends MetaUIComponent> beanType() {
            return MetaUIComponent.class;
        }

        @Override
        public Map<String, MetaProperty<?>> metaPropertyMap() {
            return metaPropertyMap$;
        }

        //-----------------------------------------------------------------------
        @Override
        protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
            switch (propertyName.hashCode()) {
                case -1396997382:  // metaProperty
                    return ((MetaUIComponent) bean).getMetaProperty();
                case -392910375:  // mandatory
                    return ((MetaUIComponent) bean).isMandatory();
                case -1376969153:  // minValue
                    return ((MetaUIComponent) bean).getMinValue();
                case 399227501:  // maxValue
                    return ((MetaUIComponent) bean).getMaxValue();
                case 1063879027:  // minSize
                    return ((MetaUIComponent) bean).getMinSize();
                case 844081029:  // maxSize
                    return ((MetaUIComponent) bean).getMaxSize();
                case -877280420:  // limitedValues
                    return ((MetaUIComponent) bean).isLimitedValues();
                case 953376024:  // selectableValues
                    return ((MetaUIComponent) bean).getSelectableValues();
            }
            return super.propertyGet(bean, propertyName, quiet);
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
            switch (propertyName.hashCode()) {
                case -1396997382:  // metaProperty
                    ((MetaUIComponent) bean).setMetaProperty((MetaProperty<?>) newValue);
                    return;
                case -392910375:  // mandatory
                    ((MetaUIComponent) bean).setMandatory((Boolean) newValue);
                    return;
                case -1376969153:  // minValue
                    ((MetaUIComponent) bean).setMinValue((Comparable<?>) newValue);
                    return;
                case 399227501:  // maxValue
                    ((MetaUIComponent) bean).setMaxValue((Comparable<?>) newValue);
                    return;
                case 1063879027:  // minSize
                    ((MetaUIComponent) bean).setMinSize((Integer) newValue);
                    return;
                case 844081029:  // maxSize
                    ((MetaUIComponent) bean).setMaxSize((Integer) newValue);
                    return;
                case -877280420:  // limitedValues
                    ((MetaUIComponent) bean).setLimitedValues((Boolean) newValue);
                    return;
                case 953376024:  // selectableValues
                    ((MetaUIComponent) bean).setSelectableValues((List<Object>) newValue);
                    return;
            }
            super.propertySet(bean, propertyName, newValue, quiet);
        }

        @Override
        protected void validate(Bean bean) {
            JodaBeanUtils.notNull(((MetaUIComponent) bean).metaProperty, "metaProperty");
        }

    }

    ///CLOVER:ON
    //-------------------------- AUTOGENERATED END --------------------------
}
