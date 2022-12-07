package reflection.measuring_dynamic_proxy.caching;

import java.io.IOException;
import java.util.Date;

public interface DataBaseReader {
    void connectToDataBase();

    @Cacheable
    String readCustomerIdByName(String firstName, String lastName) throws IOException;

    int countRowsInCustomersTable();


    void addCustomer(String id, String firstName, String lastName) throws IOException;

    @Cacheable
    Date readCustomerBirthDay(String id);

}
