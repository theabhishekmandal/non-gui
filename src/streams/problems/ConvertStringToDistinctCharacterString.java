package streams.problems;

import java.util.Random;
import java.util.stream.Collectors;

/**
 * Given a string of characters filter out the all the distinct elements from the string
 */
public class ConvertStringToDistinctCharacterString {
    public static void main(String[] args) {
        var random = new Random();
        var string = random.ints(0, 26)
                .limit(1000000)
                .mapToObj(x -> String.valueOf((char) (x + 65)))
                .collect(Collectors.joining());
        System.out.println(string);


        // first way
        var builder = new StringBuilder();
        string.codePoints().distinct().forEach(x -> builder.append((char)x));
        var distinctString = builder.toString();
        System.out.println(distinctString);

        // second way
        var distinctStringTwo = string.codePoints()
                .boxed()
                .distinct()
                .map(x -> String.valueOf((char) x.intValue()))
                .collect(Collectors.joining());

        System.out.println(distinctStringTwo);
    }
}
