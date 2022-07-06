package reflection.method_object.getter_inspection;

import reflection.method_object.getter_inspection.dto.ClothingProduct;
import reflection.method_object.getter_inspection.dto.Product;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * This class can be used to test getters and setters of pojo objects.
 */
public class ProductTest {
    public static void main(String[] args) {
        testGetters(Product.class);
        testSetters(Product.class);
        testSetters(ClothingProduct.class);
        testGetters(ClothingProduct.class);
    }


    private static List<Field> getAllFields(Class<?> dataClass) {
        if (dataClass == null || dataClass.equals(Object.class)) {
            return Collections.emptyList();
        }
        var currentClassFields = dataClass.getDeclaredFields();
        var inheritedFields = getAllFields(dataClass.getSuperclass());

        List<Field> allFields = new ArrayList<>();
        allFields.addAll(List.of(currentClassFields));
        allFields.addAll(inheritedFields);

        return allFields;
    }

    private static void testSetters(Class<?> dataClass) {
//        Field[] fields = dataClass.getDeclaredFields();
        List<Field> fields = getAllFields(dataClass);
        for (Field field : fields) {
            String setterName = "set" + capitalizeFirstLetter(field.getName());
            Method setterMethod = null;
            try {
                setterMethod = dataClass.getMethod(setterName, field.getType());
            } catch (NoSuchMethodException e) {
                throw new IllegalStateException(String.format("Setter method : %s not found", setterName));
            }

            if (!setterMethod.getReturnType().equals(void.class)) {
                throw new IllegalStateException(String.format("Setter method : %s has to return void", setterName));
            }
        }
    }

    private static void testGetters(Class<?> dataClass) {
//        Field[] fields = dataClass.getDeclaredFields();
        List<Field> fields = getAllFields(dataClass);
        Map<String, Method> methodNameToMethod = mapMethodName(dataClass);
        for (Field field : fields) {
            String getterName = "get" + capitalizeFirstLetter(field.getName());
            if (!methodNameToMethod.containsKey(getterName)) {
                throw new IllegalStateException(String.format("Field %s doesn't have a getter method", field.getName()));
            }

            Method getter = methodNameToMethod.get(getterName);
            if (!getter.getReturnType().equals(field.getType())) {
                throw new IllegalStateException(
                        String.format("Getter method : %s() has return type %s but expected %s",
                                getter.getName(), getter.getReturnType().getTypeName(), field.getType().getTypeName()));
            }

            if (getter.getParameterCount() > 0) {
                throw new IllegalStateException(
                        String.format("Getter method : %s() has %d arguments",
                                getter.getName(), getter.getParameterCount()));

            }
        }
    }

    private static String capitalizeFirstLetter(String name) {
        return name.substring(0, 1).toUpperCase().concat(name.substring(1));
    }

    private static Map<String, Method> mapMethodName(Class<?> dataClass) {
        Method[] allMethods = dataClass.getMethods();

        return Arrays.stream(allMethods)
                .collect(Collectors.collectingAndThen(Collectors.toMap(Method::getName, Function.identity(), (x, y) -> x),
                        Collections::unmodifiableMap));
    }
}
