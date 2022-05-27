package reflection.field_object;

import java.lang.reflect.Field;

/**
 * This example showing type of declared fields in the class.
 * Also checking that if a class has synthetic fields or not.
 * In below inner class MovieStats, actualPrice belongs to it's enclosing class Movie.
 * So compiler will generate its field name at runtime.
 *
 * Also, enum are special type of classes, they have a synthetic field called as values which holds all
 * enums in the form of an array.
 *
 * We can also print the values of the instance fields and static fields.
 * For static fields we have to pass field.get(null) to retrieve field value. Since, static fields
 * belong to the class and not to the instance.
 */
public class Main {

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
//        printDeclaredFieldsInfo(Movie.class);
//        printDeclaredFieldsInfo(Movie.MovieStats.class);
//        printDeclaredFieldsInfo(Category.class);

        Movie movie = new Movie("Lord of the Rings", 2001, 300, true, Category.ADVENTURE);
        printDeclaredFieldsInfo(Movie.class, movie);

        Field minPriceStaticField = Movie.class.getDeclaredField("MINIMUM_PRICE");
        System.out.printf("static MINIMUM_PRICE value :%s", minPriceStaticField.get(null));

    }


    public static <T> void printDeclaredFieldsInfo(Class<? extends T> clazz, T instance) throws IllegalAccessException {
        for (Field field : clazz.getDeclaredFields()) {
            System.out.printf("Field name : %s type : %s%n", field.getName(), field.getType().getName());
            System.out.printf("Is Synthetic field : %s%n", field.isSynthetic());
            System.out.printf("Field value is %s", field.get(instance));
            System.out.println();
        }
    }

    public static void printDeclaredFieldsInfo(Class<?> clazz) {
        for (Field field : clazz.getDeclaredFields()) {
            System.out.printf("Field name : %s type : %s%n", field.getName(), field.getType().getName());
            System.out.printf("Is Synthetic field : %s", field.isSynthetic());
            System.out.println();
        }
    }

    public static class Movie extends Product {
        public static final double MINIMUM_PRICE = 10.99;

        private boolean isReleased;
        private Category category;
        private double actualPrice;

        public Movie(String name, int year, double price, boolean isReleased, Category category) {
            super(name, year);
            this.isReleased = isReleased;
            this.category = category;
            this.actualPrice = Math.max(price, MINIMUM_PRICE);
        }

        public class MovieStats {
            private double timesWatched;

            public MovieStats(double timesWatched) {
                this.timesWatched = timesWatched;
            }

            public double getRevenue() {
                return timesWatched * actualPrice;
            }
        }
    }

    public static class Product {
        protected String name;
        protected int year;
        protected double actualPrice;

        public Product(String name, int year) {
            this.name = name;
            this.year = year;
        }
    }

    public enum Category {
        ADVENTURE,
        ACTION,
        COMEDY
    }
}
