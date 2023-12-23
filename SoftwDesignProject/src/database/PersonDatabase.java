package database;

import Person.Person;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

import Expense.BalanceCalculator;

public class PersonDatabase extends database<Person>{
    private static PersonDatabase instance;
    private final Map<Integer, Person> db;
    private static int nextPersonId = 1; // Next available person ID

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
        return null; // Person not found
    }

    public static int getNextPersonId() {
        return nextPersonId++;
    }

    public Map<Integer, Person> getAllItems() {
        return db;
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
