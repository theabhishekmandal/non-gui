package enumeration_and_autoboxing;

import java.util.Random;

/**
 * This program is an example of enumeration
 * In this we use some of the basic things that we can do with enum constants
 * we can compare them by using == operator
 * we can use switch statements in them.
 * Also we have two methods values() and valuesOf(String)
 * the value() method returns an array that contains the list of enumerations constants
 * and the valueOf(String) method returns the matching enum constant that is passed as an argument
 */
enum Apple {
    JONATHAN, GOLDEN_DEL, RED_DEL, WINESAP, CORTLAND;
}

public class EnumDemo {
    public static void main(String[] args) {

        var random = new Random();

        var ap = Apple.RED_DEL;
        System.out.println("value of ap: " + ap);

        var values = Apple.values();
        ap = values[random.nextInt(values.length)];

        //compare two enum values
        if (ap == Apple.GOLDEN_DEL) {
            System.out.println("ap contains: " + ap);
        }

        //use a enum to control enum statement
        switch (ap) {
            case JONATHAN:
                System.out.println("Jonathan is Red");
                break;
            case GOLDEN_DEL:
                System.out.println("Golden Delicious is yellow");
                break;
            case RED_DEL:
                System.out.println("Red Delicious is red");
                break;
            case WINESAP:
                System.out.println("winesap is red");
                break;
            case CORTLAND:
                System.out.println("Cortland is red");
                break;
        }

        var app = Apple.valueOf("WINESAP");
        System.out.println(app);

        var arr = Apple.values();
        for (Apple i : arr)
            System.out.print(i + " ");
    }
}
