package data_structures.strings;

/*
https://leetcode.com/problems/minimum-insertions-to-balance-a-parentheses-string/description/
 */
public class _3EasyStringMinInsertions {
    public static void main(String[] args) {
        System.out.println(minInsertions(""));
        System.out.println(minInsertions("("));
        System.out.println(minInsertions(")"));
        System.out.println(minInsertions("()"));
        System.out.println(minInsertions(")("));
        System.out.println(minInsertions("())"));
        System.out.println(minInsertions("(()"));
        System.out.println(minInsertions("))))()))())"));
//        System.out.println(minInsertions("))))()))())"));
//        System.out.println(minInsertions("(((()(()((())))(((()())))()())))(((()(()()((()()))"));
    }

    public static int minInsertions(String s) {
        s = s.replace("))", "]");
        int leftCount = 0;
        int count = 0;
        for (char ch : s.toCharArray()) {
            // count (
            if (ch == '(') {
                leftCount++;
            }
            else if ((ch == ']' || ch == ')') && leftCount > 0) {
                leftCount--;
                count += ch == ')' ? 1 : 0;
            }
            else {
                count += ch == ')' ? 2 : 1;
            }
        }
        return count + leftCount * 2;
    }
}