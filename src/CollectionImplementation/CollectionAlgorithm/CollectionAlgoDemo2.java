package CollectionImplementation.CollectionAlgorithm;

import java.util.*;

import static java.lang.System.out;
import java.math.*;

/**
 * Below is the implementation of several methods in Collections for List:
 *
 *  1 static <T> int binarySearch(List<? extends T>
 *    list, T value, Comparator<? super T> c)       :   searches for  value in list ordered according to c. Returns the
 *                                                      position of value in list, or negative value if value is not
 *                                                      found.
 *
 *  2 static <T> int binarySearch(List<? extends
 *    Comparable <? super T>> list, T value)        :   searches for value in list. The list must be sorted. Returns the
 *                                                      position of value in list, or a negative value if value is not
 *                                                      found.
 *
 *  3 static <T> void copy(List<? super T> list1,
 *    List<? extends T> list2)                      :   copies the elements of list2 to list1. The added elements should
 *                                                      have a type which should be the subclass of the list1 type
 *                                                      Destination list should be large enough
 *                                                      that it can contain all the elements of the source list otherwise
 *                                                      it will throw indexOutOfBoundException.
 *                                                      Also for copy that number of elements should be present in the
 *                                                      list. Also, copy method replaces the previous value from the list
 *
 *  4 static <E> List<E>
 *     checkedList(List<E> c, Class<E> t)           :   returns a run-time type-safe view of a List. An attempt to insert
 *                                                      an incompatible element will cause a ClassCastException.
 *
 *  5 static <T> void fill(List<? super T> list,
 *      T obj)                                      :   assigns obj to each element of list.
 *
 *  6 static int indexOfSubList(List<?> list,
 *      List<?> subList)                            :   searches list for the first occurrence of subList. Returns the
 *                                                      index of the first match, or -1 if no match is found.
 *
 *  7 static int lastIndexOfSubList(List<?> list,
 *      List<?> subList)                            :   searches list for the last occurrence of subList. Returns the
 *                                                      index of the last match, or -1 if no match is found.
 *
 *  8 static <T> List<T> nCopies(int num, T obj)    :   returns num copies of obj contained in an immutable list. num
 *                                                      must be greater than or equal to zero.
 *
 *  9 static <T> boolean replaceAll(List <T> list,
 *      T old, T new)                               :   replaces all occurrences of old with new in list. Returns true
 *                                                      if at lest one replacement occurred. Returns false otherwise.
 *
 *  10 static void reverse(List<T> list)            :   reverse the sequences in the list.
 *
 *  11 static void rotate(List<T> list, int n)      :   rotates list by n places to the right. To rotate left, use a
 *                                                      negative value for n.
 *
 *  12 static void shuffle(List<T> list, Random r)  :   Shuffles the elements in list by using r as a source of random
 *                                                      numbers.
 *
 *  13 static void shuffle(List<T> list)            :   Shuffles the elements in list.
 *
 *  14 static <T> List<T> singletonList(T ob)       :   returns obj as an immutable list. This is an easy way to convert
 *                                                      a single object into a list.
 *
 *  15 static <T extends Comparable<? super T>>
 *      void sort(List<T> list)                     :   sort the elements of the list as determined by their natural
 *                                                      sorting order.
 *
 *  16  static <T> void sort(List<T> list,
 *      Comparator<? super T> comp)                 :   sort the elements of the list as determined by comp.
 *
 *  17 static void swap(List<?> list, int idx1,
 *      int idx2)                                   :   exchanges the elements in list at the indices specified by
 *                                                      idx1 and idx2.
 *
 *  18 static <T> List<T> synchronizedList(List<T>
 *      list)                                       :   returns a thread-safe list backed by list.
 *
 *  19 static <T> List<T> unmodifiableList(
 *      List<? extends T> list)                     :   returns an unmodifiable list backed by list.
 *
 *  20 static <T> List<T> emptyList()               :   returns an immutable, empty List object of the inferred type.
 *
 *  21 static <T> ArrayList<T> list(Enumeration
 *      <T> enum)                                   :   returns a ArrayList that contains the elements of enum.
 *
 */
