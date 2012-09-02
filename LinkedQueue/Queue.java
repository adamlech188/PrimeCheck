import java.util.Iterator;

// -------------------------------------------------------------------------
/**
 * This is an interface that lists the methods to be implemented.
 *
 * @param <Item>
 * @author Adam Lech (adaml8)
 * @version 2012.06.28
 */
public interface Queue<Item>
    extends Iterable<Item>
{
    // ----------------------------------------------------------
    /**
     * Gets number of elements in queue.
     *
     * @return number of elements in the queue.
     */
    public int size();


    /**
     * Method that adds element to the queue. First it creates new link that
     * will store data passed in parameter.
     *
     * @param newItem
     *            data entered by the user.
     */
    public void enqueue(Item newItem);


    // ----------------------------------------------------------
    /**
     * This method removes the first element added to the queue.Since it's not a
     * circular linked queue, it has to handle special case when there is only
     * one element in the queue.
     *
     * @return returnValue removed data element.
     */
    public Item dequeue();


    // ----------------------------------------------------------
    /**
     * Returns value of the element that was first added to the queue.
     *
     * @return value stored in the head of the queue.
     */
    public Item peek();


    // ----------------------------------------------------------
    /**
     * Clears the current queue by setting links to null.
     */
    public void clear();


    // ----------------------------------------------------------
    /**
     * Method that creates Iterator object that enables to access all elements
     * in the queue.
     *
     * @return new Iterator object.
     */
    public Iterator<Item> iterator();


    /**
     * Compares queue to check if all the elements in the queue are the same.
     *
     * @param other
     *            - a queue which value is to be compared.
     * @return true if all the elements in the queue are the same, false
     *         otherwise
     */

    public boolean equals(Object other);


    /**
     * It creates an integer value of digital "finger print" that enables to
     * identify a queue consisting of unique elements.
     *
     * @return an integer value of digital "finger print".
     */

    public int hashCode();


    /**
     * Creates new Queue with the same elements as the current queue.
     *
     * @return new Queue that contains the same elements as the current queue.
     */
    public Queue<Item> clone();


    /**
     * Creates a string representation of the queue that represents natural
     * order of the queue.
     *
     * @return string representation of the queue.
     */
    public String toString();
}
