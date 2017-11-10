package Filehandling.FileOutputStreamDemo;
/**
 * here we create three files file1.txt file2.txt and file3.txt
 *
 * file1 will contain every other alphabet of the string
 * file2 will contain all the alphabets of the string
 * file3 will contain only the last quarter
 *
 */

import java.io.*;
public class demo6 {
    public static void main(String args[]) throws IOException
    {
        String source="Now is the time for all good men\n to come to the aid of their country\n and pay their due taxes";
        byte[] buf=source.getBytes();
        String path1="F:\\non-gui\\src\\Filehandling\\FileOutputStreamDemo\\file1.txt";
        String path2="F:\\non-gui\\src\\Filehandling\\FileOutputStreamDemo\\file2.txt";
        String path3="F:\\non-gui\\src\\Filehandling\\FileOutputStreamDemo\\file3.txt";
        try(FileOutputStream f1=new FileOutputStream(path1);FileOutputStream f2=new FileOutputStream(path2);FileOutputStream f3=new FileOutputStream(path3))
        {
            //writing to the first file every other alphabet of the string
            for(int i=0;i<buf.length;i+=2)
                f1.write(buf[i]);

            //writing to the second file all the alphabets of the file
            f2.write(buf);

            //writing to third file only the last quarter
            f3.write(buf,buf.length-buf.length/4,buf.length/4);
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
}
