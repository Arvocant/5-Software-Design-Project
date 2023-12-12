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
            } case EQUAL -> {
                // code to split the sum equally
                int totalPayments = payments.size();
                double howMuchOwed = ((double) Math.round(amount*100/totalPayments))/100.0;
                for (Split payment : payments) {
                    payment.setAmount(howMuchOwed);
                }
                payments.get(0).setAmount(howMuchOwed + (amount - howMuchOwed*totalPayments));
                return new UnifiedPayment(howMuchOwed, paidBy, payments, expenseDescription);
            } default -> {
                return null;
            }
        }
    }
}
