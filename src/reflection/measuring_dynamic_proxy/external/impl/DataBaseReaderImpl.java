package reflection.measuring_dynamic_proxy.external.impl;

import reflection.measuring_dynamic_proxy.external.DataBaseReader;

public class DataBaseReaderImpl implements DataBaseReader {
    @Override
    public int countRowsInTable(String tableName) throws InterruptedException {
        System.out.printf("DatabaseReaderImpl - counting rows in table %s", tableName);
        Thread.sleep(1000);
        return 50;
    }

    @Override
    public String[] readRow(String sqlQuery) throws InterruptedException {
        System.out.printf("DatabaseReaderImpl - Executing SQL query : %s", sqlQuery);
        Thread.sleep(1000);
        return new String[]{"column1", "column2", "column3"};
    }
}
