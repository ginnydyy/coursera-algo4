## Week 2 ##
### Queues ###

This is the programming assignment for Cousera Algorithms week 2.

- Assignment specification: https://coursera.cs.princeton.edu/algs4/assignments/queues/specification.php
- Assignment grader output (100/100 score passed): https://github.com/ginnydyy/coursera-algo4/blob/master/queues/grader-output

- How to:
  - set up the development environment (on Mac): https://lift.cs.princeton.edu/java/mac/
  - download the zip package: https://coursera.cs.princeton.edu/algs4/assignments/queues/queues.zip
  - unzip the package and open it in the IDE
  - copy the java files (under queues folder) into the project 
  - run the main method in each class to test the implementation.
  
- Run/Debug Configuration:  
  - to enable the assertion for the unit tests, specify `-ea` in the `VM options`.
  - to specify the k for Permutation.java, set k as the `Programme arguements`.
  - to specify the input text file for Permutation.java, tick the box infront of `Redirect input from`, and specify the path of the text file.

- Caveat:
  - RandomizedQueue.java
    - How to dequeue item without rearragning the whole array (After removing the random item, swap the last item with it).
    
  - Permutation.java
    - How to use k sized RandomizedQueue rathan than n sized (https://en.wikipedia.org/wiki/Reservoir_sampling).
    - Handle when k == 0.
