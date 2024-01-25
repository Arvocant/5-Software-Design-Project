package database;

import Person.Person;
import database.PersonDatabase;
import database.database;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Field;
import java.util.HashMap;


// Run with PowerMock, an extended version of Mockito
@RunWith(PowerMockRunner.class)
// Prepare class RegistrationController for testing by injecting mocks
@PrepareForTest({ PersonDatabase.class })
public class RegistrationDB_UTest
{
    public RegistrationDB_UTest()
    {

    }

    @Before
    public void initialize()
    {

    }

    @Test
    @SuppressWarnings("unchecked")
    public void t_addEntry() throws NoSuchFieldException, IllegalAccessException
    {
        Field field = PersonDatabase.class.getDeclaredField("db");
        field.setAccessible(true);
        database<Person> registrationDB_underTest = PersonDatabase.getInstance();
        HashMap<Integer, Person> mock_db = (HashMap)Mockito.mock(HashMap.class);
        field.set(registrationDB_underTest, mock_db);

        Person mockPerson = (Person)Mockito.mock(Person.class);
        registrationDB_underTest.addEntry(mockPerson);
        ((HashMap)Mockito.verify(mock_db,Mockito.times(1))).put(1,mockPerson);
    }
}

