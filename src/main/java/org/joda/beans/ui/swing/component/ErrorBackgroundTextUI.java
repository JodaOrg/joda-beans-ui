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
import java.awt.Component.BaselineResizeBehavior;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.Objects;

import javax.accessibility.Accessible;
import javax.swing.JComponent;
import javax.swing.plaf.TextUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.EditorKit;
import javax.swing.text.JTextComponent;
import javax.swing.text.Position.Bias;
import javax.swing.text.View;

/**
 * A UI delegate that exists to indicate that the component is in error.
 */
public class ErrorBackgroundTextUI extends TextUI {

    /**
     * The error background color.
     */
    private static final Color ERROR_BACKGROUND = new Color(255, 219, 219);
    /**
     * The size of the error marker.
     */
    private static final int ERROR_SIZE = 8;
    /**
     * The size of the error padding.
     */
    private static final int ERROR_PAD = 4;

    /**
     * The underlying UI delegate.
     */
    private final TextUI ui;
    /**
     * The main component being decorated.
     * This is specified in cases like {@code JComboBox}.
     */
    private final JComponent component;

    /**
     * Creates an instance.
     * 
     * @param ui  the underlying, not null
     */
    public ErrorBackgroundTextUI(TextUI ui) {
        this(ui, null);
    }

    /**
     * Creates an instance.
     * 
     * @param ui  the underlying, not null
     * @param component  the main component that stores the error status, null to derive
     */
    public ErrorBackgroundTextUI(TextUI ui, JComponent component) {
        this.ui = Objects.requireNonNull(ui, "ui");
        this.component = component;
    }

    //-------------------------------------------------------------------------
    @Override
    public void installUI(JComponent c) {
        ui.installUI(c);
    }

    @Override
    public void uninstallUI(JComponent c) {
        ui.uninstallUI(c);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        ui.paint(g, c);
    }

    @Override
    public void update(Graphics g, JComponent c) {
        JComponent main = (component != null ? component : c);
        if (SwingUtils.getErrorStatus(main).isError()) {
            if (c.isOpaque()) {
                g.setColor(ERROR_BACKGROUND);
                g.fillRect(0, 0, c.getWidth(), c.getHeight());
                c.setOpaque(false);
                ui.paint(g, c);
                paintMarker(g, c);
                c.setOpaque(true);
            } else {
                g.setColor(ERROR_BACKGROUND);
                g.fillRect(0, 0, c.getWidth(), c.getHeight());
                ui.paint(g, c);
                paintMarker(g, c);
            }
        } else {
            ui.update(g, c);
        }
    }

    private void paintMarker(Graphics g, JComponent c) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int w = c.getWidth();
        int h = c.getHeight();
        int x = w - ERROR_PAD - ERROR_SIZE;
        int y = (h - ERROR_SIZE) / 2;
        g2.setPaint(Color.red);
        g2.fillRect(x, y, ERROR_SIZE + 1, ERROR_SIZE + 1);
        g2.setPaint(Color.white);
        g2.drawLine(x, y, x + ERROR_SIZE, y + ERROR_SIZE);
        g2.drawLine(x, y + ERROR_SIZE, x + ERROR_SIZE, y);
    }

    @Override
    public Dimension getPreferredSize(JComponent c) {
        return ui.getPreferredSize(c);
    }

    @Override
    public Dimension getMinimumSize(JComponent c) {
        return ui.getMinimumSize(c);
    }

    @Override
    public Dimension getMaximumSize(JComponent c) {
        return ui.getMaximumSize(c);
    }

    @Override
    public boolean contains(JComponent c, int x, int y) {
        return ui.contains(c, x, y);
    }

    @Override
    public int getBaseline(JComponent c, int width, int height) {
        return ui.getBaseline(c, width, height);
    }

    @Override
    public BaselineResizeBehavior getBaselineResizeBehavior(JComponent c) {
        return ui.getBaselineResizeBehavior(c);
    }

    @Override
    public int getAccessibleChildrenCount(JComponent c) {
        return ui.getAccessibleChildrenCount(c);
    }

    @Override
    public Accessible getAccessibleChild(JComponent c, int i) {
        return ui.getAccessibleChild(c, i);
    }

    @Override
    public Rectangle modelToView(JTextComponent t, int pos) throws BadLocationException {
        return ui.modelToView(t, pos);
    }

    @Override
    public Rectangle modelToView(JTextComponent t, int pos, Bias bias) throws BadLocationException {
        return ui.modelToView(t, pos, bias);
    }

    @Override
    public int viewToModel(JTextComponent t, Point pt) {
        return ui.viewToModel(t, pt);
    }

    @Override
    public int viewToModel(JTextComponent t, Point pt, Bias[] biasReturn) {
        return ui.viewToModel(t, pt, biasReturn);
    }

    @Override
    public int getNextVisualPositionFrom(JTextComponent t, int pos, Bias b, int direction, Bias[] biasRet) throws BadLocationException {
        return ui.getNextVisualPositionFrom(t, pos, b, direction, biasRet);
    }

    @Override
    public void damageRange(JTextComponent t, int p0, int p1) {
        ui.damageRange(t, p0, p1);
    }

    @Override
    public void damageRange(JTextComponent t, int p0, int p1, Bias firstBias, Bias secondBias) {
        ui.damageRange(t, p0, p1, firstBias, secondBias);
    }

    @Override
    public EditorKit getEditorKit(JTextComponent t) {
        return ui.getEditorKit(t);
    }

    @Override
    public View getRootView(JTextComponent t) {
        return ui.getRootView(t);
    }

    @Override
    public String getToolTipText(JTextComponent t, Point pt) {
        return ui.getToolTipText(t, pt);
    }

}
