package CollectionImplementation.Streams;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamFromFiles {
    public static void main(String[] args) throws IOException {

        String filePath = System.getProperty("user.dir")
                + File.separator
                + "src"
                + File.separator
                + "CollectionImplementation"
                + File.separator
                + "Streams"
                + File.separator
                + "Names.txt";

        // Stream rows from text file, sort, filter and print
        Stream<String> names = Files.lines(Paths.get(filePath));

        names.sorted()
                .filter(x -> x.length() > 13)
                .forEach(System.out::println);
        names.close();

        System.out.println("\nnames2 list");
        // Stream rows from text file and save to List
        List<String> names2 = Files.lines(Paths.get(filePath))
                .filter(x -> x.endsWith("Uchhiha"))
                .collect(Collectors.toList());

        names2.forEach(System.out::println);
    }
}