public class CollectionAlgoDemo2 {
    public static void main(String[] args) {

        ArrayList<String> arr = new ArrayList<>();

        // adding elements
        arr.add("one");
        arr.add("two");
        arr.add("three");
        arr.add("four");
        arr.add("five");

        // implementing binarySearch method for unsorted list
        out.println("implementing binarySearch method for unsorted list");
        int position = Collections.binarySearch(arr, "five", Comparator.reverseOrder());
        out.println(position + " " + arr.get(position));
        out.println();

        // implementing binarySearch method for sorted list in ascending order
        // this binary search method only works for ascending order
        out.println("implementing binarySearch method for sorted list in ascending order");
        Collections.sort(arr);
        position = Collections.binarySearch(arr, "five");
        out.println(position + " " + arr.get(position));
        out.println();

        // without using checkedList method
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        // if the generic list is passed to non-generic list then the non-generic list will not ensure
        // that the values inserted will be type safe
        // to ensure type safety checkedList method is used
        List unchecklist = list;
        unchecklist.add("hello");
        unchecklist.add(23.07);
        out.println("not type safe "  + unchecklist);
        // implementing checkedList method
        list = Collections.checkedList(list, Integer.class);
        List checkedList = list;
        checkedList.add(3);
        checkedList.add(4);
        // checkedList.add("hello"); this will throw error
        out.println("type safe " + checkedList);
        out.println();

        // implementing copy method
        // increasing the size of the destination list so as to avoid indexOutOfBound
        out.println("implementing copy method");
        List<Integer> arr2 = Arrays.asList(1, 2, 3, 4);
        ArrayList<Integer> arr1 = new ArrayList<>();
        for(int i = 0; i < 100; i++) arr1.add(5 + i);
        Collections.copy(arr1,arr2);
        arr1.sort(Comparator.naturalOrder());
        out.println(arr2);
        out.println();

        // implementing fill method
        // for this method to work arrayList must contain some values
        out.println("implementing fill method");
        ArrayList<String> emptylist = new ArrayList<>();
        emptylist.add("1");
        emptylist.add("2");
        Collections.fill(emptylist, "Nothing");
        out.println(emptylist);
        out.println();

        // implementing indexOfSubList method
        out.println("implementing indexOfSubList method");
        ArrayList<Integer> intlist = new ArrayList<>();
        for(int i = 1; i <= 2; i++)
            for(int j = 1; j <= 5; j++)
                intlist.add(j);
        // temp arraylist
        List<Integer> templist = new ArrayList<>();
        templist.add(1);
        templist.add(2);
        templist.add(3);
        out.println(Collections.indexOfSubList(intlist, templist));
        out.println();

        // implementing lastIndexOfSubList method
        out.println("implementing lastIndexOfSubList method");
        out.println(Collections.lastIndexOfSubList(intlist, templist));
        out.println();

        // implementing nCopies method
        out.println("implementing nCopies method");
        ArrayList<Integer> ncopylist = new ArrayList<>(Collections.nCopies(10 , 1));
        out.println(ncopylist);
        out.println();

        // implementing replaceAll method
        out.println("implementing replaceAll method");
        ArrayList<Character> charlist = new ArrayList<>();
        for(int i = 0; i < 2; i++)
            for(char ch = 'a'; ch <= 'z'; ch++)
                charlist.add(ch);
        // replacing all occurrences of a with 1
        Collections.replaceAll(charlist, 'a', '1');
        out.println(charlist);
        out.println();

        // implementing reverse method
        out.println("implementing reverse method");
        Collections.reverse(charlist);
        out.println(charlist);
        out.println();

        // implementing rotate method
        out.println("implementing rotate method");
        List<String> rotatelist = new ArrayList<>();
        rotatelist.add("hey");
        rotatelist.add("this");
        rotatelist.add("is");
        rotatelist.add("Abhishek");
        rotatelist.add("Mandal");

        Collections.rotate(rotatelist, 2);
        out.println(rotatelist);
        Collections.rotate(rotatelist, -2);
        out.println(rotatelist);
        out.println();

        // implementing shuffle method with random parameter
        out.println("implementing shuffle method with random parameter");
        List<String> shufflelist = new ArrayList<>();
        shufflelist.add("1");
        shufflelist.add("2");
        shufflelist.add("3");
        shufflelist.add("4");
        Random random = new Random();
        Collections.shuffle(shufflelist, random);
        out.println(shufflelist);
        out.println();

        // implementing shuffle method
        out.println("implementing shuffle method");
        shufflelist = new ArrayList<>();
        shufflelist.add("hello");
        shufflelist.add("world");
        shufflelist.add("everyone");
        Collections.shuffle(shufflelist);
        out.println(shufflelist);
        out.println();

        // implementing singletonList method
        out.println("implementing singletonList method");
        List<String> singletonlist = Collections.singletonList("hello world");
        out.println(singletonlist);
        out.println();

        // implementing sort method without comparator
        out.println("implementing sort method without compartor");
        List<String> sortlist = Arrays.asList("one", "two", "three", "four", "five");
        Collections.sort(sortlist);
        out.println(sortlist);
        out.println();

        // implementing sort method with comparator
        out.println("implementing sort method with comparator");
        List<String> sortlistwithcomparator = Arrays.asList("one", "two", "three", "four", "five");
        Collections.sort(sortlistwithcomparator, Comparator.comparing(String::length).thenComparing(Comparator.naturalOrder()));
        out.println(sortlistwithcomparator);
        out.println();

        // implementing swap list method
        out.println("swapping the first and last values from the previous list");
        Collections.swap(sortlistwithcomparator, 0, sortlistwithcomparator.size() - 1);
        out.println(sortlistwithcomparator);
        out.println();

        // implementing synchronizedList method
        out.println("implementing synchronized list method");
        List<String> synclist = Collections.synchronizedList(Arrays.asList("one", "two", "three"));
        out.println(synclist);
        out.println();

        // implementing unmodifiableList method
        out.println("implementing unmodifiableList method");
        List<String> unmolist = Collections.unmodifiableList(Arrays.asList("one", "two", "three"));
        out.println(unmolist);
        out.println();

        // implementing emptyList method
        out.println("implementing emptyList method");
        List<Integer> emptyList = Collections.emptyList();
        // any addition will cause exception
        // emptyList.add(1);
        out.println(emptyList);
        out.println();

        // implementing list method of the enumeration parameter
        out.println("implementing list method of the enumeration parameter");
        Enumeration<objects> enumobjects= Collections.enumeration(Arrays.asList(objects.one, objects.two, objects.three));
        List<objects> enumlist = Collections.list(enumobjects);
        out.println(enumlist);
        out.println();
    }
}
enum objects{
    one, two, three
}
