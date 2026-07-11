package data_structures.strings;

import java.util.HashMap;
import java.util.Map;

/**
 * Longest substring without repeating characters — length only.
 * Classic sliding window: expand with {@code i}, shrink {@code start} when a repeat appears inside the window.
 */
public class _4MediumLongestNonRepeatedSubstringLength {
    public static void main(String[] args) {
        String[] strings = new String[]{
                "a", "ab", "abb", "abbac", "abcabcbb"
        };
        for (String s : strings) {
            System.out.println("longest non repeated substring length for string "
                    + s + " " + getLongestNonRepetitiveSubstring(s));
        }
    }

    /**
     * @return length of the longest substring of {@code s} with all distinct characters
     */
    private static int getLongestNonRepetitiveSubstring(String s) {
        // Best window length seen so far
        int len = 0;
        // Left edge of the current valid window [start, i]
        int start = 0;
        // Last index where each character appeared (updated as we scan)
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                // If this char was seen inside the current window, move start past that occurrence
                if (map.get(c) >= start) {
                    start = map.get(c) + 1;
                }
            }
            // Window [start, i] has no repeats; its size is i - start + 1
            len = Math.max(len, i - start + 1);

            map.put(c, i);
        }
        return len;
    }
}
