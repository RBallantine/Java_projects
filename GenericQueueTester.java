package L00162110.Assignment_2;

import java.util.NoSuchElementException;

/**
 * Class tests the genericQueue methods
 *
 * @author Ronan Ballantine L00162100
 */
public class GenericQueueTester {

    public static void main(String[] args) {

        GenericQueue<String> myQueue = new GenericQueue<>();

        //Check Queue is empty
        System.out.println("\n********************************************");
        System.out.println("Queue is empty!: " + myQueue.empty());

        //Dequeue an empty queue
        System.out.println("\n\n********************************************");
        try{
            myQueue.dequeue();
        } catch(NoSuchElementException e){
            System.out.println("The queue is empty");
        }

        //Who is first in an empty queue
        System.out.println("\n\n********************************************");
        try{
            myQueue.first();
        } catch(NoSuchElementException e){
            System.out.println("The queue is empty");
        }

        //Insert elements into the Queue and print
        System.out.println("\n********************************************");
        myQueue.enque("Michelangelo");
        myQueue.enque("Raphael");
        myQueue.enque("Leonardo");
        myQueue.enque("Donatello");

        for (String element : myQueue) {
            System.out.print(element + " ");
        }

        //Remove the next person in the Queue and print
        System.out.println("\n\n********************************************");
        myQueue.dequeue();
        for (String element : myQueue) {
            System.out.print(element + " ");
        }

        //Check who is first on the list
        System.out.println("\n\n********************************************");
        System.out.println("Next in the Queue is: " + myQueue.first());


    }
}
