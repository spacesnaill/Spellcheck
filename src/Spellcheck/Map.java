package Spellcheck;

/**
 * Created by Patrick on 11/7/2015.
 */
public interface Map<K,V> {
    int size();
    boolean isEmpty();
    boolean get(K key);
    V put(K key, V value);
    V remove(K key);
    Iterable <K> keySet();
    Iterable<V> values();
    Iterable<Entry<K,V>> entrySet();
}
