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
 *
 * Approach2
 *  - This approach is based on finding the first level in which leaf is found.
 *  - If another level of leaf is found which is different than previous leaf level, then it means there are leaves
 *    at different level.
 *
 * Approach3
 *  - In this approach we store all the levels in a set which leaf nodes are found, and if there are leaves at different
 *    level, then size of set will be greater than 1.
 */
public class CheckSameLevelLeafNodes {
    public static void main(String[] args) {

        List<int[]> list = Arrays.asList(
                new int[]{3, 1, 2},
                new int[]{10, 20, 30, 10, 10}
        );

        var answer = new StringJoiner("\n");
        for (var arr : list) {
            BinaryTree<Integer> binaryTree = new BinaryTree<>();
            for (int i : arr) {
                binaryTree.insertInBinaryTreeLevelOrder(i);
            }
            answer.add(binaryTree.levelOrderPretty())
                    .add(String.valueOf(checkSameLevelLeafNodes(binaryTree)))
                    .add(String.valueOf(checkSameLevelLeafNodes2(binaryTree)))
                    .add(String.valueOf(checkSameLevelLeafNodes3(binaryTree)));
        }
        System.out.print(answer);
    }

    private static boolean checkSameLevelLeafNodes(BinaryTree<Integer> binaryTree) {
        if (binaryTree == null || binaryTree.getRoot() == null) {
            return false;
        }
        Node<Integer> nullNode = Node.of(null);
        Deque<Node<Integer>> queue = new ArrayDeque<>();
        queue.add(binaryTree.getRoot());
        queue.add(nullNode);

        var allLeavesAtSameLevel = false;
        var leafFoundAtLevel = false;

        while (!queue.isEmpty()) {
            var curr = queue.poll();
            if (curr != nullNode) {
                var isLeftNotNull = curr.getLeft() != null;
                var isRightNotNull = curr.getRight() != null;
                if (isLeftNotNull) {
                    queue.add(curr.getLeft());
                }
                if (isRightNotNull) {
                    queue.add(curr.getRight());
                }
                if (!isLeftNotNull && !isRightNotNull) {
                    leafFoundAtLevel = true;
                }
            } else {
                if (leafFoundAtLevel) {
                    if (!allLeavesAtSameLevel) {
                        allLeavesAtSameLevel = true;
                    } else {
                        queue.clear();
                        allLeavesAtSameLevel = false;
                        break;
                    }
                    leafFoundAtLevel = false;
                }
                if (!queue.isEmpty()) {
                    queue.add(nullNode);
                }
            }
        }
        return allLeavesAtSameLevel;
    }

    private static boolean checkSameLevelLeafNodes2(BinaryTree<Integer> binaryTree) {
        if (binaryTree == null || binaryTree.getRoot() == null) {
            return false;
        }
        Node<Integer> nullNode = Node.of(null);
        Deque<Node<Integer>> queue = new ArrayDeque<>();
        queue.add(binaryTree.getRoot());
        queue.add(nullNode);

        var isAllLeavesAtSameLevel = true;
        var level = 0;
        var leafLevel = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            var curr = queue.poll();

            // check if leafLevel is set or not, if set check whether it is on same level or not
            if (leafLevel != Integer.MIN_VALUE && leafLevel != level && !queue.isEmpty()) {
                queue.clear();
                isAllLeavesAtSameLevel = false;
                break;
            }
            if (curr != nullNode) {
                var isLeftNotNull = curr.getLeft() != null;
                var isRightNotNull = curr.getRight() != null;
                if (isLeftNotNull) {
                    queue.add(curr.getLeft());
                }
                if (isRightNotNull) {
                    queue.add(curr.getRight());
                }
                if (!isLeftNotNull && !isRightNotNull) {
                    leafLevel = level;
                }
            } else {
                level++;
                if (!queue.isEmpty()) {
                    queue.add(nullNode);
                }
            }
        }
        return isAllLeavesAtSameLevel;
    }

    private static boolean checkSameLevelLeafNodes3(BinaryTree<Integer> binaryTree) {
        if (binaryTree == null || binaryTree.getRoot() == null) {
            return false;
        }
        var nullNode = Node.of((Integer)null);
        Deque<Node<Integer>> queue = new ArrayDeque<>();
        queue.add(binaryTree.getRoot());
        queue.add(nullNode);

        var level = 0;
        Set<Integer> leafLevels = new HashSet<>();
        while (!queue.isEmpty()) {
            var curr = queue.poll();
            if (curr != nullNode) {
                var isLeftNotNull = curr.getLeft() != null;
                var isRightNotNull = curr.getRight() != null;
                if (isLeftNotNull) {
                    queue.add(curr.getLeft());
                }
                if (isRightNotNull) {
                    queue.add(curr.getRight());
                }
                if (!isLeftNotNull && !isRightNotNull) {
                    leafLevels.add(level);
                    if (leafLevels.size() > 1) {
                        queue.clear();
                        break;
                    }
                }
            } else {
                level++;
                if (!queue.isEmpty()) {
                    queue.add(nullNode);
                }
            }
        }
        return leafLevels.size() == 1;
    }
}
