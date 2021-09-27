package streams.problems;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

class Person {
    private final String name;
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static Person from(String name, int age) {
        return new Person(name, age);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
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
                .collect(toUnmodifiableList());
        System.out.println(list);


        // collect to string
        System.out.println(
                people.stream()
                        .map(Person::getName)
                        .map(String::toUpperCase)
                        .collect(joining(","))
        );


        // Partitioning on the basis of boolean condition
        var evenOddAgePerson = people
                .stream()
                .collect(partitioningBy(person -> (person.getAge() & 1) == 0, toSet()));
        System.out.println(evenOddAgePerson);


        //Grouping by example, meaning grouping on the basis of names
        var groupByNames = people
                .stream()
                .collect(groupingBy(Person::getName));
        System.out.println(groupByNames);


        //Grouping by example, collecting all the ages based on the name
        // here we use mapping to convert the type from Person to Integer
        Map<String, List<Integer>> ageByName = people
                .stream()
                .collect(groupingBy(Person::getName, mapping(Person::getAge, toList())));
        // Collector(Function, Collector(Function, Collector)) above example showing recursive form.
        System.out.println(ageByName);


        // counting the number of person with a given name
        //Example 1
        Map<String, Integer> countByName = people
                .stream()
                .collect(groupingBy(Person::getName, summingInt(x -> 1)));
        System.out.println(countByName);


        // Example 2
        /*
            grouping and mapping gives (Function, Collector)
            collectingAndThen gives (Collector, Function)
            because after counting we want to convert it to intvalue
         */
        countByName = people
                .stream()
                .collect(groupingBy(Person::getName,
                        collectingAndThen(counting(), Long::intValue)));
        System.out.println(countByName);


        // use this to find maxValue on the basis of age, it returns optional as there can be no maxValue present
        var optionalMaxAge = people.stream().collect(maxBy(Comparator.comparing(Person::getAge)))
                .map(Person::getName).or(Optional::empty);
        System.out.println(optionalMaxAge);


        // we can apply filtering after mapping as well
        var groupByAgeAndLengthGreaterThan4 = people.stream()
                .collect(groupingBy(Person::getAge,
                        mapping(Person::getName, filtering(name -> name.length() > 4, toList()))));
        System.out.println(groupByAgeAndLengthGreaterThan4);

        // we can convert the above to unmodifiable List
        var groupByAgeAndLengthGreaterThan4AndUnmodifiable = people.stream()
                .collect(groupingBy(Person::getAge,
                        mapping(Person::getName, filtering(name -> name.length() > 4,
                                collectingAndThen(toList(), Collections::unmodifiableList)))));
        System.out.println(groupByAgeAndLengthGreaterThan4AndUnmodifiable);


        // grouping all the person on the basis of age, but get the list of chars of the Person Name
        var groupByAgeAndGetListOfChars = people.stream()
                .collect(groupingBy(Person::getAge,
                        flatMapping(x -> Stream.of(x.getName().split("")), toSet())));
        System.out.println(groupByAgeAndGetListOfChars);


        // same as above but convert to UpperCase
        var groupByAgeAndGetListOfCharsInUpperCase = people.stream()
                .collect(groupingBy(Person::getAge,
                        mapping(person -> person.getName().toUpperCase(),
                                flatMapping(name -> Arrays.stream(name.split("")),
                                        toSet()))));
        System.out.println(groupByAgeAndGetListOfCharsInUpperCase);
    }
}
