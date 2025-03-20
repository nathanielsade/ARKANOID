package geometry;

import java.util.ArrayList;
import java.util.List;
/**
 * The Rectangle class represents a rectangle in a two-dimensional space,
 * defined by its upper-left point, width and height.
 */
public class Rectangle {
    private final Point upperLeft;
    private final double width;
    private final double height;
    /**
     * Create a new rectangle with the specified location and size.
     *
     * @param upperLeft the upper-left point of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }
    /**
     * Returns a (possibly empty) list of intersection points between
     * this rectangle and a specified line.
     *
     * @param line the line to check for intersection with this rectangle
     * @return a list of intersection points between this rectangle and the line
     */
    public List<Point> intersectionPoints(Line line) {
        //create a new list
        List<Point> intersectionPoints = new ArrayList<>();
        List<Line> rectangleLines = new ArrayList<>();
        rectangleLines.add(this.getLeftLine());
        rectangleLines.add(this.getRightLine());
        rectangleLines.add(this.getUpperLine());
        rectangleLines.add(this.getLowerLine());
        // loop through the rectangles lines
        for (Line value : rectangleLines) {
            //check if the collision point is on the specific line
            boolean x = line.isIntersecting(value);
            if (x) {
                if (line.intersectionWith(value) != null) {
                    //add it to the intersection point to the list
                    intersectionPoints.add(line.intersectionWith(value));
                }
            }
        }
        return intersectionPoints;
    }
    /**
     * Returns the width of this rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * Returns the height of this rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }
    /**
     * Returns the upper-left point of this rectangle.
     *
     * @return the upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
    /**
     * Returns the upper-right point of this rectangle.
     *
     * @return the upper-right point of the rectangle
     */
    public Point getUpperRight() {
        return new Point(upperLeft.getX() + width, upperLeft.getY());
    }
    /**
     * Returns the lower-right point of this rectangle.
     *
     * @return the lower-right point of the rectangle
     */
    public Point getLowerRight() {
        return new Point(upperLeft.getX() + width, this.upperLeft.getY() - this.height);
    }
    /**
     * Returns the lower-left point of this rectangle.
     *
     * @return the lower-left point of the rectangle
     */
    public Point getLowerLeft() {
        return new Point(this.upperLeft.getX(), this.upperLeft.getY() - this.height);
    }
    /**
     * Returns the  upper Line of this rectangle.
     *
     * @return the upper-Line  of the rectangle
     */
    public Line getUpperLine() {
        return new Line(upperLeft, this.getUpperRight());
    }
    /**
     * Returns the  left Line of this rectangle.
     *
     * @return the left-Line  of the rectangle
     */
    public Line getLeftLine() {
       return new Line(this.upperLeft, this.getLowerLeft());
    }
    /**
     * Returns the  right Line of this rectangle.
     *
     * @return the right-Line  of the rectangle
     */
    public Line getRightLine() {
        return  new Line(this.getUpperRight(), this.getLowerRight());
    }
    /**
     * Returns the  lower Line of this rectangle.
     *
     * @return the lower-Line  of the rectangle
     */
    public Line getLowerLine() {
        return  new Line(this.getLowerLeft(), this.getLowerRight());
    }
}