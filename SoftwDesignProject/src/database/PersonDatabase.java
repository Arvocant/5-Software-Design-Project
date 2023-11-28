package database;

import Person.Person;

import java.beans.PropertyChangeEvent;
import java.util.HashMap;
import java.util.Map;

public class PersonDatabase extends database<Person>{
    private static PersonDatabase instance;
    private final Map<Integer, Person> db;

    private PersonDatabase() {
        this.db = new HashMap<>();
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
    public Person getEntry(String id) {
        return null;
    }
}
