package CollectionImplementation.Streams;

/*
   We saw forEach() earlier in this section, which is a terminal operation.
   However, sometimes we need to perform multiple operations on each element of the stream before any terminal operation is applied.
   peek() can be useful in situations like this.
   Simply put, it performs the specified operation on each element of the stream and returns a new stream which can be used further.
   peek() is an intermediate operation

 */

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.List;

public class PeekDemo {
    public static void main(String[] args) {
        Employee[] arrayOfEmps = {
                new Employee(1, "Jeff Bezos", 100000.0),
                new Employee(2, "Bill Gates", 200000.0),
                new Employee(3, "Mark Zuckerberg", 300000.0)
        };

        List<Employee> empList = Arrays.stream(arrayOfEmps)
                .peek(System.out::println)
                .peek(e -> e.salaryIncrement(10.0))
                .collect(Collectors.toList());

        empList.forEach(System.out::println);
    }
}
