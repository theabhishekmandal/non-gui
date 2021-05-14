package cloning;

/**
 * To avoid deepCloning, use copyConstructor to clone objects.
 */
public class CopyConstructorCloning {
    public static void main(String[] args) {
        var person = new Person("Abhishek", new Address(1, "First Building"));
        System.out.println(person);

        var person2 = Person.from(person);
        person2.getAddress()
                .setBuilding("2nd Building")
                .setStreetNo(2);

        System.out.println(person2);
        System.out.println(person);
    }

    static class Person {
        private final String name;
        private final Address address;

        public Person(String name, Address address) {
            this.name = name;
            this.address = address;
        }

        public static Person from(Person person) {
            return new Person(person.name, Address.from(person.getAddress()));
        }

        public String getName() {
            return name;
        }

        public Address getAddress() {
            return address;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", address=" + address +
                    '}';
        }
    }


    static class Address {
        private int streetNo;
        private String building;

        public Address(int streetNo, String address) {
            this.streetNo = streetNo;
            this.building = address;
        }

        public static Address from(Address address) {
            return new Address(address.streetNo, address.building);
        }

        public int getStreetNo() {
            return streetNo;
        }

        public Address setStreetNo(int streetNo) {
            this.streetNo = streetNo;
            return this;
        }

        public String getBuilding() {
            return building;
        }

        public Address setBuilding(String building) {
            this.building = building;
            return this;
        }

        @Override
        public String toString() {
            return "Address{" +
                    "streetNo=" + streetNo +
                    ", address='" + building + '\'' +
                    '}';
        }
    }
}
