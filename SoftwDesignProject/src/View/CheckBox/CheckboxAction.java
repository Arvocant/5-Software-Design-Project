package View.CheckBox;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JCheckBox;

public class CheckboxAction extends AbstractAction {
    ArrayList<String> listOfPeople;

    public CheckboxAction(String text) {
        super(text);
        this.listOfPeople = new ArrayList<>();
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
        JCheckBox cbLog = (JCheckBox) e.getSource();
        if (cbLog.isSelected()) {
            this.listOfPeople.add(cbLog.getText());
            System.out.println(cbLog.getText() + " is selected.");
        } else {
            System.out.println(cbLog.getText() + " is deselected.");
        }
    }

    public ArrayList<String> getList() {
        return this.listOfPeople;
    }

}