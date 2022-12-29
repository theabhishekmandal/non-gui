package streams;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Given a lines of words separated by space. Each line consists of two words firstName and lastName.
 * We are sure that the order will be the same. Convert stream of each line consisting of two words to
 * stream of Person objects.
 *
 * Each Person object contains only two fields firstName and lastName of String type.
 *
 * Solution
 * By using traditional for loop using indexes we could solve this. But, below is the implementation
 * using Custom Spliterator and without using indexes. See tryAdvance method.
 */
public class CustomSpliteratorDemo {
    public static void main(String[] args) throws IOException {
        String namesFilePathString = System.getProperty("user.dir")
                + File.separator
                + "src"
                + File.separator
                + "streams"
                + File.separator
                + "Names.txt";

        Path namesFilePath = Paths.get(namesFilePathString);
        try (Stream<String> streamOfLines = Files.lines(namesFilePath)) {

            // Generate spliterator which will produces stream of words
            Spliterator<String> baseSpliterator = streamOfLines.flatMap(x -> Arrays.stream(x.split("\\s+"))).spliterator();

            // Generate a stream of person from a stream of words
            Spliterator<Person> personSpliterator = new PersonSpliterator(baseSpliterator);
            Stream<Person> stream = StreamSupport.stream(personSpliterator, false);
            stream.forEach(System.out::println);

        }
    }

    static class PersonSpliterator implements Spliterator<Person> {

        // A base spliterator should be present, through which we will retrieve objects
        private final Spliterator<String> baseSpliterator;


        // To convert String to Person object. All person instance variables should be present.
        private String firstName;
        private String lastName;

        PersonSpliterator(Spliterator<String> baseSpliterator) {
            this.baseSpliterator = baseSpliterator;
        }

        @Override
        public boolean tryAdvance(Consumer<? super Person> action) {
            /*
                For every tryAdvance, each word is assigned to either firstName or lastName instance variable.
                Since from original stream of words, we are sure that the first word will be the firstName
                and the second word will be the lastName.

                If both the tryAdvance operations are successful then we can successfully create a person object.
                Otherwise, we return false.

             */
            if (baseSpliterator.tryAdvance(word -> this.firstName = word) && baseSpliterator.tryAdvance(word -> this.lastName = word)) {
                action.accept(new Person(this.firstName, this.lastName));
                return true;
            }
            return false;
        }

        @Override
        public Spliterator<Person> trySplit() {
            // not implemented for parallelStream
            return null;
        }

        @Override
        public long estimateSize() {
            // Since every two words create one Person object so size becomes half of original stream
            return baseSpliterator.estimateSize() / 2;
        }

        @Override
        public int characteristics() {
            // return characteristics of the original stream.
            return baseSpliterator.characteristics();
        }
    }


    static class Person {
        private final String firstName;
        private final String lastName;

        public Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    '}';
        }
    }


}
