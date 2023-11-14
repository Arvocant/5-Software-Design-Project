package Expense;

import Person.Person;

public abstract class Expense {
    private String id;
    private double amount;
    private Person paidBy;
    //private List<Split> splits;
    private ExpenseDescription description;

    public Expense(String id, double amount, Person paidBy, ExpenseDescription description) {
        this.id = id;
        this.amount = amount;
        this.paidBy = paidBy;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public ExpenseDescription getDescription() {
        return description;
    }

    public void setDescription(ExpenseDescription description) {
        this.description = description;
    }

    public abstract boolean validate();
}
