package data_structures.strings;

import java.util.Random;

/**
 * Given an input string print all the duplicates
 */
public class PrintAllDuplicates {
    private static final Random random = new Random();
    public static void main(String[] args) {
        String string = getInputString();
        System.out.println("Input string is = " + string);
        System.out.println(printDuplicates(string));
    }

    private static String printDuplicates(String string) {
        StringBuilder br = new StringBuilder();
        if(string == null || string.trim().length() == 0) {
            return br.toString();
        }
        int[] arr = new int[256];
        for(char c : string.toCharArray()) {
            arr[c]++;
        }
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] > 1) {
                br.append((char)i)
                        .append(" count = ")
                        .append(arr[i]).append("\n");
            }
        }
        return br.toString();
    }

    private static String getInputString() {
        byte[] arr = new byte[20];
        for(int i = 0; i < arr.length; i++) {
            int k = 97 + random.nextInt(26);
            arr[i] = (byte) k;
        }
        return new String(arr);
    }
}
