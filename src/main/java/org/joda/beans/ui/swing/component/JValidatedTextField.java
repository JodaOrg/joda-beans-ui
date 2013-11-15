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

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.util.Objects;

import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

/**
 * A text field that can validate itself.
 * <p>
 * This {@code JTextField} has additional functionality that allows it to
 * validate entry as you type and on exit.
 * Implementations should override one of the {@code process} or {@code validate} methods.
 * <p>
 * The aim is to provide something simpler than {@code JFormattedTextField}.
 */
public class JValidatedTextField extends JTextField {

    /** Serialization version. */
    private static final long serialVersionUID = 1L;
    /**
     * The error background color.
     */
    private static final Color ERROR_BACKGROUND = new Color(255, 219, 219);

    /**
     * Current error status.
     */
    private ErrorStatus errorStatus = ErrorStatus.VALID;
    /**
     * The validator to use, may be null.
     */
    private final JTextFieldValidator validator;
    /**
     * The filter.
     */
    private final ValidationDocumentFilter filter;

    /**
     * Creates an instance.
     */
    public JValidatedTextField() {
        this(0, null);
    }

    /**
     * Creates an instance.
     * <p>
     * This uses a {@code PlainDocument} which must not be changed.
     * 
     * @param columns  the number of columns to use as the preferred width, zero for natural behavior
     */
    public JValidatedTextField(int columns) {
        this(columns, null);
    }

    /**
     * Creates an instance.
     * <p>
     * This uses a {@code PlainDocument} which must not be changed.
     * 
     * @param validator  the validator to use, not null
     */
    public JValidatedTextField(JTextFieldValidator validator) {
        this(0, validator);
    }

    /**
     * Creates an instance.
     * <p>
     * This uses a {@code PlainDocument} which must not be changed.
     * 
     * @param columns  the number of columns to use as the preferred width, zero for natural behavior
     * @param validator  the validator to use, not null
     */
    public JValidatedTextField(int columns, JTextFieldValidator validator) {
        super(columns);
        this.validator = validator;
        this.filter = new ValidationDocumentFilter(this);
        AbstractDocument doc = (AbstractDocument) getDocument();
        doc.setDocumentFilter(filter);
    }

    //-------------------------------------------------------------------------
    // override to fix text on exit
    @Override
    protected void processFocusEvent(FocusEvent e) {
        if (e.isTemporary() == false && e.getID() == FocusEvent.FOCUS_LOST) {
            String text = Objects.toString(getText(), "");
            String fixed = handleExit(text);
            if (Objects.equals(text, fixed) == false) {
                AbstractDocument doc = (AbstractDocument) getDocument();
                doc.setDocumentFilter(null);
                setText(fixed);
                doc.setDocumentFilter(filter);
            }
        }
        super.processFocusEvent(e);
    }

    //-------------------------------------------------------------------------
    /**
     * Gets the error status.
     * 
     * @return the error status, not null
     */
    public ErrorStatus getErrorStatus() {
        return (errorStatus != null ? errorStatus : ErrorStatus.VALID);
    }

    /**
     * Sets the error status.
     * 
     * @param errorStatus  the error status, not null
     */
    public void setErrorStatus(ErrorStatus errorStatus) {
        this.errorStatus = Objects.requireNonNull(errorStatus);
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
    protected boolean validatedChange(String text) {
        return (validator != null ? validator.validateChange(text) : true);
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
    protected ErrorStatus validatedStatus(String text, boolean onExit) {
        return (validator != null ? validator.checkStatus(text, onExit) : ErrorStatus.VALID);
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
    protected String validatedExit(String text) {
        return (validator != null ? validator.onExit(text, getErrorStatus()) : text);
    }

    /**
     * Handles a change to the text value of the field.
     * <p>
     * This is used to color the background if the text is invalid.
     * Implementations could override this behavior.
     * <p>
     * This is run on the EDT and must be fast and thread-safe.
     * Implementations must not access methods on the document.
     * Implementations should override {@link #validatedStatus(String)} not this method.
     * 
     * @param text  the whole text of the field to validate, not null
     */
    protected void handleChange(String text) {
        ErrorStatus status = (text.isEmpty() ? ErrorStatus.VALID : validatedStatus(text, false));
        updateErrorStatus(status, false);
    }

    protected void updateErrorStatus(ErrorStatus status, boolean repaint) {
        if (status.equals(getErrorStatus()) == false) {
            setErrorStatus(status);
            String errorText = (status.isError() ? status.getErrorText() : null);
            setToolTipText(errorText);
            if (repaint) {
                repaint();
            }
        }
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
     * Implementations should override {@link #validatedExit(String)} not this method.
     * 
     * @param text  the whole text of the field to validate, not null
     * @return true if the text is a valid value for exit
     */
    protected String handleExit(String text) {
        ErrorStatus status = validatedStatus(text, true);
        updateErrorStatus(status, true);
        String resultText = validatedExit(text);
        if (Objects.equals(text, resultText) == false) {
            status = validatedStatus(resultText, true);
            updateErrorStatus(status, true);
        }
        return resultText;
    }

    //-------------------------------------------------------------------------
    // override background color to show error
    @Override
    public Color getBackground() {
        if (getErrorStatus().isError()) {
            return ERROR_BACKGROUND;
        }
        return super.getBackground();
    }

    //-------------------------------------------------------------------------
    /**
     * A filter used to validate text document models during editing.
     * <p>
     * This {@code DocumentFilter} can be used with a {@code JTextField} to
     * validate entry as you type.
     */
    static class ValidationDocumentFilter extends DocumentFilter {

        /**
         * The validator.
         */
        private final JValidatedTextField textField;

        /**
         * Creates an instance.
         * 
         * @param textField  the text field to use, not null
         */
        ValidationDocumentFilter(JValidatedTextField textField) {
            this.textField = Objects.requireNonNull(textField, "textField");
        }

        //-------------------------------------------------------------------------
        @Override
        public void insertString(FilterBypass fb, int offset, String inserted, AttributeSet attr) throws BadLocationException {
            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.insert(offset, inserted);
            if (textField.validatedChange(sb.toString())) {
                fb.insertString(offset, inserted, attr);
                textField.handleChange(getText(fb));
            } else {
                Toolkit.getDefaultToolkit().beep();
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.replace(offset, offset + length, text);
            if (textField.validatedChange(sb.toString())) {
                fb.replace(offset, length, text, attrs);
                textField.handleChange(getText(fb));
            } else {
                Toolkit.getDefaultToolkit().beep();
            }
        }

        @Override
        public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.delete(offset, offset + length);
            if (textField.validatedChange(sb.toString())) {
                fb.remove(offset, length);
                textField.handleChange(getText(fb));
            } else {
                Toolkit.getDefaultToolkit().beep();
            }
        }

        private String getText(FilterBypass fb) {
            try {
                return fb.getDocument().getText(0, fb.getDocument().getLength());
            } catch (BadLocationException ex) {
                return "";
            }
        }
    }

}
