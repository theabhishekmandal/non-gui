package data_structures.strings;

import java.util.Arrays;

/*
This is leetcode question.
https://leetcode.com/problems/string-compression/description/?envType=study-plan-v2&envId=leetcode-75
Given an array of characters chars, compress it using the following algorithm:

Begin with an empty string s. For each group of consecutive repeating characters in chars:

If the group's length is 1, append the character to s.
Otherwise, append the character followed by the group's length.
The compressed string s should not be returned separately, but instead, be stored in the input character array chars.
Note that group lengths that are 10 or longer will be split into multiple characters in chars.

After you are done modifying the input array, return the new length of the array.

You must write an algorithm that uses only constant extra space.
 */

public class _1MediumStringCompression {
    public static void main(String[] args) {
        char[] arr = "aaaaabbb".toCharArray();
        int count = compress1(arr);
        System.out.println(count + " " + Arrays.toString(arr));
    }

    // use this
    /*
    Things learned from here
        -   for any type of comparison, don't use two different pointers pointing to different index like i and j
            instead use length i.e i and i + length to compare chars at two indexes.
     */
    public static int compress1(char[] chars) {
        int index = 0;
        int n = chars.length;
        for (int i = 0; i < n;) {
            int temp = 1;
            char ch = chars[i];
            while (i + temp < n && ch == chars[i + temp]) {
                temp++;
            }
            chars[index++] = ch;
            if (temp > 1) {
                String count = temp + "";
                for (char c : count.toCharArray()) {
                    chars[index++] = c;
                }
            }
            i += temp;
        }
        return index;
    }
    public static int compress(char[] chars) {
        int counterIndex = -1;
        int count = 0;
        int totalCount = 0;
        int size = chars.length;

        for (int startChar = 0; startChar < size;) {
            int j = startChar + 1;
            while (j < size && chars[j] == chars[startChar]) {
                j++;
            }

            // count counterIndex will be just next to character
            counterIndex = startChar + 1;

            // storing the count of the similar chars
            count = j - startChar;

            // 1 length of the actual char
            totalCount++;

            // converting and storing each digit of count in array
            String countString = "" + count;
            int countStringIndex = 0;
            // storing the count
            if (count > 1) {
                while (counterIndex  < size && countStringIndex < countString.length()) {
                    chars[counterIndex++] = countString.charAt(countStringIndex++);

                    // count of each digit, since each digit is stored as char
                    totalCount++;
                }
            }


            // next char will be here from where we will start compression
            startChar = counterIndex;

            if (count > 1) {
                while (counterIndex < j && j < size) {
                    chars[counterIndex++] = chars[j++];
                }
            }
            size = (size - (j - counterIndex));
        }

        return totalCount;
    }
}
