package data_structures.linked_list.problems;

import data_structures.linked_list.node.Sl;

import java.util.Random;

import static data_structures.linked_list.node.Sl.Node;

/**
 * Given two linked lists multiply them and return the result.
 * Approach:
 * -   Reverse both of the linked lists
 * -   for each node of first list, multiply it with each node in the other list and create a new list
 * -   return the answer list by reversing it again
 */

public class _24MediumMultiplyTwoLL {
    public static void main(String[] args) {
        Sl one = new Sl();
        Sl two = new Sl();
        Sl ans = new Sl();
        Random random = new Random();
        int t = 1;
        while (t-- > 0) {
            int n = 999;
            int m = 999;
            convertAndAdd(one, n);
            convertAndAdd(two, m);
            System.out.println("n = " + n + " m = " + m);
            ans.head = multiplyTwoll(one.head, two.head);
            System.out.println("actual answer = " + n * m + " observed answer is = " + ans);

            one.head = null;
            two.head = null;
        }
    }


    public static Node multiplyTwoll(Node one, Node two) {
        // handle edge cases, not handled here

        // revering the linked list, since multiplication starts from the last
        one = reverse(one);
        two = reverse(two);
        int carry = 0;

        Node head = null;
        Node tail = null;
        Node level = null;
        Node levelPointer = null;

        Node lowerPointer = two;
        /*
            In multiplication for every lowerPointer, we iterate upperPointer
            98 <- upperPointer
            98 <- lowerPointer
            --
            784

            -   There are two types of carry here
                -   one is multiplication carry -> 8 * 8 = 64 -> 6 is carry 4 is remainder
                -   other is addition carry -> lower 9 * upper 8 = 72 -> 7 is carry 2 is remainder, then we add this remainder to 8
                                               i.e 2 + 8 =  10 -> 1 carry and 0 rem -> in this case extra carry is added to 7, 7 + 1 = 8

         */
        for (lowerPointer = one; lowerPointer != null; lowerPointer = lowerPointer.next) {
            for (Node upperPointer = two; upperPointer != null; upperPointer = upperPointer.next) {
                carry = carry + (upperPointer.data * lowerPointer.data);
                int remainder = carry % 10;
                carry /= 10;

                Node result = Node.of(remainder);
                if (head == null) {
                    head = result;
                    tail = result;
                    level = result;
                } else {
                    // because we want to add the result with current node
                    if (levelPointer != null) {
                        int temp = (levelPointer.data + result.data);
                        levelPointer.data = temp % 10;
                        carry = carry + (temp / 10);
                        levelPointer = levelPointer.next;
                    } else {
                        tail.next = result;
                        tail = tail.next;
                    }
                }
            }
            if (carry != 0) {
                tail.next = Node.of(carry);
                tail = tail.next;
                carry = 0;
            }

            if (level != null) {
                level = level.next;
                levelPointer = level;
            }
        }
        return reverse(head);
    }

    private static Node reverse(Node one) {
        Node prev = null, curr = one, nex = null;
        while (curr != null) {
            nex = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nex;
        }
        return prev;
    }

    private static void convertAndAdd(Sl sl, int m) {
        String value = String.valueOf(m);
        for (char c : value.toCharArray()) {
            sl.insert(c - '0');
        }
    }
}
