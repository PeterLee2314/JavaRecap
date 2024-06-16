package oop;

public class Super {
    public class A {
        A() {
            System.out.println("in A");
        }
        A(int n) {
            System.out.println("in int A");
        }
    }
    public class B extends A {
        B() {
            System.out.println("in B");
        }
        B(int n) {
            System.out.println("in int B");
        }
    }
    public class C extends A {
        C() {
            System.out.println("in C");
        }
        C(int n) {
            super(n);
            System.out.println("in int C with super");
        }
    }
}
