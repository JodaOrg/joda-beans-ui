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
     * Lookup a text message by key and locale.
     * 
     * @param key  the key to lookup, not null
     * @param locale  the locale, not null
     * @return the text, not null
     */
    String lookup(String key, Locale locale);

    /**
     * Lookup a text message by key and locale, throwing an exception if not found.
     * 
     * @param key  the key to lookup, not null
     * @param locale  the locale, not null
     * @return the text, not null
     * @throws MissingResourceException if the resource is not found
     */
    String lookupRaw(String key, Locale locale);

}
