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

import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Common text fields.
 */
public final class JValidatedTextFields {

    /** Error message key. */
    private static final ErrorStatus ERROR_BYTE_INVALID = ErrorStatus.of("Error.Byte.invalid");
    /** Error message key. */
    private static final ErrorStatus ERROR_SHORT_INVALID = ErrorStatus.of("Error.Short.invalid");
    /** Error message key. */
    private static final ErrorStatus ERROR_INTEGER_INVALID = ErrorStatus.of("Error.Integer.invalid");
    /** Error message key. */
    private static final ErrorStatus ERROR_LONG_INVALID = ErrorStatus.of("Error.Long.invalid");

    /**
     * Restricted constructor.
     */
    private JValidatedTextFields() {
    }

    //-------------------------------------------------------------------------
    /**
     * Creates a {@code JTextField} that validates a {@code String} value.
     * 
     * @param mandatory  true if mandatory
     * @param maxLength  the maximum length of the text, one or more
     * @return the text field, not null
     */
    public static JValidatedTextField createStringTextField(final boolean mandatory, final int maxLength) {
        return new JValidatedTextField(new StringValidator(mandatory, maxLength));
    }

    //-------------------------------------------------------------------------
    /**
     * Creates a {@code JTextField} that validates a {@code Byte} value.
     * 
     * @param mandatory  true if mandatory
     * @return the text field, not null
     */
    public static JValidatedTextField createByteTextField(final boolean mandatory) {
        return createLongTextField(mandatory, Byte.MIN_VALUE, Byte.MAX_VALUE);
    }

    /**
     * Creates a {@code JTextField} that validates a {@code Byte} value.
     * 
     * @param mandatory  true if mandatory
     * @param minInclusive  the minimum valid value, inclusive
     * @param maxInclusive  the maximum valid value, inclusive
     * @return the text field, not null
     */
    public static JValidatedTextField createByteTextField(final boolean mandatory, final byte minInclusive, final byte maxInclusive) {
        return new JValidatedTextField(new IntegralValidator(ERROR_BYTE_INVALID, 4, mandatory, minInclusive, maxInclusive));
    }

    //-------------------------------------------------------------------------
    /**
     * Creates a {@code JTextField} that validates a {@code Short} value.
     * 
     * @param mandatory  true if mandatory
     * @return the text field, not null
     */
    public static JValidatedTextField createShortTextField(final boolean mandatory) {
        return createLongTextField(mandatory, Short.MIN_VALUE, Short.MAX_VALUE);
    }

    /**
     * Creates a {@code JTextField} that validates a {@code Short} value.
     * 
     * @param mandatory  true if mandatory
     * @param minInclusive  the minimum valid value, inclusive
     * @param maxInclusive  the maximum valid value, inclusive
     * @return the text field, not null
     */
    public static JValidatedTextField createShortTextField(final boolean mandatory, final short minInclusive, final short maxInclusive) {
        return new JValidatedTextField(new IntegralValidator(ERROR_SHORT_INVALID, 6, mandatory, minInclusive, maxInclusive));
    }

