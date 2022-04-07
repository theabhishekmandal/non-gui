package design_patterns.singleton;

/**
 * Third way of creating singleton instance.
 * Advantages of creating this
 *  -   Provides serialisation solution, by not creating multiple instances
 *  -   also guarantees against multiple instantiation.
 *
 * Disadvantages
 *  -   cannot be used when singleton class needs to be extended from superclass.
 */
enum Bob {
    INSTANCE("name");

    Bob(String name) {
        this.name = name;
    }
    private final String name;

    public String getName() {
        return name;
    }
}
public class Singleton3 {
    public static void main(String[] args) {
        System.out.println(Bob.INSTANCE.getName());
    }
}
