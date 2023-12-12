package View;

import Controller.PersonController;
import Expense.Expense;
import Expense.ExpenseDescription;
import Expense.ExpenseType;
import Factory.FactoryExpense;
import Payment.ExactPayment;
import Payment.Split;
import Person.Person;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class ViewFrame extends JFrame {
    GridBagConstraints c;

    public ViewFrame()
    {
        super("Expense Form");
        this.c = new GridBagConstraints();
    }

    public void Initialize() {
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2.0;
        c.weighty = 2.0;
    }

    public void updateView(PersonController pc) {
        ArrayList<Split> splitList = new ArrayList<>();
        // components

        JLabel nameExpenseLabel = new JLabel("Title Expense:");
        JTextField nameExpenseField = new JTextField(20);

        // moet nog in expense gezet worden
        JLabel ticketTypeLabel = new JLabel("Ticket Type:");
        String[] ticketTypes = {"Flight", "Restaurant", "Cinema", "Others"};
        JComboBox<String> ticketTypeComboBox = new JComboBox<>(ticketTypes);

        JLabel radioButtonLabel = new JLabel("Type:");
        JRadioButton equalRadioButton = new JRadioButton("Equal");
        JRadioButton exactRadioButton = new JRadioButton("Exact");
        ButtonGroup radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(equalRadioButton);
        radioButtonGroup.add(exactRadioButton);

        exactRadioButton.addActionListener(e -> {
            JFrame exactFrame = new JFrame();
            exactFrame.setVisible(true);
            exactFrame.setSize(300,300);
            exactFrame.setTitle("Exact Amount");
            exactFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            PanelExact panel = new PanelExact(pc);
            LayoutManager layout = new BoxLayout(panel, BoxLayout.PAGE_AXIS);
            panel.setLayout(layout);
            exactFrame.add(panel);
        });

        JLabel amountLabel = new JLabel("Amount:");
        JTextField amountField = new JTextField(20);

        JLabel paidByLabel = new JLabel("Paid By:");
        String[] paidByList = pc.getAllPersons();
        JComboBox<String> paidByComboBox = new JComboBox<>(paidByList);

        JLabel descriptionLabel = new JLabel("Description:");
        JTextArea descriptionTextArea = new JTextArea(5, 20);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            ExpenseDescription newDescr = new ExpenseDescription(nameExpenseField.getText(),descriptionTextArea.getText());

            ExpenseType type = ExpenseType.valueOf(equalRadioButton.isSelected() ? "EQUAL" : "EXACT");

            double amount = Double.parseDouble(amountField.getText());

            String stringPaidBy = (String) paidByComboBox.getSelectedItem();
            Person paidBy = pc.getPersonOutOfDb(stringPaidBy);

            Expense newAddedExpense = FactoryExpense.createExpense(type, amount, paidBy, splitList, newDescr);

            //System.out.println("Ticket Type: " + ticketType);
            System.out.println("Type: " + type);
            System.out.println("Amount: " + amount);
            System.out.println("Paid By: " + stringPaidBy);
            System.out.println("Description: " + newDescr.toString());
        });

        {
                // Add components to the frame
                c.gridx = 0;
                c.gridy = 0;
                c.gridwidth = 1;
                c.gridheight = 1;
                add(nameExpenseLabel, c);
                c.gridx = 1;
                c.gridy = 0;
                c.gridwidth = 2;
                c.gridheight = 1;
                add(nameExpenseField, c);
                c.gridx = 0;
                c.gridy = 1;
                c.gridwidth = 1;
                c.gridheight = 1;
                add(ticketTypeLabel, c);
                c.gridx = 1;
                c.gridy = 1;
                c.gridwidth = 2;
                c.gridheight = 1;
                add(ticketTypeComboBox, c);
                c.gridx = 0;
                c.gridy = 2;
                c.gridwidth = 1;
                c.gridheight = 1;
                add(radioButtonLabel, c);
                c.gridx = 1;
                c.gridy = 2;
                c.gridwidth = 1;
                c.gridheight = 1;
                add(equalRadioButton, c);
                c.gridx = 2;
                c.gridy = 2 ;
                c.gridwidth = 1;
                c.gridheight = 1;
                add(exactRadioButton, c);
                c.gridx = 0;
                c.gridy = 4;
                c.gridwidth = 1;
                c.gridheight = 1;
                add(amountLabel, c);
                c.gridx = 1;
                c.gridy = 4;
                c.gridwidth = 2;
                c.gridheight = 1;
                add(amountField, c);
                c.gridx = 0;
                c.gridy = 5;
                c.gridwidth = 1;
                c.gridheight = 1;
                add(paidByLabel, c);
                c.gridx = 1;
                c.gridy = 5;
                c.gridwidth = 2;
                c.gridheight = 1;
                add(paidByComboBox, c);
                c.gridx = 0;
                c.gridy = 6;
                c.gridwidth = 3;
                c.gridheight = 1;
                add(descriptionLabel, c);
                c.gridx = 0;
                c.gridy = 7;
                c.gridwidth = 3;
                c.gridheight = 1;
                add(descriptionTextArea, c);
                c.gridx = 0;
                c.gridy = 8;
                c.gridwidth = 3;
                c.gridheight = 1;
                add(addButton, c);

                // Set the frame to be visible
                setVisible(true);
            }

    }

}
