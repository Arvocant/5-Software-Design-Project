package Payment;

import Person.Person;

public abstract class Split {
    private Person person;
    public double amount;

    public Split(Person person) {
        this.person = person;
    }

    public Person getPerson(){
        return person;
    }

    public void setPerson(Person person){
        this.person = person;
    }

    public double getAmount(){
        return amount;
    }
    public void setAmount(double amount){
        this.amount = amount;
    }
}
