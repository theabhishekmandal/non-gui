package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Give an algorithm for find LCA(Least Common Ancestor) of two nodes in a Binary Tree
 * Using recursion in O(n) is the faster way
 * Approach
 *      -   Traverse the tree using post order recursion, we will set v1 or v2 if first or second matches the
 *          root
 *          -   In bottom up approach, if a node is matching one of the first and second values then return it,
 *              as we got our value we don't need to search for other value in the subtree
 *          -   If both left and right node that we got from recursion method is not null then we return the parent node
 *              for both left and right node.
 *          -   If one of the left and right are absent then return the present node
 *  TestCases
 *     -   7   - if both null and value does not match then return null
 *        /  \
 *     null  null
 *     -   7   - if value matches then return value node
 *        /  \
 *     null  null
 *     -   7   - if value does not match and both the nodes are not null then return parent node i.e 7
 *        /  \
 *       8   9
 *
 */
public class LeastCommonAncestor {
    public static void main(String[] args) {
        Random random = new Random();
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        IntStream.range(0, 10000).forEach(binaryTree::insertInBinaryTreeLevelOrder);
        int first = random.nextInt(binaryTree.getSize());
        int second = random.nextInt(binaryTree.getSize());
        System.out.println(binaryTree.levelOrder() + "\nfirst value = " + first  + "\nsecond value = " + second);

        getOutputWithTimeDifference(x -> getLeastCommonAncestor(x.fst, x.snd.fst, x.snd.snd), binaryTree.getRoot(), first, second);
        getOutputWithTimeDifference(x -> getLeastCommonAncestor3(x.fst, x.snd.fst, x.snd.snd), binaryTree.getRoot(), first, second);
    }


    private static boolean v1;
    private static boolean v2;
    private static Integer getLeastCommonAncestor(Node<Integer> root, int first, int second) {
        if(root == null) return null;
        v1 = false; v2 = false;
        Node<Integer> node = getAncestor(root, first, second);
        if(v1 && v2)
            return node.getData();
        return null;
    }

    // use this to find the least common ancestor
    private static Node<Integer> getAncestor(Node<Integer> root, int first, int second){
        if(root == null)
            return null;

        Node<Integer> temp = null;
        // if root matches with first and second then we assign it to temp
        // use of v1 and v2 is that, if both of the values are set then we will return the answer
        // it is used because it assumes that first and second is already present in the tree
        // suppose if first is not present then it will return root, but we should return null instead

        // Also, value of temp will change according to first and second.
        // we don't return after assigning temp = root because, suppose if first=5 and second=6
        // and 5 is parent of 6, then we have to traverse 6 also, we can't return if we found first

        // Also, don't use this statement after recursion call, because suppose if left and right both are same value
        // then both flags won't be set
        if(root.getData() == first){
            v1 = true;
            temp = root;
        }
        if(root.getData() == second){
            v2 = true;
            temp = root;
        }
        Node<Integer> left = getAncestor(root.getLeft(), first, second);
        Node<Integer> right = getAncestor(root.getRight(), first, second);
        if(temp != null){
            return temp;
        }
        if(left != null && right != null){
            return root;
        }
        else{
           return (left != null)? left : right;
        }
    }

    // don't use this
    private static Integer getLeastCommonAncestor3(Node<Integer> root, int first, int second){
        Deque<Node<Integer>> stack = new ArrayDeque<>();
        stack.push(root);
        stack.push(root);

        Deque<Pair<Node<Integer>, Integer>> ans = new LinkedList<>();

        boolean flag = false;
        boolean flag2 = false;
        while (!stack.isEmpty()) {
            var curr = stack.pop();
            if (!stack.isEmpty() && curr == stack.peek()) {
                if (curr.getRight() != null) {
                    stack.push(curr.getRight());
                    stack.push(curr.getRight());
                }
                if (curr.getLeft() != null) {
                    stack.push(curr.getLeft());
                    stack.push(curr.getLeft());
                }
            } else {
                boolean leftNull = curr.getLeft() == null;
                boolean rightNull = curr.getRight() == null;

                boolean isLeft = curr.getData().equals(first);
                boolean isRight = curr.getData().equals(second);
                if (isLeft || isRight) {
                    if (isLeft && isRight) {
                        flag = true;
                        flag2 = true;
                    } else if (isLeft) {
                        flag = true;
                    } else {
                        flag2 = true;
                    }
                    if (!leftNull && !rightNull) {
                       ans.pop();
                       ans.pop();
                    } else if (!leftNull || !rightNull) {
                        ans.pop();
                    }
                    ans.push(Pair.of(curr, curr.getData()));
                } else if (leftNull && rightNull) {
                    ans.push(Pair.of(curr, null));
                } else if (!leftNull && !rightNull) {
                    var right = ans.pop();
                    var left = ans.pop();
                    if (left.snd != null && right.snd != null) {
                        ans.push(Pair.of(curr, curr.getData()));
                    } else {
                        ans.push(Pair.of(curr, (left.snd == null)? right.snd : left.snd));
                    }
                } else {
                    var leftOrRight = ans.pop();
                    ans.push(Pair.of(curr, leftOrRight.snd));
                }
            }
        }
        if (flag && flag2) {
            return ans.pop().snd;
        }
        return null;
    }

    private static void getOutputWithTimeDifference(ToIntFunction<Pair<Node<Integer>, Pair<Integer, Integer>>> func,
                                                    Node<Integer> root, Integer first, Integer second) {
        var start = System.currentTimeMillis();
        Integer output = func.applyAsInt(Pair.of(root, Pair.of(first, second)));
        var stop = System.currentTimeMillis();
        System.out.println("time taken = " + (stop - start) + " output = " + output);
    }
}
