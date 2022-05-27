package reflection.field_object.object_size_calculator;

import java.lang.reflect.Field;

/**
 * This is an object size calculator. To calculate the size below formula is used.
 *
 * Size of (Object) = {header size} + {object reference} + {sum of the sizes of all its fields}.
 *
 * Assuming header size to be 12 and object references as 4 bytes.
 *
 * Here fields which are primitive and String are considered.
 */
public class ObjectSizeCalculator {
    private static final long HEADER_SIZE = 12;
    private static final long REFERENCE_SIZE = 4;

    private ObjectSizeCalculator() {
    }

    private static final ObjectSizeCalculator OBJECT_SIZE_CALCULATOR = new ObjectSizeCalculator();

    public static ObjectSizeCalculator getInstance() {
        return OBJECT_SIZE_CALCULATOR;
    }

    public long sizeOfObject(Object object) throws IllegalAccessException {
        Field[] fields = object.getClass().getDeclaredFields();
        long size = 0;
        for (Field field : fields) {
            if (field.getType().equals(String.class)) {
                field.setAccessible(true);
               size += sizeOfString((String)field.get(object));
            } else {
                size += sizeOfPrimitiveType(field.getType());
            }
        }

        // original object will also have its header and object reference as well.
        return HEADER_SIZE + REFERENCE_SIZE + size;
    }

    private long sizeOfPrimitiveType(Class<?> primitiveType) {
        if (primitiveType.equals(int.class)) {
            return 4;
        } else if (primitiveType.equals(long.class)) {
            return 8;
        } else if (primitiveType.equals(float.class)) {
            return 4;
        } else if (primitiveType.equals(double.class)) {
            return 8;
        } else if (primitiveType.equals(byte.class)) {
            return 1;
        } else if (primitiveType.equals(short.class)) {
            return 2;
        }
        throw new IllegalArgumentException(String.format("Type: %s is not supported", primitiveType));
    }

    private long sizeOfString(String inputString) {
        int stringBytesSize = inputString.getBytes().length;
        return HEADER_SIZE + REFERENCE_SIZE + stringBytesSize;
    }
}
