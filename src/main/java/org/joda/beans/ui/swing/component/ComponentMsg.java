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

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Access localized text.
 */
public final class ComponentMsg {

    /**
     * The bundle file.
     */
    private static final String BUNDLE_NAME = "org.joda.beans.ui.swing.component.ComponentMsg";
    /**
     * The loaded bundle.
     */
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    /**
     * Restricted constructor.
     */
    private ComponentMsg() {
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

}
