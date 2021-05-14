package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.*;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Given a binary tree check if all the leaves are at same level or not
 * Approach
 *  -   Traverse the tree in level order, if any leaf node is encountered then set true to leafLevelSwitch.
 *  -   When one level of tree is traversed then check if allLeavesAtSameLevel flag is true or not. If it is
 *      already true means that we have seen some leaves at previous level and we are seeing leaves again that means
 *      leaves are on different level so return false;
 */
public class CheckSameLevelLeafNodes {
    public static void main(String[] args) {

        List<int[]> list = Arrays.asList(
                new int[]{3, 1, 2},
                new int[]{10, 20, 30, 10, 10}
        );

        StringBuilder answer = new StringBuilder();
        for (var arr : list) {
            BinaryTree<Integer> binaryTree = new BinaryTree<>();
            for (int i : arr) {
                binaryTree.insertInBinaryTreeLevelOrder(i);
            }
            answer.append(binaryTree.levelOrderPretty())
                    .append(checkSameLevelLeafNodes(binaryTree))
                    .append("\n");
        }
        System.out.print(answer);
    }

    private static boolean checkSameLevelLeafNodes(BinaryTree<Integer> binaryTree) {
        if (Optional.ofNullable(binaryTree).map(BinaryTree::getRoot).isEmpty()) {
            return false;
        }
        Deque<Node<Integer>> queue = new LinkedList<>();
        queue.add(binaryTree.getRoot());
        queue.add(null);

        boolean allLeavesAtSameLevel = false;
        boolean leafLevelSwitch = false;

        while(!queue.isEmpty()) {
            var curr = queue.poll();
            if (curr != null) {
                if (curr.getLeft() != null) {
                    queue.add(curr.getLeft());
                }
                if (curr.getRight() != null) {
                    queue.add(curr.getRight());
                }
                if (curr.getLeft() == null && curr.getRight() == null) {
                    leafLevelSwitch = true;
                }
            } else {
                if (leafLevelSwitch) {
                    if (!allLeavesAtSameLevel) {
                        allLeavesAtSameLevel = true;
                    } else {
                        queue.clear();
                        return false;
                    }
                    leafLevelSwitch = false;
                }
                if (!queue.isEmpty()) {
                    queue.add(null);
                }
            }
        }
        return allLeavesAtSameLevel;
    }
}
