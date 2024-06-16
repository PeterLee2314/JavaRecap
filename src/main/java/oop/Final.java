package oop;

//final class (not allow others to extend T)
final class T {

}
//final method (not allow other method to override it)
class FA {
    final public void show() {
        System.out.println("FA");
    }
}

class FB extends FA {
//'show()' cannot override 'show()' in 'oop.FA'; overridden method is final
//    public void show() {
//        System.out.println("FB");
//    }
}
public class Final {
    public static void main(String[] args) {
        final int num = 8; // constant

    }
}
