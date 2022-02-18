package L00162110.assign1;

public class ConsolePrintTester {

    public static void main(String[] args) {

        SimplePrint simplePrintObject = new SimplePrint();
        FancyPrint fancyPrintObject = new FancyPrint();

        Student s1 = new Student("Darby O'Gill", 78, "Leprechaun Catching", simplePrintObject);
        Student s2 = new Student("Maximus Aurelius", 32, "Gladiator", fancyPrintObject);

        Employee e1 = new Employee("Patrick Bamford", 27, 50000, fancyPrintObject);
        Employee e2 = new Manager("Marcelo Bielsa", 65, 100000, fancyPrintObject, 20000);

        e1.doJob(new Job("Score Goals", 2500, 1.5));
        e2.doJob(new Job("Pick Team", 3000, 1.5));


        System.out.print("\nsimple Print:");
        s1.printDescription();
        System.out.print("\nFancy Print:");
        s2.printDescription();
        System.out.print("\nFancy Print:");
        e1.printDescription();
        System.out.print("\nFancy Print:");
        e2.printDescription();

        //Swap print objects
        s1.setPrinter(fancyPrintObject);
        s2.setPrinter(simplePrintObject);
        e1.setPrinter(simplePrintObject);
        e2.setPrinter(simplePrintObject);


        System.out.print("\nFancy Print:");
        s1.printDescription();
        System.out.print("\nsimple Print:");
        s2.printDescription();
        System.out.print("\nsimple Print:");
        e1.printDescription();
        System.out.print("\nsimple Print:");
        e2.printDescription();

    }
}
