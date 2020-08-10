package generics;

/**
 * @param <T>
 * In this program implementation of the wildcard argument "?" is used , it basically means unknown type.
 *
 * For example using the Stats example we want to determine if two stats object have the same average,no matter what
 * type of numeric data each object holds. For example if one object contains the double values 1.0,3.0,2.0 and the
 * other object contains the integer values 2,1 and 3 then the average would be same
 *
 * At first, it will appear that it can be done by passing the sameAvg() with a Stats1<T> argument , and then
 * compare the average of that argument against the invoking object
 *
 * But it won't compile see below
 */
 class Stats1<T extends Number> {
     private final T[] ob;
     Stats1(T[] ob) {
        this.ob = ob;
     }
     public double avg() {
        double sum = 0.0;
        for(T i : ob) {
            sum += i.doubleValue();
        }
        return sum / ob.length;
    }

    /*
    boolean sameAvg(Stats1<T> ob)
    this won't work because ,it will work only with other Stats objects whose type is the same as the invoking object
    For example if the invoking object is of Stats1<Integer> then the parameter ob should also be of type Stats1<Integer>

    To avoid this and to check for different types of Stats1<T> type we use the wildcard argument  "?"
    which means "UNKNOWN TYPE"
     */
    boolean sameAvg(Stats1<?> ob) {
        return this.avg() == ob.avg();
    }
}

public class WildCardArgDemo6 {
     public static void main(String[] args) {
          Integer[] inums = {1, 2, 3, 4, 5};
          Stats1<Integer> iob = new Stats1<>(inums);
          Double[] dnums = {1.0, 2.0, 3.0, 4.0, 5.0};
          Stats1<Double> dob = new Stats1<>(dnums);
          System.out.println(iob.sameAvg(dob));
    }
}
