package View;

import Controller.PersonController;
import Payment.ExactPayment;
import Payment.Split;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class PanelExact extends JPanel {
    PersonController pc;
    List<Split> splitList;

    public PanelExact(PersonController pc) {
        this.pc = pc;
        this.splitList = new ArrayList<>();
        int index = 0;

        JButton enterButton = new JButton("Enter");
        this.add(enterButton);
        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });


        /*
        while (index < pc.getAllPersons().length) {
            AtomicBoolean moveOn = new AtomicBoolean(false);
            JLabel name = new JLabel(pc.getAllPersons()[index]);
            this.add(name);
            JTextField amount = new JTextField(20);
            this.add(amount);
            JButton entered = new JButton("enter");
            this.add(entered);

            entered.addActionListener(listener -> {
                splitList.add(new ExactPayment(pc.getPersonOutOfDb(name.getText()) , Double.parseDouble(amount.getText())));
                moveOn.set(true);
            });
            while (moveOn.get()) {
                index++;
            }
        }

         */
    }

    public void nextPerson(int index) {

    }

}
