package mystuff;
import oblig_filer.Entry;

import java.util.Iterator;
import java.util.Stack;

public class BinaryNode{

    private BinaryNode left, right;

    private Entry data;


    BinaryNode(Entry data){
        this(null,null,data);
    }

    BinaryNode(BinaryNode left, BinaryNode right , Entry data){
        this.data = data;

        this.left = left;
        this.right = right;
    }

    public BinaryNode getLeft() {
        return left;
    }

    public BinaryNode getRight() {
        return right;
    }

    public void setLeft(BinaryNode left) {
        this.left = left;
    }

    public void setRight(BinaryNode right) {
        this.right = right;
    }


    public Entry getData() {
        return data;
    }

    public void setData(Entry data) {
        this.data = data;
    }

    /**
     * @return TRUE if node left child is not null.
     */
    public boolean hasLeft(){
        return this.left != null;
    }
    /**
     * @return TRUE if node right child is not null.
     */
    public boolean hasRight(){
        return this.right != null;
    }
    public boolean isLeaf(){
        return (left != null) && (right != null);
    }

    public Iterator iterator(){
        return new BinaryPOIterator();
    }

    private class BinaryPOIterator implements Iterator{
        private BinaryNode current;
        private Stack stack;

        public BinaryPOIterator(){
            current = BinaryNode.this;
            stack = new Stack();
        }
        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return current != null || !current.isLeaf();
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public BinaryNode next() {
            BinaryNode result = null;



            return result;
        }

    }

}