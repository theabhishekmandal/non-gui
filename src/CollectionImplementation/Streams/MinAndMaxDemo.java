package CollectionImplementation.Streams;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class MinAndMaxDemo {
    public static void main(String[] args) {
        Employee[] arrayOfEmps = {
                new Employee(1, "Jeff Bezos", 100000.0),
                new Employee(2, "Bill Gates", 200000.0),
                new Employee(3, "Mark Zuckerberg", 300000.0)
        };

        Employee minSalaryEmployee = Arrays.stream(arrayOfEmps)
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(NoSuchElementException::new);

        Employee maxSalaryEmployee = Arrays.stream(arrayOfEmps)
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(NoSuchElementException::new);

        System.out.println(minSalaryEmployee + "\n" + maxSalaryEmployee);

    }
}
