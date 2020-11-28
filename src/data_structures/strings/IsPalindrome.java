package data_structures.strings;

import java.util.Random;

/**
 * Given a string, check whether it is palindrome or not
 */
public class IsPalindrome {
    private static final Random random = new Random();
    public static void main(String[] args) {
        int k = 1 + random.nextInt(5);
        while(k-- > 0) {
            String s = getRandomString();
            System.out.println("given string = " + s + " is " + ((isPalindrome(s)) ? "palindrome" : "not palindrome"));
        }
    }

    private static boolean isPalindrome(String string) {
       if(string == null || string.length() == 0) return false;
       for(int i = 0, j = string.length() - 1; i < j; i++, j--) {
           if(string.charAt(i) != string.charAt(j)) {
               return false;
           }
       }
       return true;
    }

    private static String getRandomString() {
        int length = 1 + random.nextInt(5);
        if(random.nextBoolean()) {
            if(length % 2 == 0) {
               length++;
            }
        }
        else {
            if(length % 2 != 0) {
                length++;
            }
        }
        char[] arr = new char[length];
        if(random.nextBoolean()) {
            int midlength = length >> 1;
            for(int i = 0; i < midlength; i++) {
                arr[i] = (char)(97 + random.nextInt(26));
                arr[arr.length - i - 1] = arr[i];
            }
            if(length % 2 == 1) arr[midlength] = (char)(97 + random.nextInt(26));
        }
        else {
            for(int i = 0; i < length; i++) {
                arr[i] = (char)(97 + random.nextInt(26));
            }
        }
        return new String(arr);
    }
}
