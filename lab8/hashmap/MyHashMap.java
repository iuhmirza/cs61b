package hashmap;

import org.eclipse.jetty.io.ByteBufferPool;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private int size; //number of added items (get number of buckets with buckets.length)
    private double loadFactor;

    /** Constructors */
    public MyHashMap() {
        buckets = createTable(16);
        size = 0;
        loadFactor = 0.75;
    }

    public MyHashMap(int initialSize) {
        buckets = createTable(initialSize);
        size = 0;
        loadFactor = 0.75;
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        buckets = createTable(initialSize);
        size = 0;
        loadFactor = maxLoad;
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        Collection<Node>[] bkts = new Collection[tableSize];
        for (int i = 0; i < bkts.length; i++) {
            bkts[i] = createBucket();
        }
        return bkts;
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    public void clear() {
        buckets = createTable(16);
        size = 0;
    }

    public boolean containsKey(K key) {
        int bucketIndex = Math.floorMod(key.hashCode(), buckets.length);
        for(Node N : buckets[bucketIndex]) {
            if (key.equals(N.key)) {
                return true;
            }
        }
        return false;
    }

    public V get(K key) {
        int bucketIndex = Math.floorMod(key.hashCode(), buckets.length);
        for(Node N : buckets[bucketIndex]) {
            if (key.equals(N.key)) {
                return N.value;
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    public void put(K key, V value) {
        if (size / (float) buckets.length >= loadFactor) {
            resize();
        }
        int bucketIndex = Math.floorMod(key.hashCode(), buckets.length);
        for (Node N : buckets[bucketIndex]) {
            if (key.equals(N.key)) {
                N.value = value;
                return;
            }
        }
        buckets[bucketIndex].add(new Node(key, value));
        size += 1;
    }

    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        for (K key : this) {
            keySet.add(key);
        }
        return keySet;
    }

    private class MyHashMapIterator implements Iterator<K> {
        int bucketsIndex = 0;
        Iterator<Node> bucketIterator = buckets[bucketsIndex].iterator();


        public boolean hasNext() {
            while (!bucketIterator.hasNext() && bucketsIndex+1 < buckets.length) {
                bucketsIndex += 1;
                bucketIterator = buckets[bucketsIndex].iterator();
            }
            return bucketIterator.hasNext();
        }

        public K next() {
            if (hasNext()) {
                return bucketIterator.next().key;
            }
            throw new NoSuchElementException();
        }
    }
    public Iterator<K> iterator() {
        return new MyHashMapIterator();
    }

    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }



    private void resize() {
        Collection<Node>[] newBuckets = createTable((int) (buckets.length * 2));
        Set<K> keySet = keySet();
        for (K key : keySet) {
            int bucketIndex = Math.floorMod(key.hashCode(), newBuckets.length);
            newBuckets[bucketIndex].add(new Node(key, get(key)));
        }
        buckets = newBuckets;
    }
}
