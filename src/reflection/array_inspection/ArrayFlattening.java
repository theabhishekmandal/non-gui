package reflection.array_inspection;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This is an example of how to perform smart Concatenation.
 * Input
 * -   The type T representing the type of elements the method has
 * -   A variable number of arguments
 * The arguments can be of:
 * -   Some type T
 * -   An array of type T
 * -   A combination of arrays of type T and elements of type T.
 */
public class ArrayFlattening {
    public static void main(String[] args) {
        Integer[] arr = (Integer[]) concat(Integer.class, 1, 2, 3, new Integer[]{4, 5, 6});
        int[] primitiveIntegerArray = (int[]) concat(int.class, new int[]{}, 1, 2, 3);
        String[] array = (String[]) concat(String.class, new String[]{}, new String[]{}, "hello", "world");

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(primitiveIntegerArray));
        System.out.println(Arrays.toString(array));
    }

    private static Object concat(Class<?> clazz, Object... args) {
        if (args == null || args.length == 0) {
            return null;
        }
        List<Object> list = new ArrayList<>();
        for (Object arg : args) {
            if (arg.getClass().isArray()) {
                int arrayLength = Array.getLength(arg);
                for (int i = 0; i < arrayLength; i++) {
                    list.add(Array.get(arg, i));
                }
            } else {
                list.add(arg);
            }
        }
        Object main = Array.newInstance(clazz, list.size());
        for (int i = 0; i < list.size(); i++) {
            Array.set(main, i, list.get(i));
        }
        return main;
    }
}
