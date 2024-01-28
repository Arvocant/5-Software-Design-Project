package Expense;

import Payment.Split;
import Person.Person;

import java.util.List;

public abstract class Expense {
    private int id;
    private double amount;
    private Person paidBy;

    private List<Split> payments;
    private ExpenseDescription description;

    public Expense(double amount, Person paidBy, List<Split> payments, ExpenseDescription description) {
        this.amount = amount;
        this.paidBy = paidBy;
        this.payments = payments;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Person getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(Person paidBy) {
        this.paidBy = paidBy;
    }

    public List<Split> getPayments(){
        return payments;
    }

    public void setPayments(List<Split> payments){
        this.payments = payments;
    }

    public ExpenseDescription getDescription() {
        return description;
    }

    public void setDescription(ExpenseDescription description) {
        this.description = description;
    }

    public abstract boolean validate();

    @Override
    public String toString() {
        return this.getDescription().toString();
    }
}
