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
package org.joda.beans.ui.swing;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Example used to demonstrate the swing UI.
 */
public class SwingUIExample {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    createUI();
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    private static void createUI() {
        Person person = createPerson();
        JPanel panel = new JodaBeanSwingUI().createForm(person);
        JFrame frame = new JFrame("Swing bean example");
        JScrollPane scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        frame.getContentPane().add(scrollPane);
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    private static Person createPerson() {
        Person person = new Person();
        person.setForename("Stephen");
        person.setSurname("Colebourne");
        person.setAge((short) 30);
        person.setTripCount(6);
        person.setChild(true);
        person.setAddress(new Address());
        person.getAddress().setStreet("186 Park Street");
        person.getAddress().setCity("London");
        person.setRegion(WorldRegion.BENELUX);
        person.getTags().add("healthy");
        person.getTags().add("fun");
        person.getTags().add("employed");
        person.getTags().add("londoner");
        person.getTags().add("java developer");
        person.getTags().add("blogger");
        person.getTags().add("speaker");
        person.getTags().add("troublemaker");
        person.getTags().add("traveller");
        return person;
    }

}
