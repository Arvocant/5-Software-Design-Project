package database;

import Expense.Expense;
import Expense.BalanceCalculator;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

public class ExpenseDatabase extends database<Expense>{

    private Map<Integer, Expense> db;
    private static ExpenseDatabase instance; //Singleton --> 1 instance
    private static int nextExpenseId = 1; // Next available expense ID
    private BalanceCalculator balanceCalculator;

    private ExpenseDatabase() {
        this.db = new HashMap<>();
        this.balanceCalculator = new BalanceCalculator();
    } //Constructor --> make new main.database (which is a HashMap)

    public static ExpenseDatabase getInstance(){ //Create new instance of the expense main.database
        if (instance == null)
            instance = new ExpenseDatabase();
        return instance;
    }

    @Override
    public void addEntry(Expense expense) { //addExpense
        if (expense != null && !db.containsKey(expense.getId())) { //Check if there is an expense and if an ID was given
            this.db.put(nextExpenseId, expense); //Put's the new expense with the given ID in the Hashmap
            ExpenseDatabase.getNextExpenseId();
            PropertyChangeEvent event = new PropertyChangeEvent(this, "addExpense", null, expense);

            this.property.firePropertyChange(event); //fires the propertyChangeListener
        }
    }

    // getter functions

    @Override
    public Expense getItem(int id) {
        return db.get(id);
    }

    @Override
    public Expense getEntryByName(String name) {
        for (Expense exp : db.values()) {
            if (exp.getDescription().getName().equals(name)) {
                return exp;
            }
        }
        return null; // main.Expense not found
    }

    @Override
    public Map<Integer, Expense> getAllItems() {
        return db;
    }

    public static int getNextExpenseId() {
        return nextExpenseId++;
    }

    public BalanceCalculator getBalanceCalculator() {
        return this.balanceCalculator;
    }

    // specific calculate functions

    public Map<Integer, Map<Integer, Double>> calculateTotal() {
        return balanceCalculator.calculateTotal(db);
    }

    public Map<Integer, Map<Integer, Double>> calculateIndividualAmounts(int userId) {
        return balanceCalculator.calculateIndividualAmounts(db, userId);
    }
    
    public double getBalanceForUser(int userId) {
        return balanceCalculator.getBalanceForUser(userId);
    }

    // remove expenses
    public void removeObject(Expense expense) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeObject'");
    }

    @Override
    public int getId(Expense item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getId'");
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
