package data_structures.linked_list.problems;

import data_structures.linked_list.node.Sl;

import java.util.stream.Stream;

import static data_structures.linked_list.node.Sl.Node;

/**
 * Given a linked list remove all the nodes which have greater value on the right side.
 * Example 1:
 *
 * Input:
 * LinkedList = 12->15->10->11->5->6->2->3
 * Output: 15 11 6 3
 * Explanation: Since, 12, 10, 5 and 2 are
 * the elements which have greater elements
 * on their next node. So, after deleting
 * them, the linked list would like be 15,
 * 11, 6, 3.
 *
 * Example 2:
 *
 * Input:
 * LinkedList = 10->20->30->40->50->60
 * Output: 60
 *
 * Approach
 *  -   Just reverse the linked list and remove the nodes which are smaller
 *  -   In the end, just reverse the list once again to get the original list order back.
 */

public class _7EasyDeleteNodesOnRight {
    public static void main(String[] args) {
        Sl sl = new Sl();
//        String str = "32 433 636 121 831 102 695 509 104 776 905 876 219 202 326 393 141 752 504 109 640 765 537 759 971 857 147 716 897 484 60 928 268 47 48 98 500 94 607 603 221 863 831 439 416 156 183 556 259 686 16 250 450 552 9 772 409 155 487 305 990 898 232 258 945 631 707 444 724 313 47 296 527 229 734 942 736 268 849 995 953 865 244 754 416 604 526 176 110 364 832 100 262";
//        Arrays.stream(str.split(" ")).map(Integer::parseInt).forEach(sl::insert);
//        Stream.of(12, 11, 15, 10, 9, 16, 5, 6, 2, 3).forEach(sl::insert);
        Stream.of(12, 15, 10, 11, 5, 6, 2, 3).forEach(sl::insert);
        System.out.println(sl);
        sl.head = deleteNodeIfGreaterOnRight(sl.head);
        System.out.println(sl);
    }

    private static Node deleteNodeIfGreaterOnRight(Node head) {
        if (head == null) {
            return null;
        }
        head = reverse(head);
        Node curr = head;
        Node next;
        while (curr.next != null) {
            next = curr.next;
            if (next.data < curr.data) {
                curr.next = next.next;
                next.next = null;
            } else {
                curr = next;
            }
        }
        return reverse(head);
    }

    private static Node reverse(Node head) {
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
        head = prev;
        return head;
    }
}
