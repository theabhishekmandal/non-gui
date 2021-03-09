package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.*;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Given a binary tree convert it into Dll where the left pointer denotes the previous node and the right pointer denotes
 * next pointer
 *
 * Approach:
 *  -   Use Inorder traversal to insert node in Dll using head and tail pointer
 */

public class ConvertBinaryTreeToDll {
    public static void main(String[] args) {
        BinaryTree<Integer> tree = getInput();
        System.out.println(tree.levelOrderPretty());
        System.out.println(convertBinaryTreeToDllAndPrint(tree));
    }

    private static BinaryTree<Integer> getInput() {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        Node<Integer> root = Node.of(0);
        root.setLeft(Node.of(1));
        root.setRight(Node.of(2));

        root.getLeft().setRight(Node.of(3));
        root.getLeft().getRight().setRight(Node.of(4));
        root.getLeft().getRight().getRight().setRight(Node.of(5));

        root.getRight().setLeft(Node.of(6));
        root.getRight().getLeft().setLeft(Node.of(7));
        root.getRight().getLeft().getLeft().setLeft(Node.of(8));

        binaryTree.setRoot(root);
        return binaryTree;
    }

    private static <T> List<List<T>> convertBinaryTreeToDllAndPrint(BinaryTree<T> tree) {
        Node<T> node = convertBinaryTreeToDllNew(tree);
        if (node == null) return Collections.emptyList();
        List<List<T>> finalList = new ArrayList<>();

        Node<T> temp = node;
        Node<T> prev = null;

        // get left inorder traversal
        List<T> list = new ArrayList<>();
        while (temp != null) {
            list.add(temp.getData());
            prev = temp;
            temp = temp.getRight();
        }
        finalList.add(list);

        // get right inorder traversal
        Objects.requireNonNull(prev);
        list = new ArrayList<>();
        while (prev != null) {
            list.add(prev.getData());
            prev = prev.getLeft();
        }
        finalList.add(list);

        return finalList;
    }

    private static <T> Node<T> convertBinaryTreeToDllNew(BinaryTree<T> binaryTree) {
        if (Objects.isNull(binaryTree) || Objects.isNull(binaryTree.getRoot())) return null;

        Node<T> head = null;
        Node<T> tail = null;

        Deque<Node<T>> stack = new ArrayDeque<>();
        Node<T> curr = binaryTree.getRoot();

        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.getLeft();
            } else {
               curr = stack.pop();
               var right = curr.getRight();
               if (head == null) {
                   head = curr;
                   tail = head;
               } else {
                   tail.setRight(curr);
                   curr.setLeft(tail);
                   tail = curr;
               }
               curr = right;
            }
        }
        return head;
    }
}

