package reflection.class_object.private_constructor;

import reflection.class_object.private_constructor.web.WebServer;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * This is an example on how to create a singleton object using Reflection.
 */
public class Main {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException, IOException {
        // creating object using private constructor through reflection.
        initConfiguration();
        WebServer webServer = new WebServer();
        webServer.startServer();
    }

    public static void initConfiguration() throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        Constructor<ServerConfiguration> constructor = ServerConfiguration.class
                .getDeclaredConstructor(int.class, String.class);

        constructor.setAccessible(true);
        constructor.newInstance(8080, "Good Day!");
    }
}
