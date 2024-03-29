package View;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import Controller.PersonController;

public class UserEditFrame extends JFrame {
    private GridBagConstraints c;
    private PersonController pc;
    private int id;
    

    public UserEditFrame(PersonController pc, String name) {
        super("Apply edits");
        this.pc = pc;
        setSize(600, 600);
        setVisible(true);

        this.c = new GridBagConstraints();
        setLayout(new GridBagLayout());
        this.c.fill = GridBagConstraints.HORIZONTAL;
        this.c.weightx = 2.0;
        this.c.weighty = 2.0;
        setLocation(500, 10);
        this.id = this.pc.getId(this.pc.getEntryByName(name));
        this.Edits();
    }

    public void Edits() {
        JTextField nameField = new JTextField(50);
        c.gridx = 0; c.gridy = 0;
        this.add(nameField,c);
        JButton changedNameButton = new JButton("Change name");
        c.gridx = 1; 
        this.add(changedNameButton,c);
        changedNameButton.addActionListener(l -> {
            pc.getPerson(id).setName(nameField.getText());
        });

        JTextField emailField = new JTextField(50);
        c.gridx = 0; c.gridy = 1;
        this.add(emailField,c);
        JButton changedEmailButton = new JButton("Change email");
        c.gridx = 1; 
        this.add(changedEmailButton,c);
        changedEmailButton.addActionListener(l -> {
            pc.getPerson(id).setEmail(emailField.getText());
        });

        JTextField phoneField = new JTextField(50);
        c.gridx = 0; c.gridy = 2;
        this.add(phoneField,c);
        JButton changedPhoneButton = new JButton("Change phone");
        c.gridx = 1; 
        this.add(changedPhoneButton,c);
        changedPhoneButton.addActionListener(l -> {
            pc.getPerson(id).setPhone(phoneField.getText());
        });

        JButton deleteUser = new JButton("Delete User");
        c.gridx = 0; c.gridy = 3;
        this.add(deleteUser,c);
        deleteUser.addActionListener(l ->{
            pc.removePerson(pc.getPerson(id));
        });

        JButton okayButton = new JButton("Continue");
        c.gridx = 1;
        this.add(okayButton,c);
        okayButton.addActionListener(l-> {
            this.dispose();
        });

    }
}
