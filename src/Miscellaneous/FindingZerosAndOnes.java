package Miscellaneous;

import java.util.Arrays;
import java.util.Scanner;

public class FindingZerosAndOnes {
    public static void main(String args[]){
        Scanner s = new Scanner(System.in);
        int[] arr = {1, 0, 0 , 1, 1, 0, 1};
        int zero = 0, one = 0;
        for(int i = 0, j = arr.length - 1; j > i; j--, i++){
            if(arr[i] == 1 && arr[j] == 0) {
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
