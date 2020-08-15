package streams;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamFromCsvFile {
    public static void main(String[] args) throws  Exception{
        String csvFilePath = System.getProperty("user.dir")
                + File.separator
                + "src"
                + File.separator
                + "collection_implementation"
                + File.separator
                + "streams"
                + File.separator
                + "data.txt";

        first(csvFilePath);
        second(csvFilePath);
        third(csvFilePath);
    }

    private static void third(String csvFilePath) throws IOException{
        System.out.println("In third");
        // Stream rows from CSV file, store fields in HashMap
        Stream<String> rows3 = Files.lines(Paths.get(csvFilePath));

        Map<String, Integer> map;
        map = rows3.map(x -> x.split(","))
                .filter(x -> x.length == 3)
                .filter(x -> Integer.parseInt(x[1]) > 15)
                .collect(Collectors.toMap(
                        x -> x[0],
                        x -> Integer.parseInt(x[1])
                ));
        rows3.close();

        map.forEach((x, y) -> System.out.println(x + " " + y));
    }

    private static void second(String csvFilePath) throws IOException{
        System.out.println("In second");
        // Stream rows from CSV file, parse data from rows
        Stream<String> rows2 = Files.lines(Paths.get(csvFilePath));

        rows2.map(x -> x.split(","))
                .filter(x -> x.length == 3)
                .filter(x -> Integer.parseInt(x[1]) > 15)
                .forEach(x -> System.out.println(x[0] + " " + x[1] + " " + x[2]));
        rows2.close();

    }

    private static void first(String csvFilePath) throws IOException {
        System.out.println("In first");
        // Stream rows from CSV file and count
        Stream<String> rows1 = Files.lines(Paths.get(csvFilePath));
        int rowCount = (int)rows1
                .map(x -> x.split(","))
                .filter(x -> x.length == 3)
                .count();

        System.out.println(rowCount + " rows.");
        rows1.close();
    }

}
