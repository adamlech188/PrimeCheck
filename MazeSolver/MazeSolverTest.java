package cs2114.mazesolver;

import junit.framework.TestCase;

// -------------------------------------------------------------------------
/**
 * Runs test unit cases in order to test MazeSolver's solve() method.
 *
 * @author Adam Lech (adaml8)
 * @version 2012.06.17
 */
public class MazeSolverTest
    extends TestCase
{

    // Instance/variables field.
    private Maze       testMaze;

    private MazeSolver testMazeSolver;


    /**
     * Creates instances of Maze and MazeSolver in order to run test cases to
     * check solve() method.
     */
    protected void setUp()
        throws Exception
    {

        super.setUp();
        testMaze = new Maze(4);
        testMazeSolver = new MazeSolver(testMaze);

    }


    // ----------------------------------------------------------
    /**
     * Tests constructor, by checking whether instance of MazeSolver is not
     * null.
     */
    public void testConstructor()
    {
        assertNotNull(testMazeSolver);

    }


    // ----------------------------------------------------------
    /**
     * Runs test cases to check whether solve() method returns correct string
     * representation of maze solution. It loads instance of the maze to certain
     * condition and checks whether the correct path solution was obtained. In
     * case there is no solution , it checks whether solve() method returns
     * null.
     */
    public void testSolve()
    {

        testMaze.loadMazeState(" * *", "    ", "****", "    ");
        testMazeSolver = new MazeSolver(testMaze);
        assertEquals(null, testMazeSolver.solve());

        testMaze.loadMazeState(" * *", "    ", "** *", "    ");
        assertEquals(
            "(0, 0) (0, 1) (1, 1) (2, 1) (2, 2) (2, 3) (3, 3)",
            testMazeSolver.solve());

        testMaze.loadMazeState("  **", "  **", " ***", "    ");
        assertEquals(
            "(0, 0) (1, 0) (1, 1) (0, 1) (0, 2) (0, 3) (1, 3) (2, 3) (3, 3)",
            testMazeSolver.solve());

        testMaze.loadMazeState("    ", " ***", " ***", "    ");
        assertEquals(
            "(0, 0) (0, 1) (0, 2) (0, 3) (1, 3) (2, 3) (3, 3)",
            testMazeSolver.solve());

    }

}
