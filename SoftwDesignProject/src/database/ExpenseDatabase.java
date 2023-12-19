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
    private static int nextExpenseId = 0; // Next available expense ID
    private BalanceCalculator balanceCalculator;

    private ExpenseDatabase() {
        this.db = new HashMap<>();
        this.balanceCalculator = new BalanceCalculator();
    } //Constructor --> make new database (which is a HashMap)

    public static ExpenseDatabase getInstance(){ //Create new instance of the expense database
        if (instance == null)
            instance = new ExpenseDatabase();
        return instance;
    }

    @Override
    public void addEntry(Expense expense) { //addExpense
        if (expense != null /*&& !db.containsKey(expense.getId())*/) { //Check if there is an expense and if an ID was given
            this.db.put(getNextExpenseId(), expense); //Put's the new expense with the given ID in the Hashmap

            PropertyChangeEvent event = new PropertyChangeEvent(this, "addExpense", null, expense);

            this.property.firePropertyChange(event); //fires the propertyChangeListener
        }
    }

    @Override
    public Expense getExpense(int id) {
        return db.get(id);
    }

    @Override
    public Expense getEntryByName(String name) {
        for (Expense exp : db.values()) {
            if (exp.getDescription().getName().equals(name)) {
                return exp;
            }
        }
        return null; // Expense not found
    }

    @Override
    public Map<Integer, Expense> getAllExpenses() {
        return db;
    }

    public static int getNextExpenseId() {
        return nextExpenseId++;
    }

    public Map<Integer, Map<Integer, Double>> calculateTotal() {
        return balanceCalculator.calculateTotal(db);
    }

    public Map<Integer, Map<Integer, Double>> calculateIndividualAmounts(int userId) {
        return balanceCalculator.calculateIndividualAmounts(db, userId);
    }

    public double getBalanceForUser(int userId) {
        return balanceCalculator.getBalanceForUser(userId);
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
