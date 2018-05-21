package mystuff;

import oblig_filer.Entry;
import oblig_filer.ISortedTreeMap;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.stream.StreamSupport;


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
        BinaryNode nodeToRemove = getNodeByKey(rootNode,(K)key);

        if (rootNode == null || key == null || nodeToRemove == null){
            throw new NoSuchElementException("element could not be removed\n"+
            "root= "+rootNode+"\n"+ "key= "+key+"\n"+ "node= "+nodeToRemove+"\n");
        }



        numberOfEntries--;
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

    /**
     * @return first node that matches the value
     */
    private BinaryNode getNodeByValue(V val, BinaryNode currNode){
        if (isEmpty() || val == null) return null; // om tree er tom eller om val er null

        if ((V)currNode.getData().value == val){
            return currNode;
        } else {
            if (currNode.hasLeft()){
                getNodeByValue(val, currNode.getLeft());
            }
            if (currNode.hasRight()){
                getNodeByValue(val, currNode.getRight());
            }
        }
        return null;
    }
    
    @Override
    public boolean containsKey(K key) {
        return (getNodeByKey(rootNode, key) != null) && !isEmpty();
    }
    
    @Override
    public boolean containsValue(V value) {
        return getNodeByValue(value, rootNode) != null;
    }

    @Override
    public Iterable<K> keys() {
        return () -> StreamSupport.stream(entries().spliterator(), false)
                .map(ent -> ent.key).iterator();
    }
    
    @Override
    public Iterable<V> values() {
        return () -> StreamSupport.stream(entries().spliterator(), false)
                .map(ent -> ent.value).iterator();
    }


    @Override
    public Iterable<Entry<K, V>> entries() {
        return this::getIter;
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


    private Iterator getIter(){
        return new TreeIterator();
    }

    private class TreeIterator implements Iterator{
        private Stack<BinaryNode> stack;
        private BinaryNode currRoot;

        public TreeIterator(){
            currRoot = rootNode;
            stack = new Stack<>();
            while (currRoot != null){
                currRoot = currRoot.getLeft();
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

         // @throws NoSuchElementException if the iteration has no more elements
        @Override
        public Entry<K,V> next() {

            if (stack.isEmpty()){
                throw new NoSuchElementException("EMPTY stack.");
            }

            BinaryNode n = stack.pop();
            Entry result = n.getData();

            if (currRoot.hasRight()){
                currRoot = currRoot.getRight();
                while (currRoot != null){
                    stack.push(currRoot);
                    currRoot = currRoot.getLeft();
                }
            }

            return result;
        }

    }
}
