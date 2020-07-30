package DataStructures.Tree.BinaryTree.BinaryTreeImpl;


import java.util.*;
import java.util.stream.Collectors;

public class BinaryTree<T>{
    public static class node<T> {
        private T data;
        private node<T> left;
        private node<T> right;

        public node(T data){
            this.data = data;
        }

        public T getData(){
            return this.data;
        }

        public void setData(T data){
            this.data = data;
        }

        public node<T> getLeft(){
            return this.left;
        }

        public void setLeft(node<T> left){
            this.left = left;
        }

        public node<T> getRight(){
            return this.right;
        }

        public void setRight(node<T> right){
            this.right = right;
        }

        @Override
        public String toString(){
            return this.data.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof node)) return false;
            node<?> node = (node<?>) o;
            return data.equals(node.data) &&
                    (left != null && left.equals(node.left)) &&
                    (right != null && right.equals(node.right));
        }

        @Override
        public int hashCode() {
            return Objects.hash(data, left, right);
        }
    }

    private node<T> root;
    private int size;
    public node<T> getRoot(){
        return this.root;
    }
    public void setRoot(node<T> root) {
        this.root = root;
    }
    public int getSize(){
        return this.size;
    }
    public BinaryTree(){
        this.size = 0;
        this.root = null;
    }

    public void deleteTree(){
        this.root = null;
        this.size = 0;
    }

    /*
        Queue is used as a data structure while inserting to the tree
        after inserting every node we need to break because,
            -   after inserting in left side we don't want to insert it in right side
            -   after inserting in the right side we don't want to want to process other elements in the queue
                until next data comes for insertion
        after inserting the node we have to check whether both of it's child are present or not
        -   if both children are not present then insert the parent node on the left side of queue
     */
    private Deque<node<T>> queue;
    public void insertInBinaryTreeLevelOrder(T data){
        if(this.root == null) {
            this.root = new node<>(data);
            this.queue = new LinkedList<>();
            this.queue.add(this.root);
            this.size++;
            return;
        }
        node<T> temp = null;
        while(!queue.isEmpty()){
            temp = queue.poll();
            if(temp.left == null){
                temp.left = new node<>(data);
                queue.add(temp.left);
                this.size++;
                break;
            }
            if(temp.right == null){
                temp.right = new node<>(data);
                queue.add(temp.right);
                this.size++;
                break;
            }
        }
        if(temp != null && (temp.left == null || temp.right == null)) {
            queue.addFirst(temp);
        }
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

        Deque<node<T>> stack = new ArrayDeque<>();
        stack.push(this.root);

        List<String> finalAnswer = new ArrayList<>();
        while(!stack.isEmpty()){
            node<T> temp = stack.pop();

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

    private void preOrderRec(node<T> node, List<String> answer){
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

        Deque<node<T>> stack = new ArrayDeque<>();
        node<T> curr = this.root;

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

    private void inOrderRec(node<T> node, List<String> answer){
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

        Deque<node<T>> stack = new ArrayDeque<>();
        stack.push(this.root);
        stack.push(this.root);
        while(!stack.isEmpty()){
            node<T> curr = stack.pop();
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

    private void postOrderRec(node<T> node, List<String> answer){
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
       Queue<node<T>> queue = new LinkedList<>();

       queue.add(this.root);
       queue.add(null);

       List<List<String>> finalList = new ArrayList<>();
       List<String> nodeList = new ArrayList<>();
       while(!queue.isEmpty()){
           node<T> curr = queue.poll();
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
}
