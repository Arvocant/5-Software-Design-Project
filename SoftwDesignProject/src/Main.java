
import Factory.FactoryExpense;
import Payment.EqualPayment;
import Payment.ExactPayment;
import Payment.Split;
import Person.Person;
import database.ExpenseDatabase;
import database.PersonDatabase;
import Expense.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args)
    {
        Main main = new Main();
        main.run();
    }

    public Main()
    {

    }

    public void run() {

        PersonDatabase persondb = PersonDatabase.getInstance();
        ExpenseDatabase expensedb = ExpenseDatabase.getInstance();

        // GUI try
        // StartFrame start = new StartFrame(persondb);

        // Tests w/out GUI

        Person p1 = new Person(1, "Perry", "PerryPlatypus@work.com", "0485123456");
        Person p2 = new Person(2, "Emma", "Emma@work.com", "0485123456");
        Person p3 = new Person(3, "Mats", "Mats@gmail.com", "0485123456");
        

        persondb.addEntry(p1);
        persondb.addEntry(p2);
        persondb.addEntry(p3);
        
        /*
         * p2 owes p1 300
         * p3 owes p1 300
         */
        List<Split> splitList1 = new ArrayList<>(); splitList1.add(new EqualPayment(p2)); splitList1.add(new EqualPayment(p3));
        expensedb.addEntry(FactoryExpense.createExpense(ExpenseType.EQUAL, 900, p1, splitList1 , new ExpenseDescription("Vliegtickets", "payment door 1")));

        process(expensedb, p1, p2, p3);
        /*
         * p1 owes p2 10
         * p3 owes p2 10
         */
        List<Split> splitList2 = new ArrayList<>(); splitList2.add(new EqualPayment(p1)); splitList2.add(new EqualPayment(p3));
        expensedb.addEntry(FactoryExpense.createExpense(ExpenseType.EQUAL, 30, p2, splitList2, new ExpenseDescription("Cinema", "FFAF Movie")));

        process(expensedb, p1, p2, p3);
        /*
         * p2 owes p1 22
         * p3 owes p1 22
         */
        List<Split> splitList3 = new ArrayList<>(); splitList3.add(new EqualPayment(p2)); splitList3.add(new EqualPayment(p3));
        expensedb.addEntry(FactoryExpense.createExpense(ExpenseType.EQUAL, 66, p1, splitList3, new ExpenseDescription("Matching Necklaces", "To remember our friendship")));

        process(expensedb, p1, p2, p3);

        /*
         * p1 owes p3 250
         * p2 owes p3 178
         * p3 paid 72 for himself
         */
        List<Split> splitList4 = new ArrayList<>(); splitList4.add(new ExactPayment(p1, 250)); splitList4.add(new ExactPayment(p2,178));
        expensedb.addEntry(FactoryExpense.createExpense(ExpenseType.EXACT, 500, p3, splitList4, new ExpenseDescription("Shopping", "These idiots forgot their wallet")));

        process(expensedb, p1, p2, p3);

        for (int key : expensedb.getAllExpenses().keySet())
            System.out.println(key);

        /*
         * p1 owes p2 = 10              -> 0
         * p1 owes p3 = 250             -> 0
         * 
         * p2 owes p1 = 300 + 22 = 322  -> 312
         * p2 owes p3 = 178             -> 168
         * 
         * p3 owes p1 = 300 + 22 = 322  -> 72
         * p3 owes p2 = 10              -> 0
         */
        printDebtList(expensedb.calculateTotal());
        System.out.println(expensedb.getBalanceForUser(p3.getId()));
        System.out.println(expensedb.calculateIndividualAmounts(p1.getId()));

    }

    public void printDebtList(Map<Integer, Map<Integer, Double>> balanceSheet) {
        for (int PaidById : balanceSheet.keySet()) {
            Map<Integer, Double> temp = balanceSheet.get(PaidById);
            for (int inDebt : temp.keySet()) {
                System.out.println(inDebt + " has to pay " + temp.get(inDebt) + "euro to " + PaidById);
            }
        }
    }

    public void process(ExpenseDatabase expenseDatabase, Person p1, Person p2, Person p3){
        // Process the expenses
        expenseDatabase.calculateTotal();

        System.out.println("------Get the balances-----");
        System.out.println("p1: " + expenseDatabase.getBalanceForUser(p1.getId()));
        System.out.println("p2: " + expenseDatabase.getBalanceForUser(p2.getId()));
        System.out.println("p3: " + expenseDatabase.getBalanceForUser(p3.getId()));

    }
}
