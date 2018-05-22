package mystuff;

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
        tree.add(null,"waaaaaaa");  //9
        tree.add(100,null);         //10
        System.out.println("size: "+tree.size()+"\n");

        tree.entries().forEach(e -> System.out.println(e.key + "  "+e.value));
    }
}
