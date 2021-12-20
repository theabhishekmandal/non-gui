package effective_java.equals;

import java.util.ArrayList;
import java.util.List;

/**
 *  Reflexive says that, an object should be equal to itself.
 *  By default, Object equals contract checks for reflexivity, but here we are
 *  breaking it.
 */
public class ReflexiveDemo {
    static class Test {
        private final int a;
        Test(int a) {
            this.a = a;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return false;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        var test = new Test(1);

        List<Test> list = new ArrayList<>();
        list.add(test);

        System.out.println(list.contains(test));
    }
}
