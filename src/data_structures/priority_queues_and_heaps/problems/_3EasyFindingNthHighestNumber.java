package data_structures.priority_queues_and_heaps.problems;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Below is the implementation of finding the nth largest variable in a single iteration,
 * To find the nth variable we have to use n variables.
 *
 * Out of all three approaches execution time is for large value of k
 *  -   solve > solve3 > solve2
 * Approach :
 *  -   Initialise array kArray of n size which will hold our 1 to n largest value in reverse order
 *      i.e 0 index will contain nth largest value and n - 1 index will contain the largest value
 *  -   now iterate over input array and check if input is greater than kArray(using reverse index)
 *          -   if it is greater then first replace every other index i.e i < indexToBeReplaced with kArray[i + 1] value
 *              and assign kArray[indexToBeReplaced] = input, then break
 *              eg: initially kArray[m, m, m], input = [1, 2, 3]
 *                  when input = 1, kArray[m, m, 1]
 *                                          /  /
 *                       input = 2, kArray[m, 1, 2]
 *                                          /  /
 *                       input = 3, kArray[1, 2, 3]
 *              eg:
 *                  initially kArray[m, m, m], input = [3, 1, 2]
 *                  when input = 3, kArray[m, m, 3]
 *                                          /
 *                       input = 1, kArray[m, 1, 3]
 *                                          /
 *                       input = 2, kArray[1, 2, 3]
 *
 *
 */
public class _3EasyFindingNthHighestNumber {
    public static void main(String[] args){
        int[] arr = new int[20];
        Random random = new Random();
        for(int i = 0; i < arr.length; i++){
            arr[i] = random.nextInt(Integer.MAX_VALUE);
        }
        int k = 1 + random.nextInt(arr.length);

        List<String> timeList = new ArrayList<>();

        System.out.println("k = " + k + "\n" + Arrays.toString(arr));
        LocalDateTime start = LocalDateTime.now();

        System.out.println(solve(arr, k)[0]);
        System.out.println("solve = " + Arrays.toString(solve(arr, k)));
        timeList.add( " Duration solve= " + Duration.between(start, LocalDateTime.now()).toMillis());

        start = LocalDateTime.now();
        System.out.println("solve2 = " + Arrays.toString(solve2(arr, k)));
        timeList.add( " Duration solve2= " + Duration.between(start, LocalDateTime.now()).toMillis());

        start = LocalDateTime.now();
        System.out.println("solve3 = " + Arrays.toString(solve3(arr, k)));
        timeList.add( " Duration solve3= " + Duration.between(start, LocalDateTime.now()).toMillis());

        timeList.forEach(System.out::println);
    }

    static int[] solve(int[] arr, int k) {
        int[] kArray = new int[k];
        Arrays.fill(kArray, Integer.MIN_VALUE);
        for(int num : arr) {
            for(int i = kArray.length - 1; i >= 0; i--) {
                if(num > kArray[i]) {
                    System.arraycopy(kArray, 1, kArray, 0, i);
                    kArray[i] = num;
                    break;
                }
            }
        }
        return kArray;
    }

    static int[] solve2(int[] arr, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, Comparator.naturalOrder());
        for(int i : arr) {
            queue.add(i);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        int[] temp = new int[k];
        for(int i = 0; i < k; i++) {
            if(!queue.isEmpty()) {
                temp[i] = queue.poll();
            }
        }
        return temp;
    }

    static int[] solve3(int[] arr, int k) {
        Arrays.sort(arr);
        return Arrays.copyOfRange(arr, arr.length - k, arr.length);
    }
}