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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

/**
 * Example person class.
 */
@BeanDefinition
public class Person implements Bean {

    @PropertyDefinition
    private String forename;
    @PropertyDefinition
    private String surname;
    @PropertyDefinition
    private short age;
    @PropertyDefinition
    private int tripCount;
    @PropertyDefinition
    private double successRatio;
    @PropertyDefinition
    private boolean child;
    @PropertyDefinition
    private Boolean married;
    @PropertyDefinition
    private Colour favouriteColour;
    @PropertyDefinition
    private Address address;
    @PropertyDefinition
    private List<Person> friends = new ArrayList<Person>();

    //------------------------- AUTOGENERATED START -------------------------
    ///CLOVER:OFF
    /**
     * The meta-bean for {@code Person}.
     * @return the meta-bean, not null
     */
    public static Person.Meta meta() {
        return Person.Meta.INSTANCE;
    }

    static {
        JodaBeanUtils.registerMetaBean(Person.Meta.INSTANCE);
    }

    @Override
    public Person.Meta metaBean() {
        return Person.Meta.INSTANCE;
    }

    @Override
    public <R> Property<R> property(String propertyName) {
        return metaBean().<R>metaProperty(propertyName).createProperty(this);
    }

    @Override
    public Set<String> propertyNames() {
        return metaBean().metaPropertyMap().keySet();
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the forename.
     * @return the value of the property
     */
    public String getForename() {
        return forename;
    }

    /**
     * Sets the forename.
     * @param forename  the new value of the property
     */
    public void setForename(String forename) {
        this.forename = forename;
    }

    /**
     * Gets the the {@code forename} property.
     * @return the property, not null
     */
    public final Property<String> forename() {
        return metaBean().forename().createProperty(this);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the surname.
     * @return the value of the property
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the surname.
     * @param surname  the new value of the property
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets the the {@code surname} property.
     * @return the property, not null
     */
    public final Property<String> surname() {
        return metaBean().surname().createProperty(this);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the age.
     * @return the value of the property
     */
    public short getAge() {
        return age;
    }

    /**
     * Sets the age.
     * @param age  the new value of the property
     */
    public void setAge(short age) {
        this.age = age;
    }

    /**
     * Gets the the {@code age} property.
     * @return the property, not null
     */
    public final Property<Short> age() {
        return metaBean().age().createProperty(this);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the tripCount.
     * @return the value of the property
     */
    public int getTripCount() {
        return tripCount;
    }

    /**
     * Sets the tripCount.
     * @param tripCount  the new value of the property
     */
    public void setTripCount(int tripCount) {
        this.tripCount = tripCount;
    }

    /**
     * Gets the the {@code tripCount} property.
     * @return the property, not null
     */
    public final Property<Integer> tripCount() {
        return metaBean().tripCount().createProperty(this);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the successRatio.
     * @return the value of the property
     */
    public double getSuccessRatio() {
        return successRatio;
    }

    /**
     * Sets the successRatio.
     * @param successRatio  the new value of the property
     */
    public void setSuccessRatio(double successRatio) {
        this.successRatio = successRatio;
    }

    /**
     * Gets the the {@code successRatio} property.
     * @return the property, not null
     */
    public final Property<Double> successRatio() {
        return metaBean().successRatio().createProperty(this);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the child.
     * @return the value of the property
     */
    public boolean isChild() {
        return child;
    }

    /**
     * Sets the child.
     * @param child  the new value of the property
     */
    public void setChild(boolean child) {
        this.child = child;
    }

    /**
     * Gets the the {@code child} property.
     * @return the property, not null
     */
    public final Property<Boolean> child() {
        return metaBean().child().createProperty(this);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the married.
     * @return the value of the property
     */
    public Boolean getMarried() {
        return married;
    }

    /**
     * Sets the married.
     * @param married  the new value of the property
     */
    public void setMarried(Boolean married) {
        this.married = married;
    }

    /**
     * Gets the the {@code married} property.
     * @return the property, not null
     */
    public final Property<Boolean> married() {
        return metaBean().married().createProperty(this);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the favouriteColour.
     * @return the value of the property
     */
    public Colour getFavouriteColour() {
        return favouriteColour;
    }

    /**
     * Sets the favouriteColour.
     * @param favouriteColour  the new value of the property
     */
    public void setFavouriteColour(Colour favouriteColour) {
        this.favouriteColour = favouriteColour;
    }

    /**
     * Gets the the {@code favouriteColour} property.
     * @return the property, not null
     */
    public final Property<Colour> favouriteColour() {
        return metaBean().favouriteColour().createProperty(this);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the address.
     * @return the value of the property
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the address.
     * @param address  the new value of the property
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Gets the the {@code address} property.
     * @return the property, not null
     */
    public final Property<Address> address() {
        return metaBean().address().createProperty(this);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the friends.
     * @return the value of the property
     */
    public List<Person> getFriends() {
        return friends;
    }

    /**
     * Sets the friends.
     * @param friends  the new value of the property
     */
    public void setFriends(List<Person> friends) {
        this.friends = friends;
    }

    /**
     * Gets the the {@code friends} property.
     * @return the property, not null
     */
    public final Property<List<Person>> friends() {
        return metaBean().friends().createProperty(this);
    }

    //-----------------------------------------------------------------------
    @Override
    public Person clone() {
        BeanBuilder<? extends Person> builder = metaBean().builder();
        for (MetaProperty<?> mp : metaBean().metaPropertyIterable()) {
            if (mp.style().isBuildable()) {
                Object value = mp.get(this);
                if (value instanceof Bean) {
                    value = ((Bean) value).clone();
                }
                builder.set(mp.name(), value);
            }
        }
        return builder.build();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == this.getClass()) {
            Person other = (Person) obj;
            return JodaBeanUtils.equal(getForename(), other.getForename()) &&
                    JodaBeanUtils.equal(getSurname(), other.getSurname()) &&
                    (getAge() == other.getAge()) &&
                    (getTripCount() == other.getTripCount()) &&
                    JodaBeanUtils.equal(getSuccessRatio(), other.getSuccessRatio()) &&
                    (isChild() == other.isChild()) &&
                    JodaBeanUtils.equal(getMarried(), other.getMarried()) &&
                    JodaBeanUtils.equal(getFavouriteColour(), other.getFavouriteColour()) &&
                    JodaBeanUtils.equal(getAddress(), other.getAddress()) &&
                    JodaBeanUtils.equal(getFriends(), other.getFriends());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = getClass().hashCode();
        hash += hash * 31 + JodaBeanUtils.hashCode(getForename());
        hash += hash * 31 + JodaBeanUtils.hashCode(getSurname());
        hash += hash * 31 + JodaBeanUtils.hashCode(getAge());
        hash += hash * 31 + JodaBeanUtils.hashCode(getTripCount());
        hash += hash * 31 + JodaBeanUtils.hashCode(getSuccessRatio());
        hash += hash * 31 + JodaBeanUtils.hashCode(isChild());
        hash += hash * 31 + JodaBeanUtils.hashCode(getMarried());
        hash += hash * 31 + JodaBeanUtils.hashCode(getFavouriteColour());
        hash += hash * 31 + JodaBeanUtils.hashCode(getAddress());
        hash += hash * 31 + JodaBeanUtils.hashCode(getFriends());
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder(352);
        buf.append("Person{");
        int len = buf.length();
        toString(buf);
        if (buf.length() > len) {
            buf.setLength(buf.length() - 2);
        }
        buf.append('}');
        return buf.toString();
    }

    protected void toString(StringBuilder buf) {
        buf.append("forename").append('=').append(JodaBeanUtils.toString(getForename())).append(',').append(' ');
        buf.append("surname").append('=').append(JodaBeanUtils.toString(getSurname())).append(',').append(' ');
        buf.append("age").append('=').append(JodaBeanUtils.toString(getAge())).append(',').append(' ');
        buf.append("tripCount").append('=').append(JodaBeanUtils.toString(getTripCount())).append(',').append(' ');
        buf.append("successRatio").append('=').append(JodaBeanUtils.toString(getSuccessRatio())).append(',').append(' ');
        buf.append("child").append('=').append(JodaBeanUtils.toString(isChild())).append(',').append(' ');
        buf.append("married").append('=').append(JodaBeanUtils.toString(getMarried())).append(',').append(' ');
        buf.append("favouriteColour").append('=').append(JodaBeanUtils.toString(getFavouriteColour())).append(',').append(' ');
        buf.append("address").append('=').append(JodaBeanUtils.toString(getAddress())).append(',').append(' ');
        buf.append("friends").append('=').append(JodaBeanUtils.toString(getFriends())).append(',').append(' ');
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-bean for {@code Person}.
     */
    public static class Meta extends DirectMetaBean {
        /**
         * The singleton instance of the meta-bean.
         */
        static final Meta INSTANCE = new Meta();

        /**
         * The meta-property for the {@code forename} property.
         */
        private final MetaProperty<String> forename = DirectMetaProperty.ofReadWrite(
                this, "forename", Person.class, String.class);
        /**
         * The meta-property for the {@code surname} property.
         */
        private final MetaProperty<String> surname = DirectMetaProperty.ofReadWrite(
                this, "surname", Person.class, String.class);
        /**
         * The meta-property for the {@code age} property.
         */
        private final MetaProperty<Short> age = DirectMetaProperty.ofReadWrite(
                this, "age", Person.class, Short.TYPE);
        /**
         * The meta-property for the {@code tripCount} property.
         */
        private final MetaProperty<Integer> tripCount = DirectMetaProperty.ofReadWrite(
                this, "tripCount", Person.class, Integer.TYPE);
        /**
         * The meta-property for the {@code successRatio} property.
         */
        private final MetaProperty<Double> successRatio = DirectMetaProperty.ofReadWrite(
                this, "successRatio", Person.class, Double.TYPE);
        /**
         * The meta-property for the {@code child} property.
         */
        private final MetaProperty<Boolean> child = DirectMetaProperty.ofReadWrite(
                this, "child", Person.class, Boolean.TYPE);
        /**
         * The meta-property for the {@code married} property.
         */
        private final MetaProperty<Boolean> married = DirectMetaProperty.ofReadWrite(
                this, "married", Person.class, Boolean.class);
        /**
         * The meta-property for the {@code favouriteColour} property.
         */
        private final MetaProperty<Colour> favouriteColour = DirectMetaProperty.ofReadWrite(
                this, "favouriteColour", Person.class, Colour.class);
        /**
         * The meta-property for the {@code address} property.
         */
        private final MetaProperty<Address> address = DirectMetaProperty.ofReadWrite(
                this, "address", Person.class, Address.class);
        /**
         * The meta-property for the {@code friends} property.
         */
        @SuppressWarnings({"unchecked", "rawtypes" })
        private final MetaProperty<List<Person>> friends = DirectMetaProperty.ofReadWrite(
                this, "friends", Person.class, (Class) List.class);
        /**
         * The meta-properties.
         */
        private final Map<String, MetaProperty<?>> metaPropertyMap$ = new DirectMetaPropertyMap(
                this, null,
                "forename",
                "surname",
                "age",
                "tripCount",
                "successRatio",
                "child",
                "married",
                "favouriteColour",
                "address",
                "friends");

        /**
         * Restricted constructor.
         */
        protected Meta() {
        }

        @Override
        protected MetaProperty<?> metaPropertyGet(String propertyName) {
            switch (propertyName.hashCode()) {
                case 467061063:  // forename
                    return forename;
                case -1852993317:  // surname
                    return surname;
                case 96511:  // age
                    return age;
                case -424035350:  // tripCount
                    return tripCount;
                case -664622648:  // successRatio
                    return successRatio;
                case 94631196:  // child
                    return child;
                case 839462772:  // married
                    return married;
                case -1213306923:  // favouriteColour
                    return favouriteColour;
                case -1147692044:  // address
                    return address;
                case -600094315:  // friends
                    return friends;
            }
            return super.metaPropertyGet(propertyName);
        }

        @Override
        public BeanBuilder<? extends Person> builder() {
            return new DirectBeanBuilder<Person>(new Person());
        }

        @Override
        public Class<? extends Person> beanType() {
            return Person.class;
        }

        @Override
        public Map<String, MetaProperty<?>> metaPropertyMap() {
            return metaPropertyMap$;
        }

        //-----------------------------------------------------------------------
        /**
         * The meta-property for the {@code forename} property.
         * @return the meta-property, not null
         */
        public final MetaProperty<String> forename() {
            return forename;
        }

        /**
         * The meta-property for the {@code surname} property.
         * @return the meta-property, not null
         */
        public final MetaProperty<String> surname() {
            return surname;
        }

        /**
         * The meta-property for the {@code age} property.
         * @return the meta-property, not null
         */
        public final MetaProperty<Short> age() {
            return age;
        }

        /**
         * The meta-property for the {@code tripCount} property.
         * @return the meta-property, not null
         */
        public final MetaProperty<Integer> tripCount() {
            return tripCount;
        }

        /**
         * The meta-property for the {@code successRatio} property.
         * @return the meta-property, not null
         */
        public final MetaProperty<Double> successRatio() {
            return successRatio;
        }

        /**
         * The meta-property for the {@code child} property.
         * @return the meta-property, not null
         */
        public final MetaProperty<Boolean> child() {
            return child;
        }

        /**
         * The meta-property for the {@code married} property.
         * @return the meta-property, not null
         */
        public final MetaProperty<Boolean> married() {
            return married;
        }

        /**
         * The meta-property for the {@code favouriteColour} property.
         * @return the meta-property, not null
         */
        public final MetaProperty<Colour> favouriteColour() {
            return favouriteColour;
        }

        /**
         * The meta-property for the {@code address} property.
         * @return the meta-property, not null
         */
        public final MetaProperty<Address> address() {
            return address;
        }

        /**
         * The meta-property for the {@code friends} property.
         * @return the meta-property, not null
         */
        public final MetaProperty<List<Person>> friends() {
            return friends;
        }

        //-----------------------------------------------------------------------
        @Override
        protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
            switch (propertyName.hashCode()) {
                case 467061063:  // forename
                    return ((Person) bean).getForename();
                case -1852993317:  // surname
                    return ((Person) bean).getSurname();
                case 96511:  // age
                    return ((Person) bean).getAge();
                case -424035350:  // tripCount
                    return ((Person) bean).getTripCount();
                case -664622648:  // successRatio
                    return ((Person) bean).getSuccessRatio();
                case 94631196:  // child
                    return ((Person) bean).isChild();
                case 839462772:  // married
                    return ((Person) bean).getMarried();
                case -1213306923:  // favouriteColour
                    return ((Person) bean).getFavouriteColour();
                case -1147692044:  // address
                    return ((Person) bean).getAddress();
                case -600094315:  // friends
                    return ((Person) bean).getFriends();
            }
            return super.propertyGet(bean, propertyName, quiet);
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
            switch (propertyName.hashCode()) {
                case 467061063:  // forename
                    ((Person) bean).setForename((String) newValue);
                    return;
                case -1852993317:  // surname
                    ((Person) bean).setSurname((String) newValue);
                    return;
                case 96511:  // age
                    ((Person) bean).setAge((Short) newValue);
                    return;
                case -424035350:  // tripCount
                    ((Person) bean).setTripCount((Integer) newValue);
                    return;
                case -664622648:  // successRatio
                    ((Person) bean).setSuccessRatio((Double) newValue);
                    return;
                case 94631196:  // child
                    ((Person) bean).setChild((Boolean) newValue);
                    return;
                case 839462772:  // married
                    ((Person) bean).setMarried((Boolean) newValue);
                    return;
                case -1213306923:  // favouriteColour
                    ((Person) bean).setFavouriteColour((Colour) newValue);
                    return;
                case -1147692044:  // address
                    ((Person) bean).setAddress((Address) newValue);
                    return;
                case -600094315:  // friends
                    ((Person) bean).setFriends((List<Person>) newValue);
                    return;
            }
            super.propertySet(bean, propertyName, newValue, quiet);
        }

    }

    ///CLOVER:ON
    //-------------------------- AUTOGENERATED END --------------------------
}
