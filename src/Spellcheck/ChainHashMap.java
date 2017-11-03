package Spellcheck;

import java.util.ArrayList;

/**
 * Created by Patrick on 11/8/2015.
 */
public class ChainHashMap<K,V> extends AbstractHashMap<K,V> {
    //a fixed capacity array of UnsortedTableMap that serve as buckets
    private UnsortedTableMap<K,V>[] table; //initialized within createTable

    public ChainHashMap() {
        super();
    }

    public ChainHashMap(int cap) {
        super(cap);
    }

    public ChainHashMap(int cap, int p){
        super(cap ,p);
    }

    //creates an empty table having length equal to current capacity
    protected void createTable(){
        table = (UnsortedTableMap<K,V>[]) new UnsortedTableMap[capacity];
    }

    protected boolean bucketGet(int h, K k){
        UnsortedTableMap<K,V> bucket = table[h];
        if(bucket == null){
            return false;
        }
        return bucket.get(k);
    }

    protected K bucketPut(int h, K k){
        UnsortedTableMap<K,V> bucket = table[h];
        if(bucket == null){
            bucket = table[h] = new UnsortedTableMap<>();
        }
        int oldSize = bucket.size();
        K answer = bucket.put(k);
        n += (bucket.size() - oldSize); //size may have increased
        return answer;
    }

    protected V bucketRemove(int h, K K){
        return null;
    }

    public Iterable<Entry<K,V>> entrySet() {
        ArrayList<Entry<K,V>> buffer = new ArrayList<>();
        for(int h = 0; h < capacity; h++){
            if(table[h] != null){
                for(Entry<K,V> entry : table[h].entrySet()){
                    buffer.add(entry);
                }
            }
        }
        return buffer;
    }

    public V put(K a ,V b){
        return null;
    }
}
