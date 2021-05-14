package abstract_interfaces;

import java.util.Objects;
import java.util.Random;

/**
 * When you want some common method should only be used by the interface only and not the implementing class then, it should
 * be a private method
 */
interface Default {
    Random random = new Random();

    private int compute() {
       return Objects.hash(random.nextInt(20));
    }

    default String randomHello() {
       return "" + compute();
    }

    static void hello() {
        System.out.println("static method");
    }
}

class DefaultImpl implements Default {

    @Override
    public String randomHello() {
        return "overridden default method";
    }
}

public class DefaultAndPrivateMethods {
    public static void main(String[] args) {
        var obj = new DefaultImpl();
        System.out.println(obj.randomHello());
    }
}
