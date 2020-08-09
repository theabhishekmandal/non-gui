package data_structures.tree.binary_search_tree.problems;


import data_structures.tree.binary_search_tree.binary_search_tree_impl.BinarySearchTree;

import java.util.Arrays;

/**
 * Deleting a node from BST, see the approach in BinarySearchTree.java
 */
public class DeleteFromBst {
    public static void main(String[] args) {
        var bst = new BinarySearchTree<Integer>();
        var arr = new int[]{44, 17, 88, 32, 65, 97, 28, 54, 82, 29, 76, 80, 78};
        Arrays.stream(arr).forEach(bst::insertInBst);

        var arr2 = new int[]{44, 17, 88, 32, 65, 97, 28, 54, 82, 29, 76, 80, 78};
        var bst2 = new BinarySearchTree<Integer>();
        Arrays.stream(arr2).forEach(bst2::insertInBst);

        for(var i : arr) {
            bst.deleteNodeFromTreeRecur(i);
            String first = bst.levelOrder();

            bst2.deleteNodeFromTree(i);
            String first2 = bst2.levelOrder();
            boolean ans = first.equals(first2);
            if(!ans) {
                System.out.println(first + "\n\n" + first2);
                return;
            }
            System.out.println(true);
        }

        bst.deleteTree();
        Arrays.stream(arr).sorted().forEach(bst::insertInBst);
        System.out.println(bst.levelOrder());
        bst.deleteNodeFromTreeRecur(17);
        System.out.println();
        bst.insertInBst(1);
        System.out.println(bst.levelOrder());

//        arr = new int[]{44, 17, 88, 32, 65, 97, 28, 54, 82, 29, 87, 84, 83};
    }
}
