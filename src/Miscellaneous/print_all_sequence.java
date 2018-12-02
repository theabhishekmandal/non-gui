package Miscellaneous;
import java.util.*;
public class print_all_sequence {
    public static void main(String args[])
    {
        Scanner s  = new Scanner(System.in);
        String input = s.next();
        printAll(input , "");
    }
    private static void printAll(String input , String output)
    {
        if(input.length() == 0)
        {
            System.out.println(output);
            return;
        }
        printAll(input.substring(1),output);
        printAll(input.substring(1),output + input.charAt(0));
    }
}
