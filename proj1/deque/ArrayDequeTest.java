package deque;

import org.junit.Test;
import static org.junit.Assert.*;
public class ArrayDequeTest {

    @Test
    public void createEmptyList() {
        ArrayDeque<Integer> lst = new ArrayDeque<>();
        assertEquals(0, lst.size());
        assertTrue(lst.isEmpty());
    }

    @Test
    public void addFirstThree() {
        ArrayDeque<Integer> lst = new ArrayDeque<>();
        lst.addFirst(3);
        lst.addFirst(3);
        lst.addFirst(3);
        assertEquals(3, lst.size());
        assertFalse(lst.isEmpty());
        lst.removeFirst();
        lst.removeFirst();
        lst.removeFirst();
        assertEquals(0, lst.size());
        assertTrue(lst.isEmpty());
    }

    @Test
    public void addLastThree() {
        ArrayDeque<Integer> lst = new ArrayDeque<>();
        lst.addLast(3);
        lst.addLast(3);
        lst.addLast(3);
        assertEquals(3, lst.size());
        assertFalse(lst.isEmpty());
        lst.removeLast();
        lst.removeLast();
        lst.removeLast();
        assertEquals(0, lst.size());
        assertTrue(lst.isEmpty());
    }

    @Test
    public void addFirstRemoveLastThree() {
        ArrayDeque<Integer> lst = new ArrayDeque<>();
        lst.addFirst(3);
        lst.addFirst(3);
        lst.addFirst(3);
        assertEquals(3, lst.size());
        assertFalse(lst.isEmpty());
        lst.removeLast();
        lst.removeLast();
        lst.removeLast();
        assertEquals(0, lst.size());
        assertTrue(lst.isEmpty());
    }

    @Test
    public void addLastRemoveFirstThree() {
        ArrayDeque<Integer> lst = new ArrayDeque<>();
        lst.addLast(3);
        lst.addLast(3);
        lst.addLast(3);
        assertEquals(3, lst.size());
        assertFalse(lst.isEmpty());
        lst.removeLast();
        lst.removeLast();
        lst.removeLast();
        assertEquals(0, lst.size());
        assertTrue(lst.isEmpty());
    }

    @Test
    public void arrayequalslinkedlist() {
        LinkedListDeque<Integer> llist = new LinkedListDeque<>();
        llist.addFirst(10);
        llist.addFirst(11);
        llist.addFirst(12);

        ArrayDeque<Integer> array = new ArrayDeque<>();
        array.addFirst(10);
        array.addFirst(11);
        array.addFirst(12);

        assertTrue(array.equals(llist));
    }

    @Test
    public void arrayNotEqLinkedList() {
        LinkedListDeque<Integer> llist = new LinkedListDeque<>();
        llist.addFirst(10);
        llist.addFirst(13);
        llist.addFirst(12);

        ArrayDeque<Integer> array = new ArrayDeque<>();
        array.addFirst(10);
        array.addFirst(11);
        array.addFirst(12);

        assertFalse(array.equals(llist));
    }

    @Test
    public void iterableArray() {
        ArrayDeque<Integer> array = new ArrayDeque<>();
        array.addFirst(10);
        array.addFirst(-1);
        array.addFirst(12);
        array.addFirst(5);
        array.addFirst(13);
        array.addFirst(12);

        for (int x : array) {
            System.out.println(x);
        }
    }
}
