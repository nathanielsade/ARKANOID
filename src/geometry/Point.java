package geometry;

/**
 * The Point class represents a point in a 2D Cartesian plane.
 */
public class Point {
    public static final double EPSILON = 1E-10;
    private final double x; // The x-coordinate of the point.
    private final double y; // The y-coordinate of the point.

    /**
     * Creates a new Point object with the given x and y coordinates.
     *
     * @param x The x-coordinate of the new point.
     * @param y The y-coordinate of the new point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Computes the Euclidean distance between this point and another point.
     *
     * @param other The other point to compute the distance to.
     * @return The distance between this point and the other point.
     */
    public double distance(Point other) {
        return Math.sqrt(((this.x - other.x) * (this.x - other.x))
                + ((this.y - other.y) * (this.y - other.y)));
    }

    /**
     * Determines whether this point is equal to another point.
     *
     * @param other The other point to compare to.
     * @return True if this point is equal to the other point, false otherwise.
     */
    public boolean equals(Point other) {
        return Math.abs(this.x - other.x) < EPSILON
                && Math.abs(this.y - other.y) < EPSILON;

    }

    /**
     * Gets the x-coordinate of this point.
     *
     * @return The x-coordinate of this point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gets the y-coordinate of this point.
     *
     * @return The y-coordinate of this point.
     */
    public double getY() {
        return this.y;
    }
}
