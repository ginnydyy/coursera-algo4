/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {

    private List<LineSegment> lineSegmentList = new ArrayList<>();

    /**
     * Finds all line segments containing 4 or more points
     *
     * @param points the input points
     */
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("The input points is null.");
        }
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException(
                        "The input points[" + i + "] is null");
            }
            if (i < points.length - 1) {
                for (int j = i + 1; j < points.length; j++) {
                    if (points[j] == null) {
                        throw new IllegalArgumentException(
                                "The input points[" + j + "] is null");
                    }
                    if (points[i].compareTo(points[j]) == 0) {
                        throw new IllegalArgumentException(
                                "The input points[" + i + "] and points [" + j + "] are identical: "
                                        + points[i]);
                    }
                }
            }
        }

        int n = points.length;
        if (n < 4) {
            return;
        }

        Point[] copy = Arrays.copyOf(points, points.length);
        for (Point origin : points) {
            Arrays.sort(copy);
            Arrays.sort(copy, origin.slopeOrder());
            double slope = origin.slopeTo(copy[1]);
            int numberOfSameSlopePoints = 1;
            for (int i = 2; i < copy.length; i++) {
                if (Double.compare(origin.slopeTo(copy[i]), slope) == 0) {
                    numberOfSameSlopePoints++;
                }
                else {
                    if (numberOfSameSlopePoints >= 3) {
                        if (isSmallest(copy, origin, i - numberOfSameSlopePoints, i)) {
                            lineSegmentList.add(new LineSegment(origin, copy[i - 1]));
                        }
                    }
                    slope = origin.slopeTo(copy[i]);
                    numberOfSameSlopePoints = 1;
                }
            }
            if (numberOfSameSlopePoints >= 3) {
                if (isSmallest(copy, origin, copy.length - numberOfSameSlopePoints,
                               copy.length)) {
                    lineSegmentList.add(new LineSegment(origin, copy[copy.length - 1]));
                }
            }
        }
    }

    /**
     * Sort the points from the index {@code fromIndex}  to the index {@code toIndex}.
     * And compare the point to the smallest point to chec whether the point is the smallest one
     * among them.
     *
     * @param points    all the points in an array
     * @param point     the point to check whethere is the smallest
     * @param fromIndex the start index of the array to sort
     * @param toIndex   the end index of the array to sort
     * @return true if the point at index is the smallest, otherwise, false.
     */
    private boolean isSmallest(Point[] points, Point point, int fromIndex, int toIndex) {
        Point[] copy = Arrays.copyOf(points, points.length);
        Arrays.sort(copy, fromIndex, toIndex);
        return (point.compareTo(copy[fromIndex]) < 0);
    }

    /**
     * Returns the number of line segments
     *
     * @return the number of line segments
     */
    public int numberOfSegments() {
        return lineSegmentList.size();
    }

    /**
     * Returns all the line segments
     *
     * @return all the line segments
     */
    public LineSegment[] segments() {
        LineSegment[] result = new LineSegment[lineSegmentList.size()];
        for (int i = 0; i < lineSegmentList.size(); i++) {
            result[i] = lineSegmentList.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            String s = in.readString();
            if ("null".equals(s)) {
                points[i] = null;
            }
            else {
                int x = Integer.parseInt(s);
                int y = in.readInt();
                points[i] = new Point(x, y);
            }
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            if (p != null) {
                p.draw();
            }
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
