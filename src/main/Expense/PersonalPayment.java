package Expense;

import Payment.ExactPayment;
import Payment.Split;
import Person.Person;

import java.util.List;

public class PersonalPayment extends Expense{

    public PersonalPayment(double amount, Person paidBy, List<Split> payments, ExpenseDescription description) {
        super(amount, paidBy, payments, description);
    }

    @Override
    public boolean validate() { //Factory Pattern
        //for (Split payments : getPayments()){
        //  if (!(payments instanceof ExactPayment))
        //      return false;
        //}

        double total = getAmount();
        double sum = 0;
        for (Split payment : getPayments()){
            ExactPayment exactAmount = (ExactPayment) payment;
            sum += exactAmount.getAmount();
        }

        return total == sum; //return's true if value of total equals that of sum
    }

}
