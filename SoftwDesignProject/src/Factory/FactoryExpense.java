package Factory;

import Expense.*;

/*
 * is the equivalent of the ExpenseService from our example
 */

public class FactoryExpense {
    public static <Person> Expense createExpense(Type expenseType, double amount, Person paidBy, ExpenseDescription expenseDescription) {
        switch (expenseType) {
            case EXACT -> {
                return new PersonalPayment(, amount, paidBy, expenseDescription);
            } case EQUAL -> {
                // code to split the sum equally
                /*
                 * int totalSplits = splits.size();
                 * double splitAmount = ((double) Math.round(amount*100/totalSplits))/100.0;
                 * for (Split split : splits) {
                 *     split.setAmount(splitAmount);
                 * }
                 * splits.get(0).setAmount(splitAmount + (amount - splitAmount*totalSplits));
                 */
                double amountOfPeople = 1;
                double splitAmount = amount / amountOfPeople;
                return new UnifiedPayment(, splitAmount, paidBy, expenseDescription);
            } default -> {
                return null;
            }
        }
    }
}
