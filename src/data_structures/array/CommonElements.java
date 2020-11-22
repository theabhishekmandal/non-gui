package data_structures.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Given three arrays in sorted order, find the common elements in them, also don't count the duplicates if present.
 *
 * Approach:
 *  -   As all the arrays are in sorted order, just take three pointers and check whether they have same element or not
 */

public class CommonElements {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 5, 10, 20, 40, 80};
        int[] brr = new int[]{6, 7, 20, 80, 100};
        int[] crr = new int[]{3, 4, 15, 20, 30, 70, 80, 120};
        System.out.println(commonElements(arr, brr, crr));
    }

    private static List<Integer> commonElements(int[] arr, int[] brr, int[] crr) {
        int i = 0;
        int j = 0;
        int k = 0;
        List<Integer> countList = new ArrayList<>();
        while(i < arr.length && j < brr.length && k < crr.length) {
            int one = arr[i];
            int two = brr[j];
            int three = crr[k];
            if(one == two && two == three) {
                if(countList.isEmpty() || one != countList.get(countList.size() - 1)) {
                    countList.add(one);
                }
                i++;
                j++;
                k++;
            }
            else if(one <= two && one <= three) {
                i++;
            }
            else if(two <= one && two <= three) {
                j++;
            }
            else {
               k++;
            }
        }
        return countList;
    }
}
