package design_patterns.singleton;


/**
 * Singleton with static factory method.
 * Advantages of using this
 *  -   It makes clear using the static factory that the class is singleton
 *  -   Gives flexibility that class can be changed to non-singleton without changing the method name
 *  -   A method reference can be used as a supplier Supplier<Elvis> sup = Elvis::getInstance;
 */
class Elvis {
    private static final Elvis elvis = new Elvis();
    private Elvis() {}
    public static Elvis getInstance() {
        return elvis;
    }
}
public class Singleton2 {
    public static void main(String[] args) {
        Elvis elvis = Elvis.getInstance();
        Elvis elvis2 = Elvis.getInstance();
        System.out.println(elvis == elvis2);
    }
}
