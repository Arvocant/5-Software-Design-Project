package Payment;

import Person.Person;

public class EqualPayment extends Split{ //Strategy Pattern

    public EqualPayment(Person person) {
        super(person);
    }
}
