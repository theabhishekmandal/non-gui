package streams;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TakeWhileAndDropWhileDemo {
    public static void main(String[] args) {
        var list = IntStream.range(0, 10).boxed().collect(Collectors.toSet());


        /*
         * takeWhile takes the elements that match the predicate. And, whenever, it reaches to the element which does not
         * match the predicate, it breaks
         */
        System.out.println("Take while Demo");
        list.stream().takeWhile(x -> x < 5).forEach(System.out::println);

        System.out.println("Drop while Demo");
        /*
            dropWhile is the opposite of takeWhile,  dropWhile drops the elements which is matching to the
            predicate instead of taking them as takeWhile. And, whenever it reaches to the element which does not
            match the predicate, it includes the remaining elements in the returned stream
         */
        list.stream().dropWhile(x -> x < 5).forEach(System.out::println);
    }
}
