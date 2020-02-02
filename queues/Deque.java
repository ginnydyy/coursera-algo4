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
        first = new Node();
        last = new Node();
        n = 0;
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
            newItem.next = null;
            first.next = newItem;
            last.next = newItem;
        }
        else {
            Node oldFirstItem = first.next;
            newItem.next = oldFirstItem;
            oldFirstItem.previous = newItem;
            first.next = newItem;
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
            newItem.previous = null;
            first.next = newItem;
            last.next = newItem;
        }
        else {
            Node oldLastItem = last.next;
            newItem.previous = oldLastItem;
            oldLastItem.next = newItem;
            last.next = newItem;
        }
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Nothing to remove.");
        }
        Node oldFirstItem = first.next;
        first.next = oldFirstItem.next;
        if (oldFirstItem.next != null) {
            oldFirstItem.next.previous = null;
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
        Node oldLastItem = last.next;
        last.next = oldLastItem.previous;
        if (oldLastItem.previous != null) {
            oldLastItem.previous.next = null;
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
        private Node current = first.next;

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
        Deque<String> dequeFirst = new Deque<>();
        Deque<String> dequeLast = new Deque<>();
        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            dequeFirst.addFirst(s);
            dequeLast.addLast(s);
        }
        StdOut.println("dequeFirst size: " + dequeFirst.size());
        StdOut.println("iterate dequeFirst: ");
        for (String s : dequeFirst) {
            StdOut.println(s);
        }
        for (int i = 0; i < dequeFirst.size(); i++) {
            String r = dequeFirst.removeFirst();
            StdOut.println("removeFirst: " + r);
            StdOut.println("current size: " + dequeFirst.size());
            StdOut.println("print current: ");
            for (String s : dequeFirst) {
                StdOut.print(s);
            }
            StdOut.println();
        }

        StdOut.println("dequeLast size: " + dequeLast.size());
        StdOut.println("iterate dequeLast: ");
        for (String s : dequeLast) {
            StdOut.println(s);
        }
        for (int i = 0; i < dequeLast.size(); i++) {
            String r = dequeLast.removeLast();
            StdOut.println("removeLast: " + r);
            StdOut.println("current size: " + dequeLast.size());
            StdOut.println("print current: ");
            for (String s : dequeLast) {
                StdOut.print(s);
            }
            StdOut.println();
        }
    }
}
