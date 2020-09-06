package miscellaneous;

import java.util.Random;

/**
 * convert string to integer,
 * Condition:
 *  -   remove the whitespaces if present in string
 *  -   if there is a sign then use that sign to convert to positive or negative number
 *  -   if there are non-digit characters after some digit characters then return the Integer value of digit characters
 *  -   if string value exceeds Integer max value or Integer min value then return min or max
 *
 *  Example 1:
 *
 * Input: "42"
 * Output: 42
 * Example 2:
 *
 * Input: "   -42"
 * Output: -42
 * Explanation: The first non-whitespace character is '-', which is the minus sign.
 *              Then take as many numerical digits as possible, which gets 42.
 * Example 3:
 *
 * Input: "4193 with words"
 * Output: 4193
 * Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
 * Example 4:
 *
 * Input: "words and 987"
 * Output: 0
 * Explanation: The first non-whitespace character is 'w', which is not a numerical
 *              digit or a +/- sign. Therefore no valid conversion could be performed.
 * Example 5:
 *
 * Input: "-91283472332"
 * Output: -2147483648
 * Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
 *              Therefore INT_MIN (−231) is returned.
 *
 */

public class StringToInteger {
    private static final Random random = new Random();

    public static void main(String[] args) {
       int testCases = 5 + random.nextInt(5);
       while(testCases-- > 0) {
           String inputNumberString = getNumber();
           System.out.println("input numberString = " + inputNumberString);
           System.out.println("output Number = " + getIntFromString(inputNumberString) + "\n");
       }
    }

    private static int getIntFromString(String number) {
        if(number == null) {
            return 0;
        }
        char[] arr = number.trim().toCharArray();
        if(arr.length == 0) {
            return 0;
        }
        long answer = 0L;
        boolean isNegative = arr[0] == '-';
        boolean plusSign = arr[0] == '+';
        int i = 0;
        if(isNegative || plusSign) {
            i = 1;
        }

        for(; i < arr.length && Character.isDigit(arr[i]); i++) {
            char c = arr[i];
            // answer will be calculate first and then check for bounds
            answer = 10L * answer + c - '0';

            if(isNegative) {
                if(-answer < Integer.MIN_VALUE) {
                    return Integer.MIN_VALUE;
                }
            }
            else if(answer > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
        }
        if(isNegative) {
            answer = -answer;
        }
        return (int)answer;
    }

    private static String getNumber() {
        int num = random.nextInt(3);
        switch (num) {
            case 0: return getGoodNumber();
            case 1: return getNumberWithSpace();
            case 2: return getGibberishNumber();
            default: return getNumber();
        }
    }
    private static String getGibberishNumber() {
        StringBuilder br = new StringBuilder(getGoodNumber());
        boolean addFirst = random.nextBoolean();
        for(int i = 0; i < random.nextInt(5); i++) {
            char c = (char)('a' + random.nextInt(26));
            if(addFirst) {
                br.insert(0, c);
            }
            else {
                br.append(c);
            }
        }
        return br.toString();
    }


    private static String getNumberWithSpace() {
        boolean addFirst = random.nextBoolean();
        StringBuilder br = new StringBuilder(getGoodNumber());
        for(int i = 0; i < random.nextInt(5); i++) {
            if(addFirst) {
                br.insert(0, ' ');
            }
            else {
                br.append(' ');
            }
        }
        return br.toString();
    }

    private static String getGoodNumber() {
        int length = 1 + random.nextInt(10);
        boolean negative = random.nextBoolean();
        boolean positiveSign = false;
        if(!negative) {
           positiveSign = random.nextBoolean();
        }
        StringBuilder br = new StringBuilder();
        for(int i = 0; i < length; i++) {
            br.append((char)('0' + random.nextInt(10)));
        }
        if(negative) {
            br.insert(0, '-');
        }
        else if(positiveSign) {
            br.insert(0, '+');
        }
        return br.toString();
    }
}
