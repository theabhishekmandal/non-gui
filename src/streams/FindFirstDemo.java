package streams;

import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * findFirst() returns an Optional for the first entry in the stream; the Optional can, of course, be empty
 */
public class FindFirstDemo {
    public static void main(String[] args) {

        OptionalInt opt = IntStream.range(0, 10)
                .filter(x -> x % 2 == 0)
                .findFirst();
        // find first element that is even
        if(opt.isPresent()) {
            System.out.println(opt.getAsInt());
        }
    }
}
