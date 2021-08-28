package collection_implementation.queue;


import java.util.PriorityQueue;

import static java.lang.System.out;

/**
 *  Implementation of the priorityQueue class
 *  It does not permit null values because values are needed to be compared for storing
 *  If no comparator is provided then it stores the values in ascending order
 *  It is similar to TreeSet but it allows duplicates
 *  PriorityQueue extends AbstractQueue and implements the Queue interface.
 *
 *  The constructors defined are:
 *
 *  1 PriorityQueue()               :   builds an empty queue.
 *
 *  2 PriorityQueue(int capacity)   :   builds a queue that has the specified initial capacity.
 *
 *  3 PriorityQueue(Comparator<?
 *      super E> comp)              :   builds a queue based on the comparator passed.
 *
 *  4 PriorityQueue(int capacity,
 *      Comparator<? super E>
 *          comp)                   :   builds a queue with the specified capacity and comparator.
 *
 *  5 PriorityQueue(Collection<
 *      ? extends E> c)             :   builds a queue that is initialized with the elements of the
 *                                      collection c.
 *
 *  6 PriorityQueue(PriorityQueue
 *  <? extends E> c)                :   builds a queue that is initialized with the elements of the
 *                                      priority queue c.
 *
 *  7 PriorityQueue(SortedSet<?
 *      extends E> c)               :   builds a queue that is initialized with the elements of the
 *                                      SortedSet c.
 */
class Student{
    private final String name;
    private final double cgpa;

    public Student(String name, double cgpa){
        this.name = name;
        this.cgpa = cgpa;
    }

    public String getName(){
        return name;
    }
    public double getCgpa() {
        return cgpa;
    }
}

public class PriorityQueueDemo {
    public static void main(String[] args){

        // Creating Priority queue constructor having
        // initial capacity = 3 and a StudentComparator instance
        // as its parameters
        var pq = new PriorityQueue<Student>(5, (s1, s2) -> {
            if(s1.getCgpa() < s2.getCgpa()) {
                return 1;
            }
            else if(s1.getCgpa() > s2.getCgpa()) {
                return -1;
            }
            return 0;
        });

        var student1 = new Student("Nandini", 3.2);
        pq.add(student1);

        var student2 = new Student("Anmol", 3.6);
        pq.add(student2);

        var student3 = new Student("Palak", 4.0);
        pq.add(student3);

        // Printing names of student in priority order, poll()
        // method is used to access the head element of queue
        out.println("Students served in their priority order");
        while(!pq.isEmpty()) out.println(pq.poll().getName());
    }
}
