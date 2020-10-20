
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class test {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
//        System.out.println(atoi(s.next()));

//        System.out.println(findPeakElement(new int[]{3, 2, 1}));
//        System.out.println(Arrays.deepToString(kClosest(new int[][]{{3, 3}, {5, -1}, {-2, 4}}, 2)));
//        System.out.println(customString("kqep", "pekeqaaaaa"));
        String p = "23:05:38";

        String[] input = new String[] {
                "23:05:38",
                "23:05:02",
                "23:04:59",
                "23:04:31",
                "12:36:07",
                "08:59:01",
                "00:00:00",
        };
        System.out.println(Arrays.deepToString(solve(input, p)));
    }

    static String[] solve(String[] p, String r) {
        LocalTime now = LocalTime.now();
        LocalTime[] localTimes = new LocalTime[p.length];
        for(int i = 0; i < localTimes.length; i++) {
           String[] splitString = p[i].split(":");
           localTimes[i] = now.withHour(Integer.parseInt(splitString[0]))
                   .withMinute(Integer.parseInt(splitString[1]))
                   .withSecond(Integer.parseInt(splitString[2]))
                   .withNano(0);
        }
        String[] splitString = r.split(":");
        LocalTime givenTime = now.withHour(Integer.parseInt(splitString[0]))
                .withMinute(Integer.parseInt(splitString[1]))
                .withSecond(Integer.parseInt(splitString[2]))
                .withNano(0);

        String[] answer = new String[p.length];
        for(int i = 0; i < localTimes.length; i++) {
            Duration dur = Duration.between(localTimes[i], givenTime);
           long hourDiff = dur.toHours();
           if(hourDiff == 0) {
              long minutesDiff = dur.toMinutes();
              if(minutesDiff == 0) {
                 long secondsDiff = dur.toSeconds();
                 if(secondsDiff == 0) {
                     answer[i] = "now";
                 }
                 else {
                     answer[i] = secondsDiff + (secondsDiff == 1 ? " second" : " seconds" + " ago");
                 }
              }
              else {
                  answer[i] = minutesDiff + (minutesDiff == 1 ? " minute" : " minutes" + " ago");
              }
           }
           else {
               answer[i] = hourDiff + (hourDiff == 1 ? " hour" : " hours" + " ago");
           }
        }
        return answer;
    }

    static class pair {
        int a;
        int b;
        public pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
        public static pair of(int a , int b) {
           return new pair(a, b);
        }
        public int getFirst() {
            return a;
        }
        public int getSecond() {
            return b;
        }
    }
    public static int[][] kClosest(int[][] points,int k)  {
        int counter = 0;
        pair[] arr = new pair[k];
        Arrays.fill(arr, pair.of(10001, 10001));
            for (int[] point : points) {
            double distance = getDistance(point[0], point[1]);
            for (int j = arr.length - 1; j >= 0; j--) {
                if (distance < getDistance(arr[j].getFirst(), arr[j].getSecond())) {
                    System.arraycopy(arr, 1, arr, 0, j);
                    arr[j] = pair.of(point[0], point[1]);
                    break;
                }
            }
        }
        int[][] temp = new int[k][2];
        for(int i = 0; i < k; i++) {
            temp[i][0] = arr[i].getFirst();
            temp[i][1] = arr[i].getSecond();
        }
        return temp;
    }

    private static double getDistance(int x, int y) {
        return Math.sqrt((double)(x * x) + (y * y));
    }

    public static int findPeakElement(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        int mid = 0;
        while(low <= high) {
            mid = low + ((high - low) >> 1);
            if(mid - 1 >= 0 && nums[mid - 1] > nums[mid]) {
                high = mid - 1;
            }
            else if(mid + 1 < nums.length && nums[mid + 1] > nums[mid]) {
                low = mid + 1;
            }
            else {
                break;
            }
        }
        return mid;
    }

    public static String customString(String s, String t) {
        Map<Character, Long> map = t.chars().mapToObj(x -> (char)x)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        StringBuilder br = new StringBuilder();
        for(char c : s.toCharArray()) {
            if(map.containsKey(c)) {
                long rem = map.get(c);
                while(rem != 0) {
                    br.append(c);
                    rem--;
                }
                map.remove(c);
            }
        }
        for(Map.Entry<Character, Long> entry : map.entrySet()) {
            long rem = entry.getValue();
            while(rem != 0) {
                br.append(entry.getKey());
                rem--;
            }
        }
        return br.toString();
    }


    public boolean repeatedSubstringPattern(String s) {
        for(int i = 1; i < s.length(); i++) {
            if(s.length() % i == 0) {
               String subString = s.substring(0, i).repeat(s.length() / i);
               if(s.equals(subString)) {
                   return true;
               }
            }
        }
        return false;
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