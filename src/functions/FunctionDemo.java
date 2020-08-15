package functions;


import java.util.Random;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *  This is basic example of the main Functional interfaces present in the function package.
 */

@FunctionalInterface
interface MyFunctionalInterface<T, V> {
    T get(V p);
}

public class FunctionDemo {
    public static void main(String[] args) {
        biConsumerDemo();
        biFunctionDemo();
        binaryOperatorDemo();
        biPredicateDemo();
        booleanSupplierDemo();
        consumerDemo();
        functionDemo();
        predicateDemo();
        supplierDemo();
        unaryOperator();
        myFunctionalInterface();
    }

    /*
        BiConsumer accepts two different values and has a return type of void.
        BiConsumer<T, U> -> void accept(T first, U second)
     */
    private static void biConsumerDemo(){
        StringBuilder br = new StringBuilder();

        BiConsumer<StringBuilder, Integer> biConsumer = StringBuilder::append;
        biConsumer = biConsumer.andThen(biConsumer);
        biConsumer.accept(br, 1);

        System.out.println(br);
    }

    /*
        BiFunction accepts two different values and returns a result of third type

        BiFunction<T,U,R> -> R apply(T first, U second)
     */
    private static void biFunctionDemo(){
        BiFunction<StringBuilder, Integer, String> biFunction = (first, second) -> first.append(second).toString();

        StringBuilder br = new StringBuilder();

        Function<String, String> function = (input) -> (input.length() > 0)? input.substring(1) : "";
        biFunction = biFunction.andThen(function);

        String result = biFunction.apply(br, 1234);
        System.out.println(result);

    }

    /*
        BinaryOperator accepts two arguments of same type and returns the result of the same type

        BinaryOperator<T> -> T apply(T first, T second)
     */
    private static void binaryOperatorDemo(){
        BinaryOperator<Integer> binaryOperator = Integer::sum;
        System.out.println(binaryOperator.apply(10, 20));
    }

    /*
        BiPredicate accepts two different types of objects and returns a boolean result
        BiPredicate<T, U> -> boolean test(T first, U second)
     */
    private static void biPredicateDemo(){
        BiPredicate<Long, Integer> isSame = (first, second) -> Long.toString(first).equals(Integer.toString(second));
        System.out.println(isSame.test(1L, 1));

    }

    /*
        BooleanSupplier calculates and returns a boolean value
        BooleanSupplier -> boolean getAsBoolean()
     */
    private static void booleanSupplierDemo(){
        Random random = new Random();
        BooleanSupplier supplier = () -> random.nextInt(100) > random.nextInt(100);
        System.out.println(supplier.getAsBoolean());
    }

    /*
        Consumer accepts a single value and acts on it.
        Consumer<T> -> void accept(T val)
     */
    private static void consumerDemo(){
        Consumer<Integer> consumer = System.out::println;
        consumer.accept(100);
    }

    /*
        Function accepts an object and returns an object.
        Function<T, R> -> R apply(T first)
     */
    private static void functionDemo(){
        Function<String, Integer> function = Integer::parseInt;
        System.out.println(function.apply("12345"));
    }

    /*
        Predicate returns true if the passed object satisfies the condition, defined by test()
        Predicate<T> -> boolean test(T val)
     */
    private static void predicateDemo(){
        Random random = new Random();
        Predicate<Integer> predicate = (first) -> first > random.nextInt(100);
        System.out.println(predicate.test(1));
        System.out.println(predicate.negate().test(1));
    }

    /*
        Supplier returns a value of type T
        Supplier<T> -> returns and object of type T.
     */
    private static void supplierDemo(){
        Supplier<String> supplier = () -> IntStream.range(0, 10).boxed().map(String::valueOf).collect(Collectors.joining());
        System.out.println(supplier.get());
    }

    /*
        UnaryOperator accepts a object, applies the operation and returns the same type of object;
        UnaryOperator<T> -> T apply(T first)
     */
    private static void unaryOperator(){
        UnaryOperator<Integer> unaryOperator = (first) -> first * first;
        System.out.println(unaryOperator.apply(12));
    }

    private static void myFunctionalInterface() {
        MyFunctionalInterface<Integer, String> mF = x -> {
            try {
                return Integer.parseInt(x);
            }
            catch(Exception e) {
                return null;
            }
        };
        System.out.println(mF.get("12"));
        System.out.println(mF.get("sdfds"));
    }
}
