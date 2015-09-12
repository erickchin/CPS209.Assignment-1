
import java.awt.Graphics2D;

/**
 * VisibleShape interface that allows classes to use these methods and override
 * and change the objects to a more specific purpose
 *
 * @author Erick Chin
 * @date March 15, 2015
 */
public interface VisibleShape {

    /**
     * Used to draw the objects onto the panel
     *
     * @param g2 Graphics2D object to draw
     */
    void draw(Graphics2D g2);

    /**
     * Used to change the visibility policies of certain objects
     */
    void setVisibilityPolicy();

    /**
     * Compare objects to see if they overlap
     *
     * @param other the object you are comparing
     * @return if the objects overlap
     */
    boolean overlaps(VisibleShape other);
}
