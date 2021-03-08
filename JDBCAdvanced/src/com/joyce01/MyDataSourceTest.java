package com.joyce01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MyDataSourceTest {
    public static void main(String[] args) throws Exception{
        // 1. create data source object
        MyDataSource dataSource = new MyDataSource();
        System.out.println("Before use "+dataSource.getSize());
        Connection connection = dataSource.getConnection();

        // 3. check student table info
        String sql = "select * from student";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // 4. execute sql statement
        ResultSet resultSet = preparedStatement.executeQuery();

        // 5. process result set
        while (resultSet.next()){
            System.out.println(resultSet.getInt("sid")+"\t"+
                    resultSet.getString("name")+
                    resultSet.getInt("age")+
                    resultSet.getDate("birthday"));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        System.out.println("After use "+dataSource.getSize());
    }
}
