package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;
import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

import java.util.*;
import java.util.stream.IntStream;

public class DiagonalTraversal {
    public static void main(String[] args) {
        Random random = new Random();
        BinaryTree<Integer> tree = new BinaryTree<>();
        IntStream.range(0, 10).forEach(tree::insertInBinaryTreeLevelOrder);
        System.out.println(tree.levelOrder());
        List<Integer> arr = getDiagonal(tree.getRoot());
        System.out.println(arr);
    }

    private static <T> ArrayList<T> getDiagonal(Node<T> root){
        List<List<T>> arr = new ArrayList<>();
        Deque<Node<T>> stack = new LinkedList<>();
        Node<T> curr = root;
        int counter = 1;
        while(!stack.isEmpty() || curr != null){
            if(curr != null){
                stack.push(curr);
                if(arr.size() <= counter){
                    arr.add(new ArrayList<>());
                }
                arr.get(counter - 1).add(curr.getData());
                counter++;
                curr = curr.getLeft();
            }
            else{
                Node<T> temp = stack.pop();
                counter--;
                curr = temp.getRight();
            }
        }
        ArrayList<T> ans = new ArrayList<>();
        arr.forEach(ans::addAll);
        return ans;
    }
}
