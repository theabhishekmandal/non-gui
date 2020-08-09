package collection_implementation.comparator;
import java.util.*;

import static java.lang.System.out;

/**
 *  Further methods of Comparator are:
 *
 *  1 default Comparator<T> thenComparingDouble
 *   (ToDoubleFunction<? super T> getKey)           :   returns a thenComparing comparator for double type
 *
 *  2 default Comparator<T> thenComparingInt
 *    (ToIntFunction<? super T> getKey)             :   returns a thenComparing comparator for integer type
 *
 *  3 default Comparator<T> thenComparingLong
 *    (ToLongFunction<? super T> getKey)            :   returns a thenComparing comparator for long type
 *
 */
class Record{
    private final String name;
    private final long longVal;
    private final int intVal;
    private final double doubleVal;

    public Record(String name, long longVal, int intVal, double doubleVal) {
        this.name = name;
        this.longVal = longVal;
        this.intVal = intVal;
        this.doubleVal = doubleVal;
    }

    public String getName() {
        return name;
    }

    public long getLongVal() {
        return longVal;
    }

    public int getIntVal() {
        return intVal;
    }

    public double getDoubleVal() {
        return doubleVal;
    }

    @Override
    public String toString() {
        return "Record{" +
                "name='" + name + '\'' +
                ", longVal=" + longVal +
                ", intVal=" + intVal +
                ", doubleVal=" + doubleVal +
                '}';
    }
}
public class ComparatorDemo4 {
    public static void main(String[] args) {

        ArrayList<Record> arr = new ArrayList<>();
        arr.add(new Record("abhishek", 12L, 13, 23.0));
        arr.add(new Record("abhishek", 15L, 19, 20.0));
        arr.add(new Record("abhishek", 18L, 16, 17.0));
        arr.add(new Record("abhishek", 21L, 22, 14.0));

        out.println("implementing thenComparingInt method");
        Comparator<Record> comparator1 = Comparator.comparing(Record::getName)
                                        .thenComparingInt(Record::getIntVal);
        arr.sort(comparator1);
        out.println(arr);
        out.println();

        out.println("implementing thenComparingDouble method");
        Comparator<Record> comparator2 = Comparator.comparing(Record::getName)
                                        .thenComparingDouble(Record::getDoubleVal);
        arr.sort(comparator2);
        out.println(arr);
        out.println();

        out.println("implementing thenComparingLong method");
        Comparator<Record> comparator3 = Comparator.comparing(Record::getName)
                                        .thenComparingLong(Record::getLongVal);
        arr.sort(comparator3);
        out.println(arr);
        out.println();
    }
}
