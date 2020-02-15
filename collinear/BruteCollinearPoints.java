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

public class BruteCollinearPoints {

    private List<LineSegment> lineSegmentList = new ArrayList<>();

    /**
     * Finds all line segments containing 4 points
     *
     * @param points all the points in the plane
     */
    public BruteCollinearPoints(Point[] points) {
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
        Arrays.sort(copy);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double s1 = copy[i].slopeTo(copy[j]);
                for (int k = j + 1; k < n; k++) {
                    double s2 = copy[j].slopeTo(copy[k]);
                    if (s1 == s2) {
                        for (int p = k + 1; p < n; p++) {
                            double s3 = copy[k].slopeTo(copy[p]);
                            if (s2 == s3) {
                                LineSegment lineSegment = new LineSegment(copy[i], copy[p]);
                                lineSegmentList.add(lineSegment);
                            }
                        }
                    }

                }
            }
        }
    }

    /**
     * The number of line segments
     *
     * @return the number of line segments
     */
    public int numberOfSegments() {
        return lineSegmentList.size();
    }

    /**
     * The line segments
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

    // For simplicity, we will not supply any input to BruteCollinearPoints that has 5 or more collinear points.
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
