package miscellaneous;

import java.util.ArrayList;
import java.util.List;

/**
 * Generates all well-formed strings of {@code n} pairs of parentheses (LeetCode 22 style).
 * <p>
 * Backtracking: we track how many {@code '('} and {@code ')'} are still allowed to be appended.
 * A partial string is invalid if we have used more closing than opening, or if counts go negative.
 */
public class GenerateParenthesis {
    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }




    /** Builds all combinations of length {@code 2n} with balanced parentheses. */
    private static List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        // First character must be '('; remaining: (n-1) opens and n closes left to place.
        gen(list, "(", n - 1, n);
        return list;
    }




    /**
     * @param open  remaining {@code '('} we may still append
     * @param close remaining {@code ')'} we may still append (must stay ≥ remaining opens in the suffix)
     */
    private static void gen(List<String> list, String string, int open, int close) {
        if (open == 0 && close == 0) {
            list.add(string);
            return;
        }
        // Invalid: more ')' than '(' so far, or exhausted one side too early.
        else if (close < open || close < 0 || open < 0) {
            return;
        }
        gen(list, string + "(", open - 1, close);
        gen(list, string + ")", open, close - 1);
    }
}
