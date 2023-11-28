package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    private JTextField titleField, nameField, amountField, paidByField;
    private JComboBox<String> ticketTypeComboBox;
    private JRadioButton equalRadioButton, exactRadioButton;
    private ButtonGroup radioButtonGroup;
    private JTextArea descriptionTextArea;

    public Menu() {
        // Set up the frame
        setTitle("Expense Form");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Components
        JLabel titleLabel = new JLabel("Title:");
        titleField = new JTextField(20);

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);

        JLabel ticketTypeLabel = new JLabel("Ticket Type:");
        String[] ticketTypes = {"Flight", "Restaurant", "Cinema", "Others"};
        ticketTypeComboBox = new JComboBox<>(ticketTypes);

        JLabel radioButtonLabel = new JLabel("Type:");
        equalRadioButton = new JRadioButton("Equal");
        exactRadioButton = new JRadioButton("Exact");
        radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(equalRadioButton);
        radioButtonGroup.add(exactRadioButton);

        JLabel amountLabel = new JLabel("Amount:");
        amountField = new JTextField(20);

        JLabel paidByLabel = new JLabel("Paid By:");
        paidByField = new JTextField(20);

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionTextArea = new JTextArea(5, 20);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the button click (you can add your logic here)
                // For example, retrieve values from the fields
                String title = titleField.getText();
                String name = nameField.getText();
                String ticketType = (String) ticketTypeComboBox.getSelectedItem();
                String type = equalRadioButton.isSelected() ? "Equal" : "Exact";
                double amount = Double.parseDouble(amountField.getText());
                String paidBy = paidByField.getText();
                String description = descriptionTextArea.getText();

                // Print the values (replace this with your actual logic)
                System.out.println("Title: " + title);
                System.out.println("Name: " + name);
                System.out.println("Ticket Type: " + ticketType);
                System.out.println("Type: " + type);
                System.out.println("Amount: " + amount);
                System.out.println("Paid By: " + paidBy);
                System.out.println("Description: " + description);
            }
        });

        // Add components to the frame
        add(titleLabel);
        add(titleField);
        add(nameLabel);
        add(nameField);
        add(ticketTypeLabel);
        add(ticketTypeComboBox);
        add(radioButtonLabel);
        add(equalRadioButton);
        add(exactRadioButton);
        add(amountLabel);
        add(amountField);
        add(paidByLabel);
        add(paidByField);
        add(descriptionLabel);
        add(descriptionTextArea);
        add(addButton);

        // Set the frame to be visible
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Menu();
            }
        });
    }
}
