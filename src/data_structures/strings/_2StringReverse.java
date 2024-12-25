package data_structures.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringJoiner;

public class _2StringReverse {
    public static void main(String[] args) {
//        System.out.println(reverseWords(" the  sky is blue  "));
        System.out.println(reverseWords2(" the  sky is blue  "));
    }

    public static String reverseWords2(String s) {
        var builder = new StringBuilder();
        // find number of words
        //  startIndex and end Index

        int wordLength = 0;
        int wordStart = -1;
        var list = new ArrayList<String>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != ' ') {
                // set the start of word
                if (wordStart == -1) {
                    wordStart = i;
                }
                wordLength++;
            } else {
                if (wordStart != - 1) {
                    list.add(s.substring(wordStart, wordStart + wordLength));
                    wordStart = -1;
                    wordLength = 0;
                }
            }
        }
        if (wordStart != -1 && wordStart + wordLength == s.length()) {
            list.add(s.substring(wordStart, wordStart + wordLength));
        }
        StringJoiner stringJoiner = new StringJoiner(" ");
        for (int i = list.size() - 1;  i >= 0; i--) {
            stringJoiner.add(list.get(i));
        }
        return stringJoiner.toString();
    }

    public static String reverseWords(String s) {
        StringBuilder br = new StringBuilder();
        int startIndex = 0, endIndex = s.length() - 1;
        char[] arr = s.toCharArray();

        // stripe leading and trailing whitespaces
        while (startIndex < s.length() && s.charAt(startIndex) == ' ') {
            startIndex++;
        }

        while (endIndex > -1 && s.charAt(endIndex) == ' ') {
            endIndex--;
        }

        // check range of both startIndex and endIndex
        if (endIndex < startIndex) {
            return "";
        } else if (endIndex == startIndex) {
            return "" + s.charAt(endIndex);
        }

        // copy without trailing and leading whitespaces
        arr = Arrays.copyOfRange(arr, startIndex, endIndex + 1);

        //  reversing the array
        reverse(0, arr.length, arr);

        int temp;
        int len = arr.length;
        for (int i = 0; i < len;) {
            temp = 0;
            if (arr[i] == ' ') {
                i++;
            } else {
                while (i + temp < len && arr[i + temp] != ' ') {
                    temp++;
                }
                reverse(i, i + temp, arr);
                i += temp;
            }
        }

        return new String(arr);
    }

    private static int reverse(int startIndex, int endIndex, char[] arr) {
        int i = startIndex;
        int j = endIndex - 1;
        int mid = (i + endIndex) / 2;
        char c;
        while (i < mid) {
            if (arr[i] != arr[j]) {
                c = arr[i];
                arr[i] = arr[j];
                arr[j] = c;
            }
            i++;
            j--;
        }
        return endIndex;
    }
}
