package effective_java.clone;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class shows incorrect way of copying, mutable states. Mutable states should be copied separately
 * in the clone method. See next Example
 */
public class InCorrectMutableCloning {
    static class StudentNames implements Cloneable {
        String schoolName;

        List<String> names;

        public StudentNames(String schoolName, String... names) {
            this.schoolName = schoolName;
            this.names = new ArrayList<>(List.of(names));
        }

        public List<String> getNames() {
            return names;
        }

        @Override
        public StudentNames clone() {
            try {
                StudentNames clone = (StudentNames) super.clone();
                // TODO: copy mutable state here, so the clone can't change the internals of the original
                return clone;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            StudentNames that = (StudentNames) o;
            return Objects.equals(schoolName, that.schoolName) && Objects.equals(names, that.names);
        }

        @Override
        public int hashCode() {
            return Objects.hash(schoolName, names);
        }

        @Override
        public String toString() {
            return "StudentNames{" +
                    "schoolName='" + schoolName + '\'' +
                    ", names=" + names +
                    '}';
        }
    }
    public static void main(String[] args) {
        var studentNames = new StudentNames("school1", "first", "second", "third");
        var studentNames2 = studentNames.clone();

        // reference will say that these two objects are different, but adding student name to
        // one list, gets also reflected back in other, since mutable states are not being copied
        System.out.println(studentNames2 == studentNames);
        System.out.println(studentNames2.equals(studentNames));

        studentNames2.getNames().add("fourth");
        System.out.println(studentNames);
    }
}
