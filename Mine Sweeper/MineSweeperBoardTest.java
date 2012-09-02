package cs2114.minesweeper;

import junit.framework.TestCase;

// -------------------------------------------------------------------------
/**
 * This class tests methods in MineSweeperBoard class. It will run test cases
 * for each method implemented in MineSweeperBoard class.
 *
 * @author Adam Lech (PID: adaml8)
 * @version 05.29.2012
 */

public class MineSweeperBoardTest
    extends TestCase
{
    // ~Instance field
    private MineSweeperBoard someMineSweeperBoard;

    private String[]         someBoard = { "OOO+", "O++O", "O+O+", "+O+O",
        "OOOO"                        };         // ~ String array that will
// be used to create given state of the board.
    private String[]         gameIsWon = { "122M", "1MM2", "23M2", "2M3M",
        "M3M2"                        };         // ~ String that will set the
// state of the board as if the game is won.


    // ----------------------------------------------------------
    /**
     * Sets up the instances of the class in order to run test cases.
     */
    protected void setUp()
        throws Exception
    {

        someMineSweeperBoard = new MineSweeperBoard(4, 5, 7);

    }


    /**
     * A helper method that will serve as building block for certain test cases,
     * where we want to compare the expected state of the board with the actual
     * outcome of the test.
     *
     * @param theBoard
     *            - MineSweeperBoard instance, the given board expressed as a
     *            two-dimensional array.
     * @param expectedString
     *            - one dimensional array of String, which represents the
     *            expected state of the board.
     */
    public void assertBoard(MineSweeperBoard theBoard, String... expectedString)
    {

        MineSweeperBoard expectedBoard =
            new MineSweeperBoard(
                expectedString[0].length(),
                expectedString.length,
                0);
        expectedBoard.loadBoardState(expectedString);
        assertEquals(theBoard, expectedBoard);

    }


    // ----------------------------------------------------------
    /**
     * Tests the constructor by checking if the instance of MineSweeperBoard was
     * created.MineSweeperBoard is represented by two dimensional array.It loads
     * the instance of the board , to certain preconditioned state and it checks
     * whether the MineSweeperCell enumerative type values loaded into the board
     * corresponds to the state of the board we intended that to load with. It
     * creates another instance of MineSweeperBoard with the same width , height
     * and number of mines.It compares this another instance to the first
     * instance , to check if there are different. This comparison is performed
     * in order to see if the mines are distributed randomly. This constructor
     * test also checks if the two dimensional array was created with right
     * number of columns and rows.
     */
    public void testConstructor()
    {
        someMineSweeperBoard.loadBoardState(someBoard);

        assertBoard(someMineSweeperBoard, someBoard);
        MineSweeperBoard someMineSweeperBoard2 = new MineSweeperBoard(4, 5, 7);
        assertNotSame(
            someMineSweeperBoard.toString(),
            someMineSweeperBoard2.toString());
        assertEquals(5, someMineSweeperBoard.height());
        assertEquals(4, someMineSweeperBoard.width());
    }


    // ----------------------------------------------------------

    // ----------------------------------------------------------
    /**
     * Tests whether FlagCell() method places and displaces flags. It loads
     * board to the certain state. Then the certain cell is flagged. Test is run
     * whether the cell was really placed or displaced.
     */
    public void testFlagCell()
    {
        someMineSweeperBoard.loadBoardState(someBoard);
        someMineSweeperBoard.flagCell(3, 4);
        assertEquals(MineSweeperCell.FLAG, someMineSweeperBoard.getCell(3, 4));
        someMineSweeperBoard.flagCell(0, 3);
        assertEquals(
            MineSweeperCell.FLAGGED_MINE,
            someMineSweeperBoard.getCell(0, 3));
        someMineSweeperBoard.flagCell(3, 4);
        assertEquals(
            MineSweeperCell.COVERED_CELL,
            someMineSweeperBoard.getCell(3, 4));
        someMineSweeperBoard.flagCell(0, 3);
        assertEquals(MineSweeperCell.MINE, someMineSweeperBoard.getCell(0, 3));

    }


    // ----------------------------------------------------------
    /**
     * Tests height() method, if it returns proper height of the board.
     */
    public void testHeight()
    {
        assertEquals(5, someMineSweeperBoard.height());
    }


    // ----------------------------------------------------------
    /**
     * Tests whether a width method return proper width of the board.
     */
    public void testWidth()
    {

        assertEquals(4, someMineSweeperBoard.width());

    }


    // ----------------------------------------------------------
    /**
     * Tests the isGameLost() method to see if it determines whether the game is
     * lost or not.
     */
    public void testisGameLost()
    {
        someMineSweeperBoard.setCell(3, 2, MineSweeperCell.UNCOVERED_MINE);
        assertTrue(someMineSweeperBoard.isGameLost());
        someMineSweeperBoard.loadBoardState(someBoard);
        assertFalse(someMineSweeperBoard.isGameLost());

    }


    // ----------------------------------------------------------
    /**
     * Tests the isGameWon() method to see if it determines whether the game is
     * won or not. It loads the board to the state as if the game is won. Then
     * it checks whether the tested method predicated outcome of the game
     * correctly. Then it tests for different variants of the game lost.
     */
    public void testisGameWon()
    { // ~Loads the board to the state as if the game was won.
        someMineSweeperBoard.loadBoardState(gameIsWon);
        assertTrue(someMineSweeperBoard.isGameWon());
        // ~Loads the board to the state as if the game was lost.
        someMineSweeperBoard.loadBoardState(
            "  OO",
            "O++O",
            "O+O+",
            "+O+O",
            "OOOO");
        assertFalse(someMineSweeperBoard.isGameWon());
        // ~Another variant of the game lost.
        someMineSweeperBoard.loadBoardState(
            "+M21",
            "F3+ ",
            "F+F+",
            "1111",
            "OOOO");
        assertFalse(someMineSweeperBoard.isGameWon());
        // ~Another variant of the game lost.
        someMineSweeperBoard.loadBoardState(
            "F22M",
            "1MM2",
            "2332",
            "2M3M",
            "M3*2");
        assertFalse(someMineSweeperBoard.isGameWon());
        // ~Another variant of the game lost.
        someMineSweeperBoard.loadBoardState(
            "*M21",
            "F3+ ",
            "F+F+",
            "1111",
            "OOOO");
        assertFalse(someMineSweeperBoard.isGameWon());

    }


    // ----------------------------------------------------------
    /**
     * Tests the number of mines in adjacent cells.It loads the instance of the
     * board to certain preconditioned state and then checks if we obtained the
     * right number of mines using this method.
     */
    public void testNumberOfAdjacentMines()
    {
        someMineSweeperBoard.loadBoardState(someBoard);
        System.out.println(someMineSweeperBoard);

        assertEquals(1, someMineSweeperBoard.numberOfAdjacentMines(0, 0));
        assertEquals(2, someMineSweeperBoard.numberOfAdjacentMines(1, 0));
        assertEquals(3, someMineSweeperBoard.numberOfAdjacentMines(2, 0));
        assertEquals(1, someMineSweeperBoard.numberOfAdjacentMines(3, 0));

        assertEquals(2, someMineSweeperBoard.numberOfAdjacentMines(0, 1));
        assertEquals(2, someMineSweeperBoard.numberOfAdjacentMines(1, 1));
        assertEquals(4, someMineSweeperBoard.numberOfAdjacentMines(2, 1));
        assertEquals(3, someMineSweeperBoard.numberOfAdjacentMines(3, 1));

        assertEquals(3, someMineSweeperBoard.numberOfAdjacentMines(0, 2));
        assertEquals(4, someMineSweeperBoard.numberOfAdjacentMines(1, 2));
        assertEquals(5, someMineSweeperBoard.numberOfAdjacentMines(2, 2));
        assertEquals(2, someMineSweeperBoard.numberOfAdjacentMines(3, 2));

        assertEquals(1, someMineSweeperBoard.numberOfAdjacentMines(0, 3));
        assertEquals(3, someMineSweeperBoard.numberOfAdjacentMines(1, 3));
        assertEquals(2, someMineSweeperBoard.numberOfAdjacentMines(2, 3));
        assertEquals(2, someMineSweeperBoard.numberOfAdjacentMines(3, 3));

        assertEquals(1, someMineSweeperBoard.numberOfAdjacentMines(0, 4));
        assertEquals(2, someMineSweeperBoard.numberOfAdjacentMines(1, 4));
        assertEquals(1, someMineSweeperBoard.numberOfAdjacentMines(2, 4));
        assertEquals(1, someMineSweeperBoard.numberOfAdjacentMines(3, 4));

    }


    // ----------------------------------------------------------
    /**
     * Tests getCell() method by loading board to the certain preconditioned
     * state and then checking whether GetCell() method will return
     * MineSweeperCell enumerative type determined in loaded state of the board.
     */
    public void testGetCell()
    {
        someMineSweeperBoard.loadBoardState(someBoard);
        assertEquals(MineSweeperCell.MINE, someMineSweeperBoard.getCell(3, 0));
        assertEquals(
            MineSweeperCell.INVALID_CELL,
            someMineSweeperBoard.getCell(-1, 0));
        assertEquals(
            MineSweeperCell.INVALID_CELL,
            someMineSweeperBoard.getCell(4, 5));

    }


    // ----------------------------------------------------------
    /**
     * Tests revealBoard() method for two cases. It loads board to the certain
     * states , and then the reavelBoard() method is us applied. Then it checks
     * whether the revealed board displays correct MineSweeperCell enumerative
     * types.
     */
    public void testRevealBoard()

    {
        someMineSweeperBoard.loadBoardState(
            "FO++",
            "++++",
            "++O+",
            "++++",
            "+OM+");
        assertBoard(
            someMineSweeperBoard,
            "FO++",
            "++++",
            "++O+",
            "++++",
            "+OM+");
        someMineSweeperBoard.revealBoard();

        assertBoard(
            someMineSweeperBoard,
            "24**",
            "****",
            "**8*",
            "****",
            "*5**");
        someMineSweeperBoard.loadBoardState(
            "OOO*",
            "OO++",
            "++O+",
            "O+O+",
            "O+++");
        assertBoard(
            someMineSweeperBoard,
            "OOO*",
            "OO++",
            "++O+",
            "O+O+",
            "O+++");
        someMineSweeperBoard.revealBoard();
        assertBoard(
            someMineSweeperBoard,
            " 13*",
            "23**",
            "**6*",
            "4*7*",
            "2***");

    }


    // ----------------------------------------------------------
    /**
     * It tests setCell() method , by first setting a certain cell in the board
     * to the given value, and by applying this method to see if the obtained
     * value is the same as the set value.Then it loads board the predetermined
     * conditions and checks if the SetCell() method will return proper value
     * again.
     */
    public void testSetCell()
    {

        someMineSweeperBoard.setCell(1, 2, MineSweeperCell.FLAGGED_MINE);
        assertEquals(
            MineSweeperCell.FLAGGED_MINE,
            someMineSweeperBoard.getCell(1, 2));
        someMineSweeperBoard.loadBoardState(someBoard);
        assertEquals(
            MineSweeperCell.COVERED_CELL,
            someMineSweeperBoard.getCell(0, 0));

    }


    // ----------------------------------------------------------
    /**
     * Tests the uncoverCell() method by loading board to the certain state and
     * then uncovers certain cells to test all the cases of MineSweeperCell
     * enumerative types when the cell is not visible to the viewer.
     */
    public void testUncoverCell()
    {
        someMineSweeperBoard.loadBoardState(someBoard);
        someMineSweeperBoard.uncoverCell(0, 0);
        assertEquals(
            MineSweeperCell.ADJACENT_TO_1,
            someMineSweeperBoard.getCell(0, 0));
        someMineSweeperBoard.uncoverCell(1, 1);
        assertEquals(
            MineSweeperCell.UNCOVERED_MINE,
            someMineSweeperBoard.getCell(1, 1));
        someMineSweeperBoard.uncoverCell(2, 2);
        assertEquals(
            MineSweeperCell.ADJACENT_TO_5,
            someMineSweeperBoard.getCell(2, 2));
        someMineSweeperBoard.flagCell(2, 3);
        someMineSweeperBoard.uncoverCell(2, 3);
        assertEquals(
            MineSweeperCell.FLAGGED_MINE,
            someMineSweeperBoard.getCell(2, 3));

    }

}
