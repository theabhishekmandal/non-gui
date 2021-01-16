package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;
import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Find all the paths from root to the leaf nodes
 * Approach:
 *  -   Use any traversal other than level order traversal, add the node and it's value in the stack
 *  -   check if the current node is leaf or not, if it is leaf then add to final list
 *
 *  -   For Recursion, add the contents to the array
 */

public class PrintAllPathsFromRootToLeaf {

    public static void main(String[] args) {
        Random random = new Random();
        BinaryTree<Integer> binaryTree = new BinaryTree<>();

        IntStream.range(0, random.nextInt(20)).forEach(binaryTree::insertInBinaryTreeLevelOrder);
        System.out.println(binaryTree.levelOrder());

        List<String> listAllPaths = getAllPathFromRootToLeaf(binaryTree.getRoot());
        System.out.println(listAllPaths);

        nodeListForRecursion.clear();
        getAllPathFromRootToLeafRecursion(binaryTree.getRoot(), 0, new String[20]);
        System.out.println(nodeListForRecursion);
    }

    static class pair<T, U>{
        private T first;
        private U second;

        public pair(T first, U second) {
            this.first = first;
            this.second = second;
        }

        public T getFirst() {
            return first;
        }

        public void setFirst(T first) {
            this.first = first;
        }

        public U getSecond() {
            return second;
        }

        public void setSecond(U second) {
            this.second = second;
        }
        public static <T, U> pair<T, U> of(T first, U second){
            return new pair<>(first, second);
        }
    }

    private static final List<String> nodeListForRecursion = new ArrayList<>();

    private static <T> void getAllPathFromRootToLeafRecursion(Node<T> node, int number, String[] arr){
        if(node == null)
            return;
        arr[number] = node.getData().toString();
        if(node.getLeft() == null && node.getRight() == null){

            StringBuilder br = new StringBuilder("[");
            boolean flag = true;
            for(int i = 0; i <= number; i++){
                if(flag){
                   flag = false;
                   br.append(arr[i]);
                }
                else{
                   br.append(", ").append(arr[i]);
                }
            }
            br.append("]");
            nodeListForRecursion.add(br.toString());
            return;
        }
        getAllPathFromRootToLeafRecursion(node.getLeft(), number + 1, arr);
        getAllPathFromRootToLeafRecursion(node.getRight(), number + 1, arr);
    }
    private static <T> List<String> getAllPathFromRootToLeaf(Node<T> root){
        if(root == null) return Collections.singletonList("[]");
        List<String> list = new LinkedList<>();
        Deque<pair<Node<T>, String>> stack = new LinkedList<>();
        stack.push(new pair<>(root, "[" + root.getData().toString()));

        while(!stack.isEmpty()){
           pair<Node<T>, String> pair = stack.pop();
           Node<T> curr = pair.getFirst();
           String string = pair.getSecond();

           if(curr.getLeft() == null && curr.getRight() == null){
                list.add(string + "]");
           }

           if(curr.getRight() != null)
               stack.push(new pair<>(curr.getRight(), string + ", " + curr.getRight().getData().toString()));
           if(curr.getLeft() != null)
                stack.push(new pair<>(curr.getLeft(), string + ", " + curr.getLeft().getData().toString()));
        }
        return list;
    }
}
