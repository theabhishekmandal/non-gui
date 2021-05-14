package streams;

import java.util.stream.IntStream;

public class IterateDemo {
    public static void main(String[] args) {
        /*
            this iterate is similar to for (int i = 0; i < 10; i +=2)
         */
        IntStream.iterate(0, i -> i < 10, i -> i + 2)
                .forEach(System.out::println);

        /*
           this iterate is similar to
           for (int i = 0;; i++) {
                if (i < 10 {
                    print
                }
           }
         */
        IntStream.iterate(0, i -> i + 1)
                .filter(i -> i % 2 == 0)
                .takeWhile(i -> i < 10)
                .forEach(System.out::println);
    }
}
