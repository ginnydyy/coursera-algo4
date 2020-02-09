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
        int n = points.length;
        if (n < 4) {
            return;
        }

        Arrays.sort(points);
        Point[] copy = Arrays.copyOf(points, points.length);
        for (Point origin : points) {
            Arrays.sort(copy, origin.slopeOrder());
            double slope = origin.slopeTo(copy[1]);
            int numberOfSameSlopePoints = 1;
            for (int i = 2; i < copy.length; i++) {
                if (Double.compare(origin.slopeTo(copy[i]), slope) == 0) {
                    numberOfSameSlopePoints++;
                }
                else {
                    if (numberOfSameSlopePoints >= 3) {
                        lineSegmentList.add(new LineSegment(origin, copy[i - 1]));
                    }
                    slope = origin.slopeTo(copy[i]);
                    numberOfSameSlopePoints = 1;
                }
            }
            if (numberOfSameSlopePoints >= 3) {
                lineSegmentList.add(new LineSegment(origin, copy[copy.length - 1]));
            }
        }
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

    private int factorial(int n) {
        if (n == 0) {
            return 1;
        }
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result = result * i;
        }
        return result;
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
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
