package com.joyce03;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class C3P0Test2 {
    public static void main(String[] args) throws Exception {
        // 1. create a c3p0 database connection obj
        DataSource dataSource = new ComboPooledDataSource("otherc3p0");

        //2. test
        for (int i=1; i<=10; i++){
            Connection connection = dataSource.getConnection();
            System.out.println(i+" :"+connection);

            if (i==5){
                connection.close();;
            }
        }
    }
}
