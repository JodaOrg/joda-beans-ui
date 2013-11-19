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

import org.jdesktop.swingx.JXComboBox;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.combobox.MapComboBoxModel;
import org.joda.beans.Bean;
import org.joda.beans.ui.form.MetaUIComponent;
import org.joda.beans.ui.swing.SwingUIUtils;
import org.joda.beans.ui.swing.SwingUIComponent;

/**
 * An instantiated Swing component for a {@code TimeZone}.
 */
@SuppressWarnings({"rawtypes", "unchecked" })
public class TimeZoneSwingUIComponent extends SwingUIComponent<JXComboBox> {

    /**
     * The model.
     */
    private final MapComboBoxModel<String, TimeZone> model;

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
        this.model = new MapComboBoxModel(map);
        JXComboBox component = new JXComboBox(model);
        component.setEditable(metaComponent.isMandatory() == false);
        component.setSelectedIndex(-1);
        AutoCompleteDecorator.decorate(component);
        setComponent(component);
    }

    //-------------------------------------------------------------------------
    @Override
    public void updateUI(Bean bean) {
        Enum value = (Enum) getMetaProperty().get(bean);
        model.setSelectedItem(value);
    }

}
