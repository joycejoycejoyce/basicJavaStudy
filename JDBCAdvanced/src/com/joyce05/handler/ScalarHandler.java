package com.joyce05.handler;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/*
1. create a class
2. rewrite handler method
3. define a long var
4. check if the result set still have data
5. get object in result set
6, get the first col
7. get col values
8. return result
 */

public class ScalarHandler<T> implements ResultSetHandler<T> {
    //2. rewrite handler method
    @Override
    public Long handler(ResultSet resultSet) {
        // 3. define a long var
        Long value = null;

        // 4. check if the result set still have data
        try {
            if (resultSet.next()){
                // 5. get object in result set
                ResultSetMetaData metaData = resultSet.getMetaData();
                // 6, get the first col
                String columnName = metaData.getColumnName(1);
                // 7. get row values
                value =resultSet.getLong(columnName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return value;
    }
}
