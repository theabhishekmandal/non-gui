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
public class _7EasyCheckSameLevelLeafNodes {
    public static void main(String[] args) {

        List<int[]> list = Arrays.asList(
                new int[]{3, 1, 2},
                new int[]{10, 20, 30, 10, 10}
        );

        var answer = new StringJoiner("\n");
        for (var arr : list) {
            var binaryTree = new BinaryTree<Integer>();
            for (int i : arr) {
                binaryTree.insertInBinaryTreeLevelOrder(i);
            }
            answer.add(binaryTree.levelOrderPretty())
                    .add(String.valueOf(checkSameLevelLeafNodes(binaryTree)));
        }
        System.out.print(answer);
    }

    private static boolean checkSameLevelLeafNodes(BinaryTree<Integer> binaryTree) {
        if (binaryTree == null || binaryTree.getRoot() == null) {
            return false;
        }
        var nullNode = Node.of((Integer)null);
        Deque<Node<Integer>> queue = new ArrayDeque<>();
        queue.add(nullNode);
        queue.add(binaryTree.getRoot());

        var level = 0;
        var allLeavesAtSameLevel = true;
        var leafLevel = -1;
        while (!queue.isEmpty()) {
           var curr = queue.poll();

           if (curr == nullNode) {
               // counting the level
               level++;

               if (!queue.isEmpty()) {
                   queue.add(nullNode);
               }
           }
           else {
               if (curr.getLeft() != null) {
                   queue.add(curr.getLeft());
               }

               if (curr.getRight() != null) {
                   queue.add(curr.getRight());
               }


               if (curr.getLeft() == null && curr.getRight() == null) {

                   // first time leaf node seen.
                   if (leafLevel == -1) {
                       leafLevel = level;
                   }
                   // next time leaf node seen and not at same level break the journey.
                   else if (leafLevel != level) {
                       allLeavesAtSameLevel = false;
                       break;
                   }
               }
           }
        }
        return allLeavesAtSameLevel;
    }
}
