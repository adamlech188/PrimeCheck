//-------------------------------------------------------------------------
/**
 * This class is an extension of Link that represents a doubly-linked node in a
 * chain. In addition to being able to travel forward through the chain, users
 * of this class can travel backward as well. The {@link #join(Link)} and
 * {@link #split()} methods are overridden to automatically link up the
 * previous link references as well as the next ones.
 *
 * @param <T> the type of data that the Link will contain
 *
 * @author  Tony Allevato
 * @version 2012.06.13
 */
public class Link<T>
{
    //~ Instance/static variables .............................................

    // The data element associated with the link.
    private T data;

    // The next link in the chain.
    private Link<T> next;

    // The link that precedes this one.
    private Link<T> previous;


    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new Link with the specified data.
     *
     * @param data the data associated with the link
     */
    public Link(T data)
    {
        this.data = data;
    }


    // ----------------------------------------------------------
    /**
     * Connects the specified link as the follower of the receiver.
     *
     * @precondition The receiver must not already have a link following it.
     *
     * @param follower the Link that should follow the receiver
     * @return the receiver
     */
    public Link<T> join(Link<T> follower)
    {
        if (this.next != null)
        {
            throw new IllegalStateException("Cannot join because the "
                    + "receiver already has a follower.");
        }
        else if (follower != null && follower.previous != null)
        {
            throw new IllegalStateException("Cannot join because the "
                    + "follower already has a predecessor.");
        }

        this.next = follower;

        if (follower != null)
        {
            follower.previous = this;
        }

        return this;
    }


    // ----------------------------------------------------------
    public Link<T> split()
    {
        // Sever the backward link from the follower to the receiver.
        Link<T> follower = next();
        if (follower != null)
        {
            follower.previous = null;
        }

        this.next = null;

        return follower;
    }


    // ----------------------------------------------------------
    public Link<T> next()
    {
        return next;
    }


    // ----------------------------------------------------------
    /**
     * Gets the link that precedes the receiver.
     *
     * @return the Link that precedes the receiver, or null if the receiver
     *     has no predecessor
     */
    public Link<T> previous()
    {
        return previous;
    }


    // ----------------------------------------------------------
    /**
     * Gets the data element associated with the receiver.
     *
     * @return the data element associated with the receiver
     */
    public T data()
    {
        return data;
    }


    // ----------------------------------------------------------
    /**
     * Sets the data element associated with the receiver.
     *
     * @param newData the new data element to be associated with the receiver
     */
    public void setData(T newData)
    {
        this.data = newData;
    }
}