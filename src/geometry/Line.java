package geometry;

import java.util.List;

/**
 * This class represents a line in 2D space.
 */
public class Line {
    private Point start; // The starting point of the line
    private Point end; // The ending point of the line

    /**
     * Creates a new line from two given points.
     *
     * @param start The starting point of the line
     * @param end   The ending point of the line
     */
// constructors
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Creates a new line from four given coordinates.
     *
     * @param x1 The x-coordinate of the starting point
     * @param y1 The y-coordinate of the starting point
     * @param x2 The x-coordinate of the ending point
     * @param y2 The y-coordinate of the ending point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Returns the length of the line.
     *
     * @return The length of the line
     */
    public double length() {
        return this.start.distance(end);
    }

    /**
     * Returns the middle point of the line.
     *
     * @return The middle point of the line
     */
// Returns the middle point of the line
    public Point middle() {
        double midX = Math.abs(this.start.getX() + this.end.getX()) / 2;
        double midY = Math.abs(this.start.getY() + this.end.getY()) / 2;
        return new Point(midX, midY);
    }

    /**
     * Returns the starting point of the line.
     *
     * @return The starting point of the line
     */
// Returns the start point of the line
    public Point start() {
        return this.start;
    }

    /**
     * Returns the ending point of the line.
     *
     * @return The ending point of the line
     */
// Returns the end point of the line
    public Point end() {
        return this.end;
    }

    /**
     * Determines if this line is parallel to another line.
     *
     * @param other The other line to compare to
     * @return True if the lines are parallel, false otherwise
     */
    public boolean isParallel(Line other) {
        return this.slope() == other.slope();
    }

    /**
     * Returns the slope of the line.
     *
     * @return The slope of the line
     */
    public double slope() {
        // Calculate the slope
        double yDifference = this.start.getY() - this.end.getY();
        double xDifference = this.start.getX() - this.end.getX();
        if (xDifference == 0) {
            return Double.POSITIVE_INFINITY;
        }
        return yDifference / xDifference;
    }

    /**
     * Returns the y-intercept of the line.
     *
     * @return The y-intercept of the line
     */
    public double yIntersecting() {
        //Calculate the y-intercepts
        return this.start.getY() - (this.slope() * this.start.getX());
    }

    /**
     * Determines if this line is in range of another line.
     *
     * @param other The other line to compare to
     * @return True if the lines are in range of each other, false otherwise
     */
    public boolean inRange(Line other) {
        double b1 = this.yIntersecting();
        double b2 = other.yIntersecting();
        // Calculate the x-coordinate of the intersecting point
        double xIntersect = (b2 - b1) / (this.slope() - other.slope());
        double yIntersected = this.slope() * xIntersect + b1;
        double xMin1 = Math.min(this.start.getX(), this.end.getX());
        double xMin2 = Math.min(other.start.getX(), other.end.getX());
        double xMax1 = Math.max(this.start.getX(), this.end.getX());
        double xMax2 = Math.max(other.start.getX(), other.end.getX());
        // if the line are parallel the check will base on the x values
        if (this.slope() == other.slope()) {
            return !(xMin1 > xMax2) && !(xMin2 > xMax1);
        }
        //Check if y-coordinate of intersecting point is within range of both lines
        //or if it's one of the adje points
        return ((xIntersect > xMin1) && (xIntersect < xMax1)
                && (xIntersect > xMin2) && (xIntersect < xMax2))
                || this.inLine(new Point(xIntersect, yIntersected))
                && other.inLine(new Point(xIntersect, yIntersected));
    }

    /**
     * In line boolean.
     *
     * @param p the point to check if she's on the line
     * @return true if in range else false
     */
    public boolean inLine(Point p) {
        double epsilon = 1e-10;
        double xMin = Math.min(this.start.getX(), this.end.getX());
        double xMax = Math.max(this.start.getX(), this.end.getX());
        double yMin = Math.min(this.start.getY(), this.end.getY());
        double yMax = Math.max(this.start.getY(), this.end.getY());
        double y = p.getY();
        double x = p.getX();
        //if is a vertical line check base on the x values
        if (this.slope() == Double.POSITIVE_INFINITY) {
            if (Math.abs(x - xMax) < epsilon) {
                return (p.getY() > yMin && p.getY() < yMax)
                        || ((p.equals(this.start) || (p.equals(this.end))));
            }
        } else {
            if ((Math.abs(y - this.slope() * x - this.yIntersecting()) < epsilon)) {
                return p.getX() > xMin && p.getX() < xMax
                        || ((p.equals(this.start) || (p.equals(this.end))));
            }
        }
        return false;
    }

