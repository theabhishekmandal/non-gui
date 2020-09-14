package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Delete a given node from the tree.
 *
 * Approach 1:
 *      -   To delete a node from the tree, first find that node and the last node using level order traversal
 *      -   Also store the nodes encountered while traversing into a list
 *      -   then reverse traverse this list to find the parent and make the linkage with parent to the lastNode as null
 *
 * Approach 2:
 *      -   In this find the node that is to be deleted
 *      -   We also have a map which contains the node as a key and it's parent as a value, it is added while inserting
 *          value to a tree
 *      -   When the node to be deleted is replaced with lastNode value, then lastNode can be deleted, by getting it's
 *          parent from the map and then changing the linkage between lastNode and the parent
 */
public class DeleteNodeFromTree {
    public static void main(String[] args) {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        var binaryTree2 = new BinaryTree<Integer>();

        List<Integer> integerList = IntStream.range(0, 10000).boxed().collect(Collectors.toList());

        integerList.forEach(binaryTree::insertInBinaryTreeLevelOrder);
        integerList.forEach(binaryTree2::insertInBinaryTreeLevelOrder);

        System.out.println("before deletion\n" + binaryTree.levelOrder() + "\n\n" + binaryTree2.levelOrder() + "\n");
        StringBuilder br = new StringBuilder();

        long startTime;
        long time;
        long avgTimeFirst = 0L;
        long avgTimeSecond = 0L;
        for(int i = integerList.size() - 1; i >= 0; i--) {
            var val = integerList.get(i);

            startTime = System.nanoTime();
            boolean isDeleted = deleteNodeFromTree(binaryTree, val);
            time = System.nanoTime() - startTime;
            avgTimeFirst += time;
            br.append("deleted value = ").append(val).append("\ndeleted from first = ").append(isDeleted)
                    .append("\n").append("time taken = ").append(time).append("\n");


            startTime = System.nanoTime();
            boolean isDeleted2 = binaryTree2.deleteNode(val);
            time = System.nanoTime() - startTime;
            avgTimeSecond += time;
            br.append("deleted value = ").append(val).append("\ndeleted from second = ").append(isDeleted2)
                    .append("\n").append("time taken = ").append(time).append("\n");

            br.append("\n\n");
        }
        System.out.println(br);
        System.out.println("avg time first = " + (double)avgTimeFirst / integerList.size() + "\n" +
                            "avg time second = " + (double)avgTimeSecond / integerList.size());
    }

    private static <T> boolean deleteNodeFromTree(BinaryTree<T> tree, T data) {
        if(tree == null) return false;
        if(tree.getRoot() == null) return false;

        var node = tree.getRoot();
        Queue<Node<T>> queue = new ArrayDeque<>();
        List<Node<T>> allNodesInPath = new ArrayList<>();
        queue.add(node);

        Node<T> nodeToBeDeleted = null;
        Node<T> lastNode = null;
        while(!queue.isEmpty()){
            lastNode = queue.poll();
            allNodesInPath.add(lastNode);
            if(lastNode.getData().equals(data)) {
                nodeToBeDeleted = lastNode;
            }
            if(lastNode.getLeft() != null) {
                queue.add(lastNode.getLeft());
            }
            if(lastNode.getRight() != null) {
                queue.add(lastNode.getRight());
            }
        }
        if(nodeToBeDeleted != null) {
            if(nodeToBeDeleted != node) {
                nodeToBeDeleted.setData(lastNode.getData());

                boolean parentFound = false;
                // iterate reverse to find the parent of the last node
                for(int i = allNodesInPath.size() - 1; i >= 0; i--) {
                   var parent = allNodesInPath.get(i);
                   if(parent.getLeft() == lastNode) {
                       parent.setLeft(null);
                       parentFound = true;
                   }
                   else if(parent.getRight() == lastNode) {
                       parent.setRight(null);
                       parentFound = true;
                   }
                   if(parentFound) {
                       break;
                   }
                }
            }
            else {
                tree.setRoot(null);
            }
            return true;
       }
       return false;
    }

    private static <T> void deleteLastNode(Node<T> node, Node<T> lastNode) {
        Queue<Node<T>> queue = new ArrayDeque<>();
        queue.add(node);
        while(!queue.isEmpty()){
            Node<T> curr = queue.poll();
            // set to null if parent's left or right child
            if(curr.getLeft() == lastNode) {
                curr.setLeft(null);
            }
            else if(curr.getRight() == lastNode) {
                curr.setRight(null);
            }
            else{
                if(curr.getLeft() != null) {
                    queue.add(curr.getLeft());
                }
                if(curr.getRight() != null) {
                    queue.add(curr.getRight());
                }
            }
        }
    }
}
