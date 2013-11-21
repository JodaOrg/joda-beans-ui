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
package org.joda.beans.ui.form;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Objects;

/**
 * A factory allowing the real UI component to be created.
 * For example, this might return a Swing-based factory.
 */
@SuppressWarnings("rawtypes")
public final class DefaultUIComponentFactory extends UIComponentFactory {

    /**
     * The {@code UIComponent} class to instantiate.
     */
    private final Constructor constructor;

    /**
     * Obtains an instance for a type.
     * 
     * @param type  the type to instantiate, not null
     * @return the factory, not null
     */
    public static UIComponentFactory of(Class<?> type) {
        Objects.requireNonNull(type, "type");
        return new DefaultUIComponentFactory(type);
    }

    /**
     * Creates an instance.
     */
    private DefaultUIComponentFactory(Class<?> type) {
        if (type.isInterface() || Modifier.isAbstract(type.getModifiers())) {
            throw new IllegalArgumentException("Type is not instantiable: " + type.getName());
        }
        Constructor cons;
        try {
            cons = type.getDeclaredConstructor(MetaUIComponent.class);
        } catch (ReflectiveOperationException ex) {
            throw new IllegalArgumentException(ex);
        }
        if (Modifier.isPublic(cons.getModifiers()) == false) {
            throw new IllegalArgumentException("Constructor is not public: " + cons);
        }
        this.constructor = cons;
    }

    //-------------------------------------------------------------------------
    /**
     * Creates the UI component.
     * 
     * @param <T>  the UI component type
     * @param metaComponent  the meta-component, not null
     * @return the UI component, not null
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> UIComponent<T> createComponent(MetaUIComponent metaComponent) {
        try {
            return (UIComponent<T>) constructor.newInstance(metaComponent);
        } catch (ReflectiveOperationException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    //-------------------------------------------------------------------------
    @Override
    public String toString() {
        return constructor.toString();
    }

}
