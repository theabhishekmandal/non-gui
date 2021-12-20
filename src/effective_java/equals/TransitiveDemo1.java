package effective_java.equals;

/**
 * Transitive says that for three objects x, y and z
 *  -   if x.equals(y) and y.equals(z), then x.equals(z) should also be true
 */
public class TransitiveDemo1 {
    public static void main(String[] args) {
        var point = new Point(1, 2);
        var colorPoint = new ColorPoint(1, 2, Color.RED);

        // first one will give the result as true, but the second one will give result as false
        // because, ColorPoint equals method expecting point to be of ColorPoint type
        // So, to avoid this if the point is of Point type, then we have to do ColorBlind
        // comparison.
        System.out.println(point.equals(colorPoint));
        System.out.println(colorPoint.equals(point));
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
            if (o == this) {
                return true;
            }
            if (!(o instanceof Point)) {
                return false;
            }
            Point point = (Point)o;
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
            if (o == this) {
                return true;
            }
            if (!(o instanceof ColorPoint)) {
                return false;
            }
            return super.equals(o)
                    && this.color == ((ColorPoint) o).color;
        }
    }
    enum Color {
        RED, BLUE, GREEN
    }
}
