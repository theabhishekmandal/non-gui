package DataStructures.Queue.Problems;

import DataStructures.linked_list.Node.DoublyLinkedList;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *  Given a list of numbers and window size k, find the maximum number in the sub array of length k
 *  eg:
 *      arr = [1, 3, -1, -3, 5, 3, 6, 7] k = 3
 *      Window Position                 Max
 *      [1, 3, -1], -3, 5, 3, 6, 7     3
 *      1, [3, -1, -3], 5, 3, 6, 7     3
 *      1, 3, [-1, -3, 5], 3, 6, 7     5
 *      1, 3, -1, [-3, 5, 3], 6, 7     5
 *      1, 3, -1, -3, [5, 3, 6], 7     6
 *      1, 3, -1, -3, 5, [3, 6, 7]     7
 *
 *  Approach is that we are using doubly linked list as data structure which will act as a queue
 *  in which insertion and deletion can be performed in both of the ends.
 *
 *  Also we will be storing indices of the values rather than the value itself, because
 *  through index we know which is oldest index that's need to be popped if the sliding window moves
 *
 *  while queue != empty and currentData > list.get(queue.lastIndexValue) then
 *      keep popping from the last (because only the highest value can come in front of
 *      the queue)
 *
 *  if queue is not empty and if queue is full with k elements then
 *      pop out the last index
 *
 *  now you can push the index in the queue
 *
 *  if we reach k elements
 *      then store the max value from queue.frontIndexValue
 *
 */

public class MaximumInSlidingWindow {
    public static void main(String[] args) {
        Random random = new Random();
        List<Integer> list = IntStream.range(0, 10 + random.nextInt(20))
                            .map(x -> random.nextInt(100))
                            .boxed().collect(Collectors.toList());
        int k;
        do {
            k = 2 + random.nextInt(list.size());
        } while (list.size() % k != 0);
        System.out.println( "list size is " + list.size() + "\nlist is\n" + list + "\nwindow size is\n" + k + "\n");
        List<Integer> maxSumList = getMaxSumInThisWindow(list, k);
        System.out.println(maxSumList);
    }

    private static List<Integer> getMaxSumInThisWindow(List<Integer> list, int maxSize) {
        if(list == null || list.isEmpty() || maxSize < 1) return new ArrayList<>();

        DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
        List<Integer> answerlist = new ArrayList<>();

        for(int i = 0; i < list.size(); i++){

            // for checking if front of the queue has maximum element
            while(dll.getSize() != 0 && list.get(i) > list.get(dll.getTail().getData()))
                dll.deleteLast();

            // for checking if the current element should reside in the given window
            if(dll.getSize() != 0 && dll.getHead().getData() + maxSize == i)
                dll.deleteFirst();

            dll.addLast(i);
            // appending the result
            if(i + 1 >= maxSize)
            answerlist.add(list.get(dll.getHead().getData()));
        }
        return answerlist;
    }
}
