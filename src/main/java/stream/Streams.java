package stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Streams {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(4, 5, 7, 3, 2, 6);
        System.out.println(list);
        // Consumer take 1 input and do void
//        Consumer<Integer> con = new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) {
//                System.out.println(integer);
//            }
//        };
        Consumer<Integer> con = i -> System.out.println(i);
        list.forEach(con);
        System.out.println("------------------");
        //list.forEach(n -> System.out.println(n));
        // Stream (make any operation without affecting list)
        Stream<Integer> s1 = list.stream();
        //System.out.println("S1:");
        //s1.forEach(n -> System.out.printf("%d ",n));
        System.out.println();
        Stream<Integer> s2 = s1.filter(n -> n%2==0);
        //System.out.println("S2:");
        //s2.forEach(n -> System.out.printf("%d ",n)); // output: 4 2 6
        Stream<Integer> s3 = s2.map(n -> n*2);
        //System.out.println("S3:");
        //s3.forEach(n -> System.out.printf("%d ",n)); // Output: 4*2 2*2 6*2 -> 8 4 12
        // Stream cant reuse (it close once executed)
        // Reduce will only give you 1 output by the operation you customized
        //
        int result = s3.reduce(0, (c,e) -> c+e);  // return you a Stream type in here is Integer
        System.out.println(result); // Output: 24
        /*
        Invalid Case (use s1 twice):
        s1.forEach(n -> System.out.printf("%d ",n));
        s1.forEach(n -> System.out.printf("%d ",n));
         */
        System.out.println("---------------");
        int result2 = list.stream().filter(n -> n%2==0).map(n -> n*2).reduce(0, (c,e) -> c+e);
        System.out.println(result2);
    }
}
