package L00162110.Assignment_2;

import java.util.EmptyStackException;
import java.util.Iterator;

/**
 * Class creates a generic Stack
 *
 * @author Ronan Ballantine L00162100
 */
public class GenericStack<T> implements IStack<T>, Iterable<T> {

    IList<T> stackData;

    /**
     * Default constructor creates stack backed by an arrayList
     */
    public GenericStack() {
        stackData = new GenericArrayList<>();
    }

    /**
     * Constructor creates a stack containing the IList data
     *
     * @param stackData the list to be stacked
     */
    public GenericStack(IList<T> stackData) {
        this.stackData = stackData;
    }

    /**
     * Pushes an item onto the top of this stack
     *
     * @param element the element argument.
     */
    @Override
    public void push(T element) {
        stackData.add(element);     //adds element to the top of the stack
    }

    /**
     * Removes the object at the top of this stack and returns that
     * object as the value of this function.
     *
     * @return The object at the top of this stack
     */
    @Override
    public T pop() {
        if (empty()) {
            throw new EmptyStackException();        //Throws an exception if the stack is empty
        }
        return stackData.remove(stackData.size() - 1);      //removes the element at the top of the stack and returns it
    }

    /**
     * Looks at the object at the top of this stack without removing it
     * from the stack.
     *
     * @return the object at the top of this stack
     */
    @Override
    public T peek() {
        if (empty()) {
            throw new EmptyStackException();
        }

        return stackData.get(stackData.size() - 1);     //Returns the data at the top of the stack
    }

    /**
     * Tests if this stack is empty.
     *
     * @return {@code true} if and only if this stack contains
     * no items; {@code false} otherwise.
     */
    @Override
    public boolean empty() {

        return stackData.isEmpty();     //Returns true if the stack is empty
    }

    /**
     * Iterates through the stack list using
     * GenericArrayList iterator method
     *
     * @return iterated stack list
     */
    @Override
    public Iterator<T> iterator() {

        return stackData.iterator();        //Calls iterator from GenericArrayList
    }
}
