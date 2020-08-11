package streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *sorted in Streams is used to sort by passing the comparator or by using the default sorting order
 */

public class SortedDemo {
    public static void main(String[] args) {
        Employee[] arrayOfEmps = {
                new Employee(1, "Jeff Bezos", 100000.0),
                new Employee(2, "Bill Gates", 200000.0),
                new Employee(3, "Mark Zuckerberg", 300000.0)
        };

        List<Employee> employeesWithSortedName = Arrays.stream(arrayOfEmps)
                                                    .sorted(Comparator.comparing(Employee::getName))
                                                    .collect(Collectors.toList());

        List<Employee> employeesWithSortedSalary = Arrays.stream(arrayOfEmps)
                                                    .sorted(Comparator.comparing(Employee::getSalary))
                                                    .collect(Collectors.toList());
        System.out.println(
                employeesWithSortedName + "\n" + employeesWithSortedSalary
        );
    }
}
