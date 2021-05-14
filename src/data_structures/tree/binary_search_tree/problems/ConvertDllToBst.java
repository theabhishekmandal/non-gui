package data_structures.tree.binary_search_tree.problems;

import data_structures.Pair;
import data_structures.linked_list.node.DoublyLinkedList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.IntStream;

import static data_structures.linked_list.node.DoublyLinkedList.Node;

/**
 * Given a sorted doubly linked list convert it into a Binary Search tree
 * Things to Remember:
 *  -   a doubly linked list's previous and next pointers will be used as left and right pointers.
 *  -   use of binary order is preferred i.e getting the middle node of the list, and using it as current head
 *  -   inclusive low and exclusive high is used in finding the mid index
 *  -   using preorder approach to construct BST from top to bottom
 *
 *  Approach
 *  -   Traverse the linked list and store all the nodes in an array
 *  -   Now, find the head which will become bst head using size / 2;
 *  -   now using a stack push Pair.of(currentNode, Pair.of(0, size)) and why we are pushing this type of pair?
 *          -   currentNode will be used to change the left and right pointers.
 *          -   Pair.of(0, size) is pushed because this will be used to calculate the next left and right
 *              element of the current node
 *  -   Now similarly we will try to push left and and right nodes in the form of Pair.of(node, Pair.of(low/mid + 1, mid/high)
 *  -   a left child is only allowed to push if (mid - low >= 1) and a right child is only to push if (high - (mid + 1) >= 0)
 *      and we are doing this because we don't want to reach the leaves.
 *
 *  Example:
 *  index     0, 1, 2, 3, 4, 5, 6
 *  values   [1, 2, 3, 4, 5, 6, 7]
 *
 *  -   in left side mid becomes the new high and in right side (mid + 1) becomes the new low
 *  -   we can see the middle index is giving which node should become the next right or left child
 *  -   also we are not traversing further if (high - mid + 1) and (mid - low) < 1
 *                        low high mid
 *                        (0, 7, 3)
 *            (0, 3, 1)                (4, 7, 5)
 *     (0, 1, 0)    (2, 3, 2)    (4, 5, 4)     (6, 7, 6)
 *
 */

public class ConvertDllToBst {
    public static void main(String[] args) {
        var evenLengthList = new DoublyLinkedList<Integer>();
        var oddLengthList = new DoublyLinkedList<Integer>();
        IntStream.rangeClosed(1, 6).forEach(x -> {
            evenLengthList.addLast(x);
            oddLengthList.addLast(x);
        });
        oddLengthList.addLast(oddLengthList.getTail().getData());

        System.out.println(getInorderString(getBstFromDll(evenLengthList.getHead())));
        System.out.println(getInorderString(getBstFromDll(oddLengthList.getHead())));
    }

    private static int getMiddle(int low, int high) {
        return low + ((high - low) >> 1);
    }

    private static <T> Node<T> getBstFromDll(Node<T> node) {
        if(node == null) {
            return null;
        }
        var listOfNodes = new ArrayList<Node<T>>();
        var tempHead = node;
        while(tempHead != null) {
            listOfNodes.add(tempHead);
            tempHead = tempHead.getNext();
        }
        var head = listOfNodes.get(getMiddle(0, listOfNodes.size()));

        var currPair = Pair.of(head, Pair.of(0, listOfNodes.size()));
        var stack = new LinkedList<Pair<Node<T>, Pair<Integer, Integer>>>();
        stack.push(currPair);
        while(!stack.isEmpty()) {
           var tempPair = stack.pop();
           int low = tempPair.getSecond().getFirst();
           int high = tempPair.getSecond().getSecond();
           int mid = getMiddle(low, high);
           Node<T> temp = tempPair.getFirst();
           temp.setNext(null);
           temp.setPrevious(null);
           if(mid - low >= 1) {
              var leftNode = listOfNodes.get(getMiddle(low, mid));
              temp.setPrevious(leftNode);
              stack.push(Pair.of(leftNode, Pair.of(low, mid)));
           }
           if(high - (++mid) >= 1) {
               var rightNode = listOfNodes.get(getMiddle(mid, high));
               temp.setNext(rightNode);
               stack.push(Pair.of(rightNode, Pair.of(mid, high)));
           }
        }
        return head;
    }


    private static <T> String getInorderString(Node<T> head) {
        if(head == null) {
            return "[]";
        }
        StringBuilder br = null;
        var stack = new LinkedList<Node<T>>();
        var curr = head;
        while(curr != null || !stack.isEmpty()) {
            if(curr != null) {
                stack.push(curr);
                curr = curr.getPrevious();
            }
            else {
                curr = stack.pop();
                if(br == null) {
                    br = new StringBuilder("[").append(curr.getData());
                }
                else {
                    br.append(", ").append(curr.getData());
                }
                curr = curr.getNext();
            }
        }
        return br == null ? "[]" : br.append("]").toString();
    }
}
