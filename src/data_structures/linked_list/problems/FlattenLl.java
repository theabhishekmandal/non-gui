package data_structures.linked_list.problems;


import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Given a Linked List of size N, where every node represents a sub-linked-list and contains two pointers:
 * (i) a next pointer to the next node,
 * (ii) a bottom pointer to a linked list where this node is head.
 * Each of the sub-linked-list is in sorted order.
 * Flatten the Link List such that all the nodes appear in a single level while maintaining the sorted order.
 * Note: The flattened list will be printed using the bottom pointer instead of next pointer.
 *
 *
 *
 * Example 1:
 *
 * Input:
 * 5 -> 10 -> 19 -> 28
 * |     |     |     |
 * 7     20    22   35
 * |           |     |
 * 8          50    40
 * |                 |
 * 30               45
 * Output:  5-> 7-> 8- > 10 -> 19-> 20->
 * 22-> 28-> 30-> 35-> 40-> 45-> 50.
 *
 * Note: | represents the bottom pointer
 *
 * Approach
 *  -   See below code to see the approach
 */

public class FlattenLl {
    public static void main(String[] args) {
        Bll bl = new Bll();
//        bl.insert(5, true);
//        bl.insert(6, false);
//        bl.insert(8, false);
//        bl.insert(30, false);
//
//        bl.insert(7, true);
//        bl.insert(20, false);
//
//        bl.insert(19, true);
//        bl.insert(22, false);
//        bl.insert(50, false);
//
//        bl.insert(28, true);
//        bl.insert(35, false);
//        bl.insert(40, false);
//        bl.insert(45, false);
        Random random = new Random();
        int n = 1 + random.nextInt(5);
        for (int i = 0; i < n; i++) {
            int m = 1 + random.nextInt(5);
            List<Integer> list = random.ints(0, 100)
                    .limit(m).boxed().sorted().collect(Collectors.toList());
            bl.insert(list.get(0), true);
            for (int j = 1; j < list.size(); j++) {
                bl.insert(list.get(j), false);
            }
        }
        System.out.println(bl);


        bl.head = flatten(bl.head);
        System.out.println(stringNodeFunction.apply(bl.head));
    }

    static Function<Node, String> stringNodeFunction = x -> {
        StringBuilder br = new StringBuilder();
        Node curr = x;
        while (curr != null) {
            br.append(curr.data).append(" ");
            curr = curr.bottom;
        }
        return br.toString();
    };

    private static Node flatten(Node root) {
        if (root == null) {
            return null;
        }
        if (root.next == null) {
            return root;
        }

        Node next;
        Node future;
        while (root.next != null) {
            next = root.next;
            future = next.next;

            next.next = null;
            root.next = future;
            Node bottom = next;
            Node bottomNext;
            Node curr = root;
            while (bottom != null) {
                bottomNext = bottom.bottom;
                Node prev = null;
                while (curr != null && curr.data < bottom.data) {
                    prev = curr;
                    curr = curr.bottom;
                }

                // if the head is greater than the bottom node then we have to replace it and it's next pointer
                if (prev == null) {
                    bottom.bottom = curr;
                    bottom.next = curr.next;
                    curr.next = null;
                    root = bottom;
                } else {
                    bottom.bottom = prev.bottom;
                    prev.bottom = bottom;
                }

                curr = bottom;
                bottom = bottomNext;
            }
        }
        return root;
    }


    public static class Node {
        int data;
        Node next;
        Node bottom;
        public Node(int data) {
            this.data = data;
        }
        public static Node of(int data) {
            return new Node(data);
        }
        @Override
        public String toString() {
            return String.valueOf(this.data);
        }
    }

    static class Bll {
        Node head;
        Node tail;
        Node bottom;
        public void insert(int data, boolean flag) {
            if(head == null) {
                head = Node.of(data);
                tail = head;
                bottom = tail;
            }
            else {
                if(flag) {
                    tail.next = Node.of(data);
                    tail = tail.next;
                    bottom = tail;
                }
                else {
                    bottom.bottom = Node.of(data);
                    bottom = bottom.bottom;
                }
            }
        }

        @Override
        public String toString() {
            Node temp = head;
            StringBuilder main = new StringBuilder();
            while(temp != null) {
                Node bottomTemp = temp.bottom;
                StringBuilder br = new StringBuilder();
                while(bottomTemp != null) {
                    br.append(bottomTemp.data).append(" ");
                    bottomTemp = bottomTemp.bottom;
                }
                main.append(temp.data).append("-->").append(br).append("\n");
                temp = temp.next;
            }
            return main.toString();
        }
    }
}
