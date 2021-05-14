package abstract_interfaces;
/**
 * An interface can have a default method and as well as a static method
 * in it
 */

interface Number {
    default String getString() {
        return "returning string";
    }
    static String getName() {
      return "Abhishek Mandal";
    }
}


public class InterfaceStaticMethodDemo implements Number {
    public static void main(String[] args) {
        var ob = new InterfaceStaticMethodDemo();

        //calling interface default method
        System.out.println(ob.getString());

        //calling interface static method
        System.out.println(Number.getName());
    }
}
