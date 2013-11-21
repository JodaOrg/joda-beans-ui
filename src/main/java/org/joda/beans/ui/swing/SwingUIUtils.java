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

import java.util.Locale;
import java.util.MissingResourceException;

import org.joda.beans.MetaProperty;
import org.joda.beans.ui.UIName;
import org.joda.beans.ui.form.UIComponent;

/**
 * Utilities for the Swing UI.
 */
public final class SwingUIUtils {

    /**
     * Restricted constructor.
     */
    private SwingUIUtils() {
    }

    //-------------------------------------------------------------------------
    /**
     * Gets the localized text for selection text.
     * 
     * @param type  the type of the selection, not null
     * @param value  the value to lookup, not null
     * @param format  true to apply a default humanizing format to the value
     * @return the text, not null
     */
    public static String lookupSelectionText(Class<?> type, String value, boolean format) {
        try {
            String key1 = type + "." + value + ".text";
            String key2 = type.getSimpleName() + "." + value + ".text";
            return SwingUISettings.INSTANCE.lookupResource(key1, key2);
        } catch (MissingResourceException ex) {
            if (value.length() <= 3) {
                return value.toUpperCase(Locale.US);
            }
            if (format) {
                return titleCase(value.toLowerCase(Locale.US).replace('_', ' '));
            }
            return value;
        }
    }

    //-------------------------------------------------------------------------
    /**
     * Gets the localized text for the label associated with the component.
     * 
     * @param component  the UI component, not null
     * @return the text, not null
     */
    public static String lookupFieldLabel(UIComponent<?> component) {
        String prompt = lookupFieldLabel(component.getMetaProperty());
        if (component.isDisplayedAsMandatory()) {
            return SwingUISettings.INSTANCE.lookupResourceUI("Prompt.mandatory.pattern", prompt);
        } else {
            return SwingUISettings.INSTANCE.lookupResourceUI("Prompt.optional.pattern", prompt);
        }
    }

    /**
     * Gets the localized text for the label associated with the property.
     * 
     * @param metaProperty  the metaProperty, not null
     * @return the text, not null
     */
    public static String lookupFieldLabel(MetaProperty<?> metaProperty) {
        UIName name = UIName.of(metaProperty);
        String prompt;
        try {
            String key1 = name.getFullName() + ".label";
            String key2 = name.getName() + ".label";
            prompt = SwingUISettings.INSTANCE.lookupResource(key1, key2);
        } catch (MissingResourceException ex2) {
            prompt = generateFieldPrompt(metaProperty);
        }
        return SwingUISettings.INSTANCE.lookupResourceUI("Prompt.pattern", prompt);
    }

    private static String generateFieldPrompt(MetaProperty<?> metaProperty) {
        String input = metaProperty.name().trim();
        if (input == null || input.length() == 0) {
            return "";
        }
        String format = "(?<=[A-Z])(?=[A-Z][a-z])|(?<=[^A-Z])(?=[A-Z])|(?<=[A-Za-z])(?=[^A-Za-z])";
        String split = input.replaceAll(format, " ");
        return titleCase(split);
    }

    private static String titleCase(String text) {
        if (text.isEmpty()) {
            return text;
        }
        return Character.toUpperCase(text.charAt(0)) + text.substring(1).toLowerCase(Locale.US);
    }

    //-------------------------------------------------------------------------
    /**
     * Lookup a resource value and add ellipsis three dots).
     * 
     * @param key  the resource key, not null
     * @return the value with ellipsis, not null
     */
    public static String lookupWithEllipsis(String key) {
        String base = SwingUISettings.INSTANCE.lookupResource(key);
        return SwingUISettings.INSTANCE.lookupResourceUI("Ellipsis.pattern", base);
    }

}
