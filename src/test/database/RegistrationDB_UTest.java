package database;

import Person.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

// Run with PowerMock, an extended version of Mockito
@RunWith(PowerMockRunner.class)
// Prepare class PersonDatabase for testing by injecting mocks
@PrepareForTest({database.class})

public class RegistrationDB_UTest {

    public RegistrationDB_UTest()
    {   /*
        Needs to stay empty.
        Necessary initializations need to be done in function 'initialize()'
        */   }

    @Before
    public void initialize() throws Exception {

    }

    @Test
    public void t_addEntry() throws NoSuchFieldException, IllegalAccessException
    {
        Field field = database.class.getDeclaredField("db");
        field.setAccessible(true);

        PersonDatabase personDatabaseUnderTest = PersonDatabase.getInstance();

        Person mockPerson1 = mock(Person.class);
        when(mockPerson1.getId()).thenReturn(1); // Assuming Person has an getId method
        when(mockPerson1.getName()).thenReturn("Person One"); // Optional, based on your Person class

        Person mockPerson2 = mock(Person.class);
        when(mockPerson2.getId()).thenReturn(2);
        when(mockPerson2.getName()).thenReturn("Person Two");

        Map<Integer, Person> mockDb = new HashMap<>();
        field.set(personDatabaseUnderTest, mockDb);

        personDatabaseUnderTest.addEntry(mockPerson1);
        assertEquals("Testing size of mockDb - should be 1",
                1, mockDb.size());
        assertThat(mockDb.get(1), instanceOf(Person.class));

        personDatabaseUnderTest.addEntry(mockPerson2);
        assertEquals("Testing size of mockMap - should be 2",
                2, mockDb.size());
        assertThat(mockDb.get(2), instanceOf(Person.class));
    }
}
