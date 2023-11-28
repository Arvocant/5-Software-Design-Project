package Controller;

import Expense.Expense;
import Expense.ExpenseDescription;
import Person.Person;
import database.database;
import View.View;

/*
 * The one that will make changes to the view and to the state of the model
 */

public class ExpenseController implements IController {

    private Expense modelExpense;
    private database<Expense> db;

    private View view;

    public ExpenseController(Expense model, database<Expense> db, View view) {
        this.modelExpense = model;
        this.db = db;
        this.view = view;
    }

    @Override
    public void addToDb() {
        this.db.addEntry(this.modelExpense);
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
