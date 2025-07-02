import java.util.*;
import java.util.stream.Collectors;

public class AVLTree {
    static class Node {
        int data;
        int height;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.height = 1;
        }
    }


    private Node root;
    private int size;

    private void insert(int data) {
        this.root = insert(root, data);
    }


    private Node insert(Node node, int data) {
        if (node == null) {
            this.size++;
            return new Node(data);
        }

        if (node.data < data) {
            node.right = insert(node.right, data);
            if (checkBalance(node) == 2) {
                if (getHeight(node.right.right) > getHeight(node.right.left)) {
                    node = llRotate(node);
                } else {
                    node = rlRotate(node);
                }
            }
        }
        else if (node.data > data) {
            node.left = insert(node.left, data);
            if (checkBalance(node) == 2) {
                if (getHeight(node.left.left) > getHeight(node.left.right)) {
                    node = rrRotate(node);
                } else {
                    node = lrRotate(node);
                }
            }
        } else {
            return node;
        }
        node.height = Math.max(getHeight(node.right), getHeight(node.left)) + 1;
        return node;
    }

    private Node llRotate(Node node) {
        Node right = node.right;
        node.right = right.left;
        right.left = node;
        return right;
    }

    private Node rrRotate(Node node) {
        Node left = node.left;
        node.left = left.right;
        left.right = node;
        return left;
    }

    private Node rlRotate(Node node) {
        node.right = rrRotate(node.right);
        return llRotate(node);
    }

    private Node lrRotate(Node node) {
        node.left = llRotate(node.left);
        return rrRotate(node);
    }

    private int checkBalance(Node node) {
        return Math.abs(getHeight(node.left) - getHeight(node.right));
    }

    private int getHeight(Node node) {
        return node == null? 0 : node.height;
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

    private List<List<String>> levelOrderPrivate() {
        if(this.root == null) {
            return Collections.emptyList();
        }
        List<List<String>> finalAnswer = new ArrayList<>();
        List<String> tempList = new ArrayList<>();

        Deque<Node> queue = new LinkedList<>();
        queue.add(this.root);

        Node emptyNode = new Node(0);

        Node curr;
        while(!queue.isEmpty()) {
            int size = queue.size();
            tempList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                curr = queue.poll();
                if(curr != emptyNode) {
                    tempList.add(curr.data + "");
                    if (curr.left != null) {
                        queue.add(curr.left);
                    } else {
                        queue.add(emptyNode);
                    }

                    if (curr.right != null) {
                        queue.add(curr.right);
                    } else {
                        queue.add(emptyNode);
                    }
                }
                else {
                    tempList.add("*");
                }
            }
            finalAnswer.add(tempList);
        }
        return finalAnswer;
    }

    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();

        List<List<Integer>> listOfList = List.of(
//                List.of(10, 20, 30),
//                List.of(30, 20, 10),
                List.of(20, 4, 60, 50, 40),
                List.of(60, 40, 70, 20, 50, 55)
        );

        for (var list : listOfList) {

            System.out.println("\n\nnew tree printing");
            for (var i : list) {
                avlTree.insert(i);
                System.out.println(avlTree.levelOrderPretty());
            }

            avlTree.root = null;
            avlTree.size = 0;
        }
    }
}
