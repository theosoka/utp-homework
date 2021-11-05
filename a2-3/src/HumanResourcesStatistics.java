import employee.Employee;
import employee.Manager;
import employee.Trainee;
import employee.Worker;
import payroll.PayrollEntry;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class HumanResourcesStatistics {

    public static List<PayrollEntry> payroll(List<Employee> employees) {
        if (employees == null)
            return null;
        return employees
                .stream()
                .map(HumanResourcesStatistics::payrollEntry)
                .collect(Collectors.toList());
    }

    private static PayrollEntry payrollEntry(Employee e) {
        if (e == null)
            return  null;
        BigDecimal bonus = e instanceof Trainee ? BigDecimal.valueOf(0) : ((Worker)e).getBonus();
        return new PayrollEntry(e, e.getSalary(), bonus);
    }

    // payroll for all subordinates
    public static List<PayrollEntry> subordinatesPayroll(Manager manager) {
        if (manager == null)
            return null;
        List<Employee> employees = manager.getSubordinates();
        return payroll(employees);
    }

    public static BigDecimal bonusTotal(List<Employee> employees) {
        if (employees == null)
            return null;
        return employees
                .stream()
                .map(e -> e instanceof Trainee ? BigDecimal.ZERO : ((Worker)e).getBonus())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static Employee longestSeniority(List<Employee> employees) {
        return employees
                .stream()
                .filter(e -> e instanceof Worker)
                .reduce(null, HumanResourcesStatistics::compareSeniority);
    }

    public static BigDecimal largestSalaryWithoutBonus(List<Employee> employees) {
        return employees
                .stream()
                .map(Employee::getSalary)
                .reduce(BigDecimal.ZERO, HumanResourcesStatistics::largestSalary);
    }

    public static BigDecimal largestSalaryWithBonus(List<Employee> employees) {
        return employees
                .stream()
                .map(e -> e instanceof Trainee ? e.getSalary() : e.getSalary().add(((Worker)e).getBonus()))
                .reduce(BigDecimal.ZERO, HumanResourcesStatistics::largestSalary);
    }

    public static List<Employee> namesStartWithA(Manager manager) {
        List<Employee> employees = manager.getSubordinates();
        //System.out.print(employees);
        return employees
                .stream()
                .filter(e -> e.getSurname().startsWith("A"))
                .collect(Collectors.toList());
    }

    public static List<Employee> earnMoreThan10000(List<Employee> employees) {
        return employees
                .stream()
                .filter(e -> e.getSalary().intValue() > 10000)
                .collect(Collectors.toList());
    }

    public static Employee compareSeniority(Employee e1, Employee e2) {
        if (e1 == null)
            return e2;
        if (((Worker)e1).getEmploymentDate().isAfter(((Worker)e2).getEmploymentDate()))
            return e2;
        else
            return e1;

    }

    public static BigDecimal largestSalary(BigDecimal s1, BigDecimal s2)
    {
        int res = s1.compareTo(s2);
        if (res < 0)
            return s2;
        else
            return s1;

    }

    // * search for Employees older than given employee and earning less than him
    public static List<Employee> olderThanAndEarnLess(List<Employee> allEmployees, Employee employee) {
        if (employee == null)
            return null;

        Predicate<Employee> isOlder = e -> e.isOlderThan(employee);
        Predicate<Employee> earnsLess = e -> e.isLess(employee.getSalary());

        return allEmployees
                .stream()
                .filter(isOlder.and(earnsLess))
                .collect(Collectors.toList());
    }

    // * search for Trainees whose practice length is longer than given number of days and raise their salary by 5%
    public static List<Trainee> practiceLengthLongerThan(List<Employee> allEmployees, int daysCount) {
        if (daysCount < 0)
            return null;
        Predicate<Trainee> practiceLonger =  t -> t.practiceIsLonger(daysCount);
        Predicate<Employee> isTrainee =  t -> t instanceof Trainee;
        Consumer<Trainee> modifySalary = t -> t.addToSalary(t.getSalary().multiply(BigDecimal.valueOf(0.05)));
        List<Trainee> tr = allEmployees
                .stream()
                .filter(isTrainee)
                .map(t -> (Trainee)t)
                .filter(practiceLonger)
                .collect(Collectors.toList());
        tr.forEach(modifySalary);
        return tr;

    }


    // * search for Workers whose seniority is longer than given number of months and give them bonus of 4500 if their bonus is smaller
    public static List<Worker> seniorityLongerThan(List<Employee> allEmployees, int monthCount) {
        if (monthCount < 1)
            return null;
        Predicate<Worker> seniorityLonger = w -> w.seniorityLongerMonths(monthCount);
       List<Worker> wr = allEmployees
                .stream()
                .filter(w -> w instanceof Worker)
                .map(w -> (Worker)w)
                .filter(seniorityLonger)
                .collect(Collectors.toList());
       wr.forEach(w ->  {
           if (!w.greaterBonusThan(BigDecimal.valueOf(300)))
               w.setBonus(BigDecimal.valueOf(300));
       });
       return wr;
    }


    // * search for Workers whose seniority is between 1 and 3 years and give them raise of salary by 10%
    public static List<Worker> seniorityBetweenOneAndThreeYears(List<Employee> allEmployees) {
        Predicate<Worker> moreThan1 = w -> w.seniorityLongerYears(1);
        Predicate<Worker> lessThan3 = w -> !w.seniorityLongerYears(3);
        Predicate<Worker> between1and3 = moreThan1.and(lessThan3);
        Consumer<Worker> raiseSalary = w -> w.addToSalary(w.getSalary().multiply(BigDecimal.valueOf(0.1)));
        List<Worker> wr = allEmployees
                .stream()
                .filter(w -> w instanceof Worker)
                .map(w -> (Worker)w)
                .filter(between1and3)
                .collect(Collectors.toList());
        wr.forEach(raiseSalary);
        return wr;
    }


    // * search for Workers whose seniority is longer than the seniority of a given employee and
    // earn less than him and align their salary with the given employee
    public static List<Worker> seniorityLongerThan(List<Employee> allEmployees, Employee employee) {
        if (employee == null)
            return null;
        Predicate<Worker> longerSeniority = w -> w.seniorityLongerMonths(((Worker)employee).monthsSeniority());
        Consumer<Worker> alignSalary = w -> w.setSalary(employee.getSalary());
        List<Worker> wr = allEmployees
                .stream()
                .filter(w -> w instanceof Worker)
                .map(w -> (Worker)w)
                .filter(longerSeniority)
                .collect(Collectors.toList());
        wr.forEach(alignSalary);
        return wr;
    }


    // * search for Workers whose seniority is between 2 and 4 years and whose age is greater than given number of years
    public static List<Worker> seniorityBetweenTwoAndFourYearsAndAgeGreaterThan(List<Employee> allEmployees, int age) {
        Predicate<Worker> moreThan2 = w -> w.seniorityLongerYears(2);
        Predicate<Worker> lessThan4 = w -> !w.seniorityLongerYears(4);
        Predicate<Worker> isOlder = w -> w.isOlder(age);
        Predicate<Worker> between2and4andIsOlder = moreThan2.and(lessThan4).and(isOlder);
        return allEmployees
                .stream()
                .filter(w -> w instanceof Worker)
                .map(w -> (Worker)w)
                .filter(between2and4andIsOlder)
                .collect(Collectors.toList());
    }
}