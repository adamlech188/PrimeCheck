import java.util.NoSuchElementException;
import java.util.Iterator;

// -------------------------------------------------------------------------
/**
 * This class uses link structures to implement Queue<Item> interface. It
 * enables to add data elements to the queue and stores them in the link, and
 * allows user access the first element in the queue. It is based on single,
 * non-circular link data structure.
 *
 * @param <Item>
 *            generic type, the type of data that are to be stored in queue.
 * @author Adam Lech (adaml8)
 * @version 2012.06.28;
 */
public class LinkedQueue<Item>
    extends AbstractQueue<Item>
    implements Queue<Item>
{
    // ~Instance field. Declares variables types for links that will store data
    // added to the queue.
    private Link<Item> back;
    private Link<Item> head;
    private int        size;


    // ----------------------------------------------------------
    /**
     * This is constructors method that instantiates types declared in instance
     * field that will be used to store data added to the queue.
     */
    public LinkedQueue()
    {
        size = 0;
        head = null;
        back = null;

    }


    /**
     * Gets the size (number of elements) of the queue.
     *
     * @return - size of the queue.
     */
    @Override
    public int size()
    {
        return size;
    }


    /**
     * Method that adds element to the queue. First it creates new link that
     * will store data passed in parameter.
     *
     * @param newItem
     *            data entered by the user.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void enqueue(Object newItem)
    {

        Link<Item> newLink = new Link<Item>((Item)newItem);
        if (head == null && back == null)
        {

            back = newLink;
            head = back;
            ++size;

        }

        else
        {

            back = newLink.join(back);
            ++size;

        }

    }


    /**
     * This method removes the first element added to the queue.Since it's not a
     * circular linked queue, it has to handle special case when there is only
     * one element in the queue.
     *
     * @return returnValue removed data element.
     */
    @Override
    public Item dequeue()

    {

        if (size == 0)
        {
            throw new IllegalStateException("Queue cannot be empty.");
        }
        // ~Creates a variable to store a current data, that is removed by
        // dequeue()
        Item returnValue = head.data();
        // ~Special case, where there is only one element in the queue.
        if (size == 1)
        {
            back = null;
            head = null;

            --size;
            return returnValue;

        }
        else
        { // Changes value of head of the current queue.
            head = head.previous();
            head.split();
            --size;
            return returnValue;
        }

    }


    /**
     * Returns value of the element that was first added to the queue.
     *
     * @return value stored in the head of the queue.
     */
    @Override
    public Item peek()
    {
        if (head == null)
        {
            throw new IllegalStateException("Queue cannot be empty.");
        }
        return head.data();
    }


    /**
     * Clears the current queue by setting links to null.
     */
    @Override
    public void clear()
    {
        head = null;
        back = null;
        size = 0;

    }


    /**
     * It instantiates iterator object that enables to move through all the
     * elements in the Queue.
     *
     * @return new QueueIterator() - an iterator object.
     */

    @Override
    public QueueIterator iterator()
    {

        return new QueueIterator();
    }


    /**
     * Creates new Queue with the same elements as the current queue.
     *
     * @return new Queue that contains the same elements as the current queue.
     */
    public LinkedQueue<Item> clone()
    {
        LinkedQueue<Item> newQueue = new LinkedQueue<Item>();
        Iterator<Item> queueIt = this.iterator();
        while (queueIt.hasNext())
        {
            newQueue.enqueue(queueIt.next());
        }
        return newQueue;

    }


    /**
     * // ----------------------------------------------------------------------
     * --- /** Private nested class that creates iterator, in order to enable
     * access to all elements in the queue. It implements Iterator interface.
     *
     * @author Adam Lech (adaml8)
     * @version 2012.06.28
     */

    private class QueueIterator
        implements Iterator<Item>
    {
        private Link<Item> current;
        private Link<Item> toRemoveLink;
        private boolean    markNext;
        private int        index;


        /**
         * Constructor that sets the value of current link to head.
         */

        public QueueIterator()
        {
            current = head;
            index = size;
        }


        /**
         * Checks if iterator has any more elements to go over.
         *
         * @return - true , if there are any more elements to access, false
         *         otherwise
         */
        @Override
        public boolean hasNext()
        {
            if (index > 0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }


        /**
         * Accesses next element in the queue.
         */

        @Override
        public Item next()
        {

            if (!hasNext())
            {

                throw new NoSuchElementException("Queue cannot be empty.");

            }
            else
            {
                Item returnValue = current.data();
                toRemoveLink = current;
                current = current.previous();
                markNext = true;
                --index;
                return returnValue;
            }

        }


        /**
         * Remove element called by next().
         */
        @Override
        public void remove()
        {
            if (!markNext)
            {
                throw new IllegalStateException(
                    "Method was called before next().");
            }
            if (current == null && toRemoveLink.next() != null && markNext
                && head != back)
            {
                back.split();
                --size;

            }

            else if (toRemoveLink.next() == null && markNext && head != back)
            {

                head = current;
                current.split();
                markNext = false;
                --size;
            }
            else if (toRemoveLink.next() == null && head == back && markNext)
            {
                head = null;
                back = null;
                current = null;
                markNext = false;
                --size;

            }

            else if (toRemoveLink.next() != null && markNext && head != back)
            {

                current.split();
                Link<Item> afterRemove = toRemoveLink.split();
                current.join(afterRemove);
                markNext = false;
                --size;

            }

            else
            {
                throw new IllegalStateException(
                    "Method was called before next().");
            }
        }
    }
}
