package data_structures.graph.problems;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://neetcode.io/problems/count-number-of-islands/question
 *
 * Number of Islands (Medium)
 *
 * Given a 2D grid grid where '1' represents land and '0' represents water,
 * count and return the number of islands.
 *
 * An island is formed by connecting adjacent lands horizontally or vertically
 * and is surrounded by water. You may assume water is surrounding the grid
 * (i.e., all the edges are water).
 *
 * Example 1:
 * Input: grid = [
 *     ["0","1","1","1","0"],
 *     ["0","1","0","1","0"],
 *     ["1","1","0","0","0"],
 *     ["0","0","0","0","0"]
 * ]
 * Output: 1
 *
 * Example 2:
 * Input: grid = [
 *     ["1","1","0","0","1"],
 *     ["1","1","0","0","1"],
 *     ["0","0","1","0","0"],
 *     ["0","0","0","1","1"]
 * ]
 * Output: 4
 *
 * Constraints:
 * - 1 <= grid.length, grid[i].length <= 100
 * - grid[i][j] is '0' or '1'
 */

/**
 *  This is simple BFS approach, only thing to keep in mind is instead of processing two times we have to process only one
 *  time by marking it visited while enqueing.
 */
public class _4MediumNumberOfIslands {
    public static void main(String[] args) {
        var obj = new _4MediumNumberOfIslands();
        System.out.println(obj.numIslands(new char[][]{
                {'0', '1', '1', '1', '0'},
                {'0', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        }));

        System.out.println(obj.numIslands(new char[][]{
                {'1', '1', '0', '0', '1'},
                {'1', '1', '0', '0', '1'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        }));
    }
    public int numIslands(char[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '0' || visited[i][j]) {
                    continue;
                }
                bfs(grid, i, j, visited);
                count++;
            }
        }
        return count;
    }


    public void bfs(char[][] grid, int i, int j, boolean[][] visited) {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{i, j});
        visited[i][j] = true;
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] dir : dirs) {
                int x = cell[0] + dir[0];
                int y = cell[1] + dir[1];
                if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
                    continue;
                }
                char neighbour = grid[x][y];
                if (neighbour == '1' && !visited[x][y]) {
                    queue.add(new int[]{x, y});
                    visited[x][y] = true;
                }
            }
        }
    }
}
