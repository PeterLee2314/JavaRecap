package oop;
class A {
    public void show() {
        System.out.println("in A");
    }
}
class B extends A {
    public void show() {
        System.out.println("in B");
    }
}
public class DynamicMethodDispatch {
    public static void main(String[] args) {
        // in A
        A obj = new A();
        obj.show();
        obj = new B();
        // in B
        obj.show();
        B obj2 = new B();
        obj2.show();
    }
}
