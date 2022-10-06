package reflection.annotations.annotation_init_example;

import reflection.annotations.annotation_init_example.annotations.InitializerClass;
import reflection.annotations.annotation_init_example.annotations.InitializerMethod;
import reflection.annotations.annotation_init_example.annotations.RetryOperation;
import reflection.annotations.annotation_init_example.annotations.ScanPackages;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.*;

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

        ScanPackages scanPackagesAnnotation = Main.class.getAnnotation(ScanPackages.class);
        if (scanPackagesAnnotation == null || scanPackagesAnnotation.values().length == 0) {
            return;
        }

        List<Class<?>> classes = getAllClasses(scanPackagesAnnotation.values());

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

    private static List<Class<?>> getAllClasses(String[] packageNames) throws URISyntaxException, IOException,
            ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        for (String packageName : packageNames) {
            String packageRelativePath = packageName.replace(".", "/");
            URI packageUri = Main.class.getResource(packageRelativePath).toURI();
            if (packageUri.getScheme().equals("file")) {
                Path packageFullPath = Paths.get(packageUri);
                classes.addAll(getAllPackageClasses(packageFullPath, packageName));
            } else if (packageUri.getScheme().equals("jar")) {
                FileSystem fileSystem = FileSystems.newFileSystem(packageUri, Collections.emptyMap());
                Path packageFullPath = fileSystem.getPath(packageRelativePath);
                classes.addAll(getAllPackageClasses(packageFullPath, packageName));
                fileSystem.close();
            }
        }
        return classes;
    }


    private static List<Class<?>> getAllPackageClasses(Path packagePath, String packageName) throws IOException,
            ClassNotFoundException {
        if (!Files.exists(packagePath)) {
            return Collections.emptyList();
        }

        List<Path> files = Files.list(packagePath)
                .filter(Files::isRegularFile)
                .toList();

        List<Class<?>> classes = new ArrayList<>();
        for (var file : files) {
            String fileName = file.getFileName().toString();
            if (fileName.endsWith(".class")) {
                if (packageName.startsWith("/")) {
                    packageName = packageName.substring(1);
                }
                String replacedString = packageName.replace('/', '.');
                String classFullName = replacedString + "." + fileName.replaceAll("\\.class$", "");
                Class<?> clazz = Class.forName(classFullName);
                classes.add(clazz);
            }
        }
        return classes;
    }
}
