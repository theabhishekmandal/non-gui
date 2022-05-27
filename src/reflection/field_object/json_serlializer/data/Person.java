package reflection.field_object.json_serlializer.data;

public class Person {
    private final String name;
    private final boolean isEmployed;
    private final int age;
    private final float salary;

    private final Address address;

    private final Company company;

    public Person(String name, boolean isEmployed, int age, float salary) {
        this(name, isEmployed, age, salary, null, null);
    }

    public Person(String name, boolean isEmployed, int age, float salary, Address address, Company company) {
        this.name = name;
        this.isEmployed = isEmployed;
        this.age = age;
        this.salary = salary;
        this.address = address;
        this.company = company;
    }
}
