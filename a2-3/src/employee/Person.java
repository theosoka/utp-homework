package employee;

import java.time.LocalDate;
import java.time.Period;

public abstract class Person {

    private final String _firstName; // backing field
    private final String _surname;
    private final LocalDate _birthDate;

    protected Person(String firstName, String surname, LocalDate birthDate) {
        _firstName = firstName;
        _surname = surname;
        _birthDate = birthDate;
    }

    public String getFirstName() { // getter
        return _firstName;
    }

    public String getSurname() {
        return _surname;
    }

    public LocalDate getBirthDate() {
        return _birthDate;
    }

    public short getAge() {
        LocalDate today = LocalDate.now();
        Period days = Period.between(_birthDate, today);
        return (short)days.getYears();
    }

    public boolean isOlderThan(Employee e){
        return compareAges(e) > 0;
    }

    public boolean isYounger(Employee e){
        return compareAges(e) < 0;
    }

    public boolean isOlder(int age) {
        return this.getAge() - age > 0;
    }

    public int compareAges(Employee e) {
        return this.getAge() - e.getAge();
    }

}