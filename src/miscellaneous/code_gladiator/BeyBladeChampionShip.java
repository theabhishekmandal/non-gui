package miscellaneous.code_gladiator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class BeyBladeChampionShip {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        StringBuilder br = new StringBuilder();
        while(t --> 0){
            int n = s.nextInt();
            Long[] first = new Long[n];
            Long[] second = new Long[n];
            for(int i = 0; i < first.length; i++) first[i] = s.nextLong();
            for(int i = 0; i < second.length; i++) second[i] = s.nextLong();
            Arrays.sort(first, Comparator.reverseOrder());
            Arrays.sort(second, Comparator.reverseOrder());
            int count= 0;
            int counter = 0;
            for (Long aLong : second) {
                if (aLong - first[counter] < 0) {
                    count++;
                    counter++;
                }
            }
            br.append(count).append("\n");
        }
        System.out.print(br);
    }
}
