public class DummyClass {
    static String name;
    static {
        name = "called by forName";
        System.out.println(name);
    }
}
