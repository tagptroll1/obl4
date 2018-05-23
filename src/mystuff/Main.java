package mystuff;

import oblig_filer.Entry;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        SortedTreeMap<Integer, String> tree = new
                SortedTreeMap(Comparator.naturalOrder());
        tree.add(15,"asd");
        tree.add(13,"ddd");
        tree.add(12,"r4");
        tree.add(17,"s");
        tree.add(16,".");
        tree.add(-4,"aaaaa");
        tree.add(22,"22---");
        tree.add(-1,"");            //8

        tree.add(100,"ONE HUNDER");

        System.out.println("size: "+tree.size()+"\n");

        tree.getNodeByKey(tree.getRootNode(),100).setData(new Entry(99,"I CHANGE"));

        tree.entries().forEach(e -> System.out.println(e.key + "  "+e.value));
        System.out.println("----");


//
//        //if node does not have children, just kill it.
//        if (nodeToRemove.isLeaf()){
//            nodeToRemove = null;
//        } else {
//
//            // two children
//            if (nodeToRemove.isFull()){
//                BinaryNode replacement = nodeToRemove
//                        .getLeft()
//                        .rightmost();
//                remove(replacement.getData().key);
//                nodeToRemove.setData(replacement.getData());
//
//
//            } else if (nodeToRemove.hasRight()){
//                nodeToRemove = nodeToRemove.getRight();
//            } else {
//                nodeToRemove = nodeToRemove.getLeft();
//            }
//
//        }
//
//
//        numberOfEntries--;
//        return result;


    }
}
