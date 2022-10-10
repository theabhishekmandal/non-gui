package reflection.annotations.annotation_init_example.graph_example;

import reflection.annotations.annotation_init_example.annotations.Annotations;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
//        BestGamesFinder bestGamesFinder = new BestGamesFinder();
//        Object result = execute(bestGamesFinder);
//        System.out.println(result);

        SqlQueryBuilder sqlQueryBuilder = new SqlQueryBuilder(Arrays.asList("1", "2", "3"),
                10, "Movies", Arrays.asList("Id", "Name"));

        String sqlQuery = execute(sqlQueryBuilder);
        System.out.println(sqlQuery);
    }

    public static <T> T execute(Object instance) throws InvocationTargetException, IllegalAccessException {
        Class<?> aClass = instance.getClass();
        Map<String, Method> operationToMethod = getOperationToMethod(aClass);
        Map<String, Field> fieldToMethod = getFieldToMethod(aClass);
        Method finalResultMethod = findFinalResultMethod(aClass);

        return (T) executeWithDependencies(instance, finalResultMethod, operationToMethod, fieldToMethod);
    }

    private static Object executeWithDependencies(Object instance, Method method,
                                                  Map<String, Method> operationToMethod,
                                                  Map<String, Field> inputToField)
            throws InvocationTargetException, IllegalAccessException {
        List<Object> parameterValues = new ArrayList<>(method.getParameterCount());
        for (Parameter parameter : method.getParameters()) {
            Object result = null;
            if (parameter.isAnnotationPresent(Annotations.DependsOn.class)) {
                String value = parameter.getAnnotation(Annotations.DependsOn.class).value();
                Method dependencyMethod = operationToMethod.get(value);
                result = executeWithDependencies(instance, dependencyMethod, operationToMethod, inputToField);
            } else if (parameter.isAnnotationPresent(Annotations.Input.class)) {
                String value = parameter.getAnnotation(Annotations.Input.class).value();
                Field field = inputToField.get(value);
                field.setAccessible(true);
                result = field.get(instance);
            }
            parameterValues.add(result);
        }
        return method.invoke(instance, parameterValues.toArray());
    }

    private static Map<String, Method> getOperationToMethod(Class<?> clazz) {
        Map<String, Method> operationsNameToMethod = new HashMap<>();
        for (Method method : clazz.getDeclaredMethods()) {
            if (!method.isAnnotationPresent(Annotations.Operation.class)) {
                continue;
            }
            Annotations.Operation annotation = method.getAnnotation(Annotations.Operation.class);
            operationsNameToMethod.put(annotation.value(), method);
        }
        return operationsNameToMethod;
    }

    private static Map<String, Field> getFieldToMethod(Class<?> clazz) {
        Map<String, Field> fieldNamesToMethod = new HashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            if (!field.isAnnotationPresent(Annotations.Input.class)) {
                continue;
            }
            Annotations.Input annotation = field.getAnnotation(Annotations.Input.class);
            fieldNamesToMethod.put(annotation.value(), field);
        }
        return fieldNamesToMethod;
    }

    private static Method findFinalResultMethod(Class<?> clazz) {
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Annotations.FinalResult.class)) {
                return method;
            }
        }

        throw new RuntimeException("No method found with FinalResult annotation");
    }
}
