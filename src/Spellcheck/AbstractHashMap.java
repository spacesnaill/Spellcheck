package Spellcheck;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Patrick on 11/8/2015.
 */
public abstract class AbstractHashMap<K,V> extends AbstractMap<K,V>{
    protected int n = 0; //number of entries in the dictionary
    protected int capacity; //length of the table
    private int prime; //prime factor
    private long scale, shift; //the shift and scaling factors

    public AbstractHashMap(int cap, int p){
        prime = p;
        capacity = cap;
        Random rand = new Random();
        scale = rand.nextInt(prime-1) + 1;
        shift = rand.nextInt(prime);
        createTable();
    }

    public AbstractHashMap(int cap) {
        this(cap, 109345121); //default prime
    }

    public AbstractHashMap(){
        this(17); //default capacity
    }

    //public methods
    public int size() {
        return n;
    }
    public boolean get(K key){
        return bucketGet(hashValue(key), key);
    }
    public V remove(K key){
        return bucketRemove(hashValue(key), key);
    }
    public K put(K key){
        K answer = bucketPut(hashValue(key), key);
        if(n > capacity / 2){
            resize(2 * capacity - 1); //keep load factor <= 0.5 (or find a nearby prime)
        }
        return answer;
    }

    //private utilities
    private int hashValue(K key){
        return(int) ((Math.abs(key.hashCode()*scale + shift) % prime) % capacity);
    }
    private void resize(int newCap){
        ArrayList<Entry<K,V>> buffer = new ArrayList<>(n);
        for(Entry<K,V> e : entrySet()){
            buffer.add(e);
        }
        capacity = newCap;
        createTable(); //based on updated capacity
        n = 0; //will be recomputed while reinserting entries
        for(Entry<K,V> e : buffer){
            put(e.getKey());
        }
    }

    //protected abstract methods to be implemented by subclass
    protected abstract void createTable();
    protected abstract boolean bucketGet(int h, K k);
    protected abstract K bucketPut(int h, K k);
    protected abstract V bucketRemove(int h, K k);
}
