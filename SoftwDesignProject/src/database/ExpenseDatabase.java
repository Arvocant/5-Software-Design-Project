package database;

import Expense.Expense;

import java.util.HashMap;
import java.util.Map;

public class ExpenseDatabase extends database<Expense>{

    private Map<String, Expense> db;
    private static ExpenseDatabase instance; //Singleton --> 1 instance

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
            this.property.firePropertyChange("addExpense", null, expense); //fires the propertyChangeListener
        }
    }

    @Override
    public Expense getEntry(String id) {
        return db.get(id);
    }

}
