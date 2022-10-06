package reflection.annotations.annotation_init_example.app.databases;

import reflection.annotations.annotation_init_example.annotations.InitializerClass;
import reflection.annotations.annotation_init_example.annotations.InitializerMethod;
import reflection.annotations.annotation_init_example.annotations.RetryOperation;

import java.io.IOException;

@InitializerClass
public class DatabaseConnection {

    private int failCounter = 5;

    @InitializerMethod
    public void connectToDatabase1() {
        System.out.println("Connecting to database 1");
    }

    @InitializerMethod
    @RetryOperation(numberOfRetries = 10,
            retryExceptions = IOException.class,
            durationBetweenRetriesMs = 1000,
            failureMessage = "Connection to database 1 failed after retries")
    public void connectToDatabase2() throws IOException {
        System.out.println("Connecting to database 2");
        if (failCounter > 0) {
            failCounter--;
            throw new IOException("Connection failed");
        }
        System.out.println("Connection to database 1 succeeded");
    }
}
