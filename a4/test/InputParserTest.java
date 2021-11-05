import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class InputParserTest {
    File file = new File("D:\\my folder\\education\\iii-semester\\utp\\a4\\test\\People.txt");
    List<Person> people;

    @Test
    public void InputParser() throws ParseException {
        people = InputParser.parse(file);
        Assert.assertEquals("Thomas", people.get(0).get_firstName());
        Assert.assertEquals("Abbott", people.get(0).get_surname());
        Assert.assertEquals(7, people.size());
        Assert.assertEquals(new SimpleDateFormat("yyyy-MM-dd").parse("1974-04-28"), people.get(0).get_birthdate());
        /*
        Thomas Abbott 1974-04-28
Ben Voss 1954-01-09
         */
    }
}
