package DataStructures.Tree.BinaryTree.Problems;

import DataStructures.Tree.BinaryTree.TreeImpl.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.stream.Collectors;

import static DataStructures.Tree.BinaryTree.TreeImpl.BinaryTree.node;

/**
 * Print the level order traversal in reverse order
 * Approach:
 *  -   Traverse level order and store the current node in the stack
 *  -   After traversal now print the list.
 */
public class ReverseLevelOrder {
    public static void main(String[] args) {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        Random random = new Random();
        for(int i = 0; i < random.nextInt(20); i++)
            binaryTree.insertInBinaryTreeLevelOrder(random.nextInt(100));
        String answer = printReverseLevelOrder(binaryTree.getRoot());
        System.out.println(binaryTree.levelOrder());
        System.out.println(answer);
    }

    private static <T> String printReverseLevelOrder(node<T> node) {
        if(node == null) return "";
        LinkedList<String> nodeLevelList = new LinkedList<>();
        LinkedList<LinkedList<String>> finalList = new LinkedList<>();
        Queue<node<T>> queue = new LinkedList<>();
        queue.offer(node);
        queue.offer(null);
        while(!queue.isEmpty()){
            node<T> curr = queue.poll();
            if(curr != null){
                nodeLevelList.addFirst(curr.getData().toString());
                if(curr.getLeft() != null)
                    queue.offer(curr.getLeft());
                if(curr.getRight() != null)
                    queue.offer(curr.getRight());
            }
            else{
                LinkedList<String> newList = new LinkedList<>(nodeLevelList);
                finalList.addFirst(newList);

                // clearing the list
                nodeLevelList.clear();

                if(!queue.isEmpty())
                    queue.offer(null);
            }
        }
        return "[" + finalList.stream()
                .map(list -> "(" + String.join(", ", list) + ")")
                .collect(Collectors.joining(", ")) + "]";
    }
}
