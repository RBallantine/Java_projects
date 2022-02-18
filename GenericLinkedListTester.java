package L00162110.Assignment_2;

/**
 * Tester class to test GenericArrayList Methods
 *
 * @author Ronan Ballantine L00162100
 */
public class GenericLinkedListTester {

    public static void main(String[] args) {

        GenericLinkedList<Double> myList = new GenericLinkedList<>();

        //Check if list is empty
        System.out.println("\n********************************************");
        System.out.println("List is empty?: " + myList.isEmpty());

        //Add element to the list using element parameter
        myList.add(3.0);
        System.out.println("\n********************************************");
        myList.printList("List should have a 3.0 in index 0:\n");

        //Check addToStart method
        System.out.println("\n********************************************");
        myList.addToStart(1.0);
        myList.printList("List should have a 1.0 and 3.0 in index 0 and 1:\n");

        //add to the list using index parameter
        System.out.println("\n********************************************");
        myList.add(1, 2.0);
        myList.printList("List should have a 2.0 in index 1:\n");

        //add more elements
        for (double i = 4.0; i <= 10.0; i++) {
            myList.add(i);
        }

        // print list 1.0 to 10.0
        System.out.println("\n********************************************");
        myList.printList("List should display double numbers from 1.0 to 10.0:\n");

        // Attempt to write into an index out of bounds
        System.out.println("\n********************************************");
        System.out.println("Index should be out of bounds: ");
        try {
            myList.add(15, 20.0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds\n");
        }

        //set element at index 7 to 33.0 and display previous element
        System.out.println("\n********************************************");
        System.out.println("Element previously at index 7: " + myList.set(7, 33.0));
        myList.printList("Index 7 should be changed to 33.0: \n");

        //Attempt to set to an index out of bounds
        System.out.println("\n********************************************");
        System.out.println("Index should be out of bounds: ");
        try {
            myList.set(16, 33.0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds");
        }

        //Get element at index 5
        System.out.println("\n********************************************");
        System.out.println("6.0 should be displayed here: " + myList.get(5));

        //Attempt to get an element at an index out of bounds
        System.out.println("\n********************************************");
        System.out.println("Index should be out of bounds: ");
        try {
            myList.get(20);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds");
        }

        //Remove element 33 from the list
        System.out.println("\n********************************************");
        System.out.println("Element 33.0 was removed?: " + myList.remove(33.0));
        myList.printList("List should not contain 33.0: \n");

        //Remove index that is out of bounds
        System.out.println("\n********************************************");
        System.out.println("Index should be out of bounds: ");
        try {
            myList.remove(9);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds\n");
        }

        //Remove index 5 from the list
        System.out.println("\n********************************************");
        System.out.println("Element removed from the list: " + myList.remove(5));
        myList.printList("List should not contain the removed element: \n");

        //Check if list is empty
        System.out.println("\n********************************************");
        System.out.println("List is empty?: " + myList.isEmpty());

        //Does the list contain certain integers?
        System.out.println("\n********************************************");
        System.out.println("Does list contain double 8.0?: " + myList.contains(8.0));
        System.out.println("Does list contain double 5.0?: " + myList.contains(5.0));
        myList.printList("List:");

        //Check iterator Overrides with enhanced for loop
        System.out.println("\n********************************************");
        System.out.println("iterated List: ");
        for (double element : myList) {
            System.out.print(element + " ");
        }


        //Check the list size
        System.out.println("\n\n********************************************");
        myList.printList("List: ");
        System.out.println("List size = " + myList.size());

        //Rotate elements by a distance of 3
        System.out.println("\n\n********************************************");
        myList.printList("List prior to rotation: \n");
        myList.rotate(3);
        myList.printList("\nRotated List by a distance of 3: \n");
        myList.printList("\nList prior to rotation: \n");
        myList.rotate(-3);
        myList.printList("\nRotated List by a distance of -3: \n");

    }
}
