package data_structure.sorting;
import java.util.*;
import java.io.*;
public class HeapSort {
    public static void main(String args[])
    {
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        int arr[]= new int[t];
        for(int i = 0; i < arr.length ; i++)
            arr[i] = s.nextInt();
        heapsort(arr);
        System.out.println(Arrays.toString(arr));
    }
    private static void heapsort(int arr[])
    {
        buildmaxheap(arr, arr.length - 1);
        for(int i = arr.length - 1; i >= 1; i--)
        {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, 0 , i);     // the point from where you want to start making max heap and to the length.
        }
    }
    private static void buildmaxheap(int[] arr, int length)
    {
        for(int i = length / 2; i >= 0; i--)
            heapify(arr, i, length);
    }
    private static void heapify(int[] arr, int i, int length)
    {
        int l = 2 * i +1;
        int r = 2 * i + 2;
        int largest;
        if(l < length && arr[l] > arr[i])
            largest = l;
        else
            largest = i;
        if(r < length && arr[r] > arr[largest])
            largest = r;
        if(largest != i)
        {
            int temp = arr[largest];
            arr[largest] = arr[i];
            arr[i] = temp;
            heapify(arr, largest, length);
        }
    }
}
