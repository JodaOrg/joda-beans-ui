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

import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.util.Objects;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

/**
 * A text field that can validate itself.
 * <p>
 * This {@code JTextField} has additional functionality that allows it to
 * validate entry as you type and on exit.
 * <p>
 * The aim is to provide something simpler than {@code JFormattedTextField}.
 */
public class JValidatedTextField extends JTextField {

    /** Serialization version. */
    private static final long serialVersionUID = 1L;

    /**
     * The validator.
     */
    private final TextFieldValidator validator;

    //-------------------------------------------------------------------------
    /**
     * Creates an instance.
     * 
     * @param validator  the validator to use, not null
     */
    public JValidatedTextField(TextFieldValidator validator) {
        this.validator = Objects.requireNonNull(validator, "validator");
        setText("");
        PlainDocument doc = (PlainDocument) getDocument();
        doc.setDocumentFilter(new WholeTextDocumentFilter(validator));
    }

    //-------------------------------------------------------------------------
    // override to fix text on exit
    @Override
    protected void processFocusEvent(FocusEvent e) {
        if (e.isTemporary() == false && e.getID() == FocusEvent.FOCUS_LOST) {
            String text = getText();
            String fixed = validator.fixOnExit(text);
            if (Objects.equals(text, fixed) == false) {
                setText(fixed);
            }
        }
        super.processFocusEvent(e);
    }

    //-------------------------------------------------------------------------
    /**
     * Interface to check if the whole text is valid.
     */
    public interface TextFieldValidator {

        /**
         * Checks if the text is valid.
         * <p>
         * This method may be slightly lenient.
         * For example, it should normally allow a blank string to aid user editing.
         * <p>
         * This is run on the EDT and must be fast, thread-safe and idempotent.
         * 
         * @param text  the text to check, not null
         * @return true if valid
         */
        boolean isValid(String text);

        /**
         * Fixes the text on exit.
         * <p>
         * This takes text which is known to be invalid and must return
         * alternate text that to be stored in the component.
         * The result of this method must be valid as per {@link #isValid}.
         * <p>
         * This is run on the EDT and must be fast, thread-safe and idempotent.
         * 
         * @param text  the text to fix, not null
         * @return the fixed text, not null
         */
        String fixOnExit(String text);

    }

    //-------------------------------------------------------------------------
    /**
     * A filter used to validate text document models during editing.
     * <p>
     * This {@code DocumentFilter} can be used with a {@code JTextField} to
     * validate entry as you type.
     */
    static class WholeTextDocumentFilter extends DocumentFilter {

        /**
         * The validator.
         */
        private final TextFieldValidator validator;

        /**
         * Creates an instance.
         * 
         * @param validator  the validator to use, not null
         */
        WholeTextDocumentFilter(TextFieldValidator validator) {
            this.validator = Objects.requireNonNull(validator, "validator");
        }

        //-------------------------------------------------------------------------
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.insert(offset, string);
            if (validator.isValid(sb.toString())) {
                super.insertString(fb, offset, string, attr);
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
            if (validator.isValid(sb.toString())) {
                super.replace(fb, offset, length, text, attrs);
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
            if (validator.isValid(sb.toString())) {
                super.remove(fb, offset, length);
            } else {
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }

}
