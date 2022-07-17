package reflection.method_object;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * here returnedClass will be int.class and actuall return type of actualReturnedClass will be Integer.class.
 */
public class MethodTest {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Cache cache = new Cache();
        Method method = Cache.class.getMethod("getCacheSize");
        Class<?> returnedClass = method.getReturnType();
        Class<?> actualReturnedClass = method.invoke(cache).getClass();
        System.out.println(returnedClass.getSimpleName());
        System.out.println(actualReturnedClass.getSimpleName());
    }

    static class Cache {
        private Map<String, Integer> nameToId = new HashMap<>();

        public void addEntry(String name, Integer id) {
            nameToId.put(name, id);
        }

        public Integer readIdOrThrow(String name) throws Exception {
            if (nameToId.containsKey(name)) {
                return nameToId.get(name);
            }

            throw new IllegalArgumentException(String.format("Name: %s is not in the cache", name));
        }

        public int getCacheSize() {
            return nameToId.size();
        }
    }
}
