package effective_java.clone;

import java.util.Objects;

/**
 * Example showing subclass using the super.clone() method on the super class where
 * the clone() implementation has been done using constructor.
 * This will cause issue of class cast exception.
 */
public class CloningFromSubClass {
    static class One implements Cloneable {
        int num;

        public One(int num) {
            this.num = num;
        }

        @Override
        public int hashCode() {
            return Objects.hash(num);
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
    }

    static class Two extends One implements Cloneable {
        int num2;
        public Two(int num, int num2) {
            super(num);
            this.num2 = num2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            Two two = (Two) o;
            return num2 == two.num2;
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), num2);
        }

        @Override
        public Two clone() {
            Two clone = (Two) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        }
    }

    public static void main(String[] args) {
        var two = new Two(1, 2);
        var clonedTwo = two.clone();
        System.out.println(two.equals(clonedTwo));
        System.out.println(two == clonedTwo);
    }
}
