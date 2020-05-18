package Miscellaneous.leetcode.Challenge30DaysApril.Week2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Week 2 day 4
 *
 */
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
public class BinaryTreeDiameter {
    private TreeNode root;
    private final Random random;
    private final StringBuilder stringBuilder;
    private final List<List<String>> list;
    public BinaryTreeDiameter(){
        this.root = null;
        this.random = new Random();
        this.stringBuilder = new StringBuilder();
        this.list = new ArrayList<>(Collections.nCopies(100, new ArrayList<>()));
    }
    public void insertInBinaryTreeLevelOrder(Integer data){
        if(this.root == null) {
            this.root = new TreeNode(data);
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            TreeNode temp = queue.poll();
            boolean leftSide = random.nextBoolean();
            if(leftSide){
                if(temp.left != null)
                    queue.add(temp.left);
                else{
                    temp.left = new TreeNode(data);
                    return;
                }
            }
            else{
                if(temp.right != null)
                    queue.add(temp.right);
                else{
                    temp.right = new TreeNode(data);
                    return;
                }
            }
        }
    }
    @Override
    public String toString(){
        getString(null, this.root, "root", 0);
        int counter = 0;
        for(int i = list.size() - 1; i >= 0; i--){
            List<String> insidelist = list.get(i);
            if(!insidelist.isEmpty()){
                String spaces = IntStream.range(0, counter).boxed().map(x -> " ").collect(Collectors.joining());
                StringBuilder what = new StringBuilder();
                String answer = String.join(spaces, insidelist);
                System.out.println(answer);
                counter++;
            }
        }
        return stringBuilder.toString();
    }

    private void getString(TreeNode parent, TreeNode node, String r, int stackdepth) {
        if(node == null) return;
        getString(node, node.left , "l", stackdepth + 1);
        String nodevalue = "(" + (parent == null ? "null" : parent.val) + ", " + node.val + ", " + r + ")";
        stringBuilder.append(nodevalue);
        List<String> templist = list.get(stackdepth);
        templist.add(nodevalue);
//        list.set(stackdepth, (list.get(stackdepth) != null)? list.get(stackdepth) + " " + nodevalue: nodevalue);
        getString(node, node.right, "r", stackdepth + 1);
    }

    public static void main(String[] args) {
        BinaryTreeDiameter ob = new BinaryTreeDiameter();
        for(int i = 0; i < 20; i++){
            ob.insertInBinaryTreeLevelOrder(i);
        }
        int value = ob.findLongestPath(ob.root);
        System.out.println(value);
        ob.toString();
    }

    private int findLongestPath(TreeNode root) {
        max = 0;
        count(root);
        return max;
    }
    private static int max;
    private int count(TreeNode node) {
        if(node == null) return 0;
        else{
            int leftvalue = count(node.left);
            int rightvalue = count(node.right);
            max = Math.max(max, leftvalue + rightvalue + 1);
            return Math.max(leftvalue, rightvalue) + 1;
        }
    }
}
