// -------------------------------------------------------------------------
/**
 * This class runs test cases in order to tests all methods in ArrayQueue.
 *
 * @author Adam Lech (adaml8)
 * @version 2012.06.28
 */
public class LinkedQueueTest
    extends AbstractQueueTest

{

    /**
     * The method that returns queue on which tests will be run.
     *
     * @return LinkedQueue<String> - on which tests are run
     */
    @Override
    public LinkedQueue<String> theQueue()
    {
        LinkedQueue<String> testLinkedQueue = new LinkedQueue<String>();
        return testLinkedQueue;
    }

}
