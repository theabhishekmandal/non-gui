package data_structures.strings;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WordWrap {
    public static void main(String[] args) {
        int[] arr = {3, 2, 2};
        int k = 4;
        System.out.println(getCharsPerLine2(arr, k));
    }

    private static String getCharsPerLine(int[] arr, int charsPerLine) {
        List<Integer> list = new ArrayList<>();
        int count = 0;
        int start = 1;
        int end = 1;
        for (int i = 0; i < arr.length; i++) {
            int totalChars = (i == arr.length - 1 ? 0 : 1) + arr[i];
            count += totalChars;
            if (count > charsPerLine) {
                count = totalChars;
                list.add(start);
                list.add(end);
                start = i + 1;
            }
            end = i + 1;
        }
        list.add(start);
        list.add(end);
        return list.stream().map(String::valueOf).collect(Collectors.joining(" "));
    }


    private static String getCharsPerLine2(int[] arr, int charsPerLine) {
        List<Integer> list = new ArrayList<>();
        int count = 0;
        int start = 1;
        int end = 1;
        for (int i = 0; i < arr.length - 1; i++) {
            int temp = arr[i] + 1 + arr[i + 1];
            if (count + temp <= charsPerLine) {
                count += temp;
            } else {
                list.add(start);
                list.add(i + 1);
            }
        }
        list.add(start);
        list.add(end);
        return list.stream().map(String::valueOf).collect(Collectors.joining(" "));
    }
}
