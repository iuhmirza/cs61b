package deque;

public class ArrayDeque<T> {
    private int size;
    private T[] list;
    private int start;

    public ArrayDeque() {
        size = 0;
        list = (T[]) new Object[8];
        start = list.length / 2;
    }

    public void addFirst(T x) {
        resize();
        if (size != 0) {
            start -= 1;
        }
        list[start] = x;
        size += 1;
    }

    public T removeFirst() {
        resize();
        if (size == 0) {
            return null;
        }
        T first = list[start];
        list[start] = null;
        start += 1;
        size -= 1;
        return first;
    }

    public void addLast(T x) {
        resize();
        list[start+size] = x;
        size += 1;
    }

    public T removeLast() {
        resize();
        if (size == 0) {
            return null;
        }
        T last = list[start+size-1];
        list[start+size-1] = null;
        size -= 1;
        return last;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printDeque() {
        for (int i = start; i < start+size; i++) {
            System.out.print(list[i] + " ");
        }
    }

    public T get(int index) {
        return list[start+index];
    }

    public String toString() {
        String repr = "";
        for (int i = start; i < start+size; i++) {
            repr = repr + " " + list[i];
        }
        return repr;
    }

    public void resize() {
        if (size == 0) {
          return;
        } else if (size / (float) list.length > 0.75 || start+size == list.length || start == 0) {
            T[] newList = (T[]) new Object[list.length * 2];
            System.arraycopy(list, start, newList, list.length-size/2, size);
            start = list.length - size / 2;
            list = newList;
        } else if (size / (float) list.length < 0.25 && list.length > 8) {
            T[] newList = (T[]) new Object[list.length / 2];
            System.arraycopy(list, start, newList, list.length / 4 - size / 2, size);
            start = list.length / 4 - size / 2;
            list = newList;
        }
    }
}
