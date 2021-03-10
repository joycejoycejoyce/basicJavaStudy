package com.joyce04;

import com.joyce.utils.DataSourceUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DruidTest2 {
    public static void main(String[] args) throws Exception{
        // 1. get connection through util class
        Connection connection = DataSourceUtils.getConnection();

        // use
        String sql = "SELECT * FROM student";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // 4. get result from db
        ResultSet resultSet = preparedStatement.executeQuery();

        // 5. process result set
        while (resultSet.next()){
            System.out.println(resultSet.getInt("sid")+ " " + resultSet.getString("name"));
        }

        DataSourceUtils.close(connection, preparedStatement, resultSet);
    }
}
