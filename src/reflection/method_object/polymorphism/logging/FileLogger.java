package reflection.method_object.polymorphism.logging;

public class FileLogger {
    public boolean sendRequest(String data) {
        System.out.printf("Data : %s was logged to the file system\n", data);
        return true;
    }
}
