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
import java.util.TreeMap;

import javax.swing.JComboBox;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.combobox.MapComboBoxModel;
import org.joda.beans.Bean;
import org.joda.beans.ui.form.MetaUIComponent;
import org.joda.beans.ui.swing.SwingUIComponent;
import org.joda.beans.ui.swing.SwingUIUtils;

/**
 * An instantiated Swing component for an enum.
 */
@SuppressWarnings({"rawtypes", "unchecked" })
public class EnumSwingUIComponent extends SwingUIComponent<JComboBox> {

    /**
     * The model.
     */
    private final MapComboBoxModel<String, Enum> model;

    /**
     * Creates an instance.
     * 
     * @param metaComponent  the meta-component, not null
     */
    public EnumSwingUIComponent(MetaUIComponent metaComponent) {
        super(metaComponent);
        Class<Enum> type = (Class<Enum>) metaComponent.getPropertyType();
        if (type.isEnum() == false) {
            throw new IllegalArgumentException("Unknown type: " + type);
        }
        Map<String, Enum> map = new TreeMap<>();
        for (Enum en : type.getEnumConstants()) {
            map.put(SwingUIUtils.lookupSelectionText(type, en.name(), true), en);
        }
        this.model = new MapComboBoxModel(map);
        JComboBox component = new JComboBox(model);
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
