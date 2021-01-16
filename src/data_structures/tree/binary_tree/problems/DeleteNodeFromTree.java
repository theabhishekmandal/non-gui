package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Delete a given node from the tree.
 *
 * Approach:
 *      -   To delete a node from the tree, first find that node and the last node using level order traversal
 *      -   Also store the nodes encountered while traversing into a list
 *      -   then reverse traverse this list to find the parent and make the linkage with parent to the lastNode as null
 */
public class DeleteNodeFromTree {
    public static void main(String[] args) {
        var random = new Random();
        var binaryTree = new BinaryTree<>();
        var binaryTree2 = new BinaryTree<Integer>();

        List<Integer> integerList = IntStream.range(0, 10).boxed().collect(Collectors.toList());

        integerList.forEach(binaryTree::insertInBinaryTreeLevelOrder);
        integerList.forEach(binaryTree2::insertInBinaryTreeLevelOrder);

        System.out.println("binary tree before deletion\n" + binaryTree.levelOrderPretty() + "\n");
        while(binaryTree.getSize() != 0) {
            int num = random.nextInt(11);
            System.out.println(num + (binaryTree.deleteNode(num)? " is " : " is not ") + "successful\n" + binaryTree.levelOrder() + "\n");
        }
    }
}
