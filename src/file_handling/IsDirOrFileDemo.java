package file_handling;
/**
 * In this program we are creating a File object and checking if it is file or a directory
 * if it is a directory then we store the directory items in a String array objects
 * then we separately check if whether the given  item is file or a directory
 */
import java.io.*;
import java.util.Arrays;
import java.util.Objects;

public class IsDirOrFileDemo {
    public static void main(String[] args) {

        String directoryPath = String.join(File.separator, System.getProperty("user.dir"), "src", "file_handling");
        File directoryFileObject = new File(directoryPath);

        if(directoryFileObject.isDirectory()) {
            Arrays.stream(Objects.requireNonNull(directoryFileObject.list())).map(x -> new File(directoryPath + File.separator + x))
                    .forEach(x -> {
                        if(x.isDirectory()) {
                            System.out.println("Yes " + x.getName() + " is a directory");
                        }
                        else {
                            System.out.println("Yes " + x.getName() + " is a File");
                        }
                    });
        }
        else {
            if(directoryFileObject.isFile()) {
                System.out.println("hey " + directoryFileObject.getName() + " is a file");
            }
            else{
                System.out.println("This is something else");
            }
        }
    }
}
