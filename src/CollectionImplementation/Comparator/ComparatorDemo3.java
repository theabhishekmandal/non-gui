package CollectionImplementation.Comparator;
import java.util.*;

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

class Person{
    private int number;
    private String name;
    private int age;
    Person(int number, String name, int age){
        this.number = number;
        this.name = name;
        this.age = age;
    }
    public int getNumber(){
        return this.number;
    }
    public String getName(){
        return this.name;
    }
    public int getAge(){
        return this.age;
    }
    @Override
    public String toString(){
        return "[" + this.number + " " + this.name + " " + this.age + "]";
    }
}
public class ComparatorDemo3 {
    public static void main(String[] args) {

        // implementing the first way of thenComparing Method
        Comparator<Person> personComparator = Comparator.comparing(Person::getNumber)
                                                .thenComparing(Comparator.comparing(Person::getName));

        // implementing the second way of thenComparing Method
        Comparator<Person> personComparator1 = Comparator.comparing(Person::getNumber).thenComparing(Person::getName);

        // implementing the third way of thenComparing Method
        // here the lambda expression allows to define the ordering of the elements based on
        // getAge method
        Comparator<Person> personComparator2 = Comparator.comparing(Person::getNumber)
                    .thenComparing(Person::getAge, (age1, age2) -> {
                        return age2.compareTo(age1);
                    });

        ArrayList<Person> arr = new ArrayList<>();
        arr.add(new Person(1, "Abhishek", 10));
        arr.add(new Person(1, "Aman", 11));
        arr.add(new Person(3, "Abhishek", 11));
        arr.add(new Person(4, "Raman", 12));

        arr.sort(personComparator);
        System.out.println(arr);

        arr.sort(personComparator1);
        System.out.println(arr);

        arr.sort(personComparator2);
        System.out.println(arr);
    }
}
