package effective_java.clone;

import java.util.Objects;

/**
 * This is an example on how we can implement Cloneable and override clone method
 * to clone object using constructor cloning.
 */
public class CloneDemoByConstructorCloning {
    static class One implements Cloneable {
        int num;

        public One(int num) {
            this.num = num;
        }

        @Override
        public One clone() {
            return new One(this.num);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            One one = (One) o;
            return num == one.num;
        }

        @Override
        public int hashCode() {
            return Objects.hash(num);
        }
    }

    public static void main(String[] args) {
        var one = new One(1);
        var clonedOne = one.clone();
        System.out.println("equality check " + one.equals(clonedOne));
        System.out.println("reference check " + (one == clonedOne));
    }
}
