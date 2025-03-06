import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The Paddle class represents the player's paddle in the game.
 * It implements the Sprite and Collidable interfaces and uses the
 * KeyboardSensor to receive user input.
 */
public class Paddle implements Sprite, Collidable, KeyboardSensor {
    public static final int FRAME_HEIGHT = 25;
    public static final int FRAME_WIDTH = 775;
    public static final int FIRST_REGION = 1;

    public static final int SECOND_REGION = 2;
    public static final int FORTH_REGION = 4;
    public static final int FIFTH_REGION = 5;

    private final biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private final double paddleSpeed = 5;

    /**
     * Creates a new Paddle object.
     *
     * @param keyboard  the KeyboardSensor to listen to user input
     * @param rectangle the rectangle representing the paddle's shape
     */
    public Paddle(KeyboardSensor keyboard, Rectangle rectangle) {
        this.keyboard = keyboard;
        this.rectangle = rectangle;
    }

    /**
     * Moves the paddle to the left, unless it is already at the leftmost edge
     * of the board.
     */
    public void moveLeft() {
        double epsilon = 1e-10;
        if (this.rectangle.getUpperLeft().getX() - this.paddleSpeed - FRAME_HEIGHT > epsilon) {
            Point newp = new Point(this.rectangle.getUpperLeft().getX() - this.paddleSpeed,
                    this.rectangle.getUpperLeft().getY());
            this.rectangle = new Rectangle(newp, rectangle.getWidth(), this.rectangle.getHeight());
        } else {
            this.rectangle = new Rectangle(new Point(FRAME_HEIGHT, this.rectangle.getUpperLeft().getY()),
                    rectangle.getWidth(), this.rectangle.getHeight());
        }
    }

    /**
     * Moves the paddle to the right, unless it is already at the rightmost edge
     * of the screen.
     */
    public void moveRight() {
        // A small number to avoid precision errors
        double epsilon = 1e-10;
        //if the paddle is not in the right most edge
        if (this.rectangle.getUpperRight().getX() + this.paddleSpeed - FRAME_WIDTH < epsilon) {
            //change the position of the rectangle
            this.rectangle = new Rectangle(new Point(this.rectangle.getUpperLeft().getX() + this.paddleSpeed,
            this.rectangle.getUpperLeft().getY()), this.rectangle.getWidth(), this.rectangle.getHeight());
        } else {
            //move as much as possible
            this.rectangle = new Rectangle(new Point(FRAME_WIDTH - this.rectangle.getWidth(),
                    this.rectangle.getUpperLeft().getY()), this.rectangle.getWidth(), this.rectangle.getHeight());
        }
    }
    /**
     * Draws the paddle on the given DrawSurface.
     *
     * @param d the DrawSurface to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.yellow);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) ((int) this.rectangle.getUpperRight().getY() - this.rectangle.getHeight()),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
    }

    /**
     * Moves the paddle one step according to the user input.
     */
    public void moeOneStep() {
        // listen for key events and move the paddle accordingly
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }
    /**
     * Notifies the paddle that time has passed and moves it accordingly.
     */
    @Override
    public void timePassed() {
        moeOneStep();
    }


    /**
     * Returns the collision rectangle of the paddle.
     *
     * @return the collision rectangle of the paddle
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double paddleWidth = this.rectangle.getWidth();
        double regionWidth = paddleWidth / 5.0;

        // determine which region the ball hit
        //This divides the distance by the width of each region, so we get a value between 0 and 4 (inclusive)
        // indicating which region the collision point is in.
        int region = (int) ((collisionPoint.getX() - this.rectangle.getUpperLeft().getX()) / regionWidth) + 1;
        //by Pitagoras sentence
        double speed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));

        // calculate new velocity based on region hit
        // Modify the velocity based on the region of the paddle
        if (region == FIRST_REGION || this.rectangle.getLeftLine().inLine(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(300, speed);
        } else if (region == SECOND_REGION) {
            return Velocity.fromAngleAndSpeed(330, speed);
        } else if (region == FORTH_REGION) {
            return Velocity.fromAngleAndSpeed(30, speed);
        } else if (region == FIFTH_REGION || this.rectangle.getRightLine().inLine(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(60, speed);
        } else { // region 3 - middle region
            // Only change the direction of the y-coordinate
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
    }

    /**
     * Add this paddle to the game.
     *
     * @param g the game
     */
        public void addToGame(GameLevel g) {
            g.addCollidable(this);
            g.addSprite(this);
        }

    /**
     * Check if a specific key is pressed and move the paddle accordingly.
     * @param s the string representing the key that was pressed
     * @return true if the key was recognized and the paddle moved, false otherwise
     */
        @Override
        public boolean isPressed(String s) {
            if (s.equals(KeyboardSensor.LEFT_KEY)) {
                moveLeft();
                return true;
            }
            if (s.equals(KeyboardSensor.RIGHT_KEY)) {
                moveRight();
                return true;
            }
            return false;
        }
    }
