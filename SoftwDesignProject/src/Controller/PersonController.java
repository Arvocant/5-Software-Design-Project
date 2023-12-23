package Controller;

import Person.Person;
import View.Panels.PersonPanel;
import database.database;

import java.util.ArrayList;
import java.util.Map;

import Expense.BalanceCalculator;

public class PersonController implements IController {

    private database<Person> db;
    private PersonPanel view;

    public PersonController(database<Person> dbModel, PersonPanel view) {
        this.db = dbModel;
        this.view = view;
    }

    public void addToDb(Person person) {
        this.db.addEntry(person);
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

    public String[] getAllPersonsStringArray() {
        ArrayList<String> AllPerson = new ArrayList<>();
        Map<Integer, Person> mapList = this.db.getAllItems();
        for (Person value : mapList.values()) {
            AllPerson.add(value.toString());
        }

        return AllPerson.toArray(new String[0]);
    }

    @Override
    public void updateView() {
        //
    }
}
