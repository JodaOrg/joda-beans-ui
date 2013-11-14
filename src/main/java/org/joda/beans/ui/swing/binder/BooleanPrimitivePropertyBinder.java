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

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JComponent;

import org.joda.beans.Bean;
import org.joda.beans.ui.form.MetaUIComponent;
import org.joda.beans.ui.swing.component.JRadioButtonPanel;

/**
 * Binder for a {@code boolean}.
 */
public class BooleanPrimitivePropertyBinder
        extends SingleComponentPropertyBinder {

    /**
     * The factory.
     */
    public static final PropertyBinderFactory FACTORY = new PropertyBinderFactory() {
        @Override
        public PropertyBinder createBinder(MetaUIComponent metaComponent) {
            if (metaComponent.getPropertyType() == Boolean.TYPE) {
                return new BooleanPrimitivePropertyBinder(metaComponent);
            }
            return null;
        }
    };
    /**
     * The radio button map.
     */
    private static final Map<Boolean, String> TRUE_FALSE;
    static {
        Map<Boolean, String> map = new LinkedHashMap<>();
        map.put(Boolean.TRUE, BinderMsg.lookup("Boolean.true"));
        map.put(Boolean.FALSE, BinderMsg.lookup("Boolean.false"));
        TRUE_FALSE = Collections.unmodifiableMap(map);
    }

    /**
     * The associated component.
     */
    private final JRadioButtonPanel<Boolean> component;

    /**
     * Creates an instance.
     * 
     * @param metaComponent  the meta-component to bind, not null
     */
    public BooleanPrimitivePropertyBinder(MetaUIComponent metaComponent) {
        super(metaComponent);
        this.component = new JRadioButtonPanel<Boolean>(TRUE_FALSE);
    }

    //-------------------------------------------------------------------------
    @Override
    protected JComponent getComponent() {
        return component;
    }

    @Override
    public void updateUI(Bean bean) {
        component.setSelection((Boolean) getMetaProperty().get(bean));
    }

    @Override
    public void updateProperty(Bean bean) {
        Boolean value = component.getSelection();
        if (value == null) {  // should not happen
            value = Boolean.FALSE;
        }
        getMetaProperty().set(bean, component.getSelection());
    }

}
