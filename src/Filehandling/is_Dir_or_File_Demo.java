package Filehandling;
/**
 * In this program we are creating a File object and checking if it is file or a directory
 * if it is a directory then we store the directory items in a String array objects
 * then we separately check if whether the given  item is file or a directory
 */
import java.io.*;
public class is_Dir_or_File_Demo {
    public static void main(String args[]) throws IOException
    {
        String s="F:\\non-gui\\src\\data_structure";
        File ob=new File(s);
        if(ob.isDirectory())
        {
            String arr[]=ob.list();
            for(int i=0;i<arr.length;i++)
            {
                String check=s+"\\"+arr[i];
                File ob1=new File(check);
                if(ob1.isDirectory())
                    System.out.println("Yes "+ob1.getName()+" is a directory");
                else
                     if(ob1.isFile())
                         System.out.println("Yes "+ob1.getName()+" is a File");
            }


        }
        else
             if(ob.isFile())
                 System.out.println("hey "+ob.getName()+" is a file");
        else
            System.out.println("This is something else");

    }
}
