package Person;

import Expense.Expense;

import java.util.ArrayList;
import java.util.List;

//Website used as example: https://workat.tech/machine-coding/editorial/how-to-design-splitwise-machine-coding-ayvnfo1tfst6
public class Person {
    private int id;
    private String name;
    private String email;
    private String phone;
    //private double amount;

    //private List<Integer> expenseIds; // List to store associated expenses

    public Person(int id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        //this.expenseIds = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return this.name;
    }
//    public void setAmount(double amount) {
//        this.amount = amount;
//    }
//
//    public List<Integer> getExpenseIds() {
//        return expenseIds;
//    }
//
//    public void addExpenseId(int expenseId) {
//        this.expenseIds.add(expenseId);
//    }
}

