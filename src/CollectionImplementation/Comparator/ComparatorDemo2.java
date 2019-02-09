package CollectionImplementation.Comparator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;
import static java.lang.System.*;

/**
 * This is an implementation on how differently a comparator is defined
 * 1 Using Lambda Expressions
 * 2 Using without Lambda Expressions
 * 3 Using Comparator.Comparing
 *
 * Also, different methods defined in comparator interface
 *
 * 1 static <T> Comparator<T>
 *   nullsFirst(Comparator
 *   <? super T> comp)                  :   this returns a comparator that views null values as less than
 *                                          other values
 *
 * 2 static <T> Comparator<T>
 *   nullsLast(Comparator
 *   <? super T> comp)                  :   this returns a comparator that views null values as greater than
 *                                          other values
 */

// A Student class implementing the Comparator interface to sort in descending order
class Student{
    private String name;
    private int number;

    Student(String name, int number){
        this.name = name;
        this.number = number;
    }

    public String getname(){
        return this.name;
    }

    public int getnumber(){
        return this.number;
    }
    @Override
    public String toString(){
        return "[" + this.getnumber() + " " + this.getname()  + "]";
    }
}

public class ComparatorDemo2 {
    public static void main(String[] args) {

        out.println("First way of Defining a comparator using lambda expression");
        // Remember, to Store a user defined class object, TreeSet should
        // define its comparator while declaring it
        // defining the comparator to store elements in descending order
        // this comparator will sort them on the basis of Integer values
        TreeSet<Student> student = new TreeSet<>((Student one, Student two) -> {
            Integer valone = one.getnumber();
            Integer valtwo = two.getnumber();
            return -valone.compareTo(valtwo);
        });
        student.add(new Student("Mokton", 1));
        student.add(new Student("Doton", 2));
        student.add(new Student("Raiton", 3));
        student.add(new Student("Shuton", 4));
        student.add(new Student("Fuuton", 5));
        student.add(new Student("Khaton", 6));
        out.println(student);
        out.println();


        out.println("Second way of defining a comparator separately to store the elements in descending order");
        // Without Lambda Expression
        // Sorting based on String values
        Comparator<Student> studentcomparator1 = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o2.getname().compareTo(o1.getname());
            }
        };
        TreeSet<Student> student2 = new TreeSet<>(studentcomparator1);
        student2.add(new Student("Mokton", 1));
        student2.add(new Student("Doton", 2));
        student2.add(new Student("Raiton", 3));
        student2.add(new Student("Shuton", 4));
        student2.add(new Student("Fuuton", 5));
        student2.add(new Student("Khaton", 6));
        out.println(student2);
        out.println();


        out.println("Third way of defining a comparator");
        Comparator<Student> studentComparator2 = Comparator.comparing(Student::getnumber);
        ArrayList<Student> stud = new ArrayList<>();
        stud.add(new Student("Delhi", 1));
        stud.add(new Student("Mumbai", 2));
        stud.add(new Student("Chennai", 3));
        stud.add(new Student("kolkata", 4));
        stud.add(new Student("kanpur", 5));
        Collections.sort(stud, Collections.reverseOrder(studentComparator2));
        out.println(stud);
        out.println();

        out.println("implementing nullsFirst method");
        stud.add(null);
        stud.sort(Comparator.nullsFirst(studentComparator2));
        out.println(stud);
        out.println();

        out.println("implementing nullsLast method");
        stud.sort(Comparator.nullsLast(studentComparator2));
        out.println(stud);
        out.println();
    }
}
