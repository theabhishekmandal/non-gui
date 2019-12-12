package CollectionImplementation.Streams;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/*
    A stream can hold complex data structures like Stream<List<String>>.
     In cases like this, flatMap() helps us to flatten the data structure to simplify further operations
 */

public class FlatMapDemo {
    public static void main(String[] args) {
        List<List<String>> namesNested = Arrays.asList(
                Arrays.asList("Jeff", "Bezos"),
                Arrays.asList("Bill", "Gates"),
                Arrays.asList("Mark", "Zuckerberg"));

        List<String> namesFlatStream = namesNested.stream()
                                        .flatMap(Collection::stream)
                                        .collect(Collectors.toList());
        namesFlatStream.forEach(System.out::println);
    }
}
