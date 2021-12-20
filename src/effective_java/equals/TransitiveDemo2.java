package effective_java.equals;

public class TransitiveDemo2 {
    public static void main(String[] args) {
        var p1 = new ColorPoint(1, 2, Color.RED);
        var p2 = new Point(1, 2);
        var p3 = new ColorPoint(1, 2, Color.BLUE);

        /*
            Here, we can now compare, a point and a colorPoint object,
            therefore enabling symmetry, but at the cost of transitivity

            p1.equals(p2) -> true
            p2.equals(p3) -> true
            but p1.equals(p3) gives false,

            In the first two comparisons does not take Color comparison to picture,
            but the last one takes, thus violating transitivity.
         */
        System.out.println(p1.equals(p2));
        System.out.println(p2.equals(p3));
        System.out.println(p1.equals(p3));

        /*
            Also, the equals method implementation, can also cause StackOverflow error,
            As Two different subclasses of Point will call each other equals method infinitely.
         */
        var smellPoint = new SmellPoint(1, 2, false);
        System.out.println(p1.equals(smellPoint));
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
            if (o == this) {
                return true;
            }
            if (!(o instanceof ColorPoint)) {
                return o.equals(this);
            }
            return super.equals(o)
                    && this.color == ((ColorPoint) o).color;
        }
    }

    static class SmellPoint extends Point {
        private final boolean smell;

        SmellPoint(int x, int y, boolean smell) {
            super(x, y);
            this.smell = smell;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof SmellPoint)) {
                return o.equals(this);
            }

            return super.equals(o) && this.smell == ((SmellPoint) o).smell;
        }
    }

    enum Color {
        RED, BLUE, GREEN
    }
}
