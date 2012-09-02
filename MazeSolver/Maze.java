package cs2114.mazesolver;



// -------------------------------------------------------------------------
/**
 * This class represents data model for the Maze Solver project. It stores all
 * the information necessary to construct a user's interface (mainly grid with
 * marked rectangles) and all the data entered by user. It extends abstract
 * class MazeBase and implements all the abstract methods of this class.It
 * initializes a two-dimensional array representing a grid of rectangles, with
 * values corresponding to the paths that are either unexplored, aborted or
 * representing walls. Enumerative data types are employed for this purpose.
 *
 * @author Adam Lech (PID adaml8)
 * @version 2012.06.04
 */
public class Maze
    extends MazeBase
{
    // ~ Instance field

    private MazeCell[][] myMaze;
    private int          length;



    // ----------------------------------------------------------
    /**
     * Constructor method that creates a two dimensional array representing
     * rectangular grid, with enumerative data type of MazeCell class. When it
     * initializes grid it marks all the cells as unexplored.
     *
     * @param length
     *            - represents length of the side of two dimensional array. Grid
     *            is rectangular so both width and height of this array will be
     *            the same
     */
    public Maze(int length)

    {
        this.length = length;

        myMaze = new MazeCell[length][length];
        for (int k = 0; k < length; ++k)
        {
            for (int i = 0; i < length; ++i)
            {
                myMaze[k][i] = MazeCell.UNEXPLORED;
            }
        }

    }


    /**
     * Clears the entire maze of all current and failed paths and walls by
     * setting every cell to MazeCell.UNEXPLORED
     */

    @Override
    public void clearMaze()
    {

        for (int k = 0; k < length; ++k)
        {
            for (int i = 0; i < length; ++i)
            {
                myMaze[k][i] = MazeCell.UNEXPLORED;
            }
        }

        setChanged(); // marks maze as changed
        notifyObservers(); // notifies observers (in this case view)

    }


    /**
     * Obtains the value of the cell at the specified location in the maze.
     *
     * @param x
     *            - column number in the grid.
     * @param y
     *            - row number in the grid.
     * @return MazeCell - enumerative type of MazeCell
     */

    @Override
    public MazeCell getCell(int x, int y)
    {
        if ((x < 0 || y < 0) || (x >= length) || (y >= length))
        {
            return MazeCell.INVALID_CELL;
        }

        else
        {

            return myMaze[y][x];
        }

    }


    /**
     * Resets any current and failed paths in the maze to be unexplored. Any
     * walls in the maze should remain unmodified.
     */

    @Override
    public void resetPaths()
    {
        for (int k = 0; k < length; ++k)
        {
            for (int i = 0; i < length; ++i)
            {
                if ((myMaze[k][i] == MazeCell.FAILED_PATH)
                    || (myMaze[k][i] == MazeCell.CURRENT_PATH)) // ~Checks if
// the Maze cell is the failed or already marked by the user.
                {
                    myMaze[k][i] = MazeCell.UNEXPLORED;

                }
                else
                {

                    continue;
                }
            }
        }
        setChanged();
        notifyObservers();

    }


    /**
     * Sets the value of the cell at the specified location in the maze.
     *
     * @param x
     *            - column number in rectangular grid
     * @param y
     *            - row number in rectangular grid
     * @param value
     *            - MazeCell enumaritive type that is to be set.
     */

    @Override
    public void setCell(int x, int y, MazeCell value)

    { // This condition contains compound statement which checks whether out of
// bounds MazeCell coordinates are entered and whether the top left corner and
// bottom right cell are not attempted to be set as wall (there is a negation of
// this conditions).
        if ((x >= 0 && y >= 0)
            && (x < length && y < length)
            && ((x != 0 || y != 0) && (x != length - 1 || y != length - 1)
                || (value != MazeCell.WALL )))
        {
            myMaze[y][x] = value;
            setChanged();
            notifyObservers();
        }

    }


    /**
     * Getter method. Returns the size (length) of the maze.
     *
     * @return - size of the maze
     */

    @Override
    public int size()
    {
        // TODO Auto-generated method stub
        return length;
    }

}
