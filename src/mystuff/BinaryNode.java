package mystuff;
import oblig_filer.Entry;



public class BinaryNode<K,V>{

    private BinaryNode left, right;

    private Entry<K, V> data;


    BinaryNode(Entry<K,V> data){
        this(null,null,data);
    }

    BinaryNode(BinaryNode left, BinaryNode right , Entry<K,V> data){
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

    public void setData(Entry<K,V> data) {
        this.data = data;
    }

    /**
     * @return TRUE if node left child is not null.
     */
    public boolean hasLeft(){
        return (this.left != null);
    }
    /**
     * @return TRUE if node right child is not null.
     */
    public boolean hasRight(){
        return (this.right != null);
    }
    public boolean isLeaf(){
        return (left == null) && (right == null);
    }


}