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

    private static int nextExpenseId = 1; // Next available expense ID

    private ExpenseDatabase() { this.db = new HashMap<>(); } //Constructor --> make new database (which is a HashMap)

    public static ExpenseDatabase getInstance(){ //Create new instance of the expense database
        if (instance == null)
            instance = new ExpenseDatabase();
        return instance;
    }

    @Override
    public void addEntry(Expense expense) { //addExpense
        if (expense != null && !db.containsKey(expense.getId())) { //Check if there is an expense and if an ID was given
            this.db.put(expense.getId(), expense); //Put's the new expense with the given ID in the Hashmap
            PropertyChangeEvent event = new PropertyChangeEvent(this, "addExpense", null, expense);

            this.property.firePropertyChange(event); //fires the propertyChangeListener
        }
    }

    @Override
    public Expense getEntry(int id) {
        return db.get(id);
    }
    
    public static int getNextExpenseId() {
        return nextExpenseId++;
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
