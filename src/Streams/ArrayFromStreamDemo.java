package Streams;



import java.util.List;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayFromStreamDemo {
    /*
    If we need to get an array out of the stream, we can simply use toArray()
     */

    public static void main(String[] args) {

        List<Employee> arr = new ArrayList<>();
        arr.add(new Employee(1, "Abhishek", 100000.0));
        arr.add(new Employee(2, "Mandal", 100000.0));
        arr.add(new Employee(3, "Amit", 200000.0));

        // Using toArray to convert stream to Array
        Employee[] empArray = arr.stream().toArray(Employee[]::new);
        Arrays.stream(empArray)
                .forEach(System.out::println);
    }
}
