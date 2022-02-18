package L00162110.Assignment_2;

import java.util.NoSuchElementException;

/**
 * Class creates generic collections
 *
 * @author Ronan Ballantine L00162100
 */
public class GenericCollections {

    /**
     * Sort based on natural ordering (as defined by class's compareTo method)
     *
     * @param listToSort provided list to sort
     */
    public static <T extends Comparable<T>> void sort(IList<T> listToSort) {
        for (int i = 0; i < listToSort.size(); i++) //number of passes
        {
            //keeps track of positions per pass
            for (int j = 0; j < (listToSort.size() - 1 - i); j++) {
                //if left value is greater than right value
                if (listToSort.get(j).compareTo(listToSort.get(j + 1)) > 0) {
                    //swap values
                    T temp = listToSort.get(j);
                    listToSort.set(j, listToSort.get(j + 1));
                    listToSort.set(j + 1, temp);
                }
            }
        }
    }

    /**
     * Returns the maximum element of the given collection, according to the natural ordering
     * of its elements.
     *
     * @param list the collection whose maximum element is to be determined
     * @return the maximum element of the given collection, according to the natural ordering
     * of its elements.
     * Throws:
     * NoSuchElementException - if the collection is empty.
     */
    public static <T extends Comparable<T>> T max(IList<T> list) {
        if (list.isEmpty()) {
            throw new NoSuchElementException();
        }

        T maxElem = list.get(0);        //initialises generic maxElem to first index

        //cycles list to compare elements
        for (T currElem : list) {
            int check = currElem.compareTo(maxElem);

            //if result is 1, current element is greater, reassign
            if (check == 1) {
                maxElem = currElem;
            }
        }
        return maxElem;     //Return the maximum element
    }

    /**
     * @param list     the list to be rotated.
     * @param distance the distance to rotate the list. There are no constraints on this
     *                 value; it may be zero, negative, or greater than list.size().
     */
    public static <T extends Comparable<T>> void rotate(IList<T> list, int distance) {
        //if distance is positive
        if(distance>=0) {
            for (int i = 0; i < distance; i++) {      //iterate distance time
                list.add(list.remove(0));       //remove first element and add to the end of the list
            }
        }

        //If distance is negative
        if(distance<0){
            for (int i = 0; i > distance; i--) {                    //iterate distance time
                list.add(0, list.remove((list.size()-1)));    //Remove the last element and add it to the start of the list
            }
        }
    }

    /**
     * Uses IList interface to rotate the list
     */
    public static <T extends Comparable<T>> void rotate2(IList<T> list, int distance){
        list.rotate(distance);
    }

}
