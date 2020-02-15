## Week 3 ##
### Collinear ###

This is the programming assignment for Cousera Algorithms week 3.

- Assignment specification: https://coursera.cs.princeton.edu/algs4/assignments/collinear/specification.php
- Assignment grader output (100/100 score passed): https://github.com/ginnydyy/coursera-algo4/blob/master/collinear/grader-output

- How to:
  - set up the development environment (on Mac): https://lift.cs.princeton.edu/java/mac/
  - download the zip package: https://coursera.cs.princeton.edu/algs4/assignments/collinear/collinear.zip
  - unzip the package and open it in the IDE
  - copy the java files (under queues folder) into the project
  - run the main method in each class to test the implementation.

- Run/Debug Configuration:
  - to enable the assertion for the unit tests, specify `-ea` in the `VM options`.
  - to specify the input text file for BruteCollinearPoints.java and FastCollinearPoints, set the path of the file as the `Programme arguements`.

- Caveat:
  - Point.java
    - Do not handle and throw other exception when the argument of slopeTo() compareTo() and slopeOrder().compare() is null. Let it throw NPE.
    - Avoid returning -1 or 1 in the compareTo() method. And the cosumers of compreTo() should avoid checking the result by -1 or 1 (https://www.coursera.org/learn/algorithms-part1/discussions/weeks/3/threads/4onlqgjaEeepAAp3RBDGpA).

  - BruteCollinearPoints.java
    - Do null check and duplicate check first, before doing other logic.
    - Check that data type does not mutate the constructor argument (Make a copy of the input points).

  - FastCollinearPoints.java
    - Re-sort the points by natural order in each iteration, to make sure the points are in correct order after being processed in previous iteration.
    - Avoid extra line segments, check the origin is the smallest point among the points with same slope before adding a new line segment (https://www.coursera.org/learn/algorithms-part1/discussions/weeks/3/threads/YL_ngYEeEearKBKQJr-skQ).
