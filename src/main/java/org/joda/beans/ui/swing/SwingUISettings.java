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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import org.joda.beans.ui.swing.binder.BooleanObjectPropertyBinder;
import org.joda.beans.ui.swing.binder.BooleanPrimitivePropertyBinder;
import org.joda.beans.ui.swing.binder.PropertyBinderFactory;
import org.joda.beans.ui.swing.binder.StringPropertyBinder;
import org.joda.convert.StringConvert;

/**
 * Settings for the Swing UI.
 */
public class SwingUISettings {

    /**
     * The singleton settings.
     */
    public static final SwingUISettings INSTANCE = new SwingUISettings();

    /**
     * The factories.
     */
    private final List<PropertyBinderFactory> factories = new ArrayList<>();
    /**
     * The string converter.
     */
    private StringConvert convert = StringConvert.create();
    /**
     * The locale.
     */
    private Locale locale = Locale.getDefault();

    //-------------------------------------------------------------------------
    /**
     * Creates an instance.
     */
    public SwingUISettings() {
        factories.add(BooleanPrimitivePropertyBinder.FACTORY);
        factories.add(BooleanObjectPropertyBinder.FACTORY);
        factories.add(StringPropertyBinder.FACTORY);
    }

    /**
     * Creates an instance.
     * 
     * @param other  the settings to clone, not null
     */
    private SwingUISettings(SwingUISettings other) {
        this.factories.addAll(other.factories);
        this.convert = other.convert;
        this.locale = other.locale;
    }

    //-------------------------------------------------------------------------
    /**
     * Gets the list of factories, which can be edited.
     * 
     * @return the editable list of factories, not null
     */
    public List<PropertyBinderFactory> getFactories() {
        return factories;
    }

    /**
     * Gets the string converter, which can be edited.
     * 
     * @return the converter, not null
     */
    public StringConvert getStringConvert() {
        return convert;
    }

    /**
     * Sets the string converter.
     * 
     * @param convert  the converter to set, not null
     */
    public void setStringConvert(StringConvert convert) {
        this.convert = Objects.requireNonNull(convert, "convert");
    }

    /**
     * Gets the locale.
     * 
     * @return the locale, not null
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * Sets the locale.
     * 
     * @param locale  the locale to set, not null
     */
    public void setLocale(Locale locale) {
        this.locale = Objects.requireNonNull(locale, "locale");
    }

    //-------------------------------------------------------------------------
    /**
     * Clones these settings.
     * 
     * @return the clone, not null
     */
    @Override
    public SwingUISettings clone() {
        return new SwingUISettings(this);
    }

}
