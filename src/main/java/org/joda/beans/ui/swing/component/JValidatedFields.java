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
package org.joda.beans.ui.swing.component;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Map;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.combobox.MapComboBoxModel;

/**
 * Validated fields.
 */
public final class JValidatedFields {

    /**
     * Restricted constructor.
     */
    private JValidatedFields() {
    }

    //-------------------------------------------------------------------------
    /**
     * Applies mandatory validation to a {@code JComboBox}.
     * 
     * @param <T>  the data type
     * @param comboData  the combobox data, not null
     * @param mandatory  whether the combobox is mandatory
     * @param limitedValues  whether the combobox has limited values
     * @return the combobox, not null
     */
    public static <T> JComboBox<T> createCombobox(final Map<String, T> comboData, boolean mandatory, boolean limitedValues) {
        @SuppressWarnings("unchecked")
        ComboBoxModel<T> model = new MapComboBoxModel<String, T>(comboData);
        final JComboBox<T> component = new JComboBox<T>(model);
        component.setEditable((mandatory && limitedValues) == false);
        component.setSelectedIndex(-1);
        AutoCompleteDecorator.decorate(component);
        if (mandatory) {
            JValidatedFields.validateMandatory(component);
        }
        return component;
    }

    /**
     * Applies mandatory validation to a {@code JComboBox}.
     * 
     * @param comboBox  the combobox to update, not null
     */
    public static void validateMandatory(final JComboBox<?> comboBox) {
        // everything needs to work on the text field
        if (comboBox.getEditor().getEditorComponent() instanceof JTextField) {
            final JTextField editorText = (JTextField) comboBox.getEditor().getEditorComponent();
            editorText.setUI(new ErrorBackgroundTextUI(editorText.getUI(), comboBox));
            // add/clear error on leaving field
            editorText.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent ev) {
                    if (comboBox.getSelectedItem() == null || comboBox.getSelectedItem().toString().isEmpty()) {
                        SwingUtils.setErrorStatus(comboBox, ErrorStatus.MANDATORY);
                    } else {
                        SwingUtils.setErrorStatus(comboBox, ErrorStatus.VALID);
                    }
                }
            });
            // clear error if selection made
            comboBox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent ev) {
                    if (comboBox.getSelectedItem() != null && comboBox.getSelectedItem().toString().length() > 0) {
                        SwingUtils.setErrorStatus(comboBox, ErrorStatus.VALID);
                    }
                }
            });
        }
    }

}
