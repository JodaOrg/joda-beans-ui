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

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLayer;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.LayerUI;

/**
 * A dialog containing a single text field.
 * <p>
 * This {@code JDialog} provides greater control than using {@code JOptionPane}.
 */
public class JTextFieldDialog extends JDialog {

    /** Serialization version. */
    private static final long serialVersionUID = 1L;

    /**
     * The main panel.
     */
    private final JPanel mainPanel;
    /**
     * The text field.
     */
    private final JValidatedTextField textField;
    /**
     * The button panel.
     */
    private final JPanel buttonPanel;
    /**
     * The add button.
     */
    private final JButton okButton;
    /**
     * The edit button.
     */
    private final JButton cancelButton;
    /**
     * Whether OK was pressed.
     */
    private boolean resultOk;

    /**
     * Creates an instance.
     * 
     * @param parent  the parent component, not null
     * @param title  the title of the dialog, not null
     */
    public JTextFieldDialog(JComponent parent, String title) {
        this(parent, title, new JValidatedTextField(30));
    }

    /**
     * Creates an instance.
     * 
     * @param parent  the parent component, not null
     * @param title  the title of the dialog, not null
     * @param textField  the text field, not null
     */
    public JTextFieldDialog(JComponent parent, String title, JValidatedTextField textField) {
        super(findWindow(parent), title, ModalityType.APPLICATION_MODAL);
        this.textField = Objects.requireNonNull(textField, "textField");
        // create
        mainPanel = new JPanel(new GridLayout(1, 1));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        mainPanel.add(textField);
        buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBorder(new CompoundBorder(new MatteBorder(1, 0, 0, 0, Color.GRAY), BorderFactory.createEmptyBorder(4, 4, 4, 4)));
        okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                resultOk = true;
                JTextFieldDialog.this.setVisible(false);
                JTextFieldDialog.this.dispose();
            }
        });
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                JTextFieldDialog.this.setVisible(false);
                JTextFieldDialog.this.dispose();
            }
        });
        JPanel buttonGrid = new JPanel(new GridLayout(1, 2));
        buttonGrid.add(okButton);
        buttonGrid.add(cancelButton);
        buttonPanel.add(buttonGrid, BorderLayout.EAST);
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(mainPanel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);
        getContentPane().setLayout(new GridLayout(1, 1));
        getContentPane().add(contentPanel);
        setTitle(title);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setComponentOrientation(parent.getComponentOrientation());
        setLocationRelativeTo(parent);
        getRootPane().setDefaultButton(okButton);
        addEscapeTrapper(this);
        pack();
    }

    @SuppressWarnings({"rawtypes", "unchecked" })
    private static void addEscapeTrapper(final JDialog dialog) {
        LayerUI escapeTrapper = new LayerUI() {
            private static final long serialVersionUID = 1L;
            private boolean closing;
            @Override
            public void installUI(JComponent comp) {
                super.installUI(comp);
                ((JLayer) comp).setLayerEventMask(AWTEvent.KEY_EVENT_MASK);
            }
            @Override
            public void uninstallUI(JComponent comp) {
                super.uninstallUI(comp);
                ((JLayer) comp).setLayerEventMask(0);
            }
            @Override
            protected void processKeyEvent(KeyEvent ev, JLayer layer) {
                if (ev.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    if (closing) {
                        return;
                    }
                    closing = true;
                    dialog.dispose();
                }
            }
        };
        JLayer layer = new JLayer(dialog.getContentPane(), escapeTrapper);
        dialog.setContentPane(layer);
    }

    /**
     * Creates an instance with list data.
     * 
     * @param data  the list data, not null
     */
    private static Window findWindow(Container parent) {
        while (parent != null && parent instanceof Frame == false && parent instanceof Dialog == false) {
            parent = parent.getParent();
        }
        if (parent == null) {
            return SwingUtilities.getWindowAncestor(parent);
        }
        return (Window) parent;
    }

    //-------------------------------------------------------------------------
    /**
     * Gets the scroll pane.
     * 
     * @return the scroll pane, not null
     */
    public JValidatedTextField getTextField() {
        return textField;
    }

    /**
     * Gets the button panel.
     * 
     * @return the button panel, not null
     */
    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    /**
     * Gets the OK button.
     * 
     * @return the button, not null
     */
    public JButton getOkButton() {
        return okButton;
    }

    /**
     * Gets the cancel button.
     * 
     * @return the button, not null
     */
    public JButton getCancelButton() {
        return cancelButton;
    }

    //-------------------------------------------------------------------------
    /**
     * Gets whether the result is OK or not.
     * 
     * @return the result, not null
     */
    public boolean isResultOk() {
        return resultOk;
    }

    /**
     * Gets the result.
     * 
     * @return the result, not null
     */
    public String getResult() {
        return getTextField().getText();
    }

}
