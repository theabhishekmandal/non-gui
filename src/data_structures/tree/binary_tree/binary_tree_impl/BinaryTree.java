package data_structures.tree.binary_tree.binary_tree_impl;


import java.util.*;
import java.util.stream.Collectors;

public class BinaryTree<T> {
    private static final String DELIMITER = ", ";
    private static final String PREFIX = "[";
    private static final String SUFFIX = "]";
    private static final String EMPTY_BRACES = "[]";
    private static final String STAR = "*";
    /*
                Queue is used as a data structure while inserting to the tree
                after inserting every node we need to break because,
                    -   after inserting in left side we don't want to insert it in right side
                    -   after inserting in the right side we don't want to want to process other elements in the queue
                        until next data comes for insertion
                after inserting the node we have to check whether both of it's child are present or not
                -   if both children are not present then insert the parent node on the left side of queue
     */
    private final Deque<Node<T>> queue = new LinkedList<>();
    private Node<T> root;
    private boolean isSetThroughRootMethod = false;
    private int size;

    public BinaryTree() {
        this.size = 0;
        this.root = null;
    }

    public BinaryTree(Node<T> root) {
        setRoot(root);
    }

    public Node<T> getRoot() {
        return this.root;
    }

    public void setRoot(Node<T> root) {
        this.root = root;
        this.isSetThroughRootMethod = root != null;
        this.size = calculateSize(this.root);
    }

    private void isSetThroughRoot() {
        if (this.isSetThroughRootMethod) {
            throw new UnsupportedOperationException();
        }
    }

    public int getSize() {
        return this.size;
    }

    public int calculateSize(Node<T> root) {
        if (root == null) {
            return 0;
        }
        Queue<Node<T>> tempQueue = new ArrayDeque<>();
        tempQueue.add(root);
        var tempSize = 0;
        while (!tempQueue.isEmpty()) {
            var curr = tempQueue.poll();
            if (curr != null) {
                ++tempSize;
                if (curr.left != null) {
                    tempQueue.add(curr.left);
                }
                if (curr.right != null) {
                    tempQueue.add(curr.right);
                }
            }
        }
        return tempSize;
    }

    public void deleteTree() {
        this.root = null;
        this.size = 0;
    }

    public void insertInBinaryTreeLevelOrder(T data) {
        isSetThroughRoot();
        Node<T> newNode = Node.of(data);
        if (queue.isEmpty()) {
            root = newNode;
        } else {
            Node<T> temp = queue.peek();
            if (temp.left == null) {
                temp.left = newNode;
            } else if (temp.right == null) {
                temp.right = newNode;
            }
            if (temp.right != null) {
                queue.poll();
            }
        }
        queue.add(newNode);
        size++;
    }

