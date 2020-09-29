package data_structures.priority_queues_and_heaps.binary_heap.binary_heap_impl;

import java.util.Arrays;

/**
 * Binary Max Heap implementation.
 * Operations performed are
 *  -   findMaximum
 *  -   deleteMaximum
 *  -   insertion
 *
 *  findMaximum
 *      -   In Binary Max Heap the first element denotes the maximum element in heap
 *
 *  deleteMaximum
 *      -   As first element is the maximum element in the heap, to delete it first
 *          it is stored in a temporary variable
 *      -   Then the max element is replaced by the last element of the heap
 *      -   Then the count is decreased,
 *      -   But now, the heap does not follow the heap conditions i.e parent should
 *          be greater than the child, to avoid this we start doing heapify from the
 *          root i.e top to bottom heapify
 *
 *  insertion
 *      -   A new element is inserted at the end
 *      -   But this may result in unbalanced heap, so heapify is done from the current
 *          element to the root element i.e bottom to top heapify
 *      -   In this type of bottom to top heapify we only take the path from the current inserted
 *          node till the root node, because only this path is unbalanced rest of the nodes are already
 *          balanced
 *      -   this takes O(n log n) time, log n time for insertion and there are n keys
 *
 *
 *  creating heap from array
 *      -   If we have predefined set of keys for which a new heap is to be created then we
 *          can create the heap in O(n) time
 *      -   Inserting a single key at a time, takes O(log n) operations and inserting n keys
 *          takes O(n log n) time
 */

public class BinaryMaxHeap {
    private int[] array;
    private int count;
    private int capacity;

    public BinaryMaxHeap(int capacity) {
        this.capacity = capacity;
        this.count = 0;
        this.array = new int[this.capacity];
    }

    public BinaryMaxHeap() {
        this(10);
    }

    public void destroyHeap() {
        this.count = 0;
        this.array = null;
    }

    public int getParent(int i) {
        if(i <= 0 || i >= this.count) {
            return -1;
        }
        return (i - 1) / 2;
    }

    public int getLeftChild(int i) {
        int left = 2 * i + 1;
        if(left >= this.count) {
            return -1;
        }
        return left;
    }

    public int getRightChild(int i) {
        int right = 2 * i + 2;
        if(right >= this.count) {
            return -1;
        }
        return right;
    }

    // in max heap max element is at the root
    public int getMaximum() {
        if(this.count == 0) {
            return -1;
        }
        return this.array[0];
    }

    public int getCount() {
        return this.count;
    }

    private void heapify(int i) {
        int left = this.getLeftChild(i);
        int right = this.getRightChild(i);
        int max = i;
        if(left != -1 && this.array[left] > this.array[max]) {
            max = left;
        }
        if(right != -1 && this.array[right] > this.array[max]) {
            max = right;
        }
        if(max != i) {
            swap(i, max);
            heapify(max);
        }
    }

    /*
        When doing deletion, the root element is deleted and replaced with last
        element of the heap, now the heap would be unbalanced, so from top
        we start doing heapify
     */
    public int deleteMax() {
        if(this.count == 0) {
            return -1;
        }
        int max = this.array[0];
        this.array[0] = this.array[this.count  - 1];
        this.count--;
        heapify(0);
        return max;
    }

    private void swap(int i, int j) {
        int temp = this.array[i];
        this.array[i] = this.array[j];
        this.array[j] = temp;
    }

    /*
        When doing insert the new element is added at the last,
        now the heap would be unbalanced, so from bottom we start
        doing heapify
     */
    public void insert(int data) {
        int i;
        if(this.count == this.capacity) {
            resizeHeap();
        }
        this.count++;
        i = this.count - 1;
        this.array[i] = data; // first add the element and then compare

        int parent = getParent(i);
        // using i != 0, don't use i >= 0 because if i = 0 then parent is -1, will give exception
        while(i != 0 && this.array[i] >= this.array[parent]) {
            swap(i, parent);
            i = parent;
            parent = getParent(i);
        }
    }

    public void resizeHeap() {
        int[] oldArray = this.array;
        this.capacity *= 2;
        this.array = new int[this.capacity];
        System.arraycopy(oldArray, 0, this.array, 0, this.count);
    }

    public void buildHeap(int[] arr) {
        if(arr == null || arr.length == 0) {
            throw new NullPointerException("input array is empty");
        }
        if(this.count != 0) {
            throw new ArrayIndexOutOfBoundsException("heap is already built cannot be built with existing array");
        }
        if(arr.length > this.capacity) {
            this.resizeHeap();
        }
        System.arraycopy(arr, 0, this.array, 0, arr.length);
        this.count += arr.length;
        for(int i = (this.count - 1) / 2; i >= 0; i--) {
            heapify(i);
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(this.array);
    }
}
