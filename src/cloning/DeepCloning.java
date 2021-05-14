package cloning;

/**
 * This is an example of DeepCloning. In this reference types also need to implement the Cloneable interface. Any change in the
 * new object is not reflected back in the original object.
 * DeepCloning is quite complicated and copy constructor should be used. see CopyConstructorCloning.
 */
public class DeepCloning {
    public static void main(String[] args) {

        var person = new Person("Abhishek", new Address(1, "First Building"));
        System.out.println(person);
        try {
            // changing second person address also changes the first one
            var person2 = ((Person) person.clone());
            person2.getAddress()
                    .setBuilding("2nd Building")
                    .setStreetNo(2);

            System.out.println(person2);
            System.out.println(person);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    static class Person implements Cloneable {
        private final String name;
        private Address address;

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
            var person = (Person) super.clone();
            person.address = (Address) address.clone();
            return person;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", address=" + address +
                    '}';
        }
    }


    static class Address implements Cloneable {
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
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
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
