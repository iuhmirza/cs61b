package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{

    private BSTNode root;
    private int size;
    private class BSTNode {
        public BSTNode left;
        public K key;
        public V value;
        public BSTNode right;


        BSTNode(K k, V v) {
            left = null;
            key = k;
            value = v;
            right = null;
        }
        BSTNode() {
            left = null;
            key = null;
            value = null;
            right = null;
        }

        public BSTNode find(K key) {
            BSTNode current = this;
            if (current.key == null) {
                return null;
            }
            while (current != null) {
                if (key.compareTo(current.key) > 0) {
                    current = current.right;
                } else if (key.compareTo(current.key) < 0) {
                    current = current.left;
                } else {
                    return current;
                }
            }
            return null;
        }

        public boolean insert(K key, V value) {
            BSTNode current = this;
            if(current.key == null) {
                current.key = key;
                current.value = value;
                return true;
            }
            while (current != null) {
                if (key.compareTo(current.key) > 0) {
                    if(current.right == null) {
                        current.right = new BSTNode(key, value);
                        return true;
                    }
                    current = current.right;
                } else if (key.compareTo(current.key) < 0) {
                    if (current.left == null) {
                        current.left = new BSTNode(key, value);
                        return true;
                    }
                    current = current.left;
                } else {
                    current.value = value;
                    return false;
                }
            }
            return false;
        }
    }
    public BSTMap() {
        root = new BSTNode();
        size = 0;
    }

    public void clear() {
        root = new BSTNode();
        size = 0;
    }

    public boolean containsKey(K key) {
        return root.find(key) != null;
    }

    public V get(K key) {
        BSTNode current = root.find(key);
        if (root.find(key) != null) {
            return current.value;
        }
        return null;
    }

    public int size() {
        return size;
    }

    public void put(K key, V value) {
        if(root.insert(key, value)) {
            size += 1;
        }
    }

    public void printInOrder() {
        printHelper(root);
    }

    private void printHelper(BSTNode N) {
        //System.out.println(N.key.toString() + N.value.toString());
        if (N == null) {
            return;
        }
        if (N.left == null) {
            System.out.println(N.key + ": " + N.value);
        } else {
            printHelper(N.left);
            System.out.println(N.key + ": " + N.value);
        }
        if (N.right == null) {
            return;
        } else {
            printHelper(N.right);
        }
    }

    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
