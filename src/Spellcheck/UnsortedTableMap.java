package Spellcheck;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Patrick on 11/8/2015.
 */
public class UnsortedTableMap<K,V> extends AbstractMap<K,V> {
    //underlying storage for the map of entries
    private ArrayList<MapEntry<K,V>> table = new ArrayList<>();

    //constructs an initially empty map
    public UnsortedTableMap() {

    }

    //private utility
    //returns the index of an entry with equal key or -1 if none found
    private int findIndex(K key){
        int n = table.size();
        for(int j = 0; j<n; j++){
            if(table.get(j).getKey().equals(key)){
                return j;
            }
        }
        return -1;
    }

    public int size() {
        return table.size();
    }

    public boolean get(K key){
        int j = findIndex(key);
        if(j == -1){
            return false;
        }
        return true;
        //return table.get(j).getKey(); //come back to this later on. we don't want the value we want the key
    }

    //come back to this as well, we don't want a value
    public K put(K key){
        V value = null;
        int j = findIndex(key);
        if (j == -1){
            table.add(new MapEntry<>(key, value)); //add a new entry
            return null;
        }
        else{
            return table.get(j).getKey();
        }
    }

    //should be nothing to remove so this method is mostly useless
    public V remove(K key){
        int j = findIndex(key);
        int n = size();
        if(j == -1){
            return null; //not found
        }
        V answer = table.get(j).getValue();
        if(j != n - 1){
            table.set(j, table.get(n-1)); //relocate last entry to 'hole' created by removal
        }
        table.remove(n-1); //remove last entry of the table
        return answer;
    }

    private class EntryIterator implements Iterator<Entry<K,V>> {
        private int j = 0;
        public boolean hasNext(){
            return j < table.size();
        }

        public Entry<K,V> next() {
            if (j == table.size()) {
                throw new NoSuchElementException();
            }
            return table.get(j++); //watch out for this
        }
        public void remove(){
            throw new UnsupportedOperationException();
        }
    }
    private class EntryIterable implements Iterable<Entry<K,V>>{
        public Iterator<Entry<K,V>> iterator() {
            return new EntryIterator();
        }
    }

    public Iterable<Entry<K,V>> entrySet(){
        return new EntryIterable();
    }

    public V put(K a , V b){
        return null;
    }
}
