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

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.joda.beans.MetaBean;
import org.joda.beans.MetaProperty;
import org.joda.beans.PropertyDefinition;

/**
 * Factory used to create {@code MetaUIForm} instances.
 */
public class MetaUIFactory {

    /**
     * Creates an instance.
     */
    public MetaUIFactory() {
    }

    //-------------------------------------------------------------------------
    /**
     * Creates a form for a meta-bean.
     * 
     * @param metaBean  the meta-bean, not null
     * @return the form, not null
     */
    public MetaUIForm createForm(final MetaBean metaBean) {
        Objects.requireNonNull(metaBean, "metaBean");
        MetaUIForm form = new MetaUIForm(metaBean);
        for (MetaProperty<?> mp : metaBean.metaPropertyIterable()) {
            buildComponent(form, mp);
        }
        return form;
    }

    /**
     * Builds the component for the property.
     * 
     * @param form  the form to add to, not null
     * @param mp  the meta-property, not null
     */
    protected void buildComponent(MetaUIForm form, MetaProperty<?> mp) {
        MetaUIComponent comp = new MetaUIComponent(mp);
        buildMandatory(comp);
        buildMinMax(comp);
        buildSize(comp);
        buildValues(comp);
        form.getComponents().add(comp);
    }

    /**
     * Builds the mandatory flag for the component.
     * 
     * @param comp  the component, not null
     */
    protected void buildMandatory(MetaUIComponent comp) {
        if (comp.getMetaProperty().propertyType().isPrimitive()) {
            comp.setMandatory(true);
        }
        PropertyDefinition propAnno = findAnnotation(comp, PropertyDefinition.class);
        if (propAnno != null) {
            if (propAnno.validate().equals("notNull") || propAnno.validate().equals("notEmpty")) {
                comp.setMandatory(true);
            }
        }
        NotNull nnAnno = findAnnotation(comp, NotNull.class);
        if (nnAnno != null) {
            comp.setMandatory(true);
        }
    }

    /**
     * Builds the min/max values for the component.
     * 
     * @param comp  the component, not null
     */
    protected void buildMinMax(MetaUIComponent comp) {
        Max maxAnno = findAnnotation(comp, Max.class);
        Min minAnno = findAnnotation(comp, Min.class);
        Class<?> type = comp.getMetaProperty().propertyType();
        if (type == Byte.class || type == Byte.TYPE) {
            if (minAnno != null) {
                comp.setMinValue((byte) minAnno.value());
            }
            if (maxAnno != null) {
                comp.setMaxValue((byte) maxAnno.value());
            }
        } else if (type == Short.class || type == Short.TYPE) {
            if (minAnno != null) {
                comp.setMinValue((short) minAnno.value());
            }
            if (maxAnno != null) {
                comp.setMaxValue((short) maxAnno.value());
            }
        } else if (type == Integer.class || type == Integer.TYPE) {
            if (minAnno != null) {
                comp.setMinValue((int) minAnno.value());
            }
            if (maxAnno != null) {
                comp.setMaxValue((int) maxAnno.value());
            }
        } else if (type == Long.class || type == Long.TYPE) {
            if (minAnno != null) {
                comp.setMinValue((byte) minAnno.value());
            }
            if (maxAnno != null) {
                comp.setMaxValue((byte) maxAnno.value());
            }
        } else if (type == Float.class || type == Float.TYPE) {
            comp.setMinValue(Float.NEGATIVE_INFINITY);
            comp.setMaxValue(Float.POSITIVE_INFINITY);
        } else if (type == Double.class || type == Double.TYPE) {
            comp.setMinValue(Double.NEGATIVE_INFINITY);
            comp.setMaxValue(Double.POSITIVE_INFINITY);
        }
    }

    /**
     * Builds the min/max size for the component.
     * 
     * @param comp  the component, not null
     */
    protected void buildSize(MetaUIComponent comp) {
        Size sizeAnno = findAnnotation(comp, Size.class);
        if (sizeAnno != null) {
            comp.setMinSize(sizeAnno.min());
            comp.setMaxSize(sizeAnno.max());
        }
    }

    /**
     * Builds the selectable values for the component.
     * 
     * @param comp  the component, not null
     */
    protected void buildValues(MetaUIComponent comp) {
        Class<?> type = comp.getMetaProperty().propertyType();
        if (Enum.class.isAssignableFrom(type)) {
            Object[] values = type.getEnumConstants();
            comp.setLimitedValues(true);
            comp.setSelectableValues(new ArrayList<>(Arrays.asList(values)));
        }
    }

    //-------------------------------------------------------------------------
    /**
     * Finds an annotation on the property, returning null if not found.
     * 
     * @param <A>  the annotation type
     * @param metaComponent  the meta-component to examine, not null
     * @param annotationType  the annotation type, not null
     * @return the annotation, null if not found
     */
    protected <A extends Annotation> A findAnnotation(MetaUIComponent metaComponent, Class<A> annotationType) {
        try {
            return metaComponent.getMetaProperty().annotation(annotationType);
        } catch (RuntimeException ex) {
            return null;
        }
    }

}
