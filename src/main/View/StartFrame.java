package View;

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

    JPanel panelInsideFrame;

    public StartFrame(PersonController pc, ExpenseController ec)
    {
        super("Add users");
        this.c = new GridBagConstraints();
        this.pc = pc;
        this.ec = ec;
        this.panelInsideFrame = new JPanel();
        this.panelInsideFrame.setLayout(new GridBagLayout());
        this.panelInsideFrame.setPreferredSize(new Dimension(400,400));
        
        setSize(new Dimension(500,500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setVisible(true);
        this.c.fill = GridBagConstraints.HORIZONTAL;
        this.c.weightx = 2.0;
        this.c.weighty = 2.0;

        this.getContentPane().add(panelInsideFrame);

        this.addUsers();
    }

    public void addUsers() {
        JLabel usernameLabel = new JLabel("Username: ");
        JTextField usernameField = new JTextField(20);
        {
            c.gridx = 0;
            c.gridy = 0;
            this.panelInsideFrame.add(usernameLabel,c);
            c.gridx = 1;
            c.gridy = 0;
            this.panelInsideFrame.add(usernameField,c);
        }

        JLabel emailLabel = new JLabel("Email: ");
        JTextField emailField = new JTextField(20);
        {
            c.gridx = 0;
            c.gridy = 1;
            this.panelInsideFrame.add(emailLabel,c);
            c.gridx = 1;
            c.gridy = 1;
            this.panelInsideFrame.add(emailField,c);
        }

        JLabel phoneLabel = new JLabel("Phone: ");
        JTextField phoneField = new JTextField(20);
        {
            c.gridx = 0;
            c.gridy = 2;
            this.panelInsideFrame.add(phoneLabel,c);
            c.gridx = 1;
            c.gridy = 2;
            this.panelInsideFrame.add(phoneField,c);
        }

        JButton addButton = new JButton("Add user");
        {
            c.gridx = 0;
            c.gridy = 3;
            this.panelInsideFrame.add(addButton,c);
        }
        addButton.addActionListener(listener -> {
            this.pc.addToDb(new Person(idCounter, usernameField.getText(), emailField.getText(), phoneField.getText()));
            idCounter ++;
        });

        JButton nextButton = new JButton("Continue");
        {
            c.gridx = 1;
            c.gridy = 3;
            this.panelInsideFrame.add(nextButton,c);
        }
        nextButton.addActionListener(listener -> {
            this.dispose();
            new ManagerFrame(this.pc, this.ec);
        });

    }
    
}
