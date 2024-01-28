package View;

import javax.swing.*;

import Controller.ExpenseController;
import Controller.PersonController;
import Expense.BalanceCalculator;
import Person.Person;

import java.awt.*;

public class ManagerFrame extends JFrame {
    GridBagConstraints c;
    PersonController pc;
    ExpenseController ec;
    
    public ManagerFrame(PersonController pc, ExpenseController ec) {
        super("Manager");
        this.c = new GridBagConstraints();
        this.pc = pc;
        this.ec = ec;
        
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setVisible(true);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2.0;
        c.weighty = 2.0;

        JButton makeExpenseButton = new JButton("Make new expense");
        c.gridx = 0; c.gridy = 0;
        this.add(makeExpenseButton,c);
        makeExpenseButton.addActionListener(listener ->{
            new MakeExpenseFrame(this.pc, this.ec);
            System.out.println("Making new expense");
        });

        JButton editUsersButton = new JButton("Edit users");
        c.gridx = 1;
        this.add(editUsersButton,c);
        editUsersButton.addActionListener(listener ->{
            new UserChoiceFrame(this.pc);
        });

        JButton calculateTotalButton = new JButton("Calculate total");
        c.gridx = 1; c.gridy = 1;
        this.add(calculateTotalButton,c);
        calculateTotalButton.addActionListener(listener -> {
            BalanceCalculator BC = this.ec.getBalanceCalculator();
            for (Person p : this.pc.getAllPerson().values()) {
                System.out.println(BC.calculateIndividualAmounts(ec.getAllExpenses(),p.getId()));
            }
            System.out.println(this.pc.getAllPerson());
        });

        JButton showProfileButton = new JButton("Show Profiles/Expenses");
        c.gridx = 0;
        this.add(showProfileButton,c);
        showProfileButton.addActionListener(l-> {
            this.pc.updateView();
            this.ec.updateView();
        });

    }
}
