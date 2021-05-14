package file_handling;

/**
 * This program is just a demo on how we can use the method read(byte[] arr,int offset,int numbytes)
 *
 * in this the read method read upto numbytes bytes and put it to the byte array starting from the offset index
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
public class ReadingFileAsBytesDemo1 {
    public static void main(String[] args) throws IOException {

        String currentDir = System.getProperty("user.dir");
        try (FileInputStream fin = new FileInputStream(currentDir + File.separator + "hello.txt")) {
            //using the method read(byte[] arr)
            byte[] arr = new byte[1000];

            int bytesRead = fin.read(arr,0,fin.available());
            System.out.println("bytes read " + bytesRead);
            for(byte i : arr){
                System.out.print((char)i);
            }
        }
    }
}
