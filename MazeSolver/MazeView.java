package cs2114.mazesolver;

import android.view.MotionEvent;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.graphics.Paint;
import android.graphics.Canvas;
import java.util.Observable;
import java.util.Observer;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

// -------------------------------------------------------------------------
/**
 * This class is the most important part of the user's interface. It draws
 * square grid of rectangles representing the maze. It extends View class and
 * implements its methods. It sets the conditions for interaction with user by
 * enabling drawing the wall and then erasing it. It provides information for
 * Maze model.
 *
 * @author Adam Lech (PID adaml8)
 * @version 2012.06.04
 */
public class MazeView
    extends View

{
// ~Static instances/variables.

    private Maze    modelMaze;

    private boolean isDrawWallsMode;


    // ----------------------------------------------------------
    /**
     * A constructor that creates MazeView , which is added to the layout XML
     * file. It passes arguments along the super constructor and then performs
     * any necessary initialization.
     *
     * @param context
     *            - the Context where the MazeView is running in.
     * @param attrs
     *            - the attributes of the XML tag that is inflating the view.
     */
    public MazeView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

    }


    // ----------------------------------------------------------
    /**
     * Sets the model that is rendered by this view. It adds the view as an
     * observer for the model.
     *
     * @param model
     *            - model represented by Maze class.
     */

    public void setModel(Maze model)
    {
        this.modelMaze = model;

        modelMaze.addObserver(new MazeObserver());

    }


    /**
     * Overrides onMeasure method , which helps to determine the ideal width and
     * height of the view. Since maze is a square, both width and height of the
     * view should be of the same size. By overriding onMeasure method we are
     * forcing view to be always square by making width and height to be of the
     * same size.
     *
     * @param widthMeasureSpec
     *            - the desired width as determined by the layout
     * @param heightMeasureSpec
     *            - the desired height as determined by the layout
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        // Choose the smallest of both dimension for width and height of the
        // cell.
        int measureSpec = Math.min(widthMeasureSpec, heightMeasureSpec);

        // Call the superclass implementation but pass it with the modified
        // width and height instead of the incoming ones.
        super.onMeasure(measureSpec, measureSpec);

    }


    /**
     * Called when the touching event occurs on the view; either pressing the
     * finger down for the first time, moving it on the screen, or lifting it
     * back up. If the finger is down and the drawing mode is on (which is
     * represented by isDrawWallsMode variable) then the rectangles on the maze
     * are filled in white color. If the finger is down and the drawing mode is
     * off , the Rectangles on the grid are filled in black color.
     *
     * @param event
     *            - a MotionEvent object that describes the touch event
     * @return true - if the screen was touched (or the mouse was clicked over
     *         the view) and false if it wasn't
     */

    @Override
    public boolean onTouchEvent(MotionEvent event)

    {

        boolean changed = false;
        // Every time screen is touched (regardless of DrawWallsMode),paths in
        // the maze are reset.
        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            modelMaze.resetPaths();

            changed = true;
        }
        // Condition where screen is touched or fingers are dragged when
        // DrawWallsMode is on.
        if ((event.getAction() == MotionEvent.ACTION_DOWN ||
            event.getAction() == MotionEvent.ACTION_MOVE)
            && this.isDrawWallsMode)

        {
            // x , y represent the each individual cell coordinates as specified
            // in array of MazeCell value, they are obtained by dividing pixel
            // values of location of touch event by the width of the individual
            // rectangle
            float widthCell = this.getWidth() / modelMaze.size();
            int x = (int)(event.getX() / widthCell);
            int y = (int)(event.getY() / widthCell);
            modelMaze.setCell(x, y, MazeCell.WALL);

            changed = true;
        }
        // Condition where screen is touched or fingers are dragged when
        // DrawWallsMode is off.
        else if ((event.getAction() == MotionEvent.ACTION_DOWN || event
            .getAction() == MotionEvent.ACTION_MOVE) && !this.isDrawWallsMode)
        {
            float widthCell = this.getWidth() / modelMaze.size();
            int x = (int)(event.getX() / widthCell);
            int y = (int)(event.getY() / widthCell);
            modelMaze.setCell(x, y, MazeCell.UNEXPLORED);

            changed = true;

        }

        else
        {

            changed = false;

        }

        return changed;
    }


    /**
     * Called when the view is asked to redraw its contents. It it's redrawn
     * each time changes to the data model(Maze) occur.
     *
     * @param canvas
     *            - the object that represents the canvas to be drawn on
     */

    @Override
    public void onDraw(Canvas canvas)

    {
        // This is a safety check.Returns empty statement in case the model is
        // empty and there is nothing to draw.When XML is edited the views are
        // actually drawn live and this method is called, but in the GUI
        // designer there won't be an actual model set.

        if (modelMaze == null)
        {
            return;
        }
        // Sets the width(length) of each individual cell.
        float widthCell = this.getWidth() / modelMaze.size();

        // Creates five paint objects. The first black , which represent
        // unexplored path, the second one white which represent wall and the
        // third one is gray grid-line , which helps to distinguish all
        // the individual cells.The fourth one is red which represents failed
        // path and the fifth one is green which represents explored path.

        Paint gridRect = new Paint();
        gridRect.setStyle(Style.STROKE);
        gridRect.setColor(Color.GRAY);

        Paint whiteRect = new Paint();
        whiteRect.setStyle(Style.FILL);
        whiteRect.setColor(Color.WHITE);

        Paint blackRect = new Paint();
        blackRect.setStyle(Style.FILL);
        blackRect.setColor(Color.BLACK);

        Paint redRect = new Paint();
        redRect.setStyle(Style.FILL);
        redRect.setColor(Color.RED);

        Paint greenRect = new Paint();
        greenRect.setStyle(Style.FILL);
        greenRect.setColor(Color.GREEN);

        // Redraws maze by looping over each individual cell, and setting it's
        // colors according to the changes in MazeCell values.

        for (int w = 0; w < modelMaze.size(); ++w)
        {
            for (int h = 0; h < modelMaze.size(); ++h)
            {
                if (modelMaze.getCell(w, h) == MazeCell.WALL)
                {

                    canvas.drawRect(w * widthCell, h * widthCell, w * widthCell
                        + widthCell, h * widthCell + widthCell, whiteRect);
                    canvas.drawRect(w * widthCell, h * widthCell, w * widthCell
                        + widthCell, h * widthCell + widthCell, gridRect);
                }
                else if (modelMaze.getCell(w, h) == MazeCell.CURRENT_PATH)
                {

                    canvas.drawRect(w * widthCell, h * widthCell, w * widthCell
                        + widthCell, h * widthCell + widthCell, greenRect);
                    canvas.drawRect(w * widthCell, h * widthCell, w * widthCell
                        + widthCell, h * widthCell + widthCell, gridRect);
                }
                else if (modelMaze.getCell(w, h) == MazeCell.FAILED_PATH)
                {

                    canvas.drawRect(w * widthCell, h * widthCell, w * widthCell
                        + widthCell, h * widthCell + widthCell, redRect);
                    canvas.drawRect(w * widthCell, h * widthCell, w * widthCell
                        + widthCell, h * widthCell + widthCell, gridRect);
                }
                else
                {

                    canvas.drawRect(w * widthCell, h * widthCell, w * widthCell
                        + widthCell, h * widthCell + widthCell, blackRect);
                    canvas.drawRect(w * widthCell, h * widthCell, w * widthCell
                        + widthCell, h * widthCell + widthCell, gridRect);

                }

            }

        }

    }


    // ----------------------------------------------------------
    /**
     * Helper method. If the check box is set checked (which means that the
     * drawing Mode is on), then it sets the value of isDrawWallsMode to true.
     *
     * @param value
     *            - true if the check box is checked , false if it is not
     *            checked.
     */
    public void drawWallsMode(boolean value)
    {
        isDrawWallsMode = value;

    }


    // ----------------------------------------------------------
    /**
     * This method return current drawErase walls mode as a boolean
     *
     * @return - current drawErae walls mode as boolean
     */
    public boolean isdrawWallsMode()
    {
        return isDrawWallsMode;
    }


    // ----------------------------------------------------------------------

    /**
     * --- /** An observer that listens to the changes made to the Maze model.
     * This is a nested class inside a MazeView that can still access methods
     * that belong to the surrounding view.
     */
    private class MazeObserver
        implements Observer
    {
        /**
         * Called when the Maze model (in this case modelMaze instance) is
         * changed any time users interacts with the maze view.
         *
         * @param observable
         *            - the Observable object that was changed.
         * @param data
         *            - extra data about notification
         */
        public void update(Observable observable, Object data)
        {
            // The invalidate method that is used to force a view to be
            // repainted at the earliest opportunity.
            invalidate();
        }

    }

}
