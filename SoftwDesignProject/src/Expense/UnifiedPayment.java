package Expense;

import Person.Person;

public class UnifiedPayment extends Expense{

    public UnifiedPayment(int id, double amount, Person paidBy, ExpenseDescription description) {
        super(id, amount, paidBy, description);
    }

    @Override
    public boolean validate() {
        return false;
    }
}
