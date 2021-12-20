package effective_java.equals;

import java.util.Objects;

public class TransitiveDemo4 {
    public static void main(String[] args) {
        var p2 = new Point(1, 2);
        var p1 = new ColorPoint(p2, Color.RED);
        var p3 = new ColorPoint(p2, Color.BLUE);

        /*
            Now, here liskov principle does not apply since, we are not extending
            Point, We prefer to use composition over inheritance.
         */

        System.out.println(p1.equals(p2));
        System.out.println(p2.equals(p3));
        System.out.println(p1.equals(p3));
    }
    static class Point {
        private final int x;
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    // Adds value component without violating the equals contract
    static class ColorPoint {
        private final Point point;
        private final Color color;

        public ColorPoint(Point point, Color color) {
            this.point = point;
            this.color = color;
        }

        private Point asPoint() {
            return point;
        }

        private Color getColor() {
            return color;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ColorPoint)) {
                return false;
            }
            ColorPoint cp = (ColorPoint) o;
            return cp.point.equals(point) && cp.color.equals(color);
        }
    }
    enum Color {
        RED, BLUE
    }
}
