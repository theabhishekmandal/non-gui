package Collection_demo.Comparator;
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
    private String name;
    private long longval;
    private int intval;
    private double doubleval;

    public Record(String name, long longval, int intval, double doubleval) {
        this.name = name;
        this.longval = longval;
        this.intval = intval;
        this.doubleval = doubleval;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "Record{" +
                "name='" + name + '\'' +
                ", longval=" + longval +
                ", intval=" + intval +
                ", doubleval=" + doubleval +
                '}' + "\n";
    }

    public long getLongval() {
        return this.longval;
    }

    public int getIntval() {
        return this.intval;
    }

    public double getDoubleval() {
        return this.doubleval;
    }
}
public class ComparatorDemo4 {
    public static void main(String[] args) {
        Comparator<Record> comparator1 = Comparator.comparing(Record::getName).thenComparingInt(Record::getIntval);
        Comparator<Record> comparator2 = Comparator.comparing(Record::getName).thenComparingDouble(Record::getDoubleval);
        Comparator<Record> comparator3 = Comparator.comparing(Record::getName).thenComparingLong(Record::getLongval);

        ArrayList<Record> arr = new ArrayList<>();
        arr.add(new Record("abhishek", 12L, 13, 23.0));
        arr.add(new Record("abhishek", 15L, 19, 20.0));
        arr.add(new Record("abhishek", 18L, 16, 17.0));
        arr.add(new Record("abhishek", 21L, 22, 14.0));

        arr.sort(comparator1);
        out.println(arr);

        arr.sort(comparator2);
        out.println(arr);

        arr.sort(comparator3);
        out.println(arr);
    }
}
