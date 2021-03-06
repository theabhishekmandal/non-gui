package file_handling;

import java.io.File;

/**
 * IN this program we are creating File object and checking it's various methods
 * for handling file objects.
 */
public class FileObjectDemo {
    public static void main(String[] args) {
        String directory = System.getProperty("user.dir");
        String directoryPath = String.join(File.separator, directory, "src", "file_handling");
        File ob1 = new File(directoryPath);
        File ob2 = new File(directory, "hello.txt");
        File ob3 = new File(ob1, "hello.txt");
        System.out.println("Name of the file " + ob3.getName());
        System.out.println("Name of the parent " + ob3.getParent());
        System.out.println("Does the file exits? " + ob3.exists());
        System.out.println("Get the path of the file " + ob3.getPath());
        System.out.println("Get the absolute path of the file " + ob3.getAbsolutePath());
        System.out.println("is the file writable " + ob3.canWrite());
        System.out.println("is the file readable " + ob3.canRead());
        System.out.println("is the file executable " + ob3.canExecute());
        System.out.println("Length of the file " + ob3.length() + " bytes");
        System.out.println("When was the file last modified " + ob3.lastModified());
        System.out.println(ob3.delete());
    }
}
