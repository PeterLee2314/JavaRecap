public class StaticClass {
    // Execute sequence ([class load]:static -> [object load]:object instantiated with constructor)
    // Class loader only execute once (static block)
    // Class loader by default not load if no Object Created
    // Class.forName(className) load Class Loader even no Object Created
    public class Mobile {
        String brand;
        static String name;
        static {
            name = "Phone"; // call only once
            System.out.println("object loader executed by Object Creation");
        }
        public void show() {
            System.out.println(name);
        }
        // non-static unacceptable in static method (parameterless)
        public static void show2() {
            System.out.println("method show2: " + name);
        }
        public static void show2(Mobile mobile) {
            System.out.println("method show2: " + mobile.brand + " " + name);
        }

        static public void call() {
            System.out.println("Calling");
        }
    }
}
