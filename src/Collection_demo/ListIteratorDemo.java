package Collection_demo;

import java.util.ArrayList;
import java.util.ListIterator;

import static java.lang.System.out;

public class ListIteratorDemo {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4);
        arr.add(5);
        arr.add(6);
        arr.add(7);
        arr.add(8);
        arr.add(9);
        arr.add(10);

        ListIterator it = arr.listIterator();
        // traversing in forward direction
        out.println("traversing in forward direction");
        while(it.hasNext()){
            out.println(it.next() + " " + it.nextIndex());
        }

        // now for traversing in reverse direction iterator must be it at last index
        out.println("traversing in reverse direction");
        while(it.hasPrevious()){
            out.println(it.previous() + " "+ it.previousIndex());
        }

        // for each remaining implementation
        it.forEachRemaining(System.out::println);
    }
}
