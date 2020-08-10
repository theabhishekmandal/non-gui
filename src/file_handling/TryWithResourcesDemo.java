package file_handling;

/**
 * This program is just a demo on how to use the try with resources statement to auto close  the file
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class
TryWithResourcesDemo {
    public static void main(String args[]) throws IOException {
        String currentDir = System.getProperty("user.dir");
        try (FileOutputStream fout = new FileOutputStream(currentDir + File.separator + "hello1.txt");
             FileInputStream fin = new FileInputStream(currentDir + File.separator + "hello.txt")) {
            int k;
            while((k = fin.read()) != -1) {
                System.out.print((char)k);
                fout.write(k);
            }
        }
        catch(IOException e) {
            System.out.println(e);
        }
    }
}
