package streams.problems;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CombineLists {
    public static void main(String[] args) {
        var one = Stream.of(1, 2, 3).map(Objects::toString).collect(Collectors.toList());
        var two = Stream.of(4, 5, 6).map(Objects::toString).collect(Collectors.toList());
        String three = "7";

        // 1st method
        var finalList = Stream.of(Collections.singletonList(three), one, two)
                .flatMap(Collection::stream).sorted().collect(Collectors.toList());
        System.out.println(finalList);

        //2nd method
        finalList = Stream.concat(one.stream(), Stream.concat(two.stream(), Stream.of(three)))
                .sorted().collect(Collectors.toList());
        System.out.println(finalList);

    }
}
