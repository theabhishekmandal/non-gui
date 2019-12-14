package Streams;

import java.util.ArrayList;
import java.util.List;
/*
    forEach() is simplest and most common operation; it loops over the stream elements,
     calling the supplied function on each element.

    It is a terminal method that is no other stream method cannot be applied after this
 */

public class ForEachDemo {
    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>();
        for(int i = 0; i < 10; i++) arr.add(i);
        arr.forEach(System.out::println);
    }
}
