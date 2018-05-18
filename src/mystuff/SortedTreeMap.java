package mystuff;

import oblig_filer.Entry;
import oblig_filer.ISortedTreeMap;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public class SortedTreeMap<K extends Comparable<? super K>, V> implements ISortedTreeMap<K, V>{
    private int numberOfEntries;
    private BinaryNode rootNode;

    private Comparator<? super K> comparator;


    public SortedTreeMap(Comparator<K> comparator) {
        this.comparator = comparator;
        numberOfEntries = 0;
        rootNode = null;
    }



    @Override
    public Entry<K, V> min() {
        return null;
    }


    @Override
    public Entry<K, V> max() {
        return null;
    }


    @Override
    public V add(K key, V value) {
        return add(new Entry<>(key, value));
    }
    
    @Override
    public V add(Entry<K, V> entry) {
        //om Map er "tom"
        if (isEmpty()){
            rootNode = new BinaryNode(entry);
            numberOfEntries++;
            return entry.value;
        }
        //om key er lik
        if (rootNode.getData().key == entry.key){
            Entry tmp = rootNode.getData();
            rootNode.setData(entry);
            return (V)tmp.value;
        }

        addy(rootNode, new BinaryNode(entry));

        return null;
    }

    private BinaryNode addy(BinaryNode currRoot, BinaryNode toBeInserted){
        int comp = comparator.compare((K)currRoot.getData().key, (K)toBeInserted.getData().key);

        if(comp == 0){

        }

        return currRoot;
    }


    @Override
    public void replace(K key, V value) throws NoSuchElementException {

    }
    
    @Override
    public void replace(K key, BiFunction<K, V, V> f) throws NoSuchElementException {

    }


    @Override
    public V remove(Object key) throws NoSuchElementException {
        return null;
    }
    
    @Override
    public V getValue(Object key) throws NoSuchElementException {
        return null;
    }
    
    @Override
    public boolean containsKey(K key) {
        return false;
    }
    
    @Override
    public boolean containsValue(V value) {
        return false;
    }


    @Override
    public Iterable<K> keys() {
        return null;
    }
    
    @Override
    public Iterable<V> values() {
        return null;
    }


    @Override
    public Iterable<Entry<K, V>> entries() {
        return null;
    }
    
    @Override
    public Entry<K, V> higherOrEqualEntry(K key) {
        return null;
    }
    
    @Override
    public Entry<K, V> lowerOrEqualEntry(K key) {
        return null;
    }
    
    @Override
    public void merge(ISortedTreeMap<K, V> other) {

    }
    
    @Override
    public void removeIf(BiPredicate<K, V> p) {

    }
    
    @Override
    public boolean isEmpty() {
        return rootNode == null;
    }
    
    @Override
    public int size() {
        return numberOfEntries;
    }
    
    @Override
    public void clear() {
        numberOfEntries = 0;
        rootNode = null;
    }
}
