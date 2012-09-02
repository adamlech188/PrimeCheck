package cs2114.minesweeper;

import student.*;

// -------------------------------------------------------------------------
/**
 * This class extends abstract class MineSweeperBoardBase and implements all of
 * its abstract methods.It serves as a data model for Mine Sweeper game. It
 * stores and provides all the necessary data for view and interaction with
 * graphical interface of the game. Methods implemented in this class provides
 * all the necessary data for graphical events in GUI interface.
 *
 * @author Adam Lech (PID adaml8)
 * @version 05.29.2012
 */

public class MineSweeperBoard
    extends MineSweeperBoardBase
{ // ~ Instance/static fields
    private int                 boardHeight;
    private int                 boardWidth;
    private TestableRandom      someRand;   // ~ Creates random number
// generator that will be used to distributes mines randomly in the column.

    private MineSweeperCell[][] myBoard;


    // ----------------------------------------------------------
    /**
     * Create a new MineSweeperBoard object.It initializes two - dimensional
     * array of MineSweeperCells. It populates this array with covered cell and
     * randomly distributes mines within this array. It uses random number
     * generator to distributes mines randomly.
     *
     * @param width
     *            - width of the board
     * @param height
     *            - height of the board
     * @param mines
     *            - number of mines randomly distributed
     */
    public MineSweeperBoard(int width, int height, int mines)
    {

        boardHeight = height;
        boardWidth = width;

        myBoard = new MineSweeperCell[height][width];

        for (int i = 0; i < height; ++i)
        {
            for (int k = 0; k < width; ++k)
            {
                myBoard[i][k] = MineSweeperCell.COVERED_CELL;
            }
        }

        int counter = 0;
        someRand = new TestableRandom();
        while (counter < mines)
        {

            int randWidth = someRand.nextInt(width); // Chooses random column
// number.
            int randHeight = someRand.nextInt(height); // Chooses random row
// number.
            if (myBoard[randHeight][randWidth] != MineSweeperCell.MINE) // In
// order to avoid assigning mine in the same place twice during initialization,
// this statement checks whether the mine was assigned already.

            {
                myBoard[randHeight][randWidth] = MineSweeperCell.MINE;
                counter += 1;
            }
            else
            {
                continue;
            }

        }

    }


    /**
     * Places and displaces a flag on the cell.
     *
     * @param x
     *            - column number
     * @param y
     *            - row number
     */

    public void flagCell(int x, int y)

    {
        if (myBoard[y][x] == MineSweeperCell.COVERED_CELL)
        {
            myBoard[y][x] = MineSweeperCell.FLAG;
        }
        else if (myBoard[y][x] == MineSweeperCell.MINE)
        {
            myBoard[y][x] = MineSweeperCell.FLAGGED_MINE;
        }
        else if (myBoard[y][x] == MineSweeperCell.FLAG)
        {
            myBoard[y][x] = MineSweeperCell.COVERED_CELL;
        }
        else
        {
            myBoard[y][x] = MineSweeperCell.MINE;
        }

    }


    /**
     * Getter method that will provide height of the board.
     *
     * @return - board height
     */

    public int height()
    {
        return boardHeight;
    }


    /**
     * Getter method that will provide width of the board
     *
     * @return - board width
     */

    public int width()
    {
        return boardWidth;

    }


    /**
     * Determines whether a player has lost the game. Game is lost if player
     * uncovers mine.
     *
     * @return - true if the current game has been lost and false otherwise
     */

    public boolean isGameLost()
    {
        for (int i = 0; i < boardHeight; ++i)
        {
            for (int k = 0; k < boardWidth; ++k)
            {
                if (myBoard[i][k] == MineSweeperCell.UNCOVERED_MINE)
                {

                    return true;
                }
                else
                {
                    continue;
                }

            }
        }
        return false;
    }


    /**
     * Determine whether the player has won the current game. There conditions
     * must be met: 1. Flags has been placed on all the mines 2. No flags has
     * been placed incorrectly. 3. All non-flagged cells has been uncovered. It
     * checks reiterates over all the cells in the board, by checking if the
     * negation of the above conditions is met. In that case it returns false.
     * Otherwise it returns false.
     *
     * @return - true if the game is won , false if it is lost.
     */
    public boolean isGameWon()
    {
        for (int i = 0; i < boardHeight; ++i)
        {
            for (int k = 0; k < boardWidth; ++k)
            {
                if (myBoard[i][k] == MineSweeperCell.UNCOVERED_MINE)
                {
                    return false;
                }
                else if (myBoard[i][k] == MineSweeperCell.FLAG)
                {
                    return false;
                }
                else if (myBoard[i][k] == MineSweeperCell.COVERED_CELL)
                {

                    return false;
                }
                else if (myBoard[i][k] == MineSweeperCell.MINE)
                {
                    return false;
                }

            }
        }
        return true;
    }


    /**
     * Determines the number of mines in adjacent cells.
     *
     * @param x
     *            - column number of the cell
     * @param y
     *            - row number of the cell
     * @return number of mines in any given cell
     */

    public int numberOfAdjacentMines(int x, int y)
    {
        int adjacentMines = 0;
        // ~ Corners

        if (x == 0 && y == 0)
        {
            adjacentMines = this.getMinesAtUpperLeftCorner(); // Helper method
// that counts the number of adjacent mines at the UpperLeftCorner
        }

        else if ((x == boardWidth - 1) && y == 0)
        {
            adjacentMines = this.getMinesAtUpperRightCorner();
        }

        else if (x == 0 && y == boardHeight - 1)
        {
            adjacentMines = this.getMinesAtLowerLeftCorner();
        }

        else if (x == boardWidth - 1 && y == boardHeight - 1)
        {
            adjacentMines = this.getMinesAtLowerRightCorner();
        }

        // Any entry in the middle
        else if ((x > 0 && x != boardWidth - 1)
            && (y > 0 && y != boardHeight - 1))
        {
            adjacentMines = this.getMinesMiddleCell(x, y);
        }

        // ~ Upper edge (without corners)
        else if ((x > 0 && x != boardWidth - 1) && y == 0)
        {
            adjacentMines = this.getMinesUpperEdge(x);
        }

        // ~Bottom edge
        else if ((x > 0 && x != boardWidth - 1) && y == boardHeight - 1)
        {
            adjacentMines = this.getMinesBottomEdge(x);
        }

        // ~Right edge.
        else if (x == boardWidth - 1 && (y > 0 && y != boardHeight - 1))
        {
            adjacentMines = this.getMinesRightEdge(y);
        }

        // ~ Left edge
        else
        {
            adjacentMines = this.getMinesLeftEdge(y);
        }

        return adjacentMines;

    }


    /**
     * Obtains content of the specified cell. The value obtained must correspond
     * to one of the MineSweeperCell enumerated type.
     *
     * @param x
     *            - column number (horizontal coordinate)
     * @param y
     *            - row number (vertical coordinate)
     * @return MineSweeperCell enumerated type , which corresponds to the
     *         content of the cell
     */

    public MineSweeperCell getCell(int x, int y)

    {
        if (x < 0 || y < 0)
        {
            return MineSweeperCell.INVALID_CELL;
        }
        else if ((x >= boardWidth) || (y >= boardHeight))
        {
            return MineSweeperCell.INVALID_CELL;
        }
        else
        {
            return myBoard[y][x];
        }
    }


    /**
     * Uncovers all the cells on the board.
     */

    public void revealBoard()
    {
        for (int i = 0; i < boardHeight; ++i)
        {
            for (int k = 0; k < boardWidth; ++k)
            {
                if (myBoard[i][k] == MineSweeperCell.COVERED_CELL)
                {
                    this.setUncoveredMineSweeperCell(k, i); // ~Helper method
// that uncovers cells that don't contain mines and show the number of adjacent
// mines.
                }

                else if (myBoard[i][k] == MineSweeperCell.MINE)
                {
                    myBoard[i][k] = MineSweeperCell.UNCOVERED_MINE;
                }
                else if (myBoard[i][k] == MineSweeperCell.FLAGGED_MINE)
                {
                    myBoard[i][k] = MineSweeperCell.UNCOVERED_MINE;
                }
                else if (myBoard[i][k] == MineSweeperCell.UNCOVERED_MINE)
                {
                    myBoard[i][k] = MineSweeperCell.UNCOVERED_MINE;
                }

                else
                {
                    this.setUncoveredMineSweeperCell(k, i);
                }

            }
        }

    }


    /**
     * Sets the content of the cell ,which corresponds to one of the
     * MineSweeperCell enumerated type.
     *
     * @param x
     *            - column number (horizontal coordinate)
     * @param y
     *            - row number (vertical coordinate)
     * @param value
     *            - MineSweeperCell enumerative type
     */

    protected void setCell(int x, int y, MineSweeperCell value)
    {
        myBoard[y][x] = value;

    }


    /**
     * Reveals the content of the specific cell. If there is no mine in the cell
     * it will show the number of mines in the adjacent cells.
     *
     * @param x
     *            - column number (horizontal coordinate)
     * @param y
     *            - row number (vertical coordinate)
     */

    public void uncoverCell(int x, int y)
    {
        if (myBoard[y][x] == MineSweeperCell.MINE)
        {
            myBoard[y][x] = MineSweeperCell.UNCOVERED_MINE;
        }
        else if (myBoard[y][x] == MineSweeperCell.FLAGGED_MINE)
        {
            myBoard[y][x] = MineSweeperCell.FLAGGED_MINE;
        }

        else
        {

            this.setUncoveredMineSweeperCell(x, y); // Helper method that shows
// the number of adjacent mines.
        }

    }


    // ------------Helper Methods -----------------------------------
    // ----------------------------------------------------------
    /**
     * Checks whether a cell contains a mine.
     *
     * @param widthCord
     *            - column number (horizontal coordinate)
     * @param heightCord
     *            - row number (vertical coordinate)
     * @return <b>True</b> if the cell contains a mine and <b>false</b> if there
     *         is no mine in the cell.
     */
    private boolean isMine(int widthCord, int heightCord)
    {
        if (myBoard[heightCord][widthCord] == MineSweeperCell.MINE)
        {
            return true;
        }
        else if (myBoard[heightCord][widthCord] == MineSweeperCell.FLAGGED_MINE)
        {
            return true;
        }
        else if (myBoard[heightCord][widthCord] ==
            MineSweeperCell.UNCOVERED_MINE)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    // ----------------------------------------------------------
    /**
     * Sets the uncovered cell , which doesn't contain a mine, into proper
     * MineSweeperCell enumerated type which will show how many mines are there
     * in adjacent cells.
     *
     * @param widthCor
     *            - column number (horizontal coordinate)
     * @param heightCor
     *            - row number (vertical coordinate)
     */
    private void setUncoveredMineSweeperCell(int widthCor, int heightCor)
    {
        if (this.numberOfAdjacentMines(widthCor, heightCor) == 0)
        {
            myBoard[heightCor][widthCor] = MineSweeperCell.ADJACENT_TO_0;
        }
        else if (this.numberOfAdjacentMines(widthCor, heightCor) == 1)
        {
            myBoard[heightCor][widthCor] = MineSweeperCell.ADJACENT_TO_1;
        }
        else if (this.numberOfAdjacentMines(widthCor, heightCor) == 2)
        {
            myBoard[heightCor][widthCor] = MineSweeperCell.ADJACENT_TO_2;
        }
        else if (this.numberOfAdjacentMines(widthCor, heightCor) == 3)
        {
            myBoard[heightCor][widthCor] = MineSweeperCell.ADJACENT_TO_3;
        }
        else if (this.numberOfAdjacentMines(widthCor, heightCor) == 4)
        {
            myBoard[heightCor][widthCor] = MineSweeperCell.ADJACENT_TO_4;
        }
        else if (this.numberOfAdjacentMines(widthCor, heightCor) == 5)
        {
            myBoard[heightCor][widthCor] = MineSweeperCell.ADJACENT_TO_5;
        }
        else if (this.numberOfAdjacentMines(widthCor, heightCor) == 6)
        {
            myBoard[heightCor][widthCor] = MineSweeperCell.ADJACENT_TO_6;
        }
        else if (this.numberOfAdjacentMines(widthCor, heightCor) == 7)
        {
            myBoard[heightCor][widthCor] = MineSweeperCell.ADJACENT_TO_7;
        }
        else
        {
            myBoard[heightCor][widthCor] = MineSweeperCell.ADJACENT_TO_8;
        }

    }


    // ----------------------------------------------------------
    /**
     * Counts the number of adjacent mines in the upper left corner.
     *
     * @return - number of adjacent mines in the upper left corner of the board.
     */
    private int getMinesAtUpperLeftCorner()
    {
        int adjacentMines = 0;
        for (int i = 0; i <= 1; ++i)
        {
            for (int k = 0; k <= 1; ++k)
            {

                if ((k != 0 || i != 0) && this.isMine(k, i)) // ~It ensures that
// only mines in adjacent cells will be counted by excluding the cell which
// adjacent mines are counted.
                {

                    adjacentMines += 1;
                }

                else
                {

                    continue;
                }
            }

        }

        return adjacentMines;

    }


    // -----------------------------------------------------------------------

    /**
     * Counts the number of adjacent mines in the upper right corner of the
     * board.
     *
     * @return - number of adjacent mines in the upper right corner of the
     *         board.
     */
    private int getMinesAtUpperRightCorner()
    {
        int adjacentMines = 0;
        for (int i = 0; i <= 1; ++i)
        {
            for (int k = -1; k <= 0; ++k)
            {

                if ((k != 0 || i != 0) && this.isMine(boardWidth - 1 + k, i))
                {

                    adjacentMines += 1;
                }

                else
                {

                    continue;
                }
            }
        }

        return adjacentMines;

    }


    // ----------------------------------------------------------
    /**
     * Counts the number of adjacent mines at the lower left corner.
     *
     * @return - number of adjacent mines at the lower left corner of the board.
     */
    private int getMinesAtLowerLeftCorner()
    {
        int adjacentMines = 0;

        for (int i = -1; i <= 0; ++i)
        {
            for (int k = 0; k <= 1; ++k)
            {

                if ((k != 0 || i != 0) && this.isMine(k, boardHeight - 1 + i))
                {

                    adjacentMines += 1;
                }

                else
                {

                    continue;
                }
            }

        }

        return adjacentMines;

    }


    // ----------------------------------------------------------
    /**
     * Counts the number of adjacent mines at the lower right corner cell.
     *
     * @return - the number of adjacent mines at the lower right corner cell.
     */
    private int getMinesAtLowerRightCorner()
    {
        int adjacentMines = 0;

        for (int i = -1; i <= 0; ++i)
        {
            for (int k = -1; k <= 0; ++k)
            {
                if ((k != 0 || i != 0)
                    && this.isMine(boardWidth - 1 + k, boardHeight - 1 + i))
                {
                    adjacentMines += 1;
                }
                else
                {
                    continue;
                }
            }

        }

        return adjacentMines;

    }


    // ----------------------------------------------------------
    /**
     * Counts the number of adjacent mines of the cell that is in the middle of
     * the board (edges and corners are excluded).
     *
     * @param x
     *            - column number
     * @param y
     *            - row number
     * @return - number of adjacent mines in the middle cell
     */
    private int getMinesMiddleCell(int x, int y)
    {
        int adjacentMines = 0;

        for (int i = -1; i <= 1; ++i)
        {
            for (int k = -1; k <= 1; ++k)
            {

                if ((k != 0 || i != 0) && this.isMine(x + k, y + i))
                {

                    adjacentMines += 1;
                }

                else
                {
                    continue;
                }
            }

        }

        return adjacentMines;

    }


    // ----------------------------------------------------------
    /**
     * Counts the number of adjacent mines in cell at the upper edge (corners
     * are excluded)
     *
     * @param x
     *            - column number, row will be always zero
     * @return - number of adjacent mines in the given cell
     */
    private int getMinesUpperEdge(int x)
    {
        int adjacentMines = 0;

        for (int i = 0; i <= 1; ++i)
        {
            for (int k = -1; k <= 1; ++k)
            {

                if ((k != 0 || i != 0) && this.isMine(x + k, i))
                {

                    adjacentMines += 1;
                }

                else
                {

                    continue;
                }
            }
        }

        return adjacentMines;

    }


    // ----------------------------------------------------------
    /**
     * Counts the number of adjacent mines in the bottom edge cells.
     *
     * @param x
     *            - column number
     * @return - number of adjacent mines
     */
    private int getMinesBottomEdge(int x)
    {
        int adjacentMines = 0;

        for (int i = -1; i <= 0; ++i)
        {
            for (int k = -1; k <= 1; ++k)
            {

                if ((k != 0 || i != 0)
                    && this.isMine(x + k, boardHeight - 1 + i))
                {

                    adjacentMines += 1;
                }

                else
                {

                    continue;
                }
            }

        }

        return adjacentMines;

    }


    // ----------------------------------------------------------
    /**
     * Counts the number of adjacent mines in the right edge cell on the board.
     *
     * @param y
     *            - row number;
     * @return - number of adjacent mines
     */
    private int getMinesRightEdge(int y)
    {
        int adjacentMines = 0;

        for (int i = -1; i <= 1; ++i)
        {
            for (int k = -1; k <= 0; ++k)
            {

                if ((k != 0 || i != 0)
                    && this.isMine(boardWidth - 1 + k, y + i))
                {

                    adjacentMines += 1;
                }

                else
                {

                    continue;
                }
            }
        }

        return adjacentMines;

    }


    // ----------------------------------------------------------
    /**
     * Counts the number of adjacent mines in the cell at the left edge.
     *
     * @param y
     *            - row number
     * @return - number of adjacent mines
     */
    private int getMinesLeftEdge(int y)
    {
        int adjacentMines = 0;

        for (int i = -1; i <= 1; ++i)
        {
            for (int k = 0; k <= 1; ++k)
            {
                if ((k != 0 || i != 0) && this.isMine(k, y + i))
                {
                    adjacentMines += 1;
                }
                else
                {
                    continue;
                }
            }
        }
        return adjacentMines;

    }

}
