/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param x the <em>x</em>-coordinate of the point
     * @param y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        if (that == null) {
            throw new IllegalArgumentException("The input point is null.");
        }
        int dy = that.y - this.y;
        int dx = that.x - this.x;
        if (dy == 0) {
            if (dx == 0) {
                return Double.NEGATIVE_INFINITY; // degenerate line
            }
            return 0.0; // horizontal line
        }
        else if (dx == 0) {
            return Double.POSITIVE_INFINITY; // vertical line
        }
        else {
            return ((double) dy) / dx;
        }
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     * point (x0 = x1 and y0 = y1);
     * a negative integer if this point is less than the argument
     * point; and a positive integer if this point is greater than the
     * argument point
     */
    public int compareTo(Point that) {
        if (that == null) {
            throw new IllegalArgumentException("The input point is null.");
        }
        if (this.y < that.y) {
            return -1;
        }
        else if (this.y == that.y) {
            if (this.x < that.x) {
                return -1;
            }
            else if (this.x == that.x) {
                return 0;
            }
        }
        return 1;
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        return new Comparator<Point>() {
            public int compare(Point o1, Point o2) {
                return Double.compare(slopeTo(o1), slopeTo(o2));
            }
        };
    }

    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        // test slopeTo method
        testSlopeToWithPositiveSlope();
        testSlopeToWithNegativeSlope();
        testSlopeToWithHorizontalLine();
        testSlopeToWithVerticalLine();
        testSlopeToWithDegenerateLine();
        testSlopeToWithOrigin();
        testSlopeToWithNull();
        // test compareTo method
        testCompareToWithNull();
        testCompareToReturnsEqual();
        testCompareToReturnsPositive();
        testCompareToReturnsNegative();
    }

    //================TESTs======================
    private static void testSlopeToWithPositiveSlope() {
        Point p1 = new Point(3, 3);
        Point p2 = new Point(5, 7);
        double s1 = p1.slopeTo(p2);
        assert (s1 == 2.0);
        double s2 = p2.slopeTo(p1);
        assert (s2 == 2.0);
    }

    private static void testSlopeToWithNegativeSlope() {
        Point p1 = new Point(3, 3);
        Point p2 = new Point(2, 7);
        double s1 = p1.slopeTo(p2);
        assert (s1 == -4.0);
        double s2 = p2.slopeTo(p1);
        assert (s2 == -4.0);
    }

    private static void testSlopeToWithHorizontalLine() {
        Point p1 = new Point(3, 3);
        Point p2 = new Point(5, 3);
        double s1 = p1.slopeTo(p2);
        assert (s1 == 0.0);
        double s2 = p2.slopeTo(p1);
        assert (s2 == 0.0);
    }

    private static void testSlopeToWithVerticalLine() {
        Point p1 = new Point(3, 3);
        Point p2 = new Point(3, 5);
        double s1 = p1.slopeTo(p2);
        assert (s1 == Double.POSITIVE_INFINITY);
        double s2 = p2.slopeTo(p1);
        assert (s2 == Double.POSITIVE_INFINITY);
    }

    private static void testSlopeToWithDegenerateLine() {
        Point p1 = new Point(3, 3);
        Point p2 = new Point(3, 3);
        double s1 = p1.slopeTo(p2);
        assert (s1 == Double.NEGATIVE_INFINITY);
        double s2 = p2.slopeTo(p1);
        assert (s2 == Double.NEGATIVE_INFINITY);
    }

    private static void testSlopeToWithOrigin() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 32767);
        Point p3 = new Point(32767, 0);
        Point p4 = new Point(32767, 32767);

        assert ((p1.slopeTo(p1) == Double.NEGATIVE_INFINITY)); // degenerate line
        assert ((p1.slopeTo(p2) == Double.POSITIVE_INFINITY)); // vetical line
        assert ((p1.slopeTo(p3) == 0.0)); // horizontal line
        assert ((p1.slopeTo(p4) == 1.0));
    }

    private static void testSlopeToWithNull() {
        try {
            Point p1 = new Point(0, 0);
            p1.slopeTo(null);
        }
        catch (IllegalArgumentException e) {
            assert (e.getMessage().equals("The input point is null."));
        }
    }

    private static void testCompareToReturnsEqual() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 1);
        assert (p1.compareTo(p2) == 0);
    }

    private static void testCompareToReturnsPositive() {
        Point p1 = new Point(3, 5);
        Point p2 = new Point(3, 4);
        Point p3 = new Point(2, 4);
        Point p4 = new Point(4, 4);
        Point p5 = new Point(2, 5);

        assert (p1.compareTo(p2) > 0);
        assert (p1.compareTo(p3) > 0);
        assert (p1.compareTo(p4) > 0);
        assert (p1.compareTo(p5) > 0);
    }

    private static void testCompareToReturnsNegative() {
        Point p1 = new Point(3, 3);
        Point p2 = new Point(3, 4);
        Point p3 = new Point(2, 4);
        Point p4 = new Point(4, 4);
        Point p5 = new Point(4, 3);
        assert (p1.compareTo(p2) < 0);
        assert (p1.compareTo(p3) < 0);
        assert (p1.compareTo(p4) < 0);
        assert (p1.compareTo(p5) < 0);
    }

    private static void testCompareToWithNull() {
        try {
            Point p1 = new Point(0, 0);
            int i = p1.compareTo(null);
        }
        catch (IllegalArgumentException e) {
            assert (e.getMessage().equals("The input point is null."));
        }
    }

}
