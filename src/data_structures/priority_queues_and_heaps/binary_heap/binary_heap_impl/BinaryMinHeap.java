package data_structures.priority_queues_and_heaps.binary_heap.binary_heap_impl;

import java.util.Arrays;

/**
 * Binary Min Heap implementation.
 * Operations performed are
 *  -   findMinimum
 *  -   deleteMinimum
 *  -   insertion
 *
 *  findMinimum
 *      -   In Binary Min Heap the first element denotes the minimum element in heap
 *
 *  deleteMinimum
 *      -   As first element is the minimum element in the heap, to delete it first
 *          it is stored in a temporary variable
 *      -   Then the min element is replaced by the last element of the heap
 *      -   Then the count is decreased,
 *      -   But now, the heap does not follow the heap conditions i.e parent should
 *          be greater than the child, to avoid this we start doing heapify from the
 *          root i.e top to bottom heapify
 *
 *  insertion
 *      -   A new element is inserted at the end
 *      -   But this may result in unbalanced heap, so heapify is done from the current
 *          element to the root element i.e bottom to top heapify
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

    @Override
    public String toString() {
        return Arrays.toString(this.array);
    }
}
