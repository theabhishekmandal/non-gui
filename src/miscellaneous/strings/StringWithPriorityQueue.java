package miscellaneous.strings;

import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given a string s, you have to create a new string. Procedure of creating a new string is
 * - find the letter which has the max count and append to new string.
 * - if there are different letters with same count then, order of priority changes.
 *      -   Numeric
 *      -   UpperCase
 *      -   lowerCase
 *   Example
 * - Approach
 *      -   First count the frequency of each letter
 *      -   Next we could sort them first on the basis of occurrence then on the basis of priority
 *      -   But here lies the problem
 *          Example: "1AbcdAaab1";
 *          After sorting on above criteria "1=2, A=2, a=2, b=2, c=1, d=1"
 *          Now if we pick the letters then final string becomes
 *          1Aabcd1Aab -> incorrect.
 *          1Aab1Aabcd -> correct.
 *
 *          If we use Priority Queue with above criteria
 *          [1=2, A=2, a=2, b=2, c=1, d=1]
 *          finalString = "1"
 *          [A=2, a=2, b=2, 1=1, c=1, d=1]
 *          finalString = "1A"
 *          [a=2, b=2, 1=1, A=1, c=1, d=1]
 *
 *          -   Basically we are appending the letter decreasing the count and checking the priority, again.
 *
 */
public class StringWithPriorityQueue {
    public static void main(String[] args) {
        System.out.println(test("1AbcdAaab1"));
    }
    private static String test(String s) {
       if (s == null || s.isEmpty()) {
           return s;
       }

       Map<Character, Integer> characterIntegerMap = new LinkedHashMap<>();
       for (char c : s.toCharArray()) {
           characterIntegerMap.merge(c, 1, Integer::sum);
       }

       Comparator<Character> comp = (a, b) -> {
           // higher count has more priority.
           int compare = characterIntegerMap.get(b) - characterIntegerMap.get(a);

           if (compare != 0) return compare;

           compare = priority(a) - priority(b);

           if (compare != 0) return compare;

           return a.compareTo(b);
       };


        PriorityQueue<Character> pq = new PriorityQueue<>(comp);
        pq.addAll(characterIntegerMap.keySet());


        StringBuilder br = new StringBuilder();
        while (!pq.isEmpty()) {
            var ch = pq.poll();
            int count = characterIntegerMap.get(ch);
            br.append(ch);

            characterIntegerMap.put(ch, count - 1);
            if (characterIntegerMap.get(ch) > 0) {
                pq.add(ch);
            }
        }

       return br.toString();
    }

    private static int priority(Character b) {
        if (Character.isDigit(b)) {
            return -1;
        }
        if (Character.isUpperCase(b)) {
            return 0;
        }

        return Character.isLowerCase(b)? 1 : Integer.MAX_VALUE;
    }
}
