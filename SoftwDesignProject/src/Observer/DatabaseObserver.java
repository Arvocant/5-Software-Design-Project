package Observer;

import Expense.Expense;
import Person.Person;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DatabaseObserver implements PropertyChangeListener {

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Database got updated");
        if("addExpense".equals(evt.getPropertyName())) {
            Expense expense = (Expense) evt.getNewValue();
            System.out.println("An expense was added: " + expense);
        }
        else{
            Person person = (Person) evt.getNewValue();
            System.out.println("A person was added: " + person);
        }
    }
}
