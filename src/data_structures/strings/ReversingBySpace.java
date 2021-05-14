package data_structures.strings;

import java.util.Scanner;
/**
 This program shows how to reverse the contents of the string such as
 i am happy
 happy am i

 for this we first reverse the list
 yppah ma i
 then we reverse the words.


 */

class ReversingBySpace {
    public static void reverse(char[] arr, int start, int end) {
        while(start <= end) {
            char temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        char[] arr = s.nextLine().toCharArray();
        reverse(arr,0,arr.length - 1);
        int j = 0;
        int i = 0;
        for( i = 0; i < arr.length; i++) {
            if(arr[i] == ' ') {

                //to check if there is a string to be reversed
                if(i - j > 0) {

                    // till i - 1 we want to reverse because at i space is encountered
                    reverse(arr,j,i - 1);
                    j = i;

                }

                // if encounter space then escape it
                j++;
            }
        }
        reverse(arr, j, i - 1);
        System.out.println(new String(arr));
    }
}