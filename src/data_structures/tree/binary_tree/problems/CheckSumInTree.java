package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.*;
import java.util.stream.IntStream;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Given an algorithm for checking the existence of path with given sum. That means, given a sum,
 * check whether there exists a path from root to any of the nodes.
 *
 * Approach,
 *  -   Iterative
 *      -   Use any traversal and while pushing the node in the stack or queue, just add (sum - current node value) along with the
 *          node value.
 *      -   if leaf node is reach check the added (sum - current node value) is 0, if it is zero then return true otherwise false
 *  -   Recursion
 *      -   In recursion call the root without subtracting the value and then while calling left or right subtree
 *          , then subtract the value
 *      -   when the left and right child are null then add the current node and then add it to the static list.
 *
 */

class Pair<A, B> {
    public final A fst;
    public final B snd;

    public Pair(A fst, B snd) {
        this.fst = fst;
        this.snd = snd;
    }

    public String toString() {
        return "Pair[" + this.fst + "," + this.snd + "]";
    }

    @SuppressWarnings("unchecked")
    public boolean equals(Object other) {
        return other instanceof Pair &&
                Objects.equals(this.fst, ((Pair<A, B>)other).fst) &&
                Objects.equals(this.snd, ((Pair<A, B>)other).snd);
    }

    public int hashCode() {
        if (this.fst == null) {
            return this.snd == null ? 0 : this.snd.hashCode() + 1;
        } else {
            return this.snd == null ? this.fst.hashCode() + 2 : this.fst.hashCode() * 17 + this.snd.hashCode();
        }
    }

    public static <A, B> Pair<A, B> of(A a, B b) {
        return new Pair<>(a, b);
    }
}
public class CheckSumInTree {
    public static void main(String[] args) {
        Random random = new Random();
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        IntStream.range(0, random.nextInt(20)).forEach(binaryTree::insertInBinaryTreeLevelOrder);
        int number = random.nextInt(20);
        System.out.println("Number to be found " + number + "\n" + binaryTree.levelOrder());

        // Using Iteration
        boolean isSumExists = checkSumInTree(binaryTree.getRoot(), number);
        if(isSumExists){
            System.out.println(finalList);
            finalList.clear();
        }
        else{
            System.out.println("no path exists");
        }


        // Using Recursion
        checkSumInTreeRecursion(binaryTree.getRoot(), number, "[");
        if(finalListRecursion.isEmpty()){
            System.out.println("no path exists");
        }
        else{
            System.out.println(finalListRecursion);
            finalListRecursion.clear();
        }
    }

    private static final List<String> finalList = new ArrayList<>();

    private static boolean checkSumInTree(Node<Integer> root, int sum) {
        if(root == null) return false;

        Deque<Pair<Node<Integer>, Pair<Integer, String>>> stack = new LinkedList<>();
        // adding the node, sum - current node value and current node as string
        stack.push(Pair.of(root, Pair.of(sum - root.getData(), "[ " + root.getData().toString())));

        boolean found = false;
        while(!stack.isEmpty()){
           Pair<Node<Integer>, Pair<Integer, String>> pair = stack.pop();
           Node<Integer> curr = pair.fst;
           Pair<Integer, String> innerPair = pair.snd;

           if(curr.getRight() == null && curr.getLeft() == null && innerPair.fst == 0){
               found = true;
               finalList.add(innerPair.snd + " ]");
           }

           if(curr.getRight() != null){
              stack.push(Pair.of(curr.getRight(), Pair.of(innerPair.fst - curr.getRight().getData(),
                      innerPair.snd + ", " + curr.getRight().getData().toString())));
           }

           if(curr.getLeft() != null){
               stack.push(Pair.of(curr.getLeft(), Pair.of(innerPair.fst - curr.getLeft().getData(),
                       innerPair.snd + ", " + curr.getLeft().getData().toString())));
           }
        }
        return found;
    }

    private static final List<String> finalListRecursion = new ArrayList<>();
    private static void checkSumInTreeRecursion(Node<Integer> node, int sum, String ans){
        if(node == null){
            return;
        }

        Integer data = node.getData();
        if(node.getLeft() == null && node.getRight() == null && sum - data == 0){
            finalListRecursion.add(ans + ", " + node.getData().toString() + " ]");
            return;
        }

        // checking for first time
        String temp = ans;
        if(ans.length() == 1) {
            temp += " ";
        }
        else{
           temp += ", ";
        }
        temp += node.getData().toString();

        checkSumInTreeRecursion(node.getLeft(), sum - node.getData(), temp);
        checkSumInTreeRecursion(node.getRight(), sum - node.getData(), temp);
    }
}
