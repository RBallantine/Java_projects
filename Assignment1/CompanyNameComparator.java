package L00162110.assign1;

import java.util.Comparator;

/**
 * CompanyNameComparator class compares
 * the employees by their name
 *
 * Author Ronan Ballantine L00162110
 */
public class CompanyNameComparator implements Comparator<Employee> {

    /**
     * compare method is overridden to compare
     * employees by their name
     * @param o1 employee object 1
     * @param o2 employee object 2
     * @return integer to signify employee with
     * greatest name value
     */
    @Override
    public int compare(Employee o1, Employee o2) {
        return (o1.getName().compareTo(o2.getName()));
    }
}

