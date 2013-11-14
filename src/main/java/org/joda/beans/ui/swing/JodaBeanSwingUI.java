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

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.joda.beans.Bean;
import org.joda.beans.ui.form.MetaUIComponent;
import org.joda.beans.ui.form.MetaUIFactory;
import org.joda.beans.ui.form.MetaUIForm;
import org.joda.beans.ui.swing.binder.PropertyBinder;
import org.joda.beans.ui.swing.binder.PropertyBinderFactory;

/**
 * Entry point factory class capable of creating a Swing UI for a bean.
 */
public class JodaBeanSwingUI {

    public JPanel createReadOnly(final Bean bean) {
        MetaUIForm form = new MetaUIFactory().createForm(bean.metaBean());
        SwingFormPanelBuilder builder = new SwingFormPanelBuilder();
        for (MetaUIComponent comp : form.getComponents()) {
            JComponent field = createField(comp, bean);
            if (field != null) {
                String name = DisplayMsg.lookupFieldPrompt(comp);
                builder.append(name, field);
            }
        }
        return builder.build();
    }

    private JComponent createField(MetaUIComponent metaComponent, final Bean bean) {
        Object value = metaComponent.getMetaProperty().get(bean);
        for (PropertyBinderFactory factory : SwingUISettings.INSTANCE.getFactories()) {
            PropertyBinder binder = factory.createBinder(metaComponent);
            if (binder != null) {
                binder.updateUI(bean);
                return binder.getComponentList().get(0);
            }
        }
        if (Bean.class.isAssignableFrom(metaComponent.getPropertyType())) {
            return new JLabel(value.toString());
        } else {
            return null;
            //throw new IllegalArgumentException("Unable to create UI as declared type is neither a bean nor a simple type: " + mp.propertyType().getName());
        }
    }

}
