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
package org.joda.beans.ui.metawidget;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import org.joda.beans.ui.metawidget.MWJodaBeanPropertyStyle;
import org.joda.beans.ui.swing.Address;
import org.joda.beans.ui.swing.Person;
import org.metawidget.inspector.impl.BaseObjectInspectorConfig;
import org.metawidget.inspector.propertytype.PropertyTypeInspector;
import org.metawidget.swing.SwingMetawidget;

/**
 * Example used to demonstrate the swing UI.
 */
public class MetaSwingUIExample {

    public static void main(String[] args) {
        try {
            Person person = new Person();
            person.setForename("Stephen");
            person.setSurname("Colebourne");
            person.setAge(30);
            person.setAddress(new Address());
            person.getAddress().setStreet("186 Park Street");
            person.getAddress().setCity("London");
            
            SwingMetawidget panel = new SwingMetawidget();
            PropertyTypeInspector inspector = new PropertyTypeInspector(
                    new BaseObjectInspectorConfig().setPropertyStyle(new MWJodaBeanPropertyStyle()));
            panel.setInspector(inspector);
            panel.setToInspect(person);
            
//            JPanel panel = new JodaBeanSwingUI().createReadOnly(person);
            
            JFrame frame = new JFrame("Swing bean example");
            frame.getContentPane().setLayout(new BorderLayout());
            frame.getContentPane().add(panel, BorderLayout.CENTER);
            frame.setSize(400, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

}
