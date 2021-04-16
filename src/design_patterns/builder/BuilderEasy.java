package design_patterns.builder;

/**
 * This is an example of how to create immutable object with the help of builder pattern.
 * Approach:
 *  -   delegate all the setter methods in the static builder class
 *  -   and for the getter use the original class object.
 */
class Student {
    private final String firstName;
    private final String middleName;
    private final String lastName;

    private Student(StudentBuilder studentBuilder) {
        firstName = studentBuilder.firstName;
        middleName = studentBuilder.middleName;
        lastName = studentBuilder.lastName;
    }

    public static class StudentBuilder {
        private String firstName;
        private String middleName;
        private String lastName;

        public StudentBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public StudentBuilder setMiddleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public StudentBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}

public class BuilderEasy {
    public static void main(String[] args) {
        Student student = new Student.StudentBuilder()
                .setFirstName("Abhishek")
                .setMiddleName("Kumar")
                .setLastName("Mandal")
                .build();
        System.out.println(student.getFirstName());
        System.out.println(student.getMiddleName());
        System.out.println(student.getLastName());
    }
}
