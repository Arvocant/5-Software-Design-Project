package database;

import Person.Person;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

public class PersonDatabase extends database<Person>{
    private static PersonDatabase instance;

    private PersonDatabase() {
        super();
    }

    public static synchronized PersonDatabase getInstance() {
        if (instance == null)
            instance = new PersonDatabase();
        return instance;
    }

    @Override
    public void addEntry(Person person) {
        if (person != null && !db.containsKey(person.getId())) {
            this.db.put(person.getId(), person);
            PropertyChangeEvent event = new PropertyChangeEvent(this, "addPerson", this.db, person);
            this.property.firePropertyChange(event);
        }
    }

    @Override
    public Person getItem(int id) {
        return db.get(id);
    }

    @Override
    public Person getEntryByName(String name) {
        for (Person person : db.values()) {
            if (person.getName().equals(name)) {
                return person;
            }
        }
        return null; // main.Person not found
    }

    @Override
    public int getId(Person person) {
        for (Person p : db.values()) {
            if (p.getName().equals(person.getName())) {
                return person.getId();
            }
        }
        return -1;
    }

    public Map<Integer, Person> getAllItems() {
        return db;
    }

    public void removeObject(Person person) {
        if (person != null && db.containsKey(person.getId())) {
            this.db.remove(person.getId(),person);

            PropertyChangeEvent event = new PropertyChangeEvent(this, "removePerson", this.db, person);
            this.property.firePropertyChange(event);
        }
    }

    @Override
    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        super.addPropertyChangeListener(propertyName, listener);
    }

    @Override
    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        super.removePropertyChangeListener(propertyName, listener);
    }
}
