package cs2114.mazesolver;

import java.util.EmptyStackException;
import junit.framework.TestCase;

// -------------------------------------------------------------------------
/**
 * This is a test class that will run unit test cases in order to test methods
 * in LinkedStack class. It sets instances of different generic Linked stacks in
 * order to run test cases for each method.
 *
 * @author Adam Lech (adaml8)
 * @version 2012.06.17
 */
public class LinkedStackTest
    extends TestCase
{
    // Since LinkedStack class provides abstract construct without specifying
    // what types of data can be inserted into stack, instances with different
    // data type are created.

    private LinkedStack<Object> myStack;
    private LinkedStack<String> stringStack;


    /**
     * Creates different instances of LinkedStack variables of different types.
     */
    protected void setUp()
        throws Exception
    {
        super.setUp();

        myStack = new LinkedStack<Object>();
        stringStack = new LinkedStack<String>();

    }


    // ----------------------------------------------------------
    /**
     * Tests the constructor, by checking whether the instances of generic
     * LinkedStacks are not null.
     */
    public void testConstructor()
    {
        assertNotNull(myStack);
        assertNotNull(stringStack);

    }


    // ----------------------------------------------------------
    /**
     * Runs the test cases to check whether isEmpty() method returns proper
     * values. It tests for empty LinkedStack first and then for LinkedStack
     * with element in it.
     */
    public void testIsEmpty()
    {
        assertTrue(myStack.isEmpty());
        myStack.push("Adam");
        assertFalse(myStack.isEmpty());
    }


    // ----------------------------------------------------------
    /**
     * Runs various test cases to test the pop() method. First it push some
     * elements into the given instance of LinkedStack and then it checks to see
     * whether pop() method removed them.
     */
    public void testPop()
    {

        // This test case checks if the pop methods throws correct type
        // of exception.
        Exception occurred = null;
        try
        {
            myStack.pop();
        }
        catch (Exception e)
        {
            occurred = e;
        }
        assertNotNull(occurred);
        assertTrue(occurred instanceof EmptyStackException);
        // Pushes elements into the stack, than checks whether pop() removes
        // them.
        myStack.push(2);
        myStack.push(3);
        myStack.push(5);
        assertEquals(3, myStack.size());
        assertEquals(5, myStack.top());

        myStack.pop();
        assertEquals(2, myStack.size());
        assertEquals(3, myStack.top());

        myStack.pop();
        assertEquals(2, myStack.top());
        assertEquals(1, myStack.size());

        myStack.push(3);
        assertEquals(2, myStack.size());

        myStack.pop();
        myStack.pop();
        assertEquals(0, myStack.size());
    }


    // ----------------------------------------------------------
    /**
     * Runs test cases to check whether top() method returns proper values,
     * after elements were pushed into stack.
     */
    public void testTop()
    {
        // This test case checks whether top() method throws correct type of
        // exception in case the stack is empty.
        Exception someException = null;
        try
        {
            myStack.top();
        }
        catch (Exception e)
        {
            someException = e;
        }
        assertNotNull(someException);
        assertTrue(someException instanceof EmptyStackException);

        // The elements are pushed into stack.
        myStack.push(0);
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        myStack.push(5);
        myStack.push(6);
        myStack.push(7);
        myStack.push(8);
        myStack.push(9);

        // First it checks whether top() methods returns last pushed element.
        // Then it pops elements, to see whether top() would return proper
        // values, as they were pushed into stack.
        assertEquals(9, myStack.top());
        myStack.pop();
        assertEquals(8, myStack.top());
        myStack.pop();
        assertEquals(7, myStack.top());
        myStack.pop();
        assertEquals(6, myStack.top());
        myStack.push(100);
        assertEquals(100, myStack.top());
        myStack.pop();
        assertEquals(6, myStack.top());
        myStack.pop();
        assertEquals(5, myStack.top());
        myStack.pop();
        assertEquals(4, myStack.top());
        myStack.pop();
        assertEquals(3, myStack.top());
        myStack.pop();
        assertEquals(2, myStack.top());
        myStack.pop();
        assertEquals(1, myStack.top());
        myStack.pop();
        assertEquals(0, myStack.top());

    }


    // ----------------------------------------------------------
    /**
     * Run test cases to check push() method. It checks whether the elements
     * were added to the given instances of LinkedStack class after push()
     * method was employed.
     */
    public void testPush()
    {
        stringStack.push("Adam");
        assertEquals("Adam", stringStack.top());

        myStack.push(2);
        assertFalse(myStack.isEmpty());

        myStack.push(3);
        assertEquals(2, myStack.size());
        assertEquals(3, myStack.top());

        myStack.push(5);
        assertEquals(5, myStack.top());

        myStack.push(100);
        assertEquals(100, myStack.top());

    }
}
