package data_structures.linked_list.problems;

import data_structures.linked_list.node.Sl;

import java.util.Random;
import java.util.function.UnaryOperator;

import static data_structures.linked_list.node.Sl.Node;

/**
 * Given two linked lists multiply them and return the result.
 * Approach:
 *  -   Reverse both of the linked lists
 *  -   for each node of first list, multiply it with each node in the other list and create a new list
 *  -   return the answer list by reversing it again
 */

public class MultiplyTwoLL {
    public static void main(String[] args) {
        Sl one = new Sl();
        Sl two = new Sl();
        Sl ans = new Sl();
        Random random = new Random();
        int t = 4;
        while (t-- > 0) {
            int n = 1 + random.nextInt(1000);
            int m = 1 + random.nextInt(1000);
            convertAndAdd(one, n);
            convertAndAdd(two, m);
            System.out.println("n = " + n + " m = " + m);
            ans.head = multiplyTwoLl(one.head, two.head);
            System.out.println("actual answer = " + n * m + " observed answer is = " + ans);

            one.head = null;
            two.head = null;
        }
    }

    private static Node multiplyTwoLl(Node one, Node two) {

        if (one == null && two == null) {
            return null;
        }

        UnaryOperator<Node> reverse = head -> {
            if (head == null) {
                return null;
            }
            Node prev = null;
            Node curr = head;
            Node next;
            while (curr != null) {
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            return prev;
        };

        one = reverse.apply(one);
        two = reverse.apply(two);

        Node ans = null;
        Node ansTemp = null;
        Node level = null;
        Node twoPointer = two;
        Node levelPointer = null;
        Node onePointer;
        int carry = 0;


        while (twoPointer != null) {
            int digit = twoPointer.data;
            onePointer = one;

            // increase the level after multiplying each digit
            if (level != null) {
                level = level.next;
                levelPointer = level;
            }

            while (onePointer != null) {
                int temp = carry + (digit * onePointer.data);
                carry = temp / 10;
                int rem = temp % 10;


                if (ans == null) {
                    ans = Node.of(rem);
                    ansTemp = ans;
                    level = ans;
                } else {
                    if (levelPointer == null) {
                        ansTemp.next = Node.of(rem);
                        ansTemp = ansTemp.next;
                    } else {
                        int temm = levelPointer.data + rem;
                        carry = carry + temm / 10;
                        levelPointer.data = temm % 10;
                        levelPointer = levelPointer.next;
                    }
                }

                onePointer = onePointer.next;
            }

            if (carry > 0) {
                ansTemp.next = new Node(carry);
                ansTemp = ansTemp.next;
                carry = 0;
            }

            twoPointer = twoPointer.next;
        }
        return reverse.apply(ans);
    }

    private static void convertAndAdd(Sl sl, int m) {
        String value = String.valueOf(m);
        for (char c : value.toCharArray()) {
           sl.insert(c - '0');
        }
    }
}
