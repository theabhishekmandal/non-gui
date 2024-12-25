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
 *  -   We have to use inorder traversal, because through this we will get leftmost node as the first element.
 */

public class _11EasyConvertBinaryTreeToDll {
    public static void main(String[] args) {
        var tree = getInput();
        System.out.println(tree.levelOrderPretty());
        System.out.println(convertBinaryTreeToDllAndPrint(tree));
    }

    private static BinaryTree<Integer> getInput() {
        var binaryTree = new BinaryTree<Integer>();
        var root = Node.of(0);
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
        var linkedListFromBinaryTree = convertBinaryTreeToDllNew(tree);
        if (linkedListFromBinaryTree.head == null) {
            return Collections.emptyList();
        }
        return List.of(linkedListFromBinaryTree.getLeftInOrderList(), linkedListFromBinaryTree.getRightInOrderList());
    }

    private static <T> LinkedListFromBinaryTree<T> convertBinaryTreeToDllNew(BinaryTree<T> binaryTree) {
        var linkedListFromBinaryTree = new LinkedListFromBinaryTree<T>();
        if (Objects.isNull(binaryTree) || Objects.isNull(binaryTree.getRoot())) {
            return linkedListFromBinaryTree;
        }

        Deque<Node<T>> stack = new ArrayDeque<>();
        Node<T> curr = binaryTree.getRoot();

        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.getLeft();
            } else {
                curr = stack.pop();
                var right = curr.getRight();
                linkedListFromBinaryTree.linkNode(curr);
                curr = right;
            }
        }
        return linkedListFromBinaryTree;
    }

    static class LinkedListFromBinaryTree<T> {
        private Node<T> head;
        private Node<T> tail;

        public void linkNode(Node<T> node) {
            if (node == null) {
                return;
            }
            node.setRight(null);
            if (head == null) {
                head = node;
                tail = head;
            } else {
                tail.setRight(node);
                node.setLeft(tail);
                tail = node;
            }
        }

        public Node<T> getTail() {
            return tail;
        }

        public Node<T> getHead() {
            return head;
        }

        public List<T> getLeftInOrderList() {
            if (head == null) {
                return Collections.emptyList();
            }
            var temp = head;
            var list = new ArrayList<T>();
            while (temp != null) {
                list.add(temp.getData());
                temp = temp.getRight();
            }
            return Collections.unmodifiableList(list);
        }

        public List<T> getRightInOrderList() {
            if (tail == null) {
                return Collections.emptyList();
            }
            var temp = tail;
            var list = new ArrayList<T>();
            while (temp != null) {
                list.add(temp.getData());
                temp = temp.getLeft();
            }
            return Collections.unmodifiableList(list);
        }
    }
}
