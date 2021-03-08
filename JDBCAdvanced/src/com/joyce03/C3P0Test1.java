package com.joyce03;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class C3P0Test1 {
    public static void main(String[] args) throws SQLException {
        // 1. create c3p0 db connection object
        DataSource dataSource = new ComboPooledDataSource();

        // 2. get db connection through the connection object
        Connection connection = dataSource.getConnection();

        //3. execute operation
        String sql = "SELECT * FROM student";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // 4. get result from db
        ResultSet resultSet = preparedStatement.executeQuery();

        // 5. process result set
        while (resultSet.next()){
            System.out.println(resultSet.getInt("sid")+ " " + resultSet.getString("name"));
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
