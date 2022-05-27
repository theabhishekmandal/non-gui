package reflection.field_object.object_size_calculator;

public class AccountSummary {
    private final String firstName;

    private final String lastName;

    private final short address;

    private final int age;

    private final int salary;

    public AccountSummary(String firstName, String lastName, Short address, int age, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.age = age;
        this.salary = salary;
    }

}
