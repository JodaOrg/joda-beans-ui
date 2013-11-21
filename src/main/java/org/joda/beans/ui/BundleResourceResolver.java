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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Resolves requests for localized text using a {@code ResourceBundle}.
 */
public final class BundleResourceResolver implements ResourceResolver {

    /**
     * The name of the application bundle.
     */
    public static final String APP_BUNDLE_NAME = "org.joda.beans.ui.AppResources";
    /**
     * The name of the default bundle.
     */
    public static final String DEFAULT_BUNDLE_NAME = "org.joda.beans.ui.DefaultResources";
    /**
     * Default resource bundle.
     */
    public static final ResourceResolver DEFAULT = BundleResourceResolver.of(APP_BUNDLE_NAME, DEFAULT_BUNDLE_NAME);

    /**
     * The bundle names.
     */
    private final List<String> bundleNames;

    //-------------------------------------------------------------------------
    /**
     * Obtains a resolver trying a single bundle name.
     * 
     * @param bundleName  the name to query, not null
     * @return the resolver, not null
     */
    public static BundleResourceResolver of(String bundleName) {
        return new BundleResourceResolver(Collections.singletonList(bundleName));
    }

    /**
     * Obtains a resolver trying each of an array of bundle names in turn.
     * 
     * @param bundleNames  the array of names to query, not null
     * @return the resolver, not null
     */
    public static BundleResourceResolver of(String... bundleNames) {
        List<String> list = new ArrayList<String>();
        for (String bundleName : bundleNames) {
            list.add(Objects.requireNonNull(bundleName, "bundleName"));
        }
        return new BundleResourceResolver(list);
    }

    /**
     * Obtains a resolver trying each of a list of bundle names in turn.
     * 
     * @param bundleNames  the names to query, not null
     * @return the resolver, not null
     */
    public static BundleResourceResolver of(List<String> bundleNames) {
        return new BundleResourceResolver(bundleNames);
    }

    //-------------------------------------------------------------------------
    /**
     * Restricted constructor.
     * 
     * @param bundleNames  the names to query, not null
     */
    private BundleResourceResolver(List<String> bundleNames) {
        this.bundleNames = Objects.requireNonNull(bundleNames, "bundleNames");
    }

    //-------------------------------------------------------------------------
    @Override
    public String lookup(Locale locale, String... keys) {
        String result = doLookup(locale, keys);
        if (result == null) {
            throw new MissingResourceException(
                    "Unable to find resource for key: " + keys[keys.length - 1],
                    bundleNames.toString(), Arrays.toString(keys));
        }
        return result;
    }

    @Override
    public String lookupUI(Locale locale, String... keys) {
        String result = doLookup(locale, keys);
        if (result == null) {
            return '!' + keys[keys.length - 1] + '!';
        }
        return result;
    }

    private String doLookup(Locale locale, String... keys) {
        Objects.requireNonNull(keys, "keys");
        if (keys.length == 0) {
            throw new IllegalArgumentException("No keys specified");
        }
        for (String key : keys) {
            for (String bundleName : bundleNames) {
                try {
                    return ResourceBundle.getBundle(bundleName, locale).getString(key);
                } catch (MissingResourceException ex) {
                    continue;
                }
            }
        }
        return null;
    }

    //-------------------------------------------------------------------------
    @Override
    public String toString() {
        return "ResourceMsgResolver" + bundleNames;
    }

}
