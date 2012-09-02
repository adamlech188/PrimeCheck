import java.util.Iterator;
import java.util.NoSuchElementException;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 *
 * @author Adam Lech
 * @version 2012.06.28
 */
public abstract class AbstractQueueTest
    extends TestCase

{
    /**
     * Test queue on which tests are run.
     */
    protected Queue<String> testQueue;


    // ----------------------------------------------------------
    /**
     * This is abstract method that will be implemented by the extended class.
     *
     * @return Queue<String>
     */
    public abstract Queue<String> theQueue();


    /**
     * Sets up the instance of the queue on which tests will be run.
     */
    @Before
    public void setUp()

    {
        testQueue = theQueue();
    }


    // ----------------------------------------------------------
    /**
     * Tests enqueue methods. It inserts value into queue and checks for size.
     */
    @Test
    public void testEnqueue()
    {
        testQueue.enqueue("A");
        assertEquals(1, testQueue.size());
        assertEquals("A", testQueue.peek());
        testQueue.enqueue("B");
        testQueue.enqueue("C");
        assertEquals(3, testQueue.size());
        assertEquals("A", testQueue.peek());
    }


    // ----------------------------------------------------------
    /**
     * Checks if dequeue() remove given values and return those that are
     * removed.
     */
    public void testDequeue()
    {
        testQueue.enqueue("A");
        assertEquals("A", testQueue.dequeue());
        testQueue.enqueue("B");
        testQueue.enqueue("C");
        assertEquals("B", testQueue.peek());
        assertEquals("B", testQueue.dequeue());
        assertEquals("C", testQueue.dequeue());

        testQueue.enqueue("A");
        assertEquals("A", testQueue.peek());
        assertEquals("A", testQueue.dequeue());

        testQueue.enqueue("A");
        testQueue.enqueue("B");
        testQueue.enqueue("C");
        assertEquals("A", testQueue.dequeue());
        assertEquals("B", testQueue.dequeue());
        assertEquals("C", testQueue.dequeue());
        assertEquals(0, testQueue.size());

        testQueue.enqueue("A");
        testQueue.enqueue("B");
        testQueue.enqueue("C");
        testQueue.enqueue("D");
        testQueue.enqueue("E");
        testQueue.enqueue("F");
        testQueue.enqueue("G");
        testQueue.enqueue("H");
        testQueue.enqueue("I");
        assertEquals("A", testQueue.dequeue());
        assertEquals("B", testQueue.dequeue());
        assertEquals("C", testQueue.dequeue());
        assertEquals("D", testQueue.dequeue());
        assertEquals("E", testQueue.dequeue());
        assertEquals("F", testQueue.dequeue());
        assertEquals("G", testQueue.dequeue());
        assertEquals("H", testQueue.dequeue());
        assertEquals("I", testQueue.dequeue());
        assertEquals(0, testQueue.size());
        // --------------------------------------------
        // Testing exception.
        Exception occurred = null;
        try
        {
            testQueue.dequeue();
        }
        catch (Exception e)
        {
            occurred = e;
        }
        assertNotNull(occurred);
        assertTrue(occurred instanceof IllegalStateException);
        assertEquals("Queue cannot be empty.", occurred.getMessage());

    }


    // ----------------------------------------------------------
    /**
     * Tests peek() method by checking whether after adding element to the
     * queue, peek() will return proper value.
     */
    public void testPeek()
    {

        Exception occurred = null;
        try
        {
            testQueue.peek();
        }
        catch (Exception e)
        {
            occurred = e;
        }
        assertNotNull(occurred);
        assertTrue(occurred instanceof IllegalStateException);
        assertEquals("Queue cannot be empty.", occurred.getMessage());
        testQueue.enqueue("A");
        testQueue.enqueue("B");
        testQueue.enqueue("C");
        testQueue.enqueue("D");
        assertEquals("A", testQueue.peek());

    }


    // ----------------------------------------------------------
    /**
     * Checks if clear() method empties queue.
     */
    public void testClear()
    {
        testQueue.enqueue("A");
        testQueue.enqueue("B");
        testQueue.enqueue("C");
        testQueue.enqueue("D");
        testQueue.enqueue("E");
        testQueue.enqueue("F");
        testQueue.enqueue("G");
        testQueue.enqueue("H");
        testQueue.enqueue("I");

        testQueue.clear();
        assertEquals(0, testQueue.size());

    }


    // ----------------------------------------------------------
    /**
     * Runs extensive sub unit test cases , to see if Iterator() method
     * instantiates Iterator() class and tests its methods.
     */
    public void testIteratorNext()
    {
        testQueue.enqueue("A");
        testQueue.enqueue("B");
        testQueue.enqueue("C");
        testQueue.enqueue("D");
        Iterator<String> linkIt = testQueue.iterator();
        assertEquals("A", linkIt.next());
        assertEquals("B", linkIt.next());
        assertEquals("C", linkIt.next());
        assertTrue(linkIt.hasNext());
        assertEquals("D", linkIt.next());
        assertFalse(linkIt.hasNext());

        // -----------------------------------------------------
        // Testing for exceptions when queue is empty.
        Exception occurred = null;
        try
        {
            linkIt.next();
        }
        catch (Exception e)
        {
            occurred = e;
        }
        assertNotNull(occurred);
        assertTrue(occurred instanceof NoSuchElementException);
        assertEquals("Queue cannot be empty.", occurred.getMessage());

        testQueue.clear();
    }


    // ----------------------------------------------------------
    /**
     * Testing remove() in iterator object.
     */
    public void testIteratorRemove()
    {
        testQueue.enqueue("A");
        testQueue.enqueue("B");
        testQueue.enqueue("C");
        testQueue.enqueue("D");
        Iterator<String> otherIt = testQueue.iterator();
        Exception occurred = null;
        try
        {
            otherIt.remove();
        }
        catch (Exception e)
        {
            occurred = e;
        }
        assertNotNull(occurred);
        assertTrue(occurred instanceof IllegalStateException);
        assertEquals("Method was called before next().", occurred.getMessage());

        assertEquals("A", otherIt.next());
        assertTrue(otherIt.hasNext());
        otherIt.remove();
        assertEquals("B", testQueue.peek());
        assertEquals("B", otherIt.next());
        otherIt.remove();
        assertEquals("C", testQueue.peek());

        assertEquals(2, testQueue.size());

    }


    // ----------------------------------------------------------
    /**
     * Tests if hashCode() returns unique digital signature , consisting of
     * certain integer.
     */
    public void testHashCode()
    {

        testQueue.enqueue("A");
        testQueue.enqueue("B");
        Queue<String> someArrayQueue = new ArrayQueue<String>(4);
        someArrayQueue.enqueue("A");
        someArrayQueue.enqueue("C");
        assertNotSame(someArrayQueue.hashCode(), testQueue.hashCode());

    }


    /**
     * Tests clone() method , by declaring new variable of type Queue, and then
     * by instantiating by using clone() method.
     */
    public void testClone()
    {
        Queue<String> clonedtestQueue;
        testQueue.enqueue("Adam");
        testQueue.enqueue("Joker");
        testQueue.enqueue("Me");
        clonedtestQueue = testQueue.clone();
        assertEquals("Adam", clonedtestQueue.peek());
        assertEquals(3, clonedtestQueue.size());
        assertEquals(3, testQueue.size());
        assertEquals(3, clonedtestQueue.size());
    }


    // ----------------------------------------------------------
    /**
     * Tests equals method, by comparing different queues with the same or
     * different elements in it.
     */
    public void testEquals()
    {
        ArrayQueue<String> newQueue = new ArrayQueue<String>();
        newQueue.enqueue("Adam");
        newQueue.enqueue("Joker");
        newQueue.enqueue("Me");
        testQueue.enqueue("Adam");
        testQueue.enqueue("Joker");
        testQueue.enqueue("Me");
        assertEquals(false, testQueue.equals("Adam"));
        assertEquals(true, testQueue.equals(newQueue));
        newQueue.enqueue("Bane");
        assertEquals(false, testQueue.equals(newQueue));
        newQueue.clear();
        assertEquals(false, testQueue.equals(newQueue));
        // ------------------------------------------------------------------
        // Creating queue with different than default capacity.
        ArrayQueue<String> someQueue = new ArrayQueue<String>(4);
        testQueue.clear();
        someQueue.enqueue("A");
        someQueue.enqueue("B");
        someQueue.enqueue("C");
        someQueue.enqueue("D");
        someQueue.dequeue();
        someQueue.dequeue();
        someQueue.enqueue("F");
        testQueue.enqueue("C");
        testQueue.enqueue("D");
        testQueue.enqueue("F");
        assertEquals(true, testQueue.equals(someQueue));
        someQueue.enqueue("G");
        assertEquals(false, testQueue.equals(someQueue));
        testQueue.enqueue("G");
        assertEquals(true, testQueue.equals(someQueue));
        someQueue.clear();
        testQueue.clear();
        someQueue.enqueue("A");
        someQueue.enqueue("B");
        someQueue.enqueue("C");
        testQueue.enqueue("C");
        testQueue.enqueue("D");
        testQueue.enqueue("F");
        someQueue.dequeue();
        testQueue.dequeue();
        assertEquals(false, testQueue.equals(someQueue));




    }


    // ----------------------------------------------------------
    /**
     * Tests to see, if toString() method returns correct representation of the
     * queue in natural order.
     */
    public void testToString()
    {
        testQueue.enqueue("Adam");
        testQueue.enqueue("Joker");
        testQueue.enqueue("Me");
        testQueue.enqueue("Bane");
        assertEquals("<Adam,Joker,Me,Bane>", testQueue.toString());
        Queue<String> cloned = testQueue.clone();
        assertEquals("<Adam,Joker,Me,Bane>", cloned.toString());

    }
}
