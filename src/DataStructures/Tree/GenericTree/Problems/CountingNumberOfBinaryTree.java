package DataStructures.Tree.GenericTree.Problems;

import java.util.Random;

public class CountingNumberOfBinaryTree {
    public static void main(String[] args) {
        Random random = new Random();
        int n = random.nextInt(10);
        System.out.println(getNumberOfBinaryTrees(random.nextInt(10)));
        System.out.println(getNumberOfBinaryTreesNew(n));
    }

    private static long getNumberOfBinaryTreesNew(int v) {
       long n = (long) 2 * v;
       long k = v;
       return 1L;
    }

    private static long getNumberOfBinaryTrees(int n){
        long[] arr = new long[n + 1];
        arr[0] = 1;
        arr[1] = 1;
        for(int i = 2; i <= n; i++){
            for(int j = 0; j < i; j++){
                long first = arr[j];
                long second = arr[i - j - 1];
                arr[i] += first * second;
            }
        }
        return arr[n];
    }
}
