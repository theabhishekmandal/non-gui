package design_patterns.singleton;

/**
 * Simple example showing how to create a singleton class using public final field.
 * Singleton class is that class which is instantiated only once.
 */
class Presley {
    private Presley() {
        this.name = "Presley";
    }
    public static final Presley PRESLEY = new Presley();
    private final String name;

    public String getName() {
        return name;
    }
}
public class Singleton {
    public static void main(String[] args) {
        System.out.println(Presley.PRESLEY.getName());
    }
}
