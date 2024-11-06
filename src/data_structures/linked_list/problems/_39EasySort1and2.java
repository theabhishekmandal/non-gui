package data_structures.linked_list.problems;
/**
 * This program is an example of sorting elements of the linked list which can be 0, 1 and 2 integers in a single
 * traversal and without using extra space and without using node values and only by changing the links.
 *
 * The idea is that we will use three pointers namely zero, one and two. we will traverse the linked list and while
 * traversing we will add the elements to their respective pointers. And after that we will join zero, one and two
 * linked list in the same order
 */

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;
public class _39EasySort1and2 {

    // static class containing the properties of the doubly linked list
    static class Node {
        int data;
        Node next;
        Node prev;
        Node(int data){
            this.data = data;
            this.next = this.prev = null;
        }
    }
    // head of the linked list
    private static Node root;

    // method to add the elements in the linked list
    private static void append(int data){
        if(root == null){
            root = new Node(data);
            return;
        }
        Node temp = root;
        while(temp.next != null) temp = temp.next;
        Node temp2 = new Node(data);
        temp.next = temp2;
        temp2.prev = temp;
    }
    // to display the elements of the linked list
    public static String show() {
        StringBuilder br = new StringBuilder();
        Node temp = root;
        while (temp != null) {
            br.append(temp.data).append(" ");
            temp = temp.next;
        }
        return br.toString();
    }
    /*
    This method will use three pointers namely zero, one and two. In a single traversal when an element is
    encountered then it is added to its separate linked list.

    For eg: if an element is 0, then that particular node is removed and then added to zero pointer's linked list
    similarly, if the encountered element is 1 then it is added into the one pointer's linked list.
     */
    private static void sort(){
        Node zero;
        Node one;
        Node two;
        // Initially all the pointers are null
        zero = one = two = null;

        // temporary variable to point the head of the linked list
        Node temp = root;

        // traversing the list is not empty
        while(temp != null){
            // temporary node to store the current node
            Node blah = temp;

            // jumping to the next node
            temp = temp.next;

            // if the current node is null then we cannot use it's previous value
            // if the current node is not null then dereference its previous value
            if(temp != null)
                temp.prev = null;

            // dereferencing  of the selected node from the main list and
            // add it to it's matching linked list
            blah.next = null;
            if(blah.data == 0){
                zero = add(zero, blah);
            }
            else if(blah.data == 1){
                one = add(one, blah);
            }
            else{
                two = add(two, blah);
            }
        }
        // adding all the list again in a single linked list in increasing order of elements
        linkAll(zero, one, two);
    }

    // adding the node by changing the links to a separate linked list
    private static Node add(Node foo, Node data){
        if(foo == null){
            foo = data;
        }
        else {
            Node fooend = foo;
            while(fooend.next != null)
                fooend = fooend.next;
            fooend.next = data;
            data.prev = fooend;
        }
        return foo;
    }

    // method to link zero, one and two linked list in a single linked list in an increasing order of elements
    private static void linkAll(Node zero, Node one, Node two){
        if(zero == null || one == null || two == null) {
            return;
        }
        root = zero;
        while(zero.next != null) zero = zero.next;
        zero.next = one;
        while(one.next != null) one = one.next;
        one.next = two;
    }
    public static void main(String[] args){
        Scanner s = new Scanner(in);
        // number of elements in the linked list
        int n = s.nextInt();

        // entering the elements of the linked list
        while(n-- > 0) append(s.nextInt());
        out.println(show());

        // sorting in increasing order of elements
        sort();

        // print values after sorting
        out.println(show());
    }
}
