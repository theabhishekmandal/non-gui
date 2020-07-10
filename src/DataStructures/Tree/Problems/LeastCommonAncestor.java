package DataStructures.Tree.Problems;

import DataStructures.Tree.TreeImpl.BinaryTree;
import static DataStructures.Tree.TreeImpl.BinaryTree.node;

import java.util.*;
import java.util.concurrent.DelayQueue;
import java.util.stream.IntStream;

/**
 * Give an algorithm for find LCA(Least Common Ancestor) of two nodes in a Binary Tree
 * Approach
 *      -   Traverse the tree using post order recursion, we will set v1 or v2 if first or second matches the
 *          root
 *      -   now the result of left and right can be (null, null), (nonnull, null), (null, nonnull) and (nonnull, nonnull)
 *      -   if both are not null then there common part is root then we return root
 *      -   if both of them are null then we return null
 *      -   if one of them is null, then in this case the flags will be used, if at the end of recursion
 *          if these flags are not set then we will return null because there can be a possibility while finding
 *          the common root of both first and second node,
 */
public class LeastCommonAncestor {
    public static void main(String[] args) {
        Random random = new Random();
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        int first = random.nextInt(10);
        int second = random.nextInt(10);
        IntStream.range(0, 10).forEach(binaryTree::insertInBinaryTreeLevelOrder);
        System.out.println(binaryTree.levelOrder() + "\nfirst value = " + first  + "\nsecond value = " + second);
        Integer value = getLeastCommonAncestor(binaryTree.getRoot(), first, second);
        System.out.println(value);
    }

    private static boolean v1, v2;
    private static Integer getLeastCommonAncestor(node<Integer> root, int first, int second) {
        if(root == null) return null;
        v1 = false; v2 = false;
        node<Integer> node = getAncestor(root, first, second);
        if(v1 && v2)
            return node.getData();
        return null;
    }

    private static node<Integer> getAncestor(node<Integer> root, int first, int second){
        if(root == null)
            return null;

        node<Integer> temp = null;
        // if root matches with first and second then we assign it to temp
        // use of v1 and v2 is that, if both of the values are set then we will return the answer
        // it is used because it assumes that first and second is already present in the tree
        // suppose if first is not present then it will return root, but we should return null instead
        // Also, value of temp will change according to first and second.
        // we don't return after assigning temp = root because, suppose if first=5 and second=6
        // and 5 is parent of 6, then we have to traverse 6 also, we can't return if we found first
        if(root.getData() == first){
            v1 = true;
            temp = root;
        }
        if(root.getData() == second){
            v2 = true;
            temp = root;
        }
        node<Integer> left = getAncestor(root.getLeft(), first, second);
        node<Integer> right = getAncestor(root.getRight(), first, second);
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

    private static node<Integer> getAncestorNew(node<Integer> node, int first, int second){

        if(node == null){
            return null;
        }

        boolean firstFound = false;
        boolean secondFound = false;

        Deque<node<Integer>> stack = new LinkedList<>();
        stack.push(node);
        stack.push(node);

        node<Integer> foundNode = null;

        List<node<Integer>> tempList = new ArrayList<>(2);
        while(!stack.isEmpty()){
            node<Integer> curr = stack.pop();
            if(curr != null){
                if(curr.getData() == first){
                    firstFound = true;
                    foundNode = curr;
                }
                else if(curr.getData() == second){
                    secondFound = true;
                    foundNode = curr;
                }
            }
            if(curr != null && !stack.isEmpty() && stack.peek() == curr){
               node<Integer> left = curr.getLeft();
               node<Integer> right = curr.getRight();
               if(right != null){
                   stack.push(right);
               }
               stack.push(right);
               if(left != null){
                   stack.push(left);
               }
               stack.push(left);
            }
            else{
                if(tempList.size() == 2){
                    node<Integer> left = tempList.get(0);
                    node<Integer> right = tempList.get(1);
                    tempList.clear();
                    if(foundNode != null){
                       tempList.add(foundNode);
                    }
                    else if(left != null && right != null){
                       tempList.add(curr);
                    }
                    else{
                       tempList.add((left != null)? left : right);
                    }
                }
                else{
                    tempList.add(curr);
                }
            }
        }
        if(firstFound && secondFound){
            return tempList.get(1);
        }
        return null;
    }

    private static <T> node<T> solve(node<T> root, T first, T second){
        if(root == null){
            return null
    }

    static class pair<T,U>{
        T first;
        U second;
        pair(T first, U second){
            this.first = first;
            this.second = second;
        }
        static <T, U> pair<T,U> of(T first, U second){
            return new pair<>(first, second);
        }

        public T getFirst(){return this.first;}
        public U getSecond(){return this.second;}
    }
}
