package oop;

class AClass {
    public void show() {
        System.out.println("in A");
    }
}
abstract class AbstractAClass {
    abstract public void show();
}
public class AnonymousClass {
    public static void main(String a[]) {
        AClass obj = new AClass() {
            //AnonymousClass
            public void show() {
                System.out.println("in new Show");
            }
        };
        //it give implementation, so now it allows abstract class object create
        AbstractAClass abstractAClass = new AbstractAClass() {
            @Override
            public void show() {
                System.out.println("abstract class with implementation");
            }
        };

    }
}
