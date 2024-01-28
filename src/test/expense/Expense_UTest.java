package expense;

import static org.junit.Assert.*;

import Expense.Expense;
import Expense.ExpenseDescription;
import Payment.Split;
import Payment.ExactPayment;
import Payment.EqualPayment;
import Expense.PersonalPayment;
import Expense.UnifiedPayment;
import Person.Person;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

public class Expense_UTest {

    @Mock
    private Person mockPerson;

    @Mock
    private Split mockSplit;

    @Mock
    private ExpenseDescription mockDescription;

    private Expense personalPayment;
    private Expense unifiedPayment;
    private Split equalPayment;
    private Split exactPayment;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        // Create instances of different expense types for testing
        List<Split> personalSplits = Arrays.asList(new ExactPayment(mockPerson, 100.0));
        personalPayment = new PersonalPayment(100.0, mockPerson, personalSplits, mockDescription);

        List<Split> unifiedSplits = Arrays.asList(new EqualPayment(mockPerson));
        unifiedPayment = new UnifiedPayment(100.0, mockPerson, unifiedSplits, mockDescription);

        List<Split> equalSplits = Arrays.asList(new EqualPayment(mockPerson));
        equalPayment = new EqualPayment(mockPerson);

        List<Split> exactSplits = Arrays.asList(new ExactPayment(mockPerson, 50.0));
        exactPayment = new ExactPayment(mockPerson, 100.0);
    }

    @Test
    public void testGetAmount() {
        assertEquals(100.0, personalPayment.getAmount(), 0.01);
        assertEquals(100.0, unifiedPayment.getAmount(), 0.01);
    }

    @Test
    public void testSetAmount() {
        personalPayment.setAmount(200.0);
        assertEquals(200.0, personalPayment.getAmount(), 0.01);

        unifiedPayment.setAmount(300.0);
        assertEquals(300.0, unifiedPayment.getAmount(), 0.01);

        equalPayment.setAmount(400.0);
        assertEquals(400.0, equalPayment.getAmount(), 0.01);

        exactPayment.setAmount(500.0);
        assertEquals(500.0, exactPayment.getAmount(), 0.01);
    }

    @Test
    public void testGetPaidBy() {
        assertEquals(mockPerson, personalPayment.getPaidBy());
        assertEquals(mockPerson, unifiedPayment.getPaidBy());
    }

    @Test
    public void testSetPaidBy() {
        Person newPerson = new Person(14, "NewPerson", "Testmail@test.com", "+3212345678");

        personalPayment.setPaidBy(newPerson);
        assertEquals(newPerson, personalPayment.getPaidBy());

        unifiedPayment.setPaidBy(newPerson);
        assertEquals(newPerson, unifiedPayment.getPaidBy());
    }

    @Test
    public void testGetDescription() {
        assertEquals(mockDescription, personalPayment.getDescription());
        assertEquals(mockDescription, unifiedPayment.getDescription());
    }

    @Test
    public void testSetDescription() {
        ExpenseDescription newDescription = new ExpenseDescription("test description", "A good test description has seldom words.");

        personalPayment.setDescription(newDescription);
        assertEquals(newDescription, personalPayment.getDescription());

        unifiedPayment.setDescription(newDescription);
        assertEquals(newDescription, unifiedPayment.getDescription());
    }

    @Test
    public void testValidateReturnsTrue() {
        assertTrue(personalPayment.validate());
        assertTrue(unifiedPayment.validate());
    }

    @Test
    public void testToString() {
        assertNotNull(personalPayment.toString());
        assertNotNull(unifiedPayment.toString());
        assertNotNull(equalPayment.toString());
        assertNotNull(exactPayment.toString());
    }
}
