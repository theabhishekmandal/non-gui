package data_structures.tree.binary_search_tree.binary_search_tree_impl;


import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class BinarySearchTree<T extends Comparable<? super T>>{
    public static class Node<T extends Comparable<? super T>> implements Comparable<Node<T>>{
        private T data;
        private Node<T> left;
        private Node<T> right;

        public Node(T data){
            this.data = data;
        }

        public T getData(){
            return this.data;
        }

        public void setData(T data){
            this.data = data;
        }

        public Node<T> getLeft(){
            return this.left;
        }

        public void setLeft(Node<T> left){
            this.left = left;
        }

        public Node<T> getRight(){
            return this.right;
        }

        public void setRight(Node<T> right){
            this.right = right;
        }

        @Override
        public String toString(){
            return this.data.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof BinarySearchTree.Node)) return false;
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
    }

    private Node<T> root;
    private int size;
    private boolean doReverse;
    public Node<T> getRoot(){
        return this.root;
    }
    public void setRoot(Node<T> root) {
        this.root = root;
    }
    public int getSize(){
        return this.size;
    }
    public BinarySearchTree(){
        this.size = 0;
        this.root = null;
    }
    public BinarySearchTree(Boolean reverse){
        this();
        this.doReverse = reverse != null && reverse;
    }

    public void deleteTree(){
        this.root = null;
        this.size = 0;
    }

    // Don't use this this is slow
    /*
    private void insertInBstUsingQueue(@NotNull T data){
        if(this.root == null) {
            this.root = new node<>(data);
            this.size++;
            return;
        }
        node<T> newnode = new node<>(data);
        Deque<node<T>> queue = new LinkedList<>();
        queue.add(this.root);
        while(!queue.isEmpty()){
            node<T> temp = queue.poll();
            int compare = temp.compareTo(newnode);
            if(compare < 0){
                if(temp.right == null){
                    temp.right = newnode;
                    return;
                }
                else{
                    queue.add(temp.right);
                }
            }
            else{
                if(temp.left == null){
                    temp.left = newnode;
                    return;
                }
                else{
                    queue.add(temp.left);
                }
            }
        }
    }
    */

    public void insertInBst(@NotNull  T data){
        if(this.root == null) {
            this.root = new Node<>(data);
            this.size++;
            return;
        }
        Node<T> prev = null;
        Node<T> temp = this.root;
        Node<T> newnode = new Node<>(data);
        while(temp != null){
            prev = temp;
            int compare = temp.compareTo(newnode);
            compare = (doReverse) ? -compare : compare;
            if(compare >= 1) {
                temp = temp.left;
            }
            else {
                temp = temp.right;
            }
        }

        int compare = prev.compareTo(newnode);
        compare = (doReverse) ? -compare : compare;
        if(compare >= 1){
            prev.left = newnode;
        }
        else {
            prev.right = newnode;
        }
        this.size++;
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
    public String preOrder(){
        if(this.root == null) return "";

        Deque<Node<T>> stack = new ArrayDeque<>();
        stack.push(this.root);

        List<String> finalAnswer = new ArrayList<>();
        while(!stack.isEmpty()){
            Node<T> temp = stack.pop();

            // adding the answer
            finalAnswer.add(temp.toString());

            if(temp.right != null) {
                stack.push(temp.right);
            }
            if(temp.left != null){
                stack.push(temp.left);
            }
        }
        return "[" + String.join(", ", finalAnswer) + "]";
    }

    /*
        process current, left and right

        Preorder traversal using recursion
     */
    public String preOrderRecursive(){
        List<String> finalAnswer = new ArrayList<>();
        preOrderRec(this.root, finalAnswer);
        return "[" + String.join(", ", finalAnswer) + "]";
    }

    private void preOrderRec(Node<T> node, List<String> answer){
        if(node == null) return;
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
    public String inOrder(){
        if(this.root == null) return "";
        List<String> finalAnswer = new ArrayList<>();

        Deque<Node<T>> stack = new ArrayDeque<>();
        Node<T> curr = this.root;

        while(curr != null || !stack.isEmpty()){
            if(curr != null){
                stack.push(curr);
                curr = curr.left;
            }
            else{
                curr = stack.pop();

                // adding the answer
                finalAnswer.add(curr.data.toString());
                curr = curr.right;
            }
        }

        return "[" + String.join(", ", finalAnswer) + "]";
    }
    /*
        process left, current and right

        InOrder traversal using recursion
     */
    public String inOrderRecursive(){
        List<String> finalAnswer = new ArrayList<>();
        inOrderRec(this.root, finalAnswer);
        return "[" + String.join(", ", finalAnswer) + "]";
    }

    private void inOrderRec(Node<T> node, List<String> answer){
        if(node == null) return;
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
    public String postOrder(){
        if(this.root == null) return "";
        List<String> finalAnswer = new ArrayList<>();

        Deque<Node<T>> stack = new ArrayDeque<>();
        stack.push(this.root);
        stack.push(this.root);
        while(!stack.isEmpty()){
            Node<T> curr = stack.pop();
            if(!stack.isEmpty() && stack.peek() == curr){
                if(curr.right != null){
                    stack.push(curr.right);
                    stack.push(curr.right);
                }
                if(curr.left != null){
                    stack.push(curr.left);
                    stack.push(curr.left);
                }
            }
            else
               finalAnswer.add(curr.data.toString());
        }
        return "[" + String.join(", ", finalAnswer) + "]";
    }
    /*
        process left, right and current

        PostOrder traversal using recursion
     */
    public String postOrderRecursive(){
        List<String> finalAnswer = new ArrayList<>();
        postOrderRec(this.root, finalAnswer);
        return "[" + String.join(", ", finalAnswer) + "]";
    }

    private void postOrderRec(Node<T> node, List<String> answer){
        if(node == null) return;
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
    public String levelOrder(){
       if(this.root == null) return "";
       Queue<Node<T>> queue = new LinkedList<>();

       queue.add(this.root);
       queue.add(null);

       List<List<String>> finalList = new ArrayList<>();
       List<String> nodeList = new ArrayList<>();
       while(!queue.isEmpty()){
           Node<T> curr = queue.poll();
           if(curr != null){
              nodeList.add(curr.data.toString());
              if(curr.left != null)
                  queue.add(curr.left);
              if(curr.right != null)
                  queue.add(curr.right);
           }
           else{
               // creating new list because, if we clear nodeList then nodeList will be cleared
               // from the final list
                List<String> tempList = new ArrayList<>(nodeList);
                finalList.add(tempList);

                // clearing the list for next level
                nodeList.clear();
                if(!queue.isEmpty())
                    queue.add(null);

           }
       }
       return "[" + finalList.stream()
               .map(list -> "(" + String.join(", ", list) + ")")
               .collect(Collectors.joining(", \n")) + "]";
    }


    /*
        To delete a node from a BST, there are three conditions
            -   node to be deleted is leaf node
                -   simply remove from the tree
            -   node to be deleted has one child
                -   copy the child value to the node and then delete the child
            -   node to be deleted has both right as well left child
                -   find the inorder successor of the node
                    -   if inorder successor is present which is a left leaf node, then make the
                        right child of the successor, left child of the parent
                    -   if inorder successor is the immediate right child of the node(to be deleted), then
                        make the right child of successor as right child of the parent
     */
    public void deleteNodeFromTreeRecur(T data) {
        this.root = delete(this.root, data);
    }

    private Node<T> delete(Node<T> node, T data) {
        if(this.root == null) {
            return null;
        }

        // returning node is important, consider the case of node to be deleted, is the only child
        // eg: [1, 2] and node to be deleted is 2, so we have to make 1.right = 2.right
        if(node.data.compareTo(data) > 0) {
            node.left = delete(node.left, data);
            return node;
        }
        else if(node.data.compareTo(data) < 0) {
            node.right = delete(node.right, data);
            return node;
        }

        // if there are no children or only one children then these two conditions
        if(node.left == null) {
            return node.right;
        }
        else if(node.right == null) {
            return node.left;
        }
        // if both of the children exists
        else {
            var parent = node;
            var succ = node.right;

            // finding nearest inorder successor
            while(succ.left != null) {
                parent = succ;
                succ = succ.left;
            }

            /*
            first if condition
                  65                                76
                    \                                 \
                     82         -> delete 65 ->       82
                    /  \                             /  \
                   76   88                          78   88
                     \   \                                \
                     78   89                              89
             else condition
                  65                                82
                    \                                 \
                     82         -> delete 65 ->       88
                       \                                \
                        88                               89
                         \
                          89
             */
            if (parent != node) {
                parent.left = succ.right;
            }
            else {
                parent.right = succ.right;
            }
            node.data = succ.data;
            return node;
        }
    }


    // iterative solution of above problem
    public void deleteNodeFromTree(T data) {
        if(this.root == null) {
            return;
        }

        var curr = this.root;
        Node<T> prev = null;
        // finding the correct node that is to be deleted
        while(curr != null) {
            var compare = curr.data.compareTo(data);
            if(compare == 0) {
                break;
            }
            prev = curr;
            if(compare > 0) {
                curr = curr.left;
            }
            else {
                curr = curr.right;
            }
        }

        if(curr == null) {
            return;
        }
        // this means root is not your data node to be deleted
        if(prev != null) {
            if(prev.left == curr) {
                prev.left = deleteNode(prev.left);
            }
            else {
                prev.right = deleteNode(prev.right);
            }
        }
        else {
            this.root = deleteNode(this.root);
        }
    }

    private Node<T> deleteNode(Node<T> curr) {
       if(curr.left == null) {
           var temp = curr.right;
           curr.right = null;
           return temp;
       }
       else if(curr.right == null) {
           var temp = curr.left;
           curr.left = null;
           return temp;
       }
       else {
           var parent = curr;
           Node<T> succ = curr.right;
           while(succ.left != null) {
               parent = succ;
               succ = succ.left;
           }

           if(parent != curr) {
               parent.left = succ.right;
           }
           else {
               parent.right = succ.right;
           }
           curr.data = succ.data;
           return curr;
       }
    }
}
