package data_structures.priority_queues_and_heaps.binary_heap.binary_heap_impl;

import java.util.Arrays;

/**
 * 📦 Binary Max Heap implementation using array.
 *
 * A Binary Max Heap is a complete binary tree where each node is greater than or equal to its children.
 * This implementation supports the following operations:
 *
 * 🔹 Operations:
 *  - getMaximum()      : Returns the maximum element (at root) → O(1)
 *  - deleteMax()       : Removes and returns the max element → O(log n)
 *  - insert(int data)  : Inserts a new element while maintaining max heap property → O(log n)
 *  - buildHeap(int[])  : Builds heap from existing array using bottom-up heapify → O(n)
 *  - destroyHeap()     : Clears the heap → O(1)
 *
 * 🔁 Heapify Notes:
 *  - Top-down heapify (used in deleteMax and buildHeap) ensures heap property from root → O(log n)
 *  - Bottom-up heapify (used in insert) restores heap from leaf to root → O(log n)
 *
 * 📈 Time Complexity Summary:
 * ┌────────────────────────────┬────────────────┐
 * │ Operation                  │ Time Complexity│
 * ├────────────────────────────┼────────────────┤
 * │ getMaximum()               │ O(1)           │
 * │ deleteMax()                │ O(log n)       │
 * │ insert(int data)           │ O(log n)       │
 * │ buildHeap(int[] arr)       │ O(n)           │
 * │ insert n elements (1-by-1) │ O(n log n)     │
 * └────────────────────────────┴────────────────┘
 *
 * 🔸 Note:
 * - Use buildHeap() when inserting a predefined array → O(n)
 * - For dynamic insertions, use insert() repeatedly → O(n log n) for n elements
 *
 * 🌳 Binary Heap is used in:
 *  - Priority Queues
 *  - Heap Sort
 *  - Graph Algorithms (e.g., Dijkstra, Prim)
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
        this.array[0] = this.array[this.count - 1];
        this.count--;
        // since only root is unbalanced we do heapify from root and not from last non-leaf node.
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
        // to get to the first non leaf node in reverse order use (length - 1) / 2
        for(int i = (this.count - 1) / 2; i >= 0; i--) {
            heapify(i);
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(this.array);
    }
}
