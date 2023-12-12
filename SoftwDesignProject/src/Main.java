import Controller.ExpenseController;
import Controller.PersonController;
import Expense.Expense;
import Person.Person;
import View.ViewFrame;
import database.ExpenseDatabase;
import database.PersonDatabase;
import Expense.ExpenseDescription;
import Expense.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.E;


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

        ViewFrame view = new ViewFrame();
        view.Initialize();

        PersonDatabase persondb = PersonDatabase.getInstance();
        ExpenseDatabase expensedb = ExpenseDatabase.getInstance();

        Person p1 = new Person(1, "Perry", "PerryPlatypus@work.com", "0485123456");
        Person p2 = new Person(2, "Emma", "Emma@work.com", "0485123456");
        Person p3 = new Person(3, "Mats", "Mats@gmail.com", "0485123456");

        persondb.addEntry(p1);
        persondb.addEntry(p2);
        persondb.addEntry(p3);

        ExpenseDescription description = new ExpenseDescription("Perry", "All good!");

        view.updateView(new PersonController(persondb.getEntry(1),persondb,view));

        //expensedb.addEntry(new UnifiedPayment( 36.3, p1, List<Split> payments, description));


        // ExpenseController expenseController = new ExpenseController();

        /*
        expenseManager.addUser(new Person("u1", "User1", "gaurav@workat.tech", "9876543210"));
        expenseManager.addUser(new Person("u2", "User2", "sagar@workat.tech", "9876543210"));
        expenseManager.addUser(new Person("u3", "User3", "hi@workat.tech", "9876543210"));
        expenseManager.addUser(new Person("u4", "User4", "mock-interviews@workat.tech", "9876543210"));

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();
            String[] commands = command.split(" ");
            String commandType = commands[0];

            switch (commandType) {
                case "SHOW" -> {
                    if (commands.length == 1) {
                        expenseManager.showBalances();
                    } else {
                        expenseManager.showBalance(commands[1]);
                    }
                }
                case "EXPENSE" -> {
                    String paidBy = commands[1];
                    Double amount = Double.parseDouble(commands[2]);
                    int noOfUsers = Integer.parseInt(commands[3]);
                    String expenseType = commands[4 + noOfUsers];
                    List<Split> splits = new ArrayList<>();
                    switch (expenseType) {
                        case "EQUAL" -> {
                            for (int i = 0; i < noOfUsers; i++) {
                                splits.add(new EqualSplit(expenseManager.userMap.get(commands[4 + i])));
                            }
                            expenseManager.addExpense(ExpenseType.EQUAL, amount, paidBy, splits, null);
                        }
                        case "EXACT" -> {
                            for (int i = 0; i < noOfUsers; i++) {
                                splits.add(new ExactSplit(expenseManager.userMap.get(commands[4 + i]), Double.parseDouble(commands[5 + noOfUsers + i])));
                            }
                            expenseManager.addExpense(ExpenseType.EXACT, amount, paidBy, splits, null);
                        }
                        case "PERCENT" -> {
                            for (int i = 0; i < noOfUsers; i++) {
                                splits.add(new PercentSplit(expenseManager.userMap.get(commands[4 + i]), Double.parseDouble(commands[5 + noOfUsers + i])));
                            }
                            expenseManager.addExpense(ExpenseType.PERCENT, amount, paidBy, splits, null);
                        }
                    }
                }
            }
        }
         */


    }
    public void view(Person p){

    }
}