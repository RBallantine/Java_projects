package L00162110.Assignment_2;
/**
 * Class Tests generic stack methods
 *
 * @author Ronan Ballantine L00162100
 */

import java.util.EmptyStackException;

public class GenericStackTester {

    public static void main(String[] args) {

        GenericStack<Integer> myStack = new GenericStack<>();

        //Check if the stack is empty
        System.out.println("\n********************************************");
        System.out.println("The stack is empty?: " + myStack.empty());

        //Peek at an empty stack
        System.out.println("\n********************************************");
        try {
            System.out.println("Top element in the stack is: " + myStack.peek());
        } catch (EmptyStackException e) {
            System.out.println("Stack is empty");
        }

        //Remove from an empty stack
        System.out.println("\n********************************************");
        try {
            System.out.println("The element removed from the top of the stack is: " + myStack.pop());
        } catch (EmptyStackException e) {
            System.out.println("The stack is empty");
        }

        //Push elements into the stack
        System.out.println("\n********************************************");
        for (int i = 0; i <=5; i++) {
            myStack.push(i);
        }

        //Print stack using iterator
        System.out.println("\n********************************************");
        System.out.println("Stack: \n");
        for (int element : myStack) {
            System.out.print(element + " ");
        }

        //Peek at the top element
        System.out.println("\n********************************************");
        try {
            System.out.println("Top element in the stack is: " + myStack.peek());
        } catch (EmptyStackException e) {
            System.out.println("Stack is empty");
        }

        //Remove the top element in the stack
        System.out.println("\n********************************************");
        try {
            System.out.println("The element removed from the top of the stack is: " + myStack.pop());
        } catch (EmptyStackException e) {
            System.out.println("The stack is empty");
        }

        //Peek at the new element at the top of the stack
        System.out.println("\n********************************************");
        try {
            System.out.println("Top element in the stack is: " + myStack.peek());
        } catch (EmptyStackException e) {
            System.out.println("Stack is empty");
        }

        //Check if the stack is empty
        System.out.println("\n********************************************");
        System.out.println("Is the stack empty?: " + myStack.empty());

        //Print stack using iterator
        System.out.println("\n********************************************");
        System.out.println("iterated list: \n");
        for (int element : myStack) {
            System.out.print(element + " ");
        }
    }
}
