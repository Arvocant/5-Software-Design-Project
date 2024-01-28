package View.ControllerViews;

import Expense.Expense;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class ExpenseFrame extends JFrame {
    public ExpenseFrame() {
        setSize(new Dimension(600,200));
        setLocation(500,300);
        setLayout(new GridBagLayout());
    }

    public void updatePanel(Map<Integer, Expense> AllExpensesInDB) {
        this.getContentPane().removeAll();
        this.setVisible(true);
        JPanel panel = new JPanel();

        panel.setLayout(new GridBagLayout());
        this.getContentPane().add(panel);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        int i = 0;

        for (Expense expense : AllExpensesInDB.values()) {
            c.gridy = i;
            c.gridx = 0;
            JLabel titel = new JLabel("Title: "+ expense.getDescription().getName());
            panel.add(titel,c);
            c.gridx = 1;
            JLabel paidBy = new JLabel("|Paid by: "+ expense.getPaidBy());
            panel.add(paidBy,c);
            c.gridx = 2;
            JLabel amount = new JLabel("|Amount: "+ expense.getAmount());
            panel.add(amount,c);
            c.gridx = 3;
            JLabel desc = new JLabel("|Notes: "+ expense.getDescription().getNotes());
            panel.add(desc,c);
            i++;
        }

        JButton goBackButton = new JButton("Go back");
        c.gridx = 0; c.gridy = i; panel.add(goBackButton,c);
        goBackButton.addActionListener(l-> {
            this.dispose();
        });

    }
}
