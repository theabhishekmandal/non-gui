package collection_implementation.UserDefinedClass;

/**
 *  This program is an implementation of storing an user defined class inside the collection.
 *  Collections have the ability to store user defined class other than the built-in objects such as
 *  String or Integer.
 *
 *  Below is an implementation of LinkedList storing user defined object.
 */

import java.util.*;
class Address{
    private String name;
    private String street;
    private String city;
    private String state;
    Address(String name, String street, String city, String state){
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
    }
    @Override
    public String toString(){
        return this.name + "\n" + this.street + "\n" + this.city + "\n" + this.state + "\n";
    }
}
public class UserDefinedClassDemo {
    public static void main(String[] args) {
        LinkedList<Address> arr = new LinkedList<>();

        // Adding elements to linkedlist
        arr.add(new Address("J.W West", "ll Oak Ave", "Urbana", "IL"));
        arr.add(new Address("Ralph Baker", "1142 Maple Lane", "Mahomet", "IL"));
        arr.add(new Address("Tom Carlton", "867 Elm st", "Champaign", "IL"));

        // Display the mailing list
        for(Address a : arr){
            System.out.println(a + "\n");
        }
    }
}
