package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Given two binary trees, find that they are isomorphic or not. Two trees are isomorphic if one of them can be obtained
 * from the other with the given series of flips, i.e swapping the left and right children of a number of nodes.
 *
 * - These two are isomorphic as first flip 2, 3 from the second tree and flip 7, 8 from the second tree as well to make it isomorphic
 *    1
 *   2 3
 *  4 5 6 *
 * * * 7 8 * *
 *
 *    1
 *   3 2
 *  * 6 4 5
 * * * * * 8 7
 *
 * - These two are not isomorphic because, after flipping 2, 3 from second tree, then flipping 4 and 5 and at last,
 * 7, 8 are pointing to 5 in first tree and 8,7 are pointing to 4 in second tree, so they are not equal.
 *    1
 *   2 3
 *  4 5 6 *
 * * * 7 8 * *
 *
 *    1
 *   3 2
 *  * 6 5 4
 * * * * * 8 7
 *
 * Approach:
 * -    First we will check for null i.e if both of first and second node are null then they are equal, so return true
 * -    Second, if one of them is null and the other one is non null, then they cannot be made isomorphic, so return false
 * -    Third, if value of both of them are not equal, then also they are not isomorphic, so return false.
 * -    Last, in the recursion condition we have to check both conditions
 *          -   if they are flipped, then check left node with right and vice versa.
 *          -   if they are not flipped, then check left node with left and similarly with right.
 */
public class CheckIfIsomorphic {
    public static void main(String[] args) {
        first();
    }

    private static void first() {
        final var root = Node.of(1);
        root.setLeft(Node.of(2));
        root.setRight(Node.of(3));

        root.getLeft().setLeft(Node.of(4));
        root.getLeft().setRight(Node.of(5));

        root.getRight().setLeft(Node.of(6));

        root.getLeft().getRight().setLeft(Node.of(7));
        root.getLeft().getRight().setRight(Node.of(8));

        final var root1 = Node.of(1);
        root1.setLeft(Node.of(3));
        root1.setRight(Node.of(2));

        root1.getLeft().setRight(Node.of(6));

        // flip 4, 5 to make it return false
        root1.getRight().setLeft(Node.of(4));
        root1.getRight().setRight(Node.of(5));


        root1.getRight().getRight().setLeft(Node.of(8));
        root1.getRight().getRight().setRight(Node.of(7));

        final var binaryTree = new BinaryTree<>(root);
        final var binaryTree1 = new BinaryTree<>(root1);

        System.out.println(binaryTree.levelOrderPretty());
        System.out.println(binaryTree1.levelOrderPretty());

        System.out.println(isIsoMorphic(root, root1));
    }

    private static boolean isIsoMorphic(Node<Integer> first, Node<Integer> second) {
        return getValue(first, second);
    }

    private static boolean getValue(Node<Integer> first, Node<Integer> second) {
        if (first == null && second == null) {
            return true;
        }
        if (first == null || second == null) {
            return false;
        }
        if (!first.getData().equals(second.getData())) {
            return false;
        }

        return (getValue(first.getLeft(), second.getLeft()) && getValue(first.getRight(), second.getRight())) ||
                (getValue(first.getLeft(), second.getRight()) && getValue(first.getRight(), second.getLeft()));
    }
}
