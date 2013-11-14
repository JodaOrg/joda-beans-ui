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
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.KeyStroke;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.CompoundEdit;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;

/**
 * An advanced {@code UndoManager} for text fields.
 */
public final class TextUndoManager extends UndoManager {

    /**
     * Serialization version.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Ctrl+Z or similar.
     */
    private static final KeyStroke CTRL_Z = KeyStroke.getKeyStroke(KeyEvent.VK_Z, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
    /**
     * Ctrl+Y or similar.
     */
    private static final KeyStroke CTRL_Y = KeyStroke.getKeyStroke(KeyEvent.VK_Y, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
    /**
     * Undo key.
     */
    private static final String UNDO = "Undo";
    /**
     * Redo key.
     */
    private static final String REDO = "Redo";

    /**
     * The delay milliseconds to use.
     */
    private int delayMillis = 500;
    /**
     * The current edit.
     */
    private CompoundEdit currentEdit;
    /**
     * The instant of the last edit, millis.
     */
    private long lastEditInstantMillis;

    //-------------------------------------------------------------------------
    /**
     * Applies the undo manager to a text component.
     * <p>
     * Stores 100 edits.
     * 
     * @param component  the text field to add to, not null
     */
    public static void applyTo(JTextComponent component) {
        applyTo(component, 100);
    }

    /**
     * Applies the undo manager to a text component.
     * 
     * @param component  the text field to add to, not null
     * @param limit  the maximum history of edits
     */
    public static void applyTo(JTextComponent component, int limit) {
        final TextUndoManager undo = new TextUndoManager();
        undo.setLimit(limit);
        component.getDocument().addUndoableEditListener(undo);
        InputMap im = component.getInputMap();
        im.put(CTRL_Z, UNDO);
        im.put(CTRL_Y, REDO);
        ActionMap am = component.getActionMap();
        am.put(UNDO, new AbstractAction() {
            private static final long serialVersionUID = 1L;
            @Override
            public void actionPerformed(ActionEvent event) {
                if (undo.canUndo()) {
                    undo.undo();
                }
            }
        });
        am.put(REDO, new AbstractAction() {
            private static final long serialVersionUID = 1L;
            @Override
            public void actionPerformed(ActionEvent event) {
                if (undo.canRedo()) {
                    undo.redo();
                }
            }
        });
    }

    /**
     * Resets the undo manager.
     * 
     * @param textComponent  the text field to change, not null
     */
    public static void reset(JTextComponent textComponent) {
        Document document = textComponent.getDocument();
        if (document instanceof AbstractDocument) {
            for (UndoableEditListener undo : ((AbstractDocument) document).getUndoableEditListeners()) {
                if (undo instanceof TextUndoManager) {
                    ((TextUndoManager) undo).discardAllEdits();
                }
            }
        }
    }

    //-------------------------------------------------------------------------
    /**
     * Creates an undo manager.
     * <p>
     * This will not be attached to a text field.
     * Use {@link #applyTo(JTextComponent)} for full integration.
     */
    public TextUndoManager() {
    }

    //-------------------------------------------------------------------------
    /**
     * Gets the delay in milliseconds before declaring edits complete.
     * 
     * @return the delay in milliseconds, not negative
     */
    public synchronized int getDelayMillis() {
        return delayMillis;
    }

    /**
     * Sets the delay in milliseconds before declaring edits complete.
     * 
     * @param delayMillis  the delay in milliseconds, not negative
     */
    public synchronized void setDelayMillis(int delayMillis) {
        if (delayMillis < 0) {
            throw new IllegalArgumentException("Delay must be zero or greater");
        }
        this.delayMillis = delayMillis;
    }

    //-------------------------------------------------------------------------
    @Override
    public synchronized boolean addEdit(UndoableEdit edit) {
        long now = System.currentTimeMillis();
        boolean inProgress;
        if (currentEdit == null) {
            currentEdit = new CompoundEdit();
            inProgress = currentEdit.addEdit(edit);
        } else {
            if (now - lastEditInstantMillis > delayMillis) {
                finishEdit();
                currentEdit = new CompoundEdit();
            }
            inProgress = currentEdit.addEdit(edit);
        }
        lastEditInstantMillis = now;
        return inProgress;
    }

    @Override
    public synchronized void discardAllEdits() {
        currentEdit = null;
        super.discardAllEdits();
    }

    @Override
    public void die() {
        currentEdit = null;
        super.die();
    }

    @Override
    public synchronized void end() {
        finishEdit();
        super.end();
    }

    @Override
    public synchronized boolean canUndo() {
        finishEdit();
        return super.canUndo();
    }

    @Override
    public synchronized void undo() throws CannotUndoException {
        finishEdit();
        super.undo();
    }

    @Override
    public synchronized boolean canRedo() {
        finishEdit();
        return super.canRedo();
    }

    @Override
    public synchronized void redo() throws CannotRedoException {
        finishEdit();
        super.redo();
    }

    private void finishEdit() {
        if (currentEdit != null) {
            currentEdit.end();
            super.addEdit(currentEdit);
            currentEdit = null;
        }
    }

    @Override
    public synchronized boolean canUndoOrRedo() {
        finishEdit();
        return super.canUndoOrRedo();
    }

    @Override
    public synchronized void undoOrRedo() throws CannotRedoException, CannotUndoException {
        finishEdit();
        super.undoOrRedo();
    }

}
