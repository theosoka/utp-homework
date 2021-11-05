import java.io.File;
import java.util.*;

public final class PersonDatabase {
    private final List<Person> people;
    Map<Date, List<Person>> map = new TreeMap<>();

    public PersonDatabase(File file) {
        people = InputParser.parse(file);
    }

    public List<Person> sortedByFirstName() {
        people.sort(new FirstNameComparator());
        return people; // external rule for ordering (based on Comparator --- FirstNameComparator)
    }

    public List<Person> sortedBySurnameFirstNameAndBirthdate() {
        Collections.sort(people);
        return people;// natural order (Comparable)
    }

    public List<Person> sortedByBirthdate() {
        people.sort(new BirthdateComparator());
        return people; // external rule for ordering (based on Comparator --- BirthdateComparator)
    }

    public List<Person> bornOnDay(Date date) {
        if (date == null)
            return null;
        List<Person> res = new ArrayList<>();;
        for (Person p: people) {
            if (p.get_birthdate().equals(date)) {
                res.add(p);
            }
        }
        map.put(date, res);
        return map.get(date);
    }
}