    public boolean deleteNode(T data) {
        isSetThroughRoot();
        if (this.root == null || data == null) {
            return false;
        }

        Node<T> nodeToBeDeleted = null;
        Node<T> lastNode = null;
        Node<T> parent = null;
        var firstMatch = true;

        Deque<Node<T>> tempQueue = new ArrayDeque<>();
        tempQueue.add(root);

        while (!tempQueue.isEmpty()) {
            lastNode = tempQueue.poll();

            // using flag to detect the first matching node
            if (lastNode.data.equals(data) && firstMatch) {
                firstMatch = false;
                nodeToBeDeleted = lastNode;
            }

            // saving the parent of lastNode
            if (lastNode.left != null || lastNode.right != null) {
                parent = lastNode;
            }

            // saving the parent node also
            if (lastNode.left != null) {
                tempQueue.add(lastNode.left);
            }
            if (lastNode.right != null) {
                tempQueue.add(lastNode.right);
            }
        }

        if (lastNode != null && nodeToBeDeleted != null) {
            nodeToBeDeleted.data = lastNode.data;
            if (parent == null) {
                root = null;
            } else {
                if (parent.left == lastNode) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
            size--;
            return true;
        }
        return false;
    }

    /*
     process current, left and right
     Approach:
            -   Stack is used here to traverse the nodes
            -   First pop the current element from the stack and append it to the string
            -   Then add the right element of the current popped node and the left node.
                Remember to add the right node first in the stack and then the left node, because
                in stack the left node will be processed first.
    */
    public String preOrder() {
        if (this.root == null) {
            return EMPTY_BRACES;
        }

        Deque<Node<T>> stack = new ArrayDeque<>();
        stack.push(root);

        var joiner = new StringJoiner(DELIMITER, PREFIX, SUFFIX);
        while (!stack.isEmpty()) {
            Node<T> temp = stack.pop();

            // adding the answer
            joiner.add(temp.toString());

            if (temp.right != null) {
                stack.push(temp.right);
            }
            if (temp.left != null) {
                stack.push(temp.left);
            }
        }
        return joiner.toString();
    }

    /*
        process current, left and right

        Preorder traversal using recursion
     */
    public String preOrderRecursive() {
        var joiner = new StringJoiner(DELIMITER, PREFIX, SUFFIX);
        preOrderRec(root, joiner);
        return joiner.toString();
    }

    private void preOrderRec(Node<T> node, StringJoiner answer) {
        if (node == null) {
            return;
        }
        answer.add(node.toString());
        preOrderRec(node.left, answer);
        preOrderRec(node.right, answer);
    }

    /*
    process left, current, right
     Approach:
            -   Stack is used here to traverse the nodes
            -   In the while condition two types of conditions are used.
                    -   if curr != null , this will be required when we have processed all the left side of the
                        root node, but the right side still remains. So, in that case we cannot depend on
                        !stack.isEmpty(), because stack will be empty at that time.
                        Example: use node [1, 2, 3], when 1 and 2 will be processed then current node will be 3
                        but stack will be empty at that time


                    -   !stack.isEmpty(), will be required when we have reached a child of the leaf node which
                        will be null, So, at that time we have to process the node present in the stack

                    -   If the current node is not null then push the current to the stack and make left the new
                        current.
                        Else pop the last node from the stack and add it to the string, and make the right node
                        of popped value the new current node.

    */
    public String inOrder() {
        if (this.root == null) {
            return EMPTY_BRACES;
        }
        var joiner = new StringJoiner(DELIMITER, PREFIX, SUFFIX);

        Deque<Node<T>> stack = new ArrayDeque<>();
        Node<T> curr = root;

        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = stack.pop();

                // adding the answer
                joiner.add(curr.data.toString());
                curr = curr.right;
            }
        }

