package Payment;

import Person.Person;

public class ExactPayment extends Split{

    public ExactPayment(Person person, double amount) {
        super(person);
        this.amount = amount;
    }
}
