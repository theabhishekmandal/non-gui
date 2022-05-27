package reflection.array_inspection;

import java.lang.reflect.Array;
import java.util.StringJoiner;

/**
 * Example showing how to inspect array objects. This includes any dimension array value
 */
public class Main {
    public static void main(String[] args) {
        int[] arr = {1, 2};
        long[][] brr = {{1, 2}, {3, 4}};
        long[][][] crr = {{{1, 2}, {3, 4}}, {{5, 6}, {7, 8}}};
        inspectArrayObject(arr);
        inspectArrayObject(brr);
        inspectArrayObject(crr);

        inspectArrayValue(arr);
        inspectArrayValue(brr);
        inspectArrayValue(crr);
    }

    public static void inspectArrayObject(Object arrayObject) {
        Class<?> clazz = arrayObject.getClass();
        System.out.printf("Is array : %s%n", clazz.isArray());
        Class<?> arrayComponentType = clazz.getComponentType();
        System.out.printf("This is an array of type : %s%n", arrayComponentType.getTypeName());
    }

    public static void inspectArrayValue(Object arrayObject) {
        System.out.println(getArrayValue(arrayObject));
    }

    private static String getArrayValue(Object arrayObject) {
        int length = Array.getLength(arrayObject);
        StringJoiner stringJoiner = new StringJoiner(",", "[", "]");
        for (int i = 0; i < length; i++) {
            Object arrayElement = Array.get(arrayObject, i);
            if (arrayElement.getClass().isArray()) {
                stringJoiner.add(getArrayValue(arrayElement));
            } else {
                stringJoiner.add(arrayElement.toString());
            }
        }
        return stringJoiner.toString();
    }
}
