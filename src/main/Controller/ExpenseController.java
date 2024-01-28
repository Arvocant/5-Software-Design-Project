package Controller;

import Expense.Expense;
import View.ControllerViews.ExpenseFrame;
import Expense.BalanceCalculator;
import database.database;

import java.util.Map;

public class ExpenseController implements IController {

    private final database<Expense> db;
    private final ExpenseFrame view;

    private final BalanceCalculator balanceCalculator;

    public ExpenseController(database<Expense> dbModel, ExpenseFrame view, BalanceCalculator balanceCalculator) {
        this.db = dbModel;
        this.view = view;
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
        view.updatePanel(this.db.getAllItems());
    }
}