package View;

import javax.swing.*;

import Controller.ExpenseController;
import Controller.PersonController;
import Expense.BalanceCalculator;
import Expense.Expense;
import Person.Person;

import java.awt.*;
import java.util.Map;

public class ManagerFrame extends JFrame {
    GridBagConstraints c;
    PersonController pc;
    ExpenseController ec;
    
    public ManagerFrame(PersonController pc, ExpenseController ec) {
        super("Manager");
        this.c = new GridBagConstraints();
        this.pc = pc;
        this.ec = ec;
        
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setVisible(true);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2.0;
        c.weighty = 2.0;

        JButton makeExpenseButton = new JButton("Make new expense");
        c.gridx = 0;
        this.add(makeExpenseButton,c);
        makeExpenseButton.addActionListener(listener ->{
            new MakeExpenseFrame(this.pc, this.ec);
            System.out.println("Making new expense");
        });

        JButton editUsersButton = new JButton("Edit users");
        c.gridx = 1;
        this.add(editUsersButton,c);

        JButton calculateTotalButton = new JButton("Calculate total");
        c.gridx = 2;
        this.add(calculateTotalButton,c);
        calculateTotalButton.addActionListener(listener -> {
            BalanceCalculator BC = ec.getBalanceCalculator();
            Map<Integer, Map<Integer, Double>> balanceSheet = BC.calculateTotal(ec.getAllExpenses());
            for (Person p : pc.getAllPerson().values()) {
                System.out.println(BC.calculateIndividualAmounts(ec.getAllExpenses(),p.getId()));
            }
        });

    }
}
