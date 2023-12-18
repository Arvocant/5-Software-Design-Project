package database;

import Expense.Expense;
import Payment.Split;
import Person.Person;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseDatabase extends database<Expense>{

    private Map<Integer, Expense> db;
    private static ExpenseDatabase instance; //Singleton --> 1 instance

    private static int nextExpenseId = 0; // Next available expense ID

    private ExpenseDatabase() { this.db = new HashMap<>(); } //Constructor --> make new database (which is a HashMap)

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
    public Expense getEntry(int id) {
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
    public Map<Integer, Expense> getDb() {
        return db;
    }

    public static int getNextExpenseId() {
        return nextExpenseId++;
    }


    public Map<Integer, Map<Integer, Double>> calculateTotal() {
        /// To Do: schrijf code om totaal te berekenen
        Map<Integer, Map<Integer, Double>> balanceSheet = new HashMap<Integer, Map<Integer, Double>>();

        for (Expense expenseInDb : db.values()) {
            for (Split split : expenseInDb.getPayments()) {
                int inDebtId = split.getPerson().getId();
                
                // calculations for the money the in debted have to pay
                if (! balanceSheet.containsKey(expenseInDb.getPaidBy().getId())) {                      // if PaidBy not in balanceSheet yet
                    balanceSheet.put(expenseInDb.getPaidBy().getId(), new HashMap<Integer, Double>());  // add PaidBy to balanceSheet
                }
                Map<Integer, Double> balancesInDebtToPaidBy = balanceSheet.get(expenseInDb.getPaidBy().getId());
                if (! balancesInDebtToPaidBy.containsKey(inDebtId)) {                                   // if inDebt is not in balancesInDebtToPaidBy yet
                    balancesInDebtToPaidBy.put(inDebtId, 0.0);                                    // add inDebt to the indebted list
                }
                balancesInDebtToPaidBy.put(inDebtId, balancesInDebtToPaidBy.get(inDebtId) + split.getAmount());

                // calculations for the outweighted money the PaidBy lost/wins idk how to explain it propperly
                if (! balanceSheet.containsKey(inDebtId)) {                      // if InDebt not in balanceSheet yet
                    balanceSheet.put(inDebtId, new HashMap<Integer, Double>());  // add InDebt to balanceSheet
                }
                Map<Integer, Double> balancesPaidByToIndebted = balanceSheet.get(inDebtId);
                if (! balancesPaidByToIndebted.containsKey(inDebtId)) {                                   // if inDebt is not in balancesInDebtToPaidBy yet
                    balancesPaidByToIndebted.put(inDebtId, 0.0);                                    // add inDebt to the indebted list
                }
                balancesPaidByToIndebted.put(inDebtId, balancesPaidByToIndebted.get(inDebtId) - split.getAmount());
            }
        }

        return balanceSheet;

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
