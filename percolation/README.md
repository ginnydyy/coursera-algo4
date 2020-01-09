## Week 1 ##
### Percolation ###

This is the programming assignment for Cousera Algorithms week 1.

- Assignment specification: https://coursera.cs.princeton.edu/algs4/assignments/percolation/specification.php
- Assignment grader output (100/100 score passed): https://github.com/ginnydyy/coursera-algo4/blob/percolation-readme/percolation/grader-output

- How to:
  - set up the development environment (on Mac): https://lift.cs.princeton.edu/java/mac/
  - download the zip package: http://coursera.cs.princeton.edu/algs4/testing/percolation.zip
  - unzip the package and open it in the IDE
  - copy the https://github.com/ginnydyy/coursera-algo4/blob/master/percolation/Percolation.java and https://github.com/ginnydyy/coursera-algo4/blob/master/percolation/PercolationStats.java into the project (under percolation folder)
  - run the main method in the each class to test the implementation.

- Caveat:
  - Percolation.java
    - How to check the scope of the adjacent nodes.
    - How to reduce the number of calling union find class methods (use connected() instead of find()).
    - How to deal with backwash (isFull() be cautious on the nodes of the bottom row), use two union find instances to check, one with virtual top and bottom node, the other one with only virtual top node.

  - PercolationStats.java
    - Check the definition of the experiment, new a union find instance for each experiment.
    - Check the definition of mean and stddev, store the fraction of numOpenedSite/numTotalSite for calculation.
    - How to reduce the number of calling StdStats and StdRandom methods (store the returned values in memory).
