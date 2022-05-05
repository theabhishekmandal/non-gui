package reflection.class_object.dependency_injection.internal;

public class Address {
    private final String mainAddress;

    public Address() {
        mainAddress = "New Delhi";
    }

    public String getMainAddress() {
        return mainAddress;
    }

    @Override
    public String toString() {
        return "Address{" +
                "mainAddress='" + mainAddress + '\'' +
                '}';
    }
}
