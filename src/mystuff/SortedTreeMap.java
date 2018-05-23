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

    private Comparator<K> comparator;


    public SortedTreeMap(Comparator comparator) {
        this.comparator = comparator;
        numberOfEntries = 0;
        rootNode = null;
    }


    @Override
    public Entry<K, V> min() {
        Iterator itr = getIter();
        if (itr.hasNext()) return (Entry<K, V>) itr.next();
        return null;
    }


    @Override
    public Entry<K, V> max() {
        Entry<K,V> last = null;
        Iterator itr = getIter();
        while (itr.hasNext()){
            last = (Entry<K, V>) itr.next();
        }
        return last;
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
            rootNode = new BinaryNode<>(entry);
            numberOfEntries++;
            return null;
        }

        V res = addElem(entry, rootNode);

        if (res == null) numberOfEntries++;
        return res;
    }

    private V addElem(Entry<K,V> entry, BinaryNode node){
        int comparison = comparator.compare( entry.key, (K)node.getData().key);

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
                node.setLeft(new BinaryNode<>(entry));
            }
        }

        if (comparison > 0){
            if (node.hasRight()){
                result = addElem(entry, node.getRight());
            } else {
                node.setRight(new BinaryNode<>(entry));
            }
        }

        return result;
    }



    @Override
    public void replace(K key, V value) throws NoSuchElementException {
        if (containsKey(key)){
            add(key,value);
        } else {
            throw new NoSuchElementException("replace() failed");
        }
    }
    
    @Override
    public void replace(K key, BiFunction<K, V, V> f) throws NoSuchElementException {
        BinaryNode b = getNodeByKey(rootNode, key);

        if (b == null) throw new NoSuchElementException("replace() fail, entry not found");

        Entry<K, V> entry = b.getData();

        add(entry.key, f.apply(entry.key,entry.value));
    }


    @Override
    public V remove(Object key) throws NoSuchElementException {
        BinaryNode nodeToRemove = getNodeByKey(rootNode,(K)key);

        //sjekker at ingen verdier er ute på tur...(null)
        if (rootNode == null || key == null || nodeToRemove == null){
            throw new NoSuchElementException("element could not be removed\n"+
            "root= "+rootNode+"\n"+ "key= "+key+"\n"+ "node= "+nodeToRemove+"\n");
        }


        BinaryNode<K,V> res = removeEntry(nodeToRemove);

        numberOfEntries--;
        return (V)res.getData().value;
    }

    /**
     * @param node to remove
     * @return removed node
     */
    private BinaryNode<K,V> removeEntry(BinaryNode node){
        //store copy of original
        BinaryNode<K,V> returnVal = new BinaryNode<>(
                new Entry<>((K)node.getData().key,(V)node.getData().value)
        );


        if (node.hasRight() && node.hasLeft()){
            //go left sub
            BinaryNode minNode = node.getRight();
            BinaryNode minParent = null;

            //find min
            while (minNode.hasLeft()){
                minParent = minNode;
                minNode = minNode.getLeft();
            }
            // minNode is now min in right Sub

            //???

            return returnVal;
        }


        if (node.hasLeft()) {
            node = node.getLeft();
        } else if (node.hasRight()) {
            node = node.getRight();
        } else {
            node = null;
        }

        return returnVal;
    }



    @Override
    public V getValue(Object key) throws NoSuchElementException {
        BinaryNode result = getNodeByKey(rootNode, (K)key);

        if (result == null || isEmpty()){
            throw new NoSuchElementException("result is null or tree is empty");
        }

        return (V)result.getData().value;
    }


    public BinaryNode getRootNode() {
        return rootNode;
    }

    public BinaryNode getNodeByKey(BinaryNode node, K key){
        if (node == null) return null;

        int comparison = comparator.compare(key, (K)node.getData().key);
        BinaryNode result;

        if (comparison == 0){
            result = node;
        } else if (comparison < 0){
            result = getNodeByKey(node.getLeft(), key);
        } else {
            result = getNodeByKey(node.getRight(), key);
        }

        return result;
    }


    
    @Override
    public boolean containsKey(K key) {
        return (getNodeByKey(rootNode, key) != null) && !isEmpty();
    }
    
    @Override
    public boolean containsValue(V value) {
        Iterator itr = getIter();
        while (itr.hasNext()){
            Entry<K,V> e = (Entry<K, V>) itr.next();
            if (e.value.equals(value)){
                return true;
            }
        }
        return false;
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
        Iterator itr = getIter();
        Entry<K,V> cur = null;

        while (itr.hasNext()){
            cur = (Entry<K, V>) itr.next();
            int comp = comparator.compare(cur.key, key);
            if (comp >= 0) return cur;
        }
        return null;
    }


    
    @Override
    public Entry<K, V> lowerOrEqualEntry(K key) {
        Iterator itr = getIter();
        Entry<K,V> cur,prev = null;


        while (itr.hasNext()){
            cur = (Entry<K, V>) itr.next();
            int comp = comparator.compare(cur.key, key);
            if (comp == 0){
                return cur;
            } else if (comp > 0){
                return prev;
            }
            prev = cur;
        }
        return prev;
    }
    
    @Override
    public void merge(ISortedTreeMap<K, V> other) {
        other.entries().forEach(this::add);
    }
    
    @Override
    public void removeIf(BiPredicate<K, V> p) {
        // TODO: 22.05.2018 IMPLEMENT
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

        private TreeIterator(){
            currRoot = rootNode;
            stack = new Stack<>();
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty() || (currRoot != null);
        }

        // @throws NoSuchElementException if the iteration has no more elements
        @Override
        public Entry<K,V> next() {
            //648,685

            BinaryNode next = null;

            while (currRoot != null){
                stack.push(currRoot);
                currRoot = currRoot.getLeft(); //go all waay left..
            }

            if (!stack.isEmpty()){
                next = stack.pop();
                assert next != null;
                currRoot = next.getRight();
            } else {
                throw new NoSuchElementException();
            }
            Entry<K,V> result = next.getData();
            return result;
        }

    }
}
