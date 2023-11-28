
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
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2.0;
        c.weighty = 2.0;

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
        c.gridx = 0; c.gridy = 0;
        c.gridwidth = 1; c.gridheight = 1;
        add(titleLabel,c);
        c.gridx = 1; c.gridy = 0;
        c.gridwidth = 2; c.gridheight = 1;
        add(titleField,c);
        c.gridx = 0; c.gridy = 1;
        c.gridwidth = 1; c.gridheight = 1;
        add(nameLabel,c);
        c.gridx = 1; c.gridy = 1;
        c.gridwidth = 2; c.gridheight = 1;
        add(nameField,c);
        c.gridx = 0; c.gridy = 2;
        c.gridwidth = 1; c.gridheight = 1;
        add(ticketTypeLabel,c);
        c.gridx = 1; c.gridy = 2;
        c.gridwidth = 2; c.gridheight = 1;
        add(ticketTypeComboBox,c);

        c.gridx = 0; c.gridy = 3;
        c.gridwidth = 1; c.gridheight = 1;
        add(radioButtonLabel,c);
        c.gridx = 1; c.gridy = 3;
        c.gridwidth = 1; c.gridheight = 1;
        add(equalRadioButton,c);
        c.gridx = 2; c.gridy = 3;
        c.gridwidth = 1; c.gridheight = 1;
        add(exactRadioButton,c);

        c.gridx = 0; c.gridy = 4;
        c.gridwidth = 1; c.gridheight = 1;
        add(amountLabel,c);
        c.gridx = 1; c.gridy = 4;
        c.gridwidth = 2; c.gridheight = 1;
        add(amountField,c);
        c.gridx = 0; c.gridy = 5;
        c.gridwidth = 1; c.gridheight = 1;
        add(paidByLabel,c);
        c.gridx = 1; c.gridy = 5;
        c.gridwidth = 2; c.gridheight = 1;
        add(paidByField,c);
        c.gridx = 0; c.gridy = 6;
        c.gridwidth = 3; c.gridheight = 1;
        add(descriptionLabel,c);
        c.gridx = 0; c.gridy = 7;
        c.gridwidth = 3; c.gridheight = 1;
        add(descriptionTextArea,c);
        c.gridx = 0; c.gridy = 8;
        c.gridwidth = 3; c.gridheight = 1;
        add(addButton,c);

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
