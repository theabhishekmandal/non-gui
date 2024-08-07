package data_structures.linked_list.problems;

import data_structures.linked_list.node.SinglyLinkedList;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.StringJoiner;

import static java.lang.System.out;

/**
 * Given two number a and b, add them using linked list.
 */
public class _2EasyAddingNumbers {
    public static void main(String[] args) {
        var random = new Random();
        // splitting the string on the basis of '+'
        var one = new String[]{String.valueOf(random.nextInt(1000)), String.valueOf(random.nextInt(1000))};

        out.println(Arrays.toString(one));
        // converting both the values to int
        var first = Integer.parseInt(one[0]);
        var second = Integer.parseInt(one[1]);
        out.println(solve(first, second));
        out.println(solveSecond(first, second));

    }

    private static String solve(int first, int second) {
        // creating linked list for the two numbers
        var num1 = new LinkedList<Integer>();
        var num2 = new LinkedList<Integer>();

        // adding numbers in reverse order for making addition easy
        while (first > 0) {
            num1.add(first % 10);
            first /= 10;
        }
        while (second > 0) {
            num2.add(second % 10);
            second /= 10;
        }
        // carry generated during addition
        var carry = 0;

        // temporary node to swap the larger number with the smaller number
        // num1 will contain larger number and num2 will contain smaller number
        LinkedList<Integer> temp;
        if (num1.size() < num2.size()) {
            temp = num1;
            num1 = num2;
            num2 = temp;
        }

        // going from 0 to min(num1.size(), num2.size())
        int i;
        for (i = 0; i < num2.size(); i++) {
            int foo = carry + num1.get(i) + num2.get(i);
            carry = foo / 10;
            num1.set(i, foo % 10);
        }
        // if at last carry is generated
        // check whether you can add that carry to remaining elements of the list
        // if not then create a new node and add the carry
        while (carry > 0) {
            if (i < num1.size()) {
                int foo = carry + num1.get(i);
                carry = foo / 10;
                num1.set(i++, foo % 10);
            } else {
                num1.add(carry);
                // make carry zero after use
                carry = 0;
            }
        }

        // reversing the linked list and printing the result
        var joiner = new StringJoiner(", ", "[", "]");
        while (!num1.isEmpty()) {
            joiner.add(String.valueOf(num1.removeLast()));
        }
        return joiner.toString();
    }

    private static String solveSecond(int one, int two) {

        var first = new SinglyLinkedList<Integer>();
        var second = new SinglyLinkedList<Integer>();

        while (one > 0) {
            first.addLast(one % 10);
            one /= 10;
        }
        while (two > 0) {
            second.addLast(two % 10);
            two /= 10;
        }

        if (second.getSize() > first.getSize()) {
            var temp = first;
            first = second;
            second = temp;
        }

        var counter = 0;
        var carry = 0;
        var min = Math.min(first.getSize(), second.getSize());
        var max = Math.max(first.getSize(), second.getSize());
        var firstNode = first.getHead();
        var secondNode = second.getHead();

        while (counter < max || carry > 0) {
            if (counter < max) {
                int sum = carry + firstNode.getData();
                if (counter < min) {
                    sum += secondNode.getData();
                    secondNode = secondNode.getNext();
                }
                firstNode.setData(sum % 10);
                carry = sum / 10;
                firstNode = firstNode.getNext();
                counter++;
            } else {
                first.addLast(carry);
                carry = 0;
            }
        }

        var joiner = new StringJoiner(", ", "[", "]");
        while (firstNode != null) {
            joiner.add(String.valueOf(firstNode.getData()));
            firstNode = firstNode.getNext();
        }
        return joiner.toString();
    }
}
