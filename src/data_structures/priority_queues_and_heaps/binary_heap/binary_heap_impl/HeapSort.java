package data_structures.priority_queues_and_heaps.binary_heap.binary_heap_impl;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Heap Sort
 * Approach
 *  -   To sort the elements in sorted array
 *
 *  -   First initially create max Heap using buildMaxHeap method.
 *      -   To create max Heap, first start with the last node which has the leaf node
 *      -   To get the last non-leaf node use -> (length - 1) / 2
 *      -   from last non-leaf node to start of the node start doing heapifying
 *
 *  -   After building max heap, we want to swap the max value i.e 0th index value with last value.
 *      The purpose of this is, we are collecting the largest values and placing them to the end of
 *      the array, Also we will reduce the heap size after every insertion, so that we don't touch
 *      the larger elements that were placed before.
 */
public class HeapSort {
    private int[] array;
    private int size;
    private static final String ERR_MESSAGE = "input array cannot be null";

    public void initialise(int[] arr) {
        Objects.requireNonNull(arr, ERR_MESSAGE);
        this.array = arr.clone();
        this.size = this.array.length;
    }

    public void initialise(Integer[] arr) {
        Objects.requireNonNull(arr, ERR_MESSAGE);
        array = new int[arr.length];
        System.arraycopy(arr, 0, array, 0, arr.length);
        this.size = this.array.length;
    }

    public void initialise(List<Integer> arr) {
        Objects.requireNonNull(arr, ERR_MESSAGE);
        array = arr.stream().mapToInt(x -> x).toArray();
        this.size = this.array.length;
    }

    public void destroy() {
        this.array = null;
        this.size = 0;
    }

    public int[] getArray() {
        return this.array;
    }

    public void sort() {
        buildMaxHeap();
        for(int i = array.length - 1; i >= 0; i--) {
            // now replace last element with first element and reduce the heap size
            // purpose of reducing size denotes, we are removing the last element from heap after swap
            this.size--;
            swap(0, i);
            heapify(0);
        }
    }

    private void buildMaxHeap() {
        for(int i = (this.size - 1) / 2; i >= 0; i--) {
            heapify(i);
        }
    }

    private void heapify(int i) {
        int left = getLeft(i);
        int right =  getRight(i);
        int largest = i;
        if(left != -1 && array[left] > array[largest]) {
            largest = left;
        }
        if(right != -1 && array[right] > array[largest]) {
            largest = right;
        }
        if(i != largest) {
            swap(i, largest);
            heapify(largest);
        }
    }

    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private int getLeft(int i) {
        int left = 2 * i + 1;
        if(left >= this.size) return -1;
        return left;
    }

    private int getRight(int i) {
        int right = 2 * i + 2;
        if(right >= this.size) return -1;
        return right;
    }

    @Override
    public String toString() {
        if(array == null) return "[]";
        return Arrays.toString(array);
    }
}
