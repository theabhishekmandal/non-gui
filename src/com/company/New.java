package com.company;

import java.util.*;
public class New {


    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        ArrayList<Character> arr=new ArrayList<Character>();
        for(char i='0';i<='9';i++)
            arr.add(i);
        for(char i='A';i<='Z';i++)
            arr.add(i);
        int t = s.nextInt();
        while (t-- > 0) {
            String hel = s.next();
            char a = hel.charAt(0);
            char b = hel.charAt(1);
            int one = arr.indexOf(a);
            int two = arr.indexOf(b);
            int max = Math.max(one, two);
            if (one % 2 == 1 || two % 2 == 1) {
                String check = "";
                for (int i = max+1; i <= 36; i++) {
                    int base = (one * i) + two;
                    if (isprime(base)) {
                            check += i + " ";
                    }
                }
                System.out.println(check.substring(0,check.length()-1));

            } else
                System.out.println("NONE");

        }
    }
    private static boolean isprime(int base) {
        if(base<2)return false;
        if(base<=3)return true;
        if(base%2==0||base%3==0)return false;
        else
        {
            for(int i=5;i*i<=base;i+=6)
            {
                if(base%i==0||base%(i+2)==0)
                    return false;
            }
        }
        return true;
    }
}
