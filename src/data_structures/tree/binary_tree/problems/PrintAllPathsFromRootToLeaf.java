package data_structures.tree.binary_tree.problems;

import utility.Pair;
import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.*;
import java.util.stream.IntStream;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Find all the paths from root to the leaf nodes
 * Approach:
 *  -   Use any traversal other than level order traversal, add the node and it's value in the stack
 *  -   check if the current node is leaf or not, if it is leaf then add to final list
 *
 *  -   For Recursion, add the contents to the array
 */

public class PrintAllPathsFromRootToLeaf {

    private static final List<String> nodeListForRecursion = new ArrayList<>();

    public static void main(String[] args) {
        var random = new Random();
        var binaryTree = new BinaryTree<Integer>();

        IntStream.range(0, random.nextInt(20)).forEach(binaryTree::insertInBinaryTreeLevelOrder);
        System.out.println(binaryTree.levelOrder());

        List<String> listAllPaths = getAllPathFromRootToLeaf(binaryTree.getRoot());
        System.out.println(listAllPaths);

        nodeListForRecursion.clear();
        getAllPathFromRootToLeafRecursion(binaryTree.getRoot(), 0, new String[20]);
        System.out.println(nodeListForRecursion);
    }

    private static <T> void getAllPathFromRootToLeafRecursion(Node<T> node, int number, String[] arr) {
        if (node == null) {
            return;
        }
        arr[number] = node.getData().toString();
        if (node.getLeft() == null && node.getRight() == null) {

            var br = new StringBuilder("[");
            var flag = true;
            for (var i = 0; i <= number; i++) {
                if (flag) {
                    flag = false;
                    br.append(arr[i]);
                } else {
                    br.append(", ").append(arr[i]);
                }
            }
            br.append("]");
            nodeListForRecursion.add(br.toString());
            return;
        }
        getAllPathFromRootToLeafRecursion(node.getLeft(), number + 1, arr);
        getAllPathFromRootToLeafRecursion(node.getRight(), number + 1, arr);
    }

    private static <T> List<String> getAllPathFromRootToLeaf(Node<T> root) {
        if (root == null) {
            return Collections.singletonList("[]");
        }
        List<String> list = new LinkedList<>();
        Deque<Pair<Node<T>, String>> stack = new LinkedList<>();
        stack.push(new Pair<>(root, "[" + root.getData().toString()));

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
