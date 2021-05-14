package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;
import java.util.stream.IntStream;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Give an algorithm for printing all the ancestors of a node in a Binary tree.
 */
public class PrintAncestorsOfNode {
    public static void main(String[] args) {
        Random random = new Random();
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        IntStream.range(0, random.nextInt(20)).forEach(binaryTree::insertInBinaryTreeLevelOrder);
        int number = random.nextInt(20);
        String ancestorString = getAncestors(binaryTree.getRoot(), number);
        System.out.println(binaryTree.levelOrder() + "\n\nvalue of node for which ancestor is to be found= " + number);
        System.out.println(ancestorString);
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
    private static <T> String getAncestors(Node<T> root, T valueToBeSearched){
        if(root == null) return "null";
        Deque<pair<Node<T>, String>> stack = new LinkedList<>();
        stack.push(new pair<>(root, "[" + root.getData().toString()));


        while(!stack.isEmpty()){
            pair<Node<T>, String> pair = stack.pop();
            Node<T> curr = pair.getFirst();
            String string = pair.getSecond();

            if(curr.getData().equals(valueToBeSearched)){
                return string + "]";
            }

            if(curr.getRight() != null)
                stack.push(new pair<>(curr.getRight(), string + ", " + curr.getRight().getData().toString()));
            if(curr.getLeft() != null)
                stack.push(new pair<>(curr.getLeft(), string + ", " + curr.getLeft().getData().toString()));
        }
        return "null";
    }
}
