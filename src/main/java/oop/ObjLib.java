package oop;
class ObjLibLaptop {
    String model;

    public String toString() {
        return "Hello";
    }

    public boolean equals(ObjLibLaptop obj2) {
        if(this.model.equals(obj2.model)) {
            return true;
        }
        return false;
    }
}
public class ObjLib {
    public static void main(String[] a) {
        ObjLibLaptop obj = new ObjLibLaptop();
        obj.model = "Iphone";
        System.out.println(obj.toString());  // oop.ObjLibLaptop@214c265e (ClassName+@+Hashcode)
        System.out.println(obj); // oop.ObjLibLaptop@214c265e (ClassName+@+Hashcode)
        //If custom toString method override it, the above two way will change to "Hello"
        // obj.equals(obj2) mean compare their hex hashcode whether is same which exactly same as obj == obj2
        ObjLibLaptop obj2 = new ObjLibLaptop();
        obj2.model = "Iphone";
        System.out.println(obj.equals(obj2));
    }
}