        return joiner.toString();
    }

    /*
        process left, current and right

        InOrder traversal using recursion
     */
    public String inOrderRecursive() {
        var joiner = new StringJoiner(DELIMITER, PREFIX, SUFFIX);
        inOrderRec(root, joiner);
        return joiner.toString();
    }

    private void inOrderRec(Node<T> node, StringJoiner answer) {
        if (node == null) {
            return;
        }
        inOrderRec(node.left, answer);
        answer.add(node.toString());
        inOrderRec(node.right, answer);
    }

    /*
        process left, right, root
        Approach:
            -   In postOrder traversal we need to process left then right and then at last the current node.
                But, what happens is that, if we process left node and then go to current node, then we are
                not sure whether we have come back from the left child or the right child. So, that's why a
                different approach is used
            -   In this approach we will push each node two times.
            -   First push the root node two times
            -   In the while loop check if the popped element is equal to the top of the stack.
                -   If it is equal, it means that we need to process the child nodes also,
                        -   push the left and right children two times if present. Also note that
                            right child are pushed first then left children because, left is processed before
                            in post order traversal.
                -   If it is not equal, then it means processing of it's children has been done and now
                    it can be added to answer.
     */
    public String postOrder() {
        if (this.root == null) {
            return EMPTY_BRACES;
        }

        var joiner = new StringJoiner(DELIMITER, PREFIX, SUFFIX);
        Deque<Node<T>> stack = new ArrayDeque<>();
        stack.push(root);
        stack.push(root);
        while (!stack.isEmpty()) {
            Node<T> curr = stack.pop();
            if (!stack.isEmpty() && stack.peek() == curr) {
                if (curr.right != null) {
                    stack.push(curr.right);
                    stack.push(curr.right);
                }
                if (curr.left != null) {
                    stack.push(curr.left);
                    stack.push(curr.left);
                }
            } else {
                joiner.add(curr.data.toString());
            }
        }
        return joiner.toString();
    }

    /*
        process left, right and current

        PostOrder traversal using recursion
     */
    public String postOrderRecursive() {
        var joiner = new StringJoiner(DELIMITER, PREFIX, SUFFIX);
        postOrderRec(this.root, joiner);
        return joiner.toString();
    }

    private void postOrderRec(Node<T> node, StringJoiner answer) {
        if (node == null) {
            return;
        }
        postOrderRec(node.left, answer);
        postOrderRec(node.right, answer);
        answer.add(node.toString());
    }

    /*
        Level Order traversal is same as the insertion above,
        first process the l level elements and then l + 1 elements and so on

        Approach
                -   Using queue as ds because, traversing of all nodes at the same level are done first
                -   Here after every level we are adding null, null is added to first process the nodes of level l
                    and then go to level l + 1
     */
    public String levelOrder() {
        if (this.root == null) {
            return EMPTY_BRACES;
        }
        return PREFIX + levelOrderPrivate().stream()
                .map(StringJoiner::toString)
                .collect(Collectors.joining(", \n")) + SUFFIX;
    }

    private List<StringJoiner> levelOrderPrivate() {
        if (this.root == null) {
            return Collections.emptyList();
        }
        Queue<Node<T>> nodeQueue = new LinkedList<>();

        nodeQueue.add(this.root);
        nodeQueue.add(null);

        List<StringJoiner> finalList = new ArrayList<>();
        var joiner = new StringJoiner(DELIMITER, PREFIX, SUFFIX);
        Node<T> emptyObject = Node.of(null);
        while (!nodeQueue.isEmpty()) {
            Node<T> curr = nodeQueue.poll();
            if (curr != null) {
                if (curr != emptyObject) {
                    joiner.add(curr.data.toString());
                    nodeQueue.add(Objects.requireNonNullElse(curr.left, emptyObject));
                    nodeQueue.add(Objects.requireNonNullElse(curr.right, emptyObject));
                } else {
                    joiner.add(STAR);
                }
            } else {
                // creating new list because, if we clear joiner then joiner will be cleared
                // from the final list
                finalList.add(joiner);

                // clearing the list for next level
                joiner = new StringJoiner(DELIMITER, PREFIX, SUFFIX);
                if (!nodeQueue.isEmpty()) {
                    nodeQueue.add(null);
                }
            }
        }
        return finalList;
    }

    public String levelOrderPretty() {
        List<StringJoiner> finalAnswer = levelOrderPrivate();
        if (finalAnswer.isEmpty()) {
            return EMPTY_BRACES;
        }
        var br = new StringBuilder();
        int maxSize = finalAnswer.get(finalAnswer.size() - 2).length();
        for (var i = 0; i < finalAnswer.size() - 1; i++) {
            var joiner = finalAnswer.get(i);
            int rem = ((maxSize - joiner.length()) + 1) >> 1;
            br.append(" ".repeat(Math.max(0, rem)));
            br.append(joiner).append("\n");
        }
        return br.toString();
    }

    public static class Node<T> {
        private T data;
        private Node<T> left;
        private Node<T> right;

        public Node(T data) {
            this.data = data;
        }

        public static <T> Node<T> of(T data) {
            return new Node<>(data);
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
            if (!(o instanceof BinaryTree.Node)) return false;
            Node<?> node = (Node<?>) o;
            return data.equals(node.data) &&
                    (left != null && left.equals(node.left)) &&
                    (right != null && right.equals(node.right));
        }

        @Override
        public int hashCode() {
            return Objects.hash(data, left, right);
        }
    }
}
