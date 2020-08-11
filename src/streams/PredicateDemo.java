package streams;

import java.util.function.Predicate;

/**
 * Predicate is basically used for boolean condition
 */

public class PredicateDemo {
    public static void print(int num, Predicate<Integer> pred, String string){
        System.out.println(num + " " + string + " : " + pred.test(num));
    }
    public static void main(String[] args) {
        Predicate<Integer> isEven = x -> x % 2 == 0;
        Predicate<Integer> isGT4 = x -> x > 4;
        print(2, isEven, "? is even");
        print(2, isGT4, "? is > 4");
        print(2, isEven.and(isGT4), "? is even and > 4");
        print(2, isEven.or(isGT4), "? is even or > 4");
        print(5, isEven.or(isGT4), "? is even or > 4");
        print(6, isEven.or(isGT4), "? is even or > 4");
    }
}
