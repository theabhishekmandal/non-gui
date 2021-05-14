package file_handling;
/**
 * This program is an example of the FilenameFilter interface
 * Notice that we have not written implements FileNameFilter Interface
 * because we have used anonymous class to create the object of the interface
 * and then we have implemented the only method which is accept() method
 * which returns true if the given String ends with a given string or not.
 *
 * after creating the object of the FilenameFilter interface, then we
 * have passed this object in the list method which will provide those files
 * which match the given pattern.
 */

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Objects;

public class FileNameFilterDemo {
    public static void main(String[] args) {
        String directoryPath = String.join(File.separator, System.getProperty("user.dir"), "src", "file_handling");
        File directoryFileObject = new File(directoryPath);
        try {
            if(directoryFileObject.isDirectory()) {
                FilenameFilter fileNameFilter = (dir, hello) -> hello.endsWith(".java");
                Arrays.stream(Objects.requireNonNull(directoryFileObject.list(fileNameFilter))).forEach(System.out::println);
            }
            else{
                System.out.print("working on directories only and nothing else");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}