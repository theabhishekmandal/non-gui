package miscellaneous;

/**
 * This is an example of how to make a class immutable if it's dependent object is mutable i.e use constructor copying.
 * Use constructor copying when object is initialised as well as in getter methods.
 */

// we have to make this class immutable
class A {
    private final String name;
    private final B b;

    public A(String name, B b) {
        this.name = name;
        // copy constructor
        this.b = new B(b.getName(), b.getNum());
    }

    public String getName() {
        return name;
    }

    public B getB() {
        // copy constructor
        return new B(b.getName(), b.getNum());
    }
}

// this is mutable
class B {
    private String name;
    private int num;

    public B(String name, int num) {
        this.name = name;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public B setName(String name) {
        this.name = name;
        return this;
    }

    public int getNum() {
        return num;
    }

    public B setNum(int num) {
        this.num = num;
        return this;
    }
}

public class ImmutableDemo {
    public static void main(String[] args) {
        var b = new B("hello", 1);
        var a = new A("world", b);

        // mutating b object
        b.setName("new hello");
        System.out.println("name after mutating b object " + b.getName() + "\nand in a object " + a.getName());

    }
}
