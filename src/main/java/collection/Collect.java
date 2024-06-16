package collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Collect {
    public static void main(String[] a) {
        // Contents of collection 'nums' are updated, but never queried
        Collection nums = new ArrayList();
        nums.add(6);
        nums.add(5);
        nums.add(3);
        nums.add(8);

        // Solution, add the type Integer (Generics)
        Collection<Integer> nums2 = new ArrayList<>();
        nums2.add(6);
        nums2.add(5);
        nums2.add(3);
        nums2.add(8);
        Iterator<Integer> values = nums2.iterator();
        while (values.hasNext()) {
            System.out.println(values.next()); // Output: 6, 5, 3, 8
        }

        // Collection only support add and fetch
        // With List, it support index fetch
        List<Integer> list = new ArrayList<>();
        list.add(11);
        System.out.println(list.get(0));

        ArrayList<Integer> num = new ArrayList<>();
    }
}
