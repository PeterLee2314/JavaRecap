package oop;

public class Main {
    public static void main(String[] args) {
        Super s = new Super();
        // Output: in A
        //         in B
        Super.B b = s.new B();
        // Output: in A
        //         in int B
        // Because without super(int), the parent default constructor will execute
        // there is hidden a super() at the child constructor
        //  not the parameterized one
        Super.B b2 = s.new B(1);
        // Output: in int A
        //         in int C with super
        Super.C c = s.new C(1);

        // Two way to allocate value for class variable
        This t = new This();
        t.setThisWithObj("John", t);
        t.showName();

        t.setThis("Tommy");
        t.showName();

    }
}
