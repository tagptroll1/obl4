package mystuff;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

public class Tests {

    @Test
    public void test_remove() {
        SortedTreeMap<Integer, String> tm = new SortedTreeMap<>(Comparator.naturalOrder());
        tm.add(5, "5");
        tm.add(2, "2");
        tm.add(9, "9");
        tm.add(7, "7");
        tm.add(8, "8");
        tm.add(4, "4");
        tm.add(6, "6");
        tm.add(3, "3");
        tm.add(1, "1");
        tm.add(10, "10");
        tm.add(11, "11");


        /*
                  5
               /     \
              2       9
             / \     / \
            1  4    7   10
              /    / \    \
             3    6   8   11
         */

        assertEquals(11, tm.size());
        String returnedV = tm.remove(9);
        assertEquals(true,tm.containsKey(9));
        assertEquals(true,tm.containsValue("9"));

        assertEquals("9", returnedV);

        assertEquals(false, tm.isEmpty());
        assertEquals(10, tm.size());
        assertEquals(false, tm.containsValue("9"));
        assertEquals(false, tm.containsKey(9));

        // Sjekk alle andre
        assertEquals(true,tm.containsKey(1));
        assertEquals(true,tm.containsValue("1"));

        assertEquals(true,tm.containsKey(2));
        assertEquals(true,tm.containsValue("2"));

        assertEquals(true,tm.containsKey(3));
        assertEquals(true,tm.containsValue("3"));

        assertEquals(true,tm.containsKey(4));
        assertEquals(true,tm.containsValue("4"));

        assertEquals(true,tm.containsKey(5));
        assertEquals(true,tm.containsValue("5"));

        assertEquals(true,tm.containsKey(6));
        assertEquals(true,tm.containsValue("6"));

        assertEquals(true,tm.containsKey(7));
        assertEquals(true,tm.containsValue("7"));

        assertEquals(true,tm.containsKey(8));
        assertEquals(true,tm.containsValue("8"));

        assertEquals(true,tm.containsKey(10));
        assertEquals(true,tm.containsValue("10"));

        assertEquals(true,tm.containsKey(11));
        assertEquals(true,tm.containsValue("11"));
    }
}
