package CollectionImplementation.Arrays;
import java.util.*;
import static java.lang.System.*;

/**
 * Below is the implementation of several methods for Arrays:
 *
 *  1 static <T> List asList(T... array)        :   returns a List that is backed by a specified array. means both
 *                                                  the array and the list point to the same location. Cannot alter the
 *                                                  contents of the list for this type of list.
 *
 *  2 static <T> int binarySearch(T[] array,
 *      T value, Comparator<? super T> c)       :   uses a binary search to find a specified value. This method must
 *                                                  be applied to sorted arrays.
 *
 *  3 static <T> T[] copyOf(T[] source,
 *      int len)                                :   returns a copy of an array. The original array is specified by
 *                                                  source, and the length of hte copy is specified by len. If the
 *                                                  copy is longer than the source, then the copy is padded with zeros
 *                                                  , nulls for object arrays and boolean for boolean arrays. If the
 *                                                  copy is smaller than the source then the copy si truncated.
 *
 *  4 static <T, U> T[] copyOf(U[] source,
 *      int len, Class<? extends T[]>
 *          resultT)                            :   In this, the type of resultT becomes the type fo the array
 *                                                  returned.
 *
 *  5 static <T> T[] copyOfRange(T[] source,
 *      int start, int end)                     :   returns a copy of a range within an array, similar to copyOf method
 *                                                  but here, starting index can also be provided.
 *
 *  6 static
 */
public class ArraysDemo {
    public static void main(String args[]){

        out.println("implementing asList method");
        String[] arraytolist = {"one", "two", "three"};
        List<String> listofarray = Arrays.asList(arraytolist);
        out.println(listofarray);
        out.println();

        out.println("implementing binarySearch method");
        Arrays.sort(arraytolist);
        out.println(Arrays.binarySearch(arraytolist, "two", Comparator.naturalOrder()));
        out.println();

        out.println("implementing copy of method");
        String[] copyarr = Arrays.copyOf(arraytolist, arraytolist.length + 10);
        out.println(Arrays.toString(copyarr));
        out.println();

        out.println("implementing copy of method of resultT type");
        // only wrapper class can be used for example: int[] intarray would not work
        Integer[] intarray = {1, 2, 3, 4};
        Number[] floatarray = Arrays.copyOf(intarray, intarray.length + 10, Number[].class);
        out.println(Arrays.toString(floatarray));
        out.println();
    }
}
