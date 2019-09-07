package FileHandling;

/**
 * This program is just a demo on how we can use the method read(byte[] arr,int offset,int numbytes)
 *
 * in this the read method read upto numbytes bytes and put it to the byte array starting from the offset index
 */

import java.io.*;
public class ReadingFileAsBytesDemo1 {
    public static void main(String args[]) throws IOException {

         FileInputStream fin = new FileInputStream("F:\\non-gui\\src\\FileHandling\\hello1.txt")  ;
         FileOutputStream fout = new FileOutputStream("F:\\non-gui\\src\\FileHandling\\hello.txt");
         int k = -1;
         //using the method read(byte[] arr)

         byte[] arr = new byte[1000];

        fin.read(arr,0,fin.available());
        for(byte i : arr)
            System.out.print((char)i);
    }
}
