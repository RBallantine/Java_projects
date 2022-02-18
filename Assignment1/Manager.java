package L00162110.assign1;

/**
 * A Manager is a special extension of employee
 * that earns a bonus which is included in
 * their total salary
 *
 * Author Ronan Ballantine L00162110
 */
public class Manager extends Employee {
    private double bonus;

    /**
     * initialises manager constructor
     * to super name, age and salary values
     * and manager bonus value.
     */
    Manager() {
        super();
        this.bonus = bonus;
    }

    /**
     * Manager constructor sets name, age
     * salary and bonus.
     *
     * @param name String manager name
     * @param age int manager age
     * @param salary double manager salary
     * @param bonus double manager bonus
     */
    Manager(String name, int age, double salary, double bonus) {
        super(name, age, salary);
        this.bonus = bonus;
    }

    /**
     * Manager constructor sets name, age
     * salary, print output format and bonus.
     *
     * @param name String manager name
     * @param age int manager age
     * @param salary double manager salary
     * @param bonus double manager bonus
     * @param printer ConsolePrint output type
     */
    Manager(String name, int age, double salary, ConsolePrint printer, double bonus) {
        super(name, age, salary, printer);
        this.bonus = bonus;
    }

    /**
     * method retrieves manager's bonus
     * @return double managers bonus
     */
    public double getBonus() {
        return this.bonus;
    }

    /**
     * overides employee's getSalary method
     * to include the bonus in the return
     * value
     * @return salary plus bonus
     */
    @Override
    public double getSalary() {
        return super.getSalary() + bonus;
    }

    /**
     * prints manager description including ID, name
     * age, salary and bonus
     * @return String manager's description
     */
    public String getDescription() {
        return String.format("\n%03d %-20s %-10d %,-10g bonus = %g ", getEmployeeID(), getName(), getAge(), super.getSalary(), this.bonus);
    }

    /**
     * doJob method allows manager to do jobs and adds
     * price to salary
     * @param j Job object the employee done includes
     */
    @Override
    public void doJob(Job j) {
        double newSalary = super.getSalary();
        newSalary += j.getPrice()*0.7;
        super.setSalary(newSalary);
    }
}
