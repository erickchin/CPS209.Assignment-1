
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * A subclass of GameToken which creates a particular GameToken that has a
 * visibility policy that takes a random integer interval and changes its color
 * of the pattern and rectangle after the timer duration is over every time
 *
 * @author Erick Chin
 * @date March 15, 2015
 */
public class BlinkingToken extends GameToken {

    // Define a Timer variable
    Timer timer;

    /**
     * Constructor used to create a BlinkingToken with properties of GameToken
     *
     * @param x determines the x-axis of the token
     * @param y determines the y-axis of the token
     * @param width determines the width of the token
     * @param height determines the height of the token
     */
    public BlinkingToken(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    /**
     * Overrides GameToken's setVisibilityPolicy Creates a timer for the object
     * to run changeColor method to generate a random color for the token
     */
    @Override
    public void setVisibilityPolicy() {
        /*
         * Implements ActionListener interface and checks the timer's duration 
         and runs changeColor method when the duration is over
         */
        class TimerListener implements ActionListener {

            // Overrides actionPerformed in ActionListener object and checks the 
            // timer's duration and applies changeColor when the interval is done
            @Override
            public void actionPerformed(ActionEvent event) {
                changeColor();
            }

        }
        // Creates a TimeListener object
        ActionListener timerListener = new TimerListener();
        // Define a integer DELAY that runs GameToken's random object and generages a number between [2, 4]
        final int DELAY = (super.random.nextInt(3) + 2) * 1000;
        // Creates Timer object to timer that takes the DELAY value and uses timerListener object
        timer = new Timer(DELAY, timerListener);
        // Start the timer
        timer.start();
    }

    /**
     * Overrides GameToken's draw method Checks if the token object has been
     * complete and if it is, it will stop the timer and also runs GameToken's
     * draw method
     *
     * @param g2 use to draw the object onto the panel
     */
    @Override
    public void draw(Graphics2D g2) {
        if (super.checkCompletetion()) {
            timer.stop();
        }
        super.draw(g2);
    }

    /**
     * Generates a random number from 0-9 and changes the color of the token
     * object depending on the number gotten
     */
    public void changeColor() {
        // Create a random number with GameToken's random object and store into number
        int number = super.random.nextInt(10);
        // If the number is equal to one of the case, it will change it to a specific color
        switch (number) {
            case 0:
                super.changeColor(Color.BLUE);
                break;
            case 1:
                super.changeColor(Color.CYAN);
                break;
            case 2:
                super.changeColor(Color.GRAY);
                break;
            case 3:
                super.changeColor(Color.GREEN);
                break;
            case 4:
                super.changeColor(Color.MAGENTA);
                break;
            case 5:
                super.changeColor(Color.ORANGE);
                break;
            case 6:
                super.changeColor(Color.PINK);
                break;
            case 7:
                super.changeColor(Color.WHITE);
                break;
            case 8:
                super.changeColor(Color.LIGHT_GRAY);
                break;
            case 9:
                super.changeColor(Color.DARK_GRAY);
                break;
        }
    }
}
