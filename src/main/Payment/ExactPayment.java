package Payment;

import Person.Person;

public class ExactPayment extends Split{ //Strategy Pattern

    public ExactPayment(Person person, double amount) {
        super(person);
        this.amount = amount;
    }
}
