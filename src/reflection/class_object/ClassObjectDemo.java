package reflection.class_object;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Example showing how different methods on Reflection API gives different types of results.
 */
public class ClassObjectDemo {

    public static void main(String[] args) throws ClassNotFoundException {
        Class<String> stringClass = String.class;
        Map<String, Integer> mapObject = new HashMap<>();
        Class<?> hashMapClass = mapObject.getClass();
        Class<?> squareClass = Class.forName("reflection.class_object.ClassObjectDemo$Square");

        printClassInfo(stringClass, hashMapClass, squareClass);
        System.out.println();


        var circleObject = new Drawable() {
            @Override
            public int getNumberOfCorners() {
                return 0;
            }
        };
        printClassInfo(Collection.class, boolean.class, int[][].class, Color.class, circleObject.getClass());
    }
    private static void printClassInfo(Class<?>... classes) {
        for (Class<?> clazz : classes) {
            System.out.printf("class name : %s, class package name : %s%n",
                    clazz.getSimpleName(), clazz.getPackageName());

            Class<?>[] implementInterfaces = clazz.getInterfaces();

            for (Class<?> implementedInterface : implementInterfaces) {
                System.out.printf("class %s implements : %s%n",
                        clazz.getSimpleName(), implementedInterface.getSimpleName());
            }
            System.out.println("Type name " + clazz.getTypeName());
            System.out.println("Super class is " + clazz.getSuperclass());
            System.out.println("Is array : " + clazz.isArray());
            System.out.println("Is primitive : " + clazz.isPrimitive());
            System.out.println("Is enum : " + clazz.isEnum());
            System.out.println("Is interface : " + clazz.isInterface());
            System.out.println("Is anonymous : " + clazz.isAnonymousClass());

            System.out.println();
            System.out.println();
        }
    }

    private static class Square implements Drawable {
        @Override
        public int getNumberOfCorners() {
            return 4;
        }
    }

    private interface Drawable {
        int getNumberOfCorners();
    }

    private enum Color {
        BLUE,
        RED,
        GREEN
    }
}
