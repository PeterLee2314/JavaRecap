package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Compa {
    public static void main(String[] args) {
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                //1 (true) = swap
                //0 (false) = not swap
                return o1%10 > o2%10 ? 1 : -1;
            }
        };
        List<Integer> list = new ArrayList<>();
        list.add(31);
        list.add(30);
        list.add(22);
        list.add(56);
        list.add(15);
        System.out.println(list);
        Collections.sort(list, com);
        System.out.println(list);
    }
}
