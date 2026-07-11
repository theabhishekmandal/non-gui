package data_structures.trie;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
This is leetcode question.
https://leetcode.com/problems/longest-common-suffix-queries/description/?envType=daily-question&envId=2026-05-28

3093. Longest Common Suffix Queries

You are given two string arrays wordsContainer and wordsQuery.

For each wordsQuery[i], you need to find a string in wordsContainer that has the longest common suffix
with wordsQuery[i]. If there are multiple strings with the same length of the common suffix, pick the
one with the smallest index. If there are no common suffix, pick the string with the smallest length;
if there are multiple strings with the smallest length, pick the one with the smallest index.

Return an integer array ans where ans[i] is the index of the string in wordsContainer that is selected
for wordsQuery[i].

Explanation:
    A common suffix of two strings is the longest substring that appears at the end of both strings.
    For example, "bcd" and "xbcd" share the suffix "bcd", while "abcd" and "xyz" share no suffix.

    For each query, pick the index from wordsContainer using this priority:
        1. Longest common suffix with the query (larger suffix length wins).
        2. If multiple indices tie on suffix length, pick the smallest index.
        3. If there is no common suffix at all, pick the shortest string in wordsContainer.
        4. If multiple strings tie on shortest length, pick the smallest index among them.

Example 1:
    Input: wordsContainer = ["abcd","bcd","xbcd"], wordsQuery = ["cd","bcd","xyz"]
    Output: [1,1,1]
    Explanation:
        For "cd", all three strings share the suffix "cd" (length 2). Pick the smallest index: 1 ("bcd").
        For "bcd", all three strings share the suffix "bcd" (length 3). Pick the smallest index: 1 ("bcd").
        For "xyz", there is no common suffix with any string. Pick the shortest string; "bcd" at index 1 has length 3.

Example 2:
    Input: wordsContainer = ["abcdef","uvwxyz"], wordsQuery = ["xyz","abcd"]
    Output: [1,0]
    Explanation:
        For "xyz", only "uvwxyz" at index 1 has a common suffix ("xyz", length 3).
        For "abcd", there is no common suffix with any string. Both strings have length 6, so pick the smallest index: 0.

Example 3:
    Input: wordsContainer = ["bc","abc"], wordsQuery = ["abc"]
    Output: [1]
    Explanation:
        For "abc", "bc" at index 0 shares suffix "bc" (length 2), while "abc" at index 1 shares suffix "abc" (length 3).
        The longest common suffix is length 3, so the answer is index 1.

Constraints:
    - 1 <= wordsContainer.length, wordsQuery.length <= 10^4
    - 1 <= wordsContainer[i].length, wordsQuery[i].length <= 1000
    - wordsContainer[i] and wordsQuery[i] consist only of lowercase English letters.
 */
public class _1HardLongestCommonSuffixQueries {

    static class TrieNode {
        private Map<Character, TrieNode> map;
        private int lengthOfMinString = Integer.MAX_VALUE;
        private int idx = Integer.MAX_VALUE;

        TrieNode() {
            this.map = new HashMap<>();
        }

        TrieNode getChildren(Character c) {
            return map.get(c);
        }
    }

    static TrieNode root;
    void insert(String word, int index) {
        TrieNode temp = root;
        // since we don't add any character at the root, this operation is clever part
        //  since for empty string we have to return the smallest length string index.
        if (word.length() < temp.lengthOfMinString) {
            temp.lengthOfMinString = word.length();
            temp.idx = index;
        }

        for (int i = 0; i < word.length(); i++) {
            // reverse the word.
            char c = word.charAt(word.length() - i - 1);

            if (!temp.map.containsKey(c)) {
                temp.map.put(c, new TrieNode());
            }

            temp = temp.getChildren(c);

            if (word.length() < temp.lengthOfMinString) {
                temp.idx = index;
                temp.lengthOfMinString = word.length();
            }
        }
    }
    public int search(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(word.length() - i - 1);
            if (node.map.containsKey(c)) {
                node = node.getChildren(c);
            } else {
                break;
            }
        }
        return node.idx;
    }

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        root = new TrieNode();
        for (int i = 0; i < wordsContainer.length; i++) {
            insert(wordsContainer[i], i);
        }
        int[] ans = new int[wordsQuery.length];
        int counter = 0;
        for (int i = 0; i < wordsQuery.length; i++) {
            int idx = search(wordsQuery[i]);
            ans[counter++] = idx;
        }
        return ans;
    }

    public static void main(String[] args) {
        _1HardLongestCommonSuffixQueries solution = new _1HardLongestCommonSuffixQueries();

        String[] wordsContainer1 = {"abcd", "bcd", "xbcd"};
        String[] wordsQuery1 = {"cd", "bcd", "xyz"};
        System.out.println(Arrays.toString(solution.stringIndices(wordsContainer1, wordsQuery1))); // [1, 1, 1]

        String[] wordsContainer2 = {"abcdef", "uvwxyz"};
        String[] wordsQuery2 = {"xyz", "abcd"};
        System.out.println(Arrays.toString(solution.stringIndices(wordsContainer2, wordsQuery2))); // [1, 0]

        String[] wordsContainer3 = {"bc", "abc"};
        String[] wordsQuery3 = {"abc"};
        System.out.println(Arrays.toString(solution.stringIndices(wordsContainer3, wordsQuery3))); // [1]
    }
}
