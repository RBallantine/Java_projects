package L00162110.assign1;

/**
 * FancyPrint class prints a String in a fancy format
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
        int textSize = 0, whiteSpace = 3;

        try {

            //Catches an empty String
            if (infoToPrint.equals("")) {
                System.out.println("Error: String empty, Please enter a valid String");
            }

            //Splits Sting into an array
            fancyPrint = infoToPrint.split(" ", 30);

            //Calculates the amount of characters in the text
            System.out.println("\n");
            for (String value : fancyPrint) {
                textSize += value.length();
                textSize += (whiteSpace + 1);
            }

            //Prints a header of the required length
            this.HeaderFooter(textSize);

            //Prints the String
            System.out.println();
            for (String s : fancyPrint) {
                System.out.format("%s %" + whiteSpace + "s", s, " ");
            }
            System.out.println();

            //Prints the footer of the required length
            this.HeaderFooter(textSize);

            //Catches a Null Pointer Exception
        } catch (NullPointerException e) {
            System.out.println("\nError: Null String received");
        }
    }


    /**
     * Method prints a header or footer
     *
     * @param textSize the amount of astrix to print
     */
    private void HeaderFooter(int textSize) {

        for (int i = 0; i < textSize; i++) {
            System.out.print("*");
        }

        System.out.println();
    }
}

