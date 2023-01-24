package deque;

public class LinkedListDeque<T> {
    private int size;
    private Node sentinel;

    private class Node {
        public Node prev;
        public T item;
        public Node next;

        public Node(Node p, T i,  Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public void addFirst(T x) {
        Node oldFirst = sentinel.next;
        sentinel.next = new Node(sentinel, x, oldFirst);
        oldFirst.prev = sentinel.next;
        size += 1;
    }

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

    public void addLast(T x) {
        Node oldLast = sentinel.prev;
        sentinel.prev = new Node(oldLast, x, sentinel);
        oldLast.next = sentinel.prev;
        size += 1;
    }

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

    public int size() {
        return size;
    }

    public T get(int index) {
        if (index > size / 2) {
            for (Node p = sentinel.prev; p != sentinel; p = p.prev ) {
                if (index == size-1) {
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

    public T getRecursiveHelper(int index, Node lst, int start) {
        if (lst == sentinel) {
            return null;
        } else if (start == index) {
            return lst.item;
        } else {
            return getRecursiveHelper(index, lst, start + 1);
        }
    }

    public T getRecursive(int index) {
        int start = 0;
        if (index > size / 2) {
            start = size / 2;
        }
        return getRecursiveHelper(index, sentinel.next, start);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printDeque() {
        for(Node p = sentinel.next; p != sentinel; p = p.next) {
            System.out.print(p.item + " ");
        }
    }
}
