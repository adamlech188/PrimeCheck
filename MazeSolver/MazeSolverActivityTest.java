package cs2114.mazesolver;

import android.widget.Button;
import android.widget.TextView;
import android.widget.CheckBox;

// -------------------------------------------------------------------------
/**
 * This class is tests all the instances of views and activities in MazeSolver
 * Activity class. It instantiates all widgets and views in activity class and
 * then tests activities which may be performed by a user (like clicking and
 * dragging finger in the maze).
 *
 * @author Adam Lech (PID adaml8)
 * @version 2012.06.05
 */

public class MazeSolverActivityTest
    extends student.AndroidTestCase<MazeSolverActivity>
{

    // ~Static
    // instance/variables------------------------------------------
    private CheckBox drawEraseMode;
    private TextView statusLabel;
    private Button   eraseMazeButton;
    private Button   solveMazeButton;
    private Maze     testMazeModel;
    private MazeView testMazeView;


    /**
     * Create a new MazeSolverActivityTest object. It declares a non-argument
     * constructor and calls the superclass constructor ,passing into it class
     * that represent tested activity.
     */
    public MazeSolverActivityTest()
    {
        super(MazeSolverActivity.class);
    }


    // ----------------------------------------------------------
    /**
     * Instantiates the classes defines in Maze model and view, in order to run
     * test cases. It checks whether elements of user's interface (like labels
     * and buttons) interacts properly.
     */
    public void setUp()

    {
        drawEraseMode = getView(CheckBox.class, R.id.drawEraseMode);
        statusLabel = getView(TextView.class, R.id.statusLabel);
        testMazeView = getView(MazeView.class, R.id.mazeView);
        eraseMazeButton = getView(Button.class, R.id.clearMaze);
        solveMazeButton = getView(Button.class, R.id.solveMaze);
        testMazeModel = getActivity().getMaze();
        new MazeSolver(testMazeModel);

    }


    // ----------------------------------------------------------
    /**
     * Runs test cases for check box that allows user to switch between draw and
     * erase mode. It clicks the button (default state is erase mode) and checks
     * whether check box was marked as checked. Then it simulates touching and
     * dragging the finger on screen to mark certain cells , after that it
     * checks whether the cells were marked as walls. After that it touches the
     * check box again and simulates touching and dragging the finger on screen
     * again, this time to erase walls. After that it checks whether the
     * appropriate cells were marked as unexplored.
     */
    public void testDrawEraseMode()
    {
        click(drawEraseMode);
        assertTrue(drawEraseMode.isChecked());

        touchDown(testMazeView, 0.0f, 0.0f);
        touchMove(50.0f, 0.0f);
        touchMove(100.0f, 0.0f);
        touchMove(150.0f, 0.0f);
        touchMove(200.0f, 0.0f);
        touchMove(250.0f, 0.0f);
        touchUp();

        assertEquals(MazeCell.UNEXPLORED, testMazeModel.getCell(0, 0));
        assertEquals(MazeCell.WALL, testMazeModel.getCell(1, 0));
        assertEquals(MazeCell.WALL, testMazeModel.getCell(2, 0));
        assertEquals(MazeCell.WALL, testMazeModel.getCell(3, 0));
        assertEquals(MazeCell.WALL, testMazeModel.getCell(4, 0));

        click(drawEraseMode);

        touchDown(testMazeView, 250.0f, 0.0f);

        touchMove(250.0f, 0.0f);
        touchMove(200.0f, 0.0f);
        touchMove(150.0f, 0.0f);
        touchMove(100.0f, 0.0f);
        touchMove(50.0f, 0.0f);
        touchMove(0.0f, 0.0f);

        touchUp();

        assertEquals(MazeCell.UNEXPLORED, testMazeModel.getCell(0, 0));
        assertEquals(MazeCell.UNEXPLORED, testMazeModel.getCell(1, 0));
        assertEquals(MazeCell.UNEXPLORED, testMazeModel.getCell(2, 0));
        assertEquals(MazeCell.UNEXPLORED, testMazeModel.getCell(3, 0));
        assertEquals(MazeCell.UNEXPLORED, testMazeModel.getCell(4, 0));

    }


    // ----------------------------------------------------------
    /**
     * Runs test cases to see whether "Clear Maze" button marks all the cells as
     * unexplored.First it sets the view to drawing mode and then simulates
     * touching down and dragging finger on maze view.It checks whether certain
     * cells were marked as walls. After that, it simulates clicking of
     * "Clear Maze" button and runs the tests to see whether the cells that had
     * a wall drawn in , are marked as unexplored.
     */
    public void testClearMazeClicked()
    {
        click(drawEraseMode);
        touchDown(testMazeView, 0.0f, 0.0f);
        touchMove(50.0f, 0.0f);
        touchMove(100.0f, 0.0f);
        touchMove(150.0f, 0.0f);
        touchMove(200.0f, 0.0f);

        touchUp();

        assertEquals(MazeCell.UNEXPLORED, testMazeModel.getCell(0, 0));
        assertEquals(MazeCell.WALL, testMazeModel.getCell(1, 0));
        assertEquals(MazeCell.WALL, testMazeModel.getCell(2, 0));
        assertEquals(MazeCell.WALL, testMazeModel.getCell(3, 0));

        click(eraseMazeButton);

        assertEquals(MazeCell.UNEXPLORED, testMazeModel.getCell(0, 0));
        assertEquals(MazeCell.UNEXPLORED, testMazeModel.getCell(1, 0));
        assertEquals(MazeCell.UNEXPLORED, testMazeModel.getCell(2, 0));
        assertEquals(MazeCell.UNEXPLORED, testMazeModel.getCell(3, 0));

    }


    // ----------------------------------------------------------
    /**
     * Tests "Solve Maze" button. It simulates the clicking of this button. And
     * then checks whether the text label was changed to "Solve Clicked." label.
     */
    public void testSolveMazeButton()
    {

        click(solveMazeButton);
        assertEquals(
            "(0, 0) (1, 0) (2, 0) (3, 0) (4, 0) (5, 0) (6, 0) (7, 0) (8, 0)" +
            " (8, 1) (8, 2)"
                + " (8, 3) (8, 4) (8, 5) (8, 6) (8, 7) (8, 8)",
            statusLabel.getText());

        click(drawEraseMode);
        assertTrue(testMazeView.isdrawWallsMode());

        touchDown(testMazeView, 0.0f, 0.0f);
        touchMove(70.0f, 0.0f);
        touchMove(70.0f, 60.0f);
        touchMove(70.0f, 120.0f);
        touchMove(70.0f, 170.0f);
        touchMove(30.0f, 170.0f);
        assertEquals(MazeCell.WALL, testMazeModel.getCell(1, 0));
        assertEquals(MazeCell.WALL, testMazeModel.getCell(1, 1));
        assertEquals(MazeCell.WALL, testMazeModel.getCell(1, 2));
        assertEquals(MazeCell.WALL, testMazeModel.getCell(1, 3));
        assertEquals(MazeCell.WALL, testMazeModel.getCell(0, 3));

        touchUp();

        click(solveMazeButton);

        assertEquals("No solution.", statusLabel.getText());

        assertEquals(MazeCell.WALL, testMazeModel.getCell(1, 0));
    }

}
