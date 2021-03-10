package com.joyce.utils;
/*
Data source tool
 */

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DataSourceUtils {
// 1. private constructor
    private DataSourceUtils(){}
    //2. declare var
    private static DataSource dataSource;

    //3. static block. load config file and connection pool
    static {
        try {
            InputStream inputStream = DataSourceUtils.class.getClassLoader().getResourceAsStream("druid.properties");

            Properties properties = new Properties();

            properties.load(inputStream);

            dataSource  = DruidDataSourceFactory.createDataSource(properties);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //4. get a method to do db connection
    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    //5. method: get db connection object
    public static DataSource getDataSource(){
        return dataSource;
    }
    // 6. release resource

    public static void close(Connection connection, Statement statement, ResultSet resultSet){
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection connection, Statement statement){
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
