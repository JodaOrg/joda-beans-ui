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
package org.joda.beans.ui;

import java.util.Locale;
import java.util.MissingResourceException;

/**
 * Resolves requests for localized text.
 */
public interface ResourceResolver {

    /**
     * Lookup a text message using a set of keys and locale throwing an exception if not found.
     * <p>
     * This throws an exception if the key is not found.
     * 
     * @param locale  the locale, not null
     * @param keys  the keys to lookup, not null
     * @return the resource value, not null
     * @throws MissingResourceException if the resource is not found
     */
    String lookup(Locale locale, String... keys);

    /**
     * Lookup a text message using a set of keys and locale for a UI.
     * <p>
     * This returns a default value if the key is not found.
     * 
     * @param locale  the locale, not null
     * @param keys  the keys to lookup, not null
     * @return the resource value, not null
     */
    String lookupUI(Locale locale, String... keys);

}
