package dataStructures.Tree.BinarySearchTree.Problems;

import dataStructures.Tree.BinarySearchTree.BinarySearchTreeImpl.BinarySearchTree;
import static dataStructures.Tree.BinarySearchTree.BinarySearchTreeImpl.BinarySearchTree.node;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Given a Bst and two separate pointers, find the least common ancestor of the two nodes.
 * Approach
 *  -   check whether if both of them lie on the right subtree
 *  -   or check if they lie on the left subTree
 *  -   otherwise
 *      -   both of them are the child of the parent node, then return the parent node
 *      -   otherwise check if both of them lie in the subtree
 *          -   if they lie then return the parent node
 *          -   otherwise return null
 */

public class ShortestPathBetweenTwoNodes {
    static class Pair<U, T> {
        U first;
        T second;
        Pair(U first, T second) {
            this.first = first;
            this.second = second;
        }
        public static <U, T> Pair<U, T> of(U first, T second) {
            return new Pair<>(first, second);
        }
    }
    public static void main(String[] args) {
        var bst = new BinarySearchTree<Integer>();
        var random = new Random();
        IntStream.rangeClosed(0, 10).forEach(x -> bst.insertInBst(random.nextInt(10)));
        System.out.println(bst.levelOrder());
        var set = new HashSet<Integer>();
        var set2 = new HashSet<Integer>();
        IntStream.rangeClosed(0, 10).forEach(x -> {
            Integer first = 0, second = 1;
            while(set.contains(first)) {
                first = random.nextInt(10);
            }
            set.add(first);
            while(set2.contains(second)) {
               second = random.nextInt(10);
            }
            set2.add(second);
            System.out.println("first = " + first + " second = " + second + " lca = " + getLCA(bst.getRoot(), first, second));
        });
//        doSpecific();
    }

    private static node<Integer> getLCA(node<Integer> root, Integer a, Integer b) {
        if(root == null) {
            return null;
        }
        var temp = root;
        var bothExists = true;
        while(temp != null) {
            int firstCompare = temp.getData().compareTo(a);
            int secondCompare = temp.getData().compareTo(b);
            if(firstCompare < 0 && secondCompare < 0) {
                temp = temp.getRight();
            }
            else if(firstCompare > 0 && secondCompare > 0) {
                temp = temp.getLeft();
            }
            else{
                bothExists = bothExists(temp, a, b);
                break;
            }
        }
        return  (bothExists)? temp : null;
    }

    private static boolean bothExists(node<Integer> root, Integer a, Integer b) {
        boolean first = false;
        boolean second = false;

        var queue = new LinkedList<node<Integer>>();
        queue.add(root);
        while(!queue.isEmpty()) {
            var curr = queue.poll();
            if(curr.getData().compareTo(a) == 0) {
                first = true;
            }
            if(curr.getData().compareTo(b) == 0) {
                second = true;
            }
            if(first && second) {
                break;
            }
            if(curr.getLeft() != null) {
                queue.add(curr.getLeft());
            }
            if(curr.getRight() != null) {
                queue.add(curr.getRight());
            }
        }
        return first && second;
    }

    private static void doSpecific() {
        var bst = new BinarySearchTree<Integer>();
        /*
        [(3),
        (19),
        (13, 19),
        (4, 14),
        (7, 13, 18),
        (6, 8)]
         */
        bst.insertInBst(3);
        bst.insertInBst(19);
        bst.insertInBst(13);
        bst.insertInBst(19);
        bst.insertInBst(4);
        bst.insertInBst(14);
        bst.insertInBst(7);
        bst.insertInBst(13);
        bst.insertInBst(18);
        bst.insertInBst(6);
        bst.insertInBst(8);
        System.out.println(bst.levelOrder());

        var list = Arrays.asList(Pair.of(3, 3), Pair.of(13, 19), Pair.of(4, 14), Pair.of(13, 22));
        for(var pair : list){
            Integer first = pair.first;
            Integer second = pair.second;
            System.out.println("first = " + first + " second = " + second + " lca = " + getLCA(bst.getRoot(), first, second));
        }
    }

}
