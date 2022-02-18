package L00162110.Assignment_2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class creates a generic array list
 *
 * @author Ronan Ballantine L00162100
 */
public class GenericArrayList<T> implements IList<T>, Iterable<T> {

    /**
     * This will hold our data - an ArrayList is nothing more than a managed array
     */
    private T[] buffer;

    /**
     * Index of next free location - will also help us to determine if the buffer is full
     */
    private int nextFreeLoc;

    /**
     * This will change as buffer fills up and we allocate more and more storage space
     */
    private int currentCapacity;

    private static final int INITIAL_CAPACITY = 3;

    /**
     * Default Constructor
     */
    public GenericArrayList() {
        currentCapacity = INITIAL_CAPACITY;
        nextFreeLoc = 0;
        buffer = (T[]) new Object[currentCapacity];
    }

    /**
     * Add to the end of the list.
     * <p>
     * Each time you need to grow the array you should declare a temporary array
     * which is double the currentCapacity of buffer.
     * <p>
     * Copy everything in buffer to tempArray
     * <p>
     * Then update the buffer reference to refer to tempArray
     *
     * @param elem The data to be added to the end of the managed array
     */

    @Override
    public void add(T elem) {
        growArrayIfNeeded();            //private helper method to increase the array if more space is needed

        buffer[nextFreeLoc++] = elem;   //adds the data to the next free location then increments the next free location by one.
    }

    /**
     * Adds elements to a specified index
     * does not allow gaps or bufferOverflows.
     * Grows the array if required
     *
     * @param index where to insert
     * @param elem  the data to insert
     */
    public void add(int index, T elem) {

        //if index is out of bounds
        if (index > nextFreeLoc) {

            throw new IndexOutOfBoundsException();

        } else { //if index is within bounds

            //doubles the array size if needed.
            growArrayIfNeeded();

            // shuffle everything up from right to left
            // Note that this is a much easier mechanism to implement than trying to insert the new
            // element and then shuffle everything from left to right
            for (int i = nextFreeLoc; i > index; i--) {
                buffer[i] = buffer[i - 1];
            }

            //New element inserted
            buffer[index] = elem;
            //Increment to adjust the next free location
            nextFreeLoc++;
        }
    }

    @Override
    public T set(int index, T element) {
        if (index >= buffer.length) {
            throw new IndexOutOfBoundsException();
        }

        T previous = buffer[index];
        buffer[index] = element;

        return previous;
    }

    /**
     * Retrieve an element from the list
     *
     * @param index the location to be return
     * @return the data at buffer[index]
     */
    @Override
    public T get(int index) {
        if (index >= nextFreeLoc) {
            throw new IndexOutOfBoundsException();  //index is out of bounds of array
        }
        return buffer[index];      //returns data from the index position of the array
    }

    /**
     * Removes the first occurrence of an element from the array
     * It does this by "closing the gap" after/if it finds a matching element in the array.
     *
     * @param elem the element to remove
     */
    @Override
    public boolean remove(T elem) {

        for (int index = 0; index < nextFreeLoc; index++) {
            if (buffer[index].equals(elem)) {

                //Close the gap - move elements 1 position to the left
                for (int i = index; i < nextFreeLoc; i++) {
                    buffer[i] = buffer[i + 1];
                }

                nextFreeLoc--;
                return true;
            }
        }
        return false;
    }

    /**
     * Remove the element at the specified index.
     *
     * @param index the index of the element that should be removed
     */
    @Override
    public T remove(int index) {
        //if index is within bounds
        if (index < nextFreeLoc) {
            T temp = buffer[index];         //store the removed element to a temporary element

            //Close the gap - move elements 1 position to the left
            for (int i = index; i < nextFreeLoc; i++) {
                buffer[i] = buffer[i + 1];
            }

            nextFreeLoc--;      //decrement the next free location

            return temp;        //the removed element

        } else {
            throw new IndexOutOfBoundsException();      //if the index is out of bounds
        }
    }

    /**
     * Checks if the list is empty
     *
     * @return whether the list is empty or not
     */
    public boolean isEmpty() {
        return (nextFreeLoc == 0);      //Returns true if the list is empty
    }

    /**
     * Searches through the array to see if a matching element is present
     * Note: We already use this mechanism for one of the remove() methods.
     *
     * @param element element to search the array for
     * @return whether the element was present in the list or not
     */
    @Override
    public boolean contains(T element) {
        //cycles list to look for the element
        for (int index = 0; index < nextFreeLoc; index++) {
            //if the element is found return true
            if (buffer[index].equals(element)) {
                return true;
            }
        }
        return false;       //if the element is not found
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     * Specified by:
     * iterator in interface Iterable<T>
     *
     * @return an iterator over the elements in this list in proper sequence
     */
    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {

            private int position = 0;       //Starting position

            /**
             * Checks if the list has an any elements left to
             * follow its current position
             *
             * @return true if it has elements left to follow
             */
            @Override
            public boolean hasNext() {
                return position < nextFreeLoc;
            }

            /**
             * Returns the current element position and
             * increments the position
             *
             * @return the element at the current position
             */
            @Override
            public T next() {

                if (position == nextFreeLoc) {
                    throw new NoSuchElementException();     //If the end of the list is reached
                }

                return buffer[position++];      //return the element at the current position and increment the position
            }
        };
    }


    /**
     * @return the size (i.e. the number of elements stored) in the list
     */
    public int size() {
        return nextFreeLoc;     //The next free location equals the size of the arrayList
    }

    /**
     * Private helper method to check if the currently allocated space is full.
     * If it is then it will allocate a bigger array, copy the contents, and set our
     * instance field (buffer) to refer to the newly allocated space.
     */
    private void growArrayIfNeeded() {
        //grows the array if the next free location is one below the current capacity
        if (nextFreeLoc == (currentCapacity - 1)) {

            int length = (buffer.length * 2);
            //Allocate double the space to a temporary array
            T[] tempArr = (T[]) new Object[length];
            currentCapacity *= 2;
            //copy elements from the old space into the new list space
            for (int i = 0; i < buffer.length; i++) {
                tempArr[i] = buffer[i];
            }

            //Recreate the buffer with double the space
            buffer = (T[]) new Object[length];
            //copy from the temporary space back to the original but increased space
            for (int i = 0; i < buffer.length; i++) {
                buffer[i] = tempArr[i];
            }
        }
    }

    /**
     * Prints a string containing the ArrayList data
     *
     * @return String of ArrayList data
     */
    @Override
    public String toString() {
        String data = "";
        for (int i = 0; i < nextFreeLoc; i++) {
            data += " " + buffer[i] + ",";
        }

        return "MyGenericArrayList[" + data + " ]";
    }

    /**
     * Prints the Array list data
     *
     * @param listName the name given to the printed list
     */
    @Override
    public void printList(String listName) {
        System.out.print(listName);
        System.out.print(" ");
        for (int i = 0; i < size(); i++) {
            System.out.print(get(i));
            if (i == (size() - 1)) {
                System.out.print(" ");
            } else {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    /**
     * Rotates the elements in a list by any distance
     *
     * @param distance the amount of rotations
     */
    @Override
    public void rotate(int distance) {

        //if the distance is a positive number, rotate left times the distance amount
        if (distance >= 0) {
            for (int i = 0; i < distance; i++) {
                add(remove(0));     //Remove the element in index zero and add it to the end of the list
            }
        }

        //if the distance is a negative number, rotate right times the distance amount
        if(distance<0) {
            for (int i = 0; i > distance; i--) {
                add(0, remove((size()-1)));     //remove the last element in the list and add it to the start
            }
        }
    }
}

