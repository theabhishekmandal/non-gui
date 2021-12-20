package data_structures.queue.problems;

import java.util.*;
import java.util.stream.Collectors;


/**
 * Given an input stream of N characters consisting only of lower case alphabets. The task is to find the first non repeating character,
 * each time a character is inserted to the stream. If no non repeating element is found print -1.
 *
 * Input:
 * The first line of input contains an integer T denoting the no of test cases. Then T test cases follow. Each test case contains an integer
 * N denoting the size of the stream. Then in the next line are x characters which are inserted to the stream.
 *
 * Output:
 * For each test case in a new line print the first non repeating elements separated by spaces present in the stream at every instinct
 * when a character is added to the stream, if no such element is present print -1.
 *
 * Example:
 * Input:
 * 2
 * 4
 * a a b c
 * 3
 * a a c
 *
 * Output:
 * a -1 b b
 * a -1 c
 *
 * Approach:
 *  -   see the below code to find the approach
 */
public class NonRepeatingCharacter {
    public static void main(String[] args) {
        var arr = "g d q r n l l u i e a z v m w n u u f n n x v l o y v g m l i u q a n d l y a v f a u a o s n l n v a c s v p i u m o i a w c q x s w k q w g x y a z n t n a i k a m e y b n u q b c q a g g x a c h r y n q x q q m l f o t p q h v o k i i a m m q m v x j v b s o a i f z y x n j c b e r r n m i x x s y j h o v e n g b p y q r i x q g w d r y g x r x k f h i c a i n h w i l k q m b p e s z d i g z n z x t z q s j w a t y c b m j a w w m n i n e p f d u p l u c l t x m k p v g r r g t u s e u r a g e l t k c a p w p b q r o m q a w i x e z q k v l";
        var temp = arr.chars().mapToObj(char.class::cast).filter(x -> x != ' ').toArray(Character[]::new);
        var tempString = Arrays.stream(temp).map(String::valueOf).collect(Collectors.joining());
        var answer = getNonRepeatingCharacterString(tempString.toCharArray());
        var answer2 = getNonRepeatingCharacterString2(tempString.toCharArray());

        System.out.println(arr.length());
        System.out.println(answer.length());
        System.out.println(arr);
        System.out.println(answer);

        var expected = "g g g g g g g g g g g g g g g g g g g g g g g g g g g d d d d d d d d r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r p p p p p p p p p p p p h h -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 j j j j j j j j j j j j -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1";
        System.out.println(expected);
        System.out.println(expected.equals(answer));
        System.out.println(answer2);
    }

    // this one has more complexity
    private static String getNonRepeatingCharacterString(char[] arr) {
        var br = new StringBuilder();
        Queue<Character> queue = new ArrayDeque<>();
        Map<Character, Integer> map = new HashMap<>();
        var one = "-1";
        for (char c : arr) {
            map.merge(c, 1, Integer::sum);

            if (queue.isEmpty()) {

                // if queue is empty and count of character is greater than one then it means it is already repeated
                // so add -1 otherwise append the character and add it to the queue
                if(map.getOrDefault(c, 0) > 1) {
                    br.append(one);
                }
                else {
                    br.append(c);
                    queue.add(c);
                }
            } else if (queue.peek() != c) {

                // if the character does not match the queue front then first check is the queue character is repeated
                // if repeated then pop it and if queue is null then add -1
                // add the character to the queue if it's count is 1
                popIfRequired(queue, map);
                br.append((queue.peek() == null) ? one : queue.peek());
                if (map.getOrDefault(c, 0) == 1) queue.add(c);
            } else {

                // if the character c matches the queue front then it is a repeated character
                // first pop it and check if queue is Empty then add -1 otherwise check for non repeated character
                // in the queue
                queue.poll();
                if(queue.isEmpty()) {
                    br.append(one);
                } else {
                    popIfRequired(queue, map);
                    br.append((queue.peek() == null) ? one : queue.peek());
                }
            }
            br.append(" ");
        }
        return br.toString().stripTrailing();
    }

    private static void popIfRequired(Queue<Character> queue, Map<Character, Integer> map) {
        while (!queue.isEmpty() && map.getOrDefault(queue.peek(), 0) > 1) {
            queue.poll();
        }
    }

    // this one is easy
    private static String getNonRepeatingCharacterString2(char[] arr) {
        var count = new char[26];
        Queue<Character> queue = new ArrayDeque<>();
        var br = new StringBuilder();
        for (char c : arr) {
            queue.add(c);
            count[c - 'a']++;
            while (!queue.isEmpty()) {
                if (count[queue.peek() - 'a'] > 1)  {
                    queue.poll();
                } else {
                    br.append(queue.peek()).append(" ");
                    break;
                }
            }
            if(queue.isEmpty()) {
                br.append(-1).append(" ");
            }
        }
        return br.toString().stripTrailing();
    }
}
