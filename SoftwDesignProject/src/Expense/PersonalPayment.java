package Expense;

import Person.Person;

public class PersonalPayment extends Expense{

    public PersonalPayment(String id, double amount, Person paidBy, ExpenseDescription description) {
        super(id, amount, paidBy, description);
    }

    @Override
    public boolean validate() {
        return false;
    }

}
