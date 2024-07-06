package stream;

import java.util.List;
import java.util.stream.IntStream;

public class ParallelStream {
    private static final int TIMES = 2;
    public static void main(String[] args) {
        for(int i = 0; i < TIMES; i++) {
            var before = System.currentTimeMillis();
            IntStream.range(1,1_000_000).sequential().sum(); // sequential
            System.out.println(System.currentTimeMillis() - before);

        }
        System.out.println();
        for(int i = 0; i < TIMES; i++) {
            var before = System.currentTimeMillis();
            IntStream.range(1,1_000_000).parallel().sum(); // turn stream to parallel
            System.out.println(System.currentTimeMillis() - before);
        }


        // parallelStream VS stream().parallel()
        // not much difference (but as Documentation said)
        List.of("one").parallelStream(); // not guarantee return is parallel
        List.of("one").stream().parallel(); // guarantee return is parallel
        List.of("one").parallelStream().isParallel(); // check is parallel

        var result = IntStream.rangeClosed(1,10).parallel().reduce(100,Integer::sum); // should be 155, but 1055
        // WHY? because rangeClosed() generate a stream with size 10, when parallel() it make 10 threads starting with a value of 100,
        // they add current Element to the identity element(start value) 1000+1 , 1001+2, 1001+3.... 1045+10 => 1055
        // because 1+2+3+4+5+6+7+8+9+10 = 55
        System.out.println(result);

    }
}
