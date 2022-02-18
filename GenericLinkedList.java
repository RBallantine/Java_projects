package L00162110.Assignment_2;

import java.util.Iterator;

/**
 * Class creates a generic linked list
 *
 * @author Ronan Ballantine L00162100
 */
public class GenericLinkedList<T> implements IList<T>, Iterable<T> {
    Node head; // head of list
    Node tail; // End of list

    /**
     * Private class used to create each element containing
     * data and a pointer to the next element the list.
     */
    private class Node {
        T data;     //Generic type of data stored in the element
        Node next;  //Pointer to the next element

        // Constructor to create a new node
        Node(T data) {
            this.data = data;
            next = null;
        }
    }

    /**
     * Add an element to the end of the list
     *
     * @param elem element to be added
     */
    @Override
    public void add(T elem) {
        //if the list is empty
        Node newNode = new Node(elem);
        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }

        tail = newNode;
    }

    /**
     * Inserts the specified element at the specified position in this list
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     */

    public void add(int index, T element) {
        if (index == 0) {
            addToStart(element);
        } else {
            Node newNode = new Node(element);       //Creates a new node with the required data
            Node current = getElement((index - 1));   //Finds the node before the index position and sets a temporary node to point to it

            newNode.next = current.next;            //Points the new node to the node that will follow it in the list
            current.next = newNode;                 //Points the node before the index position to point to the new node now in the index position

            if (newNode.next == null) {
                tail = newNode;                     //Sets the tail to equal the new node if it has been added to the end of the list
            }
        }
    }

    /**
     * Replaces the element at the specified position in this list with the specified element
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     */
    @Override
    public T set(int index, T element) {
        Node current = getElement(index);      //Points a new element to the element in that index position
        T replaced = current.data;             //Sets the data to be replaced into the generic replace variable
        current.data = element;                //Sets the elements data to the new data

        return replaced;         //Returns the data that was replaced
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     */
    @Override
    public T get(int index) {
        //Throws an out of bounds exception if the index is not within bounds
        if (index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        Node current = getElement(index);     //Points a new element to the element at that index position

        return current.data;        //Returns the data from the element at the index position
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        //If the list is empty, return zero
        if (head == null) {
            return 0;
        }

        int size = 1;       //List is not empty so start at one

        Node current = head;        //New element pointing to the head of the list (used to traverse elements)

        //While the current element points to another element
        while (current.next != null) {
            size++;                     //Count that element by increasing size by one
            current = current.next;     //Points element to the next element in the list (Traverse)
        }

        return size;    //Return list size
    }

    /**
     * Removes an element in a certain position from the list and returns it
     *
     * @param index the element position in the list
     * @return the element removed from the list
     */
    @Override
    public T remove(int index) {
        //Throws an out of bounds exception if the index is not within bounds
        if (index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        //If the element is the first element
        if (index == 0) {
            Node current = head;     //Point a new element to the head element
            head = current.next;     //Point the head to the following element

            return current.data;     //Return removed element data

            //If the index is the tail element
        } else if (index == (size() - 1)) {
            Node current = getElement(index - 1);     //Points a new element to the element before the element being removed
            Node removed = current.next;            //Points a new element to the element to be removed
            current.next = null;                    //Removes the tail element from the list
            tail = current;                         //Sets the tail to the new last element on the list

            return removed.data;        //Returns the removed element data

            //Removes elements that arent the head or tail element
        } else {
            Node current = getElement(index - 1);     //Points a new element to the element before the element being removed
            Node removed = current.next;            //Points a new element to the element to be removed

            current.next = removed.next;            //Points the element before to the element after the element being removed

            return removed.data;        //Returns the removed element data
        }
    }

    /**
     * Removes the first element containing certain data from the list
     *
     * @param elem the element to remove
     * @return whether the elements was removed or not
     */
    @Override
    public boolean remove(T elem) {

        Node current = head;        //Point a new element to the head element
        Node previous = current;    //Point a new element to the current element

        //Loop cycles the list to find an element containing the required data
        for (int i = 0; i < size(); i++) {

            //If the element contains the data
            if (current.data.equals(elem)) {

                //If the element is the head element
                if (i == 0) {
                    head = current.next;        //Points head o the next element on the list

                    //If the element is the tail element
                } else if (i == (size() - 1)) {
                    previous.next = null;       //Points the previous element to null
                    tail = previous;            //Points the tail to the previous element

                    //else for all other list positions
                } else {
                    previous.next = current.next;   //Points the previous element to the element after the one being removed
                }
                return true;    //Return true if an element was removed
            }

            previous = current;         //traverses previous element pointer
            current = current.next;     //traverses current element pointer
        }

        return false;       //Returns false if an element containing that data was not found
    }


    public void addToStart(T val) {
        Node newNode = new Node(val);

        if (head == null) {
            head = newNode;
            tail = newNode;
            newNode.next = null;
        } else {
            newNode.next = head;
            head = newNode;
        }

    }

    /**
     * Finds the element at a certain index position and returns it
     *
     * @param index the position of the element in the list
     * @return the element at the required position
     */
    public Node getElement(int index) {
        if (index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        Node current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current;
    }

    /**
     * Returns true if this list contains no elements.
     *
     * @return true if this list contains no elements
     */
    @Override
    public boolean isEmpty() {

        return head == null;
    }

    @Override
    public boolean contains(T element) {
        Node current = head;

        for (int i = 0; i < size(); i++) {

            if (current.data.equals(element)) {
                return true;
            }

            current = current.next;
        }

        return false;
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

            private Node current = head;

            @Override
            public boolean hasNext() {
                return (!(current == null));
            }

            @Override
            public T next() {

                Node returnData = current;      //Stores current element data to be returned later
                current = current.next;         //Points to the next element

                return returnData.data;         //Returns the data previously stored
            }
        };
    }

    public void printList(String listName) {
        System.out.println(listName);
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

    @Override
    public void rotate(int distance) {

        //Positive distance rotation
        if (distance >= 0) {
            for (int i = 0; i < distance; i++) {
                tail.next = head;
                tail = head;
                head = head.next;
                tail.next = null;
            }
        }

        //Negative distance rotation
        if (distance < 0) {
            for (int i = 0; i > distance; i--) {

                Node previous = getElement((size()-2)); //sets previous to the element before the tail
                tail.next = head;
                head = tail;
                tail = previous;
                previous.next = null;
            }
        }
    }
}

