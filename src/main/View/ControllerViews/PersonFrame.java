package View.ControllerViews;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import Person.Person;

public class PersonFrame extends JFrame {
    public PersonFrame() {
        setSize(new Dimension(600,200));
        setLocation(500,10);
        setLayout(new GridBagLayout());
    }

    public void updatePanel(Map<Integer, Person> AllPersonsInDB) {
        this.getContentPane().removeAll();
        this.setVisible(true);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        this.getContentPane().add(panel);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        int i = 0;

        for (Person user : AllPersonsInDB.values()) {
            c.gridy = i;
            c.gridx = 0; 
            JLabel name = new JLabel("Name: " + user.getName());
            panel.add(name,c);
            c.gridx = 1;
            JLabel email = new JLabel("Email: " + user.getEmail());
            panel.add(email,c);
            c.gridx = 2;
            JLabel phone = new JLabel("Phone: " + user.getPhone());
            panel.add(phone,c);
            i++;
        }

        JButton goBackButton = new JButton("Go back");
        c.gridx = 0; c.gridy = i; panel.add(goBackButton,c);
        goBackButton.addActionListener(l-> {
            this.dispose();
        });

    }
}
