package L00162110.assign1;

public class EmployeeTester{

    public static void main(String[] args) {
        Employee e1 = new Employee();
        Employee e2 = new Employee("Arthur Guinness", 25, 40000);
        Employee e3 = new Employee("John Jameson", 25, 50000);
        Employee e4 = new Employee("John Jameson", 25, 50000);
        Manager m1 = new Manager("Paddy Losty", 48, 50000, 25000);
        Manager m2 = new Manager();

        System.out.println("\n\tEmployee Descriptions\n***************************************");
        System.out.println(e1.getDescription());
        System.out.println(e2.getDescription());
        System.out.println(e3.getDescription());
        System.out.println(e4.getDescription());
        System.out.println(m1.getDescription() + "Effective Salary = " + m1.getSalary());
        System.out.println(m2.getDescription() + "Effective Salary = " + m2.getSalary());


        System.out.println("\n\tEmployee setSalary, getSalary and getEmployeeID\n***************************************");
        e2.setSalary(505050);
        System.out.println("Arthur's salary = " + e2.getSalary());
        System.out.println("Arthur's employee ID = " + e2.getEmployeeID());

        System.out.println("\n\tManager setSalary, getBonus, getSalary\n***************************************");
        m1.setSalary(80000);
        System.out.println("Paddy's salary = " + m1.getSalary());
        System.out.println("Paddy's bonus = " + m1.getBonus());


        int returnVal = (e2.compareTo(e3));

        System.out.print("\n\tCompare Salaries\n***************************************");
        if(returnVal > 0) {
            System.out.println("\n" + e2.getName() + " makes more money then " + e3.getName());
        }
        else if (returnVal < 0 ){
            System.out.println("\n" + e3.getName() + " makes more money then " + e2.getName());
        }
        else {
            System.out.println("\n" + e2.getName() + " and " + e3.getName() + " make the same money");
        }

        boolean val = (e3.equals(e2));

        System.out.println("\n\tCompare Employees\n***************************************");
        System.out.println(e3.getName() + " equals " + e2.getName() + " : " + val);

        val = (e3.equals(e4));
        System.out.println(e3.getName() + " equals " + e4.getName() + ": " + val);

        System.out.print("\ndoJob Check\n***************************************");
        e2.doJob(new Job("Serve Drink", 10.00, 10.00));
        System.out.println(e2.getDescription());

       }
}
