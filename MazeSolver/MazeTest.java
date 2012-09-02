package cs2114.mazesolver;

import junit.framework.TestCase;

// -------------------------------------------------------------------------
/**
 * This class runs unit tests for all the methods employed in Maze class. It
 * instantiates the Maze class objects and and implements test methods for each
 * method in the class.
 *
 * @author Adam Lech (PID adaml8)
 * @version 2012.06.07
 */
public class MazeTest
    extends TestCase
{ // ~Instances/Variables
    private Maze testMaze;


    // ----------------------------------------------------------
    /**
     * Sets up the instances of the class in order to run test cases.
     */

    protected void setUp()
        throws Exception
    {
        super.setUp();
        testMaze = new Maze(4);

    }


    // ----------------------------------------------------------
    /**
     * Helper method that will serve as a building block for test cases. It
     * takes Maze object as one of the argument and array of strings which
     * represents the expected state of the maze.Then it compares that to the
     * given t state of the Maze.
     *
     * @param theMaze
     *            - maze object expressed as a two dimensional array of
     *            enumerative data types
     * @param expected
     *            - array of string that represent expected state of the maze
     */
    public void assertMaze(Maze theMaze, String... expected)
    {
        Maze expectedMaze = new Maze(expected.length);
        expectedMaze.loadMazeState(expected);
        assertEquals(expectedMaze, theMaze);
    }


    // ----------------------------------------------------------
    /**
     * Tests the constructor method.It tests the instance of a maze created in
     * setUp() method. First it checks whether all the cells in the maze were
     * set as to be unexplored.After that it checks to see whether the maze of
     * the proper size was created.Then it loads the maze to the certain
     * preconditioned state and checks whether it loaded correctly.
     */
    public void testConstructor()

    {

        //assertEquals(MazeCell.CURRENT_PATH, testMaze.getCell(0, 0));
        // Checks to see whether all MazeCell in testMaze object were set as
        // unexplored.
        assertMaze(testMaze, "    ", "    ", "    ", "    ");

        assertEquals(4, testMaze.size());

        testMaze.loadMazeState(".   ", ".   ", ".   ", "....");

        assertMaze(testMaze, ".   ", ".   ", ".   ", "....");

    }


    // ----------------------------------------------------------
    /**
     * Tests cleareMaze() method by loading the maze to the certain state with
     * the drawn wall. First it checks whether the wall was drawn properly.
     * After that clearMaze() method is employed and the maze is tested to see
     * whether all the cells has been set as unexplored.
     */
    public void testClearMaze()
    {
        testMaze.loadMazeState("  * ", "  * ", "  * ", "  **");

        assertMaze(testMaze, "  * ", "  * ", "  * ", "  **");
        testMaze.clearMaze();
        assertMaze(testMaze, "    ", "    ", "    ", "    ");

    }


    // ----------------------------------------------------------
    /**
     * Tests getCell() method. It loads testMaze instance to certain state. Then
     * getCell() method is employed to see whether it will return proper values
     * as predicated by the loaded condition. It tests the cases where the out
     * of boundary cell coordinates were entered.
     */
    public void testGetCell()
    {
        testMaze.loadMazeState("  * ", "  * ", "  * ", "  **");

        assertEquals(MazeCell.WALL, testMaze.getCell(2, 0));
        assertEquals(MazeCell.UNEXPLORED, testMaze.getCell(0, 0));
        assertEquals(MazeCell.INVALID_CELL, testMaze.getCell(-1, 0));
        assertEquals(MazeCell.INVALID_CELL, testMaze.getCell(4, 3));

    }


    // ----------------------------------------------------------
    /**
     * Tests resetPath() method, which sets all the cells as unexplored except
     * those marked as walls. It loads the maze to condition where wall is drawn
     * with some path attempted.After resetPath() method is applied , it checks
     * whether all the cells where set as unexplored except those where wall was
     * drawn.
     */
    public void testResetPath()
    {
        testMaze.loadMazeState("x * ", "x * ", "xx* ", "  **");
        assertMaze(testMaze, "x * ", "x * ", "xx* ", "  **");
        testMaze.resetPaths();
        assertMaze(testMaze, "  * ", "  * ", "  * ", "  **");

    }


    // ----------------------------------------------------------
    /**
     * Tests setCell() method by first loading the maze to certain state. Then
     * it employs this method by first setting Cell to the certain condition and
     * then checks whether the state of maze was changed as expected. Then the
     * cases are tested, where the our of boundary cell coordinates were
     * entered.
     */
    public void testSetCell()
    {
        testMaze.loadMazeState("x * ", "x * ", "xx* ", "  * ");
        testMaze.setCell(0, 0, MazeCell.UNEXPLORED);
        assertMaze(testMaze, "  * ", "x * ", "xx* ", "  * ");

        testMaze.setCell(-1, -2, MazeCell.WALL);
        assertEquals(MazeCell.UNEXPLORED, testMaze.getCell(0, 0));

        testMaze.setCell(4, 2, MazeCell.WALL);
        assertEquals(MazeCell.UNEXPLORED, testMaze.getCell(3, 3));

        testMaze.setCell(0, 0, MazeCell.WALL);
        assertNotSame(MazeCell.WALL, testMaze.getCell(0, 0));

    }


    // ----------------------------------------------------------
    /**
     * Tests testSize() method. It employs this method to check whether the
     * proper size was returned.
     */
    public void testSize()
    {
        assertEquals(4, testMaze.size());
    }
}
