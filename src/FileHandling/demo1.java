package FileHandling;
/**
 * This program implements on reading a String and a character using a BufferedReader object
 * BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
 *
 * we can read a string from the console using readLine() method of BufferedReader
 * whose return type is a string
 *
 * we can read a character from the console using the read() method whose return type is int
 * and so we type cast it to char
 *
 * we can read an integer using read() method .But, here is the catch that if you enter a number such as
 * 12 then it will read the first byte 1 and return it's ascii value
 *
 * we read an integer using readLine() method and then we parse it to int
 *
 * Also remember that while reading input read() or readLine() when we press enter then the "\n" ,will become input
 * for the next variable .So to avoid this we escape it using the  readLine() method. See line number 39
 *
 */
import java.io.IOException;
import java.io.*;
public class demo1 {
    public static void main(String args[])throws IOException
    {
       BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

       //using PrintWriter Stream object to display the output instead of using System.out
       //here auto flush the contents of the out object is true
        PrintWriter out=new PrintWriter(System.out,true);

       out.println("Reading a String from the console ");
       String str=br.readLine();

       out.println("Reading a character from a console");
       char c=(char)br.read();

       br.readLine();

       out.println("Reading a integer from the string");
       int num=Integer.parseInt(br.readLine());

       out.println(str);
       out.println(c);
       out.println(num);


    }
}
