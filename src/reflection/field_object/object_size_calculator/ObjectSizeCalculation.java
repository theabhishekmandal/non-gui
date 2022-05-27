package reflection.field_object.object_size_calculator;

public class ObjectSizeCalculation {
    public static void main(String[] args) throws IllegalAccessException {
        AccountSummary accountSummary = new AccountSummary("Abhishek", "Mandal",
                (short)10, 20, 100_000);

        System.out.println(ObjectSizeCalculator.getInstance().sizeOfObject(accountSummary));
    }
}
