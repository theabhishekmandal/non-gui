package CollectionImplementation.Streams;

import java.util.Arrays;

public class ArrayStreamDemo {
    public static void main(String[] args) {
        String[] arr = {"hello", "world", "Abhishek", "Mandal"};
        Arrays.stream(arr)
                .filter(x -> x.contains("o"))
                .filter(x -> x.contains("h"))
                .forEach(System.out::println);
    }
}