    /**
     * Determines whether this line intersects another line.
     *
     * @param other the other line
     * @return true if the lines intersect, false otherwise
     */
// Returns true if the lines intersect, false otherwise
    public boolean isIntersecting(Line other) {
        double b1 = this.yIntersecting();
        double b2 = other.yIntersecting();
        //if both lines parallel to y
        if (this.slope() == Double.POSITIVE_INFINITY && other.slope() == Double.POSITIVE_INFINITY) {
            //if they are on the same x
            if (this.start().getX() == other.start.getX()) {
                double yMin1 = Math.min(this.start.getY(), this.end.getY());
                double yMin2 = Math.min(other.start.getY(), other.end.getY());
                double yMax1 = Math.max(this.start.getY(), this.end.getY());
                double yMax2 = Math.max(other.start.getY(), other.end.getY());
                // if they do not intersect
                return !(yMin2 > yMax1) && !(yMax2 < yMin1);
                //if they have different x value
            } else {
                return false;
            }
        }
        //if only one of the lines is parallel to y
        if (this.slope() == Double.POSITIVE_INFINITY || other.slope() == Double.POSITIVE_INFINITY) {
            // to check which one of them
            if (this.slope() == Double.POSITIVE_INFINITY) {
                //find the y value in of the line at the intersect point between the two lines
                double yIntersect = other.slope() * this.start.getX() + other.yIntersecting();
                Point intersect = new Point(this.start.getX(), yIntersect);
                //if the intersection point is in the range of the line
                return this.inLine(intersect) && other.inLine(intersect);
            } else {
                //if the intersection point is in the range of the line
                double yIntersect = this.slope() * other.start.getX() + this.yIntersecting();
                Point intersect = new Point(other.start.getX(), yIntersect);
                //if the intersection point is in the range of the line
                return other.inLine(intersect) && this.inLine(intersect);
            }
        }
        // in case of Parallel lines
        if (this.slope() == other.slope()) {
            // Lines are parallel and non-overlapping
            if (b1 != b2) {
                return false;
            } else {
                // Lines are collinear check overlapping
                return this.inRange(other);
            }
        }
        //if no any special cases check if the intersection point is in
        // the correct range
        return this.inRange(other);
    }


    /**
     * Calculates the intersection point of this line and another line.
     *
     * @param other the other line
     * @return the intersection point if the lines intersect, null otherwise
     */
// Returns the intersection point if the lines intersect,
    // and null otherwise.
    public Point intersectionWith(Line other) {
        double b1 = this.yIntersecting();
        double b2 = other.yIntersecting();
        if (this.isParallel(other)) {
            //if the line are parallel, but they have different y intersecting they will not meet
            if (b1 != b2) {
                return null;
            }
            //if the start point or the end point are in the range of the other line
            if (this.start.getX() > other.start.getX() && this.start.getX() < other.end.getX()
                    || this.start.getX() < other.start.getX() && this.start.getX() > other.end.getX()
                    || (this.end.getX() > other.start.getX() && this.end.getX() < other.end.getX()
                    || this.end.getX() < other.start.getX() && this.end.getX() > other.end.getX())) {
                return null;
            }
            if (other.start.getX() > this.start.getX() && other.start.getX() < this.end.getX()
                    || other.start.getX() < this.start.getX() && other.start.getX() > this.end.getX()
                    || other.end.getX() > this.start.getX() && other.end.getX() < this.end.getX()
                    || other.end.getX() < this.start.getX() && other.end.getX() > this.end.getX()) {
                return null;
            }
            if (this.start.equals(other.end)) {
                if (this.end.equals(other.start)) {
                    return null;
                }
                return this.start;
            }
            if (this.end.equals(other.start)) {
                return this.end;
            }
        }
        //if one of the line is parallel to y
        if (this.slope() == Double.POSITIVE_INFINITY || other.slope()
                == Double.POSITIVE_INFINITY) {
            //check if they have intersection point
            if (this.isIntersecting(other)) {
                if (this.slope() == Double.POSITIVE_INFINITY) {
                    double xValue = this.start.getX();
                    double yValue = other.slope() * xValue
                            + other.yIntersecting();
                    return new Point(xValue, yValue);
                } else {
                    double xValue = other.start.getX();
                    double yValue = this.slope() * xValue
                            + this.yIntersecting();
                    return new Point(xValue, yValue);
                }
            }
            return null;
        }
        //Calculate the x-coordinate of the intersecting point
        double xValue = (b2 - b1) / (this.slope() - other.slope());
        //Substitute x_intersect into this line equation to get y-coordinate
        double yValue = (this.slope() * xValue + b1);
        Point p = new Point(xValue, yValue);
        //check if the intersection point is in range of both lines
        if (this.inLine(p) && other.inLine(p)) {
            return p;
        } else {
            return null;
        }
    }

    /**
     * This method checks if this line is equal to another line.
     *
     * @param other the other line to compare to
     * @return true if the lines have the same start and end points, false otherwise
     */
// equals -- return true is the lines are equal, false otherwise
    public boolean equals(Line other) {
        return this.start.equals(other.start) && (this.end.equals(other.end))
                || (this.start.equals(other.end)
                && this.end.equals(other.start));
    }

    /**
     * The method checks witch point is the Closest intersection to start of the
     * trajectory of the centers ball.
     *
     * @param rect the rectangle to check for intersections
     * @return the closest intersection point to the start point of this line,
     * or null if there are no intersections.
     */

    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // Find all intersection points between the line and the rectangle

        List<Point> intersectionPoints = rect.intersectionPoints(this);
        if (intersectionPoints.isEmpty()) {
            return null;
        }
        // Initialize variables to keep track of the closest intersection point
        double closestDistance = this.start.distance(intersectionPoints.get(0));
        Point closestIntersection = intersectionPoints.get(0);
        // Initialize variables to keep track of the closest intersection point
        for (Point intersection : intersectionPoints) {
            double distance = intersection.distance(this.start);
            if (distance < closestDistance) {
                closestDistance = distance;
                closestIntersection = intersection;
            }
        }
        return closestIntersection;
    }

}