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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaBean;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

/**
 * A description of a UI form.
 */
@BeanDefinition(style = "minimal")
public class MetaUIForm implements Bean {

    /**
     * The bean that the form is for.
     */
    @PropertyDefinition(validate = "notNull", set = "private")
    private MetaBean formMetaBean;
    /**
     * The components that are part of the form.
     */
    @PropertyDefinition
    private final List<MetaUIComponent> components = new ArrayList<>();

    /**
     * Creates an instance.
     */
    protected MetaUIForm() {
    }

    /**
     * Creates an instance.
     * 
     * @param metaBean  the meta-bean, not null
     */
    public MetaUIForm(MetaBean metaBean) {
        setFormMetaBean(metaBean);
    }

    //------------------------- AUTOGENERATED START -------------------------
    ///CLOVER:OFF
    /**
     * The meta-bean for {@code MetaUIForm}.
     * @return the meta-bean, not null
     */
    public static MetaUIForm.Meta meta() {
        return MetaUIForm.Meta.INSTANCE;
    }

    static {
        JodaBeanUtils.registerMetaBean(MetaUIForm.Meta.INSTANCE);
    }

    @Override
    public MetaUIForm.Meta metaBean() {
        return MetaUIForm.Meta.INSTANCE;
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
     * Gets the bean that the form is for.
     * @return the value of the property, not null
     */
    public MetaBean getFormMetaBean() {
        return formMetaBean;
    }

    /**
     * Sets the bean that the form is for.
     * @param formMetaBean  the new value of the property, not null
     */
    private void setFormMetaBean(MetaBean formMetaBean) {
        JodaBeanUtils.notNull(formMetaBean, "formMetaBean");
        this.formMetaBean = formMetaBean;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the components that are part of the form.
     * @return the value of the property
     */
    public List<MetaUIComponent> getComponents() {
        return components;
    }

    /**
     * Sets the components that are part of the form.
     * @param components  the new value of the property
     */
    public void setComponents(List<MetaUIComponent> components) {
        this.components.clear();
        this.components.addAll(components);
    }

    //-----------------------------------------------------------------------
    @Override
    public MetaUIForm clone() {
        BeanBuilder<? extends MetaUIForm> builder = metaBean().builder();
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
            MetaUIForm other = (MetaUIForm) obj;
            return JodaBeanUtils.equal(getFormMetaBean(), other.getFormMetaBean()) &&
                    JodaBeanUtils.equal(getComponents(), other.getComponents());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = getClass().hashCode();
        hash += hash * 31 + JodaBeanUtils.hashCode(getFormMetaBean());
        hash += hash * 31 + JodaBeanUtils.hashCode(getComponents());
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder(96);
        buf.append("MetaUIForm{");
        int len = buf.length();
        toString(buf);
        if (buf.length() > len) {
            buf.setLength(buf.length() - 2);
        }
        buf.append('}');
        return buf.toString();
    }

    protected void toString(StringBuilder buf) {
        buf.append("formMetaBean").append('=').append(JodaBeanUtils.toString(getFormMetaBean())).append(',').append(' ');
        buf.append("components").append('=').append(JodaBeanUtils.toString(getComponents())).append(',').append(' ');
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-bean for {@code MetaUIForm}.
     */
    public static class Meta extends DirectMetaBean {
        /**
         * The singleton instance of the meta-bean.
         */
        static final Meta INSTANCE = new Meta();

        /**
         * The meta-property for the {@code formMetaBean} property.
         */
        private final MetaProperty<MetaBean> formMetaBean = DirectMetaProperty.ofReadWrite(
                this, "formMetaBean", MetaUIForm.class, MetaBean.class);
        /**
         * The meta-property for the {@code components} property.
         */
        @SuppressWarnings({"unchecked", "rawtypes" })
        private final MetaProperty<List<MetaUIComponent>> components = DirectMetaProperty.ofReadWrite(
                this, "components", MetaUIForm.class, (Class) List.class);
        /**
         * The meta-properties.
         */
        private final Map<String, MetaProperty<?>> metaPropertyMap$ = new DirectMetaPropertyMap(
                this, null,
                "formMetaBean",
                "components");

        /**
         * Restricted constructor.
         */
        protected Meta() {
        }

        @Override
        protected MetaProperty<?> metaPropertyGet(String propertyName) {
            switch (propertyName.hashCode()) {
                case 1925283897:  // formMetaBean
                    return formMetaBean;
                case -447446250:  // components
                    return components;
            }
            return super.metaPropertyGet(propertyName);
        }

        @Override
        public BeanBuilder<? extends MetaUIForm> builder() {
            return new DirectBeanBuilder<MetaUIForm>(new MetaUIForm());
        }

        @Override
        public Class<? extends MetaUIForm> beanType() {
            return MetaUIForm.class;
        }

        @Override
        public Map<String, MetaProperty<?>> metaPropertyMap() {
            return metaPropertyMap$;
        }

        //-----------------------------------------------------------------------
        @Override
        protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
            switch (propertyName.hashCode()) {
                case 1925283897:  // formMetaBean
                    return ((MetaUIForm) bean).getFormMetaBean();
                case -447446250:  // components
                    return ((MetaUIForm) bean).getComponents();
            }
            return super.propertyGet(bean, propertyName, quiet);
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
            switch (propertyName.hashCode()) {
                case 1925283897:  // formMetaBean
                    ((MetaUIForm) bean).setFormMetaBean((MetaBean) newValue);
                    return;
                case -447446250:  // components
                    ((MetaUIForm) bean).setComponents((List<MetaUIComponent>) newValue);
                    return;
            }
            super.propertySet(bean, propertyName, newValue, quiet);
        }

        @Override
        protected void validate(Bean bean) {
            JodaBeanUtils.notNull(((MetaUIForm) bean).formMetaBean, "formMetaBean");
        }

    }

    ///CLOVER:ON
    //-------------------------- AUTOGENERATED END --------------------------
}
