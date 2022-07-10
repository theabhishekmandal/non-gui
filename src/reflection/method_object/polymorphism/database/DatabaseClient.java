package reflection.method_object.polymorphism.database;

public class DatabaseClient {
    public boolean storeData(String data) {
        System.out.printf("Data : %s was successfully stored in database%n", data);
        return true;
    }
}
