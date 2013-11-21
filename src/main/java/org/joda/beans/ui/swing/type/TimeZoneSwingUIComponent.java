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

import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;

import javax.swing.JComboBox;

import org.joda.beans.Bean;
import org.joda.beans.ui.form.MetaUIComponent;
import org.joda.beans.ui.swing.SwingUIComponent;
import org.joda.beans.ui.swing.SwingUIUtils;
import org.joda.beans.ui.swing.component.JValidatedFields;

/**
 * An instantiated Swing component for a {@code TimeZone}.
 */
public class TimeZoneSwingUIComponent extends SwingUIComponent<JComboBox<TimeZone>> {

    /**
     * Creates an instance.
     * 
     * @param metaComponent  the meta-component, not null
     */
    public TimeZoneSwingUIComponent(MetaUIComponent metaComponent) {
        super(metaComponent);
        Class<?> type = metaComponent.getPropertyType();
        if (type != TimeZone.class) {
            throw new IllegalArgumentException("Unknown type: " + type);
        }
        Map<String, TimeZone> map = new TreeMap<>();
        for (String zoneId : TimeZone.getAvailableIDs()) {
            String text = SwingUIUtils.lookupSelectionText(TimeZone.class, zoneId, false);
            map.put(text, TimeZone.getTimeZone(zoneId));
        }
        JComboBox<TimeZone> component = JValidatedFields.createCombobox(map, metaComponent.isMandatory(), true);
        setComponent(component);
    }

    //-------------------------------------------------------------------------
    @Override
    public void updateUI(Bean bean) {
        TimeZone value = (TimeZone) getMetaProperty().get(bean);
        getComponent().getModel().setSelectedItem(value);
    }

}
