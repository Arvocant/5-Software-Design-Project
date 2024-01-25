package Observer;

import Expense.Expense;
import Person.Person;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DatabaseObserver implements PropertyChangeListener {

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("\n---Database got updated");
        if("addExpense".equals(evt.getPropertyName())) {
            Expense expense = (Expense) evt.getNewValue();
            System.out.println("An expense was added:\n" + expense.toString() +"\n");
        }

        if("addPerson".equals(evt.getPropertyName())) {
            Person person = (Person) evt.getNewValue();
            System.out.println("A person was added: " + person + "\n");
        }

        if("removePerson".equals(evt.getPropertyName())) {
            Person person = (Person) evt.getNewValue();
            System.out.println("A person was removed: " + person + "\n");
        }

    }
}
