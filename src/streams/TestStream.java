package streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Student{

    List<String> studentNames;
    Student(List<String> list){
        this.studentNames = list;
    }

    public List<String> getStudentNames(){
        return studentNames;
    }
}
public class TestStream {
    private static void print(List<String> arr){
        arr.forEach(System.out::println);
    }
    public static void main(String[] args) {

        List<Student> arr = Arrays.asList(
                new Student(Arrays.asList("hello", "world")),
                new Student(Arrays.asList("Abhishek", "Mandal"))
        );

        arr.stream().map(Student::getStudentNames).forEach(TestStream::print);



        List<String> hello = new ArrayList<>();
        solve(hello);
        System.out.println(hello);
    }
    private static void solve(List<String> test){
        test.add("hello");
    }
}
