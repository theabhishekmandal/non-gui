package method_overriding_and_overloading;

import java.util.Random;

/**
 * Example of Runtime Polymorphism
 */
abstract class Shape {
    protected final double length;
    protected final double breadth;
    protected static final Random random = new Random();

    protected Shape(double length, double breadth) {
        this.length = length;
        this.breadth = breadth;
    }

    public abstract void area();

    public static void staticMethod() {
        System.out.println("hello from abstract class " + random.nextInt(10));
    }
}

class Triangle extends Shape {

    public Triangle(double length, double breadth) {
        super(length, breadth);
    }

    @Override
    public void area() {
        System.out.println("Area of triangle is " + ((length * breadth) / 2));
    }

    public static void staticMethod() {
        System.out.println("hello from Triangle class " + random.nextInt(10));
    }
}

class Rectangle extends Shape {

    public Rectangle(double length, double breadth) {
        super(length, breadth);
    }

    @Override
    public void area() {
        System.out.println("Area of rectangle is " + (length * breadth));
    }


    public static void staticMethod() {
        System.out.println("hello from Rectangle class " + random.nextInt(10));
    }
}

public class RunTimePolyMorphismExample {
    public static void main(String[] args) {
        var triangle = new Triangle(5, 5);
        var rectangle = new Rectangle(6, 7);
        Rectangle.staticMethod();

        Shape shape = triangle;
        shape.area(); // area of triangle will be called
        Shape.staticMethod();

        shape = rectangle;
        shape.area(); // area of rectangle will be called
        Shape.staticMethod();
    }
}
