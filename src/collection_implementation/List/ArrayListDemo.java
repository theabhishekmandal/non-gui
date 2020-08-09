package collection_implementation.List;

import java.util.*;
import static java.lang.System.out;

/**
 *  Implementation of Arraylist:
 *  ArrayList is basically used for dynamic array or variable length array.
 *  ArrayList extends AbstractList and implements the list interface.
 *
 *  The constructors defined are:
 *
 *  1 ArrayList()                       :   builds an empty array list.
 *
 *  2 ArrayList(Collection<?
 *      extends E> c)                   :   builds an array list that is initialized with the
 *                                          elements of the collection c.
 *
 *  3 ArrayList(int capacity)           :   builds an array list that has the specified initial capacity.
 */
public class ArrayListDemo
{
    public static void main(String args[])
    {
        // creating an arrayList
        ArrayList<Object> arr=new ArrayList<>();

        // adding values to arraylist
        arr.add(1);
        arr.add("hello");
        arr.add(12.00000000);
        arr.add(1e9);
        arr.add(null);
        arr.add(null);
        out.println("Arraylist values " + arr);

        String arg[] = {"hey", "hello", "how are you doing"};
        // adding all the elements to the invoking arraylist
        arr.addAll(Arrays.asList(arg));

        // checking if the arraylist contains a specific value
        boolean check = arr.contains(1e9);
        out.println("checking if the arraylist contains 1e9\n " + check);

        ArrayList<String> blah = new ArrayList<>();
        out.println("checking if two arraylist are same\n " + arr.equals(blah));

        // returning the hashcode of the invoking collection
        out.println("returning the hashcode of arraylist\n " + arr.hashCode());

        // checking if the arraylist is empty
        out.println("checking if the arraylist is empty\n " + blah.isEmpty());

        // using iterator to print the values
        out.println("printing the values using iterator");
        Iterator it = arr.iterator();
        while(it.hasNext()) out.print(it.next() + " ");
        out.println();

        // removing an object from the arrayslist
        out.println("removing 1e9 from arraylist \n" + arr.remove(1e9));

        // remove all the elements from the collection which are present in the passed collection
        out.println("removing all elements \n" + arr.removeAll(Arrays.asList(arg)));

        // removing all the elements except those which are passed in the given collection
        arr.addAll(Arrays.asList(arg));
        out.println("keeping the common elements \n" + arr.retainAll(Arrays.asList(arg)));

        // converting list to array
        out.println("list to array \n" + Arrays.toString(arr.toArray()));
    }
}
