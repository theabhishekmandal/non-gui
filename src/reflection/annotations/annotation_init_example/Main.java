package reflection.annotations.annotation_init_example;

import reflection.annotations.annotation_init_example.annotations.InitializerClass;
import reflection.annotations.annotation_init_example.annotations.InitializerMethod;
import reflection.annotations.annotation_init_example.annotations.RetryOperation;
import reflection.annotations.annotation_init_example.annotations.ScanPackages;
import reflection.annotations.util.AnnotationUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * This is an example showing, how to execute the classes when a specific Annotation header is
 * present.
 */
@ScanPackages(values = {"/reflection/annotations/annotation_init_example/app",
        "/reflection/annotations/annotation_init_example/app/configs",
        "/reflection/annotations/annotation_init_example/app/databases",
        "/reflection/annotations/annotation_init_example/app/http"})
public class Main {
    public static void main(String[] args) throws Throwable {
        initialize();
    }

    public static void initialize() throws Throwable {
        List<Class<?>> classes = AnnotationUtil.getAllClasses(Main.class);

        for (var clazz : classes) {
            if (!clazz.isAnnotationPresent(InitializerClass.class)) {
                continue;
            }
            // assuming constructor is present.
            Object instance = clazz.getConstructor().newInstance();

            for (Method method : getAllInitializingMethods(clazz)) {
                callInitializingMethod(instance, method);
            }
        }
    }

    private static void callInitializingMethod(Object instance, Method method) throws Throwable {
        RetryOperation retryOperation = method.getAnnotation(RetryOperation.class);
        int numberOfRetries = retryOperation == null ? 0 : retryOperation.numberOfRetries();
        while (true) {
            try {
                method.invoke(instance);
                break;
            } catch (InvocationTargetException ex) {
                Throwable targetException = ex.getTargetException();
                if (numberOfRetries > 0 && Set.of(retryOperation.retryExceptions()).contains(targetException.getClass())) {
                    numberOfRetries--;
                    System.out.println("Retrying... ");
                    Thread.sleep(retryOperation.durationBetweenRetriesMs());
                } else if (retryOperation != null) {
                    throw new Exception(retryOperation.failureMessage(), targetException);
                } else {
                    throw targetException;
                }
            }
        }
    }

    // filtering all the annotated methods
    private static List<Method> getAllInitializingMethods(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(InitializerMethod.class))
                .toList();
    }


}
