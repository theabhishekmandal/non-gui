package reflection.class_object;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

/**
 * This is an example showing how to create Objects using reflection.
 */
public class ConstructorObjectDemo {
    public static void main(String[] args)
            throws InvocationTargetException, InstantiationException, IllegalAccessException {
//        printConstructorData(Person.class);
//        printConstructorData(Address.class);

//        Person person = createInstanceWithArguments(Person.class);

        Address address = createInstanceWithArguments(Address.class, "First Street", 10);
        Person person = createInstanceWithArguments(Person.class, "John", 20, address);
        System.out.println(person);
    }

    public static <T> T createInstanceWithArguments(Class<T> clazz, Object... args)
            throws InvocationTargetException, InstantiationException, IllegalAccessException {
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            if (constructor.getParameterTypes().length == args.length) {
                return (T) constructor.newInstance(args);
            }
        }
        System.out.println("An appropriate constructor was not found");
        return null;
    }

    public static void printConstructorData(Class<?> clazz) {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        System.out.printf("class %s has %d declared constructors%n", clazz.getSimpleName(), constructors.length);
        for (Constructor<?> constructor : constructors) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            List<String> parameterTypeNames = Arrays.stream(parameterTypes)
                    .map(Class::getSimpleName).toList();
            System.out.println(parameterTypeNames);
        }
    }

    private static class Person {
        private final Address address;
        private final String name;
        private final int age;

        public Person() {
            this("anonymous");
        }

        public Person(String name) {
            this(name, 0);
        }

        public Person(String name, int age) {
            this(name, age, null);
        }

        public Person(String name, int age, Address address) {
            this.address = address;
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "address=" + address +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    private static class Address {
        private String street;
        private int number;

        public Address(String street, int number) {
            this.street = street;
            this.number = number;
        }

        @Override
        public String toString() {
            return "Address{" +
                    "street='" + street + '\'' +
                    ", number=" + number +
                    '}';
        }
    }
}
