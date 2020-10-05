package data_structures.priority_queues_and_heaps.binary_heap.binary_heap_impl;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HeapSortImpl {
    public static void main(String[] args) {
        var random = new Random();
        int test = 1 + random.nextInt(5);
        var heapSort = new HeapSort();
        while(test-- > 0) {
            List<Integer> integerList = IntStream.range(0, 10).boxed()
                    .map(x -> random.nextInt(100))
                    .collect(Collectors.toList());
            System.out.println("input list = " + integerList);
            heapSort.initialise(integerList);
            heapSort.sort();
            System.out.println("output ascending order sorted list " + heapSort);
            heapSort.destroy();
        }
    }
}
