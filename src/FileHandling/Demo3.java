package FileHandling;

/**
 * This program is just a demo on how to use the try with resources statement to auto close  the file
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Demo3 {
    public static void main(String args[]) throws IOException
    {
        try(FileInputStream fin=new FileInputStream("F:\\non-gui\\src\\FileHandling\\hello1.txt");
        FileOutputStream fout=new FileOutputStream("F:\\non-gui\\src\\FileHandling\\hello.txt"))
        {
            int k=0;
            while((k=fin.read())!=-1)
            {
                System.out.print((char)k);
                fout.write(k);
            }

        }
        catch(IOException e)
        {
            System.out.println(e);
        }

    }
}
