package miscellaneous;
import java.util.*;

/**
 * Below is the implementation of finding the nth largest variable in a single iteration,
 * To find the nth variable we have to use n variables.
 */
public class FindingNthHighestNumber {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < arr.length; i++){
            arr[i] = s.nextInt();
        }
        int a, b, c;
        a = b = c = Integer.MIN_VALUE;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] > c){
                a = b;
                b = c;
                c = arr[i];
            }
            else if(arr[i] > b){
                a = b;
                b = arr[i];
            }
            else if(arr[i] > a){
                a = arr[i];
            }
        }
        System.out.println(a+ " " + b + " " + c);
    }
}