package oop;

abstract class Car {
    // No idea implementation of drive, but its needs
    abstract public void drive();
    abstract public void fly();
    public void playMusic() {
        System.out.println("Play music");
    }
}

abstract class Wagon extends Car {
    @Override
    public void drive() {
        System.out.println("Wagon have drive code!");
    }
}

class FlyCar extends Wagon {
    @Override
    public void fly() {
        System.out.println("flyCar can fly");
    }
}

public class AbstractMethod {
    public static void main(String[] a) {
        // Not Allow abstract class creation
        // Car car = new Car();
        // Allow if no Fly
        /*
        Car car = new Wagon(); // refer Wagon to car, so car know Wagon drive() method
        Wagon wagon = new Wagon();
        */
        // With fly
        Car car = new FlyCar();
        Wagon wagon = new FlyCar();
    }
}
