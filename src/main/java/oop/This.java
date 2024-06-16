package oop;

public class This {
    //two way of this
    private String name;
    public void setThisWithObj(String name, This t1) {
        // wrong: name = name;
        t1.name = name;
    }
    public void setThis(String name) {
        this.name = name;
    }
    public void showName() {
        System.out.println(name);
    }

}
