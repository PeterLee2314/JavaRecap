package funcInterface;

@FunctionalInterface
interface A2 {
    int add1();
}
interface A3 {
    int add1(int i);
}
public class Main2 {
    // Anonymous class , with Lambda it can simplify it
    // With one line and its return no need to put return
    public static void main(String[] a) {
        A2 a2 = () -> 1; // its return 1
        A3 a3 = i -> 1+i;
        System.out.println(a2.add1()); // Output: 1
        System.out.println(a3.add1(1)); // Output: 2
    }

}
