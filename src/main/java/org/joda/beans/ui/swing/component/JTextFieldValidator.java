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
public class JTextFieldValidator {

    /**
     * Creates an instance.
     */
    protected JTextFieldValidator() {
    }

    //-------------------------------------------------------------------------
    /**
     * Validates a proposed edit.
     * <p>
     * This is intended to be used to block entry of certain characters into the field.
     * For example, blocking letters in a numeric field.
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
     * Checks the current status of the text.
     * <p>
     * This validates that the text is a complete value.
     * The input will never be empty during editing but can be empty on exit.
     * <p>
     * This is run on the EDT and must be fast and thread-safe.
     * Implementations must not access methods on the document.
     * 
     * @param text  the whole text of the field to validate, not null
     * @param onExit  true if exiting, false if editing
     * @return the error status, not null
     */
    protected ErrorStatus checkStatus(String text, boolean onExit) {
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
     * @param status  the current error status, not null
     * @return true if the text is a valid value for exit
     */
    protected String onExit(String text, ErrorStatus status) {
        return text;
    }

}
