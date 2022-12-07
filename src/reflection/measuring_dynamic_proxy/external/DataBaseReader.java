package reflection.measuring_dynamic_proxy.external;

public interface DataBaseReader {
    int countRowsInTable(String tableName) throws InterruptedException;

    String[] readRow(String sqlQuery) throws InterruptedException;
}
