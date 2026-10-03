package data_structures.priority_queues_and_heaps.problems;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/trapping-rain-water-ii/description/
 *
 * 407. Trapping Rain Water II (Hard)
 *
 * Given an m x n integer matrix heightMap representing the height of each unit
 * cell in a 2D elevation map, return the volume of water it can trap after raining.
 *
 * Example 1:
 * Input: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
 * Output: 4
 * Explanation: After the rain, water is trapped between the blocks.
 * We have two small ponds 1 and 3 units trapped.
 * The total volume of water trapped is 4.
 *
 * Example 2:
 * Input: heightMap = [[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]]
 * Output: 10
 *
 * Constraints:
 * - m == heightMap.length
 * - n == heightMap[i].length
 * - 1 <= m, n <= 200
 * - 0 <= heightMap[i][j] <= 2 * 10^4
 */

/*
Water can escape through the boundary, so the boundary is where the problem starts.

Boundary cells cannot trap water — they are the outer walls and water can flow out from them.
The amount of water an inner cell can hold is determined by the lowest wall on the path to the outside.
Therefore, always process the lowest current boundary first → this naturally gives us a Min Priority Queue.
Take the lowest boundary cell and examine its unvisited neighbours:

If neighbour.height < currentBoundary.height, water can be trapped:
water += currentBoundary - neighbour

The neighbour now effectively becomes a wall of height:
max(neighbour, currentBoundary)
Push this effective boundary height into the Min Heap.
Repeat — the newly filled cell becomes part of the boundary from which we expand further inward.
The key mental model

We are not filling cells independently. We are gradually moving the boundary inward.

Outside
  ↓
[Boundary] → [lowest boundary] → [neighbour] → [next neighbour]
                   ↑
             controls water level

The important invariant to remember is:

At every step, the Min Heap contains the current "walls" surrounding the unvisited region, and we expand through the lowest wall first.

And the one-line memory hook:

“Start from the outside, always break through the lowest wall, and carry its effective height inward.”

That is why BFS + Min Heap works here: it's essentially a best-first flood from the boundary, where the priority is the current boundary height.

 */

public class _6HardTrappingRainWater2 {
    public static void main(String[] args) {
        System.out.println(trapRainWater(new int[][] {
                {1,4,3,1,3,2},{3,2,1,3,2,4},{2,3,3,2,3,1}
        }));
    }


    public static int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        int n = heightMap[0].length;

        // min 3 * 3 matrix is required to hold water.
        if (m <= 2 || n <= 2) {
            return 0;
        }

        PriorityQueue<Cell> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a.height));

        // those cells which are visited will not be processed again.
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            Cell leftMost = new Cell(i, 0, heightMap[i][0]);
            Cell rightMost = new Cell(i, n - 1, heightMap[i][n - 1]);

            priorityQueue.add(leftMost);
            priorityQueue.add(rightMost);

            visited[leftMost.row][leftMost.column] = true;
            visited[rightMost.row][rightMost.column] = true;
        }

        for (int i = 1; i < n - 1; i++) {
            Cell cell1 = new Cell(0, i, heightMap[0][i]);
            Cell cell2 = new Cell(m - 1, i, heightMap[m - 1][i]);

            priorityQueue.add(cell1);
            priorityQueue.add(cell2);

            visited[cell1.row][cell2.column] = true;
            visited[cell2.row][cell2.column] = true;
        }

        int[][] directions = {
                {0, 1}, {0, -1}, {1, 0}, {-1, 0}
        };

        int water = 0;

        while (!priorityQueue.isEmpty()) {
            Cell cell = priorityQueue.poll();

            for (int[] dir : directions) {
                int nr = cell.row + dir[0];
                int nc = cell.column + dir[1];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n || visited[nr][nc]) {
                    continue;
                }

                int neighbourHeight = heightMap[nr][nc];
                if (neighbourHeight < cell.height) {
                    water += cell.height - neighbourHeight;
                }

                int effectiveHeight = Math.max(neighbourHeight, cell.height);
                priorityQueue.add(new Cell(nr, nc, effectiveHeight));
                visited[nr][nc] = true;
            }
        }
        return water;
    }

    static class Cell {
        int row;
        int column;
        int height;

        public Cell(int row, int column, int height) {
            this.row = row;
            this.column = column;
            this.height = height;
        }
    }
}
