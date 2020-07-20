package DataStructures.Tree.BinarySearchTree.BinarySearchTreeImpl;

import java.util.Arrays;
import java.util.Random;

public class BinarySearchTreeImpl {
    public static void main(String[] args) {
        Random random = new Random();
        int[] arr = new int[20];
        for(int i = 0; i < arr.length; i++){
            arr[i] = random.nextInt(100);
        }
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        Arrays.stream(arr).forEach(bst::insertInBst);
        String inorderTraversal = bst.inOrder();

        BinarySearchTree<Integer> bst2 = new BinarySearchTree<>(true);
        Arrays.stream(arr).forEach(bst2::insertInBst);
        String inorderTraversalReverse = bst2.inOrder();
        System.out.println(
                inorderTraversal + "\n" + inorderTraversalReverse
        );
    }
}
