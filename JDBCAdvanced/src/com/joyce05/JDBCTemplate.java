package com.joyce05;

import com.joyce.utils.DataSourceUtils;
import com.joyce05.handler.ResultSetHandler;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCTemplate {
    // 1. define parameter var (dataset, connection, prepared statement, result set)
    private DataSource dataSource;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    // 2. constructor
    public JDBCTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    // read column data
    public Long queryForScalar(String sql, ResultSetHandler<Long> resultSetHandler, Object... objects){
        Long value = null;

        try {
            // 5. get a connection by datasource
            connection = dataSource.getConnection();
            // 6. get prestatement object and parse sql statement
            preparedStatement = connection.prepareStatement(sql);
            ParameterMetaData metaData = preparedStatement.getParameterMetaData();
            int count = metaData.getParameterCount();
            if (count != objects.length){
                throw new RuntimeException("parameter number do not match");
            }

            for(int i=0; i<objects.length; i++){
                preparedStatement.setObject(i+1, objects[i]);
            }

            resultSet = preparedStatement.executeQuery();

            // BeanListHandler process the result
            value =resultSetHandler.handler(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DataSourceUtils.close(connection, preparedStatement,resultSet);
        }

        return value;
    }

    // read method: many records
    public <T> List<T> queryForList(String sql, ResultSetHandler<T> resultSetHandler, Object... objects){
       List<T> list = new ArrayList<>();

        try {
            // 5. get a connection by datasource
            connection = dataSource.getConnection();
            // 6. get prestatement object and parse sql statement
            preparedStatement = connection.prepareStatement(sql);
            ParameterMetaData metaData = preparedStatement.getParameterMetaData();
            int count = metaData.getParameterCount();
            if (count != objects.length){
                throw new RuntimeException("parameter number do not match");
            }

            for(int i=0; i<objects.length; i++){
                preparedStatement.setObject(i+1, objects[i]);
            }

            resultSet = preparedStatement.executeQuery();

            System.out.println("before handler");
            // BeanListHandler process the result
            list =resultSetHandler.handler(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DataSourceUtils.close(connection, preparedStatement,resultSet);
        }

        return list;
    }

    // method of read: encapsulate an object as a self defined object and return
    public <T> T queryForObject(String sql, ResultSetHandler<T> resultSetHandler, Object... objects){
        T obj = null;

        try {
            // 5. get a connection by datasource
            connection = dataSource.getConnection();
            // 6. get prestatement object and parse sql statement
            preparedStatement = connection.prepareStatement(sql);
            ParameterMetaData metaData = preparedStatement.getParameterMetaData();
            int count = metaData.getParameterCount();
            if (count != objects.length){
                throw new RuntimeException("parameter number do not match");
            }

            for(int i=0; i<objects.length; i++){
                preparedStatement.setObject(i+1, objects[i]);
            }

            resultSet = preparedStatement.executeQuery();

            System.out.println("before handler");
            // BeanHandler process the result
            obj =resultSetHandler.handler(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DataSourceUtils.close(connection, preparedStatement,resultSet);
        }

        return obj;
    }


    // 3. method: update
    public int update(String sql, Object...objects){
        // 4. define int var
        int result = 0;
        try {
            // 5. get a connection by datasource
            connection = dataSource.getConnection();
            // 6. get prestatement object and parse sql statement
            preparedStatement = connection.prepareStatement(sql);
            ParameterMetaData metaData = preparedStatement.getParameterMetaData();
            int count = metaData.getParameterCount();
            if (count != objects.length){
                throw new RuntimeException("parameter number do not match");
            }

            for(int i=0; i<objects.length; i++){
                preparedStatement.setObject(i+1, objects[i]);
            }

            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DataSourceUtils.close(connection, preparedStatement);
        }

        return result;
    }






}
