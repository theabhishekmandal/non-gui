package reflection.method_object.polymorphism;

import reflection.method_object.polymorphism.database.DatabaseClient;
import reflection.method_object.polymorphism.http.HttpClient;
import reflection.method_object.polymorphism.logging.FileLogger;
import reflection.method_object.polymorphism.udp.UdpClient;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * This is an example on how to use method invokation for different classes which are not related to each other.
 */
public class Main {
    public static void main(String[] args) throws Throwable {
        var databaseClient = new DatabaseClient();
        var httpClient1 = new HttpClient("127.0.0.1");
        var httpClient2 = new HttpClient("127.0.0.0");
        var fileLogger = new FileLogger();
        var udpClient = new UdpClient();

        String requestData = "request data";
        List<Class<?>> methodParameterTypes = List.of(String.class);
        Map<Object, Method> requestExecutors =
                groupExecutors(Arrays.asList(databaseClient, httpClient1, httpClient2, fileLogger, udpClient), methodParameterTypes);

        executeAll(requestExecutors, requestData);
    }

    public static void executeAll(Map<Object, Method> requestExecutors, String requestBody) throws Throwable {
        try {

            for (var requestExecutorEntry : requestExecutors.entrySet()) {
                Object requestExecutor = requestExecutorEntry.getKey();
                Method method = requestExecutorEntry.getValue();

                Boolean result = (Boolean) method.invoke(requestExecutor, requestBody);
                if (result != null && result.equals(Boolean.FALSE)) {
                    System.out.println("Recieved negative result. Aborting..");
                    return;
                }
            }
        } catch (InvocationTargetException e) {
            // throw the actual exception wrapped inside Invocation exception.
            throw e.getTargetException();
        }
    }

    /**
     * requestParameters containing all the instances for which you want to invoke the method. methodParameterTypes
     * will match the arguments with the methods present in the instances' methods.
     * @param requestExecutors
     * @param methodParameterTypes
     * @return
     */
    public static Map<Object, Method> groupExecutors(List<Object> requestExecutors, List<Class<?>> methodParameterTypes) {
        Map<Object, Method> instanceToMethod = new LinkedHashMap<>();
        for (Object requestExecutor : requestExecutors) {
            Method[] allMethod = requestExecutor.getClass().getDeclaredMethods();

            for (Method method : allMethod) {
                // get that method which matches the parameters
                if (Arrays.asList(method.getParameterTypes()).equals(methodParameterTypes)) {
                    instanceToMethod.put(requestExecutor, method);
                }
            }
        }
        return instanceToMethod;
    }
}
