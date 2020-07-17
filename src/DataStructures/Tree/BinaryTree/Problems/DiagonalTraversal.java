package DataStructures.Tree.BinaryTree.Problems;

import DataStructures.Tree.BinaryTree.BinaryTreeImpl.BinaryTree;
import static DataStructures.Tree.BinaryTree.BinaryTreeImpl.BinaryTree.node;

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

    private static <T> ArrayList<T> getDiagonal(node<T> root){
        List<List<T>> arr = new ArrayList<>();
        Deque<node<T>> stack = new LinkedList<>();
        node<T> curr = root;
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
                node<T> temp = stack.pop();
                counter--;
                curr = temp.getRight();
            }
        }
        ArrayList<T> ans = new ArrayList<>();
        arr.forEach(ans::addAll);
        return ans;
    }
}
