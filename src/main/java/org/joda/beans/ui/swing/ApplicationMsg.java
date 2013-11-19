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
import java.util.ResourceBundle;

import org.joda.beans.MetaProperty;
import org.joda.beans.ui.form.UIComponent;
import org.joda.beans.ui.form.UIName;

/**
 * Access localized text at the application level.
 */
public final class ApplicationMsg {

    /**
     * The bundle file.
     */
    private static final String BUNDLE_NAME = "org.joda.beans.ui.swing.ApplicationMsg";
    /**
     * The loaded bundle.
     */
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    /**
     * Restricted constructor.
     */
    private ApplicationMsg() {
    }

    //-------------------------------------------------------------------------
    /**
     * Gets the localized text for the key.
     * 
     * @param key  the key, not null
     * @return the text, not null
     */
    public static String lookup(String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
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
            String key = type + "." + value + ".text";
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException ex) {
            try {
                String key = type.getSimpleName() + "." + value + ".text";
                return RESOURCE_BUNDLE.getString(key);
            } catch (MissingResourceException ex2) {
                if (value.length() <= 3) {
                    return value.toUpperCase(Locale.US);
                }
                if (format) {
                    return titleCase(value.toLowerCase(Locale.US).replace('_', ' '));
                }
                return value;
            }
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
            prompt += "*";
        }
        return prompt;
    }

    /**
     * Gets the localized text for the label associated with the property.
     * 
     * @param metaProperty  the metaProperty, not null
     * @return the text, not null
     */
    public static String lookupFieldLabel(MetaProperty<?> metaProperty) {
        UIName name = UIName.of(metaProperty);
        try {
            String key = name.getFullName() + ".label";
            String prompt = RESOURCE_BUNDLE.getString(key);
            return (prompt.endsWith(":") ? prompt : prompt + ":");
        } catch (MissingResourceException ex) {
            try {
                String key = name.getName() + ".label";
                String prompt = RESOURCE_BUNDLE.getString(key);
                return (prompt.endsWith(":") ? prompt : prompt + ":");
            } catch (MissingResourceException ex2) {
                return generateFieldPrompt(metaProperty) + ":";
            }
        }
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

}
