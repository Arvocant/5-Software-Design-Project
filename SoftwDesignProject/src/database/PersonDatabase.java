package database;

import Expense.Expense;
import Person.Person;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonDatabase extends database<Person>{
    private static PersonDatabase instance;
    private final Map<Integer, Person> db;
    private static int nextPersonId = 1; // Next available person ID

    //List<Expense> expenseList = new ArrayList<Expense>();

    private PersonDatabase() {
        this.db = new HashMap<>();
    }

    public static synchronized PersonDatabase getInstance() {
        if (instance == null)
            instance = new PersonDatabase();
        return instance;
    }

    @Override
    public void addEntry(Person person) {
        if (person != null && !db.containsKey(person.getId())) {
            this.db.put(person.getId(), person);
            PropertyChangeEvent event = new PropertyChangeEvent(this, "addPerson", this.db, person);

            this.property.firePropertyChange(event);
        }
    }

    @Override
    public Person getEntry(int id) {
        return db.get(id);
    }

    public Person getEntryByName(String name) {
        for (Person person : db.values()) {
            if (person.getName().equals(name)) {
                return person;
            }
        }
        return null; // Person not found
    }

    public static int getNextPersonId() {
        return nextPersonId++;
    }

    @Override
    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        super.addPropertyChangeListener(propertyName, listener);
    }

    @Override
    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        super.removePropertyChangeListener(propertyName, listener);
    }

//    public void addExpenseToPerson(int personId, int expenseId, ExpenseDatabase expenseDatabase) {
//        Person person = getEntry(personId);
//        Expense expense = expenseDatabase.getEntry(expenseId);
//
//        if (person != null && expense != null) {
//            person.addExpenseId(expenseId);
//        }
//    }
//
//    public List<Expense> getPersonExpenses(int personId, ExpenseDatabase expenseDatabase) {
//        Person person = getEntry(personId);
//
//        if (person != null) {
//            List<Integer> expenseIds = person.getExpenseIds();
//            List<Expense> personExpenses = new ArrayList<>();
//
//            for (int expenseId : expenseIds) {
//                Expense expense = expenseDatabase.getEntry(expenseId);
//                if (expense != null) {
//                    personExpenses.add(expense);
//                }
//            }
//
//            return personExpenses;
//        }
//
//        return new ArrayList<>(); // Return an empty list if the person is not found
//    }


//    public List<Expense> getExpenseList() {
//        return expenseList;
//    }
//
//    public void addExpense(Expense e) {
//        this.expenseList.add(e);
//    }
//
//    public void removeExpense(Expense e){
////        if (personList.contains(p)) {
////            int toRemove = personList.indexOf(p);
////            personList.remove(toRemove);
////        } --Same functionality?
//
//        expenseList.remove(e);
//    }
//
//    public Expense getById(int id){
//        return expenseList.get(id);
//    }
}
