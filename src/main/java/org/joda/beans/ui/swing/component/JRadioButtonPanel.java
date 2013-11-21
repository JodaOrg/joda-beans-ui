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

import java.awt.AWTError;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * A panel that contains radio buttons.
 * <p>
 * This {@code JPanel} is an easy way to produce a linked set of radio buttons.
 * The buttons will be correctly initialized into a {@code ButtonGroup}.
 * <p>
 * Each radio button is linked to a domain object and it is the domain object
 * that can be set and queried on the panel.
 * Pass the map of domain object to display text into the constructor.
 * It is also possible to add radio buttons one at a time setting the constraint
 * to be the domain object.
 * <p>
 * By default, the layout will place two or three buttons on the same
 * line. If there are four buttons, there are two rows of two. Five or
 * more will be arranged vertically. This behaviour can be overridden
 * by the columns property.
 * 
 * @param <T>  the type of the data being displayed
 */
public class JRadioButtonPanel<T> extends JPanel {

    /** Serialization version. */
    private static final long serialVersionUID = 1L;
    /** Masked null. */
    private static final String NULL = "";

    /**
     * The button group.
     */
    private final ButtonGroup buttonGroup = new ButtonGroup();
    /**
     * The map from key to button.
     */
    private final Map<T, JRadioButton> keyToButtonMap = new HashMap<>();
    /**
     * The map from model to key.
     */
    private final Map<ButtonModel, T> modelToKeyMap = new HashMap<>();

    /**
     * Creates an instance.
     */
    public JRadioButtonPanel() {
        super();
        setLayout(new RadioButtonLayout(this, -1));
    }

    /**
     * Creates an instance.
     * <p>
     * The map should typically be ordered.
     * The key may be any immutable value type object, such as {@code Boolean} or an {@code Enum}.
     * 
     * @param keyToText  the map from data key to display text for each radio button, not null
     */
    public JRadioButtonPanel(Map<T, String> keyToText) {
        super();
        init(keyToText);
    }

    /**
     * Creates an instance.
     * <p>
     * The map should typically be ordered.
     * The key may be any immutable value type object, such as {@code Boolean} or an {@code Enum}.
     * Null may be used as a key.
     * 
     * @param keyToText  the map from data key to display text for each radio button, not null
     * @param selectedKey  the selected key, null if no selection
     */
    public JRadioButtonPanel(Map<T, String> keyToText, T selectedKey) {
        super();
        init(keyToText);
        setSelection(selectedKey);
    }

    private void init(Map<T, String> keyToText) {
        Objects.requireNonNull(keyToText, "keyToText");
        setBorder(BorderFactory.createEmptyBorder());
        setLayout(new RadioButtonLayout(this, -1));
        int i = 0;
        for (Entry<T, String> entry : keyToText.entrySet()) {
            Object key = maskNull(entry.getKey());
            Objects.requireNonNull(entry.getValue(), "keyToText[" + i++ + "]");
            JRadioButton btn = new JRadioButton(entry.getValue());
            add(btn, key);
        }
    }

    //-------------------------------------------------------------------------
    /**
     * Gets the button group that this panel is managing.
     * 
     * @return the button group, not null
     */
    public ButtonGroup getButtonGroup() {
        return buttonGroup;
    }

    //-------------------------------------------------------------------------
    /**
     * Gets the number of columns to group the radio buttons in.
     * 
     * @return the number of columns, one or greater, or minus one for default
     */
    public int getLayoutColumns() {
        if (getLayout() instanceof RadioButtonLayout) {
            return ((RadioButtonLayout) getLayout()).getColumns();
        }
        return -1;
    }

    /**
     * Sets the number of columns to group the radio buttons in.
     * 
     * @param columns  the number of columns, one or greater
     */
    public void setLayoutColumns(int columns) {
        setLayout(new RadioButtonLayout(this, columns));
    }

    //-------------------------------------------------------------------------
    /**
     * Gets the current selection key of the radio button set.
     * <p>
     * This returns the key associated with the text, not the text itself.
     * 
     * @return the object key associated with the selection, null if no selection
     */
    public T getSelection() {
        ButtonModel selected = buttonGroup.getSelection();
        if (selected == null) {
            return null;
        }
        T key = modelToKeyMap.get(selected);
        if (key == null) {
            throw new IllegalArgumentException("Key not found for selected radio button: " + selected);
        }
        return (key != NULL ? key : null);
    }

