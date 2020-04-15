package DataStructures.Tree.TreeImpl;


import java.util.*;

public class BinaryTree<T>{
    public static class node<T> {
        private T data;
        private node<T> left;
        private node<T> right;

        node(T data){
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
    }

    private node<T> root;
    public BinaryTree(){
        this.root = null;
    }

    public void insertInBinaryTreeLevelOrder(T data){
        if(this.root == null) {
            this.root = new node<>(data);
            return;
        }
        Queue<node<T>> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            node<T> temp = queue.poll();
            if(temp.left != null)
                queue.add(temp.left);
            else{
                temp.left = new node<>(data);
                return;
            }
            if(temp.right != null)
                queue.add(temp.right);
            else{
                temp.right = new node<>(data);
                return;
            }
        }
    }

    // process current, left and right
    public String preOrder(){
        if(this.root == null) return "";
        StringBuilder string = new StringBuilder("[ ");

        Deque<node<T>> stack = new ArrayDeque<>();
        stack.push(this.root);

        while(!stack.isEmpty()){
            node<T> temp = stack.pop();
            string.append(temp.data);
            if(temp.right != null) {
                stack.push(temp.right);
            }
            if(temp.left != null){
                stack.push(temp.left);
            }
            if(!stack.isEmpty()) string.append(", ");
        }
        string.append("]");
        return string.toString();
    }

    // process left, current, right
    public String inOrder(){
        if(this.root == null) return "";
        StringBuilder string = new StringBuilder("[ ");

        Deque<node<T>> stack = new ArrayDeque<>();
        node<T> curr = this.root;

        while(curr != null || !stack.isEmpty()){
            if(curr != null){
                stack.push(curr);
                curr = curr.left;
            }
            else{
                curr = stack.pop();
                string.append(curr.data);
                curr = curr.right;
                if(curr != null || !stack.isEmpty())
                    string.append(", ");
            }
        }

        string.append("]");
        return string.toString();
    }

    // process left, right, root
    public String postOrder(){
        if(this.root == null) return "";
        StringBuilder string = new StringBuilder("[ ");

        Deque<node<T>> stack = new ArrayDeque<>();
        node<T> curr = this.root;

        while(curr != null || !stack.isEmpty()){
            if(curr != null){
                stack.push(curr);
                curr = curr.left;
            }
            else{
                curr = stack.pop();
                string.append(curr.data);
                curr = curr.right;
                if(curr != null || !stack.isEmpty())
                    string.append(", ");
            }
        }
        string.append("]");
        return string.toString();
    }
}
