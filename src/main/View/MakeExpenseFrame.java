package View;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Controller.ExpenseController;
import Controller.PersonController;
import Expense.Expense;
import Expense.ExpenseDescription;
import Expense.ExpenseType;
import Factory.FactoryExpense;
import Payment.EqualPayment;
import Payment.ExactPayment;
import Payment.Split;
import Person.Person;
import View.CheckBox.CheckboxAction;

/// To-Do: Zorg dat het deselcten van de members voor split uit de Arraylist kan gehaald worden -> overstappen op map?

public class MakeExpenseFrame extends JFrame{
    GridBagConstraints c;
    PersonController pc;
    ExpenseController ec;

    private ArrayList<Split> splitList;
    private Map<Integer, JTextField> textFieldMap = new HashMap<>();
    private JFrame exactFrame;

    public MakeExpenseFrame(PersonController pc, ExpenseController ec)
    {
        super("main.Expense Form");
        this.c = new GridBagConstraints();
        this.pc = pc;
        this.ec = ec;
        this.splitList = new ArrayList<>();

        this.setLocation(500, 10);

        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setVisible(true);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2.0;
        c.weighty = 2.0;

        c.gridx = 0; c.gridy = 1;
        this.makeExpense();
    }

    public void makeExpense() {
        JPanel makeExpensePanel = new JPanel();
        makeExpensePanel.setLayout(new GridBagLayout());
        makeExpensePanel.setSize(200, 600);
        c.gridx = 0; c.gridy = 0;
        this.add(makeExpensePanel,c);

        JLabel paidByLabel = new JLabel("Paid By:");
        c.gridx = 0; c.gridy = 0; makeExpensePanel.add(paidByLabel,c);

        // function to make String[]
            ArrayList<String> AllPerson = new ArrayList<>();
            Map<Integer, Person> mapList = pc.getAllPerson();
            for (Person value : mapList.values()) {
                AllPerson.add(value.toString());
            }
        
        JComboBox<String> paidByComboBox = new JComboBox<>(AllPerson.toArray(new String[0]));
        c.gridx = 1; c.gridy = 0; c.gridwidth = 2; makeExpensePanel.add(paidByComboBox,c);

        JLabel nameExpenseLabel = new JLabel("Title Expense:");
        c.gridx = 0; c.gridy = 1; makeExpensePanel.add(nameExpenseLabel,c);

        JTextField nameExpenseField = new JTextField(20);
        c.gridx = 1; c.gridy = 1; c.gridwidth = 2; makeExpensePanel.add(nameExpenseField,c);

        JLabel descriptionLabel = new JLabel("Description:");
        c.gridx = 0; c.gridy = 2; makeExpensePanel.add(descriptionLabel,c);

        JTextArea descriptionTextArea = new JTextArea(5, 20);
        c.gridx = 0; c.gridy = 3; c.gridwidth = 3; makeExpensePanel.add(descriptionTextArea,c);

        JLabel ticketTypeLabel = new JLabel("Ticket Type:");
        c.gridx = 0; c.gridy = 4; makeExpensePanel.add(ticketTypeLabel,c);

        String[] ticketTypes = {"Flight", "Restaurant", "Cinema", "Others"};
        JComboBox<String> ticketTypeComboBox = new JComboBox<>(ticketTypes);
        c.gridx = 1; c.gridy = 4; c.gridwidth = 2; makeExpensePanel.add(ticketTypeComboBox,c);

        JLabel amountLabel = new JLabel("Amount:");
        c.gridx = 0; c.gridy = 5; makeExpensePanel.add(amountLabel,c);
        JTextField amountField = new JTextField(20);
        c.gridx = 1; c.gridy = 5; c.gridwidth = 2; makeExpensePanel.add(amountField,c);

        JPanel userSelectPanel = new JPanel();
        CheckboxAction listenerCheckBox = new CheckboxAction("Enable logging");
        for (int i = 0; i < AllPerson.size(); i++) {
            JCheckBox checkBox = new JCheckBox(AllPerson.get(i));
            checkBox.addActionListener(listenerCheckBox);
            userSelectPanel.add(checkBox);
        }
        c.gridx = 1; c.gridy = 6; c.gridwidth = 2; makeExpensePanel.add(userSelectPanel,c);

        JLabel radioButtonLabel = new JLabel("Type:");
        c.gridx = 0; c.gridy = 7; makeExpensePanel.add(radioButtonLabel,c);

        JRadioButton equalRadioButton = new JRadioButton("Equal");
        c.gridx = 1; c.gridy = 7; c.gridwidth = 1; makeExpensePanel.add(equalRadioButton,c);

        JRadioButton exactRadioButton = new JRadioButton("Exact");
        c.gridx = 2; c.gridy = 7; c.gridwidth = 1; makeExpensePanel.add(exactRadioButton,c);
        exactRadioButton.addActionListener(listener -> {
            // creates an external frame to add the exact values
            this.exactFrame = new JFrame("Exact Values");
            exactFrame.setVisible(true);
            exactFrame.setSize(200, 200);
            exactFrame.setLayout(new BoxLayout(exactFrame.getContentPane(), BoxLayout.Y_AXIS));
            for (int i = 0; i < listenerCheckBox.getList().size(); i++) {
                JLabel exactNameLabel = new JLabel(listenerCheckBox.getList().get(i));
                exactFrame.add(exactNameLabel);
                JTextField exactValueField = new JTextField();
                exactFrame.add(exactValueField);
                this.textFieldMap.put(i, exactValueField);
            }
        });


        ButtonGroup radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(equalRadioButton);
        radioButtonGroup.add(exactRadioButton);


        JButton addButton = new JButton("Make Expense");
        c.gridx = 0; c.gridy = 9; c.gridwidth = 3; makeExpensePanel.add(addButton,c);
        addButton.addActionListener(e -> {

            // make the exact split or make the equal split
            if (equalRadioButton.isSelected()) {
                for (String nameUser : listenerCheckBox.getList()) {
                    this.splitList.add(new EqualPayment(pc.getEntryByName(nameUser)));
                }
            } else if (exactRadioButton.isSelected()) {
                int i = 0;
                for (String nameUser : listenerCheckBox.getList()) {
                    this.splitList.add(new ExactPayment(pc.getEntryByName(nameUser), Double.parseDouble(this.textFieldMap.get(i).getText())));
                    i++;
                    exactFrame.dispose();
                }
            }

            String stringPaidBy = (String) paidByComboBox.getSelectedItem();
            Person paidBy = pc.getEntryByName(stringPaidBy);

            ExpenseDescription newDescr = new ExpenseDescription(nameExpenseField.getText(),descriptionTextArea.getText());
            ExpenseType type = ExpenseType.valueOf(equalRadioButton.isSelected() ? "EQUAL" : "EXACT");
            double amount = Double.parseDouble(amountField.getText());

            Expense newExpense = FactoryExpense.createExpense(type, amount, paidBy, splitList, newDescr);
            this.ec.addToDb(newExpense);

            this.dispose();
        });
    
        JButton cancelButton = new JButton("Cancel");
        c.gridx = 0; c.gridy = 10; c.gridwidth = 3; makeExpensePanel.add(cancelButton,c);
        cancelButton.addActionListener(e -> {
            this.dispose();
        });
    }

}
