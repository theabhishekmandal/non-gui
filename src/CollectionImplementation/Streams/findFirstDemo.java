package CollectionImplementation.Streams;

import java.util.stream.IntStream;

/*
    findFirst() returns an Optional for the first entry in the stream; the Optional can, of course, be empty
 */
public class findFirstDemo {
    public static void main(String[] args) {

        // find first element that is even
        System.out.println(
        IntStream.range(0, 10)
                .filter(x -> x % 2 == 0)
                .findFirst().getAsInt());

    }
}
