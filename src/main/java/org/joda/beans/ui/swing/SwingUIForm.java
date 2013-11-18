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
package org.joda.beans.ui.swing;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.joda.beans.ui.form.MetaUIComponent;
import org.joda.beans.ui.form.MetaUIForm;
import org.joda.beans.ui.form.UIForm;
import org.joda.beans.ui.swing.component.SwingFormPanelBuilder;

/**
 * An instantiated Swing UI component.
 */
public class SwingUIForm extends UIForm<JPanel> {

    /**
     * The UI component for the whole form.
     */
    private JPanel form;
    /**
     * The components that are part of the form.
     */
    private final List<SwingUIComponent<?>> components = new ArrayList<>();

    /**
     * Creates an instance.
     * 
     * @param metaForm  the meta-form, not null
     */
    protected SwingUIForm(MetaUIForm metaForm) {
        super(metaForm);
        SwingFormPanelBuilder builder = new SwingFormPanelBuilder();
        for (MetaUIComponent metaComp : metaForm.getComponents()) {
            if (metaComp.getComponentFactory() != null) {
                SwingUIComponent<?> comp = (SwingUIComponent<?>) metaComp.getComponentFactory().createComponent(metaComp);
                String name = ApplicationMsg.lookupFieldPrompt(comp);
                builder.append(name, comp.getComponent());
                getComponents().add(comp);
            }
        }
        JPanel panel = builder.build();
        this.form = panel;
    }

    //-------------------------------------------------------------------------
    /**
     * Gets the UI form.
     * 
     * @return the UI form, null if not yet created
     */
    public JPanel getForm() {
        return form;
    }

    /**
     * Gets the instantiated components.
     * 
     * @return the UI components, editable, not null
     */
    public List<SwingUIComponent<?>> getComponents() {
        return components;
    }

}
