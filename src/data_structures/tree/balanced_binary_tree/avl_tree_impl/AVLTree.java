package data_structures.tree.balanced_binary_tree.avl_tree_impl;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class AVLTree<T extends Comparable<? super T>> {
    public static class Node<T extends Comparable<? super T>> implements Comparable<Node<T>> {
        private T data;
        private int height;
        private Node<T> left;
        private Node<T> right;

        public Node(T data) {
            this.data = data;
            this.height = 1;
        }

        public T getData() {
            return this.data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getLeft() {
            return this.left;
        }

        public void setLeft(Node<T> left) {
            this.left = left;
        }

        public Node<T> getRight() {
            return this.right;
        }

        public void setRight(Node<T> right) {
            this.right = right;
        }

        @Override
        public String toString() {
            return this.data.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node))
                return false;
            Node<?> node = (Node<?>) o;
            return data.equals(node.data) &&
                    (left != null && left.equals(node.left)) &&
                    (right != null && right.equals(node.right));
        }

        @Override
        public int hashCode() {
            return Objects.hash(data, left, right);
        }

        @Override
        public int compareTo(@NotNull Node<T> o) {
            return this.data.compareTo(o.data);
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }

    private Node<T> root;
    private int size;

    public AVLTree() {
        this.root = null;
        this.size = 0;
    }

    public void deleteTree() {
        this.root = null;
        this.size = 0;
    }

    public Node<T> getRoot() {
        return this.root;
    }

    public int getSize() {
        return this.size;
    }

    public void insert(T data) {
        this.root = insert(this.root, data);
    }

    private Node<T> insert(Node<T> node, T data) {
        if(node == null) {
            this.size++;
            return new Node<>(data);
        }
        if(node.data.compareTo(data) >= 1) {
            node.left = insert(node.left, data);
            if(getBalance(node) == 2) {
                // if node is the left of left subtree of current node
                if(node.left.data.compareTo(data) >= 0) {
                    node = rightRotate(node);
                }
                // if node is the right of left subtree of current node
                else {
                    node = leftRightRotate(node);
                }
            }
        }
        else {
            node.right = insert(node.right, data);
            if(getBalance(node) == 2) {
                // if node is the right of the right subtree of current node
                if(node.right.data.compareTo(data) <= 0) {
                    node = leftRotate(node);
                }
                // if the node is the left of the right subtree of the current node
                else {
                    node = rightLeftRotate(node);
                }
            }
        }
        node.height = Math.max(getHeight(node.right), getHeight(node.left)) + 1;
        return node;
    }

    /*
        - stack for storing current node and it's parent

        - IMPORTANT if current node has same value as the value to be inserted then it should be inserted in right side
            eg:
               2
                \
                 2
     */
    private Deque<Node<T>> balancingStack;
    public void insertNew(T data) {
        if(this.root == null) {
            this.root = new Node<>(data);
            this.balancingStack = new ArrayDeque<>();
            this.size++;
            return;
        }

        var curr = this.root;
        Node<T> previous = null;
        var newNode = new Node<>(data);
        while(curr != null) {
            previous = curr;
            balancingStack.push(curr);
            if(curr.data.compareTo(data) >= 1) {
                curr = curr.left;
                if(curr == null) {
                    previous.left = newNode;
                }
            }
            else {
                curr = curr.right;
                if(curr == null) {
                    previous.right = newNode;
                }
            }
        }
        balanceTree(data);
        this.size++;
    }

    /*
        if node is in right of right subtree
        else node is in the left of right subtree

        if node is left of left subtree
        else node is in the right of left subtree
    */
    private void balanceTree(T data) {
        while(!balancingStack.isEmpty()) {
            var temp = balancingStack.pop();
            if(getBalance(temp) == 2) {
                if(temp.data.compareTo(data) <= 0) {
                    if(temp.right.data.compareTo(data) <= 0) {
                        temp = leftRotate(temp);
                    }
                    else {
                        temp = rightLeftRotate(temp);
                    }
                }
                else {
                    if(temp.left.data.compareTo(data) >= 0) {
                        temp = rightRotate(temp);
                    }
                    else {
                        temp = leftRightRotate(temp);
                    }
                }
                // this if check is used to determine, whether temp will be attached to left or to right side of the peek Node of stack
                // or will it become the root node
                /*
                    eg: 17                              17
                          \                               \
                          19 <- previousChild             18  <- newChild
                          /                               /  \
                         18  => after leftRotation       18  19
                           \
                           18
                 */
                if(balancingStack.isEmpty()) {
                    this.root = temp;
                }
                else {
                    var peekNode = balancingStack.peek();
                    if(peekNode.data.compareTo(temp.data) <= 0) {
                       peekNode.right = temp;
                    }
                    else {
                        peekNode.left = temp;
                    }
                }
            }
            else {
                temp.height = Math.max(getHeight(temp.left), getHeight(temp.right)) + 1;
            }
        }
    }

    /*
            A                      B
           / \                    / \
          B   C                  Bl  A
         / \       -->          /   / \
        Bl  Br                 UB Br  C
       /
     UB
    UB = unbalanced node

    */
    private Node<T> rightRotate(Node<T> node) {
        var leftNode = node.left;
        node.left = leftNode.right;
        leftNode.right = node;

        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        leftNode.height = Math.max(getHeight(leftNode.right) , getHeight(leftNode.left)) + 1;

        return leftNode;
    }

    // similar to Right rotate
    private Node<T> leftRotate(Node<T> node) {
        var rightNode = node.right;
        node.right = rightNode.left;
        rightNode.left = node;

        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        rightNode.height = Math.max(getHeight(rightNode.left), getHeight(rightNode.right)) + 1;

        return rightNode;
    }


    /*
            A              A                    Br
           / \            / \                  /  \
          B   C    LR    Br  C       RR       B    A
         / \       -->  /  \         -->    /     / \
        Bl  Br         B   UB              Bl    UB  C
             \        /
             UB     Bl
    RR = right_rotation   LR = left_rotation
     */
    private Node<T> leftRightRotate(Node<T> node) {
        node.left = leftRotate(node.left);
        return rightRotate(node);
    }

    // similar to left Right rotate just the mirror image
    private Node<T> rightLeftRotate(Node<T> node) {
        node.right = rightRotate(node.right);
        return leftRotate(node);
    }

    private int getHeight(Node<T> node) {
        if(node == null) {
            return 0;
        }
        return node.height;
    }

    private int getBalance(Node<T> node) {
        return Math.abs(getHeight(node.left) - getHeight(node.right));
    }

    public String inOrder() {
        if(this.root == null) {
            return "[]";
        }
        Deque<Node<T>> stack = new ArrayDeque<>();
        var curr = this.root;
        List<String> finalAnswer = new ArrayList<>(this.size);
        while(curr != null || !stack.isEmpty()) {
            if(curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            else {
                curr = stack.pop();
                finalAnswer.add(curr.data.toString());
                curr = curr.right;
            }
        }
        return "[ " + String.join(", ", finalAnswer) + " ]";
    }

    public String levelOrder() {
        if(this.root == null) {
            return "[]";
        }
        List<List<String>> finalAnswer = new ArrayList<>();
        List<String> tempList = new ArrayList<>();

        Deque<Node<T>> queue = new LinkedList<>();
        queue.add(this.root);
        queue.add(null);

        Node<T> curr;
        while(!queue.isEmpty()) {
           curr = queue.poll();
           if(curr != null) {
               tempList.add(curr.data.toString());
               if(curr.left != null) {
                   queue.add(curr.left);
               }
               if(curr.right != null) {
                   queue.add(curr.right);
               }
           }
           else {
               finalAnswer.add(new ArrayList<>(tempList));
               tempList.clear();
               if(!queue.isEmpty()) {
                   queue.add(null);
               }
           }
        }
        return "[" + finalAnswer.stream()
                .map(list -> "(" + String.join(", ", list) + ")")
                .collect(Collectors.joining(", \n")) + "]";
    }
}