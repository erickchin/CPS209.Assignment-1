
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;

/**
 * GameToken implements a VisibileShape interface and a object used for the main
 * core of the game that stores a unique pattern to match with the user's
 * current pattern
 *
 * after the timer duration is over every time
 *
 * @author Erick Chin
 * @date March 15, 2015
 */
public class GameToken implements VisibleShape {

    // Define a boolean variable if the object is visible
    private boolean visible = false;
    // Define a Rectangle variable
    public Rectangle bbox;
    // Define a Pattern variable
    public Pattern pattern;
    // Define a Color variable
    private Color color;
    // Define a integer variable to store the number of attempts the user has used
    public int attempts = 0;
    // Define a boolean variable to see if the object is done matching
    private boolean isCompleted = false;
    // Create a Random object
    public Random random = new Random();

    /**
     * Constructor that takes in coordinates and the size. This constructor will
     * create a random pattern
     *
     * @param x x coordinate of the location
     * @param y y coordinate of the location
     * @param width width of the rectangle
     * @param height height of the rectangle
     */
    public GameToken(int x, int y, int width, int height) {
        bbox = new Rectangle(x, y, width, height);
        pattern = new Pattern(bbox);
        visible = true;
    }

    /**
     * Constructor that takes in coordinates and the size with the specific
     * pattern type.
     *
     * @param patternType the specific pattern type
     * @param x x coordinate of the location
     * @param y y coordinate of the location
     * @param width width of the rectangle
     * @param height height of the rectangle
     */
    public GameToken(int patternType, int x, int y, int width, int height) {
        bbox = new Rectangle(x, y, width, height);
        pattern = new Pattern(patternType, bbox);
        visible = true;
    }

    /**
     * Determines if the compared object has the same pattern
     *
     * @param other the other GameToken object comparing
     * @return if it is the same pattern
     */
    public boolean equalPattern(GameToken other) {
        int pattern = this.pattern.getType();
        int otherPattern = other.pattern.getType();
        if (pattern == otherPattern) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Moves the token to a specific coordinate
     *
     * @param x the x coordinate to move to
     * @param y the y coordinate to move to
     */
    public void moveToken(int x, int y) {
        // Shifts the rectangle object to x and y coordinate
        bbox.setLocation(x, y);
        // Create a new pattern with the same type but with the new bbox's coordinates
        pattern = new Pattern(pattern.getType(), bbox);
    }

    /**
     * Returns the type of color the token is
     *
     * @return GameToken's color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Changes the color of the token
     *
     * @param color the new color of the token
     */
    public void changeColor(Color color) {
        this.color = color;
    }

    /**
     * Changes the visibility of the token, setting visible to the opposite
     */
    public void changeVisibility() {
        visible = !visible;
    }

    /**
     * Check if the token is visible
     *
     * @return the status of visible variable
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Changes isCompleted to true
     */
    public void completed() {
        isCompleted = true;
    }

    /**
     * Check if the token is completed
     *
     * @return
     */
    public boolean checkCompletetion() {
        return isCompleted;
    }

    /**
     * Overrides VisibleShape interface and draws the shape and pattern
     *
     * @param g2 graphics used to draw the tokens
     */
    @Override
    public void draw(Graphics2D g2) {
        // Sets the current color of g2 into the current color of the token
        Color color = this.getColor();
        g2.setColor(color);
        // If the token is not visible and not completed, it will draw only the rectangle
        if (!visible && !isCompleted) {
            g2.draw(bbox);
            // If the conditions above are false, it will draw the pattern and rectangle
        } else {
            g2.draw(bbox);
            pattern.draw(g2);
        }
    }

    // Overrides VisibleShape interface and sets the visibility of the token to 5 seconds
    @Override
    public void setVisibilityPolicy() {
        /*
         * Implements ActionListener interface and checks the timer's duration and applies the visibility of the token
         */
        class TimerListener implements ActionListener {

            // Overrides actionPerformed in ActionListener interface and checks the timer's duration and applies changeVisibility when the interval is done
            @Override
            public void actionPerformed(ActionEvent event) {
                changeVisibility();
            }
        }
        // Creates a TimeListener object
        ActionListener timerListener = new TimerListener();
        // Define a integer DELAY that has a value of 5000
        final int DELAY = 5000;
        // Creates timer object that takes the DELAY value and uses timerListener object
        final Timer timer = new Timer(DELAY, timerListener);
        // Start the timer
        timer.start();
    }

    /**
     * Overrides VisibleShape interface and compares two VisibleShape objects to
     * see if they overlap
     *
     * @param other the other object to compare
     */
    @Override
    public boolean overlaps(VisibleShape other) {
        // Cast the other object into a GameToken
        GameToken otherShape = (GameToken) other;
        // If bbox's is inside the center of the other's rectangle, it will return true
        if (bbox.contains(otherShape.bbox.getX() + (otherShape.bbox.getWidth() / 2), otherShape.bbox.getY() + (otherShape.bbox.getHeight() / 2))) {
            return true;
            // If the condition above is false, it will return false
        } else {
            return false;
        }
    }
}
