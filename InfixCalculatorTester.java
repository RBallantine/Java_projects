package L00162110.Assignment_2;

import java.util.Scanner;

/**
 * Class tests Infix Calculator
 *
 * @author Ronan Ballantine L00162100
 */
public class InfixCalculatorTester {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //Takes user input as a String and assigns it to expression
        System.out.println("Please enter calculation: ");
        String input = sc.nextLine();
        System.out.println();

        //Calls the Infix calculator evaluate method
        double result = InfixCalculator.evaluate(input);

        //displays result
        System.out.println("result = " + result);

    }
}
