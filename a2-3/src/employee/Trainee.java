package employee;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class Trainee extends Employee {

    // (assignment 03)
    // * practice length is shorter than given number of days
    // * practice length is longer than given number of days

    private LocalDate _startDate;
    private int _apprenticeshipLength;

    public Trainee(String firstName, String surname, LocalDate birthDate, BigDecimal salary, Manager manager, LocalDate startDate, int apprenticeshipLength) {
        super(firstName, surname, birthDate, salary, manager);
        _startDate = startDate;
        _apprenticeshipLength = apprenticeshipLength;
    }

    public LocalDate getStartDate() {
        return _startDate;
    }

    public int getApprenticeshipLength() {
        return _apprenticeshipLength;
    }

    public boolean practiceIsShorter(int days){
        return this.practiceDays() < days;
    }

    public boolean practiceIsLonger(int days) {
        return this.practiceDays() > days;
    }

    public int practiceDays(){
        LocalDate today = LocalDate.now();
        return (int)Duration.between(_startDate.atStartOfDay(),today.atStartOfDay()).toDays();
    }
}
