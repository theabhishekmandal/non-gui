package miscellaneous.leetcode.challenge30_days_april.week1;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Week 1 day 6
 * Given an array of strings, group anagrams together.
 *
 * Example:
 *
 * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Output:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * Note:
 *
 * All inputs will be in lowercase.
 * The order of your output does not matter.
 */

public class GroupAnagrams {
    private static final Random random = new Random();
    public static void main(String[] args) {
        Random random = new Random();
        int n = 10;
        String[] strings = getRandomPermutedStringArray(n, random.nextInt(n));
        System.out.print(Arrays.deepToString(strings));
        System.out.println();

        // first method
        Objects.requireNonNull(solve(strings))
                .forEach(System.out::println);

        // first method
        Objects.requireNonNull(solve2(strings))
                .forEach(System.out::println);
    }

    // for input output
    private static String[] getRandomPermutedStringArray(int n, int stringLength){
        String[] strings = new String[n];
        for (int i = 0; i < strings.length;) {
            strings[i] = generateRandomLowerCaseString(stringLength);
            boolean bool = random.nextBoolean();
            boolean tobreak = false;
            if(bool){
                List<String> list = new ArrayList<>();
                permute1(0, strings[i].toCharArray(), list);
                for(String string : list){
                    if(i == strings.length){
                        tobreak = true;
                        break;
                    }
                    strings[i++] = string;
                }
            }
            else
                i++;
            if(tobreak) break;
        }
        return strings;
    }

    // for input output
    private static String generateRandomLowerCaseString(int length){
        char[] arr = new char[length];
        int a = 97;
        for(int i = 0; i < arr.length; i++)
            arr[i] = (char)(a + random.nextInt(26));
        return new String(arr);
    }

    // for input output
    private static void permute1(int i, char[] a, List<String> list) {
        if(i == a.length - 1) {
            list.add(new String(a));
        }
        else {
            for(int j = i; j < a.length; j++) {
                char[] arr = Arrays.copyOf(a, a.length);
                arr[j] = a[i];
                arr[i] = a[j];
                permute1(i + 1, arr, list);
            }
        }
    }

    // This is the main logic for grouping anagrams together
    private static List<List<String>> solve(String[] strings) {
        long startime = System.currentTimeMillis();
        List<List<String>> finalList;
        Map<String, List<String>> map = new HashMap<>();
        for(String string : strings){
            char[] crr = string.toCharArray();
            Arrays.sort(crr);
            String newString = new String(crr);
            map.computeIfAbsent(newString, key -> new ArrayList<>()).add(string);
        }
        finalList = new ArrayList<>(map.values());
        System.err.println("in solve1 time is " + (System.currentTimeMillis() - startime));
        return finalList;
    }

    // This is the second logic for grouping anagrams together
    private static List<List<String>> solve2(String[] strings){
        long startime = System.currentTimeMillis();
        List<List<String>> finalList;
        var map = new HashMap<String, List<String>>();
        for(String string : strings){
            int[] p = new int[26];
            for(char c : string.toCharArray()) {
                p[c - 'a']++;
            }
            String ans = Arrays.stream(p).mapToObj(String::valueOf).collect(Collectors.joining("#"));
            map.computeIfAbsent(ans, k -> new ArrayList<>()).add(string);
        }
        finalList = new ArrayList<>(map.values());
        System.err.println("in solve2 time is " + (System.currentTimeMillis() - startime));
        return finalList;
    }
}
