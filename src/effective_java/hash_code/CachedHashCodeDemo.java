package effective_java.hash_code;

import java.util.Objects;

public class CachedHashCodeDemo {
    static class CachedHashCode {
        private final int age;
        private final String firstName;
        private final String lastName;
        private int hashCode;
        CachedHashCode(int age, String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        public int getAge() {
            return age;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CachedHashCode that = (CachedHashCode) o;
            return age == that.age && Objects.equals(firstName, that.firstName)
                    && Objects.equals(lastName, that.lastName);
        }

        @Override
        public int hashCode() {
            if (hashCode == 0) {
                hashCode = Objects.hash(age, firstName, lastName);
            }
            return hashCode;
        }
    }

    public static void main(String[] args) {
        final CachedHashCode cachedHashCode = new CachedHashCode(12, "Abhishek", "Mandal");
        System.out.println(cachedHashCode.getFirstName());
        System.out.println("hashCode is " + cachedHashCode.hashCode());
    }
}
