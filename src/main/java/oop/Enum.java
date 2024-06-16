package oop;

enum Status {
    // 4 objects is created
    Running, Failed, Pending, Success;
    //allows constructor, variable
}
enum Laptop {
    Macbook(2000), XPS(2200), Surface(1500);
    private int price;
    Laptop(int price) {
        this.price = price;
    }
    public int getPrice() {
        return price;
    }
}

public class Enum {
    public static void main(String[] a) {
        int i = 5;
        Status s = Status.Running;
        System.out.println(s);
        // get number of enum object
        // Output: 0
        System.out.println(s.ordinal());

        Status[] sArr = Status.values();
        for(Status status : sArr) //sArr can be replaced by Status.values()
            System.out.println(status + " " + status.ordinal());

        switch (s) {
            case Running:
                System.out.println();
                break;
            case Failed:
                System.out.println();
                break;

        }
    }
}
