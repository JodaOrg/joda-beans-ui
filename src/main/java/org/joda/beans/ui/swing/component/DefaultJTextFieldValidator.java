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

import java.util.regex.Pattern;

/**
 * A validator that extracts the validation logic from {@code JValidatedTextField}}.
 */
public class DefaultJTextFieldValidator extends JTextFieldValidator {

    /**
     * True if mandatory.
     */
    private final boolean mandatory;
    /**
     * The maximum length.
     */
    private final int maxLength;
    /**
     * The valid characters regex.
     */
    private final Pattern validChars;

    /**
     * Creates an instance.
     * 
     * @param mandatory  true if mandatory
     * @param maxLength  the maximum length allowed, inclusive, zero means no limit
     * @param validChars  the valid characters regex, not null
     */
    public DefaultJTextFieldValidator(boolean mandatory, int maxLength, Pattern validChars) {
        this.mandatory = mandatory;
        this.maxLength = (maxLength <= 0 ? 0 : maxLength);
        this.validChars = validChars;
    }

    //-------------------------------------------------------------------------
    /**
     * Checks if the field is mandatory.
     * 
     * @return true if mandatory
     */
    public boolean isMandatory() {
        return mandatory;
    }

    /**
     * Gets the maximum length allowed.
     * 
     * @return the maximum length, inclusive, zero means no limit
     */
    public int getMaxLength() {
        return maxLength;
    }

    /**
     * Gets the valid characters regex.
     * 
     * @return the valid characters regex, not null
     */
    public Pattern getValidCharacters() {
        return validChars;
    }

    //-------------------------------------------------------------------------
    @Override
    protected boolean validateChange(String text) {
        if (getMaxLength() > 0 && text.length() > getMaxLength()) {
            return false;
        }
        return getValidCharacters().matcher(text).matches();
    }

    @Override
    protected ErrorStatus checkStatus(String text) {
        if (text.isEmpty() && isMandatory()) {
            return ErrorStatus.MANDATORY;
        }
        return ErrorStatus.VALID;
    }

}
