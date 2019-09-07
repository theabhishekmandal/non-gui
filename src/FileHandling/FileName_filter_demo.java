package FileHandling;
/**
 * This program is an example of the FilenameFilter interface
 * Notice that we have not written implements FileNameFilter Interface
 * because we have used anonymous class to create the object of the interface
 * and then we have implemented the only method which is accept() method
 * which returns true if the given String ends with a given string or not.
 *
 * after creating the object of the FilenameFilter interface, then we
 * have passed this object in the list method which will provide those files
 * which match the given pattern.
 */

import java.io.*;
public class FileName_filter_demo {
    public static void main(String args[]) throws Exception {
        String s="C:\\Users\\Administrator\\Desktop\\python";
        File ob=new File(s);
        if(ob.isDirectory()) {
            FilenameFilter ob1= (dir, hello) -> hello.endsWith(".py");
            String[] arr=ob.list(ob1);
            for(String i:arr) {
                System.out.println(i);
            }
        }
        else
            System.out.print("working on directories only and nothing else");
    }
}