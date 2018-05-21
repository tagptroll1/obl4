package mystuff;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        SortedTreeMap<Integer, String> tree = new
                SortedTreeMap(Comparator.naturalOrder());
        tree.add(5,"asd");
        tree.add(3,"ddd");
        tree.add(2,"r4");
        tree.add(7,"s");
        tree.add(6,".");
        tree.add(-4,"aaaaa");
        tree.add(22,"22---");
        System.out.println("size: "+tree.size()+"\n");

        tree.entries().forEach(e -> System.out.println(e.key + "  "+e.value));
    }
}
