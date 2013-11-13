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

import org.joda.beans.ui.swing.component.JValidatedTextField.TextFieldValidator;

/**
 * Utility class for Swing components.
 */
public final class JComponentUtils {

    /**
     * Restricted constructor.
     */
    private JComponentUtils() {
    }

    //-------------------------------------------------------------------------
    /**
     * Creates a {@code JTextField} that validates a {@code Byte} value.
     * 
     * @param allowEmpty  true to allow the empty string on exit,
     *  true for {@code Byte}, false for {@code byte}
     * @return the text field, not null
     */
    public static JValidatedTextField createByteTextField(final boolean allowEmpty) {
        return createLongTextField(allowEmpty, Byte.MIN_VALUE, Byte.MAX_VALUE);
    }

    /**
     * Creates a {@code JTextField} that validates a {@code Byte} value.
     * 
     * @param allowEmpty  true to allow the empty string on exit,
     *  true for {@code Byte}, false for {@code byte}
     * @param minInclusive  the minimum valid value, inclusive
     * @param maxInclusive  the maximum valid value, inclusive
     * @return the text field, not null
     */
    public static JValidatedTextField createByteTextField(final boolean allowEmpty, final byte minInclusive, final byte maxInclusive) {
        return createLongTextField(allowEmpty, minInclusive, maxInclusive);
    }

    //-------------------------------------------------------------------------
    /**
     * Creates a {@code JTextField} that validates a {@code Short} value.
     * 
     * @param allowEmpty  true to allow the empty string on exit,
     *  true for {@code Short}, false for {@code short}
     * @return the text field, not null
     */
    public static JValidatedTextField createShortTextField(final boolean allowEmpty) {
        return createLongTextField(allowEmpty, Short.MIN_VALUE, Short.MAX_VALUE);
    }

    /**
     * Creates a {@code JTextField} that validates a {@code Short} value.
     * 
     * @param allowEmpty  true to allow the empty string on exit,
     *  true for {@code Short}, false for {@code short}
     * @param minInclusive  the minimum valid value, inclusive
     * @param maxInclusive  the maximum valid value, inclusive
     * @return the text field, not null
     */
    public static JValidatedTextField createShortTextField(final boolean allowEmpty, final short minInclusive, final short maxInclusive) {
        return createLongTextField(allowEmpty, minInclusive, maxInclusive);
    }

