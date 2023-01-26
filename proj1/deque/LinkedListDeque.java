package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private final Node sentinel;
    private int size;

    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    @Override
    public void addFirst(T x) {
        Node oldFirst = sentinel.next;
        sentinel.next = new Node(sentinel, x, oldFirst);
        oldFirst.prev = sentinel.next;
        size += 1;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        Node first = sentinel.next;
        sentinel.next = first.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return first.item;
    }

    @Override
    public void addLast(T x) {
        Node oldLast = sentinel.prev;
        sentinel.prev = new Node(oldLast, x, sentinel);
        oldLast.next = sentinel.prev;
        size += 1;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node last = sentinel.prev;
        sentinel.prev = last.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return last.item;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
        if (index > size / 2) {
            for (Node p = sentinel.prev; p != sentinel; p = p.prev) {
                if (index == size - 1) {
                    return p.item;
                }
                index += 1;
            }
        } else {
            for (Node p = sentinel.next; p != sentinel; p = p.next) {
                if (index == 0) {
                    return p.item;
                }
                index -= 1;
            }
        }
        return null;
    }

    private T getRecursiveHelper(int index, Node lst, int start) {
        if (lst == sentinel) {
            return null;
        } else if (start == index) {
            return lst.item;
        } else {
            return getRecursiveHelper(index, lst.next, start + 1);
        }
    }

    public T getRecursive(int index) {
        return getRecursiveHelper(index, sentinel.next, 0);
    }

    @Override
    public void printDeque() {
        for (Node p = sentinel.next; p != sentinel; p = p.next) {
            System.out.print(p.item + " ");
        }
    }

    public boolean equals(Object o) {
        if (o instanceof Deque) {
            Deque<T> other = (Deque<T>) o;
            if (this.size == other.size()) {
                int index = 0;
                for (Node p = sentinel.next; p != sentinel; p = p.next) {
                    if (!p.item.equals(other.get(index))) {
                        return false;
                    }
                    index += 1;
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class Node {
        Node prev;
        T item;
        Node next;

        Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node pos;

        LinkedListIterator() {
            pos = sentinel.next;
        }

        public boolean hasNext() {
            return pos != sentinel;
        }

        public T next() {
            T item = pos.item;
            pos = pos.next;
            return item;
        }

    }

}
