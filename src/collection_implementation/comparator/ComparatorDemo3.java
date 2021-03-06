package collection_implementation.comparator;

import java.util.ArrayList;
import java.util.Comparator;

import static java.lang.System.out;


/**
 *  Further implementation of Comparator Methods:
 *
 *  1 default Comparator<T> thenComparing
 *   (Comparator<? super T> thenByComp)     :   returns a second comparator that performs a second comparison when the
 *                                              outcome of  the first comparison indicates that the object being compared
 *                                              are equal.
 *  2 default <U extends Comparable<?
 *    super U>> Comparator<T> thenComparing
 *    (Function<? super T,? extends U>
 *    getKey)                               :   here it is also same as above but instead of passing the comparator you
 *                                              can pass the function which will act as next key for comparison
 *
 *  3 default <U> Comparator<T> thenComparing
 *    (Function<? super T,? extends U>
 *    getKey, Comparator<? super U>keyComp) :   here it is similar to the above method, but here in this we can specify
 *                                              the ordering of the elements using the keyComp
 */

class Person {
    private final int number;
    private final String name;
    private final int age;

    Person(int number, String name, int age) {
        this.number = number;
        this.name = name;
        this.age = age;
    }

    public int getNumber() {
        return this.number;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    @Override
    public String toString() {
        return "[" + this.number + " " + this.name + " " + this.age + "]";
    }
}

public class ComparatorDemo3 {
    public static void main(String[] args) {

        ArrayList<Person> arr = new ArrayList<>();
        arr.add(new Person(1, "Abhishek", 10));
        arr.add(new Person(1, "Aman", 11));
        arr.add(new Person(3, "Abhishek", 11));
        arr.add(new Person(4, "Raman", 12));

        out.println("implementing the first way of thenComparing Method");
        Comparator<Person> personComparator = Comparator.comparing(Person::getNumber)
                .thenComparing(Comparator.comparing(Person::getName));
        arr.sort(personComparator);
        out.println(arr);
        out.println();

        out.println("implementing the second way of thenComparing Method");
        Comparator<Person> personComparator1 = Comparator.comparing(Person::getNumber)
                .thenComparing(Person::getName);
        arr.sort(personComparator1);
        out.println(arr);
        out.println();

        out.println("implementing the third way of thenComparing Method");
        // here the lambda expression allows to define the ordering of the elements based on
        // getAge method
        Comparator<Person> personComparator2 = Comparator.comparing(Person::getNumber)
                .thenComparing(Person::getAge, Comparator.reverseOrder());
        arr.sort(personComparator2);
        out.println(arr);
        out.println();
    }
}
