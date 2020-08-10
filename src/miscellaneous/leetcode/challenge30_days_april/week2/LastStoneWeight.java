package miscellaneous.leetcode.challenge30_days_april.week2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Week 2 Day 5
 * We have a collection of stones, each stone has a positive integer weight.
 *
 * Each turn, we choose the two heaviest stones and smash them together.  Suppose the stones have weights x and y with x <= y.  The result of this smash is:
 *
 *     If x == y, both stones are totally destroyed;
 *     If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.
 *
 * At the end, there is at most 1 stone left.  Return the weight of this stone (or 0 if there are no stones left.)
 *
 *
 *
 * Example 1:
 *
 * Input: [2,7,4,1,8,1]
 * Output: 1
 * Explanation:
 * We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
 * we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
 * we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
 * we combine 1 and 1 to get 0 so the array converts to [1] then that's the value of last stone.
 *
 *
 *
 * Note:
 *     1 <= stones.length <= 30
 *     1 <= stones[i] <= 1000
 *
 * Approach
 *      -   Build Max Heap, and then take largest and second largest among the root, root's left child and right child;
 *      -   Remove the root and assign the second largest value as (largest - secondLargest)
 *      -   Build Max Heap again, and repeat this process until one element remains in the list
 */
public class LastStoneWeight {
    public static void main(String[] args) {
        Random random = new Random();
        for(int j = 1; j < 10; j++){
            int[] arr = new int[j];
            for(int i = 0; i < arr.length; i++)
                arr[i] = random.nextInt(100);
            int lastStoneWeight = lastStoneWeight(arr);
            System.out.println(lastStoneWeight);
            System.out.println();
        }
    }

    private static int lastStoneWeight(int[] arr) {
        if(arr == null || arr.length == 0) return 0;
        List<Integer> list = new ArrayList<>();
        for(int i : arr) list.add(i);
        while(list.size() > 1){
            buildMaxHeap(list);
            int largest = list.get(0);
            int secondLargestIndex = 1;
            if(list.size() >= 3){
                secondLargestIndex = (list.get(1) > list.get(2)) ? 1 : 2;
            }
            list.set(secondLargestIndex, largest - list.get(secondLargestIndex));
            list.remove(0);
        }
        return list.get(0);
    }
    private static void buildMaxHeap(List<Integer> arr){
        // starting with the last non-leaf node
        int lastNonLeafNode = (arr.size() / 2) - 1;
        for(int i = lastNonLeafNode; i >= 0; i--)
            heapify(arr, arr.size(), i);
    }

    private static void heapify(List<Integer> arr, int length, int i) {
        // initially taking the current node as the largest
        int largest = i;

        // getting the left and the right child
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if(left < length && arr.get(left) > arr.get(largest))
            largest = left;

        if(right < length && arr.get(right) > arr.get(largest))
            largest = right;

        // if i is not the largest then start making heap of the subtree affected
        if(largest != i){
            int temp = arr.get(largest);
            arr.set(largest, arr.get(i));
            arr.set(i, temp);
            heapify(arr, length, largest);
        }
    }
}
