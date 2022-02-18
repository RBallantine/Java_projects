package L00162110.assign1;

import java.util.ArrayList;
import java.util.Collections;

/**
 * CompanyDataBase class contains a list of the
 * employees in a company. It can sort the list
 * by salary or name and compare employees
 * <p>
 * Author Ronan Ballantine L00162110
 */
public class CompanyDataBase {

    ArrayList<Employee> employees = new ArrayList<Employee>();

    /**
     * Constructor adds an employee to
     * the data base
     */
    public CompanyDataBase() {
        employees.add(new Employee());
    }

    /**
     * Constructor adds a ore defined employee
     * to the data base
     *
     * @param newGuy employee
     */
    public void addEmployee(Employee newGuy) {
        employees.add(newGuy);
    }

    /**
     * generates a list of employees in the data base
     *
     * @return ArrayList employees
     */
    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    /**
     * Sort the data base by employee name
     *
     * @return list of employees
     */
    public ArrayList<Employee> sortByName() {
        Collections.sort(employees, new CompanyNameComparator());
        return employees;
    }

    /**
     * sort the data base by salary
     *
     * @return employee list
     */
    public ArrayList<Employee> sortBySalary() {
        Collections.sort(employees);
        return employees;
    }

    /**
     * calls the bubblesort method
     */
    public void bubblesortBySalary() {
        bubblesort();
    }

    /**
     * Sort the data base by salary using
     * the bubblesort method
     */
    private void bubblesort() {
        boolean swapped;
        int round = 0;
        do {
            swapped = false;

            for (int i = 1; i < employees.size() - round; i++) {
                if (employees.get(i - 1).compareTo(employees.get(i)) > 0.00) {
                    Employee temp = employees.get(i - 1);
                    employees.set(i - 1, employees.get(i));
                    employees.set(i, temp);
                    swapped = true;
                }
            }

            round++;
        } while (swapped);
    }
}

