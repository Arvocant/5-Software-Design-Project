package View;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.PersonController;
import Person.Person;

    // ik wou eerst met twee pannels in dit frame werken, en het ene panel verwijderen om de andere erover te plakken, 
    // maar dan zit ik weer met hetzelfde probleem als in onze startpagina, dat de pannel pas getoont werd vanaf er
    // met de size geslepen wordt dus heb gewoon terug een nieuw frame gemaakt :(

public class UserChoiceFrame extends JFrame{
    private GridBagConstraints c;
    private PersonController pc;

    JPanel userChoicePanel;

    public UserChoiceFrame(PersonController pc) {
        super("Choose user to edit");
        this.c = new GridBagConstraints();
        this.pc = pc;
        this.userChoicePanel = new JPanel();
        this.userChoicePanel.setSize(new Dimension(400,200));

        setSize(400, 200);
        setLocation(500, 10);
        setLayout(new GridBagLayout());
        setVisible(true);
        this.c.fill = GridBagConstraints.HORIZONTAL;
        this.c.weightx = 2.0;
        this.c.weighty = 2.0;
        getContentPane().add(this.userChoicePanel);

        this.makeUserButtons();
    }

    public void makeUserButtons() {

        for (Person user : this.pc.getAllPerson().values()) {
            JButton button = new JButton(user.getName());
            button.setName(user.getName());
            this.userChoicePanel.add(button,c);

            button.addActionListener(l -> {
                System.out.println("\nEditing: " + button.getName());
                this.dispose();
                new UserEditFrame(this.pc, button.getName());
            });
        }

    }


}
