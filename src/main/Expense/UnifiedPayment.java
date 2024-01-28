package Expense;

import Payment.Split;
import Person.Person;
import Payment.*;

import java.util.List;

public class UnifiedPayment extends Expense{

    public UnifiedPayment(double amount, Person paidBy, List<Split> payments, ExpenseDescription description) {
        super(amount, paidBy, payments ,description);
    }

    @Override
    public boolean validate() { //Factory Pattern
        for (Split payment : getPayments()){
           if (payment instanceof EqualPayment){
               return true;
           }
        }
        return false;
    }
}
