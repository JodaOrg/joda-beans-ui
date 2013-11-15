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

import java.util.Map;

import javax.swing.JPanel;

import org.joda.beans.Bean;
import org.joda.beans.ui.form.MetaUIComponent;
import org.joda.beans.ui.form.MetaUIFactory;
import org.joda.beans.ui.form.MetaUIForm;
import org.joda.beans.ui.form.UIComponentFactory;
import org.joda.beans.ui.swing.component.SwingFormPanelBuilder;

/**
 * Entry point factory class capable of creating a Swing UI for a bean.
 */
public class JodaBeanSwingUI {

    /**
     * Creates a form for the specified bean.
     * <p>
     * The bean is examined for meta-data and a form created.
     * 
     * @param bean  the bean to examine, not null
     * @return the created form panel, not null
     */
    public JPanel createForm(final Bean bean) {
        MetaUIForm metaForm = createMetaForm(bean);
        selectSwingComponents(bean, metaForm);
        JPanel form = createSwingForm(bean, metaForm);
        return form;
    }

    protected MetaUIForm createMetaForm(Bean bean) {
        return new MetaUIFactory().createForm(bean.metaBean());
    }

    protected void selectSwingComponents(Bean bean, MetaUIForm form) {
        Map<Class<?>, UIComponentFactory> factories = SwingUISettings.INSTANCE.getFactories();
        for (MetaUIComponent comp : form.getComponents()) {
            UIComponentFactory factory = factories.get(comp.getMetaProperty().propertyType());
            comp.setComponentFactory(factory);
        }
    }

    protected JPanel createSwingForm(Bean bean, MetaUIForm form) {
        SwingFormPanelBuilder builder = new SwingFormPanelBuilder();
        for (MetaUIComponent metaComp : form.getComponents()) {
            if (metaComp.getComponentFactory() != null) {
                SwingUIComponent<?> comp = (SwingUIComponent<?>) metaComp.getComponentFactory().createComponent(metaComp);
                String name = ApplicationMsg.lookupFieldPrompt(metaComp);
                builder.append(name, comp.getComponent());
                comp.updateUI(bean);
            }
        }
        return builder.build();
    }

}
