package Streams;

import java.util.stream.Stream;

public class StreamOfDemo {
    public static void main(String[] args) {
        Stream.of("abhishek", "mandal", "hello", "world")
                .sorted()                           //  sorted is present in Stream
                .findFirst()                        //  findFirst is present in Optional
                .ifPresent(System.out::println);    //  ifPresent is present in Optional
    }
}
