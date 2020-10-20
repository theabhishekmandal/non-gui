package miscellaneous.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Medium
 *  We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
 *
 * (Here, the distance between two points on a plane is the Euclidean distance.)
 *
 * You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)
 *
 *
 *
 * Example 1:
 *
 * Input: points = [[1,3],[-2,2]], K = 1
 * Output: [[-2,2]]
 * Explanation:
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
 * Example 2:
 *
 * Input: points = [[3,3],[5,-1],[-2,4]], K = 2
 * Output: [[3,3],[-2,4]]
 * (The answer [[-2,4],[3,3]] would also be accepted.)
 *
 *
 * Note:
 *
 * 1 <= K <= points.length <= 10000
 * -10000 < points[i][0] < 10000
 * -10000 < points[i][1] < 10000
 *
 *
 * Approach
 *  -   For finding K largest and k smallest elements heap and sort are the best way to do so
 *  -   Heap should be used in case of sort
 *  -   In this question we can use the max heap and not min heap to find the k smallest points to the origin
 *      -   Max heap is better than Min heap because if the size of queue exceeds k then we can remove the extra elements
 *      -   We are removing extra elements so that heapifying the heap after new key insertion should not take much execution
 *          time
 *      -   If we use min heap then, heap size may increase more than k, then heapifying the heap may take more time.
 *      -   We are creating Max heap on the basis of max euclidean distance
 *
 *  -   Sorting approach can be used as shown in method kClosest2
 */
public class KClosestPointsToOrigin {

    private static final Random random = new Random();

    public static void main(String[] args) {
        int length = 1 + random.nextInt(5);
        int[][] arr = new int[length][2];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = getPoint();
        }

        int k = 1 + random.nextInt(arr.length);
        System.out.println("Input Array " + Arrays.deepToString(arr) + " k = " + k);
        System.out.println("kClosest pair = " + Arrays.deepToString(kClosest(arr, k)));
        System.out.println("kClosest2 pair = " + Arrays.deepToString(kClosest2(arr, k)));
    }

    static class Pair<T, V> {

        T first;
        V second;

        Pair(T first, V second) {
            this.first = first;
            this.second = second;
        }

        static <T, V> Pair<T, V> of(T first, V second) {
            return new Pair<>(first, second);
        }

        public T getFirst() {
            return first;
        }

        public V getSecond() {
            return second;
        }
    }

    private static int[][] kClosest(int[][] arr, int k) {
        if(k == arr.length) return arr;
        // Comparator on the basis of max euclidean distance
        Comparator<Pair<Double, Pair<Integer, Integer>>> comparator = (x, y) -> (int) (y.first - x.first);
        PriorityQueue<Pair<Double, Pair<Integer, Integer>>> queue = new PriorityQueue<>(comparator);

        for (int[] ints : arr) {
            int x = ints[0];
            int y = ints[1];
            queue.add(Pair.of(getEuclideanDistance(x, y), Pair.of(x, y)));
            if (queue.size() > k) {
                queue.poll();
            }
        }
        int[][] temp = new int[k][2];
        for(int i = 0; i < temp.length; i++) {
            Pair<Double, Pair<Integer, Integer>> pair = queue.poll();
            assert pair != null;
            temp[i] = new int[]{pair.getSecond().getFirst(), pair.getSecond().getSecond()};
        }
        return temp;
    }

    private static int[][] kClosest2(int[][] arr, int k) {
        Arrays.sort(arr, Comparator.comparingInt(x -> (x[0] * x[0] + x[1] * x[1])));
        return Arrays.copyOfRange(arr, 0, k);
    }

    private static double getEuclideanDistance(int x, int y) {
        return Math.sqrt(((double)x * x) + y * y);
    }

    private static int[] getPoint() {
        int x = -10 + random.nextInt(10);
        int y = -10 + random.nextInt(10);
        return new int[]{x, y};
    }
}
