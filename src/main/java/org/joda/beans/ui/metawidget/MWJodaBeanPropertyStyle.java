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
package org.joda.beans.ui.metawidget;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.NoSuchElementException;

import org.joda.beans.Bean;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaBean;
import org.joda.beans.MetaProperty;
import org.metawidget.inspector.iface.InspectorException;
import org.metawidget.inspector.impl.propertystyle.BaseProperty;
import org.metawidget.inspector.impl.propertystyle.BasePropertyStyle;
import org.metawidget.inspector.impl.propertystyle.Property;
import org.metawidget.inspector.impl.propertystyle.javabean.JavaBeanPropertyStyleConfig;
import org.metawidget.util.ClassUtils;
import org.metawidget.util.CollectionUtils;

/**
 * Entry point factory class capable of creating a Swing UI for a bean.
 */
public class MWJodaBeanPropertyStyle extends BasePropertyStyle {

    public MWJodaBeanPropertyStyle() {
        super(new JavaBeanPropertyStyleConfig());
    }

    @Override
    protected Map<String, Property> inspectProperties(String type) {
        try {
            Class<?> clazz = ClassUtils.niceForName(type);
            Map<String, Property> properties = CollectionUtils.newLinkedHashMap();
            MetaBean mb = JodaBeanUtils.metaBean(clazz);
            for (MetaProperty<?> mp : mb.metaPropertyIterable()) {
                properties.put(mp.name(), new MWJodaBeanProperty(mp));
            }
            return properties;
        } catch (Exception ex) {
            throw InspectorException.newException(ex);
        }
    }

    private static class MWJodaBeanProperty extends BaseProperty {
        private final MetaProperty<?> metaProperty;

        public MWJodaBeanProperty(MetaProperty<?> metaPropery) {
            super(metaPropery.name(), metaPropery.propertyType().getName());
            this.metaProperty = metaPropery;
        }

        @Override
        public boolean isReadable() {
            return metaProperty.style().isReadable();
        }

        @Override
        public Object read(Object obj) {
            return metaProperty.get((Bean) obj);
        }

        @Override
        public boolean isWritable() {
            return metaProperty.style().isWritable();
        }

        @Override
        public void write(Object obj, Object value) {
            metaProperty.set((Bean) obj, value);
        }

        @Override
        public String getGenericType() {
            return ClassUtils.getGenericTypeAsString(metaProperty.propertyGenericType());
        }

        @Override
        public <T extends Annotation> T getAnnotation(Class<T> annotation) {
            try {
                return metaProperty.annotation(annotation);
            } catch (NoSuchElementException ex) {
                return null;
            }
        }
    }

}
