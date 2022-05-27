package reflection.field_object.json_serlializer.data;

public class Company {
    private final String companyName;
    private final String city;
    private final Address address;

    public Company(String companyName, String city, Address address) {
        this.companyName = companyName;
        this.city = city;
        this.address = address;
    }
}
