package reflection.class_object.dependency_injection.internal;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * This is an example showing how to inject dependencies using recursion.
 * Here Student class has a dependency on the Address object. So, first the Address object is
 * created and after that it is injected in the Student object.
 */
public class Main {
    public static void main(String[] args) throws InvocationTargetException,
            InstantiationException, IllegalAccessException {
        Student student = createInstance(Student.class);
        System.out.println(student);
    }

    @NotNull
    private static <T> T createInstance(Class<T> clazz) throws InvocationTargetException,
            InstantiationException, IllegalAccessException {
        Constructor<?> constructor = getFirstDeclaredConstructor(clazz);
        List<Object> parameterList = new ArrayList<>();
        for (Class<?> argumentType : constructor.getParameterTypes()) {
            Object argumentValue = createInstance(argumentType);
            parameterList.add(argumentValue);
        }
        constructor.setAccessible(true);
        if (parameterList.isEmpty()) {
            return (T) constructor.newInstance();
        }
        return (T) constructor.newInstance(parameterList.toArray());
    }

    private static Constructor<?> getFirstDeclaredConstructor(@NotNull Class<?> clazz) {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        if (constructors.length == 0) {
            throw new IllegalStateException("No constructors found for this class " + clazz.getSimpleName());
        }
        return constructors[0];
    }
}
