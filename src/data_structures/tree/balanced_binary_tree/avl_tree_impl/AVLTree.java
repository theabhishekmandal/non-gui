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
    private static final int BALANCE_FACTOR  = 2;

    public AVLTree() {
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

    /*
        Recursive version of insertion of node in AVL tree
     */
    private Node<T> insert(Node<T> node, T data) {
        if(node == null) {
            this.size++;
            return new Node<>(data);
        }
        if(node.data.compareTo(data) > 0) {
            node.left = insert(node.left, data);
            if(getBalance(node) == BALANCE_FACTOR) { // if node is imbalance then new node will be returned.
                if(getHeight(node.left.left) > getHeight(node.left.right)) {
                    node = rightRotate(node);
                }
                else {
                    node = leftRightRotate(node);
                }
            }
        }
        else if (node.data.compareTo(data) < 1){
            node.right = insert(node.right, data);
            if(getBalance(node) == BALANCE_FACTOR) {
                 if(getHeight(node.right.right) > getHeight(node.right.left)) {
                    node = leftRotate(node);
                }
                else {
                    node = rightLeftRotate(node);
                }
            }
        }
        // should not insert duplicates.
        else {
            return node;
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
        - Inserting In a AVL tree is similar to that of binary search tree, accept that at last we need to balance
          all the nodes that were encountered during the path
          eg:  if in the below tree, 6 is inserted then it becomes unbalanced, then we have to re balance all the nodes
               again that were encountered
                    4            4            5
                     \      ->    \   ->     /  \
                      5            5        4    6
                                    \
                                     6

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
        balanceTree(balancingStack);
        this.size++;
    }

    /*
        -   To delete a node first find the appropriate node that is to be deleted
        -   deleting the node here means, we are replacing it's value with new appropriate value which is calculated below
        -   after node is deleted re balancing of tree is required
     */

    public Node<T> deleteNode(T data) {
        if(data == null) {
            return null;
        }

        Deque<Node<T>> balancingStack = new ArrayDeque<>();
        Node<T> curr = this.root;
        Node<T> prev = null;
        while(curr != null) {
            // check first then assign to previous because element deleted can also be root
            int compare = curr.data.compareTo(data);
            if(compare == 0) {
                break;
            }
            prev = curr;
            // adding node visited in balancingStack for balancing
            balancingStack.push(curr);
            if(compare > 0) {
                curr = curr.left;
            }
            else {
                curr = curr.right;
            }
        }

        // value to be deleted not found in tree
        if(curr == null) {
            return null;
        }

        var nodeToBeDeleted = new Node<>(curr.data);

        if(prev != null) {
            if(prev.left == curr) {
               prev.left = delete(prev.left);
            }
            else {
               prev.right = delete(prev.right);
            }
        }
        else {
            this.root = delete(this.root);
        }
        this.size--;
        balanceTree(balancingStack);
        return nodeToBeDeleted;
    }

    /*
        -   This method finds which node will become the new node that is passed as a parameter, in other words
            it finds the successor of the current node passed as parameter
        -   If the node left is null or right is null return the other side value
        -   If both left and right sides are present, then we find the next nearest inorder successor, that will
            take it's place
        -  This is the case if both left and right child are present
            first if condition i.e the parent node has it's leftmost inorder successor,
            here you can see height of node 82 is changing.

                  65 <- node                        76
                    \                                 \
          parent ->  82         -> delete 65 ->       82
                    /  \                             /  \
          succ  -> 76   88                          78   88
                     \   \                                \
                     78   89                              89

             else condition i.e the parent node do not has it's leftmost inorder successor
                  65 <- node, parent                 82
                    \                                 \
             succ -> 82         -> delete 65 ->       88
                       \                                \
                        88                               89
                         \
                          89
     */
    private Node<T> delete(Node<T> node) {
        // if any one of the child is present, then return the child as parent will be deleted
        // and it will become the child of the ancestor.
        if(node.left == null) {
            var temp = node.right;
            node.right = null;
            return temp;
        }
        else if(node.right == null) {
           var temp = node.left;
           node.left = null;
           return temp;
        }
        else {
            var parent = node;
            var succ = node.right;
            while(succ.left != null) {
                parent = succ;
                succ = succ.left;
            }

            if(parent != node) {
                parent.left = succ.right;
            }
            else {
                parent.right = succ.right;
            }
            // calculate the new height, it's important for re balancing, because this operation removes nodes
            // so new height is to be calculated
            parent.height = calculateHeight(parent);
            node.data = succ.data;
            return node;
        }
    }

    /*
        -   To balance a tree, a stack of nodes is used, this stack is created while inserting or deleting nodes
        -   HERE ALL THE NODES ARE INSERTED EXCEPT THE NODE THAT IS INSERTED IN THE TREE OR DELETED FROM TREE
        -   Balancing is done when the balance factor is 2
            -   If right subtree has more height then balancing is done on right side, otherwise left side
                -   In this also we have different rotations based on which side they lie

                -   If node unbalanced is on the right side of right subtree then LEFT ROTATION
                -   If node unbalanced is on the left side of right subtree then RIGHT LEFT ROTATION
                -   If node unbalanced is on the left side of left subtree then RIGHT ROTATION
                -   If node unbalanced is on the right side of left subtree then LEFT RIGHT ROTATION
        -   It is important to note that after rotation, the new node that becomes the parent of the subtree,
            must be linked with top of the stack or the root

        -   If node is already balanced then update it's new height

     */
    private void balanceTree(Deque<Node<T>> stack) {
        while(!stack.isEmpty()) {
            var temp = stack.pop();
            if(getBalance(temp) == BALANCE_FACTOR) {
                if(getHeight(temp.right) >= getHeight(temp.left)) {
                    if(getHeight(temp.right.right) > getHeight(temp.right.left)) {
                        temp = leftRotate(temp);
                    }
                    else {
                        temp = rightLeftRotate(temp);
                    }
                }
                else {
                    if(getHeight(temp.left.left) > getHeight(temp.left.right)) {
                        temp = rightRotate(temp);
                    }
                    else {
                        temp = leftRightRotate(temp);
                    }
                }

                if(stack.isEmpty()) {
                    this.root = temp;
                }
                else {
                    var peekNode = stack.peek();
                    if(peekNode.data.compareTo(temp.data) <= 0) {
                        peekNode.right = temp;
                    }
                    else {
                        peekNode.left = temp;
                    }
                }
            }
            else {
                temp.height = calculateHeight(temp);
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

        node.height = calculateHeight(node);
        leftNode.height = calculateHeight(leftNode);

        return leftNode;
    }

    // similar to Right rotate
    private Node<T> leftRotate(Node<T> node) {
        var rightNode = node.right;
        node.right = rightNode.left;
        rightNode.left = node;

        node.height = calculateHeight(node);
        rightNode.height = calculateHeight(rightNode);

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
        return node == null ? 0 : node.height;
    }

    // balance is checked by taking the absolute of the left and right subtree
    private int getBalance(Node<T> node) {
        return Math.abs(getHeight(node.left) - getHeight(node.right));
    }

    // height is calculated as the max height of left or right + 1 for the parent
    private int calculateHeight(Node<T> node) {
        return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
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

    private List<List<String>> levelOrderPrivate() {
        if(this.root == null) {
            return Collections.emptyList();
        }
        List<List<String>> finalAnswer = new ArrayList<>();
        List<String> tempList = new ArrayList<>();

        Deque<Node<T>> queue = new LinkedList<>();
        queue.add(this.root);
        queue.add(null);

        Node<T> curr;
        Node<T> emptyObject = new Node<>(null);
        while(!queue.isEmpty()) {
            curr = queue.poll();
            if(curr != null) {
                if(curr != emptyObject) {
                    tempList.add(curr.data.toString());
                    queue.add(Objects.requireNonNullElse(curr.left, emptyObject));
                    queue.add(Objects.requireNonNullElse(curr.right, emptyObject));
                }
                else {
                    tempList.add("*");
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
        return finalAnswer;
    }

    public String levelOrder() {
        List<List<String>> finalAnswer = levelOrderPrivate();
        if(finalAnswer.isEmpty()) {
            return "[]";
        }
        return "[" + finalAnswer.stream()
                .map(list -> "(" + String.join(", ", list) + ")")
                .collect(Collectors.joining(", \n")) + "]";
    }

    public String levelOrderPretty() {
       List<List<String>> finalAnswer = levelOrderPrivate();
       if(finalAnswer.isEmpty()) {
           return "[]";
       }
       StringBuilder br = new StringBuilder();
       int maxSize = finalAnswer.get(finalAnswer.size() - 2).size();
       for(int i = 0; i < finalAnswer.size() - 1; i++) {
           var list = finalAnswer.get(i);
           int rem = ((maxSize - list.size()) + 1) >> 1;
           br.append(" ".repeat(Math.max(0, rem)));
           list.forEach(x -> br.append(x).append(" "));
           br.append("\n");
       }
       return br.toString();
    }
}