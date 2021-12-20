package effective_java.equals;

import java.util.Objects;

public class TransitiveDemo3 {
    public static void main(String[] args) {
        var p1 = new ColorPoint(1, 2, Color.RED);
        var p2 = new Point(1, 2);
        var p3 = new ColorPoint(1, 2, Color.BLUE);

        /*
            Here we are defining the equals contract using the getClass() method,
            and will only return true when the Class type is of same type.

            Since ColorPoint is an instance of Point, it will return false in all the
            below cases.

            But, this violates Liskov principle, such that even a ColorPoint is
            also a Point, and it still needs to function as one, but if fails to do
            so,
         */
        System.out.println(p1.equals(p2));
        System.out.println(p2.equals(p3));
        System.out.println(p1.equals(p3));

    }
    static class Point {
        private final int x;
        private final int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }
    }

    static class ColorPoint extends Point {
        private final Color color;

        ColorPoint(int x, int y, Color color) {
            super(x, y);
            this.color = color;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            ColorPoint that = (ColorPoint) o;
            return color == that.color;
        }

        @Override
        public int hashCode() {
            return Objects.hash(color);
        }
    }

    enum Color {
        RED, BLUE
    }
}