    /**
     * Sets the current selection key of the radio button set.
     * 
     * @param key  the key to select, null may be used as a key
     * @throws IllegalArgumentException if the key is not valid
     */
    public void setSelection(T key) {
        key = maskNull(key);
        JRadioButton btn = keyToButtonMap.get(key);
        if (btn == null) {
            throw new IllegalArgumentException("Key is not a valid radio button: " + key);
        }
        buttonGroup.setSelected(btn.getModel(), true);
    }

    /**
     * Clears the current selection of the radio button set.
     */
    public void clearSelection() {
        buttonGroup.clearSelection();
    }

    //-------------------------------------------------------------------------
    /**
     * Override add method to attach radio buttons to the button group.
     * <p>
     * The constraints are used as the key.
     * 
     * @param comp  the component to add, not null
     * @param constraints  the constraints to use, not null
     * @param index  the index to add at
     */
    @Override
    protected void addImpl(Component comp, Object constraints, int index) {
        Objects.requireNonNull(comp, "comp");
        Objects.requireNonNull(constraints, "constraints");
        if (comp instanceof JRadioButton == false) {
            throw new IllegalArgumentException("Only a JRadioButton can be added to JRadioButtonPanel");
        }
        super.addImpl(comp, constraints, index);
        JRadioButton radio = (JRadioButton) comp;
        buttonGroup.add(radio);
        // use constraints as the key
        @SuppressWarnings("unchecked")
        T key = (T) constraints;
        keyToButtonMap.put(key, radio);
        modelToKeyMap.put(radio.getModel(), key);
    }

    @Override
    public void remove(Component comp) {
        Objects.requireNonNull(comp, "comp");
        if (comp instanceof JRadioButton == false) {
            throw new IllegalArgumentException("Only a JRadioButton can be removed from JRadioButtonPanel");
        }
        JRadioButton radio = (JRadioButton) comp;
        Object key = modelToKeyMap.remove(radio.getModel());
        if (key == null) {
            throw new IllegalArgumentException("Only a JRadioButton that was previously added can be removed");
        }
        keyToButtonMap.remove(key);
        buttonGroup.remove(radio);
        super.remove(comp);
    }

    @Override
    public void remove(int index) {
        remove(getComponent(index));
    }

    @Override
    public void removeAll() {
        while (buttonGroup.getButtonCount() > 0) {
            buttonGroup.remove(buttonGroup.getElements().nextElement());
        }
        modelToKeyMap.clear();
        keyToButtonMap.clear();
    }

    @SuppressWarnings("unchecked")
    private static <T> T maskNull(T key) {
        return (key != null ? key : (T) NULL);
    }

    //-------------------------------------------------------------------------
    // delegate baseline through to first radio button
    @Override
    public int getBaseline(int width, int height) {
        if (getComponentCount() > 0) {
            return getComponent(0).getBaseline(width, height);
        }
        return super.getBaseline(width, height);
    }

    @Override
    public BaselineResizeBehavior getBaselineResizeBehavior() {
        if (getComponentCount() > 0) {
            return getComponent(0).getBaselineResizeBehavior();
        }
        return super.getBaselineResizeBehavior();
    }

    //-------------------------------------------------------------------------
    /**
     * Layout manager to manage the radio buttons
     */
    public static class RadioButtonLayout implements LayoutManager2 {
        private Container target;
        private int columns = -1;
        private Rectangle[] layout;
        private int xTotal;
        private int yTotal;

        /**
         * Creates an instance.
         * 
         * @param target  the target container, not null
         * @param columns  the number of columns, one or greater, or minus one for default
         */
        public RadioButtonLayout(Container target, int columns) {
            super();
            this.target = target;
            this.columns = columns;
        }

        /**
         * Gets the number of columns to group the radio buttons in.
         * 
         * @return the number of columns, one or greater
         */
        public int getColumns() {
            return columns;
        }

        @Override
        public float getLayoutAlignmentX(Container target) {
            checkContainer(target);
            return 0;
        }

        @Override
        public float getLayoutAlignmentY(Container target) {
            checkContainer(target);
            return 0.5f;
        }

