package collection_implementation.iterator;

import java.util.ArrayList;

import static java.lang.System.out;

/**
 * Demonstrate iterators
 */
public class Example {
    public static void main(String[] args) {

        // Create an array list.
        ArrayList<String> al = new ArrayList<>();

        // Add elements to the array list.
        al.add("A");
        al.add("B");
        al.add("C");
        al.add("D");
        al.add("E");
        al.add("F");

        // use iterator to display contents of al.
        out.print("Original contents of al: ");
        var itr = al.iterator();
        while (itr.hasNext()) {
            String element = itr.next();
            out.print(element + " ");
        }
        out.println();

        // Modify objects being iterated.
        var litr = al.listIterator();
        while (litr.hasNext()) {
            String element = litr.next();
            litr.set(element + "+");
        }
        out.print("Modified contents of al: ");
        itr = al.iterator();
        while (itr.hasNext()) {
            String element = itr.next();
            out.print(element + " ");
        }
        out.println();

        // Now display the list backwards
        out.print("Modified list backwards: ");
        while (litr.hasPrevious()) {
            String element = litr.previous();
            out.print(element + " ");
        }
        out.println();
    }
}
