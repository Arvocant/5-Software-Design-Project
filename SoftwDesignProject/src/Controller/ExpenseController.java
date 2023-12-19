package Controller;

import Expense.Expense;
import Expense.ExpenseDescription;
import Expense.BalanceCalculator;
import Payment.Split;
import Person.Person;
import View.ViewFrame;
import database.database;

import java.util.HashMap;
import java.util.Map;

/*
 * The one that will make changes to the view and to the state of the model
 */

public class ExpenseController implements IController {

    private Expense modelExpense;
    private database<Expense> db;

    private Map<Integer, Map<String, Double>> balance;
    private ViewFrame view;

    private BalanceCalculator balanceCalculator;

    public ExpenseController(Expense model, database<Expense> db, ViewFrame view, BalanceCalculator balanceCalculator) {
        this.modelExpense = model;
        this.db = db;
        this.view = view;
        this.balance = new HashMap<Integer, Map<String, Double>>();
        this.balanceCalculator = balanceCalculator;
    }

    @Override
    public void addToDb() {
        this.db.addEntry(this.modelExpense); //Niet helemaal mee waarom je de modelExpense meegeeft,
    }                                        // gebruiker zou deze eig. enkel als "template" mogen gebruiken, en dan de parameter expense meegeven.

    // Handle user input to add an expense
    public void addExpense(Expense expense) {  //Is dit niet beter? hier geven we een parameter mee
        db.addEntry(expense);
    }

    // Handle user input to show balances
    public void showBalances() {
        // Assuming you have a reference to your ExpenseDatabase
        Map<Integer, Expense> expenses = db.getAllExpenses();

        // Calculate balances using the BalanceCalculator
        Map<Integer, Map<Integer, Double>> balanceSheet = balanceCalculator.calculateTotal(expenses);

        // Do something with the calculated balances...
        // For example, pass the balanceSheet to the view for display
        // --Misschien zo toe te voegen in de view? extra element toevoegen mss? view.displayBalances(balanceSheet);
    }

    // all get and set methods from here down

    public int getIdExpense() {
        return modelExpense.getId();
    }

    public void setIdExpense(int id) {
        modelExpense.setId(id);
    }

    public double getAmountExpense() {
        return modelExpense.getAmount();
    }

    public void setAmountExpense(double amount) {
        modelExpense.setAmount(amount);
    }

    public Person getPaidBy() {
        return modelExpense.getPaidBy();
    }

    public void setPaidBy(Person paidBy) {
        modelExpense.setPaidBy(paidBy);
    }

    public ExpenseDescription getExpenseDescription() {
        return modelExpense.getDescription();
    }

    public void setDescription(ExpenseDescription description) {
        modelExpense.setDescription(description);
    }
    @Override
    public void updateView() {
        //view.update();
    }


}
