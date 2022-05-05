package reflection.class_object.dependency_injection.internal;

public class Student {
    private final String name;
    private final Address address;

    public Student(Address address) {
        this.name = "Anonymous";
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}
