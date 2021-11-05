import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import employee.Employee;
import employee.Manager;
import employee.Trainee;
import employee.Worker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class HumanResourcesStatisticsTest {
    private Manager d, m1, m2;
    private Worker w1, w2, w3, w4, w5;
    private Trainee t1, t2, t3, t4;
    private LinkedList<Employee> dSub;
    private LinkedList<Employee> m1Sub;
    private LinkedList<Employee> m2Sub;


    private List<Employee> _allEmployees;

    @Before
    public void emp()
    {
        d = new Manager("Thomas", "Abbott", LocalDate.of(1974,4,28),
                new BigDecimal(12000), null, LocalDate.of(2010, 5, 1),
                new BigDecimal(3000), dSub);

        m1 = new Manager("Ben", "Voss", LocalDate.of(1954,1,9),
                new BigDecimal(11000), null, LocalDate.of(2012, 3, 24),
                new BigDecimal(4100), m1Sub);
        m2 = new Manager("Elaine", "Jones", LocalDate.of(1984,6,6),
                new BigDecimal(12000), null, LocalDate.of(2019, 4, 10),
                new BigDecimal(1100), m2Sub);

        w1 = new Worker("Robin", "Aierro", LocalDate.of(1991, 12, 26),
                new BigDecimal(4000), m1, LocalDate.of(2015, 11, 4), new BigDecimal(300));
        w2 = new Worker("Michelle", "Rhone", LocalDate.of(1983, 10, 11),
                new BigDecimal(4400), m1, LocalDate.of(2014, 10, 3), new BigDecimal(200));
        w3 = new Worker("Mary", "Mendoza", LocalDate.of(1962, 7, 7),
                new BigDecimal(6000), m2, LocalDate.of(2010, 5, 3), new BigDecimal(500));
        w4 = new Worker("Roger", "Smith", LocalDate.of(1975, 6, 8),
                new BigDecimal(3000), d, LocalDate.of(2020, 3, 3), new BigDecimal(240));
        w5 = new Worker("Brooke", "Link", LocalDate.of(1971, 12, 26),
                new BigDecimal(4200), d, LocalDate.of(2018, 8, 18), new BigDecimal(310));

        t1 = new Trainee("John", "Aran", LocalDate.of(1969, 4, 20),
                new BigDecimal(700), m1, LocalDate.of(2021, 9, 4), 60);
        t2 = new Trainee("Ashley", "Coleman", LocalDate.of(1991, 1, 25),
                new BigDecimal(800), m2, LocalDate.of(2021, 10, 1), 30);
        t3 = new Trainee("Alejandro", "Baril", LocalDate.of(1986, 11, 22),
                new BigDecimal(900), m2, LocalDate.of(2021, 8, 20), 60);
        t4 = new Trainee("Leora", "Wynn", LocalDate.of(1965, 5, 1),
                new BigDecimal(600), d, LocalDate.of(2021, 10, 2), 20);

        dSub = new LinkedList<>();
        dSub.add(w4);
        dSub.add(w5);
        dSub.add(t4);
        d.setSubordinates(dSub);

        m1Sub = new LinkedList<>();
        m1Sub.add(w1);
        m1Sub.add(w2);
        m1Sub.add(t1);
        m1.setSubordinates(m1Sub);

        m2Sub = new LinkedList<>();
        m2Sub.add(w3);
        m2Sub.add(t2);
        m2Sub.add(t3);
        m2.setSubordinates(m2Sub);

        _allEmployees = new LinkedList<>();
        _allEmployees.add(d);
        _allEmployees.add(m1); _allEmployees.add(m2);
        _allEmployees.add(w1); _allEmployees.add(w2); _allEmployees.add(w3); _allEmployees.add(w4); _allEmployees.add(w5);
        _allEmployees.add(t1); _allEmployees.add(t2); _allEmployees.add(t3); _allEmployees.add(t4);



    }

    @Test
    public void payroll() {
        HumanResourcesStatistics.payroll(_allEmployees);
    }

    @Test
    public void subordinatesPayroll() {
        HumanResourcesStatistics.subordinatesPayroll(m1);
    }

    @Test
    public void bonusTotal() {
        BigDecimal total = HumanResourcesStatistics.bonusTotal(_allEmployees);
        Assert.assertEquals(new BigDecimal("9750"), total);
    }

    @Test
    public void longestSeniority()
    {
        Employee e = HumanResourcesStatistics.longestSeniority(_allEmployees);
        Assert.assertSame(d, e);

    }
    @Test
    public void largestSalaryWithoutBonus()
    {
        BigDecimal result = HumanResourcesStatistics.largestSalaryWithoutBonus(_allEmployees);
        Assert.assertEquals(12000, result.intValue());


    }
    @Test
    public void largestSalaryWithBonus()
    {
        BigDecimal result = HumanResourcesStatistics.largestSalaryWithBonus(_allEmployees);
        Assert.assertEquals(15100, result.intValue());
    }
    @Test
    public void namesStartWithA()
    {
        LinkedList<Employee> startsWithA = new LinkedList<>();
        startsWithA.add(w1);
        startsWithA.add(t1);
        Assert.assertEquals(startsWithA, HumanResourcesStatistics.namesStartWithA(m1));
    }
    @Test
    public void earnMoreThan1000()
    {
        List<Employee> more1000 = new LinkedList<>();
        more1000.add(d); more1000.add(m1); more1000.add(m2);
        Assert.assertEquals(more1000, HumanResourcesStatistics.earnMoreThan10000(_allEmployees));
    }

    @Test
    public void olderThanAndEarnLess()
    {
        List<Employee> res = HumanResourcesStatistics.olderThanAndEarnLess(_allEmployees, w2);
        List<Employee> test = new LinkedList<>();
        test.add(w4); test.add(w5); test.add(t1); test.add(t4);
        Assert.assertEquals(test, res);

    }

    @Test
    public void practiceLengthLongerThan()
    {
        List<Trainee> res = HumanResourcesStatistics.practiceLengthLongerThan(_allEmployees, 25);
        List<Trainee> test = new ArrayList<>();
        test.add(t1); test.add(t3);
        Assert.assertEquals(test, res);
        Assert.assertEquals(945, t3.getSalary().intValue());
        Assert.assertEquals(735, t1.getSalary().intValue());
    }

    @Test
    public void seniorityLongerThanMonths()
    {
        List<Worker> res = HumanResourcesStatistics.seniorityLongerThan(_allEmployees, 83);
        List<Worker> test = new ArrayList<>();
        test.add(d); test.add(m1); test.add(w2);  test.add(w3);
        Assert.assertEquals(test, res);
        Assert.assertEquals(300, w2.getBonus().intValue());
    }

    @Test
    public void seniorityBetweenOneAndThreeYears()
    {
        List<Worker> res = HumanResourcesStatistics.seniorityBetweenOneAndThreeYears(_allEmployees);
        List<Worker> test = new ArrayList<>();
        test.add(m2); test.add(w5);
        Assert.assertEquals(test, res);
        Assert.assertEquals(13200, m2.getSalary().intValue());
        Assert.assertEquals(4620, w5.getSalary().intValue());
    }

    @Test
    public void seniorityLongerThanEmployee() {
        List<Worker> res = HumanResourcesStatistics.seniorityLongerThan(_allEmployees, w1);
        List<Worker> test = new ArrayList<>();
        test.add(d); test.add(m1); test.add(w2); test.add(w3);
        Assert.assertEquals(test, res);
        Assert.assertEquals(4000, d.getSalary().intValue());
        Assert.assertEquals(4000, m1.getSalary().intValue());
        Assert.assertEquals(4000, w2.getSalary().intValue());
        Assert.assertEquals(4000, w3.getSalary().intValue());
    }

    @Test
    public void seniorityBetweenTwoAndFourYearsAndAgeGreaterThan()
    {
        List<Worker> res = HumanResourcesStatistics.seniorityBetweenTwoAndFourYearsAndAgeGreaterThan(_allEmployees, 40);
        List<Worker> test = new ArrayList<>();
        test.add(w5);
        Assert.assertEquals(test, res);
    }
}