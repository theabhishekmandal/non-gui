package strings_implementation;

import java.util.Arrays;

/**
 * compareTo() method on Strings help us to determine which strings comes in increasing order.
 * This compareTo() helps in sorting of the Strings.
 *
 *  str.compareTo(str1)
 * less than zero       :   str is smaller than str1
 * greater than zero    :   str is grater than str1
 * zero                 :   both are equal
 */
public class CompareToDemo {
    public static void main(String[] args) {
    String[] hel = {"asfldkjdslk", "aalxdvlkljsdf"};
    if(hel[0].compareTo(hel[1]) > 0){
        String temp = hel[1];
        hel[1] = hel[0];
        hel[0] = temp;
    }

    System.out.println(Arrays.toString(hel));

    String one = "ABHISHEK";
    String two = "abhishek";
    System.out.println(one.compareToIgnoreCase(two));
    }
}
