package reflection.field_object.configuration_parser;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * This class is responsible for reading configuration from .cfg files. It is also able to parse array type properties, required that
 * elements should be separated by comma.
 */
public class ConfigurationParser {
    private ConfigurationParser() {
    }

    private static final ConfigurationParser CONFIGURATION_PARSER = new ConfigurationParser();

    public static ConfigurationParser getInstance() {
        return CONFIGURATION_PARSER;
    }

    public <T> T createConfigObject(Class<T> clazz, Path filePath)
            throws IOException, NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {

        T configInstance;
        try (Scanner scanner = new Scanner(filePath)) {

            Constructor<T> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);

            configInstance = constructor.newInstance();

            while (scanner.hasNext()) {
                String configLine = scanner.nextLine();
                String[] nameValuePair = configLine.split("=");

                if (nameValuePair.length != 2) {
                    continue;
                }

                String propertyName = nameValuePair[0];
                String propertyValue = nameValuePair[1];

                Field field;
                try {
                    field = clazz.getDeclaredField(propertyName);
                } catch (NoSuchFieldException e) {
                    System.out.printf("Property name : %s is unsupported%n", propertyName);
                    continue;
                }
                field.setAccessible(true);
                Object parsedValue;
                if (field.getType().isArray()) {
                    parsedValue = parseArray(field.getType().getComponentType(), propertyValue);
                } else {
                    parsedValue = parse(field.getType(), propertyValue);
                }
                field.set(configInstance, parsedValue);
            }
        }

        return configInstance;
    }

    private static Object parse(Class<?> type, String propertyValue) {
        if (type.equals(String.class)) {
            return propertyValue;
        } else if (type.equals(int.class)) {
            return Integer.parseInt(propertyValue);
        } else if (type.equals(short.class)) {
            return Short.parseShort(propertyValue);
        } else if (type.equals(double.class)) {
            return Double.parseDouble(propertyValue);
        } else if (type.equals(float.class)) {
            return Float.parseFloat(propertyValue);
        } else if (type.equals(long.class)) {
            return Long.parseLong(propertyValue);
        }
        return null;
    }

    private static Object parseArray(Class<?> arrayElementType, String value) {
        String[] elementValues = value.split(",");
        Object arrayObject = Array.newInstance(arrayElementType, elementValues.length);

        for (int i = 0; i < elementValues.length; i++) {
            Array.set(arrayObject, i, parse(arrayElementType, elementValues[i]));
        }

        return arrayObject;
    }
}
