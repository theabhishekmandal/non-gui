package DataStructures.linked_list;

import java.util.*;
import static java.lang.System.*;
public class Adding_numbers {
    public static void main(String[] args) {
        Scanner s = new Scanner(in);
        // splitting the string on the basis of '+'
        String[] one = s.next().split("\\+");

        // converting both the values to int
        int first = Integer.parseInt(one[0]);
        int second = Integer.parseInt(one[1]);

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

        // temporaray node to swap the larger number with the smaller number
        // num1 will contain larger number and num2 will contain smaller number
        LinkedList<Integer> temp = null;
        if(num1.size() < num2.size()){
            temp = num1;
            num1 = num2;
            num2 = temp;
        }

        // going from 0 to min(num1.size(), num2.size())
        int i = 0;
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
            }
        }

        // reversing the linked list and printing the result
        Collections.reverse(num1);
        out.println(Arrays.toString(num1.toArray()));
    }
}
