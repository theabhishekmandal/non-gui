package data_structures.priority_queues_and_heaps.binary_heap.binary_heap_impl;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BinaryHeapImpl {
    public static void main(String[] args) {
        var random = new Random();
        List<Integer> inputList = IntStream.range(0, 10).boxed().map(x -> random.nextInt(10)).collect(Collectors.toList());
        System.out.println("input list = " + inputList);

        System.out.println("Binary Max Heap insertion and deletion");
        var binaryMaxHeap = new BinaryMaxHeap();
        for(int i : inputList) {
            binaryMaxHeap.insert(i);
        }
        int k;
        while((k = binaryMaxHeap.deleteMax()) != -1) {
            System.out.println(k);
        }

        System.out.println("\nBinary Min Heap insertion and deletion");
        var binaryMinHeap = new BinaryMinHeap();
        for(int i : inputList) {
            binaryMinHeap.insert(i);
        }
        int y;
        while((y = binaryMinHeap.deleteMin()) != -1) {
            System.out.println(y);
        }

        var inputArray = new int[inputList.size()];
        for(int i = 0; i < inputList.size(); i++) {
            inputArray[i] = inputList.get(i);
        }

        System.out.println("\nBinary Max Heap insertion and deletion");
        var binaryMaxHeap2 = new BinaryMaxHeap();
        binaryMaxHeap2.buildHeap(inputArray);
        while((y = binaryMaxHeap2.deleteMax()) != -1) {
            System.out.println(y);
        }

        System.out.println("\nBinary Min Heap insertion and deletion");
        var binaryMinHeap2 = new BinaryMinHeap();
        binaryMinHeap2.buildHeap(inputArray);
        while((y = binaryMinHeap2.deleteMin()) != -1) {
            System.out.println(y);
        }
    }
}
