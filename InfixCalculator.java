package L00162110.Assignment_2;

import java.util.StringTokenizer;

/**
 * Class creates an infix calculator
 *
 * @author Ronan Ballantine L00162100
 */
public class InfixCalculator {

    /**
     * Takes in a String expression and calculates the result
     * @param expression the provided string to be calculated
     * @return the calculated result as a double
     */
    public static double evaluate(String expression) {

        //Stacks used to store operators and operands for calculations
        GenericStack<String> operators = new GenericStack<>();
        GenericStack<Double> operands = new GenericStack<>();
        GenericStack<String> temp = new GenericStack<>();
        GenericStack<String> parts = new GenericStack<>();

        //Separates the expression into operators and operands and stores them in a temporary stack
        StringTokenizer st = new StringTokenizer(expression, "+-)(*/", true);
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            temp.push(token);
        }

        //adds the operators and operands to a parts stack to reverse the order
        while(!temp.empty()) {
            parts.push(temp.pop());     //removes an element from temp and adds it to parts
        }

        //calculates expression
        while(!parts.empty()) {
            String currPart = parts.pop();
            switch (currPart) {
                case ("("):
                    break;
                case ("+"):
                case ("-"):
                case ("*"):
                case ("/"):
                    operators.push(currPart);
                    break;
                case (")"):
                    double rightOperand = operands.pop();
                    double leftOperand = operands.pop();
                    double result = 0.0;
                    String operator = operators.pop();
                    switch (operator) {
                        case ("+"):
                            result = leftOperand + rightOperand;
                            break;
                        case ("-"):
                            result = leftOperand - rightOperand;
                            break;
                        case ("*"):
                            result = leftOperand * rightOperand;
                            break;
                        case ("/"):
                            result = leftOperand / rightOperand;
                            break;
                    }
                    operands.push(result);
                    break;
                default:
                    operands.push(Double.parseDouble(currPart));
                    break;
            }
        }
        //returns the result from the operands stack
        return operands.pop();
    }
}
