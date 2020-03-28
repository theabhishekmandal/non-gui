package Miscellaneous;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class FindingZerosAndOnes {
    public static void main(String args[]){
        Random random = new Random();
        int arr[] = new int[10];
        for(int i = 0; i < arr.length; i++) arr[i] = random.nextInt(2);
        System.out.println(Arrays.toString(arr));
        for(int one = 0, zero = arr.length - 1; one < zero;){
            if(arr[one] == 0 && arr[zero] == 1){
                arr[one++] = 1;
                arr[zero--] = 0;
            }
            else if(arr[one] == 1) one++;
            else if (arr[zero] == 0) zero--;
        }
        System.out.println(Arrays.toString(arr));
    }
}
