package Integration;

import Controller.ExpenseController;
import Controller.PersonController;
import Expense.BalanceCalculator;
import Expense.ExpenseDescription;
import Expense.ExpenseType;
import Factory.FactoryExpense;
import Observer.DatabaseObserver;
import Payment.EqualPayment;
import Payment.ExactPayment;
import Payment.Split;
import Person.Person;
import View.ControllerViews.ExpenseFrame;
import View.ControllerViews.PersonFrame;
import database.ExpenseDatabase;
import database.PersonDatabase;
import org.junit.Test;
import org.junit.Before;

import java.util.*;

import static org.junit.Assert.*;

public class CalculateBalance_ITest
{
    ExpenseController econtr;
    public CalculateBalance_ITest()
    {

    }

    @Before
    public  void initialize()
    {
        PersonDatabase persondb = PersonDatabase.getInstance();
        ExpenseDatabase expensedb = ExpenseDatabase.getInstance();

        DatabaseObserver observer = new DatabaseObserver();
        persondb.addPropertyChangeListener("addPerson", observer);
        persondb.addPropertyChangeListener("removePerson", observer);
        expensedb.addPropertyChangeListener("addExpense", observer);

        PersonController pcontr = new PersonController(persondb, new PersonFrame());
        econtr = new ExpenseController(expensedb, new ExpenseFrame(), expensedb.getBalanceCalculator());

        Person p1 = new Person(1, "Perry", "PerryPlatypus@work.com", "0485123456");
        Person p2 = new Person(2, "Emma", "Emma@work.com", "0485123456");
        Person p3 = new Person(3, "Mats", "Mats@gmail.com", "0485123456");

        pcontr.addToDb(p1);
        pcontr.addToDb(p2);
        pcontr.addToDb(p3);

        /*
         * p2 owes p1 300
         * p3 owes p1 300
         */
        List<Split> splitList1 = new ArrayList<>(); splitList1.add(new EqualPayment(p2)); splitList1.add(new EqualPayment(p3));
        econtr.addToDb(FactoryExpense.createExpense(ExpenseType.EQUAL, 900, p1, splitList1 , new ExpenseDescription("Vliegtickets", "payment door 1")));

        /*
         * p1 owes p2 10
         * p3 owes p2 10
         */
        List<Split> splitList2 = new ArrayList<>(); splitList2.add(new EqualPayment(p1)); splitList2.add(new EqualPayment(p3));
        econtr.addToDb(FactoryExpense.createExpense(ExpenseType.EQUAL, 30, p2, splitList2, new ExpenseDescription("Cinema", "FFAF Movie")));

        //process(expensedb, p1, p2, p3);
        /*
         * p2 owes p1 22
         * p3 owes p1 22
         */
        List<Split> splitList3 = new ArrayList<>(); splitList3.add(new EqualPayment(p2)); splitList3.add(new EqualPayment(p3));
        econtr.addToDb(FactoryExpense.createExpense(ExpenseType.EQUAL, 66, p1, splitList3, new ExpenseDescription("Matching Necklaces", "To remember our friendship")));

        //process(expensedb, p1, p2, p3);

        /*
         * p1 owes p3 250
         * p2 owes p3 178
         * p3 paid 72 for himself
         */
        List<Split> splitList4 = new ArrayList<>(); splitList4.add(new ExactPayment(p1, 250)); splitList4.add(new ExactPayment(p2,178));
        econtr.addToDb(FactoryExpense.createExpense(ExpenseType.EXACT, 500, p3, splitList4, new ExpenseDescription("Shopping", "These idiots forgot their wallet")));

        /*
         * p1 owes p2 = 10              -> 0
         * p1 owes p3 = 250             -> 0
         *
         * p2 owes p1 = 300 + 22 = 322  -> 312
         * p2 owes p3 = 178             -> 168
         *
         * p3 owes p1 = 300 + 22 = 322  -> 72
         * p3 owes p2 = 10              -> 0
         */
    }

    @Test
    public void t_calculateEndBalance()
    {
        BalanceCalculator balCalcFromCode = econtr.getBalanceCalculator();

        // p1 should not be in debt to anyone (p2 = -312) and (p3 = -72)
        double answerFromCode = balCalcFromCode.calculateIndividualAmounts(econtr.getAllExpenses(),1).get(1).get(2);
        assertEquals("Testing balance person 1 - should return -312 for p2", -312, answerFromCode, 0.0);

        answerFromCode = balCalcFromCode.calculateIndividualAmounts(econtr.getAllExpenses(),1).get(1).get(3);
        assertEquals("Testing balance person 1 - should return -72 for p3", -72, answerFromCode, 0.0);

        // p2 should be in debt to (p1 = 312) and (p3 = 168)
        answerFromCode = balCalcFromCode.calculateIndividualAmounts(econtr.getAllExpenses(),2).get(2).get(1);
        assertEquals("Testing balance person 2 - should return 312 for p1", 312, answerFromCode, 0.0);

        answerFromCode = balCalcFromCode.calculateIndividualAmounts(econtr.getAllExpenses(),2).get(2).get(3);
        assertEquals("Testing balance person 2 - should return 168 for p3", 168, answerFromCode, 0.0);

        // p3 should be in debt to (p1 = 72) and should not be in debt to (p2 = -168)
        answerFromCode = balCalcFromCode.calculateIndividualAmounts(econtr.getAllExpenses(),3).get(3).get(1);
        assertEquals("Testing balance person 3 - should return 72 for p1", 72, answerFromCode, 0.0);

        answerFromCode = balCalcFromCode.calculateIndividualAmounts(econtr.getAllExpenses(),3).get(3).get(2);
        assertEquals("Testing balance person 3 - should return -168 for p2", -168, answerFromCode, 0.0);

    }

}
