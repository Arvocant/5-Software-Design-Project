package Factory;

import Expense.*;
import Payment.Split;
import Person.Person;

import java.util.List;

/*
 * is the equivalent of the ExpenseService from our example
 */

public class FactoryExpense {

    public static Expense createExpense(ExpenseType expenseType, double amount, Person paidBy, List<Split> payments, ExpenseDescription expenseDescription) {
        switch (expenseType) {
            case EXACT -> {
                return new PersonalPayment(amount, paidBy, payments, expenseDescription);
            }
            case EQUAL ->{
                int totalSplits = payments.size();
                double splitAmount = ((double) Math.round(amount * 100 / (totalSplits+1))) / 100.0;

                for (int i = 0; i < totalSplits; i++) {
                    payments.get(i).setAmount(splitAmount);
                }

                 return new UnifiedPayment(amount, paidBy, payments, expenseDescription);
            } default -> {
                return null;
            }
        }
    }
}
