
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Random;

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
public class Pattern {

    // Set a constant to store the cross pattern ID as 0
    public static final int CROSS = 0;
    // Set a constant to store the circleplus pattern ID as 1
    public static final int CIRCLEPLUS = 1;
    // Set square plus to store the pattern ID as 2
    public static final int SQUARE_PLUS = 2;
    // Define a integer to store the type of ID it is
    private int typeOfPattern;
    // Create a array list which holds all of the patterns parts
    ArrayList<Shape> shapes = new ArrayList<Shape>();

    /**
     * Constructor that takes in a rectangle to determine the location the
     * pattern should be
     *
     * @param bbox rectangle to gather the x and y coordinates
     */
    public Pattern(Rectangle bbox) {
        // Create a Random object
        Random random = new Random();
        // Store a random number
        int randomNumber = random.nextInt(3);
        // Set typeOfPattern to the random number
        typeOfPattern = randomNumber;
        // Set x and y coordinates to a variable
        double x = bbox.getX();
        double y = bbox.getY();
        // If the random number is equal to the CROSS, it will draw diagonal lines and add it into the array list
        if (randomNumber == CROSS) {
            Line2D.Double line1 = new Line2D.Double(new Point2D.Double(x, y), new Point2D.Double(x + bbox.getWidth(), y + bbox.getHeight()));
            Line2D.Double line2 = new Line2D.Double(new Point2D.Double(x, y + bbox.getHeight()), new Point2D.Double(x + bbox.getWidth(), y));
            shapes.add(line1);
            shapes.add(line2);
            // If the random number is equal to the CIRCLEPLUS, it will draw a ellipse and two lines and storing it into the array list
        } else if (randomNumber == CIRCLEPLUS) {
            Ellipse2D.Double circle = new Ellipse2D.Double(x, y, bbox.getWidth(), bbox.getHeight());
            Line2D.Double verticalLine = new Line2D.Double(new Point2D.Double(x + (bbox.getWidth() / 2), y), new Point2D.Double(x + (bbox.getWidth() / 2), y + bbox.getHeight()));
            Line2D.Double horizontalLine = new Line2D.Double(new Point2D.Double(x, y + (bbox.getHeight()) / 2), new Point2D.Double(x + bbox.getWidth(), y + (bbox.getHeight() / 2)));
            shapes.add(verticalLine);
            shapes.add(horizontalLine);
            shapes.add(circle);
            // If it is not one of the conditions above, it will draw a plus sign
        } else {
            Rectangle2D rectangle1 = new Rectangle2D.Double(x + (bbox.getWidth() / 3), y, bbox.getWidth() / 3, bbox.getHeight());
            Rectangle2D rectangle2 = new Rectangle2D.Double(x, y + (bbox.getHeight() / 3), bbox.getWidth(), bbox.getHeight() / 3);
            shapes.add(rectangle1);
            shapes.add(rectangle2);
        }

    }

    /**
     * Constructor that takes in a rectangle to determine the location the
     * pattern should be and the specific type of pattern
     *
     * @param type the specific pattern type
     * @param bbox rectangle to gather the x and y coordinates
     */
    public Pattern(int type, Rectangle bbox) {
        // Set x and y coordinates to a variable
        double x = bbox.getX();
        double y = bbox.getY();
        // Set typeOfPattern to type 
        typeOfPattern = type;
        // If type is equal to the CROSS, it will draw diagonal lines and add it into the array list
        if (type == CROSS) {

            Line2D.Double line1 = new Line2D.Double(new Point2D.Double(x, y), new Point2D.Double(x + bbox.getWidth(), y + bbox.getHeight()));
            Line2D.Double line2 = new Line2D.Double(new Point2D.Double(x, y + bbox.getHeight()), new Point2D.Double(x + bbox.getWidth(), y));
            shapes.add(line1);
            shapes.add(line2);
            // If type is equal to the CIRCLEPLUS, it will draw a ellipse and two lines and storing it into the array list
        } else if (type == CIRCLEPLUS) {
            Ellipse2D.Double circle = new Ellipse2D.Double(x, y, bbox.getWidth(), bbox.getHeight());
            Line2D.Double verticalLine = new Line2D.Double(new Point2D.Double(x + (bbox.getWidth() / 2), y), new Point2D.Double(x + (bbox.getWidth() / 2), y + bbox.getHeight()));
            Line2D.Double horizontalLine = new Line2D.Double(new Point2D.Double(x, y + (bbox.getHeight()) / 2), new Point2D.Double(x + bbox.getWidth(), y + (bbox.getHeight() / 2)));
            shapes.add(verticalLine);
            shapes.add(horizontalLine);
            shapes.add(circle);
            // If it is not one of the conditions above, it will draw a plus sign
        } else {
            Rectangle2D rectangle1 = new Rectangle2D.Double(x + (bbox.getWidth() / 3), y, bbox.getWidth() / 3, bbox.getHeight());
            Rectangle2D rectangle2 = new Rectangle2D.Double(x, y + (bbox.getHeight() / 3), bbox.getWidth(), bbox.getHeight() / 3);
            shapes.add(rectangle1);
            shapes.add(rectangle2);
        }
    }

    /**
     * Returns the typeOfPattern
     *
     * @return typeOfPattern variable
     */
    public int getType() {
        return typeOfPattern;
    }

    /**
     * Draws the pattern onto the panel
     *
     * @param g2 draw the graphics
     */
    public void draw(Graphics2D g2) {
        // If the type of pattern is square plus, it will fill the pattern 
        if (getType() == SQUARE_PLUS) {
            // Iterates through all the shapes and draws all of the objects
            for (Shape shape : shapes) {

                g2.fill(shape);
            }
        // If it is not a square plus, it will draw the pattern
        } else {
            // Iterates through all the shapes and draws all of the objects
            for (Shape shape : shapes) {

                g2.draw(shape);
            }
        }

    }
}
