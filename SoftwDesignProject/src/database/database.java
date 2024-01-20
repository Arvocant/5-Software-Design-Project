package database;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Map;


public abstract class database<T> {
    protected final PropertyChangeSupport property;

    public database()
    {
        property = new PropertyChangeSupport(this);
    }

    public abstract void addEntry(T entry);
    public abstract T getItem(int id);
    public abstract T getEntryByName(String name);
    public abstract Map<Integer, T> getAllItems();
    public abstract void removeObject(T entry);

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {

        property.addPropertyChangeListener(propertyName, listener);
    }
    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {

        property.removePropertyChangeListener(propertyName, listener);
    }


}
