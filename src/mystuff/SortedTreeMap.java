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
        //om entry er funky
        if (entry.key == null || entry.value == null){
            return null;
        }

        //om Map er "tom"
        if (isEmpty()){
            rootNode = new BinaryNode(entry);
            numberOfEntries++;
            return entry.value;
        }

        V res = addElem(entry, rootNode);

        return res;
    }

    private V addElem(Entry<K,V> entry, BinaryNode node){
        int comparison = comparator.compare(entry.key, (K)node.getData().key);

        V result = null;

        if (comparison == 0){
            //same key
            result = (V)node.getData().value;
            node.setData(entry);
        }

        if (comparison < 0){
            if (node.hasLeft()){
                result = addElem(entry, node.getLeft());
            } else {
                node.setLeft(new BinaryNode(entry));
                numberOfEntries++;
            }
        }

        if (comparison > 0){
            if (node.hasRight()){
                result = addElem(entry, node.getRight());
            } else {
                node.setRight(new BinaryNode(entry));
                numberOfEntries++;
            }
        }

        return result;
    }



    @Override
    public void replace(K key, V value) throws NoSuchElementException {
        BinaryNode n = getNodeByKey(rootNode, key);
        if (n == null){
            throw new NoSuchElementException("Replacing -> could not find key in map");
        } else {
            n.setData(new Entry<>(key, value));
        }
    }
    
    @Override
    public void replace(K key, BiFunction<K, V, V> f) throws NoSuchElementException {

    }


    @Override
    public V remove(Object key) throws NoSuchElementException {
        //numberOfEntries--
        return null;
    }


    // TODO: 21.05.2018 EEEH....is work?
    @Override
    public V getValue(Object key) throws NoSuchElementException {
        BinaryNode result = getNodeByKey(rootNode, (K)key);

        if (result == null || isEmpty()){
            throw new NoSuchElementException("result is null or tree is empty");
        }

        return (V)result.getData().value;
    }

    private BinaryNode getNodeByKey(BinaryNode node, K key){
        if (node == null){
            return null;
        }

        int comparison = comparator.compare((K)node.getData().key, key);
        BinaryNode result = null;

        if (comparison == 0){
            result = node;
        }
        if (comparison < 0 && node.hasLeft()){
            getNodeByKey(node.getLeft(), key);
        }
        if (comparison > 0 && node.hasRight()){
            getNodeByKey(node.getRight(), key);
        }

        return result;
    }
    
    @Override
    public boolean containsKey(K key) {
        return (getNodeByKey(rootNode, key) != null) && !isEmpty();
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
        //numberOfEntries--
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
