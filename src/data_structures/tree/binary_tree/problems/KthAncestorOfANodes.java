package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;
import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

import java.util.*;
import java.util.stream.IntStream;

public class KthAncestorOfANodes {
    public static void main(String[] args) {
        var random = new Random();
        var binaryTree = new BinaryTree<Integer>();
        random.ints(0, 10)
                .limit(10)
                .forEach(binaryTree::insertInBinaryTreeLevelOrder);
        int first = random.nextInt(10);
        int second = random.nextInt(10);
        int kthAncestor = 1 + random.nextInt(3);

        System.out.println("first = " + first + " second = " + second + " kthAncestor = " + kthAncestor);
        System.out.println(binaryTree.levelOrderPretty());
        System.out.println(getKthAncestor(binaryTree, first, second, kthAncestor));
    }

    private static Integer getKthAncestor(BinaryTree<Integer> binaryTree, int first, int second, int kthAncestor) {
        List<Node<Integer>> firstList = getKthAncestorNode(binaryTree.getRoot(), first);
        List<Node<Integer>> secondList = getKthAncestorNode(binaryTree.getRoot(), second);
        System.out.println(firstList + "\n" + secondList);
        int length = Math.min(firstList.size(), secondList.size());
        if (length >= kthAncestor) {
            return firstList.get(length - kthAncestor).getData();
        }
        return -1;
    }

    private static List<Node<Integer>> getKthAncestorNode(Node<Integer> root, int first) {
        Deque<Node<Integer>> stack = new ArrayDeque<>();
        List<Node<Integer>> ancestorNodeList = new ArrayList<>();
        stack.push(root);
        stack.push(root);
        boolean found = false;
        while (!stack.isEmpty()) {
            var curr = stack.pop();
            if (!stack.isEmpty() && curr == stack.peek()) {
                ancestorNodeList.add(curr);
                if (curr.getData() == first) {
                    found = true;
                    break;
                }

                if (curr.getRight() != null) {
                    stack.push(curr.getRight());
                    stack.push(curr.getRight());
                }

                if (curr.getLeft() != null) {
                    stack.push(curr.getLeft());
                    stack.push(curr.getLeft());
                }

            } else {
                ancestorNodeList.remove(ancestorNodeList.size() - 1);
            }
        }
        return (!found)? Collections.emptyList() : ancestorNodeList;
    }

}
