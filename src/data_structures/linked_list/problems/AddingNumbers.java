package data_structures.linked_list.problems;

import data_structures.linked_list.node.SinglyLinkedList;
import static data_structures.linked_list.node.SinglyLinkedList.Node;

import java.util.*;
import static java.lang.System.*;

/**
 * Given two number a and b, add them using linked list.
 */
public class AddingNumbers {
    public static void main(String[] args) {
        Random random = new Random();
        // splitting the string on the basis of '+'
        String[] one = new String[]{String.valueOf(random.nextInt(1000)),  String.valueOf(random.nextInt(1000))};

        out.println(Arrays.toString(one));
        // converting both the values to int
        int first = Integer.parseInt(one[0]);
        int second = Integer.parseInt(one[1]);
        out.println(solve(first, second));
        out.println(solveSecond(first, second));

    }

    private static String solve(int first, int second){
        // creating linked list for the two numbers
        LinkedList<Integer> num1 = new LinkedList<>();
        LinkedList<Integer> num2 = new LinkedList<>();

        // adding numbers in reverse order for making addition easy
        while(first > 0) {
            num1.add(first % 10);
            first /= 10;
        }
        while(second  > 0){
            num2.add(second % 10);
            second /= 10;
        }
        // carry generated during addition
        int carry = 0;

        // temporary node to swap the larger number with the smaller number
        // num1 will contain larger number and num2 will contain smaller number
        LinkedList<Integer> temp = null;
        if(num1.size() < num2.size()){
            temp = num1;
            num1 = num2;
            num2 = temp;
        }

        // going from 0 to min(num1.size(), num2.size())
        int i;
        for(i = 0; i < num2.size(); i++){
            int foo = carry + num1.get(i) + num2.get(i);
            carry = foo / 10;
            num1.set(i, foo % 10);
        }
        // if at last carry is generated
        // check whether you can add that carry to remaining elements of the list
        // if not then create a new node and add the carry
        while(carry > 0){
            if(i < num1.size()){
                int foo = carry + num1.get(i);
                carry = foo / 10;
                num1.set(i++, foo % 10);
            }
            else{
                num1.add(carry);
                // make carry zero after use
                carry = 0;
            }
        }

        // reversing the linked list and printing the result
        StringBuilder br = new StringBuilder("[");
        boolean firstTime = true;
        while(!num1.isEmpty()){
            if(firstTime){
                firstTime = false;
                br.append(num1.removeLast());
            }
            else{
                br.append(", ").append(num1.removeLast());
            }
        }
        br.append("]");
        return br.toString();
    }

    private static String solveSecond(int one, int two){

        SinglyLinkedList<Integer> first = new SinglyLinkedList<>();
        SinglyLinkedList<Integer> second = new SinglyLinkedList<>();

        while(one > 0){
            first.addLast(one % 10);
            one /= 10;
        }
        while(two > 0){
            second.addLast(two % 10);
            two /= 10;
        }

        if(second.getSize() > first.getSize()){
            SinglyLinkedList<Integer> temp = null;
            temp = first;
            first = second;
            second = temp;
        }

        int counter = 0;
        int carry = 0;
        int min = Math.min(first.getSize(), second.getSize());
        int max = Math.max(first.getSize(), second.getSize());
        Node<Integer> firstnode = first.getHead();
        Node<Integer> secondnode = second.getHead();

        while(counter < max || carry > 0){
            if(counter < max){
                int sum = carry + firstnode.getData();
                if(counter < min) {
                    sum += secondnode.getData();
                    secondnode = secondnode.getNext();
                }
                firstnode.setData(sum % 10);
                carry = sum / 10;
                firstnode = firstnode.getNext();
                counter++;
            }
            else{
                first.addLast(carry);
                carry = 0;
            }
        }

        StringBuilder br = new StringBuilder("[");
        boolean firstTime = true;
        while(firstnode != null){
            if(firstTime){
                firstTime = false;
                br.append(firstnode.getData());
            }
            else{
                br.append(", ").append(firstnode.getData());
            }
            firstnode = firstnode.getNext();
        }
        br.append("]");
        return br.toString();
    }
}
