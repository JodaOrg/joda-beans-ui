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

/**
 * A validator that extracts the validation logic from {@code JValidatedTextField}}.
 */
public class JValidatedTextFieldValidator {

    /**
     * True if mandatory.
     */
    private final boolean mandatory;

    /**
     * Creates an instance.
     * 
     * @param mandatory  true if mandatory
     */
    public JValidatedTextFieldValidator(boolean mandatory) {
        this.mandatory = mandatory;
    }

    //-------------------------------------------------------------------------
    /**
     * Checks if the field is mandatory
     * 
     * @return true if mandatory
     */
    public final boolean isMandatory() {
        return mandatory;
    }

    /**
     * Checks if the text is a permitted state of the text field.
     * <p>
     * This can be used to block characters or strings, such as blocking letters
     * in a numeric field.
     * <p>
     * This is run on the EDT and must be fast and thread-safe.
     * Implementations must not access methods on the document.
     * 
     * @param text  the whole text of the field to validate, not null
     * @return true if the text is a valid value during editing
     */
    protected boolean validateChange(String text) {
        return true;
    }

    /**
     * Checks if the text is valid and adjusts the text field.
     * <p>
     * This validates that the text is complete and produces the intended value.
     * <p>
     * This is run on the EDT and must be fast and thread-safe.
     * Implementations must not access methods on the document.
     * 
     * @param text  the whole text of the field to validate, not null
     * @return the error status, not null
     */
    protected ErrorStatus validateExit(String text) {
        return ErrorStatus.VALID;
    }

    /**
     * Handles exit from the field.
     * <p>
     * This is used to adjust and correct the input value.
     * For example, an implementation could change the text to upper case.
     * <p>
     * There is no support for blocking exit of the field.
     * Implementations should either fix the value or accept that it stays invalid.
     * This method is invoked whether or not the text is invalid.
     * <p>
     * This is run on the EDT and must be fast and thread-safe.
     * Implementations must not access methods on the document.
     * 
     * @param text  the whole text of the field to validate, not null
     * @return true if the text is a valid value for exit
     */
    protected String onExit(String text) {
        return text;
    }

}
