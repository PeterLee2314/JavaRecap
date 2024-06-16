package funcInterface;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

// interface accept any Type as parameter
@FunctionalInterface
interface A<T> {
    void hello();
    // functional interface only allow 1 method
}

class B {
    public void helloFromB(A helloFunction) {
        helloFunction.hello();
    }
}
public class Main {
    // hello function dont belong to any class but interface,
    public static void main(String[] a) {
        // Functional Interface (with lambda)
        A helloFunction = () -> {
            System.out.println("Hello");
        };
        A anotherHelloFunction = () -> {
            System.out.println("Hello Another!");
        };
        B b = new B();
        helloFunction.hello();
        anotherHelloFunction.hello();
        b.helloFromB(helloFunction);
        b.helloFromB(anotherHelloFunction);
        // A function can pass to anywhere(classes)

        // Predefined functional interface by Java (Predicate<T>, Consumer<T>, Supplier<T>, UnaryOperator<T>,
        // Predicate (take 1 <T> return boolean)
        // Consumer (take 1 <T> return void)
        // Supplier (take no <T>, return any <T>)
        // UnaryOperator (take <T> return the same <T>)
        //  BiFunction<T, T, U>
        Predicate<String> stringFilter = (s) -> {
            return s.startsWith("S");
        };
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        boolean result = stringFilter.test(str);
        System.out.println(result == true ? "Input starts with S" : "Input not starts with S");

        // Get String start with S with stream
        List<String> names = Arrays.asList("Hello", "Shallow", "Super", "Windy");
        List<String> filteredNames = names.stream()
                                            .filter(stringFilter)
                                            .collect(Collectors.toList());
        System.out.println(filteredNames); // Output [Shallow, Super]

        // Another way to Achieve Functional Interface
    }
}
