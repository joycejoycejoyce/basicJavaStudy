package com.joyce04;
/*
1. create a Properties collection, and load config file

2. use Druid Connection factory class to get connection obj

3. get db connection through connection obj

 */


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class DruidTest1{
    public static void main(String[] args) throws Exception{
        // get config file stream
        InputStream inputStream = DruidTest1.class.getClassLoader().getResourceAsStream("druid.properties");

        // 1. create a Properties collection, and load config file
        Properties properties = new Properties();
        properties.load(inputStream);

        // 2. use Druid Connection factory class to get connection obj
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

        // 3. get db connection through connection obj
        Connection connection = dataSource.getConnection();

        // use
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