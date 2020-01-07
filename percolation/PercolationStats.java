/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    // the array of the number of sites opened when the system percolates for each trial
    private double[] fractions;
    private final int trials;
    private double mean = -1.0d;
    private double stddev = -1.0d;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException(
                    "Both of the input n and trials must be greater than 0.");
        }
        this.trials = trials;
        fractions = new double[trials];
        runExperiment(n, trials);
    }

    private void runExperiment(int n, int t) {
        for (int i = 0; i < t; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                // find a random blocked site and open it
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                while (percolation.isOpen(row, col)) {
                    row = StdRandom.uniform(1, n + 1);
                    col = StdRandom.uniform(1, n + 1);
                }
                percolation.open(row, col);
            }
            fractions[i] = (double) percolation.numberOfOpenSites() / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        if (mean < 0.0d) {
            mean = StdStats.mean(fractions);
        }
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        if (stddev < 0.0d) {
            stddev = StdStats.stddev(fractions);
        }
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (CONFIDENCE_95 * stddev()) / Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (CONFIDENCE_95 * stddev()) / Math.sqrt(trials);
    }

    // test client (see below)
    public static void main(String[] args) {
        if (args.length >= 2) {
            int n = Integer.parseInt(args[0]);
            int trials = Integer.parseInt(args[1]);
            PercolationStats ps = new PercolationStats(n, trials);
            String format = "%-23.23s %-3.3s %-50.50s%n";
            System.out.printf(format, "mean", " = ", ps.mean());
            System.out.printf(format, "stddev", " = ", ps.stddev());
            System.out.printf(format, "95% confidence interval", " = ",
                              "[" + ps.confidenceLo() + "," + ps.confidenceHi() + "]");
        }
    }
}
