package Streams;

import java.util.ArrayList;
import java.util.List;

/*
    map() produces a new stream after applying a function to each element of the original stream
 */
public class MapDemo {
    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>();
        for(int i = 0; i < 10; i++) arr.add(i);

        arr.stream()
                .map(x -> x * x)
                .forEach(System.out::println);
    }
}
