
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Creates a user interface with JFrame to create the panel for the user to
 * interact with
 *
 * @author Erick Chin
 * @date March 15, 2015
 */
public class GameViewer extends JFrame {

    // Constant integer for the frame width
    public static final int FRAME_WIDTH = 420;
    // Constant integer for the frame height
    public static final int FRAME_HEIGHT = 520;

    /**
     * Initialize and setup the JFrame
     */
    public static void main(String[] args) {
        // Create a new JFrame object
        JFrame frame = new JFrame();
        // Set the JFrame title to Brain Game
        frame.setTitle("Brain Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        // Create a GamePanel object
        JPanel panel = new GamePanel();
        // Add panel into JFrame
        frame.add(panel);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }
}
