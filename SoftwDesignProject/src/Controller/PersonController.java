package Controller;

import Person.Person;
import View.ViewFrame;
import database.database;

import java.util.ArrayList;
import java.util.Map;

public class PersonController implements IController {

    private Person modelPerson;
    private database<Person> db;

    private ViewFrame view;

    public PersonController(Person model, database<Person> db, ViewFrame view) {
        this.modelPerson = model;
        this.db = db;
        this.view = view;
    }

    @Override
    public void addToDb() {
        db.addEntry(this.modelPerson);
    }

    public Person getModelPerson() {
        return modelPerson;
    }

    public void setModelPerson(Person modelPerson) {
        this.modelPerson = modelPerson;
    }

    public Person getPersonOutOfDb(String name) {
        return this.db.getEntryByName(name);
    }

    public int getIdPerson() {
        return modelPerson.getId();
    }

    public void setIdPerson(int id) {
        modelPerson.setId(id);
    }

    public String getNamePerson() {
        return modelPerson.getName();
    }

    public void setNamePerson(String name) {
        modelPerson.setName(name);
    }

    public String getEmailPerson() {
        return modelPerson.getEmail();
    }

    public void setEmailPerson(String email) {
        modelPerson.setEmail(email);
    }

    public String getPhonePerson() {
        return modelPerson.getPhone();
    }

    public void setPhonePerson(String phone) {
        modelPerson.setPhone(phone);
    }

    public String[] getAllPersons() {
        ArrayList<String> AllPerson = new ArrayList<>();
        Map<Integer, Person> mapList = this.db.getAllExpenses();
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
