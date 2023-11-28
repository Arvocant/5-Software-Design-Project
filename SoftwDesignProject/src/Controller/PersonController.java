package Controller;

import Person.Person;
import database.database;
import View.Menu;

public class PersonController implements IController {

    private Person modelPerson;
    private database<Person> db;

    private Menu view;

    public PersonController(Person model, database<Person> db, Menu view) {
        this.modelPerson = model;
        this.db = db;
        this.view = view;
    }

    @Override
    public void addToDb() {
        db.addEntry(this.modelPerson);
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

    @Override
    public void updateView() {
        //
    }
}
