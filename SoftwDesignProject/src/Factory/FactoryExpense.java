package Factory;

import Expense.*;
import Person.Person;

/*
 * is the equivalent of the ExpenseService from our example
 */

public class FactoryExpense {

    private static int ID;
    public static Expense createExpense(Type expenseType, double amount, Person paidBy, ExpenseDescription expenseDescription) {
        switch (expenseType) {
            case EXACT -> {
                ID++;
                return new PersonalPayment(ID, amount, paidBy, expenseDescription);
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
                ID++;
                double amountOfPeople = 1;
                double splitAmount = amount / amountOfPeople;
                return new UnifiedPayment(ID, splitAmount, paidBy, expenseDescription);
            } default -> {
                return null;
            }
        }
    }
}
