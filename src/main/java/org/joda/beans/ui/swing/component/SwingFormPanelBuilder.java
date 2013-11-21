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

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Builder for creating a panel for a form.
 */
public class SwingFormPanelBuilder {

    private final JPanel panel;
    private final GroupLayout layout;
    private final SequentialGroup hGroup;
    private final ParallelGroup hGroupLabels;
    private final ParallelGroup hGroupFields;
    private final SequentialGroup vGroup;

    /**
     * Creates an instance.
     */
    public SwingFormPanelBuilder() {
        panel = new JPanel();
        layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        hGroup = layout.createSequentialGroup();
        vGroup = layout.createSequentialGroup();
        hGroupLabels = layout.createParallelGroup();
        hGroupFields = layout.createParallelGroup();
    }

    //-------------------------------------------------------------------------
    /**
     * Appends a label and field to the builder.
     * 
     * @param label  the label text, not null
     * @param field  the field, not null
     * @return {@code this}, for chaining
     */
    public SwingFormPanelBuilder append(String label, JComponent field) {
        return append(new JLabel(label), field);
    }

    /**
     * Appends a label and field to the builder.
     * 
     * @param label  the label, not null
     * @param field  the field, not null
     * @return {@code this}, for chaining
     */
    public SwingFormPanelBuilder append(JComponent label, JComponent field) {
        if (label instanceof JLabel) {
            ((JLabel) label).setLabelFor(field);
        }
        hGroupLabels.addComponent(label);
        hGroupFields.addComponent(field);
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(label).addComponent(field));
        return this;
    }

    /**
     * Builds the panel.
     * 
     * @return the panel, not null
     */
    public JPanel build() {
        hGroup.addGroup(hGroupLabels);
        hGroup.addGroup(hGroupFields);
        layout.setHorizontalGroup(hGroup);
        layout.setVerticalGroup(vGroup);
        return panel;
    }

}
