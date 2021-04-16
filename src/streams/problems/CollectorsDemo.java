package streams.problems;

import java.util.*;
import java.util.stream.Collectors;

class Person {
    private final String name;
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public static Person from(String name, int age) {
        return new Person(name, age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
public class CollectorsDemo {
    public static List<Person> createPeople() {
        return List.of(
                Person.from("Sara", 20),
                Person.from("Sara", 22),
                Person.from("Bob", 20),
                Person.from("Paula", 32),
                Person.from("Paul", 32),
                Person.from("Jack", 3),
                Person.from("Jack", 72),
                Person.from("Jill", 11)
        );
    }
    public static void main(String[] args) {

        List<Person> people = createPeople();
        // collect to unmodifiable list
        List<Person> list = people
                .stream()
                .filter(x -> x.getAge() > 30)
                .collect(Collectors.toUnmodifiableList());
        System.out.println(list);






        // collect to string
        System.out.println(
                people
                        .stream()
                        .map(Person::getName)
                        .map(String::toUpperCase)
                        .collect(Collectors.joining(","))
        );






        // Partitioning on the basis of boolean condition
        Map<Boolean, Set<Person>> evenOddAgePerson = people
                .stream()
                .collect(Collectors.partitioningBy(person -> (person.getAge() & 1) == 0, Collectors.toSet()));
        System.out.println(evenOddAgePerson);






        //Grouping by example, meaning grouping on the basis of names
        Map<String, List<Person>> groupByNames = people
                .stream()
                .collect(Collectors.groupingBy(Person::getName));
        System.out.println(groupByNames);






        //Grouping by example, collecting all the ages based on the name
        // here we use mapping to convert the type from Person to Integer
        Map<String, List<Integer>> ageByName = people
                .stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.mapping(Person::getAge, Collectors.toList())));
        // Collector(Function, Collector(Function, Collector)) above example showing recursive form.
        System.out.println(ageByName);






        // counting the number of person with a given name
        //Example 1
        Map<String, Integer> countByName = people
                .stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.summingInt(x -> 1)));
        System.out.println(countByName);


        // Example 2
        /*
            grouping and mapping gives (Function, Collector)
            collectingAndThen gives (Collector, Function)
            because after counting we want to convert it to intvalue
         */
        countByName = people
                .stream()
                .collect(Collectors.groupingBy(Person::getName,
                        Collectors.collectingAndThen(Collectors.counting(), Long::intValue)));
        System.out.println(countByName);






        // use this to find maxValue on the basis of age, it returns optional as there can be no maxValue present
        var optionalMaxAge = people.stream().collect(Collectors.maxBy(Comparator.comparing(Person::getAge)))
                .map(Person::getName).or(Optional::empty);
        System.out.println(optionalMaxAge);


    }
}
