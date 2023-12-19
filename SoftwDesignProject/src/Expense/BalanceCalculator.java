package Expense;

import Payment.Split;

import java.util.HashMap;
import java.util.Map;

public class BalanceCalculator {

    Map<Integer, Map<Integer, Double>> balanceSheet = new HashMap<>();

    public Map<Integer, Map<Integer, Double>> calculateTotal(Map<Integer, Expense> expenses) {
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

    public Map<Integer, Map<Integer, Double>> calculateIndividualAmounts(Map<Integer, Expense> expenses) {
        Map<Integer, Map<Integer, Double>> individualAmounts = new HashMap<>();

        for (Expense expenseInDb : expenses.values()) {
            for (Split split : expenseInDb.getPayments()) {
                int inDebtId = split.getPerson().getId();

                // calculations for the individual amounts
                updateBalance(individualAmounts, expenseInDb.getPaidBy().getId(), inDebtId, split.getAmount());
            }
        }

        return individualAmounts;
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

    public double getBalanceForUser(int userId) {
        double totalBalance = 0.0;

        for (Map<Integer, Double> balances : balanceSheet.values()) {
            if (balances.containsKey(userId)) {
                totalBalance += balances.get(userId);
            }
        }

        return totalBalance;
    }
}