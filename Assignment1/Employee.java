package L00162110.assign1;

import java.util.Objects;

/**
 * The Employee class has the following additional
 * attributes: int employeeID, double salary.
 *
 * Author Ronan Ballantine L00162110
 */

public class Employee extends Person implements Comparable<Employee>, PartTimeAble {

    private int employeeID;
    private static int idGenerator = 0;
    private double salary;

    /**
     * Employee constructor takes name and age
     * from super class, initialises salary to
     * '0.0' and calls a static idGenerator
     */
    public Employee() {
        super();
        this.salary = 0.0;
        idGenerator+=1;
        this.employeeID = idGenerator;
    }

    /**
     * Constructor sets employee name, age
     * and salary values
     * @param name String employee's name
     * @param age int employees age
     * @param salary double employee's salary
     */
    public Employee(String name, int age, double salary) {
        super(name, age);
        this.salary = salary;
        idGenerator+=1;
        this.employeeID = idGenerator;
    }

    /**
     * constructor sets employee's name, age
     * salary and output format type
     *
     * @param name String employee's name
     * @param age int employee's age
     * @param salary double employee's salary
     * @param printer ConsolePrint type
     */
    public Employee(String name, int age, double salary, ConsolePrint printer) {
        super(name, age, printer);
        this.salary = salary;
        idGenerator+=1;
        this.employeeID = idGenerator;
    }

    /**
     * Increments employee ID's
     * @return employees ID
     */
    public int getEmployeeID() {
        return this.employeeID;
    }

    /**
     * @return Employee's salary
     */
    public double getSalary() {
        return this.salary;
    }

    /**
     * Sets employee's salary
     * @param salary the given salary
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * Compares employee's age, name and salary
     * @param o employee object to compare to
     * @return true or false (equal or unequal)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return getAge() == employee.getAge() &&
                Objects.equals(getName(), employee.getName()) &&
                Double.compare(employee.salary, salary) == 0;
    }

    /**
     * Compares employee's by salary
     * @param other employye object to compare to
     * @return integer to signify employee with greatest salary
     */
    @Override
    public int compareTo(Employee other) {
        if((this.getSalary()-other.getSalary()) > 0) {
            return 1;
        }
        else if((this.getSalary()-other.getSalary()) < 0) {
            return -1;
        }
        else
            return 0;
    }

    /**
     * prints a formatted description of
     * the employee including employee's
     * ID, name, age and salary.
     * @return Description String of employee
     */
    @Override
    public String getDescription() {
        return String.format("\n%03d %-20s %-10d %,-10g", employeeID, getName(), getAge(), salary);
        //return "\nEmployee Name: " + getName() + ", Age: " + getAge() + ", Salary: " + salary + ", Employee ID: " + employeeID;
    }

    /**
     * Allows employee to complete other jobs
     * for additional pay.
     * @param j Job object the employee done includes
     *          job type, and price
     */
    @Override
    public void doJob(Job j){
        this.salary+= (j.getPrice()*0.4);
    }

}
