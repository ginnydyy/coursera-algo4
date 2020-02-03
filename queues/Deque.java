/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int n;

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("The input item for addFirst(Item item) is null.");
        }
        Node newItem = new Node();
        newItem.item = item;
        if (isEmpty()) {
            first = newItem;
            last = newItem;
        }
        else {
            Node oldFirstItem = first;
            newItem.next = oldFirstItem;
            oldFirstItem.previous = newItem;
            first = newItem;
        }
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("The input item for addLast(Item item) is null.");
        }
        Node newItem = new Node();
        newItem.item = item;
        if (isEmpty()) {
            first = newItem;
            last = newItem;
        }
        else {
            Node oldLastItem = last;
            newItem.previous = oldLastItem;
            oldLastItem.next = newItem;
            last = newItem;
        }
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Nothing to remove.");
        }
        Node oldFirstItem = first;
        first = oldFirstItem.next;
        if (oldFirstItem.next != null) {
            oldFirstItem.next.previous = null;
        }
        else { // removing the last item in the deque, need to set last to null
            last = null;
        }
        oldFirstItem.next = null;
        n--;
        return oldFirstItem.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Nothing to remove.");
        }
        Node oldLastItem = last;
        last = oldLastItem.previous;
        if (oldLastItem.previous != null) {
            oldLastItem.previous.next = null;
        }
        else { // removing the last item in the deque, need to set first to null
            first = null;
        }
        oldLastItem.previous = null;
        n--;
        return oldLastItem.item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Nothing to return.");
            }
            Item currentItem = current.item;
            current = current.next;
            return currentItem;
        }

        public void remove() {
            throw new UnsupportedOperationException("remove() is not supported.");
        }
    }

    // inner class for doubly linked list implementation
    private class Node {
        Item item;
        Node next;
        Node previous;
    }

    // unit testing (required)
    public static void main(String[] args) {
        testConstructor();
        testAddFirst(args);
        testAddLast(args);
        testRemoveFirst(args);
        testRemoveLast(args);
        testIteratorNextException();
        testIteratorRemoveException();
        testAddFirstException();
        testAddLastException();
        testRemoveFirsttException();
        testRemoveLastException();
    }

    private static void printArgs(String[] args) {
        StdOut.println("print args:");
        for (int i = 0; i < args.length; i++) {
            StdOut.print(args[i]);
            StdOut.print(" ");
        }
        StdOut.println();
    }

    private static void printCurrentDeque(Deque<String> deque) {
        StdOut.println("print current: ");
        for (String s : deque) {
            StdOut.print(s);
            StdOut.print(" ");
        }
        StdOut.println();
    }

    private static void testConstructor() {
        StdOut.println("====testConstructor====");
        Deque<String> deque = new Deque<>();
        StdOut.println("Should be empty: " + deque.isEmpty());
    }

    private static void testAddFirst(String[] args) {
        StdOut.println("====testAddFirst====");
        printArgs(args);
        Deque<String> dequeFirst = new Deque<>();
        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            dequeFirst.addFirst(s);
        }
        StdOut.println("dequeFirst size: " + dequeFirst.size());
        StdOut.println("iterate dequeFirst: ");
        for (String s : dequeFirst) {
            StdOut.println(s);
        }
    }

    private static void testAddLast(String[] args) {
        StdOut.println("====testAddLast====");
        printArgs(args);
        Deque<String> dequeLast = new Deque<>();
        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            dequeLast.addLast(s);
        }
        StdOut.println("dequeLast size: " + dequeLast.size());
        StdOut.println("iterate dequeLast: ");
        for (String s : dequeLast) {
            StdOut.println(s);
        }
    }

    private static void testRemoveFirst(String[] args) {
        StdOut.println("====testRemoveFirst====");
        printArgs(args);
        Deque<String> dequeFirst = new Deque<>();
        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            dequeFirst.addFirst(s);
        }
        StdOut.println("dequeFirst size: " + dequeFirst.size());
        StdOut.println("iterate dequeFirst: ");
        for (String s : dequeFirst) {
            StdOut.println(s);
        }
        while (!dequeFirst.isEmpty()) {
            String r = dequeFirst.removeFirst();
            StdOut.println("removeFirst: " + r);
            StdOut.println("current size: " + dequeFirst.size());
            printCurrentDeque(dequeFirst);
            StdOut.println();
        }
    }

    private static void testRemoveLast(String[] args) {
        StdOut.println("====testRemoveLast====");
        printArgs(args);
        Deque<String> dequeFirst = new Deque<>();
        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            dequeFirst.addFirst(s);
        }
        StdOut.println("dequeFirst size: " + dequeFirst.size());
        StdOut.println("iterate dequeFirst: ");
        for (String s : dequeFirst) {
            StdOut.println(s);
        }
        while (!dequeFirst.isEmpty()) {
            String r = dequeFirst.removeLast();
            StdOut.println("removeLast: " + r);
            StdOut.println("current size: " + dequeFirst.size());
            printCurrentDeque(dequeFirst);
            StdOut.println();
        }
    }

    private static void testIteratorNextException() {
        StdOut.println("====testIteratorNextException====");

        Deque<String> deque = new Deque<>();
        try {
            deque.iterator().next();
        }
        catch (NoSuchElementException e) {
            StdOut.println("Catch iterator next() exception: " + e.getMessage());
        }
    }

    private static void testIteratorRemoveException() {
        StdOut.println("====testIteratorRemoveException====");

        Deque<String> deque = new Deque<>();
        try {
            deque.iterator().remove();
        }
        catch (UnsupportedOperationException e) {
            StdOut.println("Catch iterator remove() exception: " + e.getMessage());
        }
    }

    private static void testAddFirstException() {
        StdOut.println("====testAddFirstException====");

        Deque<String> deque = new Deque<>();
        try {
            deque.addFirst(null);
        }
        catch (IllegalArgumentException e) {
            StdOut.println("Catch addFirst() exception: " + e.getMessage());
        }
    }

    private static void testAddLastException() {
        StdOut.println("====testAddLastException====");

        Deque<String> deque = new Deque<>();
        try {
            deque.addLast(null);
        }
        catch (IllegalArgumentException e) {
            StdOut.println("Catch addLast() exception: " + e.getMessage());
        }
    }

    private static void testRemoveFirsttException() {
        StdOut.println("====testRemoveFirsttException====");

        Deque<String> deque = new Deque<>();
        try {
            deque.removeFirst();
        }
        catch (NoSuchElementException e) {
            StdOut.println("Catch removeFirst() exception: " + e.getMessage());
        }
    }

    private static void testRemoveLastException() {
        StdOut.println("====testRemoveLastException====");

        Deque<String> deque = new Deque<>();
        try {
            deque.removeLast();
        }
        catch (NoSuchElementException e) {
            StdOut.println("Catch removeLast() exception: " + e.getMessage());
        }
    }
}
