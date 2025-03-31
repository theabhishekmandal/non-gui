package data_structures.tree.binary_search_tree.binary_search_tree_impl;

import java.util.Random;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BinarySearchTreeImpl {
    public static void main(String[] args) {
        final Random random = new Random();
        final var bst = new BinarySearchTree<Integer>();
        final var intList = random.ints(20, 0, 100)
                .boxed()
                .collect(Collectors.toList());

        intList.forEach(bst::insertInBst);

        final var bst2 = new BinarySearchTree<Integer>(true);
        intList.forEach(bst2::insertInBst);

        var joiner = new StringJoiner("\n");

        joiner.add("inorder iterative").add(bst.inOrder())
                .add("inorder recur").add(bst.inOrderRecursive());

        joiner.add("reverse inorder iterative").add(bst2.inOrder())
                .add("reverse inorder recursive").add(bst2.inOrderRecursive());

        joiner.add("preorder iterative").add(bst.preOrder())
                .add("preorder recursive").add(bst.preOrderRecursive());

        joiner.add("postorder iterative").add(bst.postOrder())
                .add("postorder recursive").add(bst.postOrderRecursive());


        System.out.println(joiner);

        System.out.println(bst.levelOrder());

    }
}
