package file_handling;

/**
 * DEMONSTRATING FILEINPUTSTREAM
 *
 *  This program shows how to read a single byte, an array of bytes and a subrange of an array of bytes from a given file.
 *
 *  Also we can use the skip() method to escape n number of bytes and use the available() method to know how much bytes are
 *  remaining to be read
 *
 *  also we can see in the read(byte[] arr,int offset,int numbytes) .The return type of this method tells how many bytes
 *  have been successfully read .Look line number 39
 */

import java.io.*;
public class ReadingFileAsBytesDemo2 {
    public static void main(String[] args) throws IOException {

        String filePath = String.join(File.separator, System.getProperty("user.dir"), "src", "file_handling",
                "ReadingFileAsBytesDemo2.java");
        try(FileInputStream fin = new FileInputStream(filePath)) {

            System.out.println("Total available bytes are" + fin.available());
            int size = fin.available() / 40;
            System.out.println("we are reading first " +  size  + " bytes of the input\n");

            for(int i = 0; i < size; i++) {
                System.out.print((char)fin.read());
            }

            System.out.println();

            byte[] arr = new byte[size];
            while(fin.read(arr) != size) {
                System.err.println("not able to read remaining bytes");
            }

            System.out.print(new String(arr,0,size) + "\n\n");

            System.out.println("remaining bytes are " + fin.available());
            System.out.println("skipping the half of the bytes " + fin.skip(size/2));
            System.out.println("Still available bytes are" + fin.available());

            System.out.println("Reading " + size / 2 + " into the end of array");
            if (fin.read(arr, size / 2, size / 2) != size / 2) {
                System.err.println("could not read " + size / 2 + " bytes.");

            }
            for(byte i : arr) {
                System.out.print((char)i);
            }
        }
        catch(IOException e) {
            System.out.println(e);
        }
    }

}
