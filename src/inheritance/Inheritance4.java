package inheritance;

/**
 *this code is example of :
 *                  when a class with private data members is inherited then how would you access these superclass
 *                  data members which are private
 *
 *                  you can use super constructor to initialise the superclass members
 *                  but to use the private data member values you must use getter methods
 */
class Square {
    private final int a;

    Square(int a) {
        this.a = a;
    }

     long squareArea() {
        System.out.println("i am in base class");
        return (long) a * a;
    }

    int getValueOfA() {
        return a;
    }
}

class Rectangle extends Square {
    private final int b;

    Rectangle(int a, int b) {
        super(a);
        this.b = b;
    }

    @Override
    long squareArea() {
        System.out.println("I am in child class");
        return (long) b * getValueOfA();
    }
}

public class Inheritance4 {
    public static void main(String[] args) {
        Square sq = new Rectangle(10,20);
        Rectangle rec = new Rectangle(10,20);
        System.out.println("Area of square is " + sq.squareArea());
        System.out.println("Area of rectangle is " + rec.squareArea());
    }
}
