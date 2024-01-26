package expense;

import static org.junit.Assert.*;

import Expense.Expense;
import Expense.ExpenseDescription;
import Payment.Split;
import Person.Person;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

public class ExpenseTest {

    @Mock
    private Person mockPerson;

    @Mock
    private Split mockSplit;

    @Mock
    private ExpenseDescription mockDescription;

    private Expense expense;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        List<Split> splits = Arrays.asList(mockSplit);
        expense = new ConcreteExpense(100.0, mockPerson, splits, mockDescription);
    }

    @Test
    public void testGetAmount() {
        assertEquals(100.0, expense.getAmount(), 0.01);
    }

    @Test
    public void testSetAmount() {
        expense.setAmount(200.0);
        assertEquals(200.0, expense.getAmount(), 0.01);
    }

    @Test
    public void testGetPaidBy() {
        assertEquals(mockPerson, expense.getPaidBy());
    }

    @Test
    public void testSetPaidBy() {
        Person newPerson = new Person(14, "NewPerson", "Testmail@test.com", "+3212345678");
        expense.setPaidBy(newPerson);
        assertEquals(newPerson, expense.getPaidBy());
    }

    @Test
    public void testGetPayments() {
        assertEquals(Arrays.asList(mockSplit), expense.getPayments());
    }

    @Test
    public void testGetDescription() {
        assertEquals(mockDescription, expense.getDescription());
    }

    @Test
    public void testSetDescription() {
        ExpenseDescription newDescription = new ExpenseDescription("test description", "A good test description has seldom words.");
        expense.setDescription(newDescription);
        assertEquals(newDescription, expense.getDescription());
    }

    @Test
    public void testValidateReturnsTrue() {
        assertTrue(expense.validate());
    }

    @Test
    public void testValidateReturnsFalse() {
        expense = new ConcreteExpense(0.0, mockPerson, Arrays.asList(mockSplit), mockDescription);

        assertFalse(expense.validate());
    }

    @Test
    public void testToString() {
        assertNotNull(expense.toString());
    }

    private static class ConcreteExpense extends Expense {
        public ConcreteExpense(double amount, Person paidBy, List<Split> payments, ExpenseDescription description) {
            super(amount, paidBy, payments, description);
        }

        @Override
        public boolean validate() {
            // Realistic validation logic: Check if the total amount paid is greater than zero
            double totalAmountPaid = getPayments().stream().mapToDouble(Split::getAmount).sum();
            return totalAmountPaid > 0;
        }
    }
}
