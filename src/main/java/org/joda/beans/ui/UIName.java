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

import java.util.Objects;

import org.joda.beans.MetaProperty;

/**
 * The name of a UI element.
 */
public final class UIName {

    /**
     * The standard name.
     */
    private final String name;
    /**
     * The full name.
     */
    private final String fullName;

    //-------------------------------------------------------------------------
    /**
     * Obtains a name instance.
     * 
     * @param metaProperty  the meta-property, not null
     * @return the name, not null
     */
    public static UIName of(MetaProperty<?> metaProperty) {
        String name = metaProperty.declaringType().getSimpleName() + "." + metaProperty.name();
        String fullName = metaProperty.declaringType().getName() + "." + metaProperty.name();
        return new UIName(name, fullName);
    }

    /**
     * Obtains a name instance.
     * 
     * @param name  the name, not null
     * @param fullName  the full name, not null
     * @return the name, not null
     */
    public static UIName of(String name, String fullName) {
        return new UIName(name, fullName);
    }

    //-------------------------------------------------------------------------
    /**
     * Creates an instance.
     * 
     * @param name  the name, not null
     * @param fullName  the full name, not null
     */
    private UIName(String name, String fullName) {
        this.name = Objects.requireNonNull(name, "name");
        this.fullName = Objects.requireNonNull(fullName, "fullName");
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the name.
     * 
     * @return the name, not null
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the full name.
     * 
     * @return the full name, not null
     */
    public String getFullName() {
        return fullName;
    }

    //-------------------------------------------------------------------------
    @Override
    public String toString() {
        return name;
    }

}
