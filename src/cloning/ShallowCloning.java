package cloning;


/**
 * This is an example of shallow cloning. In shallow cloning reference types don't implement the Cloneable interface
 * In this if you clone a new object having reference types. And if reference type is
 * mutated then it is reflected back in original object.
 */
public class ShallowCloning {
    public static void main(String[] args) {
        var person = new Person("Abhishek", new Address(1, "First Building"));
        System.out.println(person);
        try {
            var person2 = (Person) person.clone();
            // changing second person address also changes the first one
            person2.getAddress().setBuilding("2nd Building").setStreetNo(2);

            System.out.println(person2);
            System.out.println(person);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    static class Person implements Cloneable {
        private final String name;
        private final Address address;

        public Person(String name, Address address) {
            this.name = name;
            this.address = address;
        }

        public String getName() {
            return name;
        }

        public Address getAddress() {
            return address;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
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
