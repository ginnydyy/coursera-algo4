/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // uf with virtual top and bottom
    private final WeightedQuickUnionUF uf;
    // uf only with virtual top, for checking backwash (https://chaoli.blog/2018/10/09/percolation/)
    private final WeightedQuickUnionUF uf2;
    private final int numSitePerRow;
    private final int indexVirtualTopSite;
    private final int indexVirtualBottomSite;

    /**
     * From sites[0] to sites[n*n-1] are actual sites.
     * <p>
     * sites[n*n] is virtual top site, sites[n*n+1] is virtual bottom site.
     * <p>
     * True indicates the site is open, false indicates the site is full.
     */
    private boolean[] sites;

    private int numOpenSites = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException(
                    "The input n for the n-by-n grid should be greater than 0.");
        }
        numSitePerRow = n;
        uf = new WeightedQuickUnionUF(n * n + 2);
        uf2 = new WeightedQuickUnionUF(n * n + 1);
        sites = new boolean[n * n + 2];
        for (int i = 0; i < n * n; i++) {
            sites[i] = false;
        }
        indexVirtualTopSite = n * n;
        indexVirtualBottomSite = n * n + 1;
        sites[indexVirtualTopSite] = true; // virtual top site is open
        sites[indexVirtualBottomSite] = true; // virtual bottom site is open
    }

    // convert the row and col of the grid to the index of the array
    private int convertToIndex(int row, int col) {
        if (!isInsideOfRange(row, col)) {
            throw new IllegalArgumentException(
                    "The input (" + row + "," + col + ") is outside its prescribed range.");
        }
        int index = (row - 1) * numSitePerRow + col - 1;
        if (!isInsideOfRange(index)) {
            throw new IllegalArgumentException(
                    "The input (" + row + "," + col + ") is outside its prescribed range.");
        }
        return index;
    }

    private boolean isInsideOfRange(int row, int col) {
        return (row >= 1 && row <= numSitePerRow && col >= 1 && col <= numSitePerRow);
    }

    private boolean isInsideOfRange(int index) {
        return index > -1 && index < numSitePerRow * numSitePerRow;
    }

    private void connectToTop(int index) {
        int indexTopSite = index - numSitePerRow;
        if (isInsideOfRange(indexTopSite) && sites[indexTopSite]) {
            uf.union(index, indexTopSite);
            uf2.union(index, indexTopSite);
        }
    }

    private void connectToBottom(int index) {
        int indexBottomSite = index + numSitePerRow;
        if (isInsideOfRange(indexBottomSite) && sites[indexBottomSite]) {
            uf.union(index, indexBottomSite);
            uf2.union(index, indexBottomSite);
        }
    }

    private void connectToLeft(int index) {
        int indexLeftSite = index - 1;
        if (isInsideOfRange(indexLeftSite) && sites[indexLeftSite]) {
            uf.union(index, indexLeftSite);
            uf2.union(index, indexLeftSite);
        }
    }

    private void connectToRight(int index) {
        int indexRightSite = index + 1;
        if (isInsideOfRange(indexRightSite) && sites[indexRightSite]) {
            uf.union(index, indexRightSite);
            uf2.union(index, indexRightSite);
        }
    }

    private void connectToAdjacentOpenSites(int row, int col, int index) {
        if (row != 1) {
            connectToTop(index);
        }
        if (row != numSitePerRow) {
            connectToBottom(index);
        }
        if (col != 1) {
            connectToLeft(index);
        }
        if (col != numSitePerRow) {
            connectToRight(index);
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        int index = convertToIndex(row, col);
        if (!sites[index]) {
            sites[index] = true;
            numOpenSites++;
            connectToAdjacentOpenSites(row, col, index);
            if (row == 1) {
                uf.union(index, indexVirtualTopSite);
                uf2.union(index, indexVirtualTopSite);
            }
            // if it percolates, connect to virtual bottom will make the site also connect to the virtual top
            if (row == numSitePerRow) {
                uf.union(index, indexVirtualBottomSite);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        int index = convertToIndex(row, col);
        return sites[index];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        int index = convertToIndex(row, col);
        // check the second uf to avoid backwash
        return uf.connected(indexVirtualTopSite, index)
                && uf2.connected(indexVirtualTopSite, index);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(indexVirtualTopSite, indexVirtualBottomSite);
    }

    // test client (optional)
    public static void main(String[] args) {
        // test against input txt files
        int[] inputs = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            inputs[i] = Integer.parseInt(args[i]);
            // System.out.println(inputs[i]);
        }
        Percolation p = new Percolation(inputs[0]);

        System.out.println("1,1 is open?" + p.isOpen(1, 1));
        System.out.println("1,1 is full?" + p.isFull(1, 1));

        for (int i = 1; i < inputs.length; i += 2) {
            int row = inputs[i];
            int col = inputs[i + 1];
            System.out.println("open" + row + " " + col);
            p.open(row, col);
            System.out.println("percolates?" + p.percolates());
            System.out.println("isFull?" + p.isFull(row, col));
            if (row == 7 && col == 5) {
                p.isFull(6, 1);
            }
        }

    }
}
