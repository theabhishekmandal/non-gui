package reflection.field_object.json_serlializer;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Set;
import java.util.StringJoiner;

/**
 * Json converter handling for arrays, String and primitive types
 */
public class JsonConverter {
    private JsonConverter() {
    }

    private static final JsonConverter JSON_CONVERTER = new JsonConverter();

    public static JsonConverter getInstance() {
        return JSON_CONVERTER;
    }

    public String objectToJson(@NotNull Object instance, int indentSize) throws IllegalAccessException {
        Field[] fields = instance.getClass().getDeclaredFields();
        var stringBuilder = new StringBuilder();
        stringBuilder.append(indent(indentSize)).append("{\n");
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            if (!field.isSynthetic()) {
                field.setAccessible(true);
                stringBuilder.append(indent(indentSize + 1))
                        .append(formatString(field.getName())).append(" : ");

                // for primitive types
                if (field.getType().isPrimitive()) {
                    stringBuilder.append(formatPrimitiveValue(field.get(instance), field.getType()));
                } // for string types
                else if (field.getType().equals(String.class)) {
                    stringBuilder.append(formatString(field.get(instance).toString()));
                }
                // for array types
                else if (field.getType().isArray()) {
                    stringBuilder.append(getArrayValue(field.get(instance), indentSize + 1));
                }
                // to parse object inside object
                else {
                    stringBuilder.append(objectToJson(field.get(instance), indentSize + 1));
                }

                if (i != fields.length - 1) {
                    stringBuilder.append(",");
                }

                stringBuilder.append("\n");
            }
        }

        stringBuilder.append(indent(indentSize)).append("}");
        return stringBuilder.toString();
    }

    @NotNull
    private static String indent(int indentSize) {
        StringBuilder br = new StringBuilder();
        br.append("\t".repeat(Math.max(0, indentSize)));
        return br.toString();
    }

    private static final Set<Class<?>> classSet = Set.of(boolean.class, int.class, short.class, long.class);

    private static String formatPrimitiveValue(Object instance, Class<?> type) {
        if (classSet.contains(type)) {
            return instance.toString();
        } else if (type.equals(double.class) || type.equals(float.class)) {
            return String.format("%.02f", instance);
        }
        throw new RuntimeException(String.format("Type : %s is unsupported", type.getName()));
    }

    private static String formatString(String string) {
        return String.format("\"%s\"", string);
    }

    private String getArrayValue(Object arrayObject, int indentSize) throws IllegalAccessException {
        int length = Array.getLength(arrayObject);
        Class<?> arrayType = arrayObject.getClass().getComponentType();
        StringJoiner stringJoiner = new StringJoiner(",\n", "[\n", "\n" + indent(indentSize) + "]");
        for (int i = 0; i < length; i++) {
            Object arrayElement = Array.get(arrayObject, i);
            if (arrayElement.getClass().isArray()) {
                stringJoiner.add(getArrayValue(arrayElement, indentSize + 1));
            } else {
                if (arrayType.isPrimitive()) {
                    stringJoiner.add(indent(indentSize + 1) + formatPrimitiveValue(arrayElement, arrayType));
                } else if (arrayType.equals(String.class)) {
                    stringJoiner.add(indent(indentSize + 1) + formatString(arrayElement.toString()));
                } // if array type is also object
                else {
                    stringJoiner.add(objectToJson(arrayElement, indentSize + 1));
                }
            }
        }
        return stringJoiner.toString();
    }
}
