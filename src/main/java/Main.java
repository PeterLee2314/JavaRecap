// Way to import folder Class (folder.Class)
import oop.Encap;

public class Main {
    // Q: Why main is static?
    // A: If main is not static, we need to create a Main object first,
    //      However, main is the starting execution point, if main not started,
    //      how can we have Main object (deadlock occurred )
    public static void main(String[] args) {
        // Recap (Java Core, OOP, Design Pattern,  Spring, Microservices)
        // String
        System.out.println("String :");
        StringNote stringNote = new StringNote();
        sep();

        // Class (inner class)
        StaticClass staticClass = new StaticClass();
        StaticClass.Mobile mobile = staticClass.new Mobile();
        // Output: Phone (constructor => change everytime when new) (use static {} instead)
        mobile.show();
        // Bad practice (mobile.name = "SmartPhone") [Static should be accessed by Static not object)
        StaticClass.Mobile.name = "SmartPhone";
        // Output: SmartPhone
        mobile.show();
        StaticClass.Mobile.call();

        mobile.brand = "Apple";
        StaticClass.Mobile.show2();
        StaticClass.Mobile.show2(mobile);

        // Call static object loader by Class.forName
        try {
            Class.forName("DummyClass");
        }
        catch (ClassNotFoundException ex) {

        }
        sep();

        // instantiated from inside folder (oop)
        Encap encap = new Encap();
        Encap.hello();

        sep();


    }

    public static void sep() {
        System.out.println("----------------------------------");
    }
}
