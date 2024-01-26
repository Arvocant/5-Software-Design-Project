package database;

import Person.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Field;
import java.util.HashMap;

// Run with PowerMock, an extended version of Mockito
@RunWith(PowerMockRunner.class)
// Prepare class PersonDatabase for testing by injecting mocks
@PrepareForTest(PersonDatabase.class)
public class RegistrationDB_UTest {

    private PersonDatabase personDatabase;

    @Before
    public void initialize() {
        PowerMockito.mockStatic(PersonDatabase.class);
        personDatabase = PersonDatabase.getInstance();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void t_addEntry() throws NoSuchFieldException, IllegalAccessException {
        // Mock the static method getInstance in PersonDatabase
        PowerMockito.mockStatic(PersonDatabase.class);

        // Create a mock database
        HashMap<Integer, Person> mock_db = Mockito.mock(HashMap.class);

        // Set the mock database using reflection
        Field field = PersonDatabase.class.getDeclaredField("db");
        field.setAccessible(true);
        field.set(null, mock_db); // Use null because getInstance is mocked

        // Create a mock person
        Person mockPerson = Mockito.mock(Person.class);

        // Call the addEntry method
        PersonDatabase.getInstance().addEntry(mockPerson);

        // Verify that put method is called on the mock database
        Mockito.verify(mock_db, Mockito.times(1)).put(Mockito.anyInt(), Mockito.eq(mockPerson));
    }
}
