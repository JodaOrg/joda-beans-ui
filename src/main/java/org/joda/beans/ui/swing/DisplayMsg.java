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
import org.joda.beans.ui.form.MetaUIComponent;

/**
 * Access localized text.
 */
public final class DisplayMsg {

    /**
     * The bundle file.
     */
    private static final String BUNDLE_NAME = "org.joda.beans.ui.swing.DisplayMsg";
    /**
     * The loaded bundle.
     */
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    /**
     * Restricted constructor.
     */
    private DisplayMsg() {
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
     * Gets the localized text for the UI component.
     * 
     * @param component  the UI component, not null
     * @return the text, not null
     */
    public static String lookupFieldPrompt(MetaUIComponent component) {
        String prompt = lookupFieldPrompt(component.getMetaProperty());
        if (component.isMandatory()) {
            prompt += "*";
        }
        return prompt;
    }

    /**
     * Gets the localized text for the meta-property.
     * 
     * @param metaProperty  the metaProperty, not null
     * @return the text, not null
     */
    public static String lookupFieldPrompt(MetaProperty<?> metaProperty) {
        try {
            String key = metaProperty.declaringType() + "." + metaProperty.name();
            String prompt = RESOURCE_BUNDLE.getString(key);
            return (prompt.endsWith(":") ? prompt : prompt + ":");
        } catch (MissingResourceException ex) {
            try {
                String key = metaProperty.declaringType().getSimpleName() + "." + metaProperty.name();
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
        return Character.toUpperCase(split.charAt(0)) + split.substring(1).toLowerCase(Locale.US);
    }

}
