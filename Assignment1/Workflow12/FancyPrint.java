package L00162110.assign1.WorkflowItem12;

/**
 * Class prints a String in a Fancy manner
 * <p>
 * Author Ronan Ballantine L00162110
 */
public class FancyPrint implements ConsolePrint {
    String[] fancyPrint;

    /**
     * prints the String in a fancy format
     *
     * @param infoToPrint String of information to be printed
     */
    @Override
    public void printInfo(String infoToPrint) {
        int textSize = 0, whiteSpace = 4;

        try {

            if (infoToPrint.equals("")) {
                System.out.println("Error: String empty, Please enter a valid String");
            }


            fancyPrint = infoToPrint.split(" ", 30);

            //Calculates text length
            System.out.println("\n");
            for (int i = 1; i < fancyPrint.length; i++) {
                textSize += fancyPrint[i].length();
                textSize += whiteSpace;
            }

            //Prints the Header
            this.HeaderFooter(fancyPrint[0], textSize);

        }
        //Catches null pointer exceptions
        catch (NullPointerException e) {
            System.out.println("\nError: Null String received");
        }
    }


    /**
     * Prints the header and footer
     *
     * @param heading  firts element in the String array
     * @param textSize length of the text
     */
    private void HeaderFooter(String heading, int textSize) {
        char astrix = '*';
        int headingPosition = 0;

        //Creates even number for half of text size
        if (textSize % 2 == 1) {
            textSize += 1;
        }

        //calculates header position
        if (heading.length() % 2 == 1) {
            headingPosition = ((textSize - (heading.length() + 1)) / 2);
            textSize += 1;
        } else {
            headingPosition = ((textSize - (heading.length() + 2)) / 2);
        }

        //prints header
        for (int i = 0; i < (textSize - (heading.length() + 1)); i++) {
            if (i == headingPosition) {
                System.out.print(" " + heading + " ");
            } else {
                System.out.print(astrix);
            }
        }

        //calls method to print main body
        this.MainBody(fancyPrint);
        System.out.println();

        //prints footer
        for (int i = 0; i < textSize; i++) {
            System.out.print(astrix);
        }
        System.out.println();

    }

    /**
     * Prints the main body of the String
     *
     * @param body String to print
     */
    private void MainBody(String[] body) {

        //Prints String array without element 0 (Header)
        System.out.println();
        for (int i = 1; i < body.length; i++) {
            System.out.format("%s %4s", body[i], " ");
        }
    }
}


