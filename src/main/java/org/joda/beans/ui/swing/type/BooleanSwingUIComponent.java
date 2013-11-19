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
package org.joda.beans.ui.swing.type;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.joda.beans.Bean;
import org.joda.beans.ui.form.MetaUIComponent;
import org.joda.beans.ui.swing.SwingUIComponent;
import org.joda.beans.ui.swing.SwingUISettings;
import org.joda.beans.ui.swing.component.JRadioButtonPanel;

/**
 * An instantiated Swing component for a boolean.
 */
public class BooleanSwingUIComponent extends SwingUIComponent<JRadioButtonPanel<Boolean>> {

    /**
     * The radio button map.
     */
    private static final Map<Boolean, String> TRUE_FALSE;
    /**
     * The radio button map.
     */
    private static final Map<Boolean, String> TRUE_FALSE_NULL;
    static {
        Map<Boolean, String> map1 = new LinkedHashMap<>();
        map1.put(Boolean.TRUE, SwingUISettings.INSTANCE.lookupResource("Boolean.true"));
        map1.put(Boolean.FALSE, SwingUISettings.INSTANCE.lookupResource("Boolean.false"));
        TRUE_FALSE = Collections.unmodifiableMap(map1);
        Map<Boolean, String> map2 = new LinkedHashMap<>();
        map2.put(Boolean.TRUE, SwingUISettings.INSTANCE.lookupResource("Boolean.true"));
        map2.put(Boolean.FALSE, SwingUISettings.INSTANCE.lookupResource("Boolean.false"));
        map2.put(null, SwingUISettings.INSTANCE.lookupResource("Boolean.null"));
        TRUE_FALSE_NULL = Collections.unmodifiableMap(map2);
    }

    /**
     * Creates an instance.
     * 
     * @param metaComponent  the meta-component, not null
     */
    public BooleanSwingUIComponent(MetaUIComponent metaComponent) {
        super(metaComponent);
        Class<?> type = getMetaProperty().propertyType();
        JRadioButtonPanel<Boolean> component;
        if (type == Boolean.TYPE) {
            component = new JRadioButtonPanel<Boolean>(TRUE_FALSE);
        } else if (getMetaProperty().propertyType() == Boolean.class) {
            component = new JRadioButtonPanel<Boolean>(TRUE_FALSE_NULL);
        } else {
            throw new IllegalArgumentException("Unknown type: " + type);
        }
        setDisplayedAsMandatory(true);
        setComponent(component);
    }

    //-------------------------------------------------------------------------
    @Override
    public void updateUI(Bean bean) {
        Boolean value = (Boolean) getMetaProperty().get(bean);
        getComponent().setSelection(value);
    }

}
