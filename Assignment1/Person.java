package L00162110.assign1;

/**
 * Person is an abstract class which has
 * the following attributes: String name,
 * int age. It has an abstract method
 * getDescription() in addition to
 * constructors and getters
 *
 * Author Ronan Ballantine L00162110
 */
public abstract class Person {
    private int age;
    private String name;
    private ConsolePrint printer = null;

    /**
     * Person constructor initialises name
     * to "Don't know" and age to '0'
     */
    public Person() {
        this.name = "Don't know";
        this.age = 0;
    }

    /**
     * Constructor sets person name and age
     * @param name sets string name
     * @param age  sets integer age
     */
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Constructor sets name, age and output format
     * of the print description
     *
     * @param name sets String persons name
     * @param age sets int person age
     * @param printer   sets ConsolePrinter type
     */
    public Person(String name, int age, ConsolePrint printer) {
        this.name = name;
        this.age = age;
        this.printer = printer;
    }

    /**
     * Chooses the type of output format
     * @param printer The chosen output format
     */
    public void setPrinter(ConsolePrint printer) {
        this.printer = printer;
    }

    /**
     * prints getDescription() String in the chosen
     * output format
     */
    public void printDescription() {
        this.printer.printInfo(getDescription());
    }

    /**
     * interface to get description
     */
    public abstract String getDescription();

    /**
     * @return Person's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return Person's age
     */
    public int getAge() {
        return age;
    }

}
