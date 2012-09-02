import java.util.Iterator;

// -------------------------------------------------------------------------
/**
 * This an abstract class that implements methods for Queue interface. It lists
 * abstract methods. It is a generic class that will have a user to define a
 * type of data stored in queue.
 *
 * @param <Item>
 *            - type of data stored in queue.
 * @author Adam Lech (adaml8)
 * @version 2012.06.28
 */
public abstract class AbstractQueue<Item>
{

    // ----------------------------------------------------------
    /**
     * Gets the size (number of elements) of the queue.
     *
     * @return - size of the queue.
     */
    public abstract int size();


    /**
     * Method that adds element to the queue. First it creates new link that
     * will store data passed in parameter.
     *
     * @param newItem
     *            data entered by the user.
     */
    public abstract void enqueue(Item newItem);


    // ----------------------------------------------------------
    /**
     * This method removes the first element added to the queue.Since it's not a
     * circular linked queue, it has to handle special case when there is only
     * one element in the queue.
     *
     * @return returnValue removed data element.
     */
    public abstract Item dequeue();


    // ----------------------------------------------------------
    /**
     * Returns value of the element that was first added to the queue.
     *
     * @return value stored in the head of the queue.
     */
    public abstract Item peek();


    // ----------------------------------------------------------
    /**
     * Clears the current queue by setting links to null.
     */
    public abstract void clear();


    // ----------------------------------------------------------
    /**
     * Method that creates Iterator object that enables to access all elements
     * in the queue.
     *
     * @return new Iterator object.
     */
    public abstract Iterator<Item> iterator();


    /**
     * Compares queue to check if all the elements in the queue are the same.
     *
     * @param queue
     *            - a queue which value is to be compared.
     * @return true if all the elements in the queue are the same, false
     *         otherwise
     */

    @SuppressWarnings("unchecked")
    public boolean equals(Object queue)
    {
        // Checks either the object of null value was passed as parameter or of
        // different than Queue type. Returns false in that case.

        if (queue == null || !(queue instanceof Queue))
        {
            return false;
        }

        // Creates iterator objects in order to access all elements in the queue
        // for comparison.
        Iterator<Item> thisIt = this.iterator();
        Iterator<Item> queueIt = ((Queue<Item>)queue).iterator();
        if (this.size() != ((Queue<Item>)queue).size())
        {
            return false;
        }
        else
        {
            while (thisIt.hasNext())
            {
                Item thisItItem = thisIt.next();
                Item queueItItem = queueIt.next();
                if (thisItItem == null && queueItItem == null)
                {
                    continue;
                }
                else if ((thisItItem != null && queueItItem == null)
                    || (thisItItem == null && queueItItem != null))
                {
                    return false;
                }
                else if (!thisItItem.equals(queueItItem))
                {
                    return false;
                }

            }
        }
        return true;

    }


    /**
     * It creates an integer value of digital "finger print" that enables to
     * identify a queue consisting of unique elements.
     *
     * @return an integer value of digital "finger print".
     */
    public int hashCode()
    {

// if (this==null) {
// return 0;
// }

        int hashCode = 0;

        Iterator<Item> hashIterator = this.iterator();
        // Item someHash = hashIterator.next();
        while (hashIterator.hasNext())

        {
            Item someHash = hashIterator.next();
            if (someHash == null)
            {
                hashCode = hashCode + 0;
            }
            else
            {

                hashCode += someHash.hashCode();
            }

        }
        return hashCode;
    }


    /**
     * Creates new Queue with the same elements as the current queue.
     *
     * @return new Queue that contains the same elements as the current queue.
     */
    public abstract Queue<Item> clone();


    /**
     * Creates a string representation of the queue that represents natural
     * order of the queue.
     *
     * @return string representation of the queue.
     */
    public String toString()
    {
        StringBuilder stringQueue = new StringBuilder();
        Iterator<Item> stringIterator = this.iterator();
        stringQueue.append("<");

        while (stringIterator.hasNext())
        {
            if (stringQueue.length() == 1)
            {
                stringQueue.append(stringIterator.next());
            }
            else
            {
                stringQueue.append("," + stringIterator.next());
            }
        }
        stringQueue.append(">");
        return stringQueue.toString();

    }
}
