package miscellaneous.leetcode.challenge30_days_april.week2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Week 2 day 4
 *
 */
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
        getString(node, node.right, "r", stackdepth + 1);
    }

    public static void main(String[] args) {
        BinaryTreeDiameter ob = new BinaryTreeDiameter();
        for(int i = 0; i < 3; i++){
            ob.insertInBinaryTreeLevelOrder(i);
        }
        int result = getResult(ob.root);
        System.out.println(result);
    }
    private static int value;
    private static  int getResult(TreeNode node){
        value = 1;
        getAnswer(node);
        return value - 1;
    }
    private static int getAnswer(TreeNode node){
    if(node == null) return 0;
        int left = getAnswer(node.left);
        int right = getAnswer(node.right);
        value = Math.max(value, left + right + 1);
        return Math.max(left, right) + 1;
    }
}

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
