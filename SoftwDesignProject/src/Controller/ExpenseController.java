package Controller;

import Expense.Expense;
import Expense.BalanceCalculator;
import View.Panels.ExpensePanel;
import database.database;

import java.util.HashMap;
import java.util.Map;

/*
 * The one that will make changes to the view and to the state of the model
 */

public class ExpenseController implements IController {

    private database<Expense> db;
    private Map<Integer, Map<String, Double>> balance;
    private ExpensePanel view;

    private BalanceCalculator balanceCalculator;

    public ExpenseController(database<Expense> dbModel, ExpensePanel view, BalanceCalculator balanceCalculator) {
        this.db = dbModel;
        this.view = view;
        this.balance = new HashMap<Integer, Map<String, Double>>();
        this.balanceCalculator = balanceCalculator;
    }

    public void addToDb(Expense expense) {  
        this.db.addEntry(expense);
    }

    // Handle user input to show balances
    public void showBalances() {
        // Assuming you have a reference to your ExpenseDatabase
        Map<Integer, Expense> expenses = this.db.getAllItems();

        // Calculate balances using the BalanceCalculator
        Map<Integer, Map<Integer, Double>> balanceSheet = this.balanceCalculator.calculateTotal(expenses);
        System.out.println(balanceSheet);
        // Do something with the calculated balances...
        // For example, pass the balanceSheet to the view for display
        // --Misschien zo toe te voegen in de view? extra element toevoegen mss? view.displayBalances(balanceSheet);
    }

    // all get and set methods from here down

    public database<Expense> getModel() {
        return this.db;
    }
    
    public Expense getExpense(int id) {
        return this.db.getItem(id);
    }

    public Expense getEntryByName(String name) {
        return this.db.getEntryByName(name);
    }

    public Map<Integer, Expense> getAllExpenses() {
        return this.db.getAllItems();
    }
   
    public BalanceCalculator getBalanceCalculator() {
        return this.balanceCalculator;
    }


    @Override
    public void updateView() {
        //view.update();
    }


}