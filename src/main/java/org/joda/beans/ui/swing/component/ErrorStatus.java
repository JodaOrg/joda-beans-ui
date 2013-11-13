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

import java.util.Objects;

/**
 * The error status.
 */
public final class ErrorStatus {

    /**
     * Result indicating valid.
     */
    public static final ErrorStatus VALID = new ErrorStatus("");

    /**
     * The error key.
     */
    private final String errorKey;

    //-------------------------------------------------------------------------
    /**
     * Creates an error status.
     * 
     * @param errorKey  the key, not null
     * @return the result, not null
     */
    public static ErrorStatus of(String errorKey) {
        return new ErrorStatus(errorKey);
    }

    //-------------------------------------------------------------------------
    /**
     * Restricted constructor.
     * 
     * @param errorKey  the key, not null
     */
    private ErrorStatus(String errorKey) {
        this.errorKey = Objects.requireNonNull(errorKey, "errorKey");
    }

    //-------------------------------------------------------------------------
    /**
     * Checks if the status represents valid.
     * 
     * @return true if valid
     */
    public boolean isValid() {
        return errorKey.isEmpty();
    }

    /**
     * Checks if the status represents an error.
     * 
     * @return true if an error
     */
    public boolean isError() {
        return errorKey.length() > 0;
    }

    /**
     * Gets the error key.
     * 
     * @return the error key, empty if valid, not null
     */
    public String getErrorKey() {
        return errorKey;
    }

    /**
     * Gets the error text.
     * 
     * @return the error text, empty if valid, not null
     */
    public String getErrorText() {
        if (isError()) {
            return ComponentMsg.lookup(errorKey);
        }
        return "";
    }

    //-------------------------------------------------------------------------
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ErrorStatus) {
            ErrorStatus other = (ErrorStatus) obj;
            return errorKey.equals(other.errorKey);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return errorKey.hashCode();
    }

    public String toString() {
        return errorKey;
    }

}
