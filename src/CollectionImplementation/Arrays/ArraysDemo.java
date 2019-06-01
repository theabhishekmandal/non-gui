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
 *                                                  source, and the length of the copy is specified by len. If the
 *                                                  copy is longer than the source, then the copy is padded with zeros
 *                                                  , nulls for object arrays and boolean for boolean arrays. If the
 *                                                  copy is smaller than the source then the copy is truncated. If len
 *                                                  is negative then NegativeArraySizeException is thrown.
 *
 *  4 static <T, U> T[] copyOf(U[] source,
 *      int len, Class<? extends T[]>
 *          resultT)                            :   In this, the type of resultT becomes the type fo the array
 *                                                  returned.If source is null then NullPointerException is thrown.
 *                                                  If resultT is incompatible with the type of the source,
 *                                                  an ArrayStoreException is thrown.
 *
 *  5 static <T> T[] copyOfRange(T[] source,
 *      int start, int end)                     :   returns a copy of a range within an array, similar to copyOf method
 *                                                  but here, starting index can also be provided. ArrayIndexOutOfBound
 *                                                  exception is thrown if start is negative or end is greater than the
 *                                                  length of the source. If range is greater than the source, then
 *                                                  the copy is padded with zeros, nulls or false. If start is greater
 *                                                  than end, then IllegalArgumentException is thrown.
 *
 *  6 static <T, U> T[] copyOfRange(U[] source,
 *      int start, int end, Class<? extends T[]>
 *          resultT)                            :   In this, the type of resultT becomes the type of the array returned.
 *                                                  If source is null then NullPointerException is thrown. If resultT is
 *                                                  incompatible with the type of the source, an ArrayStoreException is
 *                                                  thrown. If start is greater than end, then IllegalArgumentException
 *                                                  is thrown.
 *
 *  7 static boolean equals(Object array1[],
 *      Object array2[])                        :   returns true if two arrays are equivalent. Otherwise, returns false.
 *                                                  For Array of type T the method  will work if equals method is
 *                                                  overridden.
 *
 *  8 static boolean deepEquals(Object[] array1,
 *      Object[] array2)                        :   returns true if two arrays having nested arrays are equivalent.
 *                                                  Otherwise, returns false.
 *
 *  9 static void fill(Object[] array, Object
 *      value)                                  :   It assign a value to all elements in an array.
 *
 *  10 static void sort(Object array[])         :   sorts an array in ascending order. There is also another method
 *                                                  which sorts for a given range.
 *
 *  11 static <T> void sort(T[] array,
 *      Comparator<? super T> c)                :   sorting the array by using the comparator c which is used to order
 *                                                  the elements of the array. Can throw ClassCastException if elements
 *                                                  of array being sorted are not comparable. There is also another method
 *                                                  which sorts for a given range.
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

        out.println("implementing copyOf method");
        String[] copyarr = Arrays.copyOf(arraytolist, arraytolist.length + 10);
        out.println(Arrays.toString(copyarr));
        out.println();

        out.println("implementing copyOf method of resultT type");
        // only wrapper class can be used for example: int[] intarray would not work
        Integer[] intarray = {1, 2, 3, 4};
        Number[] floatarray = Arrays.copyOf(intarray, intarray.length + 10, Number[].class);
        out.println(Arrays.toString(floatarray));
        out.println();

        out.println("implementing copyOfRange method");
        Integer[] arr = {1, 2, 3, 4};
        Integer[] intarr = Arrays.copyOfRange(arr, 1, arr.length);
        out.println(Arrays.toString(intarr));
        out.println();

        out.println("implementing copyOfRange method of resultT type");
        Integer[] intarray1 = {1, 2, 3, 4};
        Number[] floatarray1 = Arrays.copyOfRange(intarray1, 0, intarray.length + 10, Number[].class);
        out.println(Arrays.toString(floatarray1));
        out.println();

        out.println("implementing equals method");
        String[] namearray = {"one", "two", "four"};
        String[] namearray2 = {"one", "two", "four"};
        out.println(Arrays.equals(namearray, namearray2));
        out.println();

        out.println("implementing deepEquals method");
        String[][] deepNameArray = {{"one", "five"}, {"two"}, {"three"}};
        String[][] deepNameArray2 = {{"one", "five"}, {"two"}, {"four"}};
        out.println(Arrays.deepEquals(deepNameArray, deepNameArray2));
        out.println();

        out.println("implementing fill method");
        boolean[] boolarray = new boolean[5];
        Arrays.fill(boolarray, false);
        out.println(Arrays.toString(boolarray));
        out.println();

        out.println("implementing sort method");
        Arrays.sort(namearray);
        out.println(Arrays.toString(namearray));
        out.println();

        out.println("implementing sort method with comparator");
        Arrays.sort(namearray, Comparator.reverseOrder());
        out.println(Arrays.toString(namearray));
        out.println();

        out.println("implementing sort method with ranges");
        Arrays.sort(namearray, 1, namearray.length);
        out.println(Arrays.toString(arr));
        out.println();
    }
}
