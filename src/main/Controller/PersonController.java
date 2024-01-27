package Controller;

import Person.Person;
import View.ControllerViews.PersonFrame;
import database.database;

import java.util.Map;

public class PersonController implements IController {

    private final database<Person> db;
    private final PersonFrame view;

    public PersonController(database<Person> dbModel, PersonFrame view) {
        this.db = dbModel;
        this.view = view;
    }

    public void addToDb(Person person) {
        this.db.addEntry(person);
    }

    public int getId(Person person) {
        return this.db.getId(person);
    }

    public Person getPerson(int id) {
        return this.db.getItem(id);
    }
    
    public Person getEntryByName(String name) {
        return this.db.getEntryByName(name);
    }

    public Map<Integer, Person> getAllPerson() {
        return this.db.getAllItems();
    }

    public void removePerson(Person person) {
        this.db.removeObject(person);
    }

    @Override
    public void updateView() {
        view.updatePanel(this.db.getAllItems());
    }
}
