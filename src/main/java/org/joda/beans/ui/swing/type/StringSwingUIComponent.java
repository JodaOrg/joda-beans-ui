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

import javax.swing.JTextField;

import org.joda.beans.Bean;
import org.joda.beans.ui.form.MetaUIComponent;
import org.joda.beans.ui.swing.SwingUIComponent;
import org.joda.beans.ui.swing.SwingUISettings;
import org.joda.beans.ui.swing.component.JValidatedTextFields;
import org.joda.beans.ui.swing.component.TextUndoManager;

/**
 * An instantiated Swing {@code JTextField}.
 */
public class StringSwingUIComponent extends SwingUIComponent<JTextField> {

    /**
     * Creates an instance.
     * 
     * @param metaComponent  the meta-component, not null
     */
    public StringSwingUIComponent(MetaUIComponent metaComponent) {
        super(metaComponent);
        JTextField component = JValidatedTextFields.createStringTextField(metaComponent.isMandatory(), metaComponent.getMaxSize());
        TextUndoManager.applyTo(component);
        setComponent(component);
    }

    //-------------------------------------------------------------------------
    @Override
    public void updateUI(Bean bean) {
        String text = getMetaProperty().getString(bean, SwingUISettings.INSTANCE.getStringConvert());
        getComponent().setText(text);
    }

}
