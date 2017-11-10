package data_structure.Searching;
/**
 this program is an implementation of binary search
 For this we require a sorted array
 we have variables low,high and mid
 low and high shows the range in which we have to conduct our search
 mid is the mean of high and low

 we compare the element which we want to find with the middle value of the sorted array

 if the middle value is smaller than the value we have to find, then we ignore the array elements which
 are smaller than a[mid] including a[mid]

 or else if the middle value is greater than the value we have to find then we ignore the right portion
 including a[mid]

 or else the middle value is the value that we have to find is returned

 or else the value that you want to find is not present in the array
 in that case we return "Nothing Found"

 */
import java.util.*;
public class Binary_Search {
    public static void main(String args[])
    {
        Scanner s=new Scanner(System.in);
        System.out.println("enter the size of the array");
        int t=s.nextInt();
        int arr[]=new int[t];
        System.out.println("enter the elments of the arrays");
        for(int i=0;i<arr.length;i++)
            arr[i]=s.nextInt();
        System.out.println("enter value that you want to find");
        int find=s.nextInt();
        Binary_Search ob=new Binary_Search();
        System.out.println(ob.binarySearch(arr,t));
    }

    private String binarySearch(int[] arr, int find) {
        int arr2[]=Arrays.copyOf(arr,arr.length);
        Arrays.sort(arr2);
        int low=0;
        int high=arr2.length-1;
        /*
        This is done in this way so as to avoid overflow errors
        and not like this mid=(high+low)/2
         */
        int mid=((high-low)/2+low);
        while(low<=high)
        {
            if(arr2[mid]==find)
                return Integer.toString(find);
            if(arr2[mid]>=find)
                high=mid-1;
            else
                low=mid+1;
            mid=((high-low)/2+low);

        }
        return "Nothing found";
    }
}
