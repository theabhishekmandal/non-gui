package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.*;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Given a binary tree in which nodes are numbered from 1 to n. We have to find the kth ancestor of the node.
 *
 * Approach:
 *  - Add all the path from root to that node, and then traverse from last to find the kth ancestor
 *
 */
public class KthAncestorOfANode {
    public static void main(String[] args) {
        var random = new Random();
        var binaryTree = new BinaryTree<Integer>();
        random.ints(0, 20)
                .distinct()
                .limit(10)
                .forEach(binaryTree::insertInBinaryTreeLevelOrder);
        var nodeToBeFound = random.nextInt(10);
        var kthAncestor = 1 + random.nextInt(3);

        System.out.println("nodes ancestor to be found = " + nodeToBeFound);
        System.out.println(binaryTree.levelOrderPretty());
        System.out.println(kthAncestor + " ancestor of node = " + nodeToBeFound + " is = " + getKthAncestor(binaryTree, nodeToBeFound, kthAncestor));
    }

    private static Integer getKthAncestor(BinaryTree<Integer> binaryTree, int nodeTobeFound, int kthAncestor) {
        var list = getKthAncestorNode(binaryTree.getRoot(), nodeTobeFound);
        var length = list.size();
        if (length > kthAncestor) {
            return list.get(length - kthAncestor - 1).getData();
        }
        return -1;
    }

    private static List<Node<Integer>> getKthAncestorNode(Node<Integer> root, int first) {
        Deque<Node<Integer>> stack = new ArrayDeque<>();
        List<Node<Integer>> ancestorNodeList = new ArrayList<>();
        stack.push(root);
        stack.push(root);
        var found = false;
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
