package View;

import database.*;

import javax.swing.*;

import Controller.ExpenseController;
import Controller.PersonController;
import Person.Person;

import java.awt.*;

public class StartFrame extends JFrame {
    private int idCounter = 1;
    GridBagConstraints c;
    PersonController pc;
    ExpenseController ec;

    public StartFrame(PersonController pc, ExpenseController ec)
    {
        super("Add users");
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
        this.addUsers();

    }

    public void addUsers() {
        JLabel usernameLabel = new JLabel("Username: ");
        JTextField usernameField = new JTextField(20);
        {
            c.gridx = 0;
            c.gridy = 0;
            this.add(usernameLabel,c);
            c.gridx = 1;
            c.gridy = 0;
            this.add(usernameField,c);
        }

        JLabel emailLabel = new JLabel("Email: ");
        JTextField emailField = new JTextField(20);
        {
            c.gridx = 0;
            c.gridy = 1;
            this.add(emailLabel,c);
            c.gridx = 1;
            c.gridy = 1;
            this.add(emailField,c);
        }

        JLabel phoneLabel = new JLabel("Phone: ");
        JTextField phoneField = new JTextField(20);
        {
            c.gridx = 0;
            c.gridy = 2;
            this.add(phoneLabel,c);
            c.gridx = 1;
            c.gridy = 2;
            this.add(phoneField,c);
        }

        JButton addButton = new JButton("Add user");
        {
            c.gridx = 0;
            c.gridy = 3;
            this.add(addButton,c);
        }
        addButton.addActionListener(listener -> {
            System.out.println("person added");
            this.pc.addToDb(new Person(idCounter, usernameField.getText(), emailField.getText(), phoneField.getText()));
            idCounter ++;
        });

        JButton nextButton = new JButton("Continue");
        {
            c.gridx = 1;
            c.gridy = 3;
            this.add(nextButton,c);
        }
        nextButton.addActionListener(listener -> {
            this.dispose();
            new ManagerFrame(this.pc, this.ec);
        });

    }
    
}
