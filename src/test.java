
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class test {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println(atoi(s.next()));
    }
    static int atoi(String str) {
        if(str == null) {
            return 0;
        }
        char[] arr = str.trim().toCharArray();
        if(arr.length == 0) {
            return 0;
        }

        long answer = 0;
        boolean negative = arr[0] == '-';
        int i = 0;
        if(negative || arr[0] == '+') i = 1;
        for(; i < arr.length; i++) {
            char c = arr[i];
            if(!Character.isDigit(c)) {
                break;
            }
            if(negative) {
                if(-answer < Integer.MIN_VALUE) {
                    return Integer.MIN_VALUE;
                }
            }
            else if(answer > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            answer = 10L * answer + (c - '0');
        }
        if(negative) {
            answer = -answer;
        }
        return (int)answer;
    }
}
//"-91283472332" "9223372036854775808"