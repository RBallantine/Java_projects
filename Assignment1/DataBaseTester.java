package L00162110.assign1;

public class DataBaseTester {
    public static void main(String[] args) {
        CompanyDataBase dataBase = new CompanyDataBase();

        dataBase.addEmployee(new Employee());
        dataBase.addEmployee(new Employee("Arthur Guinness", 45, 40000));
        dataBase.addEmployee(new Employee("John Jameson", 55, 80000));
        dataBase.addEmployee(new Manager("Paddy Losty", 65, 70000, 20000));
        dataBase.employees.remove(0);

        for (Employee currEmployee : dataBase.getEmployees()) {
            System.out.println(currEmployee.getDescription());
            //Check to see if the "effective salary" is correct
            System.out.println("Effective salary: " + currEmployee.getSalary());
        }

        //Sot by name
        System.out.print("\nDatabase sorted by name\n*****************************");
        //Calls method to sort the database by name
        dataBase.sortByName();
        //Prints database
        for (Employee currEmployee : dataBase.getEmployees()) {
            System.out.format("%s", currEmployee.getDescription());
        }

        //Sort by effective salary using buublesort
        System.out.println("\n\n Sorted by effective Salary using bubbleSort: \n*****************************");
        dataBase.bubblesortBySalary();
        for (Employee currEmployee : dataBase.getEmployees()) {
            System.out.format("%s", currEmployee.getDescription());
        }

        //Test doJob interface for manager
        dataBase.employees.get(2).doJob(new Job("Serve Drink", 1000.00, 10.00));
        System.out.println("\n\nExtra Pay: " + dataBase.employees.get(2).getDescription());

    }




}