package cs2114.mazesolver;

import android.widget.TextView;
import android.view.View;
import android.app.Activity;
import android.os.Bundle;

// -------------------------------------------------------------------------
/**
 * This class extends Activity class. It contains a view of maze and another
 * buttons necessary for interaction with the user. It instantiates Maze class
 * (model) and passes it along MazeView by setModel method. It also instantiates
 * other widget and text views necessary for interaction and implements methods
 * necessary to use them.
 *
 * @author Adam Lech (PID adaml8)
 * @version 2012.06.05
 */
public class MazeSolverActivity
    extends Activity
{
    // ~ Instance/static variables .............................................
    private Maze       mazeModel;
    private MazeView   somemazeView;
    private TextView   statusLabel;
    private MazeSolver somemazeSolver;


    // ~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState
     *            previous state saved by the last run of the activity
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        // Calling super is required for the onCreate method
        super.onCreate(savedInstanceState);

        // Make this activity use the GUI layout defined in
        // res/layout/main.xml
        setContentView(R.layout.main);

        // Creates an instance of statusLabel (which informs the user about the
        // current drawing mode). It casts statusLabel widget as Text view.
        // Uses findViewById() method to obtain reference to the TextView widget
        statusLabel = (TextView)findViewById(R.id.statusLabel);

        // Creates an instance of solveBox button("Solve Maze"), which solves
        // the maze when clicked.
        // solveBox = (TextView)findViewById(R.id.solveMaze);

        // Instance of the MazeView. Uses the findViewById() method to obtain
        // and store reference to the MazeView instance , where maze is drawn
        // by a user.
        somemazeView = (MazeView)findViewById(R.id.mazeView);

        // Instantiates Maze model with nine cells in length. It will store
        // information about cells in the maze entered by a user.
        mazeModel = new Maze(9);

        // It attaches observer to the mazeModel so that observer can listen to
        // the changes to the model by user touching cells in the maze.
        somemazeView.setModel(mazeModel);

        somemazeSolver = new MazeSolver(mazeModel);

    }


    // ----------------------------------------------------------
    /**
     * Called when a "Clear Maze" button is clicked.It changes data in mazeModel
     * by setting all the cells as unexplored. It accomplishes that by calling
     * clearMaze() method of mazeModel.
     *
     * @param view
     *            - view that was clicked ("Clear Maze" button in this case)
     */
    public void clearmazeClicked(View view)
    {

        mazeModel.clearMaze();
        statusLabel.setText("Maze cleared!");

    }


    // ----------------------------------------------------------
    /**
     * Called when a check box is clicked, which allows user to switch drawing
     * mode. It calls drawWallsMode() method to inform Maze View about the
     * current drawing mode.
     *
     * @param view
     *            - the view that was clicked
     */
    public void drawEraseModeClicked(View view)
    {

        if (!somemazeView.isdrawWallsMode())
        {
            somemazeView.drawWallsMode(true);

        }
        else
        {
            somemazeView.drawWallsMode(false);

        }
    }


    // ----------------------------------------------------------
    /**
     * Calls "Solve Maze" button, which actuate solving algorithm that solves
     * the maze.
     *
     * @param view
     *            - the current view being passed into method
     */
    public void solveMazeClicked(View view)
    {
        String solutionString = somemazeSolver.solve();
        if (solutionString == null)
        {
            statusLabel.setText("No solution.");
        }
        else
        {
            statusLabel.setText(solutionString);
        }
    }


    // ----------------------------------------------------------
    /**
     * Getter method that obtains the current instance of the mazeModel, that
     * will by used in unit testing.
     *
     * @return - instance of Maze (mazeModel).
     */
    public Maze getMaze()
    {
        return mazeModel;

    }

}