        @Override
        public void invalidateLayout(Container target) {
            checkContainer(target);
            layout = null;
            xTotal = 0;
            yTotal = 0;
        }

        @Override
        public void addLayoutComponent(Component comp, Object constraints) {
            layout = null;
            xTotal = 0;
            yTotal = 0;
        }

        @Override
        public void addLayoutComponent(String name, Component comp) {
            layout = null;
            xTotal = 0;
            yTotal = 0;
        }

        @Override
        public void removeLayoutComponent(Component comp) {
            layout = null;
            xTotal = 0;
            yTotal = 0;
        }

        /**
         * Imposes the layout onto the children.
         * 
         * @param target  the target container, not null
         */
        @Override
        public void layoutContainer(Container target) {
            checkContainer(target);
            calculate(target);
            int columns = calculateColumns(target);
            int rows = calculateRows(target);

            Dimension alloc = target.getSize();
            Insets in = target.getInsets();
            int allocHeight = alloc.height - in.top - in.bottom;
            int centre = 0;
            if (allocHeight > yTotal) {
                centre = (allocHeight - yTotal) / 2;
            }

            Component[] comps = target.getComponents();
            int comp = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (comp < comps.length) {
                        comps[comp].setBounds(
                                in.left + layout[comp].x,
                                in.top + centre + layout[comp].y,
                                layout[comp].width,
                                layout[comp].height);
                    }
                    comp++;
                }
            }
        }

        /**
         * Calculate the number of columns.
         */
        private int calculateColumns(Container target) {
            if (columns > 0) {
                return columns;
            }
            int compCount = target.getComponentCount();
            if (compCount < 4) {
                return compCount;
            }
            if (compCount == 4) {
                return 2;
            }
            return 1;
        }

        /**
         * Calculate the number of rows.
         */
        private int calculateRows(Container target) {
            int compCount = target.getComponentCount();
            if (columns > 0) {
                return (compCount + columns - 1) / columns;
            }
            if (compCount < 4) {
                return 1;
            }
            if (compCount == 4) {
                return 2;
            }
            return compCount;
        }

        /**
         * Calculate sizes.
         */
        private void calculate(Container target) {
            if (layout != null) {
                return;
            }
            int columns = calculateColumns(target);
            int rows = calculateRows(target);
            Component[] comps = target.getComponents();
            layout = new Rectangle[comps.length];
            for (int i = 0; i < comps.length; i++) {
                layout[i] = new Rectangle();
            }
            int comp = 0;
            // setup basic sizes in out array
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (comp < comps.length) {
                        layout[comp].setSize(comps[comp].getPreferredSize());
                    }
                    comp++;
                }
            }
            // set column to same width
            for (int j = 0; j < columns; j++) {
                int biggest = 0;
                for (int i = 0; i < rows; i++) {
                    comp = i * columns + j;
                    if (comp < comps.length) {
                        biggest = Math.max(biggest, layout[comp].width);
                    }
                }
                for (int i = 0; i < rows; i++) {
                    comp = i * columns + j;
                    if (comp < comps.length) {
                        layout[comp].width = biggest;
                    }
                }
            }
            // setup x y positions
            int xTotal = 0;
            int yTotal = 0;
            for (int i = 0; i < rows; i++) {
                xTotal = 0;
                for (int j = 0; j < columns; j++) {
                    comp = i * columns + j;
                    if (comp < comps.length) {
                        layout[comp].setLocation(xTotal, yTotal);
                        xTotal = xTotal + layout[comp].width;
                    }
                }
                yTotal = yTotal + layout[i * columns].height;
            }
            this.xTotal = xTotal;
            this.yTotal = yTotal;
        }

        @Override
        public Dimension minimumLayoutSize(Container target) {
            return preferredLayoutSize(target);
        }

        @Override
        public Dimension preferredLayoutSize(Container target) {
            checkContainer(target);
            calculate(target);
            Insets in = target.getInsets();
            return new Dimension(xTotal + in.left + in.right, yTotal + in.top + in.bottom);
        }

        @Override
        public Dimension maximumLayoutSize(Container target) {
            return preferredLayoutSize(target);
        }

        /**
         * Ensure the correct container is being used.
         */
        void checkContainer(Container target) {
            if (this.target != target) {
                throw new AWTError("Layout can't be shared");
            }
        }
    }

}
