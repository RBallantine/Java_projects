package L00162110.Assignment_2;

/**
 * Tester class to test GenericArrayList Methods
 *
 * @author Ronan Ballantine L00162100
 */
public class GenericArrayListTester {
    public static void main(String[] args) {

        GenericArrayList<Integer> myList = new GenericArrayList<>();

        //Check if list is empty
        System.out.println("\n********************************************");
        System.out.println("List is empty?: " + myList.isEmpty());

        //Add element to the list using element parameter
        myList.add(1);
        myList.add(3);
        System.out.println("\n********************************************");
        myList.printList("List should have a 1 and 3 in index 0 and 1:\n");

        //add to the list using index parameter
        System.out.println("\n********************************************");
        myList.add(1, 2);
        myList.printList("List should have a 2 in index 1:\n");

        //add more elements
        for (int i = 4; i <= 10; i++) {
            myList.add(i);
        }

        // print list 1 to 10
        System.out.println("\n********************************************");
        myList.printList("List should display integers from 1 to 10:\n");

        // Attempt to write into an index out of bounds
        System.out.println("\n********************************************");
        System.out.println("Index should be out of bounds: ");
        try {
            myList.add(15, 20);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds\n");
        }

        //set element at index 7 to 33 and display previous element
        System.out.println("\n********************************************");
        System.out.println("Element previously at index 7: " + myList.set(7, 33));
        myList.printList("Index 7 should be changed to 33: \n");

        //Attempt to set to an index out of bounds
        System.out.println("\n********************************************");
        System.out.println("Index should be out of bounds: ");
        try {
            myList.set(16, 33);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds");
        }

        //Get element at index 5
        System.out.println("\n********************************************");
        System.out.println("6 should be displayed here: " + myList.get(5));

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
        System.out.println("Element 33 was removed?: " + myList.remove((Integer) 33));
        myList.printList("List should not contain 33: \n");

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
        System.out.println("Element removed from the list: " + myList.remove(8));
        myList.printList("List should not contain the removed element: \n");

        //Check if list is empty
        System.out.println("\n********************************************");
        System.out.println("List is empty?: " + myList.isEmpty());

        //Does the list contain certain integers?
        System.out.println("\n********************************************");
        System.out.println("Does list contain integer 8?: " + myList.contains(8));
        System.out.println("Does list contain integer 5?: " + myList.contains(5));
        myList.printList("List:");

        //Check iterator with enhanced for loop
        System.out.println("\n********************************************");
        System.out.println("iterated List: ");
        for (int element : myList) {
            System.out.print(element + " ");
        }


        //Check the list size
        System.out.println("\n\n********************************************");
        myList.printList("List: ");
        System.out.println("List size = " + myList.size());

        //Check toString method
        System.out.println("\n********************************************");
        System.out.println(myList);

        //Check Positive rotation
        System.out.println("\n********************************************");
        myList.printList("List prior to rotation: \n");
        myList.rotate(3);
        myList.printList("\nRotated List by a distance of 3: \n");

        //Check negative rotation
        System.out.println("\n********************************************");
        myList.printList("List prior to rotation: \n");
        myList.rotate(-3);
        myList.printList("\nRotated List by a distance of -3: \n");


    }
}
