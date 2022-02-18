package L00162110.Assignment_2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class creates a generic queue
 *
 * @author Ronan Ballantine L00162100
 */
public class GenericQueue<T> implements IQueue<T>, Iterable<T> {

    private IList<T> queueData;

    /**
     * Creates a default constructor
     * backed by a GenericArrayList
     */
    public GenericQueue() {
        queueData = new GenericArrayList<>();
    }

    /**
     * Creates a constructor with a supplied queue list
     * @param queueData the queue list provided
     */
    public GenericQueue(IList queueData){
        this.queueData = queueData;
    }

    /**
     * Inserts the specified element into the queue
     * @param element the element argument.
     */
    @Override
    public void enque(T element) {
        queueData.add(element);
    }

    /**
     * Retrieves and removes the head of this queue.
     *
     * @return the head of this queue
     */
    @Override
    public T dequeue() {
        if(empty()){
            throw new NoSuchElementException();
        }

        return queueData.remove(0);
    }

    /**
     * Retrieves, but does not remove, the head of this queue.
     *
     * @return the head of this queue
     */
    @Override
    public T first() {
        if(empty()){
            throw new NoSuchElementException();
        }

        return queueData.get(0);
    }

    /**
     * Tests if this Queue is empty.
     *
     * @return {@code true} if and only if this queue contains
     * no items; {@code false} otherwise.
     */
    @Override
    public boolean empty() {
        return queueData.isEmpty();
    }

    /**
     * Enhanced for loop uses this to iterate
     * over the Queue list
     *
     * @return the iterated list
     */
    public Iterator<T> iterator() {
        return queueData.iterator();
    }
}
