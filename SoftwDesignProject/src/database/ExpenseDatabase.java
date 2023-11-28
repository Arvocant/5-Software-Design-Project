package database;

import Expense.Expense;
import Person.Person;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseDatabase extends database<Expense>{

    private Map<Integer, Expense> db;
    private static ExpenseDatabase instance; //Singleton --> 1 instance

    List<Person> personList;

    private ExpenseDatabase() { this.db = new HashMap<>(); } //Constructor --> make new database (which is a HashMap)

    public static ExpenseDatabase getInstance(){ //Create new instance of the expense database
        if (instance == null)
            instance = new ExpenseDatabase();
        return instance;
    } //Get instance (bcs singleton)

    @Override
    public void addEntry(Expense expense) {
        if (expense != null && !db.containsKey(expense.getId())) { //Check if there is an expense and if an ID was given
            this.db.put(expense.getId(), expense); //Put's the new expense with the given ID in the Hashmap
            PropertyChangeEvent event = new PropertyChangeEvent(this, "addExpense", null, expense);

            this.property.firePropertyChange(event); //fires the propertyChangeListener
        }
    }

    @Override
    public Expense getEntry(String id) {
        return db.get(id);
    }

    @Override
    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        super.addPropertyChangeListener(propertyName, listener);
    }

    @Override
    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        super.removePropertyChangeListener(propertyName, listener);
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public Person getById(int id){
        return personList.get(id);
    }
}
