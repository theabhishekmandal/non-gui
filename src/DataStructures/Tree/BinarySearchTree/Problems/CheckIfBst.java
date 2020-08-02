package DataStructures.Tree.BinarySearchTree.Problems;

import DataStructures.Tree.BinarySearchTree.BinarySearchTreeImpl.BinarySearchTree;

import java.util.*;
import java.util.function.Function;
import java.util.stream.IntStream;

import static DataStructures.Tree.BinarySearchTree.BinarySearchTreeImpl.BinarySearchTree.node;

/**
 * Give an algorithm to check whether the given binary tree is BST or not
 * Approach
 *  -   Find the inorder traversal of the bst, this will given all the values in sorted order
 *  -   then check whether each element is smaller than it's next element
 */

public class CheckIfBst {
    public static void main(String[] args) {
        var random = new Random();
        var t = 3;
        while(t-- > 0) {
            var bst = getCorrectOrIncorrectBst();
            System.out.println(bst.inOrder());
            System.out.println(isBst(bst.getRoot()) + "\n" + isBst2(bst.getRoot()) + "\n");
        }
    }

    private static BinarySearchTree<Integer> getCorrectOrIncorrectBst() {
        var random = new Random();
        boolean correct = random.nextBoolean();
        var bst = new BinarySearchTree<Integer>();
        if(correct) {
           IntStream.range(0, 20).forEach(bst::insertInBst);
        }
        else {
           node<Integer> root = new node<>(1);
           root.setRight(new node<>(12));
           root.setLeft(new node<>(22));
           root.getRight().setRight(new node<>(3));
           bst.setRoot(root);
        }
        return bst;
    }

    // this method is slow as it adds the inorder list to an array and then compares it
    private static <T extends Comparable<? super T>> boolean isBst(node<T> root) {
        if(root == null) {
            return true;
        }
        Function<node<T>, List<T>> getInorderArrayFunction = node -> {
            Deque<node<T>> stack = new LinkedList<>();
            List<T> ans = new ArrayList<>();
            node<T> curr = node;
            while(curr != null || !stack.isEmpty()) {
                if(curr != null) {
                    stack.push(curr);
                    curr = curr.getLeft();
                }
                else {
                    curr = stack.pop();
                    ans.add(curr.getData());
                    curr = curr.getRight();
                }
            }
            return ans;
        };

        List<T> ans = getInorderArrayFunction.apply(root);
        for(int i = 0; i < ans.size() - 1; i++) {
            if(ans.get(i).compareTo(ans.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }

    // this method is faster as it uses a prev variable to compare node rather than creating list
    private static <T extends Comparable<? super T>> boolean isBst2(node<T> root) {
        if(root == null) {
            return true;
        }

        var stack = new LinkedList<node<T>>();
        var curr = root;
        node<T> prev = null;
        while(curr != null || !stack.isEmpty()) {
            if(curr != null){
               if(prev != null && prev.getData().compareTo(curr.getData()) > 0) {
                   return false;
               }
               prev = curr;
               stack.push(curr);
               curr = curr.getLeft();
            }
            else {
               curr = stack.pop();
               curr = curr.getRight();
            }
        }
        return true;
    }
}
