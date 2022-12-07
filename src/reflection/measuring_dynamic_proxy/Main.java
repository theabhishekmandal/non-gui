package reflection.measuring_dynamic_proxy;

import org.jetbrains.annotations.NotNull;
import reflection.measuring_dynamic_proxy.external.DataBaseReader;
import reflection.measuring_dynamic_proxy.external.HttpClient;
import reflection.measuring_dynamic_proxy.external.ThrowingInterface;
import reflection.measuring_dynamic_proxy.external.impl.DataBaseReaderImpl;
import reflection.measuring_dynamic_proxy.external.impl.HttpClientImpl;
import reflection.measuring_dynamic_proxy.external.impl.ThrowingClass;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        HttpClient httpClient = createProxy(new HttpClientImpl());
        DataBaseReader dataBaseReader = createProxy(new DataBaseReaderImpl());
        ThrowingInterface throwingInterface = createProxy(new ThrowingClass());
        useThrowingInterface(throwingInterface);
        useHttpClient(httpClient);
        useDataBaseReader(dataBaseReader);
    }

    public static void useThrowingInterface(ThrowingInterface throwingInterface) {
        try {
            throwingInterface.throwException();
        } catch (IOException ex) {
            System.out.println("Exception caught");
        }
    }

    public static void useHttpClient(HttpClient httpClient) {
        httpClient.initialise();
        String response = httpClient.sendRequest("some request");
        System.out.printf("Http Response is  : %s%n", response);
    }

    public static void useDataBaseReader(@NotNull DataBaseReader dataBaseReader) throws InterruptedException {
        int rowsInGamesTable = dataBaseReader.countRowsInTable("GamesTable");
        System.out.printf("There are %s rows in GamesTable%n", rowsInGamesTable);
        String[] data = dataBaseReader.readRow("SELECT * from GamesTable");
        System.out.printf("Received %s%n", String.join(", ", data));
    }

    @SuppressWarnings("unchecked")
    public static <T> T createProxy(@NotNull Object originalObject) {
        Class<?>[] interfaces = originalObject.getClass().getInterfaces();

        InvocationHandler timeMeasuringProxyHandler = new TimeMeasuringProxyHandler(originalObject);

        return (T) Proxy.newProxyInstance(originalObject.getClass().getClassLoader(), interfaces, timeMeasuringProxyHandler);
    }

    public static class TimeMeasuringProxyHandler implements InvocationHandler {
        private final Object originalObject;

        public TimeMeasuringProxyHandler(Object originalObject) {
            this.originalObject = originalObject;
        }


        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object result;
            System.out.printf("Measuring Proxy - Before Executing Method : %s()", method.getName());
            long start = System.currentTimeMillis();

            /*
             * Handling any exception that can be thrown by the interface should also be thrown by
             * the Proxy
             */
            try {
                result = method.invoke(originalObject, args);
            } catch (InvocationTargetException ex) {
                throw ex.getTargetException();
            }
            long endTime = System.currentTimeMillis();
            System.out.println();
            System.out.printf("Measuring Proxy - Execution of %s() took %dms %n", method.getName(), endTime - start);
            return result;
        }
    }
}
