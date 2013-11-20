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

import java.util.List;

import org.joda.beans.Bean;
import org.joda.beans.ui.form.MetaUIComponent;
import org.joda.beans.ui.swing.SwingUIComponent;
import org.joda.beans.ui.swing.SwingUISettings;
import org.joda.beans.ui.swing.SwingUIUtils;
import org.joda.beans.ui.swing.component.JEditableListPanel;

/**
 * An instantiated Swing component for a string.
 */
public class StringListSwingUIComponent extends SwingUIComponent<JEditableListPanel<String>> {

    /**
     * Creates an instance.
     * 
     * @param metaComponent  the meta-component, not null
     */
    public StringListSwingUIComponent(MetaUIComponent metaComponent) {
        super(metaComponent);
        JEditableListPanel<String> component = new JEditableListPanel<>();
        component.getAddButton().setText(SwingUIUtils.lookupWithEllipsis("Add.button"));
        component.getEditButton().setText(SwingUIUtils.lookupWithEllipsis("Edit.button"));
        component.getRemoveButton().setText(SwingUISettings.INSTANCE.lookupResource("Remove.button"));
        setComponent(component);
    }

    //-------------------------------------------------------------------------
    @Override
    @SuppressWarnings("unchecked")
    public void updateUI(Bean bean) {
        final List<String> list = (List<String>) getMetaProperty().get(bean);
        getComponent().getData().addAll(list);
    }

}