    //-------------------------------------------------------------------------
    /**
     * Creates a {@code JTextField} that validates an {@code Integer} value.
     * 
     * @param allowEmpty  true to allow the empty string on exit,
     *  true for {@code Integer}, false for {@code int}
     * @return the text field, not null
     */
    public static JValidatedTextField createIntegerTextField(final boolean allowEmpty) {
        return createLongTextField(allowEmpty, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * Creates a {@code JTextField} that validates an {@code Integer} value.
     * 
     * @param allowEmpty  true to allow the empty string on exit,
     *  true for {@code Integer}, false for {@code int}
     * @param minInclusive  the minimum valid value, inclusive
     * @param maxInclusive  the maximum valid value, inclusive
     * @return the text field, not null
     */
    public static JValidatedTextField createIntegerTextField(final boolean allowEmpty, final int minInclusive, final int maxInclusive) {
        return createLongTextField(allowEmpty, minInclusive, maxInclusive);
    }

    //-------------------------------------------------------------------------
    /**
     * Creates a {@code JTextField} that validates a {@code Long} value.
     * 
     * @param allowEmpty  true to allow the empty string on exit,
     *  true for {@code Long}, false for {@code long}
     * @return the text field, not null
     */
    public static JValidatedTextField createLongTextField(final boolean allowEmpty) {
        return createLongTextField(allowEmpty, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * Creates a {@code JTextField} that validates a {@code Long} value.
     * 
     * @param allowEmpty  true to allow the empty string on exit,
     *  true for {@code Long}, false for {@code long}
     * @param minInclusive  the minimum valid value, inclusive
     * @param maxInclusive  the maximum valid value, inclusive
     * @return the text field, not null
     */
    public static JValidatedTextField createLongTextField(final boolean allowEmpty, final long minInclusive, final long maxInclusive) {
        TextFieldValidator validator = new TextFieldValidator() {
            @Override
            public boolean isValid(String text) {
                if (isMidEdit(text)) {
                    return true;
                }
                try {
                    long value = Long.parseLong(text);
                    if ((minInclusive > 0 && value >= 0 && value < minInclusive) ||
                            (maxInclusive < 0 && value <= 0 && value > maxInclusive)) {
                        return true;
                    }
                    if (value < minInclusive || value > maxInclusive) {
                        return false;
                    }
                    return true;
                } catch (NumberFormatException ex) {
                    return false;
                }
            }
            @Override
            public String fixOnExit(String text) {
                if (isMidEdit(text) || isValid(text) == false) {
                    if (allowEmpty) {
                        return "";
                    }
                    text = "0";
                }
                long value = Long.parseLong(text);
                if (value < minInclusive) {
                    return (allowEmpty ? "" : Long.toString(minInclusive));
                }
                if (value > maxInclusive) {
                    return (allowEmpty ? "" : Long.toString(maxInclusive));
                }
                return text;
            }
            private boolean isMidEdit(String text) {
                return text.isEmpty() || text.equals("-");
            }
        };
        return new JValidatedTextField(validator);
    }

    //-------------------------------------------------------------------------
    /**
     * Creates a {@code JTextField} that validates a {@code Double} value.
     * 
     * @param allowEmpty  true to allow the empty string on exit,
     *  true for {@code Double}, false for {@code double}
     * @return the text field, not null
     */
    public static JValidatedTextField createDoubleTextField(final boolean allowEmpty) {
        return createDoubleTextField(allowEmpty, true, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    /**
     * Creates a {@code JTextField} that validates a {@code Double} value.
     * 
     * @param allowEmpty  true to allow the empty string on exit,
     *  true for {@code Double}, false for {@code double}
     * @param allowNaN  true to allow NaN
     * @param minInclusive  the minimum valid value, inclusive
     * @param maxInclusive  the maximum valid value, inclusive
     * @return the text field, not null
     */
    public static JValidatedTextField createDoubleTextField(final boolean allowEmpty, final boolean allowNaN, final double minInclusive, final double maxInclusive) {
        TextFieldValidator validator = new TextFieldValidator() {
            @Override
            public boolean isValid(String text) {
                String upper = text.toUpperCase(Locale.US);
                if (upper.endsWith("F") || upper.endsWith("D")) {
                    text = text.substring(0, text.length() - 1);
                    upper = upper.substring(0, upper.length() - 1);
                    if (upper.endsWith("F") || upper.endsWith("D")) {
                        return false;
                    }
                }
                if (allowNaN && (text.equals("N") || text.equals("Na"))) {
                    return true;
                }
                if (text.isEmpty() || text.equals("-") || text.equals(".") || 
                        (upper.endsWith("E") && upper.endsWith("EE") == false) ||
                        (upper.endsWith("E-") && upper.endsWith("EE-") == false)) {
                    return true;
                }
                try {
                    double value = Double.parseDouble(text);
                    if ((minInclusive > 0 && value >= 0 && value < minInclusive) ||
                            (maxInclusive < 0 && value <= 0 && value > maxInclusive)) {
                        return true;
                    }
                    if (value < minInclusive || value > maxInclusive) {
                        return false;
                    }
                    return true;
                } catch (NumberFormatException ex) {
                    return false;
                }
            }
            @Override
            public String fixOnExit(String text) {
                if (text.equals("N") || text.equals("Na")) {
                    text = "NaN";
                }
                String upper = text.toUpperCase(Locale.US);
                if (upper.endsWith("E")) {
                    text = text.substring(0, text.length() - 1);
                }
                if (upper.endsWith("E-")) {
                    text = text.substring(0, text.length() - 2);
                }
                text = (text.toUpperCase(Locale.US).endsWith("D") ? text.substring(0, text.length() - 1) : text);
                text = (text.toUpperCase(Locale.US).endsWith("F") ? text.substring(0, text.length() - 1) : text);
                text = (text.endsWith(".0") ? text.substring(0, text.length() - 2) : text);
                double value;
                try {
                    value = Double.parseDouble(text);
                } catch (NumberFormatException ex) {
                    if (allowEmpty) {
                        return "";
                    }
                    value = 0;
                }
                if (value < minInclusive) {
                    return (allowEmpty ? "" : Double.toString(minInclusive));
                }
                if (value > maxInclusive) {
                    return (allowEmpty ? "" : Double.toString(maxInclusive));
                }
                return text.replace('E', 'e').replace(".0e", "e");
            }
        };
        return new JValidatedTextField(validator);
    }

}
