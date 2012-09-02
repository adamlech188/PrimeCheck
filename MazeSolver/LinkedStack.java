package cs2114.mazesolver;

import cs2114.link.*;
import java.util.EmptyStackException;

// -------------------------------------------------------------------------
/**
 * This class is developed for purposes of implementing maze solving algorithm.
 * It uses instances of generic Link<T> class, to build a stack. It refers each
 * element in the stack to the previous one. The last joined link is the "top"
 * of the stack. It implements SimpleStack interface.
 *
 * @param <T>
 *            - generic type of parameter,it is specified every time instance of
 *            LinkedStack is created
 * @author Adam Lech (adaml8)
 * @version 2012.06.17
 */
public class LinkedStack<T>
    implements SimpleStack<T>
{

    // ~Instance field.

    private Link<T> front;
    private int     size;


    // ----------------------------------------------------------
    /**
     * This is a constructor. It instantiates the first link, which is set to
     * null.
     */
    public LinkedStack()
    {
        front = null;

    }


    /**
     * Checks whether the stack is empty.
     *
     * @return true if stack is empty, false if stack has some elements in it
     */
    public boolean isEmpty()
    {
        if (front == null)
        {
            return true;
        }
        else
        {
            return false;
        }

    }


    /**
     * Pops an item of the top of stack.
     */
    public void pop()

    {
        if (front == null)
        {
            throw new EmptyStackException();
        }

        else
        {
            front = front.split();

            size--;

        }

    }


    /**
     * Pushes the specified item on top of the stack.
     *
     * @param item
     *            that is to be pushed on top of the stack.
     */

    public void push(T item)

    {

        Link<T> newLink = new Link<T>(item);
        front = newLink.join(front);

        size++;

    }


    /**
     * Gets the number of items in the stack.
     *
     * @return size, which is the number of item in the stack
     */
    public int size()
    {

        return size;
    }


    /**
     * Gets the item on top of the stack.
     *
     * @return - the item on top of the stack
     */
    public T top()

    {
        if (front == null)
        {
            throw new EmptyStackException();
        }

        return front.data();

    }

}
