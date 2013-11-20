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

import java.awt.AlphaComposite;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.JLayer;
import javax.swing.JRootPane;
import javax.swing.plaf.LayerUI;

/**
 * Utilities for the Swing UI.
 */
public final class SwingUtils {

    /**
     * Restricted constructor.
     */
    private SwingUtils() {
    }

    //-------------------------------------------------------------------------
    /**
     * Enables and disables the whole of a frame/dialog.
     * <p>
     * This is intended to be used when working with a modal dialog.
     * It finds the root pane of the component and changes the content pane to
     * be a {@code JLayer} decorator. Once installed, the layer alters the paining.
     * 
     * @param componentInWindow  a component in the window to be disabled, not null
     * @param enabled  true if enabled, false if disabled
     */
    public static void setWindowEnabled(final JComponent componentInWindow, boolean enabled) {
        JRootPane root = componentInWindow.getRootPane();
        if (root != null) {
            Container content = root.getContentPane();
            if (isDisabledLayer(content)) {
                content.setEnabled(enabled);
                return;
            }
            for (Component comp : content.getComponents()) {
                if (isDisabledLayer(comp)) {
                    comp.setEnabled(enabled);
                    return;
                }
            }
            JLayer<Component> layer = new JLayer<Component>(root.getContentPane(), new DisabledLayerUI());
            root.setContentPane(layer);
            root.revalidate();
            layer.setEnabled(enabled);
        }
    }

    private static boolean isDisabledLayer(Component comp) {
        return (comp instanceof JLayer && ((JLayer<?>) comp).getUI() instanceof DisabledLayerUI);
    }

    //-------------------------------------------------------------------------
    /**
     * Special decorating painter.
     */
    private static class DisabledLayerUI extends LayerUI<Component> {
        private static final long serialVersionUID = 1L;
        @Override
        public void paint(Graphics g, JComponent comp) {
            // paint normal
            super.paint(g, comp);
            // paint disabled
            if (comp.isEnabled() == false) {
                Graphics2D g2 = (Graphics2D) g.create();
                int w = comp.getWidth();
                int h = comp.getHeight();
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.12f));
                g2.fillRect(0, 0, w, h);
                g2.dispose();
            }
        }
    }

}
