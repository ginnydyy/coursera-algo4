/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class BruteCollinearPoints {

    private LineSegment[] lineSegments;
    private int numberOfSegments;

    /**
     * Finds all line segments containing 4 points
     *
     * @param points all the points in the plane
     */
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("The input points is null.");
        }
        int n = points.length;
        if (n < 4) {
            lineSegments = new LineSegment[0];
            return;
        }
        int combinationSize = factorial(n) / factorial(4) * factorial(n - 4);
        lineSegments = new LineSegment[combinationSize];

        Arrays.sort(points);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double s1 = points[i].slopeTo(points[j]);
                if (s1 == Double.NEGATIVE_INFINITY) {
                    throw new IllegalArgumentException(
                            "The input points contains a repeated point: " + points[i] + " and "
                                    + points[j]);
                }
                for (int k = j + 1; k < n; k++) {
                    double s2 = points[j].slopeTo(points[k]);
                    if (s2 == Double.NEGATIVE_INFINITY) {
                        throw new IllegalArgumentException(
                                "The input points contains a repeated point: " + points[j] + " and "
                                        + points[k]);
                    }
                    if (s1 == s2) {
                        for (int p = k + 1; p < n; p++) {
                            double s3 = points[k].slopeTo(points[p]);
                            if (s3 == Double.NEGATIVE_INFINITY) {
                                throw new IllegalArgumentException(
                                        "The input points contains a repeated point: " + points[k]
                                                + " and "
                                                + points[p]);
                            }
                            if (s2 == s3) {
                                LineSegment lineSegment = new LineSegment(points[i], points[p]);
                                lineSegments[numberOfSegments++] = lineSegment;
                            }
                        }
                    }

                }
            }
        }
        lineSegments = Arrays.copyOf(lineSegments, numberOfSegments);
    }

    /**
     * The number of line segments
     *
     * @return the number of line segments
     */
    public int numberOfSegments() {
        return numberOfSegments;
    }

    /**
     * The line segments
     *
     * @return all the line segments
     */
    public LineSegment[] segments() {
        return lineSegments;
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

    // For simplicity, we will not supply any input to BruteCollinearPoints that has 5 or more collinear points.
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (int i = 0; i < collinear.numberOfSegments; i++) {
            LineSegment segment = collinear.lineSegments[i];
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
