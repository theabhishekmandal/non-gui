package data_structures.trie;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/longest-common-suffix-queries/description/?envType=daily-question&envId=2026-05-28
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
