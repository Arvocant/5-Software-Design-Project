package Expense;

import Payment.Split;

import java.util.HashMap;
import java.util.Map;

public class BalanceCalculator {

    Map<Integer, Map<Integer, Double>> balanceSheet = new HashMap<>();

    public void processExpense(Expense expense) {
        for (Split split : expense.getPayments()) {
            int inDebtId = split.getPerson().getId();
            
            // calculations for the money the indebted have to pay
            updateBalance(balanceSheet, expense.getPaidBy().getId(), inDebtId, split.getAmount());
        
            // calculations for the out weighted money the PaidBy lost/wins
            updateBalance(balanceSheet, inDebtId, inDebtId, -split.getAmount());
        }
    }

    public Map<Integer, Map<Integer, Double>> calculateTotal(Map<Integer, Expense> expenses) {
        // Clear the balanceSheet before calculating balances for each expense
        balanceSheet.clear();

        for (Expense expenseInDb : expenses.values()) {
            processExpense(expenseInDb);
        }
        return balanceSheet;
    }


    public Map<Integer, Map<Integer, Double>> calculateIndividualAmounts(Map<Integer, Expense> expenses, int userId) {
        Map<Integer, Map<Integer, Double>> individualAmounts = new HashMap<>();

        for (Expense expenseInDb : expenses.values()) {
            processExpense(expenseInDb);

            for (Split split : expenseInDb.getPayments()) {
                int inDebtId = split.getPerson().getId();

                // calculations for the individual amounts
                if (userId == expenseInDb.getPaidBy().getId()) {
                    // If the user paid the expense, deduct the amount from their balance
                    updateIndividualAmounts(individualAmounts, userId, inDebtId, -split.getAmount());
                } else if (userId == inDebtId) {
                    // If the user is in debt, add the amount to their balance
                    updateIndividualAmounts(individualAmounts, userId, expenseInDb.getPaidBy().getId(), split.getAmount());
                }
            }
        }

        return individualAmounts;
    }

    private void updateIndividualAmounts(Map<Integer, Map<Integer, Double>> individualAmounts, int fromUserId, int toUserId, double amount) {
        if (!individualAmounts.containsKey(fromUserId)) {
            individualAmounts.put(fromUserId, new HashMap<>());
        }

        Map<Integer, Double> balances = individualAmounts.get(fromUserId);
        balances.put(toUserId, balances.getOrDefault(toUserId, 0.0) + amount);
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

        if (balanceSheet.containsKey(userId)) {
            Map<Integer, Double> balances = balanceSheet.get(userId);
            for (double balance : balances.values()) {
                totalBalance += balance;
            }
        }

        return totalBalance;
    }
}