    //-------------------------------------------------------------------------
    /**
     * Creates a {@code JTextField} that validates an {@code Integer} value.
     * 
     * @param mandatory  true to allow the empty string on exit,
     *  true for {@code Integer}, false for {@code int}
     * @return the text field, not null
     */
    public static JValidatedTextField createIntegerTextField(final boolean mandatory) {
        return createLongTextField(mandatory, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * Creates a {@code JTextField} that validates an {@code Integer} value.
     * 
     * @param mandatory  true if mandatory
     * @param minInclusive  the minimum valid value, inclusive
     * @param maxInclusive  the maximum valid value, inclusive
     * @return the text field, not null
     */
    public static JValidatedTextField createIntegerTextField(final boolean mandatory, final int minInclusive, final int maxInclusive) {
        return new JValidatedTextField(new IntegralValidator(ERROR_INTEGER_INVALID, 11, mandatory, minInclusive, maxInclusive));
    }

    //-------------------------------------------------------------------------
    /**
     * Creates a {@code JTextField} that validates a {@code Long} value.
     * 
     * @param mandatory  true to allow the empty string on exit,
     *  true for {@code Long}, false for {@code long}
     * @return the text field, not null
     */
    public static JValidatedTextField createLongTextField(final boolean mandatory) {
        return createLongTextField(mandatory, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * Creates a {@code JTextField} that validates a {@code Long} value.
     * 
     * @param mandatory  true if mandatory
     * @param minInclusive  the minimum valid value, inclusive
     * @param maxInclusive  the maximum valid value, inclusive
     * @return the text field, not null
     */
    public static JValidatedTextField createLongTextField(final boolean mandatory, final long minInclusive, final long maxInclusive) {
        return new JValidatedTextField(new IntegralValidator(ERROR_LONG_INVALID, 20, mandatory, minInclusive, maxInclusive));
    }

    //-------------------------------------------------------------------------
    /**
     * Creates a {@code JTextField} that validates a {@code Double} value.
     * 
     * @param mandatory  true if mandatory
     * @return the text field, not null
     */
    public static JValidatedTextField createDoubleTextField(final boolean mandatory) {
        return createDoubleTextField(mandatory, true, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    /**
     * Creates a {@code JTextField} that validates a {@code Double} value.
     * 
     * @param mandatory  true if mandatory
     * @param allowNaN  true to allow NaN
     * @param minInclusive  the minimum valid value, inclusive
     * @param maxInclusive  the maximum valid value, inclusive
     * @return the text field, not null
     */
    public static JValidatedTextField createDoubleTextField(final boolean mandatory, final boolean allowNaN, final double minInclusive, final double maxInclusive) {
        return new JValidatedTextField(new DoubleValidator(mandatory, allowNaN, minInclusive, maxInclusive));
    }

    //-------------------------------------------------------------------------
    private static final class StringValidator extends DefaultJTextFieldValidator {
        /** Valid characters for double. */
        private static final Pattern VALID = Pattern.compile(".*");

        private StringValidator(boolean mandatory, int maxLength) {
            super(mandatory, maxLength, VALID);
        }
    }

    //-------------------------------------------------------------------------
    private static final class IntegralValidator extends DefaultJTextFieldValidator {
        /** Valid characters for integral. */
        private static final Pattern VALID = Pattern.compile("[0-9+-]*");

        /** The error to use when invalid. */
        private final ErrorStatus errorWhenInvalid;
        /** The minimum value. */
        private final long minInclusive;
        /** The maximum value. */
        private final long maxInclusive;

        private IntegralValidator(ErrorStatus errorWhenInvalid, int maxSize, boolean mandatory, long minInclusive, long maxInclusive) {
            super(mandatory, maxSize, VALID);
            this.errorWhenInvalid = errorWhenInvalid;
            this.minInclusive = minInclusive;
            this.maxInclusive = maxInclusive;
        }

        @Override
        protected ErrorStatus checkStatus(String text, boolean onExit) {
            if (onExit && text.isEmpty()) {
                return ErrorStatus.MANDATORY;
            }
            if (text.isEmpty() || text.equals("+") || text.equals("-")) {
                return ErrorStatus.VALID;
            }
            try {
                long value = Long.parseLong(text);
                if (value < minInclusive || value > maxInclusive) {
                    return ErrorStatus.range(minInclusive + " - " + maxInclusive);
                }
                return ErrorStatus.VALID;
            } catch (NumberFormatException ex) {
                return errorWhenInvalid;
            }
        }

        @Override
        protected String onExit(String text, ErrorStatus status) {
            if (status.isValid()) {
                if (text.isEmpty() || text.equals("+") || text.equals("-")) {
                    return "";
                }
                return text.replace("+", "");
            }
            return text;
        }
    }

    //-------------------------------------------------------------------------
    private static final class DoubleValidator extends DefaultJTextFieldValidator {
        /** Valid characters for double. */
        private static final Pattern VALID = Pattern.compile("[0-9eE.+-]*[fd]?");
        /** Error message key. */
        private static final ErrorStatus ERROR_INVALID = ErrorStatus.of("Error.Double.invalid");
        /** Error message key. */
        private static final ErrorStatus ERROR_NAN = ErrorStatus.of("Error.Double.nan");

        /** Whether to allow NaN. */
        private final boolean allowNaN;
        /** The minimum value. */
        private final double minInclusive;
        /** The maximum value. */
        private final double maxInclusive;

        private DoubleValidator(boolean mandatory, boolean allowNaN, double minInclusive, double maxInclusive) {
            super(mandatory, 128, VALID);
            this.minInclusive = minInclusive;
            this.maxInclusive = maxInclusive;
            this.allowNaN = allowNaN;
        }

        @Override
        protected boolean validateChange(String text) {
            if ("NaN".startsWith(text) || "Infinity".startsWith(text) || "-Infinity".startsWith(text)) {
                return true;
            }
            return super.validateChange(text);
        }

        @Override
        protected ErrorStatus checkStatus(String text, boolean onExit) {
            if (onExit && text.isEmpty()) {
                return ErrorStatus.MANDATORY;
            }
            if (allowNaN == false && "NaN".startsWith(text)) {
                return ERROR_NAN;
            }
            String upper = text.toUpperCase(Locale.US);
            if (text.isEmpty() || text.equals("+") || text.equals("-") || text.equals(".") ||
                    (upper.endsWith("E") && upper.endsWith("EE") == false) ||
                    "NaN".startsWith(text) || "Infinity".startsWith(text) || "-Infinity".startsWith(text)) {
                return ErrorStatus.VALID;
            }
            try {
                double value = Double.parseDouble(text);
                if (value < minInclusive || value > maxInclusive) {
                    return ErrorStatus.range(minInclusive + " - " + maxInclusive);
                }
                return ErrorStatus.VALID;
            } catch (NumberFormatException ex) {
                return ERROR_INVALID;
            }
        }

        @Override
        protected String onExit(String text, ErrorStatus status) {
            if (status.isValid()) {
                if (text.isEmpty() || text.equals("+") || text.equals("-") || text.equals(".")) {
                    return "";
                }
                if ("NaN".startsWith(text)) {
                    text = "NaN";
                }
                if ("Infinity".startsWith(text)) {
                    text = "Infinity";
                }
                if ("-Infinity".startsWith(text)) {
                    text = "-Infinity";
                }
                String upper = text.toUpperCase(Locale.US);
                text = (upper.endsWith("E") ? text.substring(0, text.length() - 1) : text);  // ignore incomplete trailing exponent
                text = (upper.endsWith("D") ? text.substring(0, text.length() - 1) : text);  // Java doubles can end in 'd'
                text = (upper.endsWith("F") ? text.substring(0, text.length() - 1) : text);  // Java doubles can end in 'f'
                text = (text.endsWith(".0") ? text.substring(0, text.length() - 2) : text);
                return text.replace('E', 'e').replace(".0e", "e").replace("+", "");
            }
            return text;
        }
    }

}
