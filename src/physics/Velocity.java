package physics;
import geometry.Point;
/**
 * The type Velocity.
 */
// Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {
    /**
     * The Dx.
     */
    private final double dx;
    /**
     * The Dy.
     */
    private final double dy;

    /**
     * Instantiates a new Velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
// constructor
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Gets dx.
     *
     * @return the dx
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Gets dy.
     *
     * @return the dy
     */
    public double getDy() {
        return this.dy;
    }
    /**
     *  * constructor taking angle and speed,and return its velocity.
     *
     * @param angle the angle
     * @param speed the speed
     * @return the velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radians = Math.toRadians(angle - 90);
        double dx = speed * Math.cos(radians);
        double dy = speed * Math.sin(radians); //negative because up is angle 0
        return new Velocity(dx, dy);
    }

    /**
     * Apply to point.
     * Take a point with position (x,y) and return a new point,
     * with position (x+dx, y+dy)
     *
     * @param p the point
     * @return the new point
     */
    public Point applyToPoint(Point p) {
        double newX = p.getX() + dx;
        double newY = p.getY() + dy;
        return new Point(newX, newY);
    }
}
