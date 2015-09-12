
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * A subclass of GameToken which creates a particular GameToken that has a
 * visibility policy that takes a random integer interval and changes its
 * visibility after the timer duration is over
 *
 * @author Erick Chin
 * @date March 15, 2015
 */
public class DisappearingToken extends GameToken {

    /**
     * Constructor used to create a DisappearingToken with properties of
     * GameToken
     *
     * @param x determines the x-axis of the token
     * @param y determines the y-axis of the token
     * @param width determines the width of the token
     * @param height determines the height of the token
     */
    public DisappearingToken(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    /**
     * Overrides GameToken's setVisibilityPolicy Creates a timer for the object
     * to allow it to disappears after the timer ends
     */
    @Override
    public void setVisibilityPolicy() {
        /*
         * Implements ActionListener interface and checks the timer's duration 
         * and applies the visibility of the token
         */
        class TimerListener implements ActionListener {

            // Overrides actionPerformed in ActionListener interface and checks 
            // the timer's duration and applies changeVisibility when the interval is done
            @Override
            public void actionPerformed(ActionEvent event) {
                changeVisibility();
            }
        }
        // Creates a TimeListener object
        ActionListener timerListener = new TimerListener();
        // Define a integer DELAY that runs GameToken's random object and generages
        // a number between [5,9] and * 1000
        final int DELAY = (super.random.nextInt(5) + 5) * 1000;
        // Creates timer object that takes the DELAY value and uses timerListener object
        final Timer timer = new Timer(DELAY, timerListener);
        // Start the timer
        timer.start();
    }
}
