package Expense;

import Payment.Split;

import java.util.HashMap;
import java.util.Map;

public class BalanceCalculator {

    public Map<Integer, Map<Integer, Double>> calculateTotal(Map<Integer, Expense> expenses) {
        Map<Integer, Map<Integer, Double>> balanceSheet = new HashMap<>();

        for (Expense expenseInDb : expenses.values()) {
            for (Split split : expenseInDb.getPayments()) {
                int inDebtId = split.getPerson().getId();

                // calculations for the money the indebted have to pay
                updateBalance(balanceSheet, expenseInDb.getPaidBy().getId(), inDebtId, split.getAmount());

                // calculations for the out weighted money the PaidBy lost/wins
                updateBalance(balanceSheet, inDebtId, inDebtId, -split.getAmount());
            }
        }

        return balanceSheet;
    }

    private void updateBalance(Map<Integer, Map<Integer, Double>> balanceSheet, int fromUserId, int toUserId, double amount) {
        if (!balanceSheet.containsKey(fromUserId)) {
            balanceSheet.put(fromUserId, new HashMap<>());
        }

        Map<Integer, Double> balances = balanceSheet.get(fromUserId);
        if (!balances.containsKey(toUserId)) {
            balances.put(toUserId, 0.0);
        }

        balances.put(toUserId, balances.get(toUserId) + amount);
    }
}

