package reflection.measuring_dynamic_proxy.caching;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DataBaseReaderImpl implements DataBaseReader {
    private static final Date date = new Date();
    private final Map<String, Data> customerIdToFirstAndLastName = new HashMap<>();

    @Override
    public void connectToDataBase() {
        System.out.println("connection established");
    }

    @Override
    public String readCustomerIdByName(String firstName, String lastName) throws IOException {
        sleep();
        System.out.printf("firstName = %s, lastName = %s %n", firstName, lastName);
        var valueToBeFound = new Data(firstName, lastName, null);
        return customerIdToFirstAndLastName.entrySet().stream()
                .filter(x -> valueToBeFound.equals(x.getValue()))
                .findAny().map(Map.Entry::getKey).orElse(null);
    }

    @Override
    public int countRowsInCustomersTable() {
        return customerIdToFirstAndLastName.size();
    }

    @Override
    public void addCustomer(String id, String firstName, String lastName) throws IOException {
        sleep();
        System.out.printf("id = %s, firstName = %s, lastName = %s %n", id, firstName, lastName);
        customerIdToFirstAndLastName.put(id, new Data(firstName, lastName, new Date()));
    }

    @Override
    public Date readCustomerBirthDay(String id) {
        sleep();
        Data data = customerIdToFirstAndLastName.get(id);
        return data == null ? date : data.date;
    }

    private static void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Data {
        private final String firstName;
        private final String secondName;
        private final Date date;

        Data(String firstName, String secondName, Date date) {
            this.firstName = firstName;
            this.secondName = secondName;
            this.date = date;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Data data = (Data) o;

            if (!Objects.equals(firstName, data.firstName)) return false;
            return Objects.equals(secondName, data.secondName);
        }

        @Override
        public int hashCode() {
            int result = firstName != null ? firstName.hashCode() : 0;
            result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
            return result;
        }
    }
}
