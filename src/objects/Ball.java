package objects;

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import geometry.Point;
import geometry.Line;
import physics.Velocity;
import collision.HitListener;
import physics.CollisionInfo;
import game.GameLevel;
import physics.GameEnvironment;
import sprites.Sprite;
import geometry.Rectangle;


/**
 * The Ball class represents a 2D ball with a center point, radius, color and velocity.
 */
public class Ball implements Sprite {
    private Point center;
    private final int r;
    private final java.awt.Color color;
    private Velocity velocity;
    private int frame;
    private GameEnvironment environment;
    private final List<HitListener> hitListeners;


    /**
     * Constructs a new ball with the given center point, radius and color.
     *
     * @param center the center point of the ball
     * @param r the radius of the ball
     * @param color  the color of the ball
     */
// constructor
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
        // this.velocity = new Velocity(0, 0);
        this.hitListeners = new ArrayList<>();

    }

    /**
     * Instantiates a new Ball.
     * Constructs a new ball with the given center coordinates, radius and color.
     *
     * @param x the x-coordinate of the center point of the ball
     * @param y the y-coordinate of the center point of the ball
     * @param r the radius of the ball
     * @param color the color of the ball
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        center = new Point(x, y);
        this.r = r;
        this.color = color;
        // this.velocity = new Velocity(0, 0);
        this.hitListeners = new ArrayList<>();

    }

    /**
     * Sets game environment.
     *
     * @param g the g
     */
    public void setGameEnvironment(GameEnvironment g) {
        this.environment = g;
    }

    /**
     * Gets x coordinate of the center point.
     *
     * @return the x coordinate
     */
// accessors
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets y coordinate of the center point.
     *
     * @return the y coordinate
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gets the size of the ball (its area).
     *
     * @return the size of the ball
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Gets the color of the ball.
     *
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    // draw the ball on the given DrawSurface
    @Override
    public void drawOn(DrawSurface surface) {
        int x = (int) this.center.getX();
        int y = (int) this.center.getY();
        surface.setColor(this.color);
        surface.fillCircle(x, y, this.r);
        surface.drawCircle(x, y, this.r);
        surface.setColor(Color.BLACK);
        surface.drawCircle(x, y, this.r);
    }

    /**
     * Sets velocity.
     *
     * @param v the new velocity of the ball
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Gets velocity.
     *
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }


    /**
     * Sets frame.
     *
     * @param frame the frame of the ball
     */
    public void setFrame(int frame) {
        this.frame = frame;
    }

    /**
     * Gets frame.
     *
     * @return the frame of the ball
     */
    public int getFrame() {
        return this.frame;
    }

    /**
     * Sets velocity.
     *
     * @param dx the change in x direction
     * @param dy the change in y direction
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Draw the ball on the given surface.
     *
     * @param surface the surface to draw on
     */
    public void drawOn1(DrawSurface surface) {
        // Clear previous position of the ball
        surface.setColor(Color.GRAY);
        surface.fillCircle((int) center.getX(), (int) center.getY(), this.r);
        // Draw ball at the new position
        surface.setColor(this.color);
        surface.fillCircle((int) center.getX(), (int) center.getY(), this.r);
    }

    /**
     * Move the ball one step. If there is a collision with an object, the ball
     * will be moved to just before the collision point and its velocity will be
     * updated based on the hit object.
     */
    public void moveOneStep() {
        double epsilon = 1E-10;
        if (Math.abs(this.center.getY() + this.r - 625) < epsilon) {
            List<HitListener> listeners = new ArrayList<>(this.hitListeners);
            // Notify all listeners about a hit event:
            for (HitListener hl : listeners) {
                hl.hitEvent(new Block(new Rectangle(new Point(25, 625), 750, 25),
                        Color.DARK_GRAY), this);
            }
        }
        //the coordinate of the point after the ball will move
        Point endP = new Point(this.center.getX() + this.velocity.getDx(),
                this.center.getY() + this.velocity.getDy());
        CollisionInfo obstacle = this.environment.getClosestCollision(new Line(this.center, endP));
        // No obstacles were hit, so move the ball to the end of the trajectory
        if (obstacle == null) {
            this.center = endP;
        } else {
            // An obstacle was hit, so move the ball to just before the intersection point
            Point intersection = obstacle.getCollisionPoint();
            this.velocity = obstacle.collisionObject().hit(this, intersection, this.velocity);
            // this.center = this.velocity.applyToPoint(this.center);
        }
    }

    /**
     * Notifies the Ball that time has passed and moves it accordingly.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Add the ball to the game.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        setGameEnvironment(g.getGameEnviroment());
    }

    /**
     * Remove from game.
     *
     * @param game the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}
