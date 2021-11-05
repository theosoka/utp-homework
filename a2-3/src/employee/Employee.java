package employee;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Employee extends Person {

    private BigDecimal _salary;
    private Manager _manager;

    protected Employee(String firstName, String surname, LocalDate birthDate, BigDecimal salary, Manager manager) {
        super(firstName, surname, birthDate);
        _salary = salary;
        _manager = manager;
    }

    public void addToSalary(BigDecimal salary) {
        _salary = _salary.add(salary);
    }

    public void setSalary(BigDecimal _salary) {
        this._salary = _salary;
    }

    public BigDecimal getSalary() {
        return _salary;
    }

    public Manager getManager() {
        return _manager;
    }

    public boolean isGreater(BigDecimal s){
        return this.compareSalary(s) > 0;
    }

    public boolean isLess(BigDecimal s){
        return this.compareSalary(s) < 0;
    }

    public int compareSalary(BigDecimal s)
    {
        return this._salary.compareTo(s);
    }
}