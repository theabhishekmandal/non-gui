package Miscellaneous;

import java.util.Scanner;

/**
 *  Tower of Hanoi is based on recursion, the basic principle behind it is as follows:
 *  1. Move n-1 disks from source to auxiliary using the destination
 *  2. Move the remaining disk from source to destination
 *  3. Move n-1 disks from auxiliary to destination using source.
 */

public class TowerOfHanoi {
    private static StringBuilder path;
    private static void towerOfHanoi(int disk, String src, String des, String aux, int i){

        // base condition when only 1 disk is left
        if(disk == 1){
            path.append(i).append(":").append(src).append("-->").append(des).append("\n");
            return;
        }

        // moving the n - 1 disk from source to auxiliary using the destination.
        towerOfHanoi(disk - 1, src, aux, des, i - 1);

        // moving the remaining 1 disk from source to destination.
        path.append(i).append(":").append(src).append("-->").append(des).append("\n");

        // moving the n - 1 disk from auxiliary to destination using source.
        towerOfHanoi(disk - 1, aux, des, src, i - 1);

    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("enter number of disks");
        int n = s.nextInt();
        path = new StringBuilder();
        towerOfHanoi(n, "src", "des", "aux", n);
        System.out.println(path);
    }
}
