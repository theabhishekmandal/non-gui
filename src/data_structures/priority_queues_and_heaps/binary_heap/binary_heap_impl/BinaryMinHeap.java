package data_structures.priority_queues_and_heaps.binary_heap.binary_heap_impl;

import java.util.Arrays;

/**
 * 📦 Binary Min Heap implementation using an array.
 *
 * A Binary Min Heap is a complete binary tree where the value of each node is
 * less than or equal to the values of its children (min-heap property).
 *
 * 🔹 Supported Operations:
 * - getMinimum()      → Returns the smallest element (root) [O(1)]
 * - deleteMin()       → Removes the root and rebalances heap [O(log n)]
 * - insert(int data)  → Adds a new element and rebalances heap [O(log n)]
 * - buildHeap(int[])  → Builds a heap from a given array in bottom-up manner [O(n)]
 * - destroyHeap()     → Clears the heap memory [O(1)]
 *
 * 🔁 Heapify Types:
 * - Top-down heapify: Used in deleteMin() and buildHeap() → O(log n)
 * - Bottom-up heapify: Used in insert() to restore min-heap property → O(log n)
 *
 * ⚙️ Time Complexity Summary:
 * ┌────────────────────────────┬────────────────┐
 * │ Operation                  │ Time Complexity│
 * ├────────────────────────────┼────────────────┤
 * │ getMinimum()               │ O(1)           │
 * │ deleteMin()                │ O(log n)       │
 * │ insert(int data)           │ O(log n)       │
 * │ buildHeap(int[] arr)       │ O(n)           │
 * │ insert n elements (1-by-1) │ O(n log n)     │
 * └────────────────────────────┴────────────────┘
 *
 * 📌 Notes:
 * - Heaps are not suitable for arbitrary searches (O(n))
 * - Use for efficient access to min element in priority queues
 * - Array-based representation ensures good cache locality
 *
 * 📚 Applications:
 * - Priority Queues
 * - Dijkstra’s Algorithm
 * - Prim’s Minimum Spanning Tree
 * - Scheduling systems
 * - Top-K problems
 */

public class BinaryMinHeap {
    private int[] array;
    private int count;
    private int capacity;

    public BinaryMinHeap(int capacity) {
        this.capacity = capacity;
        this.count = 0;
        this.array = new int[this.capacity];
    }

    public BinaryMinHeap() {
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
        int left =  2 * i + 1;
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

    // in min heap min element is at the root
    public int getMinimum() {
        if(this.count == 0) {
            return -1;
        }
        return this.array[0];
    }

    public int getCount() {
        return this.count;
    }

    private void heapify(int i) {
        int left = getLeftChild(i);
        int right = getRightChild(i);
        int min = i;
        if(left != -1 && this.array[left] < this.array[min]) {
            min = left;
        }
        if(right != -1 && this.array[right] < this.array[min]) {
            min = right;
        }

        if(min != i) {
            swap(i, min);
            heapify(min);
        }
    }

    private void swap(int i, int j) {
        int temp = this.array[i];
        this.array[i] = this.array[j];
        this.array[j] = temp;
    }

    /*
        When doing deletion, the root element is deleted and replaced with last
        element of the heap, now the heap would be unbalanced, so from top
        we start doing heapify
     */
    public int deleteMin() {
        if(this.count == 0) {
            return -1;
        }
        int temp = this.array[0];
        this.array[0] = this.array[this.count - 1];
        this.count--;
        heapify(0);
        return temp;
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
        this.array[i] = data;

        int parent = getParent(i);
        // using i != 0, don't use i >= 0 because if i = 0 then parent is -1, will give exception
        while(i > 0 && this.array[i] <= this.array[parent]) {
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
