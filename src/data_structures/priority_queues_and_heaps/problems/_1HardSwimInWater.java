package data_structures.priority_queues_and_heaps.problems;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/swim-in-rising-water/description/
 * Why do we use a PriorityQueue and return the maximum elevation?
 *
 * Problem Summary:
 * ----------------
 * - Each cell in the grid has a unique elevation.
 * - You start at (0, 0) and want to reach (n-1, n-1) as early as possible.
 * - At time t, you can enter a cell if its elevation <= t.
 * - You can move in four directions: up, down, left, right.
 *
 * PriorityQueue Usage:
 * --------------------
 * - We use a Min Heap (PriorityQueue) to always pick the next cell with the
 *   lowest elevation we can step into.
 * - This ensures we explore the least "costly" (in terms of elevation) path first.
 * - This behavior is similar to Dijkstra's algorithm for shortest path.
 *
 * Why Return Maximum Elevation in Path:
 * -------------------------------------
 * - The "cost" of a path from (0,0) to (n-1,n-1) is determined by the highest
 *   elevation along that path.
 * - You can only step into a cell when the water level (time) reaches at least
 *   that elevation.
 * - Therefore, to reach the destination at the earliest time, you need to find
 *   the path from start to end where the highest elevation is minimized.
 *
 * - We track the maximum elevation along the current path using:
 *      max = Math.max(max, currentCellElevation);
 *
 * - Once we reach the destination, this 'max' will be the minimum time required
 *   to swim from start to end.
 *
 * Time and Space Complexity:
 * --------------------------
 * - Time Complexity: O(n^2 * log n), each of the n^2 cells is added to the priority
 *   queue once and heap operations take O(log n^2) = O(log n).
 *
 * - Space Complexity: O(n^2), for visited matrix and priority queue.
 */


public class _1HardSwimInWater {
    public static void main(String[] args) {
        int[][][] arr = new int[][][]{
                {
                        {0, 2}, {1, 3}
                },
                {
                        {0, 1, 2, 3, 4},
                        {24, 23, 22, 21, 5},
                        {12, 13, 14, 15, 16},
                        {11, 17, 18, 19 , 20},
                        {10, 9, 8, 7, 6}
                }
        };

        for (int[][] grid : arr) {
            System.out.println(swimInWater(grid));
        }
    }

    public static int swimInWater(int[][] grid) {
        // Min-heap based on elevation value
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparing(x -> x[0]));
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        int length = grid.length;

        // Track visited cells to avoid cycles
        boolean[][] visited = new boolean[length][length];
        visited[0][0] = true;

        // Start at top-left corner with its elevation
        int max = 0;
        priorityQueue.add(new int[]{grid[0][0], 0, 0}); // {elevation, x, y}

        while (!priorityQueue.isEmpty()) {
            var data = priorityQueue.poll();
            int elevation = data[0];
            int x = data[1];
            int y = data[2];

            // Maintain max elevation seen so far — that's the effective time taken
            max = Math.max(max, elevation);

            // Reached destination
            if (x == length - 1 && y == length - 1) {
                return max;
            }

            // Explore 4 directions
            for (int[] dir : directions) {
                int x_ = x + dir[0];
                int y_ = y + dir[1];

                if (x_ >= 0 && x_ < length && y_ >= 0 && y_ < length && !visited[x_][y_]) {
                    visited[x_][y_] = true;
                    priorityQueue.add(new int[]{grid[x_][y_], x_, y_});
                    // Optional: Debug peek of current min elevation in heap
                    System.out.println(Arrays.toString(priorityQueue.peek()));
                }
            }
        }

        // Should never reach here since destination is always reachable
        return max;
    }
}
