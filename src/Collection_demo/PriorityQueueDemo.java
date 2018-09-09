package Collection_demo;


import java.util.Comparator;
import java.util.PriorityQueue;

import static java.lang.System.out;

/**
 *  Implementation of the priorityQueue class
 *  It does not permit null values because values are needed to be compared for storing
 *  If no comparator is provided then it stores the values in ascending order
 *  It is similar to TreeSet but it allows duplicates
 */
class Student{
    public String name;
    public double cgpa;

    public Student(String name, double cgpa){
        this.name = name;
        this.cgpa = cgpa;
    }

    public String getName(){
        return name;
    }
}

class StudentComparator implements Comparator<Student> {
    public int compare(Student s1, Student s2){
        if(s1.cgpa < s2.cgpa) return 1;
        else if(s1.cgpa > s2.cgpa) return -1;
        return 0;
    }
}

public class PriorityQueueDemo {
    public static void main(String args[]){
        // Creating Priority queue constructor having
        // initial capacity = 3 and a StudentComparator instance
        // as its parameters
        PriorityQueue<Student> pq = new PriorityQueue<>(5, new StudentComparator());

        Student student1 = new Student("Nandini", 3.2);
        pq.add(student1);

        Student student2 = new Student("Anmol", 3.6);
        pq.add(student2);

        Student student3 = new Student("Palak", 4.0);
        pq.add(student3);

        // Printing names of student in priority order, poll()
        // method is used to access the head element of queue
        out.println("Students served in their priority order");

        while(!pq.isEmpty()) out.println(pq.poll().getName());

    }
}
