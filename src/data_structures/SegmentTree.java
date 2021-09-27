package data_structures;

import java.util.Arrays;

import static java.lang.System.err;
import static java.lang.System.out;

public class SegmentTree {
    private final int[] segmentTree;

    public SegmentTree(int[] arr) {
        final int x = (int) Math.ceil(Math.log(arr.length - 1) / Math.log(2));
        final int maxsize = 2 * (int) Math.pow(2, x) - 1;
        segmentTree = new int[maxsize];
        createTree(arr, 0, arr.length - 1, 0);
    }

    private int getMid(int one, int two) {
        return one + (two - one) / 2;
    }

    private int createTree(int[] arr, int start, int end, int index) {
        if (start == end) {
            segmentTree[index] = arr[start];
            return segmentTree[index];
        }
        int mid = getMid(start, end);
        segmentTree[index] = createTree(arr, start, mid, getLeft(index)) +
                createTree(arr, mid + 1, end, getRight(index));
        return segmentTree[index];
    }

    private int getSumCheck(int segmentStart, int segmentEnd, int arrayStart, int arrayEnd, int index) {
        if (arrayStart <= segmentStart && arrayEnd >= segmentEnd) {
            return segmentTree[index];
        }
        if (arrayStart > segmentEnd || segmentStart > arrayEnd) {
            return 0;
        }
        final int mid = getMid(segmentStart, segmentEnd);
        return getSumCheck(segmentStart, mid, arrayStart, arrayEnd, getLeft(index)) +
                getSumCheck(mid + 1, segmentEnd, arrayStart, arrayEnd, getRight(index));
    }

    private int getSum(final int[] arr, final int arrayStart, final int arrayEnd) {
        if (arrayStart < 0 || arrayEnd > arr.length - 1 || arrayEnd < arrayStart) {
            return -1;
        }
        return getSumCheck(0, arr.length - 1, arrayStart, arrayEnd, 0);
    }

    private void printArray() {
        out.println(Arrays.toString(segmentTree));
    }

    private void updateUtil(int segmentStart, int segmentEnd, int arrayIndex, int diff, int segIndex) {
        if (segmentEnd < arrayIndex || segmentStart > arrayIndex) {
            return;
        }
        segmentTree[segIndex] += diff;
        if (segmentStart != segmentEnd) {
            final int mid = getMid(segmentStart, segmentEnd);
            updateUtil(segmentStart, mid, arrayIndex, diff, 2 * segIndex + 1);
            updateUtil(mid + 1, segmentEnd, arrayIndex, diff, 2 * segmentEnd + 2);
        }
    }

    private void update(int[] arr, int index, int val) {
        if (index < 0 || index > arr.length - 1) {
            err.println("Invalid index");
            return;
        }
        final int diff = val - arr[index];
        arr[index] = val;
        updateUtil(0, arr.length - 1, index, diff, 0);
    }

    private static int getLeft(int index) {
        return 2 * index + 1;
    }

    private static int getRight(int index) {
        return 2 * index + 2;
    }

    public static void main(String[] args) {
        final int[] arr = {1, 3, 5, 7, 9, 11};
        final SegmentTree ob = new SegmentTree(arr);
        ob.printArray();
        out.println(ob.getSum(arr, 0, 1));
        ob.update(arr, 0, 14);
        out.println(Arrays.toString(arr));
        ob.printArray();

    }
}


