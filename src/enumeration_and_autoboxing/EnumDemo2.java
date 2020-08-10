package enumeration_and_autoboxing;

import java.util.Random;

/**
 * Remember you cannot declare "enum constants" after the instance variables and methods of the enumeration
 */
enum Orange {
    GREEN(10), YELLOW(12), RED(20), RED_DEL(30);

    private final int price;
    Orange(int p) {
        price=p;
    }
    int getPrice()
    {
        return price;
    }
}

public class EnumDemo2 {
    public static void main(String[] args) {
        Random random = new Random();

        //Getting all the values of the enum constants
        for(Orange i : Orange.values()) {
            System.out.println("Price of " + i + " is " + i.getPrice());
        }

        Orange[] values = Orange.values();
        //using the equals method to compare two different enumeration values
        System.out.println(Orange.RED_DEL.equals(values[random.nextInt(values.length)]));
    }
}
