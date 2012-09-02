package cs2114.mazesolver;

// -------------------------------------------------------------------------
/**
 * This class takes a maze as an argument and implements method that will
 * provide solution to the maze and return it as string representation of the
 * solution path. It uses helper methods to streamline the implementation of the
 * solve() method.It contains nested class to store cell coordinate values of
 * maze cells.
 *
 * @author Adam Lech (adaml8)
 * @version 2012.06.17
 */
public class MazeSolver
{
    // ~Instance field.
    private MazeBase myMaze;


    // ----------------------------------------------------------
    /**
     * This is a constructor method, that takes MazeBase type as an argument and
     * creates and instance of a maze class.
     *
     * @param maze
     *            instance of a maze, which is a two-dimensional array of
     *            enumerative MazeCell values;
     */
    public MazeSolver(MazeBase maze)
    {
        myMaze = maze;

    }


    // ----------------------------------------------------------
    /**
     * Uses helpers method solverStact() to create a string representation of
     * solution path, in case solution stack is not empty. If it is empty it
     * returns null value.
     *
     * @return - if there is no solution to the maze it returns null and string
     *         representation of solution path otherwise,
     */
    public String solve()
    {
        myMaze.resetPaths();
        // Creates an instance of LinkedStack that is empty if there is no
        // solution.Otherwise it contains CellCoordinate values that are part
        // of the solution path.
        LinkedStack<CellCoordinate> someStack = this.solverStack();
        CellCoordinate coordinate;
        if (someStack.isEmpty())
        {
            return null;
        }
        // String representation of solution path is created.
        else
        {
            StringBuilder coordinateList = new StringBuilder();

            while (!someStack.isEmpty())
            {
                coordinate = someStack.top();
                coordinateList.insert(0, "(" + coordinate.getX() + "," + " "
                    + coordinate.getY() + ")" + " ");
                someStack.pop();

            }
            return coordinateList.toString().trim();
        }

    }


    /**
     * Helper method that contains the actual maze solver algorithm. It creates
     * empty stack that will store cell coordinates of solution path. Then it
     * pushes cell coordinates into stack if there are any unexplored paths
     * around it.It marks cell as current if they belong to solution path ,and
     * as failed otherwise.
     *
     * @return - generic LinkedStack of cell coordinates that are part of
     *         solution path.
     */
    private LinkedStack<CellCoordinate> solverStack()
    {

        int x = 0;
        int y = 0;
        LinkedStack<CellCoordinate> myStack = new LinkedStack<CellCoordinate>();
        myStack.push(new CellCoordinate(x, y));
        // Starts loop that builds a stack of CellCoordinates that are part of
        // the solution path. If there is no solution the stack is empty.
        // The cells are set to appropriate values.
        while ((x & y) != (myMaze.size() - 1) && !myStack.isEmpty())
        {

            if (myMaze.getCell(x + 1, y) == MazeCell.UNEXPLORED)
            {

                x = x + 1;
                myStack.push(new CellCoordinate(x, y));

            }
            else if (myMaze.getCell(x, y + 1) == MazeCell.UNEXPLORED)
            {

                y = y + 1;
                myStack.push(new CellCoordinate(x, y));

            }
            else if (myMaze.getCell(x - 1, y) == MazeCell.UNEXPLORED)
            {

                x = x - 1;
                myStack.push(new CellCoordinate(x, y));

            }
            else if (myMaze.getCell(x, y - 1) == MazeCell.UNEXPLORED)
            {

                y = y - 1;
                myStack.push(new CellCoordinate(x, y));

            }

            else

            {

                myMaze.setCell(x, y, MazeCell.FAILED_PATH);
                myStack.pop();

                if (!myStack.isEmpty())
                {
                    x = myStack.top().getX();
                    y = myStack.top().getY();

                }

            }

        }
        return myStack;
    }


    /**
     * // ----------------------------------------------------------------------
     * --- /** This is a nested private class that stores the cell coordinates
     * of the maze, in order to create solution path stored in LinkedStack. It
     * has two get() methods in order to obtain x and y coordinates of the cell.
     * It sets the value of cell with given coordinates to CURRENT_PATH, each
     * time it is instantiated.
     *
     * @author Adam Lech
     * @version 2012.06.05
     */

    private class CellCoordinate
    { // ~Instance/Variable field.
        private int x;
        private int y;


        /**
         * Constructor method. Takes x and y cell coordinates as arguments and
         * assigns them values.
         *
         * @param x
         *            - horizontal coordinate of cell
         * @param y
         *            - vertical coordinate of cell
         */
        public CellCoordinate(int x, int y)
        {
            this.x = x;
            this.y = y;
            myMaze.setCell(x, y, MazeCell.CURRENT_PATH);

        }


        /**
         * Gets horizontal coordinate of cell.
         *
         * @return - horizontal coordinate of cell.
         */
        public int getX()
        {
            return x;
        }


        /**
         * Gets vertical coordinate of cell.
         *
         * @return - vertical coordinate of cell
         */

        public int getY()
        {
            return y;
        }
    }
}
