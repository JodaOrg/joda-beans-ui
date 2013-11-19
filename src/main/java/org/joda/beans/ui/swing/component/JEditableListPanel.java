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

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.joda.beans.ui.swing.SwingUISettings;

/**
 * A panel that contains a list that can be edited.
 * <p>
 * This {@code JPanel} is an easy way to produce a list with a linked text field for editing.
 * Buttons will be present to allow for addition and removal of entries.
 * 
 * @param <T>  the type of the data being displayed
 */
public class JEditableListPanel<T> extends JPanel {

    /** Serialization version. */
    private static final long serialVersionUID = 1L;

    /**
     * The list.
     */
    private final SimpleListModel<T> data;
    /**
     * The list.
     */
    private final JList<T> list;
    /**
     * The list.
     */
    private final JScrollPane scrollPane;
    /**
     * The add button.
     */
    private final JPanel buttonPanel;
    /**
     * The add button.
     */
    private final JButton addButton;
    /**
     * The edit button.
     */
    private final JButton editButton;
    /**
     * The remove button.
     */
    private final JButton removeButton;

    /**
     * Creates an instance.
     */
    public JEditableListPanel() {
        super();
        // create
        data = new SimpleListModel<>();
        list = new JList<>(data);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setVisibleRowCount(7);
        scrollPane = new JScrollPane(list);
        buttonPanel = new JPanel(new BorderLayout());
        addButton = new JButton(SwingUISettings.INSTANCE.lookupResource("Add.button") + "...");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
            }
        });
        editButton = new JButton(SwingUISettings.INSTANCE.lookupResource("Edit.button") + "...");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
            }
        });
        removeButton = new JButton(SwingUISettings.INSTANCE.lookupResource("Remove.button"));
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                int index = getList().getSelectionModel().getAnchorSelectionIndex();
                if (index >= 0 && index < getData().size()) {
                    getData().remove(index);
                    setEnabled(false);
                }
            }
        });
        removeButton.setEnabled(false);
        // layout
        setLayout(new BorderLayout());
        JPanel topButtonPanel = new JPanel(new GridLayout(3, 1));
        topButtonPanel.add(addButton);
        topButtonPanel.add(editButton);
        topButtonPanel.add(removeButton);
        editButton.setEnabled(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 4, 0, 0));
        buttonPanel.add(topButtonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.EAST);
        // bind
        getList().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent ev) {
                editButton.setEnabled(getList().getSelectionModel().isSelectionEmpty() == false);
                removeButton.setEnabled(getList().getSelectionModel().isSelectionEmpty() == false);
            }
        });
    }

    /**
     * Creates an instance with list data.
     * 
     * @param data  the list data, not null
     */
    public JEditableListPanel(List<T> data) {
        this();
        data.addAll(data);
    }

    //-------------------------------------------------------------------------
    /**
     * Gets the scroll pane.
     * 
     * @return the scroll pane, not null
     */
    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    /**
     * Gets the list.
     * 
     * @return the list, not null
     */
    public JList<T> getList() {
        return list;
    }

    /**
     * Gets the editable list data.
     * <p>
     * The iterators and sub list must not be modified.
     * 
     * @return the data, not null
     */
    public List<T> getData() {
        return data;
    }

    //-------------------------------------------------------------------------
    // delegate baseline through to scrolpane
    @Override
    public int getBaseline(int width, int height) {
        return getScrollPane().getBaseline(width, height);
    }

    @Override
    public BaselineResizeBehavior getBaselineResizeBehavior() {
        return BaselineResizeBehavior.CONSTANT_ASCENT;
    }

}
