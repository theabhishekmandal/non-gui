package reflection.measuring_dynamic_proxy.caching;

import utility.StopWatch;
import utility.Triplet;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        DataBaseReader dataBaseReader = new DataBaseReaderImpl();
        DataBaseReader dataBaseReader2 = createProxy(new DataBaseReaderImpl());
        //uncomment to see how slow it workds
        start(dataBaseReader);
        start(dataBaseReader2);
    }

    @SuppressWarnings("unchecked")
    private static <T> T createProxy(T originalObject) {
        CachingInvocationHandler cachingInvocationHandler = new CachingInvocationHandler(originalObject);
        return (T) Proxy.newProxyInstance(originalObject.getClass().getClassLoader(),
                originalObject.getClass().getInterfaces(), cachingInvocationHandler);
    }

    private static void start(DataBaseReader dataBaseReader) throws IOException {

        List<Triplet<String, String, String>> list = List.of(
                Triplet.of("abhishek", "mandal", "1"),
                Triplet.of("john", "doe", "2"),
                Triplet.of("joe", "root", "3")
        );
        StopWatch stopWatch = new StopWatch();
        stopWatch.startTime();
        dataBaseReader.connectToDataBase();
        System.out.println("Adding customers");

        for (var triplet : list) {
            dataBaseReader.addCustomer(triplet.getThird(), triplet.getFirst(), triplet.getSecond());
        }

        stopWatch.stopTime();
        System.out.println("Time taken in seconds " + stopWatch.getTimeInSeconds());

        stopWatch.reset();

        stopWatch.startTime();
        for (var triplet : list) {
            // printing two times to show that reading from database takes time
            System.out.format("birthday of customer id = %s is %s %n", triplet.getThird(), dataBaseReader.readCustomerBirthDay(triplet.getThird()));
            System.out.format("birthday of customer id = %s is %s %n", triplet.getThird(), dataBaseReader.readCustomerBirthDay(triplet.getThird()));
        }

        for (var triplet : list) {
            // printing two times to show that reading from database takes time
            System.out.printf("id of customer %s is %s%n", triplet.getFirst() + triplet.getSecond(),
                    dataBaseReader.readCustomerIdByName(triplet.getFirst(), triplet.getSecond()));

            System.out.printf("id of customer %s is %s%n", triplet.getFirst() + triplet.getSecond(),
                    dataBaseReader.readCustomerIdByName(triplet.getFirst(), triplet.getSecond()));
        }

        stopWatch.stopTime();
        System.out.println("Time taken in seconds " + stopWatch.getTimeInSeconds());
    }
}
