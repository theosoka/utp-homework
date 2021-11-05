package employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public final class Manager extends Worker {

    // attributes
    // * subordinates (a list of immediate subordinates)
    // * all subordinates (derived --- i.e. calculated on the fly --- a list of subordinates in all hierarchy)

    private List<Employee> _subordinates;

    public Manager(String firstName, String surname, LocalDate birthDate, BigDecimal salary, Manager manager, LocalDate employmentDate, BigDecimal bonus, List<Employee> subordinates) {
        super(firstName, surname, birthDate, salary, manager, employmentDate, bonus);
        _subordinates = subordinates;
    }

    public List<Employee> getSubordinates() {
        return _subordinates;
    }

    public void setSubordinates(List<Employee> _subordinates) {
        this._subordinates = _subordinates;
    }

    public List<Employee> getAllSubordinates() {
        List<Employee> subordinates = new LinkedList<>();
        for (Employee e : _subordinates) {
            if (e instanceof Manager)
                ((Manager) e).getAllSubordinates();
            subordinates.add(e);
        }
        return subordinates;
    }
}
