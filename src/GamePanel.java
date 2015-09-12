
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.ArrayList;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * Creates a graphic interface for the user to play the game and creates object
 * of different GameTokens that are randomly generated and placed. Timers are
 * placed to create a delay for the user in the beginning to let the person
 * memorize it and timer which repaints the interface and check if the user has
 * completed the task. Also uses mouse listener to take user's mouse to see if
 * the user has clicked an object or wanting to switch the certain pattern.
 * After if the user has finished clicking all of the objects, it will end the
 * game.
 *
 * @author Erick Chin
 * @date November 15th, 2014
 */
public class GamePanel extends JPanel {

    // Creates an array list of GameTokens named gameTokens
    ArrayList<GameToken> gameTokens;
    // Creates a GameToken for the user's mouse named mouseToken
    GameToken mouseToken;
    // Creates a Random object named random
    Random random = new Random();
    // Create a constant of the width of the token named TOKEN_WIDTH
    final int TOKEN_WIDTH = 40;
    // Create a constant of the nubmer of tokens in the game named TOKEN_COUNT
    final int TOKEN_COUNT = 10;
    // Boolean to store if the game has started
    boolean gameStarted = false;
    // Stores the score of the player
    int score = 0;
    // Stores the number of tokens left on the field
    int numberOfTokens = 10;
    // Defines a MouseListener named gameListener
    MouseListener gameListener;
    // Timer object that delays the beginning to let the user to look at the patterns
    Timer delayTimer;
    // Timer object that updates the field and check if the game is over
    Timer updateTimer;
    // Timer object that counts the time
    Timer clockTimer;
    // JLabel that shows the score
    JLabel scoreLabel;
    // JLabel that shows the time
    JLabel timeLabel;

