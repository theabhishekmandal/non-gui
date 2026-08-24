package miscellaneous.leetcode.contests.weekly514;

/**
 * LeetCode 4016 — Maximum Area of Two Non-Overlapping Square Submatrices
 * Weekly Contest 514
 * https://leetcode.com/problems/maximum-area-of-two-non-overlapping-square-submatrices/description/
 *
 * You are given a 0-indexed binary matrix mat of size n x m.
 *
 * Choose two non-overlapping square submatrices such that:
 *  - both squares have the same side length k
 *  - every cell inside both squares is 1
 *  - the two squares do not share any cell
 *
 * Return the maximum possible area of such a square (k * k).
 * If it is impossible to choose two such squares, return 0.
 *
 * Example 1:
 * Input:
 * mat = [
 *   [1, 1, 0, 1, 1],
 *   [1, 1, 0, 1, 1],
 *   [0, 0, 0, 0, 0],
 *   [1, 1, 0, 1, 1],
 *   [1, 1, 0, 1, 1]
 * ]
 * Output: 4
 * Explanation: Two non-overlapping 2 x 2 squares of all 1s exist
 * (top-left and bottom-right). Area = 2 * 2 = 4.
 *
 * Example 2:
 * Input:
 * mat = [
 *   [1, 0],
 *   [0, 1]
 * ]
 * Output: 1
 * Explanation: Two non-overlapping 1 x 1 squares of 1s exist. Area = 1.
 */
public class _3MaxAreaOfTwoOverlappingSquares {
    public static void main(String[] args) {
        _3MaxAreaOfTwoOverlappingSquares solver = new _3MaxAreaOfTwoOverlappingSquares();

        int[][] mat1 = {
                {1, 1, 0, 1, 1},
                {1, 1, 0, 1, 1},
                {0, 0, 0, 0, 0},
                {1, 1, 0, 1, 1},
                {1, 1, 0, 1, 1}
        };
        System.out.println(solver.maxArea(mat1)); // expected: 4

        int[][] mat2 = {
                {1, 0},
                {0, 1}
        };
        System.out.println(solver.maxArea(mat2)); // expected: 1
    }


    /**
     * Lets break down the problem in different parts to get the solution.
     * for a given n * m matrix, what is the minimum length square we can have?
     *  k = min(n, m)
     *
     * now for our use case we also want to have 2 overlapping squares of side k which means
     *
     * k + k <= n  --> k <= n/2 vertically placed squares.
     * or
     * k + k <= m --> k <= m/2 horizontally placed squares
     *
     * so max side k which can have is k = max(n / 2, m / 2)
     *
     * But here is the thing we just can't use k = max(n / 2, m / 2) because it is taking the k which is the max length
     * we can place side by side either vertically or horizontally. It does not consider whether it is square or not.
     * For example 3 * 100 matrix k comes as  k = max(3/2, 100/2) = 50 we have 50 for one side but for other side we have 3 which is not a square.
     *
     * so we have to consider the above k = min(n, m) which says minimum length k which forms a square. Combining both we have
     * k = min(min(n, m), max(n / 2, m / 2))
     *
     * Now the next step is to find a matrix of k * k where all the cells have 1.
     * We can achieve this by prefix matrix as mentioned below.
     *
     * Now for every k starting in descending order we will search using prefix matrix.
     * we will have minCol, maxCol, minRow, maxRow.
     * if their difference is greater than or equal to k then we have found the square matrix.
     */
    public int maxArea(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int k = Math.min(Math.min(n, m), Math.max(n / 2, m / 2));
        int[][] prefix = new int[n + 1][m + 1];

        for (int i = 1; i < prefix.length; i++) {
            for (int j = 1; j < prefix[0].length; j++) {
                prefix[i][j] = mat[i - 1][j - 1] + prefix[i - 1][j] +
                        prefix[i][j - 1] - prefix[i - 1][j - 1];
            }
        }

        for (int i = k; i > 0; i--) {
            if (canPlaceTwoSquares(mat, prefix, i)) {
                return i * i;
            }

        }
        return 0;
    }

    private boolean canPlaceTwoSquares(int[][] mat, int[][] prefix, int k) {
        int maxCol = Integer.MIN_VALUE;
        int minCol = Integer.MAX_VALUE;

        int minRow = Integer.MAX_VALUE;
        int maxRow = Integer.MIN_VALUE;

        for (int i = 0; i + k < prefix.length; i++) {
            for (int j = 0; j + k < prefix[0].length; j++) {
                int ones = prefix[i + k][j + k] - prefix[i + k][j] - prefix[i][j + k] + prefix[i][j];
                if (ones != k * k) {
                    continue;
                }
                minRow = Math.min(minRow, i);
                maxRow = Math.max(maxRow, i);
                minCol = Math.min(minCol, j);
                maxCol = Math.max(maxCol, j);

                if (maxRow - minRow >= k || maxCol - minCol >= k) {
                    return true;
                }
            }
        }

        return false;
    }
}
