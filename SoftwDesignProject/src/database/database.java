package database;

import Person.Person;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class database {
    protected final PropertyChangeSupport property;

    public database()
    {
        property = new PropertyChangeSupport(this);
    }

//    public abstract void addEntry(Person p, RegisterEntry re);
//    public abstract RegisterEntry getEntry(Employee e);

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {

        property.addPropertyChangeListener(propertyName, listener);
    }

    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {

        property.removePropertyChangeListener(propertyName, listener);
    }
}
