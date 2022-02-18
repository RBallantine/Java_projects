package L00162110.assign1.WorkflowItem12;

/**
 * SimplePrint class prints a String in a normal output
 * <p>
 * Author Ronan Ballantine L00162110
 */
public class SimplePrint implements ConsolePrint {

    /**
     * Overrides printInfo interface to print information
     *
     * @param infoToPrint String of information to be printed
     */
    @Override
    public void printInfo(String infoToPrint) {
        try {

            //catches an empty string
            if (infoToPrint.equals("")) {
                System.out.println("\n\nError: String empty, Please enter a valid String");
            }

            //prints the required String
            System.out.println();
            System.out.println(infoToPrint);
        }
        //Catches a Null Pointer Exception
        catch (NullPointerException e) {
            System.out.println("Error: Null String received");
        }
    }

}
