package Miscellaneous;
import java.util.*;
public class SumOfLargeNumbers {
    public static void main(String arg[]) {
        Scanner s=new Scanner(System.in);
        System.out.println("enter two numbers large or small");

        //inputting two large numbers and then reversing them coz we will start adding from the last
        //you can optimise the code by traversing it from the last or without using the reverse
        char a[] = new StringBuilder(s.next()).reverse().toString().toCharArray();
        char b[] = new StringBuilder(s.next()).reverse().toString().toCharArray();

        //calculating the max length
        int max = Math.max(a.length, b.length);


        //this if statements states that whatever the length of the number is
        //character array  'a' will always point to the smaller array
        if(a.length > b.length) {
            char c[] = a;
            a = b;
            b = c;
            c = null;
        }

        //initialising the carry
        int carry = 0;
        //starting from the 0 and going till the minimum length of the two arrays
        //calculating the sum and storing the result in the larger character array
        for(int i = 0; i < a.length; i++) {
            int sum = carry + a[i] - '0' + b[i] - '0';
            b[i] = (char)((sum % 10) + '0');
            carry = sum / 10;
        }

        //this loop is  for the larger array till now the smaller char array a has finished
        //now we will add the carry and forward it to the most significant bit
        for(int i = a.length; i < b.length; i++) {
            int sum = carry + b[i] - '0';
            b[i] = (char)((sum % 10) + '0');
            carry = sum / 10;
        }

        //if carrty is not zero then we would just display
        if(carry != 0)
            System.out.print(carry);
        for(int i = max - 1; i >= 0; i--) {
            System.out.print(b[i]);
        }
    }
}
