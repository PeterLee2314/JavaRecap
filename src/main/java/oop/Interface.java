package oop;
interface InterfaceA {
    int age = 1; //static and final

}
class InterfaceBchild implements InterfaceA, InterfaceC {
    public void run() {
        System.out.println("Running");
    }
}

interface InterfaceC {
    void run();
}
//allow interface extends interface
interface InterfaceD extends InterfaceC {
    //run is here too
}

public class Interface {
    public static void main(String[] a) {
        InterfaceA A = new InterfaceBchild();
        System.out.println(InterfaceA.age);
    }
}
