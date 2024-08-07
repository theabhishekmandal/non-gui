package data_structures.linked_list.problems;

import data_structures.linked_list.node.SinglyLinkedList;

import static data_structures.linked_list.node.SinglyLinkedList.Node;

/**
 * Given a linked list find that if a loop exists, and remove the loop
 *
 *  -   To find a loop in a linked list, we use two pointers fastPointer and slowPointer
 *  -   fastPointer moves 2 steps at a time while slowPointer moves 1 step at a time.
 *  -   There will be a point when these two points meet, there by declaring that there
 *      exists loop in the linkedList.
 *
 *  -   To remove the cycle from a linked list we use floyd algorithm.
 *  -   In this we move the slowPointer to the starting of the list and
 *      the fastPointer remains at the meeting point.
 *  -   Then we move each Pointer by 1 step and check if they are pointing to same
 *      node or not.
 *  -   When they both meet, that point is the end of the list and we change the next
 *      Pointer of the tail, to remove the loop.
 *
 *      Why this works?
 *      ----d--- -y-- --z--
 *      1 - 2 - 3 - 4 - 5
 *              \_ _ _ _|
 *
 *      d = distance from head to the starting of the loop
 *      y = distance from the start of the looping point to the point
 *          where fastPointer and slowPointer meet
 *      z = distance from meeting point to the start of the looping point
 *
 *      distance travelled by slowPointer to meet at meeting point = d + y
 *      distance travelled by fastPointer to meet at meeting point= d + y + z + y
 *
 *      2 * dist by slow Pointer = dist by fastPointer
 *      2 * (d + y) = d + 2y + z
 *      2d +  2y = d + 2y + z
 *      d = z
 *
 *      d = z means distance from the MEETING POINT to the LOOP STARTING POINT
 *          and HEAD to LOOP STARTING POINT are SAME.
 */

public class _8EasyDetectingCycle {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> arr = new SinglyLinkedList<>();
        for(int i = 0; i < 10; i++) arr.addLast(i);
        for(int i = 1; i < 11; i++){
            SinglyLinkedList<Integer> temp = new SinglyLinkedList<>();
            temp.copyAll(arr);
            arr.getTail().setNext(arr.getNode(i));
            detectAndRemoveCycle(arr);
            arr = temp;
        }
    }
    private static void detectAndRemoveCycle(SinglyLinkedList<?> arr){
        Node<?> fastNode = arr.getHead();
        Node<?> slowNode = arr.getHead();
        boolean isCycle = false;
        while(fastNode != null && fastNode.getNext() != null){
            fastNode = fastNode.getNext().getNext();
            slowNode = slowNode.getNext();
            if(fastNode == slowNode){
                isCycle = true;
                break;
            }
        }
        if(isCycle){
            slowNode = arr.getHead();
            // if the linked list is a circular linked list
            if(fastNode == slowNode){
                while(fastNode.getNext() != slowNode) fastNode = fastNode.getNext();
            }
            // if the linked list contains a circle
            else{
                while(fastNode.getNext() != slowNode.getNext()){
                    fastNode = fastNode.getNext();
                    slowNode = slowNode.getNext();
                }
            }
            fastNode.setNext(null);
        }
        System.out.println(arr);
    }
}
