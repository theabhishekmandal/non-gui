package data_structures.priority_queues_and_heaps.problems;

import java.util.*;

/**
 * Merge K sorted Lists.
 * Use min priority Queue.
 * Pull the head nodes of each sorted lists and add it to queue.
 * Each node will be the pointer to the next node.
 * Since priority queue follows min order. Pull the first element and add it to final list.
 */
public class _2EasyMergeKSortedLists {
    public static void main(String[] args) {
        // Create a list of sorted integer lists
        List<List<Integer>> sortedLists = new ArrayList<>();

        // First sorted list: 1, 4, 7
        sortedLists.add(Arrays.asList(1, 4, 7));

        // Second sorted list: 2, 5, 8
        sortedLists.add(Arrays.asList(2, 5, 8));

        // Third sorted list: 3, 6, 9
        sortedLists.add(Arrays.asList(3, 6, 9));

        // Step 2: Merge them using a priority queue
        List<Integer> mergedList = mergeSortedLists(sortedLists);

        // Step 3: Print the result
        System.out.println("Merged List: " + mergedList);
    }
    // Helper class to store value and source list info
    static class Node {
        int value;
        int listIndex;   // from which list
        int elementIndex; // index within the list

        Node(int value, int listIndex, int elementIndex) {
            this.value = value;
            this.listIndex = listIndex;
            this.elementIndex = elementIndex;
        }
    }

    public static List<Integer> mergeSortedLists(List<List<Integer>> lists) {
        PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparingInt(n -> n.value));
        List<Integer> result = new ArrayList<>();

        // Initialize the heap with the first element of each list
        for (int i = 0; i < lists.size(); i++) {
            if (!lists.get(i).isEmpty()) {
                minHeap.offer(new Node(lists.get(i).get(0), i, 0));
            }
        }

        // Extract the smallest and add next element from that list
        while (!minHeap.isEmpty()) {
            Node current = minHeap.poll();
            result.add(current.value);

            int nextIndex = current.elementIndex + 1;
            if (nextIndex < lists.get(current.listIndex).size()) {
                minHeap.offer(new Node(lists.get(current.listIndex).get(nextIndex), current.listIndex, nextIndex));
            }
        }

        return result;
    }
}