    /**
     * Constructor of the JPanel that sets up the game by creating the objects,
     * starting the timer and initiate the mouse listener
     */
    public GamePanel() {
        // Creates a JLabel object for timeLabel with the starting time
        timeLabel = new JLabel("Time: 0s");
        // Adds timeLabel onto the panel
        add(timeLabel);
        // Creates a JLabel object for scoreLabel with the starting score
        scoreLabel = new JLabel("Score: 0");
        // Adds scoreLabel onto the panel
        add(scoreLabel);
        // Creates a ArrayList of GameTokens to gameTokens
        gameTokens = new ArrayList<GameToken>();
        // Adds the first token into the array list
        gameTokens.add(new GameToken(random.nextInt(350), random.nextInt(400), TOKEN_WIDTH, TOKEN_WIDTH));
        // A for loop that iterates from 1 to TOKEN_COUNT which runs createUniqueToken
        // to create tokens that do not overlap
        for (int i = 1; i < TOKEN_COUNT; i++) {
            // Runs createUniqueToken with random x, y and gameTokens parameters
            createUniqueToken(random.nextInt(350), random.nextInt(400), gameTokens);
        }
        // Create a new GameToken object to mouseToken
        mouseToken = new GameToken(0, 0, 0, TOKEN_WIDTH, TOKEN_WIDTH);
        // Runes changeColor method inside GameToken to change the color to BLUE
        mouseToken.changeColor(Color.BLUE);
        // A loop t hat iterates all of the objects in gameToken array list to start
        // up the visibility policy of the different tokens
        for (GameToken gameToken : gameTokens) {
            // Runs setVisibilityPolicy method
            gameToken.setVisibilityPolicy();
        }
        /*
         * GameListener extends to MouseAdapter and checks the user's mouse inputs
         * to see if user has placed the mouse onto a object with the right pattern
         */
        class GameListener extends MouseAdapter {

            /**
             * Overrides mousePressed in MouseAdapter class and takes in the
             * user's mouse inputs and checks if correctly placed the mouse
             * pattern onto the gameToken objects*
             *
             * @param event take user's mouse inputs
             */
            @Override
            public void mousePressed(MouseEvent event) {
                // If the user has click right click, it will change the pattern of the mouseToken
                if (event.getButton() == 3) {
                    // Saves the current pattern type 
                    int i = mouseToken.pattern.getType();
                    // Define x and y coordinates as the center of the token object
                    int x = event.getX() - TOKEN_WIDTH / 2;
                    int y = event.getY() - TOKEN_WIDTH / 2;
                    // If i is equal to 2, it will create a new mouse object that has the pattern of 0
                    if (i == 2) {
                        // Create a new GameToken that takes in parameters of a certain pattern and location of the mouse
                        mouseToken = new GameToken(0, x, y, TOKEN_WIDTH, TOKEN_WIDTH);
                        // Changes the color of the new token to blue
                        mouseToken.changeColor(Color.BLUE);
                        // If i is not equal to 2, it will creater a new GameToken object that changes the pattern by increasing it by 1
                    } else {
                        // Create a new GameToken object that has a new pattern
                        mouseToken = new GameToken(i + 1, x, y, TOKEN_WIDTH, TOKEN_WIDTH);
                        // Changes the color of the new token to Blue
                        mouseToken.changeColor(Color.BLUE);
                    }
                    // If right click is not clicked, it will see if the left click is on a object and checks
                    // if its also the same pattern and will determine the score given to the player depending on how many attempts tried
                } else {
                    // Define x and y coordinates as the center of the token object
                    int x = event.getX() - TOKEN_WIDTH / 2;
                    int y = event.getY() - TOKEN_WIDTH / 2;
                    // Moves the mouseToken object to the x and y coordinates
                    mouseToken.moveToken(x, y);
                    // Iterates on the amount of GameToken objects there are in the array list and see if user has placed the right pattern onto the gameTokens
                    for (GameToken gameToken : gameTokens) {
                        // Runs the GameToken methods overlaps, equalPattern and checkCompletion
                        // to see if the user has placed the mouse object onto a gameToken, has the same pattern and check if the gametoken is completed already
                        if (mouseToken.overlaps(gameToken) && mouseToken.equalPattern(gameToken) && !gameToken.checkCompletetion()) {
                            // Changes the token to be completed
                            gameToken.completed();
                            // If the game token has 0 attempts, it will give 2 points
                            if (gameToken.attempts == 0) {
                                score += 2;
                                // If the game token has 1 attempt, it will give 1 point
                            } else if (gameToken.attempts == 1) {
                                score++;
                                // If the game token has more than 2 attemps, it will minus 1 point
                            } else if (gameToken.attempts > 2) {
                                score--;
                            }
                            // Updates the scoreLabel with the current score
                            scoreLabel.setText("Score: " + score);
                            // Changes the color of the gameToken to green
                            gameToken.changeColor(Color.GREEN);
                            // Decrease the total number of tokens
                            numberOfTokens--;
                            break;
                            // If user places the object with a wrong pattern, it will increase attempts by 1
                        } else if (mouseToken.overlaps(gameToken) && !mouseToken.equalPattern(gameToken) && !gameToken.checkCompletetion()) {
                            gameToken.attempts++;
                        }
                    }
                }
            }
        }
        // Create a new GameListener object into gameListener
        gameListener = new GameListener();
        /*
         * Implements ActionListener interface and repaints in 10 milliseconds and also check if the game is over and run endGame method
         */
        class UpdateListener implements ActionListener {

            // Overrides actionPerformed in ActionListener object and checks the timer's duration and applies repaint and check if the game is over 
            @Override
            public void actionPerformed(ActionEvent event) {
                // If numberOfTokens is equal to 0, it will run endGame method
                if (numberOfTokens == 0) {
                    endGame();
                }
                repaint();
            }
        }
        // Create a UpdateListener object and saves it into updateListener
        UpdateListener updateListener = new UpdateListener();
        // Creates a new Timer object and takes in values of 10 milliscecond and updateListener object
        updateTimer = new Timer(10, updateListener);
        // Start the timer
        updateTimer.start();
        /*
         * Implements ActionListener interface and creates a delay for the user to check the patterns
         */
        class DelayListener implements ActionListener {

            // Checks the timer's duration and runs startGame once
            @Override
            public void actionPerformed(ActionEvent event) {
                startGame();
            }
        }
        // Create a DelayListener object and saves it into delayListener
        DelayListener delayListener = new DelayListener();
        // Creates a new Timer object and takes in values of 3000 milliscecond and delayListener object
        delayTimer = new Timer(3000, delayListener);
        // Set timer to do only 1 iteration
        delayTimer.setRepeats(false);
        // Start the timer
        delayTimer.start();
    }

