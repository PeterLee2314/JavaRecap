import java.util.Objects;

//class Alien {
//    private int id;
//    private String name;
//
//    Alien(int id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @Override
//    public String toString() {
//        return "Alien{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                '}';
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Alien alien = (Alien) o;
//        return id == alien.id && Objects.equals(name, alien.name);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name);
//    }
//}

// Use Record Class instead
// record Alien(state variable) , all variable is final, not allow extends ( can implement Interface)
record Alien(int id, String name) implements Cloneable{
    // Compact Canonical Constructor (NO Mention of Variable)
    public Alien {
        if (id == 0) {
            throw new IllegalArgumentException("id cannot be zero");
        }
    }
    static int num = 0;
    //int num = 0;// Not allow instance variable here, must above the State Variable
    public void show() {
        System.out.println("Hello");
    }
    // (NOT ALLOW) Non-canonical record constructor must delegate to another constructor
//    public Alien() {
//
//    }

    public Alien() {
        this(12, "Empty");
    }
    // Canonical Constructor (The above State Variable is Exactly Same as the Constructor variable (Canonical))
//    public Alien(int id, String name) {
//        if (id == 0) {
//            throw new IllegalArgumentException("id cannot be zero");
//        }
//        this.id = id;
//        this.name = name;
//    }
    // everything is same as above (except setter, getter)
    // it generate method of equals and toString
}

public class RecordClasses {
    public static void main(String[] args) {
        Alien alien = new Alien(123, "Bob");
        Alien alien1 = new Alien(123, "Bob");
        Alien alien2 = new Alien();
        System.out.println(alien == alien1); // false because not same reference
        System.out.println(alien.equals(alien1)); // false by default , because java dont understand what to equal (id? name?)
        // after override, the equals return true
        System.out.println(alien);
        System.out.println(alien2); // 12 and Empty

        // Getter (exactly same as the variable name)
        System.out.println(alien.name());
        System.out.println(alien.id());
        alien.show();
    }
}
