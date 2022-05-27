package reflection.class_object.package_private;

import reflection.class_object.package_private.internal.InternalClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * This is an example of how Reflection can be used to initialize package private classes and inject them accordingly
 * Here InternalClass is a public class, and it has a dependency on InternalClass2 which is package private.
 */
public class Main {
    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Interface anInterface = getObject(InternalClass.class);
        System.out.println(anInterface.getName());
    }

    private static <T> T getObject(Class<T> clazz) throws InvocationTargetException,
            InstantiationException, IllegalAccessException {
        Constructor<?> firstConstructor = getFirstConstructor(clazz);
        List<Object> arguments = new ArrayList<>();
        for (Class<?> argumentType : firstConstructor.getParameterTypes()) {
            Object argumentValue = getObject(argumentType);
            arguments.add(argumentValue);
        }
        firstConstructor.setAccessible(true);
        if (arguments.isEmpty()) {
            return (T) firstConstructor.newInstance();
        }
        return (T) firstConstructor.newInstance(arguments.toArray());
    }

    private static Constructor<?> getFirstConstructor(Class<?> clazz) {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        if (constructors.length == 0) {
            throw new IllegalStateException(String.format("No constructors has been found for class %s", clazz.getName()));
        }
        return constructors[0];
    }
}
