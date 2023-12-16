package Controller;

import Expense.Expense;
import Expense.ExpenseDescription;
import Person.Person;
import View.ViewFrame;
import database.database;
import View.Menu;

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

    public ExpenseController(Expense model, database<Expense> db, ViewFrame view) {
        this.modelExpense = model;
        this.db = db;
        this.view = view;
        this.balance = new HashMap<Integer, Map<String, Double>>();
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

    public void showBalance(Integer personId){
        boolean empty = true; //We assume the balance is always empty until proven otherwise by the for loop --> avoid unwanted nullreferences
        for (Map.Entry<String, Double> userBalance : balance.get(personId).entrySet()){
            if (userBalance.getValue() != 0) { //Balance can either be positive or negative
                empty = false;
                //Print the balance to our view
                break;
            }
        }
        if (empty)
            System.out.println("Balance is empty");
    }

    public void getFullBalance(){
        boolean empty = true;
        for (Map.Entry<Integer, Map<String, Double>> allBalances : balance.entrySet()){
            for (Map.Entry<String, Double> userBalance : allBalances.getValue().entrySet()){
                if (userBalance.getValue() != 0) {
                    empty = false;
                    //Print allBalances to view
                    break;
                }
            }
        }
        if (empty)
            System.out.println("There is no balance");
    }

    @Override
    public void updateView() {
        //view.update();
    }


}
