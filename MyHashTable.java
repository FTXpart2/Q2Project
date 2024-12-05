import java.util.ArrayList;
import java.io.Serializable;
public class MyHashTable<K, V> implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<ArrayList<Node<K, V>>> table; // List of buckets
    private int capacity;

    // Node class to store key-value pairs
    private static class Node<K, V> {
        K key;
        V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // Constructor
    public MyHashTable(int capacity) {
        this.capacity = capacity;
        table = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            table.add(new ArrayList<>()); // Initialize each bucket
        }
    }

    // Hash function
    private int hash(K key) {
        return Math.abs(key.hashCode() % capacity);
    }

    // Add key-value pair
    public void put(K key, V value) {
        int index = hash(key);
        ArrayList<Node<K, V>> bucket = table.get(index);
        for (Node<K, V> node : bucket) {
            if (node.key.equals(key)) {
                node.value = value; // Update existing value
                return;
            }
        }
        bucket.add(new Node<>(key, value)); // Add new key-value pair
    }

    // Get value by key
    public V get(K key) {
        int index = hash(key);
        ArrayList<Node<K, V>> bucket = table.get(index);
        for (Node<K, V> node : bucket) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null; // Key not found
    }

    // Remove key-value pair
    public void remove(K key) {
        int index = hash(key);
        ArrayList<Node<K, V>> bucket = table.get(index);
        bucket.removeIf(node -> node.key.equals(key));
    }

    // Get all keys
    public ArrayList<K> keySet() {
        ArrayList<K> keys = new ArrayList<>();
        for (ArrayList<Node<K, V>> bucket : table) {
            for (Node<K, V> node : bucket) {
                keys.add(node.key);
            }
        }
        return keys;
    }
        // Check if a key exists in the hash table
    public boolean containsKey(K key) {
        int index = hash(key);
        ArrayList<Node<K, V>> bucket = table.get(index);
        for (Node<K, V> node : bucket) {
            if (node.key.equals(key)) {
                return true; // Key found
            }
        }
        return false; // Key not found
    }

    // Get all values
    public ArrayList<V> values() {
        ArrayList<V> values = new ArrayList<>();
        for (ArrayList<Node<K, V>> bucket : table) {
            for (Node<K, V> node : bucket) {
                values.add(node.value);
            }
        }
        return values;
    }

    // Get size of hash table
    public int size() {
        int size = 0;
        for (ArrayList<Node<K, V>> bucket : table) {
            size += bucket.size();
        }
        return size;
    }
}
