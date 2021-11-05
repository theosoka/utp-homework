import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.sql.Date;
import java.util.List;

public class PersonDatabaseTest {
    File file = new File("D:\\my folder\\education\\iii-semester\\utp\\a4\\test\\People.txt");
    PersonDatabase database = new PersonDatabase(file);

    @Test
    public void sortedByFirstName()
    {
        List<Person> res = database.sortedByFirstName();
        Assert.assertEquals("Ben", res.get(0).get_firstName());
        Assert.assertEquals("Elanie", res.get(1).get_firstName());
    }

    @Test
    public void sortedBySurnameFirstNameAndBirthdate() {
        List<Person> res = database.sortedBySurnameFirstNameAndBirthdate();
        Assert.assertEquals(Date.valueOf("1977-06-06"), res.get(4).get_birthdate());
        Assert.assertEquals("Voss", res.get(6).get_surname());
    }

    @Test
    public void sortedByBirthdate() {
        List<Person> res = database.sortedByBirthdate();
        Assert.assertEquals(Date.valueOf("1954-01-09"), res.get(0).get_birthdate());
        Assert.assertEquals(Date.valueOf("1991-12-26"), res.get(6).get_birthdate());
    }

    @Test
    public void bornOdDay() {
        List<Person> res = database.bornOnDay(Date.valueOf("1954-01-09"));
        Assert.assertEquals(1, res.size());
        Assert.assertEquals("Ben Voss", res.get(0).get_firstName() + " " + res.get(0).get_surname());

    }
}
