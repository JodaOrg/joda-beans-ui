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

import java.util.Calendar;
import java.util.Date;

import org.jdesktop.swingx.JXDatePicker;
import org.joda.beans.Bean;
import org.joda.beans.ui.form.MetaUIComponent;
import org.joda.beans.ui.swing.SwingUIComponent;
import org.joda.beans.ui.swing.SwingUISettings;

/**
 * An instantiated Swing component for a date.
 */
public class DateSwingUIComponent extends SwingUIComponent<JXDatePicker> {

    /**
     * Creates an instance.
     * 
     * @param metaComponent  the meta-component, not null
     */
    public DateSwingUIComponent(MetaUIComponent metaComponent) {
        super(metaComponent);
        Class<?> type = (Class<?>) metaComponent.getPropertyType();
        if (Calendar.class.isAssignableFrom(type) == false && Date.class.isAssignableFrom(type) == false) {
            throw new IllegalArgumentException("Unknown type: " + type);
        }
        JXDatePicker component = new JXDatePicker(SwingUISettings.INSTANCE.getLocale());
        component.setTimeZone(SwingUISettings.INSTANCE.getTimeZone());
        setComponent(component);
    }

    //-------------------------------------------------------------------------
    @Override
    public void updateUI(Bean bean) {
        Object value = getMetaProperty().get(bean);
        if (value instanceof Calendar) {
            getComponent().setDate(((Calendar) value).getTime());
        } else if (value instanceof Date) {
            getComponent().setDate((Date) value);
        } else {
            getComponent().setDate(null);
        }
    }

}
