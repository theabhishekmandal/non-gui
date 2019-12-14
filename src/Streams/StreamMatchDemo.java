package Streams;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamMatchDemo {
    public static void main(String[] args) {
        IntStream number = IntStream.range(0, 20).filter(x -> x % 2 == 0);
        IntStream number2 = IntStream.range(0, 20).filter(x -> x % 2 == 0);
        IntStream number3 = IntStream.range(0, 20).filter(x -> x % 2 == 0);
        boolean allEven = number.boxed().allMatch(x -> x % 2 == 0);
        boolean someEven = number2.anyMatch(x -> x % 2 == 0);
        boolean noneMultipleOfThree = number3.noneMatch(x -> x % 3 == 0);
        System.out.println(allEven + "\n" + someEven + "\n" + noneMultipleOfThree);
    }
}
