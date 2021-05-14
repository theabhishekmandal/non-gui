package serialization;

import java.io.*;

/**
 * You should implement serialVersionUID, because if after serialisation, somehow new fields got introduced in your class, and those changes
 * are not significant, then in that scenario, don't change the serialVersionUID, just deserialize it, and the object will
 * be created, and for null fields the value will be null.
 *
 *  -   serialVersionUID is used for versioning of the serialized streams. During serialization process serialVersionUID is also stored.
 *      During deserialization generated serialVersionUID is matched with the stored one and if there is a mismatch process fails.
 *
 *  -   serialVersionUID is a 64-bit hash of the class name, interface class names, methods, and fields.
 *      If you don’t declare one yourself serialization process will still generate serialVersionUID. In that case it will fail for any change in the class.
 *
 *  -   If you declare the serialVersionUID that gives you control over the versioning. When you think class has grown in way
 *      that is not compatible with the previous versions then you can change the serialVersionUID.
 *      If you think change in the class are not significant enough to change the serialVersionUID you may choose to retain the same serialVersionUID. In that case serialization and deserialization will not fail even if your class had changed.
 *
 *  -   serialVersionUID is declared as a private static final long and it is always better to declare one in order to
 *      have control over the versioning of the class.

 */
public class SerializeDeserializeDemo {
    public static void main(String[] args) {
        var person = new Person("Abhishek", new Address("one", "two"));
        writeObject(person);
        var person2 = readObject();
        System.out.println(person2);
    }

    private static void writeObject(Person person) {
        // writing person object
        try (var out = new ObjectOutputStream(new FileOutputStream("serialize.txt"))) {
            out.writeObject(person);
            System.out.println("Person object written");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Person readObject() {
        try (var in = new ObjectInputStream(new FileInputStream("serialize.txt"))) {
            var person = (Person) in.readObject();
            System.out.println("Person object read");
            return person;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    static class Address implements Serializable {
        private static final long serialVersionUID = 1L;
        private final String address1;
        private final String address2;

        public Address(String address1, String address2) {
            this.address1 = address1;
            this.address2 = address2;
        }

        public String getAddress1() {
            return address1;
        }

        public String getAddress2() {
            return address2;
        }

        @Override
        public String toString() {
            return "Address{" +
                    "address1='" + address1 + '\'' +
                    ", address2='" + address2 + '\'' +
                    '}';
        }
    }

    static class Person implements Serializable {
        private static final long serialVersionUID = 1L;
        private final String name;
        private final Address address;

        public Person(String name, Address address) {
            this.name = name;
            this.address = address;
        }

        public static long getSerialVersionUID() {
            return serialVersionUID;
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
}
