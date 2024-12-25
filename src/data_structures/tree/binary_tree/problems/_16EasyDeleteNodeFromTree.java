package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Delete a given node from the tree.

 */
public class _16EasyDeleteNodeFromTree {
    public static void main(String[] args) {
        var random = new Random();
        var binaryTree = new BinaryTree<Integer>();
        var binaryTree2 = new BinaryTree<Integer>();

        List<Integer> integerList = IntStream.range(0, 10).boxed().collect(Collectors.toList());

        integerList.forEach(binaryTree::insertInBinaryTreeLevelOrder);
        integerList.forEach(binaryTree2::insertInBinaryTreeLevelOrder);

        System.out.println("binary tree before deletion\n" + binaryTree.levelOrderPretty() + "\n");
        while (binaryTree.getSize() != 0) {
            var num = random.nextInt(11);
            System.out.println(num + (binaryTree.deleteNode(num) ? " is " : " is not ") + "successful\n" + binaryTree.levelOrder() + "\n");
        }
    }
}