    // Overrides paintCompnent in JPanel class and paints all tokens onto the panel
    @Override
    public void paintComponent(Graphics g) {
        // Runs paintComponent in JPanel
        super.paintComponent(g);
        // Cast Graphics object into Graphics2D
        Graphics2D g2 = (Graphics2D) g;
        // Change the stroke to a thickness of 1
        g2.setStroke(new BasicStroke(1));
        // Iterates through all of the GameToken objects in gameTokens and sets its color to Black and draw it onto the panel
        for (GameToken gameToken : gameTokens) {
            g2.setColor(Color.BLACK);
            // Draws gameToken onto the panel with g2 object
            gameToken.draw(g2);
        }
        // Set g2 color to mouseToken color using getColor method
        g2.setColor(mouseToken.getColor());
        // Draws mouseToken onto the panel with g2 object
        mouseToken.draw(g2);
    }

    /**
     * Starts the game and initializes the timer of the duration the user is
     * taking
     */
    public void startGame() {
        /*
         * Implements ActionListener interface and updates the label of the time
         */
        class TimerListener implements ActionListener {

            // Set a private integer variable to 0
            private int count = 0;
            // Define a private and constant JLabel
            private final JLabel label;

            /**
             * Constructor that takes in a label parameter
             *
             * @param label
             */
            public TimerListener(JLabel label) {
                this.label = label;
            }

            // Overrides actionPerformed in ActionListener and increases count by 1 and sets label to the current time
            @Override
            public void actionPerformed(ActionEvent event) {
                count++;
                label.setText("Time: " + count + "s");
            }
        }
        // Create a new TimerListener object with a parameter of the timeLabel
        TimerListener clockListener = new TimerListener(timeLabel);
        // Create a new timer object with 1000 millisecond delay and clockListener object
        clockTimer = new Timer(1000, clockListener);
        // Start the timer
        clockTimer.start();
        // Add the gamelistener object to the panel
        addMouseListener(gameListener);
    }

    /**
     * Stops the game by ending the timer
     */
    public void endGame() {

        scoreLabel.setText("You win with " + score + " score!");
        clockTimer.stop();
    }

    /**
     * Recursion method that searches through all of the game tokens to see if
     * the location of the previous tokens would overlap the new current one If
     * it is true, it will run itself again to try a newly generated number
     *
     * @param x the x coordinate of the token
     * @param y the y coordinate of the token
     * @param tokens the array list of objects to see if the locations overlap
     */
    public void createUniqueToken(int x, int y, ArrayList<GameToken> tokens) {
        // Define a boolean variable
        boolean unique = false;
        // Create a new GameToken object named tempToken with the passed in coordinates
        GameToken tempToken = new GameToken(x, y, TOKEN_WIDTH, TOKEN_WIDTH);
        // Iterates depending on the number of tokens there are in the array list
        for (GameToken token : tokens) {
            // If tempToken contains or intersects the current token, it will break the loop and set unique to false;
            if (tempToken.bbox.contains(token.bbox) || tempToken.bbox.intersects(token.bbox)) {
                unique = false;
                break;
                // If the conditions are not true above, it will set unique to true;
            } else {
                unique = true;
            }
        }
        // If unique is true, it will create a new token object depeending if typeOfToken is 0 or 1
        if (unique == true) {
            // Define a new int variable with a random number
            int typeOfToken = random.nextInt(3);
            // If typeOfToken is 1, it will create a BlinkingToken
            if (typeOfToken == 1) {
                gameTokens.add(new BlinkingToken(x, y, TOKEN_WIDTH, TOKEN_WIDTH));
                // If typeOfToken is not 1, it will create a DisapearingToken
            } else if (typeOfToken == 2) {
                gameTokens.add(new DisappearingToken(x, y, TOKEN_WIDTH, TOKEN_WIDTH));
            } else {
                gameTokens.add(new GameToken(x, y, TOKEN_WIDTH, TOKEN_WIDTH));
            }
            // If unique is false, it will run the method itself again with newly generated x and y coordinates
        } else {
            createUniqueToken(random.nextInt(350), random.nextInt(400), tokens);
        }
    }
}
