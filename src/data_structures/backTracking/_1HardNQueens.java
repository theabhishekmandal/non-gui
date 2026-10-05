package data_structures.backTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _1HardNQueens {
    public static void main(String[] args) {
        _1HardNQueens obj = new _1HardNQueens();
        System.out.println(obj.solveNQueens(1));
        System.out.println(obj.solveNQueens(2));
        System.out.println(obj.solveNQueens(3));
        System.out.println(obj.solveNQueens(4));
        System.out.println(obj.solveNQueens(5));
    }

    static List<List<String>> result;
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        result = new ArrayList<>();
        backTrack(0, board);
        return result;
    }

    public void backTrack(int row, char[][]board) {
        // Successfully placed all queens
        if (row == board.length) {
            List<String> answer = new ArrayList<>();
            for (char[] ans : board) {
                answer.add(new String(ans));
            }
            result.add(answer);
            return;
        }

        // Try every column in this row
        for (int col = 0; col < board.length; col++) {
            if (!isValid(row, col, board)) {
                continue;
            }

            // Make the decision
            board[row][col] = 'Q';

            // Explore consequences
            backTrack(row + 1, board);

            // Undo the decision
            board[row][col] = '.';
        }
    }

    public boolean isValid(int row, int col, char[][] board) {

        // check at same row but different column
        for (int r = row; r >= 0; r--) {
            if (board[r][col] == 'Q') {
                return false;
            }
        }

        // check upper right diagonal
        for (int r = row, c = col; r >= 0 && c < board.length; r--, c++) {
            if (board[r][c] == 'Q') {
                return false;
            }
        }

        // check upper left diagonal
        for (int r = row, c = col; r >= 0 && c >= 0; r--, c--) {
            if (board[r][c] == 'Q') {
                return false;
            }
        }
        return true;
    }
}
