package com.quiz;

import com.joyce.utils.DataSourceUtils;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentRecord {
    // use connection pooling to get db connection
    private Connection connection;

    public void createTable(){
        String sql = "CREATE TABLE students (" +
                "StudentID " +" int primary key auto_increment," +
                "Name " + "varchar(255),"+
                "Gender "+"varchar(255)"+
                ");";

        try {
            connection = DataSourceUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            boolean b = preparedStatement.execute();
            System.out.println("b "+b);
            if(b==true){
                System.out.println("table creation successed");
            }else {
                System.out.println("table creation failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addStudent(Object...objects){
        int result = 0;
        String sql = "INSERT INTO students VALUES (null, ?, ?)";
        connection = DataSourceUtils.getConnection();
        PreparedStatement preparedStatement = null;
        try {

            preparedStatement = connection.prepareStatement(sql);

            ParameterMetaData metaData = preparedStatement.getParameterMetaData();
            int count = metaData.getParameterCount();
            
            if (objects.length != count){
                throw new RuntimeException("parameter number do not match");
            }
            
            for(int i=0; i<objects.length; i++){
                preparedStatement.setObject(i+1, objects[i]);
            }

            result = preparedStatement.executeUpdate();

            if(result > 0){
                System.out.println("insert success");
            }else {
                System.out.println("insert fail");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateStudentName(Object...objects){
        String sql = "UPDATE students SET Name=? WHERE StudentID=?";
        Connection connection = DataSourceUtils.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ParameterMetaData metaData = preparedStatement.getParameterMetaData();
            int count = metaData.getParameterCount();

            if (objects.length != count){
                throw new RuntimeException("parameter number does not match");
            }

            for (int i=0; i<objects.length; i++){
                preparedStatement.setObject(i+1, objects[i]);
            }

            int result = preparedStatement.executeUpdate();

            if (result > 0){
                System.out.println("update success");
            }else {
                System.out.println("update failed");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
