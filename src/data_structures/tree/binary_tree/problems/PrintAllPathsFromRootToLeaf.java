package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;
import utility.Pair;

import java.util.*;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Find all the paths from root to the leaf nodes
 * Approach:
 *  -   Use any traversal other than level order traversal, add the node and it's value in the stack
 *  -   check if the current node is leaf or not, if it is leaf then add to final list
 *
 *  -   For Recursion, add the contents to the list
 */

public class PrintAllPathsFromRootToLeaf {

    public static void main(String[] args) {
        var random = new Random();
        var binaryTree = new BinaryTree<Integer>();

        random.ints(10, 0, random.nextInt(20)).forEach(binaryTree::insertInBinaryTreeLevelOrder);
        System.out.println(binaryTree.levelOrder());

        List<String> listAllPaths = getAllPathFromRootToLeaf(binaryTree);
        System.out.println("All paths from root to leaves\n" + String.join("\n", listAllPaths));

        List<String> listAllPathsUsingRecursion = getAllPathFromRootToLeafRecursion(binaryTree);
        System.out.println("All paths from root to leaves using recursion\n" + String.join("\n", listAllPathsUsingRecursion));

    }

    private static <T> List<String> getAllPathFromRootToLeafRecursion(BinaryTree<T> binaryTree) {
        if (binaryTree == null || binaryTree.getRoot() == null) {
            return Collections.singletonList("[]");
        }
        List<String> list = new ArrayList<>();
        getPath(binaryTree.getRoot(), list, "[" + binaryTree.getRoot().getData().toString());
        return list;
    }

    private static <T> void getPath(Node<T> node, List<String> list, String path) {
        if (node.getRight() == null && node.getLeft() == null) {
            list.add(path + "]");
            return;
        }
        if (node.getLeft() != null) {
            getPath(node.getLeft(), list, path + ", " + node.getLeft().getData().toString());
        }
        if (node.getRight() != null) {
            getPath(node.getRight(), list, path + ", " + node.getRight().getData().toString());
        }
    }

    private static <T> List<String> getAllPathFromRootToLeaf(BinaryTree<T> binaryTree) {
        if (binaryTree == null || binaryTree.getRoot() == null) {
            return Collections.singletonList("[]");
        }
        List<String> list = new ArrayList<>();
        Deque<Pair<Node<T>, String>> stack = new ArrayDeque<>();
        stack.push(new Pair<>(binaryTree.getRoot(), "[" + binaryTree.getRoot().getData().toString()));

        while (!stack.isEmpty()) {
            var pair = stack.pop();
            var curr = pair.getFirst();
            var string = pair.getSecond();

            if (curr.getLeft() == null && curr.getRight() == null) {
                list.add(string + "]");
            }

            if (curr.getRight() != null) {
                stack.push(new Pair<>(curr.getRight(), string + ", " + curr.getRight().getData().toString()));
            }
            if (curr.getLeft() != null) {
                stack.push(new Pair<>(curr.getLeft(), string + ", " + curr.getLeft().getData().toString()));
            }
        }
        return list;
    }
}
