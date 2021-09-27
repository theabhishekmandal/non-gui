package data_structures.tree.binary_search_tree.binary_search_tree_impl;

import java.util.Random;
import java.util.stream.Collectors;

public class BinarySearchTreeImpl {
    public static void main(String[] args) {
        Random random = new Random();
        var bst = new BinarySearchTree<Integer>();
        var intList = random.ints(20, 0, 100)
                .boxed()
                .collect(Collectors.toList());

        intList.forEach(bst::insertInBst);
        String inorderTraversal = bst.inOrder();

        var bst2 = new BinarySearchTree<Integer>(true);
        intList.forEach(bst2::insertInBst);
        String inorderTraversalReverse = bst2.inOrder();
        System.out.println(
                inorderTraversal + "\n" + inorderTraversalReverse
        );

        System.out.println("\n" + bst.levelOrder() + "\n\n" + bst2.levelOrder());

    }
}
