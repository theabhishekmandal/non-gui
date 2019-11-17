package Miscellaneous;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *  This is not a dynamic programming based question.
 */

public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String args[]){
        Scanner s = new Scanner(System.in);
        String hel = s.next();
        Map<Character, Integer> map = new HashMap<>();
        int len = 0;
        int startIndex = 0;
        for(int i = 0; i < hel.length(); i++){
            char c = hel.charAt(i);

            // if the char is present it means it is already visited
            // and max is taken because
            // if there is a character that can be escaped then escape it
            /*
             eg: if string is s = "abba"

             now escape the chars when a found repititive character index is greater than
             startIndex, other wise don't escape

             eg: case 1 :when startIndex is at zero and i is at second b, so escape the first
             b character's position + 1,

             case 2: when i is at second a and startIndex is at second b, then
             the character position that we need to jump will be 'a', but, a's
             position is smaller than startIndex so we won't do anything


             */
            if(map.containsKey(c)){
                startIndex = Math.max(startIndex, map.get(c) + 1);
            }

            // putting the index of the current character
            map.put(c, i);

            // finding the max length because
            /*
            if mulitple substring exists then find that one which has the max length
            if string is abcbb
            then when startIndex is at second b then total length will be less
             */
            len = Math.max(len, i - startIndex + 1);
        }
        System.out.println(len);


        int[] arr = new int[26];
        startIndex = 0; len = 0;
        for(int i = 0; i < hel.length(); i++){
            char c = hel.charAt(i);
            if(arr[c - 'a'] > 0)
                startIndex = Math.max(startIndex,arr[c - 'a'] + 1);
            arr[c - 'a'] = i + 1;
            len = Math.max(len, i - startIndex + 1);
        }
        System.out.println(len);
    }
}
