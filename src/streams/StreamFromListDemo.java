package streams;

import java.util.Arrays;
import java.util.List;

public class StreamFromListDemo {
    public static void main(String[] args) {
        List<String> people = Arrays.asList("A1", "Ankit", "Brent", "Sarika", "amanda", "Hans", "Shivika");
        people.stream()
                .map(String::toLowerCase)
                .filter(x -> x.startsWith("a"))
                .forEach(System.out::println);
    }
}
