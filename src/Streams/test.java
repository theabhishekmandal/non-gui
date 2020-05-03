package Streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Student{

    List<String> studentNames;
    Student(List<String> list){
        this.studentNames = list;
    }

    public List<String> getStudentNames(){
        return studentNames;
    }
}
public class test {
    private static void print(List<String> arr){
        arr.forEach(System.out::println);
    }
    public static void main(String[] args) {

        List<Student> arr = Arrays.asList(
                new Student(Arrays.asList("hello", "world")),
                new Student(Arrays.asList("Abhishek", "Mandal"))
        );

        arr.stream().map(x -> x.getStudentNames()).forEach(test::print);



        List<String> hello = new ArrayList<>();
        solve(hello);
        System.out.println(hello);
    }
    private static void solve(List<String> test){
        test.add("hello");
        return;
    }
}
