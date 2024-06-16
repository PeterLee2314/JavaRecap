package stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Streams2 {
    // Predicate
    public static void main(String[] args) {
        // Predicate (boolean test(T)) Functional Interface
//        Predicate<Integer> p = new Predicate<Integer>() {
//            @Override
//            public boolean test(Integer n) {
//                return n%2 == 0;
//            }
//        };
        Predicate<Integer> p = n -> n%2 == 0;
        // X = () -> {};
        // Similar as X.filter(n -> n%2 == 0)
        // Simply put the Predicate inside filter
        List<Integer> list = Arrays.asList(4, 5, 7, 3, 2, 6, 8);
        Stream<Integer> s1 = list.stream().filter(p); // Valid

        // Stream (map) need Function , (Function<T, R> T:Type, R:Return Type)
        // Function is Functional Interface have (apply) method
//        Function<Integer , Integer> fun = new Function<Integer, Integer>() {
//            @Override
//            public Integer apply(Integer o) {
//                return o*2;
//            }
//        };
        Function<Integer , Integer> fun = n -> n*2; // Exactly as .map(n -> n*2)
        Stream<Integer> s2 = s1.map(fun); // Valid

        // Reduce (T identity, BinaryOperator<T> accumulator)
        // BinaryOperator(child) -> BiFunction(parent, Functional Interface (apply(T t, U u)))
        // BinaryOperator (perform action with 2 variable at a time)
        // c : Carry , e : Element
        // Eg (0,1,2,3,4) how to add them together? (0+1) then (1+2) then (3+3) then (6+4) -> 10
        // c : 1, 3, 6 (Carry) , e : 2, 3, 4 -> Therefore : simply c + e = 1+2, 3+3, 6+4

        // .reduce(0, (c,e) -> c+e) 0 is the start value

        Stream<Integer> sortedValues = list.stream().filter(n -> n%2 == 0).sorted();
        System.out.println(sortedValues); // cant directly print it else className+HashCode, use forEach
        sortedValues.forEach(n -> System.out.println(n));
        System.out.println("---------------");
        // parallel (multiple thread) , sorted is not make sense in thread so just filter is good to go
        list.parallelStream().filter(n -> n%2 == 0).forEach(n -> System.out.println(n));

    }
}
