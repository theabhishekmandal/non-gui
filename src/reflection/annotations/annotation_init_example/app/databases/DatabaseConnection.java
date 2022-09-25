package reflection.annotations.annotation_init_example.app.databases;

import reflection.annotations.annotation_init_example.annotations.InitializerClass;
import reflection.annotations.annotation_init_example.annotations.InitializerMethod;

@InitializerClass
public class DatabaseConnection {

    @InitializerMethod
    public void connectToDatabase1() {
        System.out.println("Connecting to database 1");
    }

    @InitializerMethod
    public void connectToDatabase2() {
        System.out.println("Connecting to database 2");
    }
}
