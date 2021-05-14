package inheritance;

/**
 * this program shows how to use super keyword
 * we can use super keyword to initialise the constructor of superclass
 * if the data members are private then we can access them using the public member functions
 */

class Box {
    private final double len;
    private final double width;
    private final double height;

    Box(double a, double b, double c) {
        len = a;
        width = b;
        height = c;
    }

    public double volume() {
        return len * width * height;
    }
}

class BoxWeight extends Box {
    private final double weight;

    BoxWeight(double a, double b, double c, double d) {
        super(a, b, c);
        this.weight = d;
    }

    public double getWeight() {
        return weight;
    }
}

public class Inheritance3 {
    public static void main(String[] args) {
        var ob = new BoxWeight(10, 20, 30, 40);
        System.out.println("volume of the box weight is " + ob.volume());
        System.out.println("weight of the box is " + ob.getWeight());
    }
}
